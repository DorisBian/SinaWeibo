package com.bzy.weibo.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bzy.weibo.MyApp;
import com.bzy.weibo.R;
import com.bzy.weibo.bean.Status;
import com.bzy.weibo.bean.User;
import com.bzy.weibo.ui.activity.SendWeiBoActivity;
import com.bzy.weibo.ui.base.MVPBaseFragment;
import com.bzy.weibo.ui.presenter.HomePresenter;
import com.bzy.weibo.ui.presenter.MyWeiboPresenter;
import com.bzy.weibo.ui.view.IHomeView;
import com.bzy.weibo.ui.view.IMyWeiboView;
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
public class MyWeiboFragment extends MVPBaseFragment<IMyWeiboView, MyWeiboPresenter> implements IMyWeiboView {

    private LinearLayoutManager mLayoutManager;

    @Bind(R.id.content_list)
    RecyclerView mRecyclerView;
    private User user;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    protected MyWeiboPresenter createPresenter() {
        return new MyWeiboPresenter(getContext());
    }

    @Override
    protected int createViewLayoutId() {
        return R.layout.fragment_my_weibo;
    }

    @Override
    protected void initView(View rootView) {
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setDataRefresh(true);
        mPresenter.getUserWeiBoTimeLine(this.user.getIdstr());
        mPresenter.scrollRecycleView(this.user.getIdstr());
    }

    @Override
    public void requestDataRefresh() {
        super.requestDataRefresh();
        setDataRefresh(true);
        mPresenter.getUserWeiBoTimeLine(user.getIdstr());
    }

    @Override
    public void setDataRefresh(Boolean refresh) {
        setRefresh(refresh);
    }

    @Override
    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    @Override
    public LinearLayoutManager getLayoutManager() {
        return mLayoutManager;
    }
}
