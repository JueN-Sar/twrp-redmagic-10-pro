package com.zte.mifavor.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import com.zte.extres.R;
import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/* loaded from: classes2.dex */
public class CircleProgressBarZTE extends View {
    private static final int DEFAULT_MAX_PROGRESS = 100;
    private static final boolean DEFAULT_SHOW_TEXT = false;
    private static final float DEFAULT_STROKE_WIDTH = 10.0f;
    private static final int DEFAULT_TEXT_COLOR = -16777216;
    private static final float DEFAULT_TEXT_SIZE = 30.0f;
    private static final int DEFAULT_TIME = 30000;
    private static final String TAG = "CircleProgressBarZTE";
    private final int INTERVAL;
    private int mBgColor;
    private Paint mBgPaint;
    private ScheduledThreadPoolExecutor mExecutor;
    private boolean mIsReverse;
    private int mMaxProgress;
    private float mProgress;
    private int mProgressColor;
    private float mProgressInterval;
    private Paint mProgressPaint;
    private RectF mRectF;
    private boolean mShowText;
    private float mStrokeWidth;
    private int mTextColor;
    private Paint mTextPaint;
    private float mTextSize;
    private int mTiming;
    private static final int DEFAULT_PROGRESS_COLOR = Color.parseColor("#0F87F7");
    private static final int DEFAULT_BACKGROUND_COLOR = Color.parseColor("#1A7D7D7D");

