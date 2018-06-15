package com.bzy.weibo.ui.presenter;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.bzy.weibo.R;
import com.bzy.weibo.bean.FriendsTimeLine;
import com.bzy.weibo.bean.Status;
import com.bzy.weibo.ui.adapter.WeiBoListAdapter;
import com.bzy.weibo.ui.view.IMyWeiboView;
import com.bzy.weibo.util.PrefUtils;
import com.google.gson.Gson;
import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 */
public class MyWeiboPresenter extends BasePresenter<IMyWeiboView> {

    private Context context;
    private IMyWeiboView myWeiboView;
    private RecyclerView recyclerView;
    private WeiBoListAdapter adapter;
    private List<Status> list = new ArrayList<>();
    private LinearLayoutManager layoutManager;
    private int lastVisibleItem;
    private boolean isLoadMore = false; // 是否加载过更多

    public MyWeiboPresenter(Context context) {
        this.context = context;
    }

    public void getUserWeiBoTimeLine(String uid) {
        myWeiboView = getView();
        if (myWeiboView != null) {
            recyclerView = myWeiboView.getRecyclerView();
            layoutManager = myWeiboView.getLayoutManager();
            Oauth2AccessToken token = readToken(context);
            if (token.isSessionValid()) {
                weiBoApi.getUserWeiBoTimeLine(getRequestMap(token.getToken(), uid))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(friendsTimeLine -> {
                            disPlayWeiBoList(friendsTimeLine, context, myWeiboView, recyclerView);
                            writeToSD(friendsTimeLine);
//                            Log.e("my", );
                        }, this::loadError);
            }
        }
    }

    private void writeToSD(FriendsTimeLine friendsTimeLine) {
        String res = new Gson().toJson(friendsTimeLine);
        File file = new File("/sdcard/search.json");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
//        StringWriter stringWriter=new StringWriter();
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
            bufferedWriter.write(res);
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadError(Throwable throwable) {
        throwable.printStackTrace();
        Toast.makeText(context, R.string.load_error, Toast.LENGTH_SHORT).show();
        if (myWeiboView != null) {
            myWeiboView.setDataRefresh(false);
        }
    }

    String max_id;

    // get request params
    private Map<String, Object> getRequestMap(String token, String uid) {
        max_id = PrefUtils.getString(context, "user_max_id", "0");
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", token);
        map.put("uid", uid);
        if (isLoadMore) {
            map.put("max_id", Long.valueOf(max_id));
        }
        return map;
    }

    // refresh data
    private void disPlayWeiBoList(FriendsTimeLine friendsTimeLine, Context context, IMyWeiboView userView, RecyclerView recyclerView) {
        List<Status> statuses = friendsTimeLine.getStatuses();
        if (isLoadMore) {
            if (max_id.equals("0")) {
                adapter.updateLoadStatus(adapter.LOAD_NONE);
                return;
            }
            if (statuses.size() == 0) {
                adapter.updateLoadStatus(adapter.LOAD_NONE);
                userView.setDataRefresh(false);
                return;
            } else if (statuses.size() == 1) {
                list.addAll(statuses);
            } else {
                list.addAll(statuses.subList(1, statuses.size() - 1));
            }
            adapter.notifyDataSetChanged();
        } else {
            list = friendsTimeLine.getStatuses();
            adapter = new WeiBoListAdapter(context, list, "home_fg");
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        userView.setDataRefresh(false);
        // save max_id
        PrefUtils.setString(context, "user_max_id", statuses.get(statuses.size() - 1).getIdstr());
    }

    /**
     * recyclerView Scroll listener
     */
    public void scrollRecycleView(String uid) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    lastVisibleItem = layoutManager
                            .findLastVisibleItemPosition();
                    if (lastVisibleItem + 1 == layoutManager
                            .getItemCount()) {
                        adapter.updateLoadStatus(adapter.LOAD_PULL_TO);
                        isLoadMore = true;
                        adapter.updateLoadStatus(adapter.LOAD_MORE);
                        new Handler().postDelayed(() -> getUserWeiBoTimeLine(uid), 1000);
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
            }
        });
    }


    private Oauth2AccessToken readToken(Context context) {
        return AccessTokenKeeper.readAccessToken(context);
    }
}
