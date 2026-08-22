package cn.nubia.gamelauncher.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PointF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import cn.nubia.gamelauncher.util.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class WinRateChartView extends View {
    private static final String RATE_0 = "0%";
    private static final String RATE_100 = "100%";
    private static final String RATE_50 = "50%";
    private static final String TAG = "WinRateChartView";
    private Context mContext;
    private int mHeight;
    private int mLineX_left;
    private int mLineX_rigth;
    private int mLineY_0;
    private int mLineY_100;
    private int mLineY_50;
    private Paint mPaint;
    private PathEffect mPathEffect;
    private Paint mRateLinePaint;
    private List<Float> mRateLists;
    private int mShadowColorBegin;
    private int mShadowColorEnd;
    private Paint mShadowPaint;
    private Paint mTextPaint;
    private int mTextX_0;
    private int mTextX_100;
    private int mTextX_50;
    private int mTextY_0;
    private int mTextY_100;
    private int mTextY_50;
    private int mTotalX;
    private int mTotalY;
    private int mWidth;

    public WinRateChartView(Context context) {
        this(context, null);
    }

    public WinRateChartView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        this.mRateLists = new ArrayList();
        Paint paint = new Paint(1);
        this.mPaint = paint;
        paint.setColor(Color.parseColor("#29FFFFFF"));
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeWidth(2.0f);
        DashPathEffect dashPathEffect = new DashPathEffect(new float[]{10.0f, 5.0f}, 0.0f);
        this.mPathEffect = dashPathEffect;
        this.mPaint.setPathEffect(dashPathEffect);
        Paint paint2 = new Paint(1);
        this.mTextPaint = paint2;
        paint2.setColor(Color.parseColor("#FFFFFFFF"));
        this.mTextPaint.setTextSize(22.0f);
        Paint paint3 = new Paint(1);
        this.mRateLinePaint = paint3;
        paint3.setColor(Color.parseColor("#FFFF6E44"));
        this.mRateLinePaint.setStyle(Paint.Style.STROKE);
        this.mRateLinePaint.setStrokeWidth(4.0f);
        Paint paint4 = new Paint(1);
        this.mShadowPaint = paint4;
        paint4.setColor(Color.parseColor("#FFFFFFFF"));
        this.mShadowColorBegin = Color.parseColor("#65FF5B4D");
        this.mShadowColorEnd = Color.parseColor("#00FF5B4D");
    }

    public WinRateChartView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    private void drawRateLine(Canvas canvas) {
        List<Float> list = this.mRateLists;
        if (list == null || list.size() <= 1) {
            return;
        }
        Path path = new Path();
        Path path2 = new Path();
        float size = this.mTotalX / (this.mRateLists.size() - 1);
        LogUtil.i(TAG, "step = " + size + " mTotalX = " + this.mTotalX);
        float f = this.mLineX_left;
        float f2 = this.mLineY_50;
        int size2 = this.mRateLists.size();
        PointF[] pointFArr = new PointF[size2];
        float f3 = f;
        float f4 = f2;
        for (int i = 0; i < this.mRateLists.size(); i++) {
            f3 = this.mLineX_left + (i * size);
            f4 = ((1.0f - this.mRateLists.get(i).floatValue()) * this.mTotalY) + this.mLineY_100;
            pointFArr[i] = new PointF(f3, f4);
        }
        for (int i2 = 0; i2 < size2; i2++) {
            if (i2 == 0) {
                path.moveTo(pointFArr[i2].x, pointFArr[i2].y);
                path2.moveTo(pointFArr[i2].x, pointFArr[i2].y);
            } else {
                path.lineTo(pointFArr[i2].x, pointFArr[i2].y);
                path2.lineTo(pointFArr[i2].x, pointFArr[i2].y);
            }
        }
        path2.lineTo(f3, this.mLineY_0);
        path2.lineTo(this.mLineX_left, this.mLineY_0);
        path2.close();
        this.mShadowPaint.setShader(new LinearGradient(f3, f4, f3, this.mLineY_0, this.mShadowColorBegin, this.mShadowColorEnd, Shader.TileMode.CLAMP));
        canvas.drawPath(path, this.mRateLinePaint);
        canvas.drawPath(path2, this.mShadowPaint);
    }

    private void drawTextAndLine(Canvas canvas) {
        Path path = new Path();
        path.moveTo(this.mLineX_left, this.mLineY_100);
        path.lineTo(this.mLineX_rigth, this.mLineY_100);
        canvas.drawPath(path, this.mPaint);
        canvas.drawText(RATE_100, this.mTextX_100, this.mTextY_100, this.mTextPaint);
        path.reset();
        path.moveTo(this.mLineX_left, this.mLineY_50);
        path.lineTo(this.mLineX_rigth, this.mLineY_50);
        canvas.drawPath(path, this.mPaint);
        canvas.drawText(RATE_50, this.mTextX_50, this.mTextY_50, this.mTextPaint);
        path.reset();
        path.moveTo(this.mLineX_left, this.mLineY_0);
        path.lineTo(this.mLineX_rigth, this.mLineY_0);
        canvas.drawPath(path, this.mPaint);
        canvas.drawText(RATE_0, this.mTextX_0, this.mTextY_0, this.mTextPaint);
        path.reset();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawTextAndLine(canvas);
        drawRateLine(canvas);
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.mWidth = getWidth();
        this.mHeight = getHeight();
        this.mTextX_100 = (int) ((this.mWidth - 13) - this.mTextPaint.measureText(RATE_100));
        int abs = (int) (18 + Math.abs(this.mTextPaint.descent() - this.mTextPaint.ascent()));
        this.mTextY_100 = abs;
        this.mLineY_100 = (int) (abs - (Math.abs(this.mTextPaint.descent() - this.mTextPaint.ascent()) / 2.0f));
        this.mTextX_0 = (int) ((this.mWidth - 13) - this.mTextPaint.measureText(RATE_0));
        int i5 = this.mHeight - 40;
        this.mTextY_0 = i5;
        this.mLineY_0 = i5;
        this.mLineY_50 = ((int) ((i5 - r2) / 2.0f)) + this.mLineY_100;
        this.mTextX_50 = (int) ((this.mWidth - 13) - this.mTextPaint.measureText(RATE_50));
        this.mTextY_50 = (int) (this.mLineY_50 + (Math.abs(this.mTextPaint.descent() - this.mTextPaint.ascent()) / 2.0f));
        this.mTotalY = this.mLineY_0 - this.mLineY_100;
        this.mLineX_left = 34;
        int i6 = this.mTextX_100;
        this.mLineX_rigth = i6 - 10;
        this.mTotalX = i6 - 44;
    }

    public void setRateList(List<Float> list, boolean z) {
        if (list != null) {
            this.mRateLists.clear();
        }
        this.mRateLists.addAll(list);
        this.mRateLinePaint.setColor(Color.parseColor(z ? "#FF5B4D" : "#9EB5FF"));
        this.mShadowColorBegin = Color.parseColor(z ? "#65FF5B4D" : "#659EB5FF");
        this.mShadowColorEnd = Color.parseColor(z ? "#00FF5B4D" : "#009EB5FF");
        postInvalidate();
    }
}
