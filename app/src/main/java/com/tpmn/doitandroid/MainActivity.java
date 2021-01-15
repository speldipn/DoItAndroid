package com.tpmn.doitandroid;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button startServiceButton;
    EditText nameEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startServiceButton = findViewById(R.id.startServiceButton);
        nameEditText = findViewById(R.id.nameEditText);

        startServiceButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, MyService.class);
            intent.putExtra("command", "show");
            intent.putExtra("name", nameEditText.getText().toString());
            startService(intent);
        });
    }
}