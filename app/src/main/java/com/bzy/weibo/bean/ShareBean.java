package com.bzy.weibo.bean;

import java.util.List;

/**
 */

public class ShareBean {

    /**
     * created_at : Wed Oct 24 23:49:17 +0800 2012
     * id : 3504803600500000
     * mid : 3504803600502730
     * idstr : 3504803600502730
     * text : 分组定向图片微博
     * source : 新浪微博</a>
     * favorited : false
     * truncated : false
     * in_reply_to_status_id :
     * in_reply_to_user_id :
     * in_reply_to_screen_name :
     * thumbnail_pic : http://ww2.sinaimg.cn/thumbnail/71666d49jw1dy6q8t3p0rj.jpg
     * bmiddle_pic : http://ww2.sinaimg.cn/bmiddle/71666d49jw1dy6q8t3p0rj.jpg
     * original_pic : http://ww2.sinaimg.cn/large/71666d49jw1dy6q8t3p0rj.jpg
     * geo : {"type":"Point","coordinates":[40.413467,116.646439]}
     * user : {"id":1902538057,"idstr":"1902538057","screen_name":"张三","name":"张三","province":"11","city":"8","location":"北京 海淀区","description":"做最受尊敬的互联网产品经理...","url":"","profile_image_url":"http://tp2.sinaimg.cn/1902538057/50/22817372040/1","profile_url":"304270168","domain":"shenbinzhu","weihao":"304270168","gender":"m","followers_count":337,"friends_count":534,"statuses_count":516,"favourites_count":60,"created_at":"Sat Dec 25 14:12:35 +0800 2010","following":false,"allow_all_act_msg":true,"geo_enabled":true,"verified":false,"verified_type":220,"allow_all_comment":true,"avatar_large":"http://tp2.sinaimg.cn/1902538057/180/22817372040/1","verified_reason":"","follow_me":false,"online_status":0,"bi_followers_count":185,"lang":"zh-cn","level":7,"type":1,"ulevel":0,"badge":{"kuainv":{"level":0},"uc_domain":0,"enterprise":0,"anniversary":0}}
     * reposts_count : 0
     * comments_count : 0
     * attitudes_count : 0
     * mlevel : 0
     * visible : {"type":3,"list_id":3469454702570000}
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
    private GeoBean geo;
    private UserBean user;
    private int reposts_count;
    private int comments_count;
    private int attitudes_count;
    private int mlevel;
    private VisibleBean visible;

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

    public GeoBean getGeo() {
        return geo;
    }

    public void setGeo(GeoBean geo) {
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

    public int getAttitudes_count() {
        return attitudes_count;
    }

    public void setAttitudes_count(int attitudes_count) {
        this.attitudes_count = attitudes_count;
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

    public static class GeoBean {
        /**
         * type : Point
         * coordinates : [40.413467,116.646439]
         */

        private String type;
        private List<Double> coordinates;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<Double> getCoordinates() {
            return coordinates;
        }

        public void setCoordinates(List<Double> coordinates) {
            this.coordinates = coordinates;
        }
    }

    public static class UserBean {
        /**
         * id : 1902538057
         * idstr : 1902538057
         * screen_name : 张三
         * name : 张三
         * province : 11
         * city : 8
         * location : 北京 海淀区
         * description : 做最受尊敬的互联网产品经理...
         * url :
         * profile_image_url : http://tp2.sinaimg.cn/1902538057/50/22817372040/1
         * profile_url : 304270168
         * domain : shenbinzhu
         * weihao : 304270168
         * gender : m
         * followers_count : 337
         * friends_count : 534
         * statuses_count : 516
         * favourites_count : 60
         * created_at : Sat Dec 25 14:12:35 +0800 2010
         * following : false
         * allow_all_act_msg : true
         * geo_enabled : true
         * verified : false
         * verified_type : 220
         * allow_all_comment : true
         * avatar_large : http://tp2.sinaimg.cn/1902538057/180/22817372040/1
         * verified_reason :
         * follow_me : false
         * online_status : 0
         * bi_followers_count : 185
         * lang : zh-cn
         * level : 7
         * type : 1
         * ulevel : 0
         * badge : {"kuainv":{"level":0},"uc_domain":0,"enterprise":0,"anniversary":0}
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
        private int level;
        private int type;
        private int ulevel;
        private BadgeBean badge;

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

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getUlevel() {
            return ulevel;
        }

        public void setUlevel(int ulevel) {
            this.ulevel = ulevel;
        }

        public BadgeBean getBadge() {
            return badge;
        }

        public void setBadge(BadgeBean badge) {
            this.badge = badge;
        }

        public static class BadgeBean {
            /**
             * kuainv : {"level":0}
             * uc_domain : 0
             * enterprise : 0
             * anniversary : 0
             */

            private KuainvBean kuainv;
            private int uc_domain;
            private int enterprise;
            private int anniversary;

            public KuainvBean getKuainv() {
                return kuainv;
            }

            public void setKuainv(KuainvBean kuainv) {
                this.kuainv = kuainv;
            }

            public int getUc_domain() {
                return uc_domain;
            }

            public void setUc_domain(int uc_domain) {
                this.uc_domain = uc_domain;
            }

            public int getEnterprise() {
                return enterprise;
            }

            public void setEnterprise(int enterprise) {
                this.enterprise = enterprise;
            }

            public int getAnniversary() {
                return anniversary;
            }

            public void setAnniversary(int anniversary) {
                this.anniversary = anniversary;
            }

            public static class KuainvBean {
                /**
                 * level : 0
                 */

                private int level;

                public int getLevel() {
                    return level;
                }

                public void setLevel(int level) {
                    this.level = level;
                }
            }
        }
    }

    public static class VisibleBean {
        /**
         * type : 3
         * list_id : 3469454702570000
         */

        private int type;
        private long list_id;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public long getList_id() {
            return list_id;
        }

        public void setList_id(long list_id) {
            this.list_id = list_id;
        }
    }
}
