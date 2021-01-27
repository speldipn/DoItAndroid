package com.tpmn.doitandroid;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MSG = "EXTRA_MSG";
    CustomViewDrawable customViewDrawable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        customViewDrawable = new CustomViewDrawable(this);
        setContentView(customViewDrawable);
        setup();
    }

    private void setup() {
    }
}

