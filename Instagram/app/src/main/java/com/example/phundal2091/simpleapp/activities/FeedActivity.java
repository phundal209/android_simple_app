package com.example.phundal2091.simpleapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.data.repository.UserModel;
import com.example.phundal2091.simpleapp.InstagramApplication;
import com.example.phundal2091.simpleapp.InstagramPreferences;
import com.example.phundal2091.simpleapp.R;
import com.example.phundal2091.simpleapp.services.IInstagramService;
import com.example.phundal2091.simpleapp.services.InstagramService;
import com.example.services.observers.ApiService;
import com.example.services.observers.IApiService;
import com.example.services.retrofit.IRetrofitProvider;
import com.example.services.retrofit.RetrofitProvider;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class FeedActivity extends AppCompatActivity {

    private InstagramPreferences instagramPreferences;
    private IRetrofitProvider retrofitProvider;
    private IApiService apiService;
    private IInstagramService instagramService;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.profile_image) ImageView profile_image;
    @BindView(R.id.post_count) TextView post_count;
    @BindView(R.id.follower_count) TextView follower_count;
    @BindView(R.id.following_count) TextView following_count;
    @BindView(R.id.username) TextView username;
    @BindView(R.id.description) TextView description;
    @BindView(R.id.recycler) RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        ButterKnife.bind(this);

        instagramPreferences = new InstagramPreferences(this);
        retrofitProvider = new RetrofitProvider();
        apiService = new ApiService(retrofitProvider);
        instagramService = new InstagramService(this, retrofitProvider, apiService, instagramPreferences);

        instagramService.getUser(new InstagramService.OnUserRetrieved() {
            @Override
            public void onRetrieved(UserModel model) {
                if(!model.getUsername().isEmpty()) {
                    toolbar.setTitle(model.getUsername());
                    bindUserProfile(model);
                }
            }
        });
    }

    private void bindUserProfile(UserModel model) {
        post_count.setText(String.valueOf(model.getCounts().getMedia()));
        follower_count.setText(String.valueOf(model.getCounts().getFollows()));
        following_count.setText(String.valueOf(model.getCounts().getFollowed_by()));
        username.setText(model.getFull_name());
        description.setText(model.getBio());
        Picasso.with(this)
                .load(model.getProfile_picture())
                .transform(new CropCircleTransformation())
                .into(profile_image);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.feed_menu, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                instagramPreferences.logout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
