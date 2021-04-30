package com.tpmn.doitandroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.telephony.SmsMessage;

import java.util.ArrayList;
import java.util.Date;

public class SmsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        ArrayList<CustomSMS> smsList = new ArrayList<>();
        Bundle bundle = intent.getExtras();
        SmsMessage[] messages = parseSmsMessage(bundle);
        if (messages != null) {
            for (SmsMessage message : messages) {
                String sender = message.getOriginatingAddress();
                String contents = message.getMessageBody();
                Date receivedDate = new Date(message.getTimestampMillis());
                smsList.add(new CustomSMS(sender, contents, receivedDate.toString()));
            }
        }
        Intent smsIntent = new Intent(context, SmsActivity.class);
        smsIntent.putExtra("sms", smsList);
        context.startActivity(smsIntent);
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

class CustomSMS implements Parcelable {

    public static final Creator<CustomSMS> CREATOR = new Creator<CustomSMS>() {
        @Override
        public CustomSMS createFromParcel(Parcel in) {
            return new CustomSMS(in);
        }

        @Override
        public CustomSMS[] newArray(int size) {
            return new CustomSMS[size];
        }
    };
    String phone;
    String message;
    String date;

    protected CustomSMS(String phone, String message, String date) {
        this.phone = phone;
        this.message = message;
        this.date = date;
    }

    protected CustomSMS(Parcel in) {
        this.phone = in.readString();
        this.message = in.readString();
        this.date = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(phone);
        dest.writeString(message);
        dest.writeString(date);
    }

}