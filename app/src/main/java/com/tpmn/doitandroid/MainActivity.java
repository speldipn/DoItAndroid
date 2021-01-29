package com.tpmn.doitandroid;

import android.content.ContentValues;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

import org.jetbrains.annotations.NotNull;

import java.io.File;

public class MainActivity extends AppCompatActivity implements AutoPermissionsListener {

    public static final String TAG = "speldipn";
    public static final String EXTRA_MSG = "EXTRA_MSG";
    public static final String ViDEO_URL = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4";
    public static final int REQ_PERMS = 100;

    MediaRecorder recorder;
    MediaPlayer player;

    Button startRecordButton;
    Button stopRecordButton;
    String filename;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();

        AutoPermissions.Companion.loadAllPermissions(this, REQ_PERMS);
    }


    private void setup() {
        startRecordButton = findViewById(R.id.startRecordButton);
        startRecordButton.setOnClickListener(v -> {
            startRecording();
        });
        stopRecordButton = findViewById(R.id.stopRecordButton);
        stopRecordButton.setOnClickListener(v -> {
            stopRecording();
        });

        String sdcard = Environment.getExternalStorageDirectory().getAbsolutePath();
        filename = sdcard + File.separator + "recorded.mp4";

        showToast("sdcard path: " + sdcard);
    }

    private void startRecording() {
        if (recorder == null) {
            recorder = new MediaRecorder();
        }

        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        recorder.setOutputFile(filename);

        try {
            recorder.prepare();
            recorder.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void stopRecording() {
        if (recorder == null) {
            return;
        }

        recorder.stop();
        recorder.release();
        recorder = null;

        ContentValues values = new ContentValues(10);
        values.put(MediaStore.MediaColumns.TITLE, "Recorded");
        values.put(MediaStore.Audio.Media.ALBUM, "Audio Album");
        values.put(MediaStore.Audio.Media.ARTIST, "Audio Album");
        values.put(MediaStore.Audio.Media.DISPLAY_NAME, "Audio Album");
        values.put(MediaStore.Audio.Media.IS_RINGTONE, 1);
        values.put(MediaStore.Audio.Media.IS_MUSIC, 1);
        values.put(MediaStore.MediaColumns.DATE_ADDED, System.currentTimeMillis()/1000);
        values.put(MediaStore.MediaColumns.MIME_TYPE, "audio/mp4");
        values.put(MediaStore.Audio.Media.DATA, filename);

        Uri audioUri = getContentResolver().insert(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, values);
        if(audioUri == null) {
            showToast("Audio insert failed");
            return;
        }
    }

    @Override
    public void onDenied(int i, @NotNull String[] permissions) {
        showToast("permissions denied");
    }

    @Override
    public void onGranted(int i, @NotNull String[] permissions) {
        showToast("permissions granted");
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
