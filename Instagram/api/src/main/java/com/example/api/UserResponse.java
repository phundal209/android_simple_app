package com.example.api;

import com.example.data.repository.UserModel;
import com.google.gson.annotations.SerializedName;

/**
 * Created by phundal2091 on 9/6/17.
 */

public class UserResponse {
    @SerializedName("data")
    private UserModel data;

    public UserModel getData() {
        return data;
    }
}
