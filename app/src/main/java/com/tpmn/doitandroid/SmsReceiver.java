package com.tpmn.doitandroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.util.Date;

public class SmsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("speldipn", "onReceived()");

        Bundle bundle = intent.getExtras();
        SmsMessage[] messages = parseSmsMessage(bundle);

        if (messages != null) {
            for (SmsMessage message : messages) {
                String sender = message.getOriginatingAddress();
                Log.d("speldipn", "SMS sender: " + sender);

                String contents = message.getMessageBody();
                Log.d("speldipn", "SMS contents: " + contents);

                Date receivedDate = new Date(message.getTimestampMillis());
                Log.d("speldipn", "SMS received date: " + receivedDate);
            }
        }
    }

    private SmsMessage[] parseSmsMessage(Bundle bundle) {
        Object[] objs = (Object[]) bundle.get("pdus");
        if (objs == null) {
            return null;
        }

        SmsMessage[] smsMessages = new SmsMessage[objs.length];
        int smsCount = smsMessages.length;
        for (int i = 0; i < smsCount; ++i) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                String format = bundle.getString("format");
                smsMessages[i] = SmsMessage.createFromPdu((byte[]) objs[i], format);
            } else {
                smsMessages[i] = SmsMessage.createFromPdu((byte[]) objs[i]);
            }
        }

        return smsMessages;
    }
}