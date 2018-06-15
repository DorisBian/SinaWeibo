package com.bzy.weibo.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bzy.weibo.MyApp;
import com.bzy.weibo.R;
import com.bzy.weibo.bean.Status;
import com.bzy.weibo.bean.User;
import com.bzy.weibo.ui.activity.FavoritesAndPhotoActivity;
import com.bzy.weibo.ui.activity.SendWeiBoActivity;
import com.bzy.weibo.ui.base.MVPBaseFragment;
import com.bzy.weibo.ui.presenter.FAPPresenter;
import com.bzy.weibo.ui.presenter.HomePresenter;
import com.bzy.weibo.ui.view.IFAPView;
import com.bzy.weibo.ui.view.IHomeView;
import com.bzy.weibo.util.RxBus;
import com.bzy.weibo.util.RxEvents;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;
import static com.bzy.weibo.ui.adapter.WeiBoListAdapter.REQ_REPOST;

/**
 * fragment of home weibo to display
 */
public class FavoritesFragment extends MVPBaseFragment<IFAPView, FAPPresenter> implements IFAPView {

    private static final String TAG = "tag";
    private static final String USER = "user";

    private String tag;
    private User user;

    private LinearLayoutManager mLayoutManager;
    private GridLayoutManager gridLayoutManager;
    @Bind(R.id.f_and_p_list)
    RecyclerView f_and_p_list;

    @Override
    protected FAPPresenter createPresenter() {
        return new FAPPresenter(getContext());
    }

    @Override
    protected int createViewLayoutId() {
        return R.layout.fragment_f_and_p;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parseIntent();
    }

    private void parseIntent() {
        tag = "favorites";
        user = getUserInfoFromDB();
    }

    private User getUserInfoFromDB() {
        ArrayList<User> query = MyApp.mDb.query(User.class);
        if (query.size() > 0) {
            return query.get(0);
        } else {
            return null;
        }
    }

    @Override
    public void initView(View view) {
        if (tag.equals("favorites")) {
            mLayoutManager = new LinearLayoutManager(getActivity());
            f_and_p_list.setLayoutManager(mLayoutManager);
            setDataRefresh(true);
            mPresenter.getFavorites();
            mPresenter.scrollRecycleView(user.getIdstr());
        } else if (tag.equals("photo")) {
            gridLayoutManager = new GridLayoutManager(getActivity(), 3);
            f_and_p_list.setLayoutManager(gridLayoutManager);
            setDataRefresh(true);
            mPresenter.getPhoto(user.getIdstr());
        }
    }

    @Override
    public void setDataRefresh(Boolean refresh) {
        setRefresh(refresh);
    }

    @Override
    public RecyclerView getRecyclerView() {
        return f_and_p_list;
    }

    @Override
    public LinearLayoutManager getLayoutManager() {
        return mLayoutManager;
    }

    @Override
    public GridLayoutManager getGridLayoutManager() {
        return gridLayoutManager;
    }

    @Override
    public String getTag1() {
        return tag;
    }


//
//
//
//
//
//    @Override
//    protected void initView(View rootView) {
//        mLayoutManager = new LinearLayoutManager(getContext());
//        mRecyclerView.setLayoutManager(mLayoutManager);
//    }
//
//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        setDataRefresh(true);
//        mPresenter.getWeiBoTimeLine();
//        mPresenter.scrollRecycleView();
//
//        RxBus.getInstance().toObserverable().subscribe(event -> {
//            if (event instanceof RxEvents.UpRefreshClick) {
//                mRecyclerView.smoothScrollToPosition(0);
//                requestDataRefresh();
//            } else if (event instanceof RxEvents.WeiBoSetLike) {
//                RxEvents.WeiBoSetLike like = (RxEvents.WeiBoSetLike) event;
//            } else if (event instanceof RxEvents.AddedWeibo) {//新微博
//                mPresenter.showSendWeibo(((RxEvents.AddedWeibo) event).status);
//            }
//        });
//    }
//
//    @Override
//    public void requestDataRefresh() {
//        super.requestDataRefresh();
//        setDataRefresh(true);
//        mPresenter.getWeiBoTimeLine();
//    }
//
//    @Override
//    public void setDataRefresh(Boolean refresh) {
//        setRefresh(refresh);
//    }
//
//    @Override
//    public RecyclerView getRecyclerView() {
//        return mRecyclerView;
//    }
//
//    @Override
//    public LinearLayoutManager getLayoutManager() {
//        return mLayoutManager;
//    }
//
//    public static final int SEND_REQ_CODE = 100;
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK && data != null) {
//            switch (requestCode) {
//                case SEND_REQ_CODE:
//                    WeiboStatus status = (WeiboStatus) data.getSerializableExtra("weibo");
//                    mPresenter.showSendWeibo(status);
//                    break;
//                case REQ_REPOST:
//                    WeiboStatus statusR = (WeiboStatus) data.getSerializableExtra("weibo");
//                    mPresenter.showSendWeibo(statusR);
//                    break;
//            }
//        }
//    }
//
//    //发微博
//    @OnClick(R.id.send_weibo)
//    void sendWeibo() {
//        startActivityForResult(new Intent(getActivity(), SendWeiBoActivity.class), SEND_REQ_CODE);
//    }
}
