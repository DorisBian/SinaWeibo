package com.bzy.weibo.ui.presenter;

import com.bzy.weibo.api.WeiBoApi;
import com.bzy.weibo.api.WeiBoFactory;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Base of Presenter
 */
public abstract class BasePresenter<T> {

    protected Reference<T> mViewRef;

    public static final WeiBoApi weiBoApi = WeiBoFactory.getWeiBoApiSingleton();

    public void attachView(T view){
        mViewRef = new WeakReference<T>(view);
    }

    protected T getView(){
        return mViewRef.get();
    }

    public boolean isViewAttached(){
        return mViewRef != null&&mViewRef.get()!=null;
    }

    public void detachView(){
        if(mViewRef!=null){
            mViewRef.clear();
            mViewRef = null;
        }
    }

}
