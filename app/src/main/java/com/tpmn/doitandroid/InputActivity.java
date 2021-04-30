package com.tpmn.doitandroid;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class InputActivity extends AppCompatActivity {

    View thisView;
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        thisView = LayoutInflater.from(this).inflate(R.layout.activity_input, null);
        setContentView(thisView);
        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> {
            finish();
        });
//        thisView.startAnimation(MainActivity.animLeft);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void finish() {
//        thisView.startAnimation(MainActivity.animRight);
        new Handler(getMainLooper()).postDelayed(super::finish, 500);
    }
}