package cn.nubia.multisubscreen.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.view.YouSheTextView;
import cn.nubia.multisubscreen.utils.MultiSubScreenUtils;
import java.util.Locale;

/* loaded from: classes.dex */
public class CpuGpuView extends View {
    private static final int START_ANGLE = 23;
    private static final long TIME_ANIM = 1000;
    private boolean mIsCpu;
    private float mMax;
    private Paint mOuterArcPaint;
    private float mOuterArcRadius;
    private SweepGradient mOuterArcSweepGradient;
    private Drawable mPointerDrawable;
    private float mProgress;
    private RectF mRectF;
    private Paint mRemainArcPaint;
    private Paint mRingPaint;
    private float mRingRadius;
    private SweepGradient mRingSweepGradient;
    private float mSweepAngle;
    private float[] mSweepPositions;
    private float mTargetProgress;
    private int mTextMargin;
    private Paint mTextPaint;
    private Rect mTextRect;
    private ValueAnimator mValueAnimator;
    private static final int[] RING_COLORS = {10818616, -1717234632};
    private static final int[] OUTER_ARC_COLORS = {16777215, -1711276033};

    public CpuGpuView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void c(Canvas canvas) {
        if (this.mPointerDrawable != null) {
            canvas.save();
            canvas.rotate((this.mSweepAngle + 23.0f) - 180.0f, getWidth() / 2, getHeight() / 2);
            this.mPointerDrawable.draw(canvas);
            canvas.restore();
        }
    }

    private void d(Canvas canvas) {
        String format;
        canvas.save();
        canvas.rotate(113.0f, getWidth() / 2, getHeight() / 2);
        this.mRectF.set((getWidth() / 2) - this.mRingRadius, (getHeight() / 2) - this.mRingRadius, (getWidth() / 2) + this.mRingRadius, (getHeight() / 2) + this.mRingRadius);
        canvas.drawArc(this.mRectF, 0.0f, this.mSweepAngle, false, this.mRingPaint);
        this.mRectF.set((getWidth() / 2) - this.mOuterArcRadius, (getHeight() / 2) - this.mOuterArcRadius, (getWidth() / 2) + this.mOuterArcRadius, (getHeight() / 2) + this.mOuterArcRadius);
        canvas.drawArc(this.mRectF, 0.0f, 314.0f, false, this.mRemainArcPaint);
        canvas.drawArc(this.mRectF, 0.0f, this.mSweepAngle, false, this.mOuterArcPaint);
        canvas.restore();
        if (MultiSubScreenUtils.f8183m) {
            format = "--";
        } else {
            format = String.format(Locale.ENGLISH, this.mIsCpu ? "%.2f" : "%.0f", Float.valueOf(this.mProgress / 1000000.0f));
        }
        this.mTextPaint.getTextBounds(format, 0, format.length(), this.mTextRect);
        canvas.drawText(format, getWidth() / 2, ((getHeight() / 2) - this.mTextRect.bottom) - this.mTextMargin, this.mTextPaint);
    }

    private float e(float f2, float f3) {
        return (f2 / f3) * 314.0f;
    }

