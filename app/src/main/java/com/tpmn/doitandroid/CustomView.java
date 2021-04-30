package com.tpmn.doitandroid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class CustomView extends View {

    Context context;
    private float posX;
    private float posY;
    private Paint paint;

    public CustomView(Context context) {
        super(context);
        init(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        paint = new Paint();
        paint.setColor(Color.RED);
//        paint.setStyle(Paint.Style.STROKE);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(posX, posY, posX + 200, posY + 200, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            posX = event.getX();
            posY = event.getY();
            Toast.makeText(context, event.getX() + ", " + event.getY(), Toast.LENGTH_SHORT).show();
            invalidate();
        }
        return super.onTouchEvent(event);
    }
}
