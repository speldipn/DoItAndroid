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
        // TODO: Return the communication channel to the service.
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
        if(intent == null) {
            // if exit failed, restart service by system
            return Service.START_STICKY;
        } else {
           processCommand(intent);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void processCommand(Intent intent) {
        String command = intent.getStringExtra("command");
        String name = intent.getStringExtra("name");
        Log.d("speldipn", command + " " + name);

        try {
            for(int i = 0; i < 5; ++i) {
                Log.d("speldipn", "Sleep 1sec..");
                Thread.sleep(1000);
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            Log.d("speldipn", "processCommand end.");
        }
    }
}