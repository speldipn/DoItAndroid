package com.tpmn.doitandroid;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MSG = "EXTRA_MSG";

    RadioGroup radioGroup;
    CustomViewCap customViewCap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();
    }

    private void setup() {
        customViewCap = findViewById(R.id.customViewCap);
        radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.radioSquare: customViewCap.setCap(Paint.Cap.SQUARE); break;
                case R.id.radioButt: customViewCap.setCap(Paint.Cap.BUTT); break;
                case R.id.radioRound: customViewCap.setCap(Paint.Cap.ROUND); break;
                default: break;
            }
        });

    }
}

