package cn.nubia.gameassist.common;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes.dex */
public abstract class BasePerformanceView extends View {
    private static final String TAG = "BasePerformanceView";
    public boolean mIsPerformaceButtonClick;
    public int mPerformanceIndex;
    public int mPerformancePrevIndex;
    private ValueAnimator mValueAnimator;

    public BasePerformanceView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void g() {
        ValueAnimator ofInt = ValueAnimator.ofInt(0, 10);
        this.mValueAnimator = ofInt;
        ofInt.setDuration(1000L);
        this.mValueAnimator.setInterpolator(new LinearInterpolator());
        this.mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: cn.nubia.gameassist.common.BasePerformanceView.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                BasePerformanceView.this.d(((Integer) valueAnimator.getAnimatedValue()).intValue() / 10.0d);
            }
        });
        this.mValueAnimator.addListener(new AnimatorListenerAdapter() { // from class: cn.nubia.gameassist.common.BasePerformanceView.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                BasePerformanceView.this.c();
            }
        });
        if (this.mValueAnimator.isRunning()) {
            this.mValueAnimator.cancel();
        }
        this.mValueAnimator.start();
    }

    public Bitmap a(int i2, Bitmap bitmap, float f2, float f3) {
        Bitmap decodeResource = BitmapFactory.decodeResource(getResources(), i2);
        Matrix matrix = new Matrix();
        matrix.postScale(f2 / decodeResource.getWidth(), f3 / decodeResource.getHeight());
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public abstract void b();

    public abstract void c();

    public abstract void d(double d2);

    public void e(int i2, boolean z) {
        GaLog.a(TAG, "setPerformanceIndex," + i2 + ", mPerformanceIndex = " + this.mPerformanceIndex + " , isPerformaceButtonClick = " + z);
        if (this.mPerformanceIndex != i2) {
            this.mPerformanceIndex = i2;
            this.mIsPerformaceButtonClick = z;
            i();
            if (z) {
                g();
            } else {
                postInvalidate();
            }
            this.mPerformancePrevIndex = i2;
        }
    }

    public void f() {
        this.mIsPerformaceButtonClick = false;
        b();
        i();
    }

    public void h() {
        this.mIsPerformaceButtonClick = false;
        ValueAnimator valueAnimator = this.mValueAnimator;
        if (valueAnimator == null || !valueAnimator.isRunning()) {
            return;
        }
        this.mValueAnimator.cancel();
        this.mValueAnimator = null;
    }

    public abstract void i();

    public void setViewSize(boolean z) {
    }

    public BasePerformanceView(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public BasePerformanceView(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.mPerformanceIndex = -1;
        this.mPerformancePrevIndex = -1;
        b();
    }
}
