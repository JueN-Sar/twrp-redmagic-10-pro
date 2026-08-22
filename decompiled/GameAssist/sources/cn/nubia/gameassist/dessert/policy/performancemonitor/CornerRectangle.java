package cn.nubia.gameassist.dessert.policy.performancemonitor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import cn.nubia.gameassist.R;

/* loaded from: classes.dex */
public class CornerRectangle extends View {
    private float mHeight;
    private float mLeft;
    private Paint mPaint;
    private float mRadius;
    private final RectF mRoundRect;
    private Paint mStrokePaint;
    private final float mStrokeWidth;
    private float mWidth;

    public CornerRectangle(Context context) {
        this(context, null);
    }

    private void b() {
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setColor(Color.parseColor("#FF222635"));
        Paint paint2 = new Paint();
        this.mStrokePaint = paint2;
        paint2.setColor(Color.parseColor("#FF545353"));
        this.mStrokePaint.setAntiAlias(true);
        this.mStrokePaint.setStrokeWidth(2.0f);
        this.mStrokePaint.setStyle(Paint.Style.STROKE);
        this.mWidth = getResources().getInteger(R.integer.performance_monitor_window_width);
        this.mHeight = getResources().getInteger(R.integer.performance_monitor_window_height);
        this.mLeft = 0.0f;
        c();
    }

    private void c() {
        float f2 = this.mLeft;
        this.mRoundRect.set(f2 + 1.0f, 1.0f, (f2 + this.mWidth) - 1.0f, this.mHeight - 1.0f);
    }

    protected void a(Canvas canvas, Paint paint) {
        RectF rectF = this.mRoundRect;
        float f2 = this.mRadius;
        canvas.drawRoundRect(rectF, f2, f2, paint);
    }

    public float getBgWidth() {
        return this.mWidth;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        a(canvas, this.mPaint);
        a(canvas, this.mStrokePaint);
    }

    public void setRectanglePara(float f2, float f3) {
        setRectanglePara(f2, f3, this.mRadius);
    }

    public CornerRectangle(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public void setRectanglePara(float f2, float f3, float f4) {
        this.mLeft = f2;
        this.mWidth = f3;
        this.mRadius = f4;
        c();
        invalidate();
    }

    public CornerRectangle(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public CornerRectangle(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.mStrokeWidth = 2.0f;
        this.mRoundRect = new RectF();
        this.mRadius = 20.0f;
        b();
    }
}