    class updateProgressTask extends TimerTask {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ CircleProgressBarZTE f17595c;

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            try {
                float f2 = this.f17595c.mIsReverse ? this.f17595c.mProgress - this.f17595c.mProgressInterval : this.f17595c.mProgress + this.f17595c.mProgressInterval;
                this.f17595c.setProgress(f2);
                Log.d(CircleProgressBarZTE.TAG, "run: update Progress Task mProgress=" + this.f17595c.getCurrentProgress() + ", progress=" + f2 + ", mMaxProgress=" + this.f17595c.mMaxProgress + ", mProgressInterval=" + this.f17595c.mProgressInterval + ", mIsReverse=" + this.f17595c.mIsReverse);
                if (!this.f17595c.mIsReverse && this.f17595c.getCurrentProgress() >= this.f17595c.mMaxProgress) {
                    CircleProgressBarZTE circleProgressBarZTE = this.f17595c;
                    circleProgressBarZTE.setProgress(circleProgressBarZTE.mMaxProgress);
                    this.f17595c.f();
                } else {
                    if (!this.f17595c.mIsReverse || this.f17595c.getCurrentProgress() > 0.0f) {
                        return;
                    }
                    this.f17595c.setProgress(0.0f);
                    this.f17595c.f();
                }
            } catch (Exception e2) {
                Log.e(CircleProgressBarZTE.TAG, "run: update Progress Task error, e = ", e2);
            }
        }
    }

    public CircleProgressBarZTE(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void e() {
        Paint paint = new Paint();
        this.mBgPaint = paint;
        paint.setColor(this.mBgColor);
        Paint paint2 = this.mBgPaint;
        Paint.Style style = Paint.Style.STROKE;
        paint2.setStyle(style);
        this.mBgPaint.setStrokeWidth(this.mStrokeWidth);
        this.mBgPaint.setAntiAlias(true);
        Paint paint3 = this.mBgPaint;
        Paint.Cap cap = Paint.Cap.ROUND;
        paint3.setStrokeCap(cap);
        Paint paint4 = new Paint();
        this.mProgressPaint = paint4;
        paint4.setColor(this.mProgressColor);
        this.mProgressPaint.setStyle(style);
        this.mProgressPaint.setStrokeWidth(this.mStrokeWidth);
        this.mProgressPaint.setAntiAlias(true);
        this.mProgressPaint.setStrokeCap(cap);
        Paint paint5 = new Paint();
        this.mTextPaint = paint5;
        paint5.setColor(this.mTextColor);
        this.mTextPaint.setTextSize(this.mTextSize);
        this.mTextPaint.setAntiAlias(true);
        this.mTextPaint.setTextAlign(Paint.Align.CENTER);
        this.mRectF = new RectF();
        Log.d(TAG, "initPaints mProgressPaint=" + this.mProgressPaint + ", mBgPaint=" + this.mBgPaint);
    }

    public void f() {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = this.mExecutor;
        if (scheduledThreadPoolExecutor != null && !scheduledThreadPoolExecutor.isShutdown()) {
            this.mExecutor.shutdownNow();
            Log.i(TAG, "shut down mExecutor now.");
        }
        this.mExecutor = null;
    }

    public float getCurrentProgress() {
        return this.mProgress;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth() / 2;
        int height = getHeight() / 2;
        float min = Math.min(width, height) - (this.mStrokeWidth / 2.0f);
        float f2 = width;
        float f3 = height;
        this.mRectF.set(f2 - min, f3 - min, f2 + min, min + f3);
        canvas.drawArc(this.mRectF, 0.0f, 360.0f, false, this.mBgPaint);
        float f4 = (this.mProgress / this.mMaxProgress) * 360.0f;
        Log.d(TAG, "onDraw  mProgress=" + this.mProgress + ", progressAngle=" + f4 + ", mBgPaint=" + this.mBgPaint + ", mProgressPaint=" + this.mProgressPaint);
        if (f4 > 0.0f) {
            canvas.drawArc(this.mRectF, 270.0f, f4, false, this.mProgressPaint);
        }
        if (this.mShowText) {
            String str = ((int) this.mProgress) + "%";
            Paint.FontMetrics fontMetrics = this.mTextPaint.getFontMetrics();
            canvas.drawText(str, f2, f3 - ((fontMetrics.top + fontMetrics.bottom) / 2.0f), this.mTextPaint);
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        int min = Math.min(View.MeasureSpec.getSize(i2), View.MeasureSpec.getSize(i3));
        setMeasuredDimension(min, min);
    }

    public void setBgColor(int i2) {
        Log.d(TAG, "setBgColor color=" + i2);
        this.mBgColor = i2;
        e();
        invalidate();
    }

    public void setMaxProgress(int i2) {
        if (i2 > 0) {
            this.mMaxProgress = i2;
            invalidate();
        }
    }

    public void setProgress(int i2) {
        setProgress(i2);
    }

    public void setProgressColor(int i2) {
        Log.d(TAG, "setProgressColor color=" + i2);
        this.mProgressColor = i2;
        e();
        invalidate();
    }

    public void setReverse(boolean z) {
        this.mIsReverse = z;
    }

    public void setShowText(boolean z) {
        this.mShowText = z;
        invalidate();
    }

    public CircleProgressBarZTE(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mMaxProgress = 100;
        this.mProgress = 0.0f;
        int i3 = DEFAULT_PROGRESS_COLOR;
        this.mProgressColor = i3;
        int i4 = DEFAULT_BACKGROUND_COLOR;
        this.mBgColor = i4;
        this.mStrokeWidth = DEFAULT_STROKE_WIDTH;
        this.mTextSize = DEFAULT_TEXT_SIZE;
        this.mTextColor = DEFAULT_TEXT_COLOR;
        this.mShowText = false;
        this.mIsReverse = false;
        this.mTiming = DEFAULT_TIME;
        this.mProgressInterval = 1.0f;
        this.INTERVAL = 100;
        this.mExecutor = null;
        setForceDarkAllowed(false);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CircleProgressBarZTE);
        this.mMaxProgress = obtainStyledAttributes.getInt(R.styleable.CircleProgressBarZTE_cpb_max, 100);
        this.mProgress = obtainStyledAttributes.getInt(R.styleable.CircleProgressBarZTE_cpb_progress, 0);
        this.mProgressColor = obtainStyledAttributes.getColor(R.styleable.CircleProgressBarZTE_cpb_progressColor, i3);
        this.mBgColor = obtainStyledAttributes.getColor(R.styleable.CircleProgressBarZTE_cpb_bgColor, i4);
        this.mStrokeWidth = obtainStyledAttributes.getDimension(R.styleable.CircleProgressBarZTE_cpb_strokeWidth, DEFAULT_STROKE_WIDTH);
        this.mTextSize = obtainStyledAttributes.getDimension(R.styleable.CircleProgressBarZTE_cpb_textSize, DEFAULT_TEXT_SIZE);
        this.mTextColor = obtainStyledAttributes.getColor(R.styleable.CircleProgressBarZTE_cpb_textColor, DEFAULT_TEXT_COLOR);
        this.mShowText = obtainStyledAttributes.getBoolean(R.styleable.CircleProgressBarZTE_cpb_showText, false);
        this.mIsReverse = obtainStyledAttributes.getBoolean(R.styleable.CircleProgressBarZTE_cpb_reverse, false);
        this.mTiming = obtainStyledAttributes.getInt(R.styleable.CircleProgressBarZTE_cpb_time, DEFAULT_TIME);
        obtainStyledAttributes.recycle();
        Log.d(TAG, "CircleProgressBarZTE out. mProgress=" + this.mProgress + ", mMaxProgress=" + this.mMaxProgress + ", mIsReverse=" + this.mIsReverse + ", mTiming=" + this.mTiming);
        e();
    }

    public void setProgress(float f2) {
        if (f2 < 0.0f) {
            f2 = 0.0f;
        } else {
            int i2 = this.mMaxProgress;
            if (f2 > i2) {
                f2 = i2;
            }
        }
        this.mProgress = f2;
        invalidate();
    }
}
