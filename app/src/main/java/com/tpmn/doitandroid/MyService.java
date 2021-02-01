package com.tpmn.doitandroid;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("speldipn", "onBind called");
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        Log.d("speldipn", "MyService onCreate called");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.d("speldipn", "MyService onDestroy called");
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (intent == null) {
            // if exit failed, restart service by system
            return Service.START_STICKY;
        } else {
            processCommand(intent);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void processCommand(Intent intent) {
////        String msg = intent.getStringExtra(SampleYoutubeActivity.EXTRA_MSG);
////        if(!msg.isEmpty()) {
////            Intent intent1 = new Intent("doitandroid");
////            intent1.putExtra(SampleYoutubeActivity.EXTRA_MSG, msg);
////            sendBroadcast(intent1);
////            Log.d("speldipn", "Service to Broadcast");
////        }
    }
}