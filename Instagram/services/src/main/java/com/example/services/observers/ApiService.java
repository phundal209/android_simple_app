package com.example.services.observers;

import com.example.api.LikeResponse;
import com.example.api.UserResponse;
import com.example.data.repository.UserModel;
import com.example.services.rest.IRestClient;
import com.example.services.retrofit.IRetrofitProvider;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.realm.Realm;

/**
 * Created by phundal2091 on 9/4/17.
 */

public class ApiService implements IApiService {
    private IRestClient restClient;
    private Realm realm;


    public ApiService(IRetrofitProvider retrofitProvider) {
        this.restClient = retrofitProvider.getRetrofit().create(IRestClient.class);
    }

    @Override
    public Observable<UserModel> getUserAsync(String accessToken) {
        return restClient.getUser(accessToken).map(new Function<UserResponse, UserModel>() {
            @Override
            public UserModel apply(final UserResponse userResponse) throws Exception {
                if(userResponse != null) {
                    // cache the response
                    realm = Realm.getDefaultInstance();
                    realm.beginTransaction();
                    realm.executeTransactionAsync(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            realm.copyToRealmOrUpdate(userResponse.getData());
                        }
                    });
                    realm.commitTransaction();
                    return userResponse.getData();
                }
                return new UserModel();
            }
        });
    }

    public Observable<LikeResponse> getLikesAsync(String accessToken, int mediaId) {
        return restClient.getLikes(mediaId, accessToken).map(new Function<LikeResponse, LikeResponse>() {
            @Override
            public LikeResponse apply(LikeResponse likeResponse) throws Exception {
                return likeResponse;
            }
        });
    }
}
