package com.bzy.weibo.bean;

import java.io.Serializable;
import java.util.List;

/**
 *  @ 我的评论
 */
public class MentionComment implements Serializable {

    private List<Comments> comments;
    private String next_cursor;
    private String previous_cursor;
    private String total_number;

    public List<Comments> getComments() {
        return comments;
    }

    public String getNext_cursor() {
        return next_cursor;
    }

    public String getPrevious_cursor() {
        return previous_cursor;
    }

    public String getTotal_number() {
        return total_number;
    }

    @Override
    public String toString() {
        return "MentionComment{" +
                "comments=" + comments +
                ", next_cursor='" + next_cursor + '\'' +
                ", previous_cursor='" + previous_cursor + '\'' +
                ", total_number='" + total_number + '\'' +
                '}';
    }
}
