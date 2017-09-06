package com.example.phundal2091.simpleapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phundal2091.simpleapp.InstagramPreferences;
import com.example.phundal2091.simpleapp.R;
import com.example.phundal2091.simpleapp.services.InstagramService;
import com.example.services.observers.IApiService;
import com.example.services.retrofit.IRetrofitProvider;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by phundal2091 on 9/6/17.
 */

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {
    private Context context;
    private InstagramPreferences instagramPreferences;
    private InstagramService instagramService;
    private IRetrofitProvider retrofitProvider;
    private IApiService apiService;
    private List<Object> data;

    public FeedAdapter(Context context, InstagramPreferences instagramPreferences, InstagramService instagramService, IRetrofitProvider retrofitProvider, IApiService apiService, List<Object> data) {
        this.context = context;
        this.instagramPreferences = instagramPreferences;
        this.instagramService = instagramService;
        this.retrofitProvider = retrofitProvider;
        this.apiService = apiService;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.feed_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        if(data == null) { return 0; }
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
