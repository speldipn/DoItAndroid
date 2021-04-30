package com.tpmn.doitandroid;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class SamplePushActivity extends AppCompatActivity {

    private static final String TAG = "FMS";

    Button button;
    TextView textView;
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_push);
        setup();
    }

    private void setup() {
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(this, new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult result) {
                showToast("onSuccess called.");
                String newToken = result.getToken();
                Log.d(TAG, newToken);
                println(newToken);
            }
        });

        button.setOnClickListener(v -> {
            String instanceId = FirebaseInstanceId.getInstance().getId();
            String msg = "Valid instane Id: " + instanceId;
            println(msg);
            Log.d(TAG, msg);
        });
    }

    private void println(String msg) {
        textView2.append(msg + "\n");
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}