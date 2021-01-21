package com.tpmn.doitandroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MSG = "EXTRA_MSG";

    LinearLayout page;
    Animation translateStart;
    Animation translateEnd;
    Button button;

    boolean isPageOpen = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        page = findViewById(R.id.page);
        translateStart = AnimationUtils.loadAnimation(this, R.anim.translate_start);
        translateEnd= AnimationUtils.loadAnimation(this, R.anim.translate_end);

        SlidingPageAnimationListener animListener = new SlidingPageAnimationListener();
        translateStart.setAnimationListener(animListener);
        translateEnd.setAnimationListener(animListener);

        button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            if (isPageOpen) {
                page.setVisibility(View.GONE);
                page.startAnimation(translateEnd);
            } else {
                page.setVisibility(View.VISIBLE);
                page.startAnimation(translateStart);
            }
        });
    }

    private class SlidingPageAnimationListener implements  Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {
            if(isPageOpen) {
                button.setText("Open");
                isPageOpen = false;
            } else {
                button.setText("Close");
                isPageOpen = true;
            }
        }

        @Override
        public void onAnimationEnd(Animation animation) {

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}