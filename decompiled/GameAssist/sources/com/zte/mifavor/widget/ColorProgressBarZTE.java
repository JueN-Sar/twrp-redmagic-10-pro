package com.zte.mifavor.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import com.zte.extres.R;
import java.math.BigDecimal;

/* loaded from: classes2.dex */
public class ColorProgressBarZTE extends View {
    private boolean DEBUG;
    private final String TAG;
    private float dx;
    private boolean isThumbOnDragging;
    private boolean isTouchToSeek;
    private int mBottomLeftRadius;
    private int mBottomRightRadius;
    private int[] mColorsProgress;
    private float mDelta;
    private boolean mIsEnable;
    private int mLeftPadding;
    private int mLength;
    private float mMax;
    private float mMin;

    @Nullable
    private OnProgressChangeListener mOnProgressChangeListener;
    private Paint mPaint;

    @Nullable
    private Path mPath;
    private int mPreProgress;
    private float mProgress;
    private float[] mRadii;
    private int mRightPadding;
    private float mSBLeft;
    private float mSBRight;
    private float mThumbCenterX;
    private int mTopLeftRadius;
    private int mTopPadding;
    private int mTopRightRadius;
    private int mTrackBgColor;
    private float mTrackLength;
    private int mTrackSize;
    private int mViewHeight;
    private int mViewWidth;

    public interface OnProgressChangeListener {
        void a(int i2);

        void b();
    }

