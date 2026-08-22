package com.zte.mifavor.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import com.google.android.gms.common.api.Api;
import com.zte.extres.R;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class DotsPageIndicatorZTE extends View {
    private static final float HANDLE_LEN_RATE = 2.0f;
    private static final float MV = 1.0f;
    private static final float SCALE_RATE = 0.3f;
    private static final String TAG = "DotsPageIndicatorZTE";
    private RectF ball1;
    private boolean isMoveOnTouch;
    private ArrayList<PageMarkerResources> mCirclePaths;
    private float mCrossWidth;
    private int mHollowColor;
    private Paint mHollowPaint;
    private float mHollowStrokeWidth;
    private boolean mIsRtl;
    private float mItemDivider;
    private int mMaxWindowSize;
    private int mPageCount;
    private int mPageWidth;
    private float mRadius;
    private PageMarkerResources mSlideCircle;
    private int mSolidColor;
    private Paint mSolidPaint;
    private int mStartScrollX;

    public enum CircleType {
        SOLID,
        HOLLOW,
        CROSS
    }

    public static class PageMarkerResources {

        /* renamed from: a, reason: collision with root package name */
        float[] f17612a;

        /* renamed from: b, reason: collision with root package name */
        float f17613b;

        /* renamed from: c, reason: collision with root package name */
        CircleType f17614c;

        public PageMarkerResources(CircleType circleType) {
            this.f17614c = circleType;
        }
    }

    public DotsPageIndicatorZTE(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mSolidPaint = new Paint();
        this.mHollowPaint = new Paint();
        this.mCirclePaths = new ArrayList<>();
        this.mMaxWindowSize = 50;
        this.ball1 = new RectF();
        this.mStartScrollX = Api.BaseClientBuilder.API_PRIORITY_OTHER;
        this.isMoveOnTouch = false;
        e(context);
    }

    private int a(int i2) {
        return i2;
    }

    private float b(float[] fArr, float[] fArr2) {
        float f2 = fArr[0] - fArr2[0];
        float f3 = fArr[1] - fArr2[1];
        return (float) Math.sqrt((f2 * f2) + (f3 * f3));
    }

    private float c(float[] fArr) {
        float f2 = fArr[0];
        float f3 = fArr[1];
        return (float) Math.sqrt((f2 * f2) + (f3 * f3));
    }

    private float[] d(float f2, float f3) {
        double d2 = f2;
        double d3 = f3;
        return new float[]{(float) (Math.cos(d2) * d3), (float) (Math.sin(d2) * d3)};
    }

    private void e(Context context) {
        this.mIsRtl = context.getResources().getConfiguration().getLayoutDirection() == 1;
        this.mSolidColor = context.getResources().getColor(R.color.mfv_common_cb_on);
        this.mHollowColor = context.getResources().getColor(R.color.mfv_common_cb_on);
        this.mRadius = context.getResources().getDimension(R.dimen.page_indicator_circle_radius);
        this.mItemDivider = context.getResources().getDimension(R.dimen.page_indicator_circle_divider_size);
        this.mHollowStrokeWidth = context.getResources().getDimension(R.dimen.page_indicator_stroke_width);
        this.mCrossWidth = context.getResources().getDimension(R.dimen.page_indicator_add_line_width);
        this.mSolidPaint.setStyle(Paint.Style.FILL);
        this.mHollowPaint.setStyle(Paint.Style.STROKE);
        this.mSolidPaint.setAntiAlias(true);
        this.mHollowPaint.setAntiAlias(true);
        this.mSolidPaint.setColor(this.mSolidColor);
        this.mHollowPaint.setColor(this.mHollowColor);
        this.mHollowPaint.setStrokeWidth(this.mHollowStrokeWidth);
        PageMarkerResources pageMarkerResources = new PageMarkerResources(CircleType.SOLID);
        this.mSlideCircle = pageMarkerResources;
        pageMarkerResources.f17613b = (this.mRadius / 4.0f) * 3.0f;
    }

    private void f(Canvas canvas, int i2, float f2, float f3, float f4) {
        float f5;
        float f6;
        float[] fArr;
        float f7;
        PageMarkerResources pageMarkerResources = this.mSlideCircle;
        PageMarkerResources pageMarkerResources2 = this.mCirclePaths.get(i2);
        RectF rectF = new RectF();
        float[] fArr2 = pageMarkerResources.f17612a;
        float f8 = fArr2[0];
        float f9 = pageMarkerResources.f17613b;
        float f10 = f8 - f9;
        rectF.left = f10;
        float f11 = fArr2[1] - f9;
        rectF.top = f11;
        rectF.right = f10 + (f9 * HANDLE_LEN_RATE);
        rectF.bottom = f11 + (f9 * HANDLE_LEN_RATE);
        RectF rectF2 = new RectF();
        float[] fArr3 = pageMarkerResources2.f17612a;
        float f12 = fArr3[0];
        float f13 = pageMarkerResources2.f17613b;
        float f14 = f12 - f13;
        rectF2.left = f14;
        float f15 = fArr3[1] - f13;
        rectF2.top = f15;
        rectF2.right = f14 + (f13 * HANDLE_LEN_RATE);
        rectF2.bottom = f15 + (f13 * HANDLE_LEN_RATE);
        float[] fArr4 = {rectF.centerX(), rectF.centerY()};
        float[] fArr5 = {rectF2.centerX(), rectF2.centerY()};
        float b2 = b(fArr4, fArr5);
        float width = rectF.width() / HANDLE_LEN_RATE;
        float width2 = rectF2.width() / HANDLE_LEN_RATE;
        if (b2 > f4) {
            CircleType circleType = pageMarkerResources2.f17614c;
            if (circleType == CircleType.HOLLOW) {
                canvas.drawCircle(rectF2.centerX(), rectF2.centerY(), pageMarkerResources2.f17613b, this.mHollowPaint);
            } else if (circleType == CircleType.CROSS) {
                f5 = width;
                canvas.drawRect(rectF2.centerX() - (this.mCrossWidth / HANDLE_LEN_RATE), rectF2.centerY() - this.mRadius, (this.mCrossWidth / HANDLE_LEN_RATE) + rectF2.centerX(), this.mRadius + rectF2.centerY(), this.mSolidPaint);
                canvas.drawRect(rectF2.centerX() - this.mRadius, rectF2.centerY() - (this.mCrossWidth / HANDLE_LEN_RATE), this.mRadius + rectF2.centerX(), (this.mCrossWidth / HANDLE_LEN_RATE) + rectF2.centerY(), this.mSolidPaint);
                f6 = width2;
            }
            f5 = width;
            f6 = width2;
        } else {
            f5 = width;
            f6 = width2 * (((MV - (b2 / f4)) * SCALE_RATE) + MV);
            canvas.drawCircle(rectF2.centerX(), rectF2.centerY(), f6, this.mSolidPaint);
        }
        float f16 = 0.0f;
        if (f5 == 0.0f || f6 == 0.0f || b2 > f4) {
            return;
        }
        if (b2 <= Math.abs(f5 - f6)) {
            return;
        }
        float f17 = f5 + f6;
        if (b2 < f17) {
            float f18 = f5 * f5;
            float f19 = b2 * b2;
            float f20 = f6 * f6;
            fArr = fArr4;
            float acos = (float) Math.acos(((f18 + f19) - f20) / ((f5 * HANDLE_LEN_RATE) * b2));
            f7 = (float) Math.acos(((f20 + f19) - f18) / ((f6 * HANDLE_LEN_RATE) * b2));
            f16 = acos;
        } else {
            fArr = fArr4;
            f7 = 0.0f;
        }
        float[] fArr6 = {fArr5[0] - fArr[0], fArr5[1] - fArr[1]};
        float f21 = f5;
        float atan2 = (float) Math.atan2(fArr6[1], fArr6[0]);
        float acos2 = (float) Math.acos(r6 / b2);
        float f22 = (acos2 - f16) * f2;
        float f23 = atan2 + f16 + f22;
        float f24 = (atan2 - f16) - f22;
        double d2 = atan2;
        double d3 = f7;
        double d4 = ((3.141592653589793d - d3) - acos2) * f2;
        float f25 = (float) (((d2 + 3.141592653589793d) - d3) - d4);
        float f26 = (float) ((d2 - 3.141592653589793d) + d3 + d4);
        float[] d5 = d(f23, f21);
        float[] d6 = d(f24, f21);
        float[] d7 = d(f25, f6);
        float[] d8 = d(f26, f6);
        float f27 = d5[0];
        float f28 = fArr[0];
        float f29 = d5[1];
        float f30 = fArr[1];
        float[] fArr7 = {f27 + f28, f29 + f30};
        float[] fArr8 = {d6[0] + f28, d6[1] + f30};
        float f31 = d7[0];
        float f32 = fArr5[0];
        float f33 = d7[1];
        float f34 = fArr5[1];
        float[] fArr9 = {f31 + f32, f33 + f34};
        float[] fArr10 = {d8[0] + f32, d8[1] + f34};
        float min = Math.min(f2 * f3, c(new float[]{fArr7[0] - fArr9[0], fArr7[1] - fArr9[1]}) / f17) * Math.min(MV, (b2 * HANDLE_LEN_RATE) / f17);
        float f35 = f21 * min;
        float f36 = f6 * min;
        float[] d9 = d(f23 - 1.5707964f, f35);
        float[] d10 = d(f25 + 1.5707964f, f36);
        float[] d11 = d(f26 - 1.5707964f, f36);
        float[] d12 = d(f24 + 1.5707964f, f35);
        Path path = new Path();
        path.moveTo(fArr7[0], fArr7[1]);
        float f37 = fArr7[0] + d9[0];
        float f38 = fArr7[1] + d9[1];
        float f39 = fArr9[0];
        float f40 = f39 + d10[0];
        float f41 = fArr9[1];
        path.cubicTo(f37, f38, f40, f41 + d10[1], f39, f41);
        path.lineTo(fArr10[0], fArr10[1]);
        float f42 = fArr10[0] + d11[0];
        float f43 = fArr10[1] + d11[1];
        float f44 = fArr8[0];
        float f45 = f44 + d12[0];
        float f46 = fArr8[1];
        path.cubicTo(f42, f43, f45, f46 + d12[1], f44, f46);
        path.lineTo(fArr7[0], fArr7[1]);
        path.close();
        canvas.drawPath(path, this.mSolidPaint);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        float[] fArr;
        super.onDraw(canvas);
        PageMarkerResources pageMarkerResources = this.mSlideCircle;
        if (pageMarkerResources == null || (fArr = pageMarkerResources.f17612a) == null) {
            return;
        }
        RectF rectF = this.ball1;
        float f2 = fArr[0];
        float f3 = pageMarkerResources.f17613b;
        float f4 = f2 - f3;
        rectF.left = f4;
        float f5 = fArr[1] - f3;
        rectF.top = f5;
        rectF.right = f4 + (f3 * HANDLE_LEN_RATE);
        rectF.bottom = f5 + (f3 * HANDLE_LEN_RATE);
        canvas.drawCircle(rectF.centerX(), this.ball1.centerY(), this.mSlideCircle.f17613b, this.mSolidPaint);
        int size = this.mCirclePaths.size();
        for (int i2 = 0; i2 < size; i2++) {
            f(canvas, i2, MV, HANDLE_LEN_RATE, this.mRadius * HANDLE_LEN_RATE);
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        Log.d(TAG, "DotsPageIndicatorZTE onMeasure");
        float size = this.mCirclePaths.size();
        float f2 = this.mRadius;
        float f3 = (this.mHollowStrokeWidth + f2) * HANDLE_LEN_RATE;
        float f4 = this.mItemDivider;
        setMeasuredDimension(View.resolveSizeAndState((int) (((size * (f3 + f4)) - f4) + (f2 * SCALE_RATE)), i2, 0), View.resolveSizeAndState((int) (this.mRadius * 4.0f), i3, 0));
    }

    public void setActiveMarker(int i2) {
        Log.d(TAG, "setActiveMarker index:" + i2);
        int a2 = a(i2);
        if (this.mCirclePaths.size() <= 0 || a2 >= this.mCirclePaths.size()) {
            return;
        }
        PageMarkerResources pageMarkerResources = this.mCirclePaths.get(a2);
        PageMarkerResources pageMarkerResources2 = this.mSlideCircle;
        float[] fArr = pageMarkerResources2.f17612a;
        if (fArr == null) {
            float[] fArr2 = pageMarkerResources.f17612a;
            pageMarkerResources2.f17612a = new float[]{fArr2[0], fArr2[1]};
        } else {
            float[] fArr3 = pageMarkerResources.f17612a;
            fArr[0] = fArr3[0];
            fArr[1] = fArr3[1];
        }
        invalidate();
    }

    public void setPageCount(int i2) {
        this.mPageCount = i2;
    }

    public void setPageWidth(int i2) {
        this.mPageWidth = i2;
    }
}
