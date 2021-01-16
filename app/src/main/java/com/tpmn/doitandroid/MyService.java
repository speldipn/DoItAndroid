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
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        Log.d("speldipn", "onCreate called");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.d("speldipn", "onDestroy called");
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent == null) {
            // Occur bad occasion, restart service by system.
            return Service.START_STICKY;
        } else {
            processCommand(intent);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void processCommand(Intent intent) {
        try {
            for(int i = 0; i < 5; ++i) {
                Log.d("speldipn", "Sleep 1sec..");
                Thread.sleep(1000);
            }
            Intent showIntent = new Intent(getApplicationContext(), MenuActivity.class);
            showIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            showIntent.putExtra("command", "show");
            showIntent.putExtra("name", "intent from service");
            startActivity(showIntent);
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            Log.d("speldipn", "End");
        }
    }
}