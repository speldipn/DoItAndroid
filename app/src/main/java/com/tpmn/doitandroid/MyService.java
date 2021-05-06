package com.tpmn.doitandroid;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        stopSelf();
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent == null) {
            return Service.START_STICKY;
        } else {
            String name = intent.getStringExtra("name");
            if (name != null) {
                Intent i = new Intent(this, ServiceActivity.class);
                i.setFlags(i.getFlags() | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("name", name);
                startActivity(i);
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }
}