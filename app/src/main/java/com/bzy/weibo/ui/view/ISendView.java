package com.bzy.weibo.ui.view;

import android.support.v7.widget.RecyclerView;

/**
 *
 *
 */
public interface ISendView {
    void permissionSetting();
    RecyclerView getPhotoGrid();
    void finishAndToast();
}
