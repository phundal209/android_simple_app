package com.example.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by phundal2091 on 9/6/17.
 */

public class LikeResponse {
    @SerializedName("data")
    List<LikedUsers> data;

    public List<LikedUsers> getData() {
        return data;
    }
}
