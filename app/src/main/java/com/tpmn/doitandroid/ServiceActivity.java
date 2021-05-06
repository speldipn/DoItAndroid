package com.tpmn.doitandroid;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ServiceActivity extends AppCompatActivity {

    Button startButton;
    TextView displayTextView;
    Boolean isStartMyService = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        setup();
    }

    @Override
    protected void onDestroy() {
        if(isStartMyService) {
            stopMyService();
        }
        super.onDestroy();
    }

    private void setup() {
        startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(v -> startMyService());
        displayTextView = findViewById(R.id.displayTextView);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null) {
            String name = intent.getStringExtra("name");
            if (name != null) {
                displayTextView.setText(name);
            }
        }
    }

    private void startMyService() {
        Intent intent = new Intent(this, MyService.class);
        intent.putExtra("name", "Neo");
        startService(intent);

        isStartMyService = true;
    }

    private void stopMyService() {
        Intent intent = new Intent(this, MyService.class);

        stopService(intent);
        isStartMyService = false;
    }
}