package com.tpmn.doitandroid;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class MyFirebasemessagingService extends Service {

    private static final String TAG = "FMS";

    public MyFirebasemessagingService() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}