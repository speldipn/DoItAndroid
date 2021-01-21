package com.tpmn.doitandroid;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MSG = "EXTRA_MSG";
    private boolean isRun = false;
    private int currentIndex = 0;
    private List<User> userList = new ArrayList<User>();
    View itemView;
    TextView nameTextView;
    TextView phoneTextView;
    TextView locationTextView;
    Button startButton;
    Button stopButton;

    Animation appearAnim;
    Animation initAnim;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userList.add(new User("오준영", "010-7319-5025", "오류동 51-31 포스시티 406호"));
        userList.add(new User("오자람", "010-0322-7842", "고척동 241-196 홍성파크빌 201호"));

        initAnim = AnimationUtils.loadAnimation(this, R.anim.init);
        appearAnim = AnimationUtils.loadAnimation(this, R.anim.appear);
        appearAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                currentIndex %= userList.size();
                setData(userList.get(currentIndex++));
            }

            @Override
            public void onAnimationEnd(Animation animation) { }

            @Override
            public void onAnimationRepeat(Animation animation) { }
        });

        itemView = findViewById(R.id.itemView);
        nameTextView = findViewById(R.id.nameTextView);
        phoneTextView = findViewById(R.id.phoneTextView);
        locationTextView = findViewById(R.id.locaitonTextView);

        startButton = findViewById(R.id.startButton);
        stopButton = findViewById(R.id.stopButton);

        userList.add(new User("오준영", "010-7319-5025", "오류동 51-31 406호"));
        userList.add(new User("오자람", "010-5552-0322", "고척동 홍성파크빌 201호"));

        startButton.setOnClickListener(v -> {
            isRun = true;
            new Thread(() -> {
                while (true) {
                    if (isRun) {
                        new Handler(getMainLooper()).post(() -> {
                            itemView.startAnimation(appearAnim);
                        });
                        try { Thread.sleep(2000); } catch (Exception e) { }
                    }
                }
            }).start();
        });

        stopButton.setOnClickListener(v -> {
            isRun = false;
            itemView.startAnimation(initAnim);
        });
    }

    void setData(User user) {
        nameTextView.setText(user.name);
        phoneTextView.setText(user.phone);
        locationTextView.setText(user.location);
    }

    class User {
        String name;
        String phone;
        String location;

        public User(String name, String phone, String location) {
            this.name = name;
            this.phone = phone;
            this.location = location;
        }
    }
}