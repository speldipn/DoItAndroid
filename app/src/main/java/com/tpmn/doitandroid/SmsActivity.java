package com.tpmn.doitandroid;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SmsActivity extends AppCompatActivity {

    TextView phoneTextView;
    TextView messageTextView;
    TextView dateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        Intent intent = getIntent();
        setupViews();
    }

    private void setupViews() {
//        phoneTextView = findViewById(R.id.phoneTextView);
//        messageTextView = findViewById(R.id.messageTextView);
//        dateTextView = findViewById(R.id.dateTextView);
//
//        String phone = smsList.get(0).phone;
//        String message = smsList.get(0).message;
//        String date = smsList.get(0).date;
//
//        phoneTextView.setText(phone);
//        messageTextView.setText(message);
//        dateTextView.setText(date);
    }
}