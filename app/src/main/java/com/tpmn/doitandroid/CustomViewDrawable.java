package com.tpmn.doitandroid;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.View;

import androidx.annotation.Nullable;

public class CustomViewDrawable extends View {

    Context context;

    ShapeDrawable upperDrawable;
    ShapeDrawable lowerDrawable;

    public CustomViewDrawable(Context context) {
        super(context);
        init(context);
    }

    public CustomViewDrawable(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        this.context = context;

        Display display = context.getDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
        Log.d("speldipn", "width: " + width + ", height: " + height);

        Resources res = getResources();
        int redColor = res.getColor(R.color.color01);
        int greenColor = res.getColor(R.color.color02);
        int blueColor = res.getColor(R.color.color03);

        upperDrawable = new ShapeDrawable();

        RectShape rectangle = new RectShape();
        rectangle.resize(width, height * 2 / 3);
        upperDrawable.setShape(rectangle);

        upperDrawable.setBounds(0, 0, width, height * 2 / 3);

        LinearGradient gradient = new LinearGradient(0, 0, 0, height * 2 / 3,
                greenColor, redColor, Shader.TileMode.CLAMP);

        Paint paint = upperDrawable.getPaint();

        paint.setShader(gradient);

        lowerDrawable = new ShapeDrawable();

        RectShape rectangle2 = new RectShape();
        rectangle2.resize(width, height * 1 / 3);
        lowerDrawable.setShape(rectangle2);

        lowerDrawable.setBounds(0, height * 2 / 3, width, height);

        LinearGradient gradient2 = new LinearGradient(0, 0, 0, height * 1 / 3,
                redColor, blueColor, Shader.TileMode.CLAMP);

        Paint paint2 = lowerDrawable.getPaint();
        paint2.setShader(gradient2);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        upperDrawable.draw(canvas);
        lowerDrawable.draw(canvas);
    }
}
