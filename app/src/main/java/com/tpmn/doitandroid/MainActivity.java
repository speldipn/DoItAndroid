package com.tpmn.doitandroid;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MSG = "EXTRA_MSG";

    BackgroundTask backgroundTask;
    ProgressBar progressBar;
    Button confirmButton;
    Button cancelButton;

    int value = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        confirmButton = findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(v -> {
            backgroundTask = new BackgroundTask();
            backgroundTask.execute();
        });
        cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(v -> {
            if(backgroundTask != null) { backgroundTask.cancel(false); }
        });

    }

    class BackgroundTask extends AsyncTask<Integer, Integer, Integer> {

        @Override
        protected void onPreExecute() {
            value = 0;
            progressBar.setProgress(value);
        }

        @Override
        protected Integer doInBackground(Integer... integers) {
            if(integers.length > 0) {
                Log.d("speldipn", "doInBackground:" + integers[0]);
                value = integers[0];
            }
            while (!isCancelled()) {
                value += 1;
                if (value >= 100) {
                    break;
                } else {
                    publishProgress(value);
                }

                try { Thread.sleep(100); } catch (Exception ignored) { }
            }

            return value;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            progressBar.setProgress(0);
        }

        @Override
        protected void onCancelled() {
            progressBar.setProgress(0);
        }
    }
}