package com.bzy.weibo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bzy.weibo.MyApp;
import com.bzy.weibo.R;
import com.bzy.weibo.bean.User;
import com.bzy.weibo.ui.fragment.FavoritesFragment;
import com.bzy.weibo.ui.fragment.HeadFragment;
import com.bzy.weibo.ui.fragment.MyWeiboFragment;
import com.bzy.weibo.ui.presenter.MainPresenter;
import com.bzy.weibo.ui.base.MVPBaseActivity;
import com.bzy.weibo.ui.view.IMainView;
import com.bzy.weibo.util.RxBus;
import com.bzy.weibo.util.RxEvents;
import com.bzy.weibo.widget.ClickCircleImageView;
import com.sina.weibo.sdk.auth.AccessTokenKeeper;

import butterknife.Bind;

/**
 * DrawerLayout and fragment
 */
public class MainActivity extends MVPBaseActivity<IMainView, MainPresenter> implements IMainView {

    private ActionBarDrawerToggle mDrawerToggle;

    @Bind(R.id.main_draw_layout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.left_draw)
    NavigationView mNavigationView;
    private ImageView userBackGround;
    private ClickCircleImageView userIcon;
    private TextView userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDrawer();
        initHeadView();
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.setHeadViewWithUser();
    }

    private void initDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);
        // now we can open drawer with btn
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    private void initHeadView() {
        View headerView = mNavigationView.inflateHeaderView(R.layout.left_draw_header);
        userBackGround = (ImageView) headerView.findViewById(R.id.iv_user_bg);
        userIcon = (ClickCircleImageView) headerView.findViewById(R.id.iv_user_icon);
        userName = (TextView) headerView.findViewById(R.id.tv_user_name);

        mNavigationView.setCheckedItem(R.id.head);
        replaceFragment(new HeadFragment());
        mNavigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.head:
                    replaceFragment(new HeadFragment());
                    break;
                case R.id.favourite:
                    replaceFragment(new FavoritesFragment());
                    break;
                case R.id.my:
                    replaceFragment(new MyWeiboFragment());
                    break;
                case R.id.settings:
                    exit();
                    break;

            }
            item.setChecked(true);
            mDrawerLayout.closeDrawers();
            return true;
        });
    }

    private void exit() {
        MyApp.mDb.delete(User.class);
        AccessTokenKeeper.clear(getApplicationContext());
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.destroyPic();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.up_refresh) {
            RxBus.getInstance().send(new RxEvents.UpRefreshClick());
            return true;
        } else if (item.getItemId() == R.id.menu_notification) {
            startActivity(new Intent(this, MessageActivity.class));
            return true;
        } else if (item.getItemId() == R.id.menu_search) {
            startActivity(new Intent(this, SearchTopicActivity.class));
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public ImageView getUserBackGround() {
        return userBackGround;
    }

    @Override
    public ClickCircleImageView getUserIcon() {
        return userIcon;
    }

    @Override
    public TextView getUserName() {
        return userName;
    }


}
