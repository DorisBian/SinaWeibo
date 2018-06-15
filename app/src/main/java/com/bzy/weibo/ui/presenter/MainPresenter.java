package com.bzy.weibo.ui.presenter;


import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bzy.weibo.MyApp;
import com.bzy.weibo.bean.User;
import com.bzy.weibo.ui.view.IMainView;
import com.bzy.weibo.widget.ClickCircleImageView;

import java.util.ArrayList;

/**
 * MainPresenter init some data
 */
public class MainPresenter extends BasePresenter<IMainView> {

    private FragmentActivity activity;
    private IMainView mainView;
    private ImageView userBackGround;
    private ClickCircleImageView userIcon;

    public MainPresenter(FragmentActivity activity) {
        this.activity = activity;
    }

    private User getUserInfoFromDB() {
        ArrayList<User> query = MyApp.mDb.query(User.class);
        if (query.size() > 0) {
            return query.get(0);
        } else {
            return null;
        }
    }

    public void setHeadViewWithUser() {
        User OAuthUser = getUserInfoFromDB();
        if (OAuthUser != null && isViewAttached()) {
            mainView = getView();
            userBackGround = mainView.getUserBackGround();
            userIcon = mainView.getUserIcon();
            mainView.getUserName().setText(OAuthUser.getScreen_name());
            userIcon.setUserImage(OAuthUser);
            Glide.with(activity).load(OAuthUser.getCover_image_phone()).centerCrop().into(userBackGround);
        }
    }

    public void destroyPic() {
        if (userBackGround != null)
            Glide.clear(userBackGround);
        if (userIcon != null)
            Glide.clear(userIcon);
    }

}
