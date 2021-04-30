package com.tpmn.doitandroid;

import android.os.Parcel;
import android.os.Parcelable;

class SimpleData implements Parcelable {

    public static final Parcelable.Creator CREATOR = new Creator<SimpleData>() {
        @Override
        public SimpleData createFromParcel(Parcel source) {
            return new SimpleData(source.readInt(), source.readString());
        }

        @Override
        public SimpleData[] newArray(int size) {
            return new SimpleData[size];
        }
    };
    int number;
    String message;

    public SimpleData(int num, String msg) {
        number = num;
        message = msg;
    }

    public SimpleData(Parcel parcel) {
        number = parcel.readInt();
        message = parcel.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(number);
        dest.writeString(message);
    }

}