    private void f(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.CpuGpuView);
        try {
            int i2 = 0;
            this.mIsCpu = obtainStyledAttributes.getBoolean(R.styleable.CpuGpuView_cpu, false);
            int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.CpuGpuView_radius_progress_inner, 0);
            int dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.CpuGpuView_radius_progress_outer, 0);
            int dimensionPixelSize3 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.CpuGpuView_width_outer_arc, 0);
            Paint paint = new Paint(1);
            this.mRingPaint = paint;
            Paint.Style style = Paint.Style.STROKE;
            paint.setStyle(style);
            this.mRingRadius = ((dimensionPixelSize + dimensionPixelSize2) * 1.0f) / 2.0f;
            this.mRingPaint.setStrokeWidth(dimensionPixelSize2 - dimensionPixelSize);
            float f2 = dimensionPixelSize2;
            float f3 = dimensionPixelSize3;
            this.mOuterArcRadius = f2 - ((1.0f * f3) / 2.0f);
            Paint paint2 = new Paint(1);
            this.mOuterArcPaint = paint2;
            paint2.setStyle(style);
            this.mOuterArcPaint.setStrokeWidth(f3);
            Paint paint3 = new Paint(1);
            this.mRemainArcPaint = paint3;
            paint3.setStyle(style);
            this.mRemainArcPaint.setStrokeWidth(f3);
            this.mRemainArcPaint.setColor(-1195501774);
            int dimensionPixelSize4 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.CpuGpuView_center_text_size, 0);
            Paint paint4 = new Paint();
            this.mTextPaint = paint4;
            paint4.setTypeface(YouSheTextView.getYouSheHei());
            this.mTextPaint.setColor(-1);
            this.mTextPaint.setTextSize(dimensionPixelSize4);
            this.mTextPaint.setTextAlign(Paint.Align.CENTER);
            this.mTextMargin = obtainStyledAttributes.getDimensionPixelSize(R.styleable.CpuGpuView_center_text_margin, 0);
            this.mPointerDrawable = obtainStyledAttributes.getDrawable(R.styleable.CpuGpuView_pointer_drawable);
            obtainStyledAttributes.recycle();
            this.mRectF = new RectF();
            this.mTextRect = new Rect();
            while (true) {
                float[] fArr = this.mSweepPositions;
                if (i2 >= fArr.length) {
                    return;
                }
                fArr[i2] = 0.0f;
                i2++;
            }
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    private boolean g(float f2, float f3) {
        float f4 = f2 - f3;
        return f4 < 0.1f && f4 > -0.1f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void h(ValueAnimator valueAnimator) {
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        this.mProgress = floatValue;
        this.mSweepAngle = e(floatValue, this.mMax);
        i(getWidth(), getHeight(), this.mSweepAngle);
        invalidate();
    }

    private void i(int i2, int i3, float f2) {
        if (i2 == 0 || i3 == 0) {
            return;
        }
        this.mSweepPositions[1] = f2 / 360.0f;
        float f3 = i2 / 2;
        float f4 = i3 / 2;
        SweepGradient sweepGradient = new SweepGradient(f3, f4, RING_COLORS, this.mSweepPositions);
        this.mRingSweepGradient = sweepGradient;
        this.mRingPaint.setShader(sweepGradient);
        SweepGradient sweepGradient2 = new SweepGradient(f3, f4, OUTER_ARC_COLORS, this.mSweepPositions);
        this.mOuterArcSweepGradient = sweepGradient2;
        this.mOuterArcPaint.setShader(sweepGradient2);
    }

    public void j(float f2, float f3, boolean z) {
        if (f2 < -0.1f) {
            j(0.0f, f3, z);
            return;
        }
        if (f3 < 0.1f) {
            return;
        }
        if (g(f2, this.mTargetProgress) && g(f3, this.mMax)) {
            return;
        }
        ValueAnimator valueAnimator = this.mValueAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        this.mTargetProgress = f2;
        this.mMax = f3;
        if (!z) {
            this.mProgress = f2;
            this.mSweepAngle = e(f2, f3);
            i(getWidth(), getHeight(), this.mSweepAngle);
            invalidate();
            return;
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(this.mProgress, f2);
        this.mValueAnimator = ofFloat;
        ofFloat.setDuration(TIME_ANIM);
        this.mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: cn.nubia.multisubscreen.view.a
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                CpuGpuView.this.h(valueAnimator2);
            }
        });
        this.mValueAnimator.addListener(new Animator.AnimatorListener() { // from class: cn.nubia.multisubscreen.view.CpuGpuView.1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                CpuGpuView.this.mValueAnimator = null;
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                CpuGpuView.this.mValueAnimator = null;
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }
        });
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
        Drawable drawable = this.mPointerDrawable;
        if (drawable != null) {
            drawable.setBounds((getWidth() - this.mPointerDrawable.getIntrinsicWidth()) / 2, (getHeight() - this.mPointerDrawable.getIntrinsicHeight()) / 2, (getWidth() + this.mPointerDrawable.getIntrinsicWidth()) / 2, (getHeight() + this.mPointerDrawable.getIntrinsicHeight()) / 2);
        }
        i(i2, i3, e(this.mProgress, this.mMax));
    }

    public CpuGpuView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mMax = 1.0f;
        this.mSweepPositions = new float[2];
        f(attributeSet);
    }
}
