package com.tpmn.doitandroid;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class Layout1 extends LinearLayout {
    ImageView imageView;
    TextView nameTextView;
    TextView descTextView;

    public Layout1(Context context) {
        super(context);
        init(context);
    }

    public Layout1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout1, this, true);
        imageView = view.findViewById(R.id.imageView);
        nameTextView = view.findViewById(R.id.nameTextView);
        descTextView = view.findViewById(R.id.descTextView);
    }

    void setImageView(int redId) { imageView.setImageResource(redId); }

    void setNameTextView(String name) { nameTextView.setText(name); }

    void setDescTextView(String desc) { descTextView.setText(desc); }
}
