package com.tpmn.doitandroid;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileReader;

public class MainActivity extends AppCompatActivity implements AutoPermissionsListener, MediaPlayer.OnCompletionListener {

    public static final String AUDIO_URL = "http://commondatastorage.googleapis.com/codeskulptor-demos/DDR_assets/Kangaroo_MusiQue_-_The_Neverwritten_Role_Playing_Game.mp3";
    public static final String TAG = "speldipn";
    public static final String EXTRA_MSG = "EXTRA_MSG";
    public static final String FILENAME = "capture.jpg";

    public static final int REQ_CAMERA = 1001;
    public static final int REQ_PERMS = 1002;

    Button playButton;
    Button stopButton;
    Button pauseButton;
    Button resumeButton;

    SampleAudioPlayer player;
    int position = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();

        AutoPermissions.Companion.loadAllPermissions(this, REQ_PERMS);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        player.release();
    }

    private void setup() {
        playButton = findViewById(R.id.playButton);
        playButton.setOnClickListener(onStartClickListener);

        stopButton = findViewById(R.id.stopButton);
        stopButton.setOnClickListener(onStopClickListener);

        pauseButton = findViewById(R.id.pauseButton);
        pauseButton.setOnClickListener(onPauseClickListener);

        resumeButton = findViewById(R.id.resumeButton);
        resumeButton.setOnClickListener(onResumeClickListener);

        player = new SampleAudioPlayer();
    }

    View.OnClickListener onStartClickListener = v -> {
        Toast.makeText(this, "Play music", Toast.LENGTH_SHORT).show();
        player = new SampleAudioPlayer();
        player.start(AUDIO_URL);
        player.setCompleteListener(this);
    };

    View.OnClickListener onStopClickListener = v -> {
        if(player != null) {
            player.stop();
            Toast.makeText(this, "Stop music", Toast.LENGTH_SHORT).show();
        }
    };

    View.OnClickListener onPauseClickListener = v -> {
        if(player != null) {
            position = player.getCurrentPosition();
            player.pause();
            Toast.makeText(this, "Pause music", Toast.LENGTH_SHORT).show();
        }
    };

    View.OnClickListener onResumeClickListener = v -> {
        if(player != null && !player.isPlaying()) {
            player.resume();
            player.seekTo(position);
            Toast.makeText(this, "Resume music", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void onDenied(int i, @NotNull String[] permissions) {
        Toast.makeText(this, "Permission denied: " + permissions.length, Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void onGranted(int i, @NotNull String[] permissions) {
        Toast.makeText(this, "Permission granted: " + permissions.length, Toast.LENGTH_SHORT)
                .show();

    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        player.release();
        Toast.makeText(this, "Player completion", Toast.LENGTH_SHORT).show();
    }
}

