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
<<<<<<< HEAD
        Log.d("speldipn", "onBind called");
=======
>>>>>>> f7cf400c2aa3bb0b5dfeb80ae50fc1a202085332
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
<<<<<<< HEAD
        Log.d("speldipn", "onCreate called");
=======
        Log.d("speldipn", "MyService onCreate called");
>>>>>>> f7cf400c2aa3bb0b5dfeb80ae50fc1a202085332
        super.onCreate();
    }

    @Override
    public void onDestroy() {
<<<<<<< HEAD
        Log.d("speldipn", "onDestroy called");
=======
        Log.d("speldipn", "MyService onDestroy called");
>>>>>>> f7cf400c2aa3bb0b5dfeb80ae50fc1a202085332
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
<<<<<<< HEAD
        if (intent == null) {
            // Occur bad occasion, restart service by system.
            return Service.START_STICKY;
        } else {
            processCommand(intent);
=======
        if(intent == null) {
            // if exit failed, restart service by system
            return Service.START_STICKY;
        } else {
           processCommand(intent);
>>>>>>> f7cf400c2aa3bb0b5dfeb80ae50fc1a202085332
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void processCommand(Intent intent) {
<<<<<<< HEAD
=======
        String command = intent.getStringExtra("command");
        String name = intent.getStringExtra("name");
        Log.d("speldipn", command + " " + name);

>>>>>>> f7cf400c2aa3bb0b5dfeb80ae50fc1a202085332
        try {
            for(int i = 0; i < 5; ++i) {
                Log.d("speldipn", "Sleep 1sec..");
                Thread.sleep(1000);
            }
<<<<<<< HEAD
            Intent showIntent = new Intent(getApplicationContext(), MenuActivity.class);
            showIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            showIntent.putExtra("command", "show");
            showIntent.putExtra("name", "intent from service");
            startActivity(showIntent);
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            Log.d("speldipn", "End");
=======
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            Log.d("speldipn", "processCommand end.");
>>>>>>> f7cf400c2aa3bb0b5dfeb80ae50fc1a202085332
        }
    }
}