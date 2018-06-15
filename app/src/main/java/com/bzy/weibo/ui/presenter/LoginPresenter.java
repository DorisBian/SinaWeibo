package com.bzy.weibo.ui.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.bzy.weibo.R;
import com.bzy.weibo.ui.view.ILoginView;
import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;

/**
 * Presenter about Login
 */
public class LoginPresenter extends BasePresenter<ILoginView> {
    static final String TAG = "LoginPresenter";
    private SsoHandler mSsoHandler;
    private Oauth2AccessToken mAccessToken;
    private ILoginView loginView;
    private Activity activity;

    public LoginPresenter(Activity activity) {
        this.activity = activity;
    }

    public void initRequestInfo() {
        mSsoHandler = new SsoHandler(activity);
    }

    //回调
    public void ssoCallBack(int requestCode, int resultCode, Intent data) {
        if (mSsoHandler != null) {
            mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
    }

    //登陆
    public void login() {
        mSsoHandler.authorize(new WbAuthListener() {
            @Override
            public void onSuccess(Oauth2AccessToken oauth2AccessToken) {
                mAccessToken = oauth2AccessToken;
                if (!mAccessToken.isSessionValid()) {
                    Toast.makeText(activity,
                            "认证失败", Toast.LENGTH_SHORT).show();
                    return;
                }
//                //show token
                if (isViewAttached()) {
                    loginView = getView();
                    loginView.showTokenInfo(false, mAccessToken);
                }
                //save token
                writeToken(mAccessToken, activity);

                Toast.makeText(activity,
                        R.string.weibo_auth_success, Toast.LENGTH_SHORT).show();
                //go to home
                loginView.toMainActivity();
            }

            @Override
            public void cancel() {
                Toast.makeText(activity,
                        R.string.weibo_auth_canceled, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(WbConnectErrorMessage wbConnectErrorMessage) {
                Log.e(TAG, wbConnectErrorMessage.getErrorCode());
                Log.e(TAG, wbConnectErrorMessage.getErrorMessage());
                Toast.makeText(activity,
                        R.string.weibo_auth_exception, Toast.LENGTH_LONG).show();
            }
        });
    }


    public void getUserInfo() {
        new SplashPresenter(activity).getUserInfo();
    }

    private void writeToken(Oauth2AccessToken mAccessToken, Context context) {
        AccessTokenKeeper.writeAccessToken(context, mAccessToken);
    }

    public Oauth2AccessToken readToken(Context context) {
        return AccessTokenKeeper.readAccessToken(context);
    }
}
