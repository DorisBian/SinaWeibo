package com.bzy.weibo.ui.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.bzy.weibo.MyApp;
import com.bzy.weibo.bean.Status;
import com.bzy.weibo.bean.User;
import com.bzy.weibo.util.DataUtil;
import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.bzy.weibo.R;
import com.bzy.weibo.ui.view.ICARView;
//import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.app.Activity.RESULT_OK;

/**
 * Comment and Repost with POST
 */
public class CARPresenter extends BasePresenter<ICARView> {

    private Context context;

    public CARPresenter(Context context) {
        this.context = context;
    }

    public void postComment(String text, String id) {
        Oauth2AccessToken token = readToken(context);
        weiBoApi.setComment(getCommentMap(token.getToken(), text, id))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(postComments -> {
                    getView().finishAndToast();
                }, this::loadError);
    }

    public void postRepost(String weiboText, String id,Status originStatus) {
        //模拟数据
        //本地模拟数据
        //设置微博刷新
        //自己拼接一个Bean
        Status status = new Status();
        status.setText(weiboText);
        User user = getUserInfoFromDB();
        if (user == null) {
            user = User.getLoginUser();
            user.setScreen_name("张三");
            user.setName("张三");
            user.setProfile_image_url("https://timgsa.baidu.com/timg?image\u0026quality\u003d80\u0026size\u003db10000_10000\u0026sec\u003d1525236570\u0026di\u003de3abeecb6e1208c7b6290adb5ee8f99f\u0026src\u003dhttp://img.taopic.com/uploads/allimg/110314/1517-110314134R642.jpg");
        }
        status.setSource("\u003ca href\u003d\"http://www.baidu.com\" rel\u003d\"nofollow\"\u003eiPhone X\u003c/a\u003e");
        status.setUser(user);

        status.setCreated_at(DataUtil.convertGMTToLoacale(new Date()));
        status.setAttitudes_count("0");
        status.setComments_count("0");
        status.setReposts_count("0");

//        WeiboStatus retweeted_status = new WeiboStatus();
//        retweeted_status.setComments_count("863");
//        retweeted_status.setCreated_at("Tue Nov 30 16:05:41 +0800 2010");
//        retweeted_status.setReposts_count("3623");
//        retweeted_status.setSource("\u003ca href\u003d\"http://www.baidu.com\" rel\u003d\"nofollow\"\u003e寻物启事\u003c/a\u003e");
//        retweeted_status.setText("对待事物的出发点、立足点，决定着事物的发展及发展后的结果。这个结果实际也是一种相对而言的成败。世俗间追求这种成败却又调整不好出发点，更是找不到立足点。所以成也好败也好，放下了最好。");
//        User rUser = new User();
//        rUser.setDescription("心存慈悲 身奉善行 出世入世 修己助人 归元禅寺官方网站：http://www.guiyuanchansi.net");
//        rUser.setGender("m");
//        rUser.setId("1799833402");
//        rUser.setLocation("湖北 武汉");
//        rUser.setName("归元隆印");
//        rUser.setProfile_image_url("http://tp3.sinaimg.cn/1799833402/50/1283207796");
//        rUser.setScreen_name("归元隆印");
//        rUser.setFavourites_count(0);
//        rUser.setFollowers_count(66710);
//        rUser.setFriends_count(4);
//        rUser.setStatuses_count(77);
//        rUser.setVerified(true);
//        retweeted_status.setUser(rUser);
//        status.setRetweeted_status(retweeted_status);

        //
        status.setRetweeted_status(originStatus);
        Intent intent = new Intent();
        intent.putExtra("weibo", status);
        ((Activity) context).setResult(RESULT_OK, intent);
        getView().finishAndToast();

//        Oauth2AccessToken token = readToken(context);
//        weiBoApi.setRepost(getRepostMap(token.getToken(),text,id))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(response -> {
//                    getView().finishAndToast();
//                },this::loadError);

    }

    private User getUserInfoFromDB() {
        ArrayList<User> query = MyApp.mDb.query(User.class);
        if (query.size() > 0) {
            return query.get(0);
        } else {
            return null;
        }
    }

    public void postCommentToReply(String text, String comment_id, String weibo_id) {
        Oauth2AccessToken token = readToken(context);
        weiBoApi.setCommentToReply(getCommentToReplyMap(token.getToken(), text, comment_id, weibo_id))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    getView().finishAndToast();
                }, this::loadError);
    }

    private void loadError(Throwable throwable) {
        throwable.printStackTrace();
        Toast.makeText(context, R.string.load_error, Toast.LENGTH_SHORT).show();
    }

    // get request params
    private Map<String, Object> getCommentMap(String token, String comment, String id) {
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", token);
        map.put("comment", comment);
        map.put("id", id);
        return map;
    }

    private Map<String, Object> getRepostMap(String token, String comment, String id) {
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", token);
        map.put("status", comment);
        map.put("id", id);
        return map;
    }

    private Map<String, Object> getCommentToReplyMap(String token, String comment, String comment_id, String weibo_id) {
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", token);
        map.put("comment", comment);
        map.put("id", weibo_id);
        map.put("cid", comment_id);
        return map;
    }

    private Oauth2AccessToken readToken(Context context) {
        return AccessTokenKeeper.readAccessToken(context);
    }

}
