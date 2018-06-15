package com.bzy.weibo.ui.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 *
 *
 */
public interface IMyWeiboView {

    void setDataRefresh(Boolean refresh);
    RecyclerView getRecyclerView();
    LinearLayoutManager getLayoutManager();
}
