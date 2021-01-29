package com.tpmn.doitandroid;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "speldipn";
    public static final String EXTRA_MSG = "EXTRA_MSG";
    public static final String ViDEO_URL = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4";

    Button playButton;
    VideoView videoView;
    MediaController mediaController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();
    }


    private void setup() {
        playButton = findViewById(R.id.playButton);
        videoView = findViewById(R.id.videoView);

        mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);

        playButton.setOnClickListener(v -> {
            videoView.setVideoURI(Uri.parse(ViDEO_URL));
            videoView.requestFocus();
            videoView.start();
        });
    }

}

