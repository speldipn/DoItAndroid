package com.tpmn.doitandroid.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.tpmn.doitandroid.R;

public class UserItem extends LinearLayout {

    TextView nameTextView;
    TextView phoneTextView;
    TextView locationTextView;

    public UserItem(Context context) {
        super(context);
        init(context);
    }

    public UserItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_item, this, true);
        nameTextView = view.findViewById(R.id.nameTextView);
        phoneTextView = view.findViewById(R.id.phoneTextView);
        locationTextView = view.findViewById(R.id.locationTextView);
    }

    public void setName(String name) { nameTextView.setText(name); }
    public void setPhone(String name) { phoneTextView.setText(name); }
    public void setLocation(String name) { locationTextView.setText(name); }
}

