package com.tpmn.doitandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class PracticeActivity extends AppCompatActivity {

    public static final String TAG = "spdn";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);
        setup();
    }

    private void setup() {
        ProgressBar progressBar = findViewById(R.id.progress);
        TextView textView = findViewById(R.id.textView);
        Button startButton = findViewById(R.id.startButton);

        startButton.setOnClickListener((v) -> {
            progressBar.setVisibility(View.VISIBLE);
            new Thread(() -> {
                for (int i = 0; i < 3; ++i) {
                    String msg = String.valueOf(i + 1);
                    runOnUiThread(() -> textView.setText(msg));
                    try { Thread.sleep(1000); } catch (Exception ignored) { }
                }
                runOnUiThread(() -> {
                    textView.setText("0");
                    progressBar.setVisibility(View.INVISIBLE);
                });
            }).start();
        });
    }

}