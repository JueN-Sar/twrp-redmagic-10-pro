package com.zte.mifavor.widget;

import android.animation.ValueAnimator;
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
import android.view.ViewConfiguration;
import android.view.animation.PathInterpolator;
import android.widget.SeekBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import com.zte.extres.R;
import java.math.BigDecimal;

/* loaded from: classes2.dex */
public class ColorSeekBarZTE extends SeekBar {
    private final int ANGLE;
    private boolean DEBUG;
    private final int R_ANGLE;
    private final String TAG;
    private float dxx;
    private boolean isThumbOnDragging;
    private boolean isTouchToSeek;
    private int mBottomLeftRadius;
    private int mBottomPadding;
    private int mBottomRightRadius;
    private int[] mColorsProgress;
    private float mDefinedMax;
    private float mDefinedMin;
    private float mDelta;
    private long mDownTime;
    private final int mDuration150;
    private final int mDuration250;
    private int mFinalPregress;
    private int mInitialTouchX;
    private int mInitialTouchY;
    private boolean mIsCancellable;
    private boolean mIsEnable;
    private boolean mIsRealtimeCallback;
    private boolean mIsSliding;
    private boolean mIsUseAnimation;
    private int mLeftPadding;
    private int mLength;
    private float mMax;
    private float mMin;
    private float mMinReachableProgress;

    @Nullable
    private SeekBar.OnSeekBarChangeListener mOnSeekBarChangeListener;
    private Paint mPaint;

    @Nullable
    private Path mPath;
    private int mPreProgress;
    private float mProgress;
    private ValueAnimator mProgressAnimation;
    private float[] mRadii;
    private boolean mReportWhenActionCancle;
    private int mRightPadding;
    private int mRotation;
    private float mSBLeft;
    private float mSBRight;
    private float mThumbCenterX;
    private int mThumbInnerColor;
    private int mThumbInnerRadius;
    private int mThumbRadius;
    private int mTopLeftRadius;
    private int mTopPadding;
    private int mTopRightRadius;
    private int mTouchSlop;
    private int mTrackBgColor;
    private float mTrackLength;
    private int mTrackSize;
    private int mViewHeight;
    private int mViewWidth;
    private float mYTop;
    PathInterpolator pathInterpolator;

