package com.tpmn.doitandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class CustomViewDrawingImproved extends View {

    Paint paint;
    Bitmap cacheBitmap;
    Canvas cacheCanvas;
    Path path;
    Point prevPos;

    public CustomViewDrawingImproved(Context context) {
        super(context);
        init();
    }

    public CustomViewDrawingImproved(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.RED);
        path = new Path();
        cacheCanvas = new Canvas();
        prevPos = new Point(0, 0);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        cacheBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        cacheCanvas.setBitmap(cacheBitmap);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(event.getX(), event.getY());
                prevPos.x = (int)event.getX();
                prevPos.y = (int)event.getY();
                cacheCanvas.drawPath(path, paint);
                break;
            case MotionEvent.ACTION_MOVE:
                path.moveTo(prevPos.x, prevPos.y);
                prevPos.x = (int)event.getX();
                prevPos.y = (int)event.getY();
                cacheCanvas.drawPath(path, paint);

                path.lineTo(event.getX(), event.getY());
                cacheCanvas.drawPath(path, paint);
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                return false;
        }

        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(cacheBitmap != null) {
            canvas.drawBitmap(cacheBitmap, 0, 0, paint);
        }
    }

}
