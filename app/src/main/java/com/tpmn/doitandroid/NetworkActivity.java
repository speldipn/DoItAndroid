package com.tpmn.doitandroid;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class NetworkActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;
    TextView textView2;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        initView();
    }

    private void initView() {
        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            final String data = editText.getText().toString();
            new Thread(() -> {
                send(data);
            }).start();
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener((v) -> {
            startServer();
        });
    }

    private void startServer() {
        try {
            int portNumber = 5001;

            ServerSocket server = new ServerSocket(portNumber);
            printServerLog("Server started. " + portNumber);
            while (true) {
                Socket sock = server.accept();
                InetAddress clientHost = sock.getLocalAddress();
                int clientPort = sock.getPort();
                printServerLog("Client joined: " + clientHost + " : " + clientPort);

                ObjectInputStream instream = new ObjectInputStream(sock.getInputStream());
                Object obj = instream.readObject();
                printServerLog("Recevied data: " + obj);

                ObjectOutputStream outstream = new ObjectOutputStream(sock.getOutputStream());
                outstream.writeObject(obj + " from server.");
                outstream.flush();
                printServerLog("Server send data");

                sock.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void printClientLog(final String data) {
        Log.d("speldipn", "client: " + data);
        handler.post(() -> textView.append(data + "\n"));
    }

    private void printServerLog(final String data) {
        Log.d("speldipn", "server: " + data);
        handler.post(() -> textView2.append(data + "\n"));
    }

    private void send(final String data) {
        try {
            int portNumber = 5001;
            Socket sock = new Socket("localhost", portNumber);
            printClientLog("Socket connected.");
            ObjectOutputStream outstream = new ObjectOutputStream(sock.getOutputStream());
            outstream.writeObject(data);
            outstream.flush();
            printClientLog("send done.");

            ObjectInputStream instream = new ObjectInputStream(sock.getInputStream());
            printClientLog("response from server: " + instream.readObject());
            sock.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}