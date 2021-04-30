package com.tpmn.doitandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class CustomViewDrawing extends View {

    Paint paint;
    Bitmap cacheBitmap;
    Canvas cacheCanvas;

    public CustomViewDrawing(Context context) {
        super(context);
        init();
    }

    public CustomViewDrawing(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(10.0F);

        Display display = getContext().getDisplay();
        cacheBitmap = Bitmap.createBitmap(display.getWidth(), display.getHeight(), Bitmap.Config.ARGB_8888);
        cacheCanvas = new Canvas(cacheBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (cacheBitmap != null) {
            canvas.drawBitmap(cacheBitmap, 0, 0, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            float x = event.getX();
            float y = event.getY();
            cacheCanvas.drawPoint(x, y, paint);
            invalidate();
        }

        return true;
    }
}
