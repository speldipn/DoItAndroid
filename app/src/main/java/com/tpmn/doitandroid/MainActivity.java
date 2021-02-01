package com.tpmn.doitandroid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements AutoPermissionsListener {

    public static final String TAG = "speldipn";
    public static final String EXTRA_MSG = "EXTRA_MSG";

    Button cameraButton;
    FrameLayout previewContainer;

    CameraCustomView cameraView;
    SurfaceHolder holder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();

        AutoPermissions.Companion.loadAllPermissions(this, 101);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 101) {
        }
    }

    private void setup() {
        cameraButton = findViewById(R.id.cameraButton);
        previewContainer = findViewById(R.id.previewContainer);


        cameraView = new CameraCustomView(this);
        holder = cameraView.getHolder();
                cameraView.capture(new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                try {
                    camera.setPreviewDisplay(holder);
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        });
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDenied(int i, @NotNull String[] strings) {
    }

    @Override
    public void onGranted(int i, @NotNull String[] strings) {
    }
}
