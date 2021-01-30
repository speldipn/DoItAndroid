package com.tpmn.doitandroid;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends YouTubeBaseActivity implements AutoPermissionsListener {

    public static final String TAG = "speldipn";
    public static final String EXTRA_MSG = "EXTRA_MSG";

    RecyclerView recyclerView;
    MyAlbumAdapter adapter;
    ContentResolver resolver;

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
            getAlbumFromStorage();
        }
    }

    private void setup() {
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new MyAlbumAdapter();
        recyclerView.setAdapter(adapter);
        resolver = getContentResolver();
        if(checkPermission()) {
            getAlbumFromStorage();
        } else {
            showToast("permission denied");
        }
    }

    private boolean checkPermission() {
        String[] permissions = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        if (checkSelfPermission(permissions[0]) == PackageManager.PERMISSION_GRANTED &&
                checkSelfPermission(permissions[1]) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }

    private List<AlbumModel> list = new ArrayList<>();

    private void getAlbumFromStorage() {
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        Log.d(TAG, "image uri: " + uri);
        String[] projects = {
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.DATE_TAKEN,
        };
        String order = projects[2] + " DESC";
        Cursor cursor = resolver.query(uri, projects, null, null, order);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                AlbumModel model = new AlbumModel();
                model.thumbUri = Uri.parse(cursor.getString(cursor.getColumnIndex(projects[1])));
                model.name = cursor.getString(cursor.getColumnIndex(projects[2]));
                model.time = cursor.getLong(cursor.getColumnIndex(projects[3]));
                list.add(model);
            } while (cursor.moveToNext());
            cursor.close();

            adapter.setData(list);
        }
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
