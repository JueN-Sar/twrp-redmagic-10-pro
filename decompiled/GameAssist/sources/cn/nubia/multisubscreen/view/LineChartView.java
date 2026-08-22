package cn.nubia.multisubscreen.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import cn.nubia.gameassist.R;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class LineChartView extends View {
    private int mAxisMarginEnd;
    private int mAxisMarginStart;
    private int mAxisWidth;
    private int mCoordinateBackgroundColor;
    private int mCoordinateMarginBottom;
    private int mCoordinateMarginEnd;
    private int mCoordinateMarginStart;
    private int mCoordinateMarginTop;
    protected List<Float> mDataList;
    private int mDefaultMaxYAxis;
    private float[] mGap;
    protected int mGradientEndColor;
    protected Paint mGradientPaint;
    protected Path mGradientPath;
    protected int mGradientStartColor;
    protected boolean mIsDynamicTuning;
    private int mLegendColor;
    private float mLegendCorner;
    private int mLegendHeight;
    private int mLegendMarginBottom;
    private int mLegendMarginRight;
    private String mLegendText;
    private int mLegendWidth;
    protected Path mLinePath;
    protected int mLineWidth;
    protected int mMainColor;
    private int mMaxYAxis;
    protected float mMaxYValue;
    protected Paint mPaint;
    private float mTextHeight;
    private float mTextOffset;
    private Paint mTextPaint;
    protected int mViewPortBottom;
    protected int mViewPortEnd;
    protected int mViewPortStart;
    protected int mViewPortTop;
    private int mXAxisMax;
    private int mXAxisNum;
    private int mXAxisTextMarginTop;
    protected int mXFactor;
    protected String mXUnit;
    private int mYAxisTextMarginEnd;
    private String[] mYAxisValues;
    protected int mYFactor;
    protected String mYUnit;

    public LineChartView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mYFactor = 1;
        this.mXFactor = 1;
        this.mXAxisMax = 7;
        this.mYAxisValues = new String[]{"0", "50", "100"};
        this.mXAxisNum = 10;
        g(attributeSet);
    }

    private void a(float f2) {
        int ceil = (int) Math.ceil(f2 / 2.0f);
        int i2 = this.mDefaultMaxYAxis;
        if (ceil < i2 / 2) {
            ceil = i2 / 2;
        }
        int i3 = ceil * 2;
        this.mMaxYAxis = i3;
        String str = this.mYUnit;
        if (str == null) {
            this.mYAxisValues[1] = String.valueOf(ceil);
            this.mYAxisValues[2] = String.valueOf(i3);
            return;
        }
        if ("%".equals(str)) {
            this.mYAxisValues[1] = ceil + this.mYUnit;
        } else {
            this.mYAxisValues[1] = String.valueOf(ceil);
        }
        this.mYAxisValues[2] = i3 + this.mYUnit;
    }

    private boolean b(float f2, float f3) {
        float f4 = f2 - f3;
        return f4 < 0.1f && f4 > -0.1f;
    }

    private void c(Canvas canvas) {
        int save = canvas.save();
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setStrokeWidth(this.mAxisWidth);
        this.mPaint.setColor(369098751);
        this.mTextPaint.setColor(this.mMainColor);
        this.mTextPaint.setFakeBoldText(false);
        this.mTextPaint.setTextAlign(Paint.Align.RIGHT);
        int i2 = this.mViewPortBottom;
        float f2 = this.mViewPortTop;
        canvas.drawLine(this.mViewPortStart, f2, this.mViewPortEnd, f2, this.mPaint);
        canvas.drawText(this.mYAxisValues[2], this.mViewPortStart - this.mYAxisTextMarginEnd, f2 + (this.mTextOffset / 2.0f), this.mTextPaint);
        float f3 = (((i2 - r2) * 1.0f) / 2.0f) + this.mViewPortTop;
        canvas.drawLine(this.mViewPortStart, f3, this.mViewPortEnd, f3, this.mPaint);
        canvas.drawText(this.mYAxisValues[1], this.mViewPortStart - this.mYAxisTextMarginEnd, f3 + (this.mTextOffset / 2.0f), this.mTextPaint);
        float f4 = this.mViewPortBottom;
        canvas.drawLine(this.mViewPortStart, f4, this.mViewPortEnd, f4, this.mPaint);
        canvas.drawText(this.mYAxisValues[0], this.mViewPortStart - this.mYAxisTextMarginEnd, f4 + (this.mTextOffset / 2.0f), this.mTextPaint);
        this.mPaint.setColor(this.mCoordinateBackgroundColor);
        canvas.drawRect(this.mViewPortStart, this.mViewPortTop, this.mViewPortEnd, this.mViewPortBottom, this.mPaint);
        this.mTextPaint.setColor(-1);
        this.mTextPaint.setTextAlign(Paint.Align.CENTER);
        int i3 = this.mViewPortEnd;
        int i4 = this.mViewPortStart;
        float f5 = (((i3 - i4) - this.mAxisMarginStart) - this.mAxisMarginEnd) / this.mXAxisNum;
        this.mGap[0] = f5;
        canvas.translate(i4 + r3, this.mViewPortBottom + this.mXAxisTextMarginTop);
        for (int i5 = 0; i5 <= this.mXAxisNum; i5++) {
            canvas.drawText(String.valueOf(i5), 0.0f, (this.mTextHeight / 2.0f) + (this.mTextOffset / 2.0f), this.mTextPaint);
            canvas.translate(f5, 0.0f);
        }
        canvas.restoreToCount(save);
        this.mPaint.setColor(this.mLegendColor);
        this.mTextPaint.setFakeBoldText(true);
        float f6 = (this.mViewPortEnd - this.mLegendMarginRight) - this.mLegendWidth;
        int i6 = this.mViewPortTop;
        int i7 = this.mLegendMarginBottom;
        float f7 = this.mLegendCorner;
        canvas.drawRoundRect(f6, (i6 - i7) - this.mLegendHeight, r1 - r2, i6 - i7, f7, f7, this.mPaint);
        canvas.drawText(this.mLegendText, (this.mViewPortEnd - this.mLegendMarginRight) - (this.mLegendWidth / 2), ((this.mViewPortTop - this.mLegendMarginBottom) - (this.mLegendHeight / 2)) + (this.mTextOffset / 2.0f), this.mTextPaint);
    }

    private void d(Canvas canvas) {
        List<Float> list = this.mDataList;
        if (list == null || list.size() == 0) {
            return;
        }
        int save = canvas.save();
        canvas.translate(this.mViewPortStart + this.mAxisMarginStart, this.mViewPortTop);
        h(canvas);
        canvas.restoreToCount(save);
    }

    private void g(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.LineChartView);
        try {
            this.mGradientStartColor = obtainStyledAttributes.getColor(R.styleable.LineChartView_gradient_start_color, 0);
            this.mGradientEndColor = obtainStyledAttributes.getColor(R.styleable.LineChartView_gradient_end_color, 0);
            this.mCoordinateBackgroundColor = obtainStyledAttributes.getColor(R.styleable.LineChartView_coordinate_background_color, 0);
            this.mMainColor = obtainStyledAttributes.getColor(R.styleable.LineChartView_chart_main_color, 0);
            this.mLegendColor = obtainStyledAttributes.getColor(R.styleable.LineChartView_legend_bg_color, 0);
            this.mLegendText = obtainStyledAttributes.getString(R.styleable.LineChartView_legend_text);
            this.mCoordinateMarginStart = obtainStyledAttributes.getDimensionPixelSize(R.styleable.LineChartView_coordinate_margin_start, getResources().getDimensionPixelSize(R.dimen.sink_chart_coordinate_margin_start));
            this.mCoordinateMarginTop = obtainStyledAttributes.getDimensionPixelSize(R.styleable.LineChartView_coordinate_margin_top, getResources().getDimensionPixelSize(R.dimen.sink_chart_coordinate_margin_top));
            this.mCoordinateMarginEnd = obtainStyledAttributes.getDimensionPixelSize(R.styleable.LineChartView_coordinate_margin_end, getResources().getDimensionPixelSize(R.dimen.sink_chart_coordinate_margin_end));
            this.mCoordinateMarginBottom = obtainStyledAttributes.getDimensionPixelSize(R.styleable.LineChartView_coordinate_margin_bottom, getResources().getDimensionPixelSize(R.dimen.sink_chart_coordinate_margin_bottom));
            this.mMaxYAxis = obtainStyledAttributes.getInt(R.styleable.LineChartView_y_axis_max, 100);
            this.mYUnit = obtainStyledAttributes.getString(R.styleable.LineChartView_y_axis_unit);
            int i2 = this.mMaxYAxis;
            float f2 = i2;
            this.mMaxYValue = f2;
            this.mDefaultMaxYAxis = i2;
            a(f2);
            obtainStyledAttributes.recycle();
            this.mAxisWidth = getResources().getDimensionPixelSize(R.dimen.sink_chart_axis_width);
            this.mLegendWidth = getResources().getDimensionPixelSize(R.dimen.sink_chart_legend_width);
            this.mLegendHeight = getResources().getDimensionPixelSize(R.dimen.sink_chart_legend_height);
            this.mLegendMarginRight = getResources().getDimensionPixelSize(R.dimen.sink_chart_legend_margin_r);
            this.mLegendMarginBottom = getResources().getDimensionPixelSize(R.dimen.sink_chart_legend_margin_b);
            this.mLegendCorner = getResources().getDimension(R.dimen.sink_chart_legend_round_corner);
            this.mYAxisTextMarginEnd = getResources().getDimensionPixelSize(R.dimen.sink_chart_y_axis_text_margin_end);
            this.mAxisMarginStart = getResources().getDimensionPixelSize(R.dimen.sink_chart_axis_margin_start);
            this.mAxisMarginEnd = getResources().getDimensionPixelSize(R.dimen.sink_chart_axis_margin_end);
            this.mXAxisTextMarginTop = getResources().getDimensionPixelSize(R.dimen.sink_chart_x_axis_text_margin_top);
            this.mLineWidth = getResources().getDimensionPixelSize(R.dimen.sink_chart_line_width);
            this.mPaint = new Paint(1);
            Paint paint = new Paint(1);
            this.mGradientPaint = paint;
            paint.setStyle(Paint.Style.FILL);
            Paint paint2 = new Paint(1);
            this.mTextPaint = paint2;
            paint2.setTextSize(getResources().getDimensionPixelSize(R.dimen.sink_chart_text_size));
            Paint.FontMetrics fontMetrics = this.mTextPaint.getFontMetrics();
            this.mTextOffset = Math.abs(fontMetrics.ascent) - (fontMetrics.descent / 2.0f);
            this.mTextHeight = fontMetrics.bottom - fontMetrics.top;
            this.mLinePath = new Path();
            this.mGradientPath = new Path();
            this.mGap = new float[]{0.0f, 0.0f};
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    private float getMaxYValue() {
        float floatValue = this.mDataList.get(0).floatValue();
        for (int i2 = 1; i2 < this.mDataList.size(); i2++) {
            if (this.mDataList.get(i2).floatValue() - floatValue > 0.001f) {
                floatValue = this.mDataList.get(i2).floatValue();
            }
        }
        return floatValue;
    }

    protected float e(int i2) {
        return ((i2 * 1.0f) / this.mXAxisMax) * (((this.mViewPortEnd - this.mViewPortStart) - this.mAxisMarginStart) - this.mAxisMarginEnd);
    }

    protected float f(float f2) {
        return (1.0f - (f2 / this.mMaxYAxis)) * (this.mViewPortBottom - this.mViewPortTop);
    }

    protected void h(Canvas canvas) {
        if (this.mDataList.size() == 1) {
            this.mPaint.setColor(this.mMainColor);
            this.mPaint.setStyle(Paint.Style.FILL);
            canvas.drawPoint(0.0f, f(this.mDataList.get(0).floatValue()), this.mPaint);
            this.mGradientPaint.setShader(new LinearGradient(0.0f, f(this.mMaxYValue), 0.0f, this.mViewPortBottom - this.mViewPortTop, this.mGradientStartColor, this.mGradientEndColor, Shader.TileMode.CLAMP));
            canvas.drawLine(0.0f, f(this.mDataList.get(0).floatValue()), 0.0f, this.mViewPortBottom - this.mViewPortTop, this.mGradientPaint);
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
        for (int i2 = 1; i2 < this.mDataList.size(); i2++) {
            this.mLinePath.lineTo(e(i2), f(this.mDataList.get(i2).floatValue()));
            this.mGradientPath.lineTo(e(i2), f(this.mDataList.get(i2).floatValue()));
        }
        this.mGradientPath.lineTo(e(this.mDataList.size() - 1), this.mViewPortBottom - this.mViewPortTop);
        this.mGradientPath.close();
        canvas.drawPath(this.mLinePath, this.mPaint);
        this.mGradientPaint.setShader(new LinearGradient(0.0f, f(this.mMaxYValue), 0.0f, this.mViewPortBottom - this.mViewPortTop, this.mGradientStartColor, this.mGradientEndColor, Shader.TileMode.CLAMP));
        canvas.drawPath(this.mGradientPath, this.mGradientPaint);
    }

    public void i(String str, int i2) {
        this.mXUnit = str;
        this.mXFactor = i2;
    }

    public void j(String str, int i2) {
        this.mYUnit = str;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        c(canvas);
        d(canvas);
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        this.mViewPortStart = this.mCoordinateMarginStart;
        this.mViewPortTop = this.mCoordinateMarginTop;
        this.mViewPortEnd = i2 - this.mCoordinateMarginEnd;
        this.mViewPortBottom = i3 - this.mCoordinateMarginBottom;
    }

    public void setData(List<Float> list) {
        this.mDataList = list;
        if (list == null || list.size() == 0) {
            return;
        }
        float maxYValue = getMaxYValue();
        if (!this.mIsDynamicTuning) {
            if (!b(this.mMaxYValue, maxYValue)) {
                this.mMaxYValue = maxYValue;
            }
            int i2 = this.mMaxYAxis;
            if (i2 - maxYValue < 0.1f || (i2 != this.mDefaultMaxYAxis && i2 - maxYValue > 0.1f)) {
                a(maxYValue * 1.1f);
            }
        } else if (!b(this.mMaxYValue, maxYValue)) {
            this.mMaxYValue = maxYValue;
            if (maxYValue > 0.1f) {
                int i3 = this.mMaxYAxis;
                if (i3 - maxYValue < 0.1f || i3 > 5.0f * maxYValue) {
                    a(maxYValue * 2.0f);
                }
            }
        }
        invalidate();
    }

    public void setDefaultYAxisMax(int i2) {
        this.mDefaultMaxYAxis = i2;
    }

    public void setDynamicTuning(boolean z) {
        this.mIsDynamicTuning = z;
    }

    public void setMaxYAxis(float f2) {
        a(f2);
    }

    public void setXAxisMax(int i2) {
        this.mXAxisMax = i2;
    }

    @VisibleForTesting
    public void test() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Float.valueOf(((float) Math.random()) * this.mMaxYAxis));
        setData(arrayList);
    }

    public LineChartView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mYFactor = 1;
        this.mXFactor = 1;
        this.mXAxisMax = 7;
        this.mYAxisValues = new String[]{"0", "50", "100"};
        this.mXAxisNum = 10;
        g(attributeSet);
    }

    public LineChartView(Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.mYFactor = 1;
        this.mXFactor = 1;
        this.mXAxisMax = 7;
        this.mYAxisValues = new String[]{"0", "50", "100"};
        this.mXAxisNum = 10;
        g(attributeSet);
    }
}
