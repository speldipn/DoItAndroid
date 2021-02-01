package com.tpmn.doitandroid;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.SurfaceHolder;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements AutoPermissionsListener {

    public static final String TAG = "speldipn";
    public static final String EXTRA_MSG = "EXTRA_MSG";

    public static final String CHANNEL_ID = "channel1_id";
    public static final String CHANNEL_NAME = "channel1_name";

    NotificationManager manager;
    Button alarmButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();

        AutoPermissions.Companion.loadAllPermissions(this, 101);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        AutoPermissions.Companion.parsePermissions(this, requestCode, permissions, this);
    }

    private void setup() {
        alarmButton = findViewById(R.id.alarmButton);
        alarmButton.setOnClickListener(v -> {
            showNoti1();
        });
    }

    private void showNoti1() {
        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = null;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if(manager.getNotificationChannel(CHANNEL_NAME) == null) {
                NotificationChannel channel = new NotificationChannel(
                        CHANNEL_ID,
                        CHANNEL_NAME,
                        NotificationManager.IMPORTANCE_DEFAULT
                );
                manager.createNotificationChannel(channel);
                builder = new NotificationCompat.Builder(this, CHANNEL_ID);
            }
        } else {
            builder = new NotificationCompat.Builder(this);
        }
        builder.setContentTitle("Simple Alarm");
        builder.setContentText("Alarm message");
        builder.setSmallIcon(android.R.drawable.ic_menu_view);

        Intent intent = new Intent(this, InputActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this,
                101,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        builder.setContentIntent(pendingIntent);

        Notification noti = builder.build();
        manager.notify(1, noti);
    }


    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDenied(int i, @NotNull String[] permissions) {
        showToast("Permissions denied: "  + permissions.length);
    }

    @Override
    public void onGranted(int i, @NotNull String[] permissions) {
        showToast("Permissions granted: "  + permissions.length);
    }
}
