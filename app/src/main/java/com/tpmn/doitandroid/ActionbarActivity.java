package com.tpmn.doitandroid;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ActionbarActivity extends AppCompatActivity {

    Button showButton;
    Button hideButton;
    Button newPageButton;
    Button showSubtitleButton;
    Button hideSubtitleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actionbar);
        setup();
    }

    private void setup() {
        showButton = findViewById(R.id.showButton);
        showButton.setOnClickListener(v -> {
                    getSupportActionBar().show();
                    getSupportActionBar().setSubtitle("This subtitle");
                }
        );

        hideButton = findViewById(R.id.hideButton);
        hideButton.setOnClickListener(v -> getSupportActionBar().hide());
        hideButton.setOnClickListener(v -> getSupportActionBar().hide());

        newPageButton = findViewById(R.id.newPageButton);
        newPageButton.setOnClickListener(v -> startActivity(new Intent(this, AlarmActivity.class)));

        showSubtitleButton = findViewById(R.id.showSubtitleButton);
        showSubtitleButton.setOnClickListener(v -> getSupportActionBar().setSubtitle("This is subtitle"));

        hideSubtitleButton = findViewById(R.id.hideSubtitleButton);
        hideSubtitleButton.setOnClickListener(v -> getSupportActionBar().setSubtitle(""));
    }

}