package com.tpmn.doitandroid;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class SampleYoutubeActivity extends YouTubeBaseActivity {

    public static final String TAG = "speldipn";
    public static final String EXTRA_MSG = "EXTRA_MSG";

    private static String API_KEY = "AIzaSyDWXmWuB7xjPaN5gwW2hlNtHr9nETESN7E";
    private static String videoId = "FbNi4tXfC6w";

    YouTubePlayer player;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_youtube);
        setup();
    }

    private void setup() {
        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            playVideo();
        });

        YouTubePlayerView playerView = findViewById(R.id.playerView);
        playerView.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                player = youTubePlayer;
                player.setPlayerStateChangeListener(new YouTubePlayer.PlayerStateChangeListener() {
                    @Override
                    public void onLoading() { }

                    @Override
                    public void onLoaded(String s) {
                        Log.d(TAG, "onLoaded()");
                        player.play();
                    }

                    @Override
                    public void onAdStarted() { }

                    @Override
                    public void onVideoStarted() { }

                    @Override
                    public void onVideoEnded() { }

                    @Override
                    public void onError(YouTubePlayer.ErrorReason errorReason) { }
                });
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });
    }

    private void playVideo() {
        if(player != null) {
            if(player.isPlaying()) {
                player.pause();
            }

            player.cueVideo(videoId);
        }
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
