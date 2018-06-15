package com.bzy.weibo.ui.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 *
 *
 */
public interface ITabView {

    String getWeiBoId();
    RecyclerView getRecyclerView();
    LinearLayoutManager getLayoutManager();
}
