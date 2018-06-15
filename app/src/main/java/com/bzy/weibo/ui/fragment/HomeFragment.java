package com.bzy.weibo.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bzy.weibo.R;
import com.bzy.weibo.bean.Status;
import com.bzy.weibo.ui.presenter.HomePresenter;
import com.bzy.weibo.ui.activity.SendWeiBoActivity;
import com.bzy.weibo.ui.base.MVPBaseFragment;
import com.bzy.weibo.ui.view.IHomeView;
import com.bzy.weibo.util.RxBus;
import com.bzy.weibo.util.RxEvents;

import butterknife.Bind;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;
import static com.bzy.weibo.ui.adapter.WeiBoListAdapter.REQ_REPOST;

/**
 * fragment of home weibo to display
 */
public class HomeFragment extends MVPBaseFragment<IHomeView, HomePresenter> implements IHomeView {

    private LinearLayoutManager mLayoutManager;

    @Bind(R.id.content_list)
    RecyclerView mRecyclerView;

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(getContext());
    }

    @Override
    protected int createViewLayoutId() {
        return R.layout.fragment_home;
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
        mPresenter.getWeiBoTimeLine();
        mPresenter.scrollRecycleView();

        RxBus.getInstance().toObserverable().subscribe(event -> {
            if (event instanceof RxEvents.UpRefreshClick) {
                mRecyclerView.smoothScrollToPosition(0);
                requestDataRefresh();
            } else if (event instanceof RxEvents.WeiBoSetLike) {
                RxEvents.WeiBoSetLike like = (RxEvents.WeiBoSetLike) event;
            } else if (event instanceof RxEvents.AddedWeibo) {//新微博
                mPresenter.showSendWeibo(((RxEvents.AddedWeibo) event).status);
            }
        });
    }

    @Override
    public void requestDataRefresh() {
        super.requestDataRefresh();
        setDataRefresh(true);
        mPresenter.getWeiBoTimeLine();
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

    public static final int SEND_REQ_CODE = 100;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            switch (requestCode) {
                case SEND_REQ_CODE:
                    Status status = (Status) data.getSerializableExtra("weibo");
                    mPresenter.showSendWeibo(status);
                    break;
                case REQ_REPOST:
                    Status statusR = (Status) data.getSerializableExtra("weibo");
                    mPresenter.showSendWeibo(statusR);
                    break;
            }
        }
    }

    //发微博
    @OnClick(R.id.send_weibo)
    void sendWeibo() {
        startActivityForResult(new Intent(getActivity(), SendWeiBoActivity.class), SEND_REQ_CODE);
    }
}
