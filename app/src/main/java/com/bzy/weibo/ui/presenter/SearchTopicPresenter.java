package com.bzy.weibo.ui.presenter;


import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.Toast;

import com.bzy.weibo.MyApp;
import com.bzy.weibo.R;
import com.bzy.weibo.bean.FriendsTimeLine;
import com.bzy.weibo.bean.SearchBean;
import com.bzy.weibo.bean.SearchResultBean;
import com.bzy.weibo.bean.Status;
import com.bzy.weibo.ui.adapter.TopicListAdapter;
import com.bzy.weibo.ui.view.ISearchTopicView;
import com.google.gson.Gson;
import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * MainPresenter init some data
 */
public class SearchTopicPresenter extends BasePresenter<ISearchTopicView> {

    private static final String TAG = "SearchTopicPresenter";

    private Context context;
    private ISearchTopicView homeView;
    private RecyclerView recyclerView;
    private TopicListAdapter adapter;
    private List<Status> list = new ArrayList<>();
    private List<Status> localList = new ArrayList<>();
    private List<Status> searchedList = new ArrayList<>();
    private LinearLayoutManager layoutManager;
    private int lastVisibleItem;
    private boolean isLoadMore = false; // 是否加载过更多
    String key = "";
    FriendsTimeLine friendsTimeLine;

    int page = 1;
    final static int count = 10;

    public SearchTopicPresenter(Context ctx) {
        this.context = ctx;
    }

    /**
     * 刷新
     *
     * @param key
     */
    public void getWeiBoTimeLine(String key) {
        this.key = key;
        page = 1;
        homeView = getHomeView();
        if (homeView != null) {
            recyclerView = homeView.getRecyclerView();
            layoutManager = homeView.getLayoutManager();

            Oauth2AccessToken token = readToken(context);
            List<Status> showList = null;
            //本地的
            getFromLocal();
            if (TextUtils.isEmpty(key)) {
                showList = localList;
            } else {
                getFromLocalByKey(key);
                showList = searchedList;
            }
            isLoadMore = false;
            disPlayWeiBoList(isLoadMore, showList, context, homeView, recyclerView);
//            if (token.isSessionValid()) {
//                String tokenStr = token.getToken();
//                weiBoApi.searchTopic(getRequestMap(tokenStr))
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(searchBean ->
//                                {
//                                    disPlayWeiBoList(searchBean, context, homeView, recyclerView);
//                                }
//                                , this::loadError);
//            }
        }
    }

    /**
     * 刷新
     */
    public void getMoreWeiBoTimeLine() {
        homeView = getHomeView();
        if (homeView != null) {
            recyclerView = homeView.getRecyclerView();
            layoutManager = homeView.getLayoutManager();
            List<Status> more = new ArrayList<>();
            disPlayWeiBoList(true, more, context, homeView, recyclerView);
//            if (token.isSessionValid()) {
//                String tokenStr = token.getToken();
//                weiBoApi.searchTopic(getRequestMap(tokenStr))
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(searchBean ->
//                                {
//                                    disPlayWeiBoList(searchBean, context, homeView, recyclerView);
//                                }
//                                , this::loadError);
//            }
        }
    }

    /**
     * 本地搜索
     */
    private List<Status> getFromLocal() {
        if (localList != null && localList.size() > 0)
            return localList;
        try {
            InputStream inputStream = context.getAssets().open("search.json");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            friendsTimeLine = new Gson().fromJson(bufferedReader, FriendsTimeLine.class);
            return localList = friendsTimeLine.getStatuses();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 搜索
     *
     * @param key
     */
    private void getFromLocalByKey(String key) {
        searchedList.clear();
        localList = getFromLocal();
        for (Status status : localList) {
            if (status.getText().contains(key) || (status.getRetweeted_status() != null && status.getRetweeted_status().getText().contains(key))) {
                searchedList.add(status);
                //把搜索到的保存到搜索库
//                SearchResult searchResult = new SearchResult();
//                searchResult.setKey(key);
//                searchResult.setStatusId(status.getId());
//                new SearchDaoUtils(context).insertSearchResult(searchResult);

                SearchResultBean searchResult = new SearchResultBean();
                searchResult.setKey(key);
                searchResult.setStatusId(status.getId());
                MyApp.mDb.insert(searchResult);

            }
        }
    }

    // 点赞POST请求
    public void setLike(String id) {
        Oauth2AccessToken token = readToken(context);
        weiBoApi.setLike(getLikeMap(token.getToken(), id))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(postComments -> {

                });
    }

    private ISearchTopicView getHomeView() {
        if (isViewAttached()) {
            return getView();
        } else {
            return null;
        }
    }

    private void loadError(Throwable throwable) {
        throwable.printStackTrace();
        Toast.makeText(context, R.string.load_error, Toast.LENGTH_SHORT).show();
        if (homeView != null) {
            homeView.setDataRefresh(false);
        }
    }

    private Oauth2AccessToken readToken(Context context) {
        return AccessTokenKeeper.readAccessToken(context);
    }

    String max_id;

    // get request params
    private Map<String, Object> getRequestMap(String token) {
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", token);
        map.put("q", key);
        map.put("count", count);
        map.put("page", page);

        return map;
    }

    private Map<String, Object> getLikeMap(String token, String id) {
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", token);
        map.put("attitude", "simle");
        map.put("id", id);
        return map;
    }

    // refresh data
    private void disPlayWeiBoList(boolean isLoadMore, List<Status> target, Context context, ISearchTopicView homeView, RecyclerView recyclerView) {
        if (isLoadMore) {
            if (target == null || target.size() == 0) {
                adapter.updateLoadStatus(adapter.LOAD_NONE);
            } else {
                list.addAll(target);
                adapter.notifyDataSetChanged();
            }
        } else {
            list = target;
            if (recyclerView.getAdapter() == null) {
                adapter = new TopicListAdapter(context, list, "home_fg");
                recyclerView.setAdapter(adapter);
            }
            adapter.notifyDataSetChanged();
            if (list == null || list.size() == 0) {
                adapter.updateLoadStatus(adapter.LOAD_NONE);
            }
        }
        homeView.setDataRefresh(false);
    }

    private List<Status> getStatusData(SearchBean searchBean) {
        return searchBean.getStatuses();
    }

    /**
     * recyclerView Scroll listener , maybe in here is wrong ?
     */

    public void scrollRecycleView() {
        if (recyclerView == null && getHomeView() != null)
            recyclerView = getHomeView().getRecyclerView();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (adapter == null) return;
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    lastVisibleItem = layoutManager
                            .findLastVisibleItemPosition();
                    if (lastVisibleItem + 1 == layoutManager
                            .getItemCount()) {//加载更多
                        adapter.updateLoadStatus(adapter.LOAD_PULL_TO);
                        isLoadMore = true;
                        adapter.updateLoadStatus(adapter.LOAD_MORE);
                        new Handler().postDelayed(() -> getMoreWeiBoTimeLine(), 1000);
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

}
