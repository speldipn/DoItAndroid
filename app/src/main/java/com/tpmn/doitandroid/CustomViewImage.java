package com.tpmn.doitandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

public class CustomViewImage extends View {

    private Context context;
    private Paint paint;
    private Bitmap cacheBitmap;
    private Canvas cacheCanvas;

    public CustomViewImage(Context context) {
        super(context);
        init(context);
    }

    public CustomViewImage(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        paint = new Paint();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        createCacheBitmap(w, h);
        testDraw();
    }

    private void createCacheBitmap(int width, int height) {
       cacheBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
       cacheCanvas = new Canvas();
       cacheCanvas.setBitmap(cacheBitmap);
    }

    private void testDraw() {
        cacheCanvas.drawColor(Color.WHITE);


        paint.setColor(Color.RED);
        cacheCanvas.drawRect(100, 100, 200, 200, paint);

        Bitmap srcImg = BitmapFactory.decodeResource(getResources(), R.drawable.waterdrop);
        cacheCanvas.drawBitmap(srcImg, 30, 30, paint);

        Matrix horInverseMatrix = new Matrix();
        horInverseMatrix.setScale(-1, 1);
        Bitmap horInverseImg = Bitmap.createBitmap(srcImg, 0, 0,
                srcImg.getWidth(), srcImg.getHeight(), horInverseMatrix, false);
        cacheCanvas.drawBitmap(horInverseImg, 30, 130, paint);

        Matrix verInverseMatrix = new Matrix();
        verInverseMatrix.setScale(1, -1);
        Bitmap verInverseBitmap = Bitmap.createBitmap(srcImg, 0, 0,
                srcImg.getWidth(), srcImg.getHeight(), verInverseMatrix, false);
        cacheCanvas.drawBitmap(verInverseBitmap, 30, 230, paint);

        paint.setMaskFilter(new BlurMaskFilter(10, BlurMaskFilter.Blur.NORMAL));
        Bitmap scaledImg = Bitmap.createScaledBitmap(srcImg, srcImg.getWidth() * 3, srcImg.getHeight() * 3, false);
        cacheCanvas.drawBitmap(scaledImg, 30, 330, paint);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(cacheBitmap != null) {
            canvas.drawBitmap(cacheBitmap, 0, 0, null);
        }
    }
}
