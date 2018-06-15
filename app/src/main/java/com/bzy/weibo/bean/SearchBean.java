package com.bzy.weibo.bean;

import java.io.Serializable;
import java.util.List;

/**
 */

public class SearchBean implements Serializable{

    /**
     * statuses : [{"created_at":"Fri Feb 24 15:18:31 +0800 2012","id":3416614810943471,"mid":"3416614810943471","idstr":"3416614810943471","text":"与大家分享我所喜爱的照片！#ABC晒新年# 。","source":"微活动<\/a>","favorited":false,"truncated":false,"in_reply_to_status_id":"","in_reply_to_user_id":"","in_reply_to_screen_name":"","thumbnail_pic":"http://ww3.sinaimg.cn/thumbnail/5f0eb04atw1dq4ir5bztkj.jpg","bmiddle_pic":"http://ww3.sinaimg.cn/bmiddle/5f0eb04atw1dq4ir5bztkj.jpg","original_pic":"http://ww3.sinaimg.cn/large/5f0eb04atw1dq4ir5bztkj.jpg","geo":null,"user":{"id":1594798154,"idstr":"1594798154","screen_name":"刘麦","name":"刘麦","province":"34","city":"16","location":"安徽 亳州","description":"新一代世界小童星。","url":"http://blog.sina.com.cn/liumaiduo","profile_image_url":"http://tp3.sinaimg.cn/1594798154/50/5614782838/1","profile_url":"liumaiduo","domain":"liumaiduo","weihao":"","gender":"m","followers_count":314,"friends_count":555,"statuses_count":1913,"favourites_count":1,"created_at":"Sat Jun 11 00:00:00 +0800 2011","following":false,"allow_all_act_msg":false,"geo_enabled":true,"verified":false,"verified_type":-1,"allow_all_comment":false,"avatar_large":"http://tp3.sinaimg.cn/1594798154/180/5614782838/1","verified_reason":"","follow_me":false,"online_status":1,"bi_followers_count":290,"lang":"zh-cn"},"annotations":[],"reposts_count":0,"comments_count":0,"mlevel":0,"visible":{"type":0,"list_id":0}}]
     * total_number : 2543821
     */

    private int total_number;
    private List<Status> statuses;

    public int getTotal_number() {
        return total_number;
    }

    public void setTotal_number(int total_number) {
        this.total_number = total_number;
    }

