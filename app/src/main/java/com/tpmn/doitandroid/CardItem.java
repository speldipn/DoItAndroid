package com.tpmn.doitandroid;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.tpmn.doitandroid.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CardItem extends LinearLayout {

    ImageView cardThumbImageView;
    TextView cardDateTextView;
    ImageView card2ThumbImageView;
    TextView card2DateTextView;
    Context context;

    public CardItem(Context context) {
        super(context);
        init(context);
    }

    public CardItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.user_item, this, true);
        cardThumbImageView = view.findViewById(R.id.cardThumbImageView);
        cardDateTextView = view.findViewById(R.id.cardDateTextView);
        card2ThumbImageView = view.findViewById(R.id.card2ThumbImageView);
        card2DateTextView = view.findViewById(R.id.card2DateTextView);
    }

    public void setPhoto(String imagePath, String date) {
        cardThumbImageView.setImageBitmap(BitmapFactory.decodeFile(imagePath));
        Date d = new Date(Long.parseLong(date));
        SimpleDateFormat sdf = new SimpleDateFormat("Y-M-d");
        cardDateTextView.setText(sdf.format(d));
    }

    public void setPhoto2(String imagePath, String date) {
        card2ThumbImageView.setImageBitmap(BitmapFactory.decodeFile(imagePath));
        Date d = new Date(Long.parseLong(date));
        SimpleDateFormat sdf = new SimpleDateFormat("Y-M-d");
        card2DateTextView.setText(sdf.format(d));
    }
}

