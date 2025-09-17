package com.nextstepviews.webviewapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class TiktokActivity extends AppCompatActivity {

    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiktok);

        webView = (WebView) findViewById(R.id.webTiktok);

        // affect the Url to load
        webView.loadUrl("https://www.tiktok.com/fr/");

        //Enable JavaScript
        webView.getSettings().setJavaScriptEnabled(true);


        // WebViewClient allows you to handle
        // onPageFinished and override Url loading.
        webView.setWebViewClient(new WebViewClient());
    }
}