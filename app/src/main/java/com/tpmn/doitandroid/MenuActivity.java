package com.tpmn.doitandroid;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();
        String command = intent.getStringExtra("command");
        String name = intent.getStringExtra("name");
        Log.d("speldipn", "command: " + command);
        Log.d("speldipn", "name: " + name);
    }
}