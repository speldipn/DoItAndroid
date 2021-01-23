package com.tpmn.doitandroid;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tpmn.doitandroid.custom.UserItem;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MSG = "EXTRA_MSG";

    Animation translateIn;
    Animation translateOut;

    ViewGroup container;
    UserItem item1;
    UserItem item2;

    Button startButton;
    Button stopButton;

    AnimationThread animationThread = null;
    boolean isRun = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();
    }

    private void setup() {
        container = findViewById(R.id.container);

        translateIn = AnimationUtils.loadAnimation(this, R.anim.translate_in);
        translateOut = AnimationUtils.loadAnimation(this, R.anim.translate_out);

        item1 = new UserItem(this);
        item1.setName("김경호");
        item1.setPhone("010-3333-4444");
        item1.setLocation("서울시 구로구 오류동");
        container.addView(item1);

        item2 = new UserItem(this);
        item2.setName("박재범");
        item2.setPhone("010-5555-6666");
        item2.setLocation("서울시 구로구 고척동");
        container.addView(item2);

        startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener((v) -> {
            animationThread = new AnimationThread();
            animationThread.start();
            isRun = true;
        });
        stopButton = findViewById(R.id.stopButton);
        stopButton.setOnClickListener((v) -> {
            isRun = false;
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        isRun = false;
    }

    class AnimationThread extends Thread {
        int index = 0;

        @Override
        public void run() {
            while(isRun) {
                new Handler(Looper.getMainLooper()).post(() -> {
                    if (index == 0) {
                        item1.startAnimation(translateIn);
                        item2.startAnimation(translateOut);
                        index = 1;
                    } else {
                        item1.startAnimation(translateOut);
                        item2.startAnimation(translateIn);
                        index = 0;
                    }
                });

                try { Thread.sleep(2000); } catch (Exception ignored) { }
            }
        }
    }

}