package com.tpmn.doitandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MSG = "EXTRA_MSG";

    Button nextButton;
    public static Animation animLeft;
    public static Animation animRight;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        animLeft = AnimationUtils.loadAnimation(this, R.anim.activity_left);
        animRight = AnimationUtils.loadAnimation(this, R.anim.activity_right);

        nextButton = findViewById(R.id.nextbutton);
        nextButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, InputActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        });
    }
}