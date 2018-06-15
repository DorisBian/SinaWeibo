package com.bzy.weibo.ui.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.bzy.weibo.MyApp;
import com.bzy.weibo.bean.Status;
import com.bzy.weibo.bean.User;
import com.bzy.weibo.util.DataUtil;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.bzy.weibo.R;
import com.bzy.weibo.ui.adapter.WeiBoPhotoAdapter;
import com.bzy.weibo.ui.view.ISendView;
import com.sina.weibo.sdk.auth.AccessTokenKeeper;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import me.iwf.photopicker.PhotoPicker;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import static android.app.Activity.RESULT_OK;

/**
 * Send Img with text
 */
public class SendPresenter extends BasePresenter<ISendView> {

    private Context context;
    private ISendView sendView;
    private List<String> photos = new ArrayList<>();
    private WeiBoPhotoAdapter photoAdapter;

    public SendPresenter(Context context) {
        this.context = context;
    }

    //添加图片
    public void pickPhoto() {
        sendView = getView();
        photoAdapter = new WeiBoPhotoAdapter(context, photos);
        sendView.getPhotoGrid().setAdapter(photoAdapter);
        photos.clear();
        photoAdapter.paths.clear();
        sendView.permissionSetting();
    }

    //photo pick打开相机后选择相机里的图片
    public void photoPick() {
        PhotoPicker.builder()
                .setPhotoCount(9)
                .setShowCamera(true)
                .setShowGif(true)
                .setPreviewEnabled(false)
                .start((Activity) context, PhotoPicker.REQUEST_CODE);
    }

    //更新数据适配器
    public void loadAdapter(ArrayList<String> paths) {
        if (photos == null) {
            photos = new ArrayList<>();
        }
        System.out.println("~~~" + paths.size());
        photos.clear();
        photos.addAll(paths);
        photoAdapter.notifyDataSetChanged();
    }

    String domainUrl = " http://www.baidu.com ";

    public void sendWeiBo(String weiboText) {
        //本地模拟数据
        //设置微博刷新￥
        //自己拼接一个Bean
        Status status = new Status();
        status.setText(weiboText);
        User user = getUserInfoFromDB();
        status.setSource("\u003ca href\u003d\"http://www.baidu.com\" rel\u003d\"nofollow\"\u003eAndroid \u003c/a\u003e");
        status.setUser(user);


        status.setCreated_at(DataUtil.convertGMTToLoacale(new Date()));
        status.setAttitudes_count("0");
        status.setComments_count("0");
        status.setReposts_count("0");
        status.setId(new Random().nextLong() + 1);


        ArrayList<Status.ThumbnailPic> pic_urls = null;
        if (getPathFromAdapter() != null && getPathFromAdapter().size() > 0) {
            pic_urls = new ArrayList<>();
            for (String path : photos) {
                Status.ThumbnailPic thumbnailPic = new Status.ThumbnailPic();
                thumbnailPic.localPic = path;
                pic_urls.add(thumbnailPic);
                MyApp.mDb.insert(thumbnailPic);
            }
        }
        status.setPic_urls(pic_urls);
        //插入数据库
        MyApp.mDb.insert(status);

        Intent intent = new Intent();
        intent.putExtra("weibo", status);
        ((Activity) context).setResult(RESULT_OK, intent);
        getView().finishAndToast();
        //处理下安全域名
//        status += domainUrl;
//        if (photoAdapter != null && getPathFromAdapter().size() > 0) {
//            weiBoApi.sendWeiBoWithImg(getTokenStr(), getTextStr(status), getImage())
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(WeiboStatus -> {
//                        getView().finishAndToast();
//                    }, this::loadError);
//        } else {
//            Oauth2AccessToken token = readToken(context);
//            weiBoApi.shareWeiBo(getSendMap(token.getToken(), status))
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(ShareBean -> {
//                        getView().finishAndToast();
//                    }, this::loadError);
//        }
    }

    private User getUserInfoFromDB() {
        ArrayList<User> query = MyApp.mDb.query(User.class);
        if (query.size() > 0) {
            return query.get(0);
        } else {
            return null;
        }
    }

    private void loadError(Throwable throwable) {
        throwable.printStackTrace();
        Toast.makeText(context, R.string.load_error, Toast.LENGTH_SHORT).show();
    }

    // get request params
    private RequestBody getTokenStr() {
        Oauth2AccessToken token = readToken(context);
        RequestBody accessBody = RequestBody.create(
                MediaType.parse("text/plain"),
                token.getToken());
        return accessBody;
    }

    private RequestBody getTextStr(String text) {
        RequestBody contentBody = RequestBody.create(
                MediaType.parse("text/plain"), text);
        return contentBody;
    }

    /**
     * 由于接口限制，只能上传一张图片
     *
     * @return
     */
    private RequestBody getImage() {
        List<String> stringList = getPathFromAdapter();
        File file = new File(stringList.get(0));
        RequestBody imageBody = RequestBody.create(
                MediaType.parse("multipart/form-data"),
                file);
        return imageBody;
    }

    private Map<String, Object> getSendMap(String token, String status) {
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", token);
        map.put("status", status);
        return map;
    }

    private Oauth2AccessToken readToken(Context context) {
        return AccessTokenKeeper.readAccessToken(context);
    }

    private List<String> getPathFromAdapter() {
        List<String> paths = new ArrayList<>();
        if (photoAdapter != null) {
            List<Object> objects = photoAdapter.paths;
            for (Object object : objects) {
                if (object instanceof String) {
                    paths.add((String) object);
                }
            }
            return paths;
        } else {
            return null;
        }
    }

}
