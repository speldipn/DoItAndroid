package com.tpmn.doitandroid;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

//public class MyFirebaseMessagingService extends FirebaseMessagingService {
//
//    private static final String TAG = "FMS";
//
//    public MyFirebaseMessagingService() { }
//
//    @Override
//    public void onNewToken(@NonNull String token) {
//        super.onNewToken(token);
//        Log.d(TAG, "onNewToken called: " + token);
//    }
//
//    @Override
//    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
//        super.onMessageReceived(remoteMessage);
//        Log.d(TAG, "onMessageReceived called.");
//    }
//}
//