    public ColorSeekBarZTE(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.colorSeekBarZTEStyle);
    }

    private Bitmap l(int[] iArr, int i2, int i3) {
        if (i2 <= 0) {
            Log.e("ColorSeekBarZTE", "build Bitmap width error, width = " + i2);
            i2 = o(2);
        }
        if (i3 <= 0) {
            Log.e("ColorSeekBarZTE", "build Bitmap height error, height = " + i3);
            i3 = o(2);
        }
        try {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setShape(0);
            gradientDrawable.setColors(iArr);
            gradientDrawable.setGradientType(0);
            gradientDrawable.setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);
            gradientDrawable.setGradientRadius(20.0f);
            gradientDrawable.setCornerRadii(this.mRadii);
            gradientDrawable.setBounds(0, 0, i2, i3);
            Bitmap createBitmap = Bitmap.createBitmap(i2, i3, gradientDrawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
            gradientDrawable.draw(new Canvas(createBitmap));
            return createBitmap;
        } catch (Exception e2) {
            Log.e("ColorSeekBarZTE", "build Bitmap error, e = ", e2);
            return Bitmap.createBitmap(o(2), o(2), Bitmap.Config.RGB_565);
        }
    }

    private float m() {
        float f2 = (((this.mThumbCenterX - this.mSBLeft) * this.mDelta) / this.mTrackLength) + this.mMin;
        if (this.DEBUG) {
            Log.d("ColorSeekBarZTE", "calculate Progress mThumbCenterX=" + this.mThumbCenterX + ", mSBLeft=" + this.mSBLeft + ", mDelta=" + this.mDelta + ", mTrackLength=" + this.mTrackLength + ", mMin=" + this.mMin + ", mMax=" + this.mMax + ", progress = " + f2);
        }
        return f2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n(boolean z) {
        SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = this.mOnSeekBarChangeListener;
        if (onSeekBarChangeListener != null) {
            onSeekBarChangeListener.onProgressChanged(this, getProgress(), z);
        }
    }

    private int o(int i2) {
        return (int) TypedValue.applyDimension(1, i2, Resources.getSystem().getDisplayMetrics());
    }

    private float p(float f2) {
        try {
            return BigDecimal.valueOf(f2).setScale(1, 4).floatValue();
        } catch (Exception e2) {
            Log.e("ColorSeekBarZTE", "format Float error, e = " + e2);
            return 0.0f;
        }
    }

    private void q() {
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
        if (this.DEBUG) {
            Log.d("ColorSeekBarZTE", "initConfigByPriority out, mMax = " + this.mMax + ", mMin = " + this.mMin + ", mDelta = " + this.mDelta + ", mProgress = " + this.mProgress + ", mPreProgress = " + this.mPreProgress);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r(float f2, boolean z, boolean z2) {
        if (Math.abs(this.mMax - f2) < 0.001d) {
            f2 = this.mMax;
        }
        if (Math.abs(this.mMin - f2) < 0.001d) {
            f2 = this.mMin;
        }
        this.mProgress = f2;
        SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = this.mOnSeekBarChangeListener;
        if (onSeekBarChangeListener != null && z2) {
            onSeekBarChangeListener.onProgressChanged(this, getProgress(), z);
        } else if (this.DEBUG) {
            Log.d("ColorSeekBarZTE", "invalidate Progress. mOnSeekBarChangeListener=" + this.mOnSeekBarChangeListener + ", mIsRealtimeCallback = " + this.mIsRealtimeCallback);
        }
        int i2 = (int) f2;
        super.setProgress(i2);
        invalidate();
        this.mPreProgress = i2;
        if (this.DEBUG) {
            Log.w("ColorSeekBarZTE", "+++++++++++ invalidate Progress out, mPreProgress=" + this.mPreProgress + ", isFromUser=" + z + ", isCallback=" + z2);
        }
    }

    private boolean s(MotionEvent motionEvent) {
        if (!isEnabled()) {
            return false;
        }
        float f2 = this.mSBLeft + ((this.mTrackLength / this.mDelta) * (this.mProgress - this.mMin));
        float measuredHeight = getMeasuredHeight() / 2.0f;
        motionEvent.getActionMasked();
        return ((motionEvent.getX() - f2) * (motionEvent.getX() - f2)) + ((motionEvent.getY() - measuredHeight) * (motionEvent.getY() - measuredHeight)) <= (this.mSBLeft + ((float) o(8))) * (this.mSBLeft + ((float) o(8)));
    }

    private boolean t(MotionEvent motionEvent) {
        int i2 = this.mRotation;
        return (270 == i2 || -90 == i2) ? isEnabled() && ((float) (-getMeasuredWidth())) <= motionEvent.getX() && motionEvent.getX() <= ((float) (getMeasuredWidth() * 2)) : isEnabled() && ((float) this.mLeftPadding) <= motionEvent.getX() && motionEvent.getX() <= ((float) getMeasuredWidth());
    }

    private float u() {
        return this.mProgress;
    }

    private void v(float f2, final float f3, int i2, boolean z) {
        float f4 = f2;
        if (this.DEBUG) {
            Log.d("ColorSeekBarZTE", "setProgressWithAnimation in, pre = " + f4 + ", post=" + f3 + ", duration=" + i2 + ", isCancelable=" + z + ", mIsCancellable=" + this.mIsCancellable + ", mProgressAnimation=" + this.mProgressAnimation + ", this=" + this);
        }
        if (!this.mIsCancellable) {
            if (this.DEBUG) {
                Log.w("ColorSeekBarZTE", "Progress Animation is Running and noncancelable. do nothing, pre=" + f4 + ", post=" + f3 + ", mProgress=" + this.mProgress + ", mIsCancellable=" + this.mIsCancellable);
                return;
            }
            return;
        }
        if (this.mProgressAnimation == null) {
            this.mProgressAnimation = ValueAnimator.ofFloat(f4, f3);
        }
        ValueAnimator valueAnimator = this.mProgressAnimation;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            if (f3 == this.mFinalPregress) {
                Log.w("ColorSeekBarZTE", "The Post and last post are the same, the Animation continues to execute, post=" + f3 + ", mFinalPregress=" + this.mFinalPregress + ", mIsCancellable=" + this.mIsCancellable);
                return;
            }
            this.mProgressAnimation.cancel();
            this.mProgressAnimation = null;
            if (this.DEBUG) {
                Log.w("ColorSeekBarZTE", "+++++++++++ Cancel Animation, pre=" + f4 + ", mProgress=" + this.mProgress + ", post=" + f3 + ", mIsCancellable=" + this.mIsCancellable + ", mFinalPregress=" + this.mFinalPregress);
            }
            r(this.mFinalPregress, false, true);
            f4 = this.mFinalPregress;
            this.mProgressAnimation = ValueAnimator.ofFloat(f4, f3);
        }
        this.mFinalPregress = Math.round(f3);
        if (this.DEBUG) {
            Log.w("ColorSeekBarZTE", "update mFinalPregress=" + this.mFinalPregress + ", pre=" + f4);
        }
        ValueAnimator valueAnimator2 = this.mProgressAnimation;
        if (valueAnimator2 == null || valueAnimator2.isRunning()) {
            if (this.DEBUG) {
                Log.w("ColorSeekBarZTE", "+++++++++++ setProgressWithAnimation warning, isRunning. pre=" + f4 + ", post=" + f3 + ", duration=" + i2);
                return;
            }
            return;
        }
        this.mIsCancellable = z;
        this.mProgressAnimation.setDuration(i2);
        this.mProgressAnimation.setInterpolator(this.pathInterpolator);
        final boolean z2 = 150 == i2;
        this.mProgressAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.zte.mifavor.widget.ColorSeekBarZTE.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator3) {
                float floatValue = ((Float) valueAnimator3.getAnimatedValue()).floatValue();
                if (ColorSeekBarZTE.this.DEBUG) {
                    Log.d("ColorSeekBarZTE", "+++++++++++ onAnimationUpdate, progress=" + floatValue + ", post=" + f3 + ", finalIsFromUser=" + z2 + ", mIsRealtimeCallback=" + ColorSeekBarZTE.this.mIsRealtimeCallback);
                }
                ColorSeekBarZTE colorSeekBarZTE = ColorSeekBarZTE.this;
                colorSeekBarZTE.r(floatValue, z2, colorSeekBarZTE.mIsRealtimeCallback);
                if (Math.abs(f3 - floatValue) < 0.01f) {
                    ColorSeekBarZTE.this.n(z2);
                    ColorSeekBarZTE.this.mIsCancellable = true;
                    ColorSeekBarZTE.this.mPreProgress = (int) floatValue;
                    if (ColorSeekBarZTE.this.mProgressAnimation != null) {
                        ColorSeekBarZTE.this.mProgressAnimation.cancel();
                    }
                    ColorSeekBarZTE.this.mProgressAnimation = null;
                    if (ColorSeekBarZTE.this.DEBUG) {
                        Log.w("ColorSeekBarZTE", "+++++++++++ End Progress Animation, mPreProgress=" + ColorSeekBarZTE.this.mPreProgress + ", mProgress=" + ColorSeekBarZTE.this.mProgress + ", mFinalPregress=" + ColorSeekBarZTE.this.mFinalPregress + ", mProgressAnimation=" + ColorSeekBarZTE.this.mProgressAnimation);
                    }
                }
            }
        });
        if (this.DEBUG) {
            Log.w("ColorSeekBarZTE", "+++++++++++ Start Progress Animation with pathInterpolator, pre=" + f4 + ", post=" + f3 + ", duration=" + i2 + ", mProgressAnimation=" + this.mProgressAnimation + ", mIsCancellable=" + this.mIsCancellable + ", this=" + this);
        }
        this.mProgressAnimation.start();
    }

    public int getFinalPregress() {
        return this.mFinalPregress;
    }

    public float getMinReachableProgress() {
        return this.mMinReachableProgress;
    }

    @Override // android.widget.ProgressBar
    public synchronized int getProgress() {
        return Math.round(u());
    }

    public float getProgressFloat() {
        return p(u());
    }

    public boolean getUseAnimation() {
        return this.mIsUseAnimation;
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    protected synchronized void onDraw(Canvas canvas) {
        try {
            super.onDraw(canvas);
            if (this.mPaint != null && canvas != null) {
                float f2 = this.mDefinedMax;
                if (0.1d < f2 && f2 < this.mProgress) {
                    this.mProgress = f2;
                    Log.d("ColorSeekBarZTE", "on Draw , mProgress = " + this.mProgress + ", mDefinedMax = " + this.mDefinedMax);
                }
                float f3 = this.mProgress;
                float f4 = this.mDefinedMin;
                if (f3 < f4) {
                    this.mProgress = f4;
                    Log.d("ColorSeekBarZTE", "on Draw , mProgress = " + this.mProgress + ", mDefinedMin = " + this.mDefinedMin);
                }
                this.mThumbCenterX = this.mSBLeft + ((this.mTrackLength / this.mDelta) * (this.mProgress - this.mMin));
                this.mPaint.setColor(this.mTrackBgColor);
                if (this.mPath == null) {
                    this.mPath = new Path();
                    this.mPath.addRoundRect(new RectF(this.mLeftPadding, this.mTopPadding, r1 + this.mLength, r3 + this.mTrackSize), this.mRadii, Path.Direction.CCW);
                }
                canvas.drawPath(this.mPath, this.mPaint);
                this.mPaint.setAlpha(255);
                int i2 = (int) ((this.mThumbCenterX - this.mLeftPadding) + this.mThumbRadius);
                int i3 = this.mLength;
                if (i2 > i3) {
                    Log.d("ColorSeekBarZTE", "on Draw error, width = " + i3 + ", mLength = " + this.mLength);
                    i2 = i3;
                }
                canvas.drawBitmap(l(this.mColorsProgress, i2, this.mTrackSize), this.mLeftPadding, this.mTopPadding, this.mPaint);
                if (this.DEBUG) {
                    Log.d("ColorSeekBarZTE", "on Draw ++++++++++++++++++++++++++++++ mProgress=" + this.mProgress + ", mPreProgress=" + this.mPreProgress);
                }
                this.mPaint.setColor(this.mThumbInnerColor);
                float f5 = this.mThumbCenterX;
                float f6 = this.mSBRight;
                if (f5 > f6) {
                    this.mThumbCenterX = f6;
                }
                canvas.drawCircle(this.mThumbCenterX, this.mYTop, this.mThumbInnerRadius, this.mPaint);
                if (this.mIsEnable) {
                    setAlpha(1.0f);
                } else {
                    setAlpha(0.26f);
                }
                this.mPreProgress = (int) this.mProgress;
            }
        } finally {
        }
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    protected synchronized void onMeasure(int i2, int i3) {
        try {
            super.onMeasure(i2, i3);
            this.mViewWidth = getMeasuredWidth();
            this.mViewHeight = getMeasuredHeight();
            if (this.DEBUG) {
                Log.d("ColorSeekBarZTE", "on Measure in, mLeftPadding=" + this.mLeftPadding + ", mRightPadding=" + this.mRightPadding + ", widthMeasureSpec=" + i2 + ", heightMeasureSpec=" + i3 + ", mViewWidth=" + this.mViewWidth + ", mViewHeight=" + this.mViewHeight);
            }
            this.mViewWidth = getMeasuredWidth();
            setMeasuredDimension(SeekBar.resolveSize(o(180), i2), this.mTrackSize + this.mTopPadding + this.mBottomPadding);
            int i4 = this.mLeftPadding;
            int i5 = this.mThumbRadius;
            float f2 = i4 + i5;
            this.mSBLeft = f2;
            float f3 = (this.mViewWidth - this.mRightPadding) - i5;
            this.mSBRight = f3;
            float f4 = f3 - f2;
            this.mTrackLength = f4;
            this.mYTop = this.mTopPadding + (this.mTrackSize / 2);
            this.mLength = Math.round(f4 + (i5 * 2));
            if (this.DEBUG) {
                Log.d("ColorSeekBarZTE", "on Measure out, mViewWidth=" + this.mViewWidth + ", mLeftPadding=" + this.mLeftPadding + ", mRightPadding=" + this.mRightPadding + ", mTopPadding=" + this.mTopPadding + ", mBottomPadding=" + this.mBottomPadding + ", mSBLeft=" + this.mSBLeft + ", mSBRight=" + this.mSBRight + ", mTrackLength=" + this.mTrackLength + ", mLength=" + this.mLength);
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // android.widget.AbsSeekBar, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z;
        boolean z2;
        int i2;
        int i3;
        if (!this.mIsEnable) {
            return super.onTouchEvent(motionEvent);
        }
        int actionMasked = motionEvent.getActionMasked();
        boolean t = t(motionEvent);
        this.isThumbOnDragging = s(motionEvent);
        if (actionMasked != 0) {
            if (actionMasked != 1) {
                if (actionMasked == 2) {
                    this.mIsSliding = true;
                    int x = (int) (motionEvent.getX() + 0.5f);
                    int y = (int) (motionEvent.getY() + 0.5f);
                    int i4 = x - this.mInitialTouchX;
                    int i5 = y - this.mInitialTouchY;
                    boolean z3 = Math.abs(i4) > this.mTouchSlop && Math.abs(i4) > Math.abs(i5);
                    if (z3) {
                        this.mInitialTouchX = x;
                        this.mInitialTouchY = y;
                    }
                    if (this.DEBUG) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("ACTION_MOVE isRunning=");
                        sb.append(z3);
                        sb.append(", x=");
                        sb.append(x);
                        sb.append(", y=");
                        sb.append(y);
                        i3 = y;
                        sb.append(", dx = ");
                        sb.append(i4);
                        sb.append(", dy = ");
                        sb.append(i5);
                        sb.append(", mIsSliding = ");
                        sb.append(this.mIsSliding);
                        sb.append(", isTrackTouched=");
                        sb.append(t);
                        sb.append(", isThumbOnDragging=");
                        sb.append(this.isThumbOnDragging);
                        sb.append(", mProgress=");
                        sb.append(this.mProgress);
                        Log.d("ColorSeekBarZTE", sb.toString());
                    } else {
                        i3 = y;
                    }
                    if (this.isThumbOnDragging || t) {
                        this.mThumbCenterX = motionEvent.getX() + this.dxx;
                        if (this.DEBUG) {
                            Log.w("ColorSeekBarZTE", "ACTION_MOVE mThumbCenterX=" + this.mThumbCenterX + ", mIsSliding = " + this.mIsSliding + ", dxx = " + this.dxx);
                        }
                        float f2 = this.mThumbCenterX;
                        float f3 = this.mSBLeft;
                        if (f2 < f3) {
                            this.mThumbCenterX = f3;
                        }
                        float f4 = this.mThumbCenterX;
                        float f5 = this.mSBRight;
                        if (f4 > f5) {
                            this.mThumbCenterX = f5;
                        }
                        if (z3) {
                            float m2 = m();
                            this.mProgress = m2;
                            if (m2 < this.mMinReachableProgress) {
                                if (this.DEBUG) {
                                    Log.d("ColorSeekBarZTE", "ACTION_MOVE, mProgress=" + this.mProgress + ", mMinReachableProgress=" + this.mMinReachableProgress + ", mPreProgress=" + this.mPreProgress);
                                }
                                this.mProgress = this.mMinReachableProgress;
                            }
                            this.mFinalPregress = Math.round(this.mProgress);
                            int progress = getProgress();
                            if (this.DEBUG) {
                                Log.d("ColorSeekBarZTE", "ACTION_MOVE, mThumbCenterX=" + this.mThumbCenterX + ", isThumbOnDragging=" + this.isThumbOnDragging + ", isTrackTouched=" + t + ", localPregress=" + progress + ", mProgress=" + this.mProgress);
                            }
                            if (this.mPreProgress != progress || progress == 0) {
                                invalidate();
                                SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = this.mOnSeekBarChangeListener;
                                if (onSeekBarChangeListener != null) {
                                    onSeekBarChangeListener.onProgressChanged(this, Math.round(progress), true);
                                }
                                this.mPreProgress = progress;
                            }
                        } else if (this.DEBUG) {
                            Log.d("ColorSeekBarZTE", "ACTION_MOVE isRunning=" + z3 + ", x=" + x + ", y=" + i3 + ", dx=" + i4 + ", dy=" + i5 + ", mInitialTouchX=" + this.mInitialTouchX + ", mInitialTouchY=" + this.mInitialTouchY + ", mTouchSlop=" + this.mTouchSlop);
                        }
                    } else {
                        if (this.DEBUG) {
                            Log.d("ColorSeekBarZTE", "ACTION_MOVE isThumbOnDragging=" + this.isThumbOnDragging + ", isTrackTouched = " + t + ", isTouchToSeek = " + this.isTouchToSeek + ", isRunning = " + z3 + ", mProgress = " + this.mProgress);
                        }
                        getParent().requestDisallowInterceptTouchEvent(false);
                    }
                    return z3 || this.isThumbOnDragging || this.isTouchToSeek || super.onTouchEvent(motionEvent);
                }
                if (actionMasked != 3) {
                    z2 = true;
                    z = false;
                }
            }
            this.mThumbCenterX = motionEvent.getX();
            if (this.DEBUG) {
                Log.d("ColorSeekBarZTE", "ACTION_UP or CANCEL, mThumbCenterX=" + this.mThumbCenterX + ", mIsSliding=" + this.mIsSliding);
            }
            float f6 = this.mThumbCenterX;
            float f7 = this.mSBLeft;
            if (f6 < f7) {
                this.mThumbCenterX = f7;
            }
            float f8 = this.mThumbCenterX;
            float f9 = this.mSBRight;
            if (f8 > f9) {
                this.mThumbCenterX = f9;
            }
            float m3 = m();
            this.mProgress = m3;
            if (m3 < this.mMinReachableProgress) {
                if (this.DEBUG) {
                    Log.d("ColorSeekBarZTE", "ACTION_UP or CANCEL, mProgress=" + this.mProgress + ", mMinReachableProgress=" + this.mMinReachableProgress + ", mPreProgress=" + this.mPreProgress);
                }
                this.mProgress = this.mMinReachableProgress;
            }
            float f10 = (this.mProgress - this.mPreProgress) / (this.mMax - this.mMin);
            if (this.DEBUG) {
                Log.d("ColorSeekBarZTE", "ACTION_UP or CANCEL, mThumbCenterX=" + this.mThumbCenterX + ", mIsSliding=" + this.mIsSliding + ", ratio=" + f10 + ", mProgress=" + this.mProgress + ", mPreProgress=" + this.mPreProgress + ", mIsUseAnimation = " + this.mIsUseAnimation + ", mFinalPregress=" + this.mFinalPregress);
            }
            if (this.mIsSliding && (i2 = this.mPreProgress) == 0 && f10 < 0.1f) {
                this.mProgress = i2;
                if (this.DEBUG) {
                    Log.w("ColorSeekBarZTE", "ACTION_UP or CANCEL, adjusted mProgress=" + this.mProgress + ", mPreProgress=" + this.mPreProgress + ", ratio=" + f10 + ", mIsSliding=" + this.mIsSliding);
                }
            }
            this.mFinalPregress = Math.round(this.mProgress);
            this.mIsSliding = false;
            if (actionMasked != 1 && (actionMasked != 3 || !this.mReportWhenActionCancle)) {
                z = false;
            } else if (this.mIsUseAnimation) {
                z = false;
                v(this.mPreProgress, this.mProgress, 150, false);
            } else {
                z = false;
                r(this.mProgress, true, true);
            }
            getParent().requestDisallowInterceptTouchEvent(z);
            SeekBar.OnSeekBarChangeListener onSeekBarChangeListener2 = this.mOnSeekBarChangeListener;
            if (onSeekBarChangeListener2 != null) {
                onSeekBarChangeListener2.onStopTrackingTouch(this);
            }
            z2 = true;
        } else {
            z = false;
            this.mFinalPregress = 0;
            this.mIsSliding = false;
            this.mInitialTouchX = (int) (motionEvent.getX() + 0.5f);
            this.mInitialTouchY = (int) (motionEvent.getY() + 0.5f);
            if (this.DEBUG) {
                Log.d("ColorSeekBarZTE", "ACTION_DOWN  mInitialTouchX=" + this.mInitialTouchX + ", mInitialTouchY=" + this.mInitialTouchY);
            }
            performClick();
            z2 = true;
            getParent().requestDisallowInterceptTouchEvent(true);
            if (this.isThumbOnDragging) {
                Log.d("ColorSeekBarZTE", "ACTION_DOWN  invalidate ...");
            } else if (this.isTouchToSeek && t) {
                this.isThumbOnDragging = true;
                float x2 = motionEvent.getX();
                this.mThumbCenterX = x2;
                float f11 = this.mSBLeft;
                if (x2 < f11) {
                    this.mThumbCenterX = f11;
                }
                float f12 = this.mThumbCenterX;
                float f13 = this.mSBRight;
                if (f12 > f13) {
                    this.mThumbCenterX = f13;
                }
            }
            float progress2 = getProgress();
            this.mProgress = progress2;
            int round = Math.round(progress2);
            this.mPreProgress = round;
            this.mFinalPregress = round;
            this.dxx = this.mThumbCenterX - motionEvent.getX();
            if (this.DEBUG) {
                Log.d("ColorSeekBarZTE", "ACTION_DOWN, mThumbCenterX=" + this.mThumbCenterX + ", dxx=" + this.dxx + ", isTrackTouched=" + t + ", isThumbOnDragging=" + this.isThumbOnDragging + ", mProgress=" + this.mProgress + ", mPreProgress=" + this.mPreProgress);
            }
            SeekBar.OnSeekBarChangeListener onSeekBarChangeListener3 = this.mOnSeekBarChangeListener;
            if (onSeekBarChangeListener3 != null) {
                onSeekBarChangeListener3.onStartTrackingTouch(this);
            }
        }
        return (this.isThumbOnDragging || this.isTouchToSeek || super.onTouchEvent(motionEvent)) ? z2 : z;
    }

    public void setColors(int[] iArr) {
        if (iArr == null || 2 != iArr.length) {
            Log.w("ColorSeekBarZTE", "set color, the colors is error.");
        } else {
            this.mColorsProgress = iArr;
            invalidate();
        }
    }

    public void setDEBUG(boolean z) {
        this.DEBUG = z;
    }

    public void setDefinedMax(float f2) {
        this.mDefinedMax = f2;
        float f3 = this.mMax;
        if (f3 < f2) {
            this.mDefinedMax = f3;
        }
    }

    public void setDefinedMin(float f2) {
        this.mDefinedMin = f2;
        float f3 = this.mMin;
        if (f3 > f2) {
            this.mDefinedMin = f3;
        }
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        this.mIsEnable = z;
    }

    public void setFinalProgress(int i2) {
        this.mFinalPregress = i2;
    }

    public void setIsRealtimeCallback(boolean z) {
        this.mIsRealtimeCallback = z;
    }

    public void setMinReachableProgress(float f2) {
        this.mMinReachableProgress = f2;
    }

    @Override // android.widget.SeekBar
    public void setOnSeekBarChangeListener(SeekBar.OnSeekBarChangeListener onSeekBarChangeListener) {
        this.mOnSeekBarChangeListener = onSeekBarChangeListener;
    }

    public void setProgress(float f2) {
        if (this.DEBUG) {
            Log.d("ColorSeekBarZTE", "set Progress in, progress=" + f2 + ", mProgress=" + this.mProgress + ", mIsSliding=" + this.mIsSliding + ", mIsUseAnimation=" + this.mIsUseAnimation + ", mPreProgress=" + this.mPreProgress);
        }
        if (this.mIsSliding || Math.abs(f2 - this.mProgress) < 1.0E-5f) {
            return;
        }
        if (this.mIsUseAnimation) {
            v(this.mPreProgress, f2, 250, true);
        } else {
            ValueAnimator valueAnimator = this.mProgressAnimation;
            if (valueAnimator != null && valueAnimator.isRunning()) {
                this.mProgressAnimation.cancel();
                this.mProgressAnimation = null;
                if (this.DEBUG) {
                    Log.d("ColorSeekBarZTE", "cancel mProgressAnimation and invalidate Progress. mFinalPregress=" + this.mFinalPregress);
                }
                r(this.mFinalPregress, false, true);
            }
            r(f2, false, true);
        }
        if (this.DEBUG) {
            Log.d("ColorSeekBarZTE", "set Progress out, mFinalPregress=" + this.mFinalPregress);
        }
    }

    public void setUseAnimation(boolean z) {
        this.mIsUseAnimation = z;
    }

    public ColorSeekBarZTE(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.TAG = "ColorSeekBarZTE";
        this.DEBUG = false;
        this.mDefinedMin = 0.0f;
        this.mDefinedMax = 0.0f;
        this.mProgress = 0.0f;
        this.mPreProgress = 0;
        this.mIsEnable = true;
        this.mViewWidth = 0;
        this.mViewHeight = 0;
        this.mLeftPadding = 0;
        this.mRightPadding = 0;
        this.mTopPadding = 0;
        this.mBottomPadding = 0;
        this.mYTop = 0.0f;
        this.mIsSliding = false;
        this.mPath = null;
        this.mRotation = 0;
        this.mDuration250 = 250;
        this.mDuration150 = 150;
        this.mIsUseAnimation = false;
        this.mIsCancellable = true;
        this.mFinalPregress = 0;
        this.mIsRealtimeCallback = false;
        this.mReportWhenActionCancle = true;
        this.mMinReachableProgress = 0.0f;
        this.dxx = 0.0f;
        this.mDownTime = 0L;
        this.ANGLE = 270;
        this.R_ANGLE = -90;
        this.mProgressAnimation = null;
        this.pathInterpolator = new PathInterpolator(0.33f, 0.0f, 0.0f, 1.0f);
        if (this.DEBUG) {
            Log.d("ColorSeekBarZTE", "ColorSeekBarZTE in, mProgress=" + this.mProgress + ", mPreProgress=" + this.mPreProgress);
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ColorSeekBarZTE, i2, 0);
        this.mMin = obtainStyledAttributes.getFloat(R.styleable.ColorSeekBarZTE_csbz_min, 0.0f);
        this.mMax = obtainStyledAttributes.getFloat(R.styleable.ColorSeekBarZTE_csbz_max, 100.0f);
        this.mProgress = obtainStyledAttributes.getFloat(R.styleable.ColorSeekBarZTE_csbz_progress, this.mMin);
        this.mIsUseAnimation = obtainStyledAttributes.getBoolean(R.styleable.ColorSeekBarZTE_csbz_use_animation, false);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ColorSeekBarZTE_csbz_track_size, o(2));
        this.mTrackSize = dimensionPixelSize;
        int dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ColorSeekBarZTE_csbz_thumb_radius, dimensionPixelSize / 2);
        this.mThumbRadius = dimensionPixelSize2;
        this.mThumbInnerRadius = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ColorSeekBarZTE_csbz_thumb_inner_radius, (dimensionPixelSize2 / 2) - 1);
        this.isTouchToSeek = obtainStyledAttributes.getBoolean(R.styleable.ColorSeekBarZTE_csbz_touch_to_seek, false);
        this.mThumbInnerColor = obtainStyledAttributes.getColor(R.styleable.ColorSeekBarZTE_csbz_thumb_inner_color, Color.parseColor("#FFFFFFFF"));
        this.mTrackBgColor = obtainStyledAttributes.getColor(R.styleable.ColorSeekBarZTE_csbz_track_bg_color, Color.parseColor("#FF000000"));
        this.mColorsProgress = new int[]{obtainStyledAttributes.getColor(R.styleable.ColorSeekBarZTE_csbz_colors_left, ContextCompat.c(context, R.color.mfv_common_pb_11_left)), obtainStyledAttributes.getColor(R.styleable.ColorSeekBarZTE_csbz_colors_right, ContextCompat.c(context, R.color.mfv_common_pb_11_right))};
        this.mLeftPadding = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ColorSeekBarZTE_csbz_paddingLeft, -1);
        this.mRightPadding = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ColorSeekBarZTE_csbz_paddingRight, -1);
        this.mTopPadding = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ColorSeekBarZTE_csbz_paddingTop, -1);
        this.mBottomPadding = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ColorSeekBarZTE_csbz_paddingBottom, -1);
        this.mTopLeftRadius = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ColorSeekBarZTE_csbz_topLeftRadius, this.mTrackSize / 2);
        this.mTopRightRadius = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ColorSeekBarZTE_csbz_topRightRadius, this.mTrackSize / 2);
        this.mBottomLeftRadius = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ColorSeekBarZTE_csbz_bottomLeftRadius, this.mTrackSize / 2);
        int dimensionPixelSize3 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ColorSeekBarZTE_csbz_bottomRightRadius, this.mTrackSize / 2);
        this.mBottomRightRadius = dimensionPixelSize3;
        int i3 = this.mTopLeftRadius;
        int i4 = this.mTopRightRadius;
        int i5 = this.mBottomLeftRadius;
        this.mRadii = new float[]{i3, i3, i4, i4, dimensionPixelSize3, dimensionPixelSize3, i5, i5};
        setEnabled(obtainStyledAttributes.getBoolean(R.styleable.ColorSeekBarZTE_android_enabled, isEnabled()));
        obtainStyledAttributes.recycle();
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        this.mPaint.setStrokeCap(Paint.Cap.ROUND);
        this.mPaint.setTextAlign(Paint.Align.CENTER);
        this.mRotation = (int) getRotation();
        q();
        this.mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop() / 8;
        if (-1 == this.mLeftPadding) {
            this.mLeftPadding = getPaddingLeft();
        }
        if (-1 == this.mRightPadding) {
            this.mRightPadding = getPaddingRight();
        }
        if (-1 == this.mTopPadding) {
            this.mTopPadding = getPaddingTop();
        }
        if (-1 == this.mBottomPadding) {
            this.mBottomPadding = getPaddingBottom();
        }
        if (Utils.f17815b) {
            setForceDarkAllowed(false);
        }
        this.mMinReachableProgress = this.mMin;
        if (this.DEBUG) {
            Log.d("ColorSeekBarZTE", "ColorSeekBarZTE out. mThumbRadius=" + this.mThumbRadius + ", mTrackSize=" + this.mTrackSize + ", mIsUseAnimation=" + this.mIsUseAnimation + ", mThumbInnerRadius = " + this.mThumbInnerRadius + ", mRotation=" + this.mRotation + ", mLeftPadding=" + this.mLeftPadding + ", mRightPadding=" + this.mRightPadding + ", mTopPadding=" + this.mTopPadding + ", mBottomPadding=" + this.mBottomPadding + ", mProgress=" + this.mProgress + ", mPreProgress=" + this.mPreProgress + ", mMinReachableProgress=" + this.mMinReachableProgress + ", getRotation=" + getRotation());
        }
    }
}
