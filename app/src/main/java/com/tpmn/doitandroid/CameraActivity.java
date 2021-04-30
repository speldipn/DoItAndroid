package com.tpmn.doitandroid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

import org.jetbrains.annotations.NotNull;

import java.io.File;

public class CameraActivity extends AppCompatActivity implements AutoPermissionsListener {

    public static final String TAG = "speldipn";
    public static final String EXTRA_MSG = "EXTRA_MSG";
    public static final String FILENAME = "capture.jpg";

    public static final int REQ_CAMERA = 1001;
    public static final int REQ_PERMS = 1002;

    Button cameraButton;
    ImageView cameraImageView;
    File file;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        setup();

        AutoPermissions.Companion.loadAllPermissions(this, REQ_PERMS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQ_CAMERA) {
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
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 8; /* 1/8 down scaling */
            Bitmap bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath(), options);
            cameraImageView.setImageBitmap(bitmap);
        } else {
            Log.d(TAG, "imgFile not exists");
        }
    }

    private void takePicture() {
        if (file == null) {
            file = createFile();
        }

        Uri uri = FileProvider.getUriForFile(this, "com.tpmn.doitandroid.fileprovider", file);
        Log.d("speldipn", "Capture, new file path = " + uri);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQ_CAMERA);
        }
    }

    private Bitmap rotateImage(Bitmap bitmap, int angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);
        /* WIP */
    }

    private File createFile() {
        return new File(Environment.getExternalStorageDirectory(), FILENAME);
    }

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
}

