package cn.nubia.multisubscreen.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import cn.nubia.gameassist.R;

/* loaded from: classes.dex */
public class SemicircleProgressView extends View {
    private static final int[] RING_COLORS = {10818619, -5958597};
    private static final int START_ANGLE = 180;
    private static final long TIME_ANIM = 1000;
    private static final int TOTAL_ANGLE = 180;
    private int mMax;
    private OnProgressChangedListener mOnProgressChangedListener;
    private Drawable mPointerDrawable;
    private int mProgress;
    private Paint mRingPaint;
    private RectF mRingRect;
    private SweepGradient mRingSweepGradient;
    private float[] mRingSweepPositions;
    private int mRingWidth;
    private float mRotationAngle;
    private ValueAnimator mValueAnimator;

    public interface OnProgressChangedListener {
        void a(int i2);
    }

    public SemicircleProgressView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mMax = 1;
        this.mRingSweepPositions = new float[2];
        this.mRotationAngle = -90.0f;
        e(attributeSet, 0, 0);
    }

    private void c(Canvas canvas) {
        if (this.mPointerDrawable != null) {
            canvas.save();
            canvas.rotate(this.mRotationAngle, getWidth() / 2, getHeight() / 2);
            this.mPointerDrawable.draw(canvas);
            canvas.restore();
        }
    }

    private void d(Canvas canvas) {
        canvas.save();
        canvas.rotate(180.0f, getWidth() / 2, getHeight() / 2);
        canvas.drawArc(this.mRingRect, 0.0f, (this.mProgress * 180) / this.mMax, false, this.mRingPaint);
        canvas.restore();
    }

    private void e(AttributeSet attributeSet, int i2, int i3) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.SemicircleProgressView, i2, i3);
        try {
            this.mPointerDrawable = obtainStyledAttributes.getDrawable(R.styleable.SemicircleProgressView_progress_pointer);
            int i4 = 0;
            this.mRingWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.SemicircleProgressView_ring_width, 0);
            obtainStyledAttributes.recycle();
            Paint paint = new Paint(1);
            this.mRingPaint = paint;
            paint.setStyle(Paint.Style.STROKE);
            this.mRingPaint.setStrokeWidth(this.mRingWidth);
            this.mRingRect = new RectF();
            while (true) {
                float[] fArr = this.mRingSweepPositions;
                if (i4 >= fArr.length) {
                    return;
                }
                fArr[i4] = 0.0f;
                i4++;
            }
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void f(ValueAnimator valueAnimator) {
        this.mProgress = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        g();
    }

    private void g() {
        this.mRotationAngle = ((this.mProgress * 180) / this.mMax) - 90;
        i(getWidth(), getHeight());
        invalidate();
        OnProgressChangedListener onProgressChangedListener = this.mOnProgressChangedListener;
        if (onProgressChangedListener != null) {
            onProgressChangedListener.a(this.mProgress);
        }
    }

    private void i(int i2, int i3) {
        if (i2 == 0 || i3 == 0) {
            return;
        }
        this.mRingSweepPositions[1] = (((this.mProgress * 1.0f) / this.mMax) * 180.0f) / 360.0f;
        SweepGradient sweepGradient = new SweepGradient(i2 / 2, i3 / 2, RING_COLORS, this.mRingSweepPositions);
        this.mRingSweepGradient = sweepGradient;
        this.mRingPaint.setShader(sweepGradient);
    }

    public void h(int i2, int i3, boolean z) {
        if (i2 < 0) {
            h(0, i3, z);
            return;
        }
        if (i3 == 0) {
            return;
        }
        if (i2 > i3) {
            i2 = i3;
        }
        if (i2 == this.mProgress && i3 == this.mMax) {
            return;
        }
        ValueAnimator valueAnimator = this.mValueAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        if (!z) {
            this.mProgress = i2;
            this.mMax = i3;
            g();
            return;
        }
        ValueAnimator ofInt = ValueAnimator.ofInt(this.mProgress, i2);
        this.mValueAnimator = ofInt;
        ofInt.setDuration(TIME_ANIM);
        this.mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: cn.nubia.multisubscreen.view.c
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                SemicircleProgressView.this.f(valueAnimator2);
            }
        });
        this.mValueAnimator.addListener(new AnimatorListenerAdapter() { // from class: cn.nubia.multisubscreen.view.SemicircleProgressView.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                SemicircleProgressView.this.mValueAnimator = null;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                SemicircleProgressView.this.mValueAnimator = null;
            }
        });
        this.mMax = i3;
        this.mValueAnimator.start();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        d(canvas);
        c(canvas);
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        if (getBackground() == null || getBackground().getIntrinsicWidth() <= 0) {
            super.onMeasure(i2, i3);
        } else {
            setMeasuredDimension(getBackground().getIntrinsicWidth(), getBackground().getIntrinsicHeight());
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        this.mPointerDrawable.setBounds(0, 0, i2, i3);
        i(i2, i3);
        float f2 = ((this.mRingWidth * 1.0f) / 2.0f) + 0.5f;
        this.mRingRect.set(f2, f2, i2 - f2, i3 - f2);
    }

    public void setOnProgressChangedListener(OnProgressChangedListener onProgressChangedListener) {
        this.mOnProgressChangedListener = onProgressChangedListener;
    }

    public SemicircleProgressView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mMax = 1;
        this.mRingSweepPositions = new float[2];
        this.mRotationAngle = -90.0f;
        e(attributeSet, i2, 0);
    }

    public SemicircleProgressView(Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.mMax = 1;
        this.mRingSweepPositions = new float[2];
        this.mRotationAngle = -90.0f;
        e(attributeSet, i2, i3);
    }
}
