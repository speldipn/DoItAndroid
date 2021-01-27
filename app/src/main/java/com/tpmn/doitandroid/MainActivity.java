package com.tpmn.doitandroid;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MSG = "EXTRA_MSG";
    CustomViewDrawing customViewDrawing;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        customViewDrawing = new CustomViewDrawing(this);
        setContentView(customViewDrawing);
        setup();
    }

    private void setup() {
    }
}

