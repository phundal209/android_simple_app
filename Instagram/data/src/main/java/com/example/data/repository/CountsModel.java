package com.example.data.repository;

import io.realm.RealmObject;

/**
 * Created by phundal2091 on 9/6/17.
 */

public class CountsModel extends RealmObject {
    private int media;
    private int follows;
    private int followed_by;

    public int getMedia() {
        return media;
    }

    public void setMedia(int media) {
        this.media = media;
    }

    public int getFollows() {
        return follows;
    }

    public void setFollows(int follows) {
        this.follows = follows;
    }

    public int getFollowed_by() {
        return followed_by;
    }

    public void setFollowed_by(int followed_by) {
        this.followed_by = followed_by;
    }
}
