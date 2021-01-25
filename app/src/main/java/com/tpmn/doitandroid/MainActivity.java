package com.tpmn.doitandroid;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MSG = "EXTRA_MSG";

    EditText urlEditText;
    Button getButton;

    TextView htmlTextView;
    WebView webView;

    String html = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();
    }

    private void setup() {
        urlEditText = findViewById(R.id.urlEditText);
        getButton = findViewById(R.id.getButton);
        
        htmlTextView = findViewById(R.id.htmlTextView);
        webView = findViewById(R.id.webView);
        
        getButton.setOnClickListener(v -> {
            String urlStr = urlEditText.getText().toString();
            new Thread(() -> {
                request(urlStr); 
            }).start();
        });

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
    }

    private void printMsg(String msg) {
        new Handler(Looper.getMainLooper()).post(() -> {
          htmlTextView.setText(msg);
          webView.loadData(html, "text/html", "utf-8");
            Log.d("speldipn", html);
        });
    }

    private void request(String urlStr) {
        StringBuilder output = new StringBuilder();
        StringBuilder htmlOutput = new StringBuilder();

        try {
            if(!urlStr.startsWith("http://")) {
                urlStr = "http://" + urlStr;
            }
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            if(conn != null) {
                printMsg("Connected.");
                conn.setConnectTimeout(10000);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);

                InputStream is = conn.getInputStream();
                DataInputStream dis = new DataInputStream(is);

                String line;
                do {
                    line = dis.readLine();
                    if(line != null) {
                        output.append(line + "\n");
                        htmlOutput.append(line);
                    }
                } while(line != null);

                dis.close();
                conn.disconnect();
            } else {
                printMsg("Disconnected.");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        printMsg(output.toString());
        html = htmlOutput.toString();
    }
}