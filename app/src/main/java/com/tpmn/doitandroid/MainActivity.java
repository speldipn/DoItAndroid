package com.tpmn.doitandroid;

import android.media.MediaCodec;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MSG = "EXTRA_MSG";
    public static final int PORT = 5555;
    EditText editText;
    Button startServerButton;
    Button sendButton;
    TextView serverLogTextView;
    TextView clientLogTextView;
    Socket socket;
    ServerSocket serverSocket;
    ServerThread server;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();
    }

    private void setup() {
        editText = findViewById(R.id.editText);
        startServerButton = findViewById(R.id.startServerButton);
        sendButton = findViewById(R.id.sendButton);
        serverLogTextView = findViewById(R.id.serverLogTextView);
        clientLogTextView = findViewById(R.id.clientLogTextView);

        startServerButton.setOnClickListener(v -> {
            server = new ServerThread();
            server.start();
        });

        sendButton.setOnClickListener(v -> {
            String msg = editText.getText().toString();
            new Thread(() -> {
                try {
                    socket = new Socket();
                    socket.connect(new InetSocketAddress("localhost", PORT));
                    if (socket.isConnected()) {
                        OutputStream os = socket.getOutputStream();
                        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
                        bw.write(msg);
                        printClientLog(msg);
                        socket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        });

    }

    private void printClientLog(String msg) {
        new Handler(Looper.getMainLooper()).post(() -> {
            clientLogTextView.setText("Client: " + msg);
        });
    }

    private void printServerLog(String msg) {
        new Handler(Looper.getMainLooper()).post(() -> {
            serverLogTextView.setText("Server: " + msg);
        });
    }

    class ServerThread extends Thread {
        boolean isRun = false;

        @Override
        public void run() {
            isRun = true;
            try {
                serverSocket = new ServerSocket(PORT);
                printServerLog("Server listening..");
                Socket sock = serverSocket.accept();
                while (sock.isConnected()) {
                    printServerLog("Acception.");
                    InputStream inputStream = sock.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                    printServerLog(br.readLine());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}