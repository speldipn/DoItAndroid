package com.tpmn.doitandroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

public class MainActivity extends AppCompatActivity {

    EditText inputEditText;
    Button startServiceButton;
    TextView messageTextView;

    BroadcastReceiver receiver;

    public static final String EXTRA_MSG = "EXTRA_MSG";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputEditText = findViewById(R.id.inputEditText);
        startServiceButton = findViewById(R.id.startServiceButton);
        messageTextView = findViewById(R.id.messageTextView);

        startServiceButton.setOnClickListener(v -> {
           String msg = inputEditText.getText().toString();
           Intent intent = new Intent(MainActivity.this, MyService.class);
           intent.putExtra(EXTRA_MSG, msg);
           startService(intent);
            Log.d("speldipn", "Activity to service");
        });

        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String msg = intent.getStringExtra(MainActivity.EXTRA_MSG);
                Intent showIntent = new Intent(getApplicationContext(), MainActivity.class);
                showIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                showIntent.putExtra(MainActivity.EXTRA_MSG, msg);
                startActivity(showIntent);
                Log.d("speldipn", "Broadcast to activity");
            }
        };
        registerReceiver(receiver, new IntentFilter("doitandroid"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if(intent != null) {
            String message = intent.getStringExtra(EXTRA_MSG);
            if(!message.isEmpty()) { messageTextView.setText(message); }
            Log.d("speldipn", "Update message text of activity");
        }
    }


}