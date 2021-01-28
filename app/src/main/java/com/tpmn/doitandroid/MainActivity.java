package com.tpmn.doitandroid;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileReader;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "speldipn";
    public static final String EXTRA_MSG = "EXTRA_MSG";
    public static final String FILENAME = "capture.jpg";

    public static final int REQ_CAMERA = 1001;
    public static final int REQ_PERMS = 1002;

    Button cameraButton;
    ImageView cameraImageView;
    File file;

    private final String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();
        checkPerms();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQ_CAMERA) {
            Log.d(TAG, "intent: " + data == null ? "null" : "not null");
            loadImage();
        }
    }

    private void setup() {
        cameraButton = findViewById(R.id.cameraButton);
        cameraImageView = findViewById(R.id.cameraImageView);

        cameraButton.setOnClickListener(v -> {
            takePicture();
        });
    }

    private void loadImage() {
        File imgFile = new File(Environment.getExternalStorageDirectory(), FILENAME);
        if (imgFile.exists()) {
            Bitmap convertedBitmap = convertBitmap(imgFile);
            cameraImageView.setImageBitmap(convertedBitmap);
        } else ㅊ{
            Log.d(TAG, "imgFile not exists");
        }
    }

    private Bitmap convertBitmap(File imgFile) {
        return BitmapFactory.decodeFile(imgFile.getAbsolutePath());
    }

    private void checkPerms() {
        requestPermissions(permissions, REQ_PERMS);
    }

    private void takePicture() {
        if (file == null) {
            file = createFile();
        }

        Uri uri = FileProvider.getUriForFile(this, "com.tpmn.doitandroid.fileprovider", file);
        Log.d("speldipn", "Capture, new file path = " + uri);
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQ_CAMERA);
        }
    }

    private Bitmap rotateImage(Bitmap bitmap, int angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);

    }

    private File createFile() {
        return new File(Environment.getExternalStorageDirectory(), FILENAME);
    }
}

