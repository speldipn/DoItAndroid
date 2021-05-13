package com.tpmn.doitandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class ThreadAnimationActivity extends AppCompatActivity {

    ImageView imageView;
    Button button;
    AnimThread animThread = new AnimThread();
    List<Drawable> drawableList = new ArrayList<>();
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_animation);
        init();
        initView();
    }

    private void init() {
        drawableList.add(ContextCompat.getDrawable(this, R.drawable.face1));
        drawableList.add(ContextCompat.getDrawable(this, R.drawable.face2));
        drawableList.add(ContextCompat.getDrawable(this, R.drawable.face3));
        drawableList.add(ContextCompat.getDrawable(this, R.drawable.face4));
        drawableList.add(ContextCompat.getDrawable(this, R.drawable.face5));
    }

    private void initView() {
        imageView = findViewById(R.id.imageView);
        button = findViewById(R.id.button);
        button.setOnClickListener((v) -> {
            animThread.start();
        });
    }

    class AnimThread extends Thread {
        @Override
        public void run() {
            int index = 0;
            for (int i = 0; i < 100; ++i) {
                final Drawable drawable = drawableList.get(index);
                index += 1;
                if (index > 4) {
                    index = 0;
                }
                handler.post(() -> imageView.setImageDrawable(drawable));

                try { Thread.sleep(1000); } catch (Exception ignored) { }
            }
        }
    }

}