package com.tpmn.doitandroid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import static java.lang.Thread.sleep;

public class DelayedActivity extends AppCompatActivity {

    Button requestButton;
    TextView textView;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delayed);
        initView();
        setup();
    }

    private void initView() {
        textView = findViewById(R.id.textView);
        requestButton = findViewById(R.id.requestButton);
        requestButton.setOnClickListener(v -> request());
    }

    private void setup() {
        handler = new Handler();
    }

    private void request() {
        String title = "Remote request";
        String message = "Need you request data?";
        String titleButtonYes = "Yes";
        String titleButtonNo = "No";
        DialogInterface.OnClickListener clickYesListener = (dialog, which) -> {
            handler.postDelayed(() -> textView.setText(R.string.done), 5000);
            dialog.dismiss();
        };
        DialogInterface.OnClickListener clickNoListener = (dialog, which) -> dialog.dismiss();
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(titleButtonYes, clickYesListener)
                .setNegativeButton(titleButtonNo, clickNoListener)
                .create();
        dialog.show();

        textView.setText(R.string.waiting);
    }
}