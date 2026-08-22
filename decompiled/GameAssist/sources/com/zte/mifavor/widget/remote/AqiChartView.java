package com.zte.mifavor.widget.remote;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import com.zte.mifavor.widget.Utils;

/* loaded from: classes2.dex */
public class AqiChartView extends View {
    private final String TAG;
    private int[] aqiColor;
    private float[] aqlPosision;
    private String mAqiLevelDesc;
    private Integer mAqiValue;
    private float mMaxValue;
    private int mMinCircleColor;
    private int mMinRadio;
    private Paint mPaint;
    private RectF mRectF;
    private int mRingNormalColor;
    private float mRingWidth;
    private int mSelectRing;
    private int mViewCenterX;
    private int mViewCenterY;
    private ValueAnimator valueAnimator;

    public AqiChartView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void c(Canvas canvas) {
        Paint paint = new Paint(this.mPaint);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(this.mRingWidth);
        paint.setColor(getAirQualityColor());
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.rotate(-90.0f, this.mViewCenterX, this.mViewCenterY);
        canvas.drawArc(this.mRectF, 210.0f, this.mSelectRing, false, paint);
    }

    private void d(Canvas canvas) {
        Paint paint = new Paint(this.mPaint);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(this.mRingWidth);
        paint.setColor(this.mRingNormalColor);
        paint.setAlpha(51);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawArc(this.mRectF, 120.0f, 300.0f, false, paint);
    }

    private void e(int i2, int i3, long j2) {
        ValueAnimator ofInt = ValueAnimator.ofInt(i2, i3);
        this.valueAnimator = ofInt;
        ofInt.setDuration(j2);
        this.valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.zte.mifavor.widget.remote.AqiChartView.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int intValue = Integer.valueOf(String.valueOf(valueAnimator.getAnimatedValue())).intValue();
                AqiChartView aqiChartView = AqiChartView.this;
                aqiChartView.mSelectRing = (int) ((intValue / aqiChartView.mMaxValue) * 360.0f);
                AqiChartView.this.invalidate();
            }
        });
        this.valueAnimator.start();
    }

    private int getAirQualityColor() {
        return (this.mAqiValue.intValue() < 0 || this.mAqiValue.intValue() > 50) ? (this.mAqiValue.intValue() < 51 || this.mAqiValue.intValue() > 100) ? (this.mAqiValue.intValue() < 101 || this.mAqiValue.intValue() > 150) ? (this.mAqiValue.intValue() < 151 || this.mAqiValue.intValue() > 200) ? (this.mAqiValue.intValue() < 200 || this.mAqiValue.intValue() > 300) ? this.mAqiValue.intValue() >= 301 ? Color.parseColor("#904242") : Color.parseColor("#7CFFFFFF") : Color.parseColor("#A02AB5") : Color.parseColor("#C4253C") : Color.parseColor("#FFA63F") : Color.parseColor("#FFE274") : Color.parseColor("#42E754");
    }

    private int getAqiColor() {
        return (this.mAqiValue.intValue() < 0 || this.mAqiValue.intValue() > 50) ? (this.mAqiValue.intValue() < 51 || this.mAqiValue.intValue() > 100) ? (this.mAqiValue.intValue() < 101 || this.mAqiValue.intValue() > 150) ? (this.mAqiValue.intValue() < 151 || this.mAqiValue.intValue() > 200) ? (this.mAqiValue.intValue() < 200 || this.mAqiValue.intValue() > 300) ? this.mAqiValue.intValue() >= 301 ? Color.parseColor("#7E0123") : Color.parseColor("#7CFFFFFF") : Color.parseColor("#98004B") : Color.parseColor("#FE0000") : Color.parseColor("#FF7E00") : Color.parseColor("#FFFF00") : Color.parseColor("#01E400");
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.mAqiValue == null) {
            return;
        }
        this.mPaint.setColor(this.mMinCircleColor);
        canvas.drawCircle(this.mViewCenterX, this.mViewCenterY, this.mMinRadio, this.mPaint);
        d(canvas);
        c(canvas);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        this.mViewCenterX = measuredWidth / 2;
        this.mViewCenterY = measuredHeight / 2;
        int i6 = this.mViewCenterX;
        int i7 = this.mMinRadio;
        float f2 = this.mRingWidth;
        int i8 = this.mViewCenterY;
        this.mRectF = new RectF((i6 - i7) - (f2 / 2.0f), (i8 - i7) - (f2 / 2.0f), i6 + i7 + (f2 / 2.0f), i8 + i7 + (f2 / 2.0f));
    }

    public void setValue(int i2) {
        float f2 = i2;
        float f3 = this.mMaxValue;
        if (f2 > f3) {
            i2 = (int) f3;
        }
        e(0, i2, 1000L);
    }

    public AqiChartView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.TAG = "AqiChartView";
        this.aqiColor = new int[]{Color.parseColor("#01E400"), Color.parseColor("#FFFF00"), Color.parseColor("#FF7E00"), Color.parseColor("#FE0000"), Color.parseColor("#98004B"), Color.parseColor("#7E0123")};
        this.aqlPosision = new float[]{0.0f, 0.1f, 0.2f, 0.3f, 0.4f, 0.6f};
        this.mSelectRing = 0;
        this.mMinRadio = Utils.c(context, 53);
        this.mRingWidth = Utils.c(context, 6);
        this.mMinCircleColor = Color.parseColor("#00FFFFFF");
        this.mRingNormalColor = Color.parseColor("#0F000000");
        this.mSelectRing = 0;
        this.mMaxValue = 500.0f;
        Log.d("AqiChartView", "mMinRadio = " + this.mMinRadio + ", mRingWidth=" + this.mRingWidth + ", mMinCircleColor=" + this.mMinCircleColor + "mRingNormalColor = " + this.mRingNormalColor + "mSelectRing = " + this.mSelectRing);
        Paint paint = new Paint(1);
        this.mPaint = paint;
        paint.setAntiAlias(true);
    }
}
