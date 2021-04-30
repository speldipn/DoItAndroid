package com.tpmn.doitandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class CustomViewDrawingImproved extends View {

    static final float TOUCH_TOLERANCE = 8;
    Canvas canvas;
    Bitmap bitmap;
    Paint paint;
    float lastX;
    float lastY;
    Path path = new Path();
    float curveEndX;
    float curveEndY;
    int invalidateExtraBorder = 10;

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
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(3.0F);

        lastX = -1;
        lastY = -1;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Bitmap img = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(img);
        canvas.drawColor(Color.WHITE);

        this.bitmap = img;
        this.canvas = canvas;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, 0, 0, null);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                Rect rect = touchUp(event);
                invalidate(rect);
                path.rewind();
                return true;

            case MotionEvent.ACTION_DOWN:
                rect = touchDown(event);
                invalidate(rect);
                return true;

            case MotionEvent.ACTION_MOVE:
                rect = touchMove(event);
                invalidate(rect);
                return true;
        }

        return false;
    }

    private Rect touchDown(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        lastX = x;
        lastY = y;

        Rect invalidRect = new Rect();
        path.moveTo(x, y);

        final int border = invalidateExtraBorder;
        invalidRect.set((int) x - border, (int) y - border, (int) x + border, (int) y + border);
        curveEndX = x;
        curveEndY = y;

        canvas.drawPath(path, paint);

        return invalidRect;
    }

    private Rect touchUp(MotionEvent event) {
        return processMove(event);
    }

    private Rect touchMove(MotionEvent event) {
        return processMove(event);
    }

    private Rect processMove(MotionEvent event) {
        final float x = event.getX();
        final float y = event.getY();

        final float dx = Math.abs(x - lastX);
        final float dy = Math.abs(y - lastY);

        Rect invalidRect = new Rect();
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            final int border = invalidateExtraBorder;
            invalidRect.set((int) curveEndX - border, (int) curveEndY - border,
                    (int) curveEndX + border, (int) curveEndY + border);

            float cX = curveEndX = (x + lastX) / 2;
            float cY = curveEndY = (y + lastY) / 2;

            path.quadTo(lastX, lastY, cX, cY);

            invalidRect.union((int) lastX - border, (int) lastY - border,
                    (int) lastX + border, (int) lastY + border);

            invalidRect.union((int) cX - border, (int) cY - border,
                    (int) cX + border, (int) cY + border);

            lastX = x;
            lastY = y;

            canvas.drawPath(path, paint);
        }

        return invalidRect;
    }

}
