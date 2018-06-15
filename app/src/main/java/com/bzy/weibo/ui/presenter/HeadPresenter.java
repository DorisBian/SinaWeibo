package com.bzy.weibo.ui.presenter;

import android.content.Context;

import com.bzy.weibo.ui.view.IHeadView;

/**
 *
 */
public class HeadPresenter extends BasePresenter<IHeadView> {

    private static final String TAG = "HeadPresenter";

    private Context context;
    private IHeadView headView;

    public HeadPresenter(Context ctx) {
        this.context = ctx;
    }
}
