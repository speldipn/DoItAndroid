package com.tpmn.doitandroid;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
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
    Button startButton;
    Button stopButton;

    ViewGroup container;

    View userView1;
    View userView2;

    Animation appearAnim;
    Animation disappearAnim;
    Animation initAnim;

    private void setUserItem(View view, User user) {
        TextView nameTextView = view.findViewById(R.id.nameTextView);
        nameTextView.setText(user.name);
        TextView phoneTextView = view.findViewById(R.id.phoneTextView);
        phoneTextView.setText(user.phone);
        TextView locationTextView = view.findViewById(R.id.locaitonTextView);
        locationTextView.setText(user.location);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userList.add(new User("Neo", "010-3333-1111", "오류동 123-456 106호"));
        userList.add(new User("Rami", "010-5555-2222", "고척동 456-123 101호"));

        container = findViewById(R.id.container);

        userView1 = LayoutInflater.from(this).inflate(R.layout.user_item, container, false);
        setUserItem(userView1, userList.get(0));
        container.addView(userView1);

        userView2 = LayoutInflater.from(this).inflate(R.layout.user_item, container, false);
        setUserItem(userView2, userList.get(1));
        container.addView(userView2);

        initAnim = AnimationUtils.loadAnimation(this, R.anim.init);
        appearAnim = AnimationUtils.loadAnimation(this, R.anim.appear);
        disappearAnim = AnimationUtils.loadAnimation(this, R.anim.disappear);
        startButton = findViewById(R.id.startButton);
        stopButton = findViewById(R.id.stopButton);

        startButton.setOnClickListener(v -> {
            new Thread(() -> {

                isRun = true;
                while (true) {
                    if (isRun) {
                        new Handler(getMainLooper()).post(() -> {
                            if(currentIndex == 0)  {
                                userView1.startAnimation(appearAnim);
                                userView2.startAnimation(disappearAnim);
                                currentIndex = 1;
                            } else {
                                userView1.startAnimation(disappearAnim);
                                userView2.startAnimation(appearAnim);
                                currentIndex = 0;
                            }

                        });
                        try { Thread.sleep(2000); } catch (Exception ignored) { }
                    }
                }
            }).start();
        });

        stopButton.setOnClickListener(v -> {
            isRun = false;
        });
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