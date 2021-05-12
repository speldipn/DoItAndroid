package com.tpmn.doitandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class KeypadActivity extends AppCompatActivity {

    EditText editText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keypad);
        initView();
    }

    private void initView() {
       editText = findViewById(R.id.editText);
       button = findViewById(R.id.button);
       button.setOnClickListener(v -> {
           InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
           imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

       });

    }
}