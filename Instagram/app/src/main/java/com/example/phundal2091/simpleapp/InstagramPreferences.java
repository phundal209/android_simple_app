package com.example.phundal2091.simpleapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.data.repository.UserModel;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by phundal2091 on 9/5/17.
 */

public class InstagramPreferences {
    private SharedPreferences sharedPreferences;
    private Context context;

    private static final String AUTH_HOLDER = "auth_key";

    public InstagramPreferences(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(
                "com.example.phundal2091.simpleapp", Context.MODE_PRIVATE
        );
    }

    public void saveAuth(String token) {
        sharedPreferences
                .edit()
                .putString(AUTH_HOLDER, token)
                .apply();
    }

    public String getAuthToken() {
        return sharedPreferences
                .getString(AUTH_HOLDER, null);
    }

    public void clearPreferences() {
        sharedPreferences
                .edit()
                .putString(AUTH_HOLDER, null)
                .apply();
    }

    public void logout() {
        clearPreferences();
        clearRealm();
        Activity activity = (Activity) context;
        activity.finish();
    }

    public void clearRealm() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<UserModel> users = realm.where(UserModel.class).findAll();
                users.deleteAllFromRealm();
            }
        });
        realm.commitTransaction();
    }
}
