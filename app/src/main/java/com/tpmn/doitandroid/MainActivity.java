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

public class MainActivity extends AppCompatActivity implements AutoPermissionsListener {

    public static final String TAG = "speldipn";
    public static final String EXTRA_MSG = "EXTRA_MSG";
    public static final String FILENAME = "capture.jpg";

    public static final int REQ_CAMERA = 1001;
    public static final int REQ_PERMS = 1002;

    Button cameraButton;
    FrameLayout previewContainer;
    SurfaceCutomView surfaceCustomView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();

        AutoPermissions.Companion.loadAllPermissions(this, REQ_PERMS);
    }

    private void setup() {
        cameraButton = findViewById(R.id.cameraButton);
        previewContainer = findViewById(R.id.previewContainer);

        surfaceCustomView = new SurfaceCutomView(this);
        previewContainer.addView(surfaceCustomView);

        cameraButton.setOnClickListener(v -> {
            takePicture();
        });
    }

    private void takePicture() {
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

