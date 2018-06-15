package com.bzy.weibo.ui.presenter;

import android.content.Context;

import com.bzy.weibo.ui.view.IFriendsView;

/**
 *
 */
public class FriendShipsPresenter extends BasePresenter<IFriendsView> {

    private Context context;

    public FriendShipsPresenter(Context context) {
        this.context = context;
    }
}
