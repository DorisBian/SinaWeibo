package com.bzy.weibo;

import android.app.Application;
import android.content.Context;

import com.bzy.weibo.info.Constants;
import com.litesuits.orm.LiteOrm;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AuthInfo;

/**
 * <p>
 * <p>
 * Application about LiteOrm
 */
public class MyApp extends Application {

    private static final String DB_NAME = "weibo.db";
    public static LiteOrm mDb;

    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        WbSdk.install(this, new AuthInfo(this, Constants.APP_KEY, Constants.REDIRECT_URL,
                Constants.SCOPE));
        //init LiteOrm
        mDb = LiteOrm.newSingleInstance(this, DB_NAME);
        if (BuildConfig.DEBUG) {
        }
        mDb.setDebugged(true);

        mContext = getApplicationContext();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
