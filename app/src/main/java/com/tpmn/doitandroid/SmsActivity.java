package com.tpmn.doitandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class SmsActivity extends AppCompatActivity {

    TextView phoneTextView;
    TextView messageTextView;
    TextView dateTextView;
    List<CustomSMS> smsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        Intent intent = getIntent();
        smsList = intent.getParcelableArrayListExtra("sms");
        Log.d("speldipn", "sms list count: " + smsList.size());
        setupViews();
    }

    private void setupViews() {
        phoneTextView = findViewById(R.id.phoneTextView);
        messageTextView = findViewById(R.id.messageTextView);
        dateTextView = findViewById(R.id.dateTextView);

        String phone = smsList.get(0).phone;
        String message = smsList.get(0).message;
        String date = smsList.get(0).date;

        phoneTextView.setText(phone);
        messageTextView.setText(message);
        dateTextView.setText(date);
    }
}