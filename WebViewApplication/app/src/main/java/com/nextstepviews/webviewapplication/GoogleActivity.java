package com.nextstepviews.webviewapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class GoogleActivity extends AppCompatActivity {

    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google);

        webView = (WebView) findViewById(R.id.webGoogle);

        // affect the Url to load
        webView.loadUrl("https://www.google.com/");

        //Enable JavaScript
        webView.getSettings().setJavaScriptEnabled(true);


        // WebViewClient allows you to handle
        // onPageFinished and override Url loading.
        webView.setWebViewClient(new WebViewClient());

    }
}