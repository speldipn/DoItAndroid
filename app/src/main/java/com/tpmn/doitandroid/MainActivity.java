package com.tpmn.doitandroid;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MSG = "EXTRA_MSG";

    private WebView webView;
    private Button goButton;
    private Button showControlButton;
    private ViewGroup controlView;
    private EditText urlEditText;
    private Animation animLeft;
    private Animation animRight;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        animLeft = AnimationUtils.loadAnimation(this, R.anim.activity_left);
        animRight = AnimationUtils.loadAnimation(this, R.anim.activity_right);

        goButton = findViewById(R.id.goButton);
        showControlButton = findViewById(R.id.showControlButton);
        controlView = findViewById(R.id.controlView);
        urlEditText = findViewById(R.id.urlEditText);

        webView = findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);

        animRight.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) { }

            @Override
            public void onAnimationEnd(Animation animation) {
                controlView.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) { }
        });
        animLeft.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                controlView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        goButton.setOnClickListener(v -> {
            String url = urlEditText.getText().toString();
            if(!url.startsWith("http://")) {
                url = "http://" + url;
            }
            webView.loadUrl(url);
            controlView.startAnimation(animRight);
        });

        showControlButton.setOnClickListener(v -> {
            controlView.startAnimation(animLeft);
        });

    }
}