    public List<Status> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<Status> statuses) {
        this.statuses = statuses;
    }

    public static class StatusesBean {
        /**
         * created_at : Fri Feb 24 15:18:31 +0800 2012
         * id : 3416614810943471
         * mid : 3416614810943471
         * idstr : 3416614810943471
         * text : 与大家分享我所喜爱的照片！#ABC晒新年# 。
         * source : 微活动</a>
         * favorited : false
         * truncated : false
         * in_reply_to_status_id :
         * in_reply_to_user_id :
         * in_reply_to_screen_name :
         * thumbnail_pic : http://ww3.sinaimg.cn/thumbnail/5f0eb04atw1dq4ir5bztkj.jpg
         * bmiddle_pic : http://ww3.sinaimg.cn/bmiddle/5f0eb04atw1dq4ir5bztkj.jpg
         * original_pic : http://ww3.sinaimg.cn/large/5f0eb04atw1dq4ir5bztkj.jpg
         * geo : null
         * user : {"id":1594798154,"idstr":"1594798154","screen_name":"刘麦","name":"刘麦","province":"34","city":"16","location":"安徽 亳州","description":"新一代世界小童星。","url":"http://blog.sina.com.cn/liumaiduo","profile_image_url":"http://tp3.sinaimg.cn/1594798154/50/5614782838/1","profile_url":"liumaiduo","domain":"liumaiduo","weihao":"","gender":"m","followers_count":314,"friends_count":555,"statuses_count":1913,"favourites_count":1,"created_at":"Sat Jun 11 00:00:00 +0800 2011","following":false,"allow_all_act_msg":false,"geo_enabled":true,"verified":false,"verified_type":-1,"allow_all_comment":false,"avatar_large":"http://tp3.sinaimg.cn/1594798154/180/5614782838/1","verified_reason":"","follow_me":false,"online_status":1,"bi_followers_count":290,"lang":"zh-cn"}
         * annotations : []
         * reposts_count : 0
         * comments_count : 0
         * mlevel : 0
         * visible : {"type":0,"list_id":0}
         */

        private String created_at;
        private long id;
        private String mid;
        private String idstr;
        private String text;
        private String source;
        private boolean favorited;
        private boolean truncated;
        private String in_reply_to_status_id;
        private String in_reply_to_user_id;
        private String in_reply_to_screen_name;
        private String thumbnail_pic;
        private String bmiddle_pic;
        private String original_pic;
        private Object geo;
        private UserBean user;
        private int reposts_count;
        private int comments_count;
        private int mlevel;
        private VisibleBean visible;
        private List<?> annotations;

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getMid() {
            return mid;
        }

        public void setMid(String mid) {
            this.mid = mid;
        }

        public String getIdstr() {
            return idstr;
        }

        public void setIdstr(String idstr) {
            this.idstr = idstr;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public boolean isFavorited() {
            return favorited;
        }

        public void setFavorited(boolean favorited) {
            this.favorited = favorited;
        }

        public boolean isTruncated() {
            return truncated;
        }

        public void setTruncated(boolean truncated) {
            this.truncated = truncated;
        }

        public String getIn_reply_to_status_id() {
            return in_reply_to_status_id;
        }

        public void setIn_reply_to_status_id(String in_reply_to_status_id) {
            this.in_reply_to_status_id = in_reply_to_status_id;
        }

        public String getIn_reply_to_user_id() {
            return in_reply_to_user_id;
        }

        public void setIn_reply_to_user_id(String in_reply_to_user_id) {
            this.in_reply_to_user_id = in_reply_to_user_id;
        }

        public String getIn_reply_to_screen_name() {
            return in_reply_to_screen_name;
        }

        public void setIn_reply_to_screen_name(String in_reply_to_screen_name) {
            this.in_reply_to_screen_name = in_reply_to_screen_name;
        }

        public String getThumbnail_pic() {
            return thumbnail_pic;
        }

        public void setThumbnail_pic(String thumbnail_pic) {
            this.thumbnail_pic = thumbnail_pic;
        }

        public String getBmiddle_pic() {
            return bmiddle_pic;
        }

        public void setBmiddle_pic(String bmiddle_pic) {
            this.bmiddle_pic = bmiddle_pic;
        }

        public String getOriginal_pic() {
            return original_pic;
        }

        public void setOriginal_pic(String original_pic) {
            this.original_pic = original_pic;
        }

        public Object getGeo() {
            return geo;
        }

        public void setGeo(Object geo) {
            this.geo = geo;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public int getReposts_count() {
            return reposts_count;
        }

        public void setReposts_count(int reposts_count) {
            this.reposts_count = reposts_count;
        }

        public int getComments_count() {
            return comments_count;
        }

        public void setComments_count(int comments_count) {
            this.comments_count = comments_count;
        }

        public int getMlevel() {
            return mlevel;
        }

        public void setMlevel(int mlevel) {
            this.mlevel = mlevel;
        }

        public VisibleBean getVisible() {
            return visible;
        }

        public void setVisible(VisibleBean visible) {
            this.visible = visible;
        }

        public List<?> getAnnotations() {
            return annotations;
        }

        public void setAnnotations(List<?> annotations) {
            this.annotations = annotations;
        }

        public static class UserBean {
            /**
             * id : 1594798154
             * idstr : 1594798154
             * screen_name : 刘麦
             * name : 刘麦
             * province : 34
             * city : 16
             * location : 安徽 亳州
             * description : 新一代世界小童星。
             * url : http://blog.sina.com.cn/liumaiduo
             * profile_image_url : http://tp3.sinaimg.cn/1594798154/50/5614782838/1
             * profile_url : liumaiduo
             * domain : liumaiduo
             * weihao :
             * gender : m
             * followers_count : 314
             * friends_count : 555
             * statuses_count : 1913
             * favourites_count : 1
             * created_at : Sat Jun 11 00:00:00 +0800 2011
             * following : false
             * allow_all_act_msg : false
             * geo_enabled : true
             * verified : false
             * verified_type : -1
             * allow_all_comment : false
             * avatar_large : http://tp3.sinaimg.cn/1594798154/180/5614782838/1
             * verified_reason :
             * follow_me : false
             * online_status : 1
             * bi_followers_count : 290
             * lang : zh-cn
             */

            private int id;
            private String idstr;
            private String screen_name;
            private String name;
            private String province;
            private String city;
            private String location;
            private String description;
            private String url;
            private String profile_image_url;
            private String profile_url;
            private String domain;
            private String weihao;
            private String gender;
            private int followers_count;
            private int friends_count;
            private int statuses_count;
            private int favourites_count;
            private String created_at;
            private boolean following;
            private boolean allow_all_act_msg;
            private boolean geo_enabled;
            private boolean verified;
            private int verified_type;
            private boolean allow_all_comment;
            private String avatar_large;
            private String verified_reason;
            private boolean follow_me;
            private int online_status;
            private int bi_followers_count;
            private String lang;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getIdstr() {
                return idstr;
            }

            public void setIdstr(String idstr) {
                this.idstr = idstr;
            }

            public String getScreen_name() {
                return screen_name;
            }

            public void setScreen_name(String screen_name) {
                this.screen_name = screen_name;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getProfile_image_url() {
                return profile_image_url;
            }

            public void setProfile_image_url(String profile_image_url) {
                this.profile_image_url = profile_image_url;
            }

            public String getProfile_url() {
                return profile_url;
            }

            public void setProfile_url(String profile_url) {
                this.profile_url = profile_url;
            }

            public String getDomain() {
                return domain;
            }

            public void setDomain(String domain) {
                this.domain = domain;
            }

            public String getWeihao() {
                return weihao;
            }

            public void setWeihao(String weihao) {
                this.weihao = weihao;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public int getFollowers_count() {
                return followers_count;
            }

            public void setFollowers_count(int followers_count) {
                this.followers_count = followers_count;
            }

            public int getFriends_count() {
                return friends_count;
            }

            public void setFriends_count(int friends_count) {
                this.friends_count = friends_count;
            }

            public int getStatuses_count() {
                return statuses_count;
            }

            public void setStatuses_count(int statuses_count) {
                this.statuses_count = statuses_count;
            }

            public int getFavourites_count() {
                return favourites_count;
            }

            public void setFavourites_count(int favourites_count) {
                this.favourites_count = favourites_count;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public boolean isFollowing() {
                return following;
            }

            public void setFollowing(boolean following) {
                this.following = following;
            }

            public boolean isAllow_all_act_msg() {
                return allow_all_act_msg;
            }

            public void setAllow_all_act_msg(boolean allow_all_act_msg) {
                this.allow_all_act_msg = allow_all_act_msg;
            }

            public boolean isGeo_enabled() {
                return geo_enabled;
            }

            public void setGeo_enabled(boolean geo_enabled) {
                this.geo_enabled = geo_enabled;
            }

            public boolean isVerified() {
                return verified;
            }

            public void setVerified(boolean verified) {
                this.verified = verified;
            }

            public int getVerified_type() {
                return verified_type;
            }

            public void setVerified_type(int verified_type) {
                this.verified_type = verified_type;
            }

            public boolean isAllow_all_comment() {
                return allow_all_comment;
            }

            public void setAllow_all_comment(boolean allow_all_comment) {
                this.allow_all_comment = allow_all_comment;
            }

            public String getAvatar_large() {
                return avatar_large;
            }

            public void setAvatar_large(String avatar_large) {
                this.avatar_large = avatar_large;
            }

            public String getVerified_reason() {
                return verified_reason;
            }

            public void setVerified_reason(String verified_reason) {
                this.verified_reason = verified_reason;
            }

            public boolean isFollow_me() {
                return follow_me;
            }

            public void setFollow_me(boolean follow_me) {
                this.follow_me = follow_me;
            }

            public int getOnline_status() {
                return online_status;
            }

            public void setOnline_status(int online_status) {
                this.online_status = online_status;
            }

            public int getBi_followers_count() {
                return bi_followers_count;
            }

            public void setBi_followers_count(int bi_followers_count) {
                this.bi_followers_count = bi_followers_count;
            }

            public String getLang() {
                return lang;
            }

            public void setLang(String lang) {
                this.lang = lang;
            }
        }

        public static class VisibleBean {
            /**
             * type : 0
             * list_id : 0
             */

            private int type;
            private int list_id;

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getList_id() {
                return list_id;
            }

            public void setList_id(int list_id) {
                this.list_id = list_id;
            }
        }
    }
}
