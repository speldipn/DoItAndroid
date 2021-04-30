package com.tpmn.doitandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class ImageDisplayView extends View implements View.OnTouchListener {

    public static String TAG = "ImageDisplayView";
    public static float MAX_SCALE_RATIO = 5.0F;
    public static float MIN_SCALE_RATIO = 0.1F;
    public float startX;
    public float startY;
    Context context;
    Canvas canvas;
    Bitmap bitmap;
    Paint paint;
    int lastX;
    int lastY;
    Bitmap sourceBitmap;
    Matrix matrix;
    float sourceWidth = 0.0F;
    float sourceHeight = 0.0F;
    float bitmapCenterX;
    float bitmapCenterY;
    float scaleRatio;
    float totalScaleRatio;
    float displayWidth = 0.0F;
    float displayHeight = 0.0F;
    int displayCenterX;
    int displayCenterY;
    float oldDistance = 0.0F;

    int oldPointCount = 0;
    boolean isScrolling = false;
    float distanceThreshhold = 3.0F;

    public ImageDisplayView(Context context) {
        super(context);
        init(context);
    }

    public ImageDisplayView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        this.context = context;

        paint = new Paint();
        matrix = new Matrix();

        setOnTouchListener(this);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (w > 0 && h > 0) {
            newImage(w, h);
            redraw();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, 0, 0, null);
        }

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        final int action = event.getAction();

        int pointerCount = event.getPointerCount();
        Log.d(TAG, "pointerCount: " + pointerCount);

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (pointerCount == 1) {
                    float curX = event.getX();
                    float curY = event.getY();

                    startX = curX;
                    startY = curY;
                } else if (pointerCount == 2) {
                    oldDistance = 0.0F;
                    isScrolling = true;
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                if (pointerCount == 1) {
                    if (isScrolling) {
                        return true;
                    }

                    float curX = event.getX();
                    float curY = event.getY();

                    if (startX == 0.0F) {
                        startX = curX;
                        startY = curY;

                        return true;
                    }
                } else if (pointerCount == 2) {

                }
                return true;
            case MotionEvent.ACTION_UP:
                return true;
        }

        return false;
    }

    private void redraw() {
    }

    private void newImage(int w, int h) {
    }
}
