package com.example.phundal2091.simpleapp.services;

import android.content.Context;

import com.example.data.repository.UserModel;
import com.example.phundal2091.simpleapp.InstagramPreferences;
import com.example.services.observers.IApiService;
import com.example.services.retrofit.IRetrofitProvider;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by phundal2091 on 9/6/17.
 */

public class InstagramService implements IInstagramService {
    private Context context;
    private IRetrofitProvider retrofitProvider;
    private IApiService apiService;
    private InstagramPreferences preferences;

    public InstagramService(Context context, IRetrofitProvider retrofitProvider,
                            IApiService apiService, InstagramPreferences preferences) {
        this.context = context;
        this.retrofitProvider = retrofitProvider;
        this.apiService = apiService;
        this.preferences = preferences;
    }

    @Override
    public void getUser(final OnUserRetrieved onUserRetrieved) {
        if(preferences.getAuthToken() != null) {
            apiService.getUserAsync(preferences.getAuthToken())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Consumer<UserModel>() {
                @Override
                public void accept(UserModel userModel) throws Exception {
                    onUserRetrieved.onRetrieved(userModel);
                }
            });
        }
    }

    public interface OnUserRetrieved {
        void onRetrieved(UserModel model);
    }
}
