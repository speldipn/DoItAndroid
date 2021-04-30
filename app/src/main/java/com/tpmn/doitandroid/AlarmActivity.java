package com.tpmn.doitandroid;

import android.content.Context;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AlarmActivity extends AppCompatActivity {

    Button button1;
    Button button2;
    Button button3;
    View.OnClickListener button1Listener = v -> {
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT > 26) {
            vibrator.vibrate(VibrationEffect.createOneShot(1000, 10));
        } else {
            vibrator.vibrate(1000);
        }
    };
    View.OnClickListener button2Listener = v -> {
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(), uri);
        ringtone.play();
    };
    View.OnClickListener button3Listener = v -> {
//        MediaPlayer player = MediaPlayer.create(getApplicationContext(), );
//        player.start();
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        setup();
    }

    private void setup() {
        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(button1Listener);

        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(button2Listener);

        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(button3Listener);
    }
}