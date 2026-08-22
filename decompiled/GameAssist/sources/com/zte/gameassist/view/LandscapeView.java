package com.zte.gameassist.view;

import android.content.Context;
import android.graphics.Matrix;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/* loaded from: classes2.dex */
public class LandscapeView extends FrameLayout {
    private final Matrix mCanvasMatrix;
    private final Matrix mEventMatrix;
    private boolean mIsVertical;

    public LandscapeView(Context context) {
        super(context);
        this.mEventMatrix = new Matrix();
        this.mCanvasMatrix = new Matrix();
    }

    private MotionEvent a(MotionEvent motionEvent) {
        if (!this.mIsVertical) {
            return MotionEvent.obtain(motionEvent);
        }
        motionEvent.transform(this.mEventMatrix);
        return MotionEvent.obtain(motionEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        MotionEvent a2 = a(motionEvent);
        try {
            try {
                return super.dispatchTouchEvent(a2);
            } catch (Exception e2) {
                e2.printStackTrace();
                a2.recycle();
                return false;
            }
        } finally {
            a2.recycle();
        }
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        int width = getWidth();
        int height = getHeight();
        this.mEventMatrix.reset();
        this.mCanvasMatrix.reset();
        boolean z2 = height > width;
        this.mIsVertical = z2;
        if (z2) {
            float f2 = width / 2.0f;
            this.mEventMatrix.setRotate(-90.0f, f2, f2);
            this.mCanvasMatrix.setRotate(90.0f, f2, f2);
        }
        setAnimationMatrix(this.mCanvasMatrix);
    }
}
