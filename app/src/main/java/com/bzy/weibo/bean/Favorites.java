package com.bzy.weibo.bean;

import java.io.Serializable;
import java.util.List;

/**
 */
public class Favorites implements Serializable {

    private List<Favorite> favorites;
    private String total_number;

    public List<Favorite> getFavorites() {
        return favorites;
    }

    public String getTotal_number() {
        return total_number;
    }

    @Override
    public String toString() {
        return "Favorites{" +
                "favorites=" + favorites +
                ", total_number='" + total_number + '\'' +
                '}';
    }
}
