package com.tpmn.doitandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LooperActivity extends AppCompatActivity {

    EditText editText;
    Button startButton;
    TextView textView;

    Handler handler = new Handler();

    ProcessThread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_looper);
        setup();
        initView();
    }

    private void setup() {
       thread = new ProcessThread();
    }

    private void initView() {
        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);
        startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener((v) -> {
            String input = editText.getText().toString();
            Message message = Message.obtain();
            message.obj = input;

            thread.processHandler.sendMessage(message);
        });
    }

    class ProcessThread extends Thread {
        ProcessHandler processHandler = new ProcessHandler();

        @Override
        public void run() {
            Looper.prepare();
            Looper.loop();
        }

        class ProcessHandler extends Handler {
            @Override
            public void handleMessage(@NonNull Message msg) {
                //super.handleMessage(msg);
                final String output = msg.obj + " from thread.";
                handler.post(() -> {
                    textView.setText(output);
                });
            }
        }
    }
}