package com.tpmn.doitandroid;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

import org.jetbrains.annotations.NotNull;

public class LocationActivity extends AppCompatActivity implements AutoPermissionsListener {

    final String[] permissions = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        setup();

        AutoPermissions.Companion.loadSelectedPermissions(this, 101, permissions);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        AutoPermissions.Companion.parsePermissions(this, requestCode, permissions, this);
    }

    private void setup() {
        textView = findViewById(R.id.textView);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            startLocationService();
        });
    }

    private void startLocationService() {
        for (String permission : permissions) {
            if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
        }

        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        try {
            Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//            Location location = manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (location != null) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                String msg = String.format("Latitude: %.2f\nLongitude: %.2f", latitude, longitude);
                new Handler(Looper.getMainLooper()).post(() -> {
                    textView.append(msg + "\n\n");
                });

                GPSListener gpsListener = new GPSListener();
                long minTime = 10000;
                float minDistance = 0;

                manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime, minDistance, gpsListener);
                showToast("내 위치확인 요청함");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDenied(int i, @NotNull String[] permissions) {
        showToast("Permissions denied: " + permissions.length);
    }

    @Override
    public void onGranted(int i, @NotNull String[] permissions) {
        showToast("Permissions granted: " + permissions.length);
    }

    class GPSListener implements LocationListener {

        @Override
        public void onLocationChanged(@NonNull Location location) {
            Double latitude = location.getLatitude();
            Double longitude = location.getLongitude();
            String msg = "Latitude: " + latitude + "\nLongitude: " + longitude;
            textView.setText(msg + "\n\n");
        }
    }
}

