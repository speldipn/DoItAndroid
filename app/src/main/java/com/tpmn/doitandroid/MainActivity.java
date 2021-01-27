package com.tpmn.doitandroid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MSG = "EXTRA_MSG";

    Button cameraButton;
    ImageView imageView;
    File file;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();
    }

    private void setup() {
        cameraButton = findViewById(R.id.cameraButton);
        imageView = findViewById(R.id.imageView);

        cameraButton.setOnClickListener(v -> {
            takePicture();
        });
    }

    private void takePicture() {
        if(file == null) {
            file = createFile();
        }

        Uri uri = FileProvider.getUriForFile(this, "com.tpmn.doitandroid.intent.fileprovider", file);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        if(intent.)

    }

    private File createFile() {
        return new File("capture.jpg");
    }
}

