package cn.nubia.multisubscreen.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import androidx.annotation.Nullable;

/* loaded from: classes.dex */
public class StepChartView extends LineChartView {
    public StepChartView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // cn.nubia.multisubscreen.view.LineChartView
    protected void h(Canvas canvas) {
        if (this.mDataList.size() == 1) {
            this.mPaint.setColor(this.mMainColor);
            this.mPaint.setStyle(Paint.Style.FILL);
            canvas.drawLine(0.0f, f(this.mDataList.get(0).floatValue()), e(this.mXFactor - 1), f(this.mDataList.get(0).floatValue()), this.mPaint);
            this.mGradientPaint.setShader(new LinearGradient(0.0f, f(this.mMaxYValue), 0.0f, this.mViewPortBottom - this.mViewPortTop, this.mGradientStartColor, this.mGradientEndColor, Shader.TileMode.CLAMP));
            this.mGradientPath.reset();
            this.mGradientPath.moveTo(0.0f, this.mViewPortBottom - this.mViewPortTop);
            this.mGradientPath.lineTo(0.0f, f(this.mDataList.get(0).floatValue()));
            this.mGradientPath.lineTo(e(this.mXFactor - 1), f(this.mDataList.get(0).floatValue()));
            this.mGradientPath.lineTo(e(this.mXFactor - 1), this.mViewPortBottom - this.mViewPortTop);
            this.mGradientPath.close();
            canvas.drawPath(this.mGradientPath, this.mGradientPaint);
            return;
        }
        this.mPaint.setStrokeWidth(this.mLineWidth);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setColor(this.mMainColor);
        this.mLinePath.reset();
        this.mGradientPath.reset();
        this.mLinePath.moveTo(0.0f, f(this.mDataList.get(0).floatValue()));
        this.mGradientPath.moveTo(0.0f, this.mViewPortBottom - this.mViewPortTop);
        this.mGradientPath.lineTo(0.0f, f(this.mDataList.get(0).floatValue()));
        int i2 = 1;
        while (i2 < this.mDataList.size()) {
            int i3 = i2 - 1;
            this.mLinePath.lineTo(e((this.mXFactor * i2) - 1), f(this.mDataList.get(i3).floatValue()));
            this.mLinePath.lineTo(e((this.mXFactor * i2) - 1), f(this.mDataList.get(i2).floatValue()));
            int i4 = i2 + 1;
            this.mLinePath.lineTo(e((this.mXFactor * i4) - 1), f(this.mDataList.get(i2).floatValue()));
            this.mGradientPath.lineTo(e((this.mXFactor * i2) - 1), f(this.mDataList.get(i3).floatValue()));
            this.mGradientPath.lineTo(e((this.mXFactor * i2) - 1), f(this.mDataList.get(i2).floatValue()));
            this.mGradientPath.lineTo(e((this.mXFactor * i4) - 1), f(this.mDataList.get(i2).floatValue()));
            i2 = i4;
        }
        this.mGradientPath.lineTo(e((this.mDataList.size() * this.mXFactor) - 1), this.mViewPortBottom - this.mViewPortTop);
        this.mGradientPath.close();
        canvas.drawPath(this.mLinePath, this.mPaint);
        this.mGradientPaint.setShader(new LinearGradient(0.0f, f(this.mMaxYValue), 0.0f, this.mViewPortBottom - this.mViewPortTop, this.mGradientStartColor, this.mGradientEndColor, Shader.TileMode.CLAMP));
        canvas.drawPath(this.mGradientPath, this.mGradientPaint);
    }

    @Override // cn.nubia.multisubscreen.view.LineChartView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public StepChartView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }

    public StepChartView(Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
    }
}
