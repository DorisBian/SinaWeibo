package com.bzy.weibo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.bzy.weibo.BuildConfig;
import com.bzy.weibo.R;
import com.bzy.weibo.ui.presenter.SplashPresenter;
import com.bzy.weibo.ui.base.MVPBaseActivity;
import com.bzy.weibo.ui.view.ISplashView;
import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.bzy.weibo.widget.splash.SplashView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * SplashActivity like Twitter !
 */
public class SplashActivity extends MVPBaseActivity<ISplashView, SplashPresenter> implements ISplashView {

    private static final String TAG = "SplashActivity";

    private Handler mHandler = new Handler();

    @Bind(R.id.splash_view)
    SplashView splash_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mPresenter = createPresenter();
    }

    @Override
    protected SplashPresenter createPresenter() {
        return new SplashPresenter(SplashActivity.this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.getUserInfo();
        startLoadingData();
    }

    /**
     * start splash animation
     */
    private void startLoadingData() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                onLoadingDataEnded();
            }
        }, 1500);
    }

    private void onLoadingDataEnded() {
        loginOrMain();
    }

    @Override
    public void loginOrMain() {
        //获取token
        Oauth2AccessToken token = AccessTokenKeeper.readAccessToken(SplashActivity.this);
        //token在session会话有效期内
        if (token.isSessionValid()) {
            if (BuildConfig.DEBUG) {
                Log.d(TAG, token.toString() + "--uid-" + token.getUid());
            }
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
        } else {
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        }
        finish();
    }

}
