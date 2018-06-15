package com.bzy.weibo.ui.presenter;

import android.content.Context;

import com.bzy.weibo.ui.view.IMessageView;

/**
 *
 */
public class MessagePresenter extends BasePresenter<IMessageView> {

    private Context context;

    public MessagePresenter(Context context) {
        this.context = context;
    }
}
