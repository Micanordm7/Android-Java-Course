package com.nextstepviews.webviewapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button tiktok, google, youtube;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tiktok = (Button) findViewById(R.id.btnTikTok);
        google = (Button) findViewById(R.id.btnGoogle);
        youtube = (Button) findViewById(R.id.btnYoutube);

        tiktok.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, TiktokActivity.class));
        });

        google.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, GoogleActivity.class));
        });
        youtube.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, YoutubeActivity.class));
        });
    }
}