package com.bzy.weibo.ui.view;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;

/**
 *
 *
 */
public interface ILoginView {

    void showTokenInfo(boolean hasExisted,Oauth2AccessToken mAccessToken);
    void toMainActivity();
}
