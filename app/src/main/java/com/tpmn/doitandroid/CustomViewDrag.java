package com.tpmn.doitandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class CustomViewDrag extends View {

    final static String TAG = "CustomViewDrag";

    private final int WIDTH = 200;
    Paint paint;
    Bitmap cacheBitmap;
    Canvas cacheCanvas;
    Rect myRect;
    private boolean isMovable = false;

    public CustomViewDrag(Context context) {
        super(context);
        init();
    }

    public CustomViewDrag(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.RED);
        myRect = new Rect(0, 0, WIDTH, WIDTH);
        cacheCanvas = new Canvas();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        cacheBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        cacheCanvas.setBitmap(cacheBitmap);
        cacheCanvas.drawColor(Color.WHITE);
        cacheCanvas.drawRect(myRect, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (isSelectedMyRect(x, y)) {
                    isMovable = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                isMovable = false;
                break;
            case MotionEvent.ACTION_MOVE:
                if (isMovable) {
                    moveRect(x, y);
                }
                break;
            default:
                return false;
        }
        return true;
    }

    private void moveRect(float x, float y) {
        cacheCanvas.drawColor(Color.WHITE);
        myRect = new Rect((int) x - WIDTH / 2, (int) y - WIDTH / 2, (int) x + WIDTH / 2, (int) y + WIDTH / 2);
        cacheCanvas.drawRect(myRect, paint);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (cacheBitmap != null) {
            canvas.drawBitmap(cacheBitmap, 0, 0, paint);
        }
    }

    private boolean isSelectedMyRect(float x, float y) {
        return (x >= myRect.left && x <= myRect.right) &&
                (y >= myRect.top && y <= myRect.bottom);
    }
}