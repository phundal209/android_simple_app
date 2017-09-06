package com.example.phundal2091.simpleapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.phundal2091.simpleapp.InstagramPreferences;
import com.example.phundal2091.simpleapp.utils.InstagramUtilities;
import com.example.phundal2091.simpleapp.InstagramWebClient;
import com.example.phundal2091.simpleapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.webview)
    public WebView webview;
    InstagramPreferences instagramPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        instagramPreferences = new InstagramPreferences(this);
        loadWebviewContent();

    }

    private void loadWebviewContent() {
        webview.setWebViewClient(new InstagramWebClient(this, instagramPreferences));
        if(instagramPreferences.getAuthToken() != null) {
            webview.loadUrl(InstagramUtilities.AUTH_URL);
        } else {
            webview.clearCache(true);
            webview.clearHistory();

            InstagramWebClient.clearCookies(MainActivity.this);
            webview.loadUrl(InstagramUtilities.AUTH_URL);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        loadWebviewContent();
    }
}
