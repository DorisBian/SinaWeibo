package com.bzy.weibo.ui.fragment;

import android.content.Intent;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;

import com.bzy.weibo.R;
import com.bzy.weibo.ui.activity.SearchTopicActivity;
import com.bzy.weibo.ui.base.MVPBaseFragment;
import com.bzy.weibo.ui.presenter.HeadPresenter;
import com.bzy.weibo.ui.view.IHeadView;

import butterknife.Bind;

/**
 * fragment of find something
 */
public class HeadFragment extends MVPBaseFragment<IHeadView, HeadPresenter> implements IHeadView {

    @Override
    protected HeadPresenter createPresenter() {
        return new HeadPresenter(getActivity());
    }

    @Override
    protected int createViewLayoutId() {
        return R.layout.fragment_head;
    }

    @Bind(R.id.navigation)
    BottomNavigationView navigation;
    @Bind(R.id.fram)
    FrameLayout frameLayout;

    HomeFragment homeFragment = null;
    SearchFragment searchFragment = null;
    Fragment currFragment = null;

    @Override
    protected void initView(View rootView) {
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        currFragment = getHomeFragment();
        getChildFragmentManager().beginTransaction().replace(R.id.fram, currFragment).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                //展示首页
                if (currFragment != getHomeFragment()) {
                    currFragment = getHomeFragment();
                    getChildFragmentManager().beginTransaction().replace(R.id.fram, currFragment).commit();
                }
                return true;
            case R.id.navigation_search:
                //
                getActivity().startActivity(new Intent(getActivity(), SearchTopicActivity.class));

                if (currFragment != getSearchFragment()) {
//                    currFragment = getSearchFragment();
//                    getChildFragmentManager().beginTransaction().replace(R.id.fram, currFragment).commit();
                }
                return true;
        }
        return false;
    };

    public HomeFragment getHomeFragment() {
        if (homeFragment == null)
            homeFragment = new HomeFragment();
        return homeFragment;
    }

    public SearchFragment getSearchFragment() {
        if (searchFragment == null)
            searchFragment = new SearchFragment();
        return searchFragment;
    }
}
