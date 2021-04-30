package com.tpmn.doitandroid;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import static android.app.ActionBar.DISPLAY_SHOW_HOME;
import static android.app.ActionBar.DISPLAY_USE_LOGO;
import static androidx.appcompat.app.ActionBar.DISPLAY_HOME_AS_UP;
import static androidx.appcompat.app.ActionBar.DISPLAY_SHOW_TITLE;

public class ActionbarActivity extends AppCompatActivity {

    Button showButton;
    Button hideButton;
    Button newPageButton;
    Button showSubtitleButton;
    Button hideSubtitleButton;
    Button showLogoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actionbar);
        setup();
    }

    @SuppressLint("WrongConstant")
    private void setup() {
        showButton = findViewById(R.id.showButton);
        showButton.setOnClickListener(v -> {
                    getSupportActionBar().show();
                    getSupportActionBar().setSubtitle("This subtitle");
                }
        );

        hideButton = findViewById(R.id.hideButton);
        hideButton.setOnClickListener(v -> getSupportActionBar().hide());

        newPageButton = findViewById(R.id.newPageButton);
        newPageButton.setOnClickListener(v -> startActivity(new Intent(this, AlarmActivity.class)));

        showSubtitleButton = findViewById(R.id.showSubtitleButton);
        showSubtitleButton.setOnClickListener(v -> getSupportActionBar().setSubtitle("This is subtitle"));

        hideSubtitleButton = findViewById(R.id.hideSubtitleButton);
        hideSubtitleButton.setOnClickListener(v -> getSupportActionBar().setSubtitle(""));

        showLogoButton = findViewById(R.id.showLogoButton);
        showLogoButton.setOnClickListener(v -> {
                    getSupportActionBar().setLogo(R.drawable.home);
//                    getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_USE_LOGO);
                    getSupportActionBar().setDisplayOptions(DISPLAY_SHOW_HOME | DISPLAY_USE_LOGO | DISPLAY_SHOW_TITLE | DISPLAY_HOME_AS_UP);
                }
        );
    }

}