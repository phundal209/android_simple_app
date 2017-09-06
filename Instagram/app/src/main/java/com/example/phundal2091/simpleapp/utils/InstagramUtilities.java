package com.example.phundal2091.simpleapp.utils;

/**
 * Created by phundal2091 on 9/5/17.
 */

public final class InstagramUtilities {
    public static final String REDIRECT_URL = "https://www.23andme.com";
    public static final String CLIENT_ID = "0637825256de4d9e9c969ec594b032c8";
    public static final String AUTH_URL = "https://api.instagram.com/oauth/authorize/?client_id=" +
            CLIENT_ID + "&redirect_uri=" + REDIRECT_URL + "&response_type=token";
    public static final String LOGOUT_URL = "https://instagram.com/accounts/logout";
}
