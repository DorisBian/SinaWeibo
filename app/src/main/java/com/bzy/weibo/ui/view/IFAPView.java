package com.bzy.weibo.ui.view;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 *
 *
 */
public interface IFAPView {
    void setDataRefresh(Boolean refresh);
    RecyclerView getRecyclerView();
    LinearLayoutManager getLayoutManager();
    GridLayoutManager getGridLayoutManager();
    String getTag1();
}