    public ColorProgressBarZTE(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.colorProgressBarZTEStyle);
    }

    private Bitmap a(int[] iArr, int i2, int i3) {
        if (i2 <= 0) {
            Log.e("ColorProgressBarZTE", "build Bitmap width error, width = " + i2);
            i2 = d(2);
        }
        if (i3 <= 0) {
            Log.e("ColorProgressBarZTE", "build Bitmap height error, height = " + i3);
            i3 = d(2);
        }
        try {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setShape(0);
            gradientDrawable.setColors(iArr);
            gradientDrawable.setGradientType(0);
            gradientDrawable.setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);
            gradientDrawable.setGradientRadius(this.mTrackSize / 2);
            gradientDrawable.setCornerRadii(this.mRadii);
            gradientDrawable.setBounds(0, 0, i2, i3);
            Bitmap createBitmap = Bitmap.createBitmap(i2, i3, gradientDrawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
            gradientDrawable.draw(new Canvas(createBitmap));
            return createBitmap;
        } catch (Exception e2) {
            Log.e("ColorProgressBarZTE", "build Bitmap error, e = ", e2);
            return Bitmap.createBitmap(d(2), d(2), Bitmap.Config.RGB_565);
        }
    }

    private float b() {
        return (((this.mThumbCenterX - this.mSBLeft) * this.mDelta) / this.mTrackLength) + this.mMin;
    }

    private synchronized void c(int i2) {
        try {
            OnProgressChangeListener onProgressChangeListener = this.mOnProgressChangeListener;
            if (onProgressChangeListener != null) {
                onProgressChangeListener.a(i2);
                if (i2 >= this.mMax) {
                    this.mOnProgressChangeListener.b();
                }
            } else if (this.DEBUG) {
                Log.w("ColorProgressBarZTE", "doProgressRefresh error, doProgressRefresh is null.");
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    private int d(int i2) {
        return (int) TypedValue.applyDimension(1, i2, Resources.getSystem().getDisplayMetrics());
    }

    private float e(float f2) {
        try {
            return BigDecimal.valueOf(f2).setScale(1, 4).floatValue();
        } catch (Exception e2) {
            Log.e("ColorProgressBarZTE", "format Float error, e = " + e2);
            return 0.0f;
        }
    }

    private void f() {
        if (Math.abs(this.mMax - this.mMin) < 0.001d) {
            this.mMin = 0.0f;
            this.mMax = 100.0f;
        }
        float f2 = this.mMin;
        float f3 = this.mMax;
        if (f2 > f3) {
            this.mMax = f2;
            this.mMin = f3;
        }
        float f4 = this.mProgress;
        float f5 = this.mMin;
        if (f4 < f5) {
            this.mProgress = f5;
        }
        float f6 = this.mProgress;
        float f7 = this.mMax;
        if (f6 > f7) {
            this.mProgress = f7;
        }
        this.mPreProgress = Math.round(this.mProgress);
        this.mDelta = this.mMax - this.mMin;
    }

    private boolean g(MotionEvent motionEvent) {
        if (!isEnabled()) {
            return false;
        }
        float f2 = this.mSBLeft + ((this.mTrackLength / this.mDelta) * (this.mProgress - this.mMin));
        float measuredHeight = getMeasuredHeight() / 2.0f;
        return ((motionEvent.getX() - f2) * (motionEvent.getX() - f2)) + ((motionEvent.getY() - measuredHeight) * (motionEvent.getY() - measuredHeight)) <= (this.mSBLeft + ((float) d(8))) * (this.mSBLeft + ((float) d(8)));
    }

    private boolean h(MotionEvent motionEvent) {
        return isEnabled() && motionEvent.getX() >= ((float) getPaddingLeft()) && motionEvent.getX() <= ((float) (getMeasuredWidth() - getPaddingRight()));
    }

    private float i() {
        float f2 = this.mProgress;
        if (this.DEBUG) {
            Log.e("ColorProgressBarZTE", "processProgress progress = " + f2);
        }
        return f2;
    }

    public float getMax() {
        return this.mMax;
    }

    public float getMin() {
        return this.mMin;
    }

    public synchronized int getProgress() {
        return Math.round(i());
    }

    public float getProgressFloat() {
        return e(i());
    }

    @Override // android.view.View
    protected synchronized void onDraw(Canvas canvas) {
        try {
            super.onDraw(canvas);
            if (this.mPaint != null && canvas != null) {
                float f2 = this.mMax;
                if (f2 < this.mProgress) {
                    this.mProgress = f2;
                    Log.d("ColorProgressBarZTE", "on Draw , mProgress = " + this.mProgress + ", mMax = " + this.mMax);
                }
                float f3 = this.mProgress;
                float f4 = this.mMin;
                if (f3 < f4) {
                    this.mProgress = f4;
                    Log.d("ColorProgressBarZTE", "on Draw , mProgress = " + this.mProgress + ", mMin = " + this.mMin);
                }
                this.mDelta = this.mMax - this.mMin;
                this.mPaint.setColor(this.mTrackBgColor);
                Path path = this.mPath;
                if (path != null) {
                    canvas.drawPath(path, this.mPaint);
                }
                float f5 = this.mSBLeft;
                float f6 = this.mTrackLength / this.mDelta;
                float f7 = this.mProgress;
                float f8 = this.mMin;
                float f9 = f5 + (f6 * (f7 - f8));
                this.mThumbCenterX = f9;
                if (f7 > f8) {
                    int i2 = (int) ((f9 - this.mLeftPadding) + (this.mTrackSize / 2));
                    int i3 = this.mLength;
                    if (i2 > i3) {
                        i2 = i3;
                    }
                    if (this.DEBUG) {
                        Log.d("ColorProgressBarZTE", "on Draw +++++ , mProgress = " + this.mProgress + ", mThumbCenterX = " + this.mThumbCenterX + ", width = " + i2 + ", mTrackSize = " + this.mTrackSize + ", mLeftPadding = " + this.mLeftPadding + ", mDelta = " + this.mDelta);
                    }
                    this.mPaint.setColor(this.mColorsProgress[0]);
                    canvas.drawBitmap(a(this.mColorsProgress, i2, this.mTrackSize), this.mLeftPadding, this.mTopPadding, this.mPaint);
                } else if (this.DEBUG) {
                    Log.w("ColorProgressBarZTE", "on Draw +++++ , mProgress = " + this.mProgress + ", mThumbCenterX = " + this.mThumbCenterX);
                }
                if (this.mIsEnable) {
                    setAlpha(1.0f);
                } else {
                    setAlpha(0.26f);
                }
            }
        } finally {
        }
    }

    @Override // android.view.View
    protected synchronized void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        this.mViewWidth = getMeasuredWidth();
        this.mViewHeight = getMeasuredHeight();
        this.mRightPadding = getPaddingRight();
        this.mLeftPadding = getPaddingLeft();
        this.mTopPadding = getPaddingTop();
        setMeasuredDimension(View.resolveSize(d(180), i2), getPaddingTop() + this.mTrackSize + getPaddingBottom());
        int i4 = this.mLeftPadding;
        int i5 = this.mTrackSize;
        float f2 = i4 + (i5 / 2);
        this.mSBLeft = f2;
        float f3 = (this.mViewWidth - this.mRightPadding) - (i5 / 2);
        this.mSBRight = f3;
        float f4 = f3 - f2;
        this.mTrackLength = f4;
        this.mLength = Math.round(f4 + (i5 * 2));
        this.mPath = new Path();
        int i6 = this.mLeftPadding;
        int i7 = this.mTopPadding;
        int i8 = i6 + this.mLength;
        int i9 = this.mTrackSize;
        this.mPath.addRoundRect(new RectF(i6, i7, i8 - i9, i7 + i9), this.mRadii, Path.Direction.CCW);
        Log.d("ColorProgressBarZTE", "on Measure out, mViewWidth=" + this.mViewWidth + ", mViewHeight=" + this.mViewHeight + ", mTopPadding=" + this.mTopPadding + ", mRightPadding=" + this.mRightPadding + ", mLeftPadding=" + this.mLeftPadding + ", mSBLeft=" + this.mSBLeft + ", mSBRight=" + this.mSBRight + ", mTrackLength=" + this.mTrackLength + ", mLength=" + this.mLength + "mMax=" + this.mMax + ", mMin=" + this.mMin + ", mDelta=" + this.mDelta + " mTrackSize=" + this.mTrackSize + ", mTopLeftRadius=" + this.mTopLeftRadius + ", mBottomRightRadius=" + this.mBottomRightRadius);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0017, code lost:
    
        if (r0 != 3) goto L44;
     */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            boolean r0 = r5.isTouchToSeek
            if (r0 != 0) goto L9
            boolean r5 = super.onTouchEvent(r6)
            return r5
        L9:
            int r0 = r6.getActionMasked()
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L84
            if (r0 == r2) goto L75
            r3 = 2
            if (r0 == r3) goto L1b
            r3 = 3
            if (r0 == r3) goto L75
            goto Ld6
        L1b:
            boolean r0 = r5.isThumbOnDragging
            if (r0 == 0) goto Ld6
            float r0 = r6.getX()
            float r3 = r5.dx
            float r0 = r0 + r3
            r5.mThumbCenterX = r0
            float r3 = r5.mSBLeft
            int r0 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r0 >= 0) goto L30
            r5.mThumbCenterX = r3
        L30:
            float r0 = r5.mThumbCenterX
            float r3 = r5.mSBRight
            int r0 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r0 <= 0) goto L3a
            r5.mThumbCenterX = r3
        L3a:
            float r0 = r5.b()
            r5.mProgress = r0
            int r0 = r5.getProgress()
            r5.invalidate()
            boolean r3 = r5.DEBUG
            if (r3 == 0) goto L6b
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "ACTION_MOVE invalidate, mPreProgress = "
            r3.append(r4)
            int r4 = r5.mPreProgress
            r3.append(r4)
            java.lang.String r4 = ", localPregress = "
            r3.append(r4)
            r3.append(r0)
            java.lang.String r3 = r3.toString()
            java.lang.String r4 = "ColorProgressBarZTE"
            android.util.Log.w(r4, r3)
        L6b:
            int r3 = r5.mPreProgress
            if (r3 == r0) goto Ld6
            r5.c(r0)
            r5.mPreProgress = r0
            goto Ld6
        L75:
            android.view.ViewParent r0 = r5.getParent()
            r0.requestDisallowInterceptTouchEvent(r1)
            int r0 = r5.getProgress()
            r5.c(r0)
            goto Ld6
        L84:
            r5.performClick()
            android.view.ViewParent r0 = r5.getParent()
            r0.requestDisallowInterceptTouchEvent(r2)
            boolean r0 = r5.g(r6)
            r5.isThumbOnDragging = r0
            if (r0 == 0) goto L9a
            r5.invalidate()
            goto Lcd
        L9a:
            boolean r0 = r5.isTouchToSeek
            if (r0 == 0) goto Lcd
            boolean r0 = r5.h(r6)
            if (r0 == 0) goto Lcd
            r5.isThumbOnDragging = r2
            float r0 = r6.getX()
            r5.mThumbCenterX = r0
            float r3 = r5.mSBLeft
            int r0 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r0 >= 0) goto Lb4
            r5.mThumbCenterX = r3
        Lb4:
            float r0 = r5.mThumbCenterX
            float r3 = r5.mSBRight
            int r0 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r0 <= 0) goto Lbe
            r5.mThumbCenterX = r3
        Lbe:
            float r0 = r5.b()
            r5.mProgress = r0
            int r0 = java.lang.Math.round(r0)
            r5.mPreProgress = r0
            r5.invalidate()
        Lcd:
            float r0 = r5.mThumbCenterX
            float r3 = r6.getX()
            float r0 = r0 - r3
            r5.dx = r0
        Ld6:
            boolean r0 = r5.isThumbOnDragging
            if (r0 != 0) goto Le4
            boolean r0 = r5.isTouchToSeek
            if (r0 != 0) goto Le4
            boolean r5 = super.onTouchEvent(r6)
            if (r5 == 0) goto Le5
        Le4:
            r1 = r2
        Le5:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.zte.mifavor.widget.ColorProgressBarZTE.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void setColors(int[] iArr) {
        if (iArr == null || 2 != iArr.length) {
            Log.w("ColorProgressBarZTE", "set color, the colors is error.");
        } else {
            this.mColorsProgress = iArr;
            invalidate();
        }
    }

    public void setDEBUG(boolean z) {
        this.DEBUG = z;
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        this.mIsEnable = z;
    }

    public void setMax(float f2) {
        this.mMax = f2;
        invalidate();
    }

    public void setMin(float f2) {
        this.mMin = f2;
        invalidate();
    }

    public void setOnProgressChangeListener(OnProgressChangeListener onProgressChangeListener) {
        this.mOnProgressChangeListener = onProgressChangeListener;
        Log.w("ColorProgressBarZTE", "set OnProgress Change Listener is " + onProgressChangeListener);
    }

    public void setProgress(float f2) {
        if (this.DEBUG) {
            Log.d("ColorProgressBarZTE", "set Progress is " + f2 + ", max = " + this.mMax + ", min = " + this.mMin);
        }
        if (Math.abs(this.mMax - f2) < 0.001d) {
            f2 = this.mMax;
        }
        if (Math.abs(this.mMin - f2) < 0.001d) {
            f2 = this.mMin;
        }
        this.mProgress = f2;
        invalidate();
        if (this.DEBUG) {
            Log.w("ColorProgressBarZTE", "invalidate and set Progress is mProgress = " + this.mProgress);
        }
        c((int) this.mProgress);
    }

    public ColorProgressBarZTE(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.TAG = "ColorProgressBarZTE";
        this.DEBUG = false;
        this.mIsEnable = true;
        this.mViewWidth = 0;
        this.mViewHeight = 0;
        this.mLeftPadding = 0;
        this.mRightPadding = 0;
        this.mTopPadding = 0;
        this.dx = 0.0f;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ColorProgressBarZTE, i2, 0);
        this.mMin = obtainStyledAttributes.getFloat(R.styleable.ColorProgressBarZTE_cpbz_min, 0.0f);
        this.mMax = obtainStyledAttributes.getFloat(R.styleable.ColorProgressBarZTE_cpbz_max, 100.0f);
        this.mProgress = obtainStyledAttributes.getFloat(R.styleable.ColorProgressBarZTE_cpbz_progress, this.mMin);
        this.mTrackSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ColorProgressBarZTE_cpbz_track_size, d(2));
        this.isTouchToSeek = obtainStyledAttributes.getBoolean(R.styleable.ColorProgressBarZTE_cpbz_touch_to_seek, true);
        this.mTrackBgColor = obtainStyledAttributes.getColor(R.styleable.ColorProgressBarZTE_cpbz_track_bg_color, Color.parseColor("#FF000000"));
        this.mColorsProgress = new int[]{obtainStyledAttributes.getColor(R.styleable.ColorProgressBarZTE_cpbz_colors_left, ContextCompat.c(context, R.color.mfv_common_pb_11_left)), obtainStyledAttributes.getColor(R.styleable.ColorProgressBarZTE_cpbz_colors_right, ContextCompat.c(context, R.color.mfv_common_pb_11_right))};
        setEnabled(obtainStyledAttributes.getBoolean(R.styleable.ColorProgressBarZTE_android_enabled, isEnabled()));
        this.mTopLeftRadius = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ColorProgressBarZTE_cpbz_topLeftRadius, this.mTrackSize / 2);
        this.mTopRightRadius = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ColorProgressBarZTE_cpbz_topRightRadius, this.mTrackSize / 2);
        this.mBottomLeftRadius = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ColorProgressBarZTE_cpbz_bottomLeftRadius, this.mTrackSize / 2);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ColorProgressBarZTE_cpbz_bottomRightRadius, this.mTrackSize / 2);
        this.mBottomRightRadius = dimensionPixelSize;
        int i3 = this.mTopLeftRadius;
        int i4 = this.mTopRightRadius;
        int i5 = this.mBottomLeftRadius;
        this.mRadii = new float[]{i3, i3, i4, i4, dimensionPixelSize, dimensionPixelSize, i5, i5};
        obtainStyledAttributes.recycle();
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        this.mPaint.setStrokeCap(Paint.Cap.ROUND);
        this.mPaint.setTextAlign(Paint.Align.CENTER);
        f();
        if (Utils.f17815b) {
            setForceDarkAllowed(false);
        }
        if (this.DEBUG) {
            Log.d("ColorProgressBarZTE", "ColorProgressBarZTE out. mTrackSize = " + this.mTrackSize + ", mTopLeftRadius=" + this.mTopLeftRadius + ", mBottomRightRadius=" + this.mBottomRightRadius);
        }
    }
}
