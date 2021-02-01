package com.tpmn.doitandroid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.util.Strings;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

import org.jetbrains.annotations.NotNull;

import java.security.Provider;

public class SampleMapActivity extends AppCompatActivity implements AutoPermissionsListener {

    public static final int REQ_PERMS = 101;
    GoogleMap map;
    LatLng seoul = new LatLng(37, 127);
    SupportMapFragment mapFragment;
    final String[] permissions = {
            Manifest.permission.ACCESS_FINE_LOCATION
    };
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_map);
        setup();

        AutoPermissions.Companion.loadSelectedPermissions(this, REQ_PERMS, permissions);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        AutoPermissions.Companion.parsePermissions(this, requestCode, permissions, this);
    }

    private boolean checkPerms() {
        for (int i = 0; i < permissions.length; ++i) {
            if (checkSelfPermission(permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @SuppressLint("MissingPermission")
    @Override
    protected void onResume() {
        super.onResume();

        if (map != null) {
            if (checkPerms()) {
                map.setMyLocationEnabled(true);
            }
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    protected void onPause() {
        super.onPause();

        if(map != null) {
            if(checkPerms()) {
                map.setMyLocationEnabled(false);
            }
        }
    }

    @SuppressLint("MissingPermission")
    private void setup() {
        if (!checkPerms()) {
            return;
        }
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(googleMap -> {
            showToast("Ready maps");
            if (googleMap != null) {
                map = googleMap;


                map.moveCamera(CameraUpdateFactory.newLatLng(seoul));
            }
        });

        try {
            MapsInitializer.initialize(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            startLocationService();
        });

    }

    @SuppressLint("MissingPermission")
    private void startLocationService() {
        if (!checkPerms()) {
            return;
        }

        LocationManager maanger = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        Location location = maanger.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        Double latitude = location.getLatitude();
        Double longitude = location.getLongitude();
        showToast("Latitude: " + latitude + "\nLongitude: " + longitude);
        if (map != null) {
            MarkerOptions markers = new MarkerOptions();
            markers.title("Marker in Seoul");
            markers.position(seoul);
            markers.snippet("Snippet from GPS");
            markers.icon(BitmapDescriptorFactory.fromResource(R.drawable.mylocation));
            map.addMarker(markers);
            map.moveCamera(CameraUpdateFactory.newLatLng(seoul));
        }
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDenied(int i, @NotNull String[] permissions) {
//        showToast("Permissions denied: " + permissions.length);
    }

    @Override
    public void onGranted(int i, @NotNull String[] permissions) {
//        showToast("Permissions granted: " + permissions.length);
    }

}