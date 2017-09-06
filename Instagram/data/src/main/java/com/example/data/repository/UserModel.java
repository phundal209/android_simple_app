package com.example.data.repository;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by phundal2091 on 9/6/17.
 */

public class UserModel extends RealmObject {
    @PrimaryKey
    private long id;
    private String username;
    private String full_name;
    private String profile_picture;
    private String bio;
    private String website;
    private CountsModel counts;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getProfile_picture() {
        return profile_picture;
    }

    public void setProfile_picture(String profile_picture) {
        this.profile_picture = profile_picture;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public CountsModel getCounts() {
        return counts;
    }

    public void setCounts(CountsModel counts) {
        this.counts = counts;
    }
}
