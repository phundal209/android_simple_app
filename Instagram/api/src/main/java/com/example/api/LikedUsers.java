package com.example.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by phundal2091 on 9/6/17.
 */

public class LikedUsers {
    @SerializedName("username")
    String username;
    @SerializedName("first_name")
    String firstname;
    @SerializedName("last_name")
    String lastName;
    @SerializedName("type")
    String type;
    @SerializedName("id")
    long id;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
