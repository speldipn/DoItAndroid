package com.tpmn.doitandroid;

import android.content.Context;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class CameraCustomView extends SurfaceView {
    public CameraCustomView(Context context) {
        super(context);
        init();
    }

    public CameraCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {

    }

    public void capture(Camera.PictureCallback callback) {
    }
}
