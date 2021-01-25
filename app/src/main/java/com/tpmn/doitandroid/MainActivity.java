package com.tpmn.doitandroid;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MSG = "EXTRA_MSG";

    EditText editText;
    Button sendButton;
    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();
    }

    private void setup() {
        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);
        sendButton = findViewById(R.id.sendButton);
        sendButton.setOnClickListener(v -> {
            String url = editText.getText().toString();
            new Thread(() -> {
                request(url);
            }).start();
        });
    }

    private void printLogMsg(String msg) {
        new Handler(Looper.getMainLooper()).post(() -> {
            textView.setText(msg);
        });
    }

    private void request(String urlStr) {
        StringBuilder output = new StringBuilder();
        try {
            if(!urlStr.startsWith("http://")) {
                urlStr = "http://" + urlStr;
            }
            URL url = new URL(urlStr);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            if(conn != null) {
                printLogMsg("Connection.");
                conn.setConnectTimeout(10000);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);

                int resCode = conn.getResponseCode();
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while(true) {
                    line = reader.readLine();
                    if(line == null) {
                        break;
                    }

                    output.append(line + "\n");
                }
                reader.close();
                conn.disconnect();

            } else {
                printLogMsg("Connection fail");
            }
        }catch(Exception e) {
            e.printStackTrace();
        }

        printLogMsg(output.toString());
    }
}