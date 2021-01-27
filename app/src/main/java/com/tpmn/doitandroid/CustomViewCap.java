package com.tpmn.doitandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class CustomViewCap extends View {

    Paint paint;
    Bitmap cacheBitmap;
    Canvas cacheCanvas;

    public CustomViewCap(Context context) {
        super(context);
        init();
    }

    public CustomViewCap(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setStrokeWidth(10F);
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        cacheCanvas = new Canvas();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        cacheBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        cacheCanvas.setBitmap(cacheBitmap);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN: break;
            case MotionEvent.ACTION_UP: break;
            case MotionEvent.ACTION_MOVE:
                cacheCanvas.drawPoint(x, y, paint);
                break;
            default:
                return false;
        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(cacheBitmap != null) {
            canvas.drawBitmap(cacheBitmap, 0, 0, paint);
        }
    }

    public void setCap(Paint.Cap cap) {
        paint.setStrokeCap(cap);
    }

}
