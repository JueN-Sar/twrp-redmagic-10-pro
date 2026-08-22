package com.zte.mifavor.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import com.zte.extres.R;

/* loaded from: classes2.dex */
public class SolidCircleView extends View {
    public static final int SHAPE_MODE_CIRCLE = 0;
    public static final int SHAPE_MODE_ROUND_RECT = 1;
    private int circleColor;
    private Paint circlePaint;
    private float circleRadius;
    private final RectF roundRectBounds;
    private int shapeMode;

    public SolidCircleView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private float a(float f2) {
        return TypedValue.applyDimension(1, f2, getContext().getResources().getDisplayMetrics());
    }

    private void b(AttributeSet attributeSet) {
        Paint paint = new Paint();
        this.circlePaint = paint;
        paint.setAntiAlias(true);
        this.circlePaint.setStyle(Paint.Style.FILL);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.SolidCircleView);
            this.circleColor = obtainStyledAttributes.getColor(R.styleable.SolidCircleView_circleColor, Color.parseColor("#0F87F7"));
            this.circleRadius = obtainStyledAttributes.getDimensionPixelSize(R.styleable.SolidCircleView_circleRadius, (int) a(42.0f));
            this.shapeMode = obtainStyledAttributes.getInt(R.styleable.SolidCircleView_shapeMode, 0);
            obtainStyledAttributes.recycle();
        }
        this.circlePaint.setColor(this.circleColor);
    }

    public int getShapeMode() {
        return this.shapeMode;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.shapeMode != 1) {
            canvas.drawCircle(getWidth() / 2.0f, getHeight() / 2.0f, this.circleRadius, this.circlePaint);
            return;
        }
        this.roundRectBounds.set(0.0f, 0.0f, getWidth(), getHeight());
        canvas.drawRoundRect(this.roundRectBounds, Math.min(this.circleRadius, getWidth() / 2.0f), Math.min(this.circleRadius, getHeight() / 2.0f), this.circlePaint);
    }

    public void setCircleColor(int i2) {
        this.circleColor = i2;
        Paint paint = new Paint();
        this.circlePaint = paint;
        paint.setAntiAlias(true);
        this.circlePaint.setStyle(Paint.Style.FILL);
        this.circlePaint.setColor(this.circleColor);
        invalidate();
    }

    public void setCircleRadius(float f2) {
        this.circleRadius = Math.max(0.0f, f2);
        invalidate();
    }

    public void setShapeMode(int i2) {
        if (this.shapeMode != i2) {
            this.shapeMode = i2;
            invalidate();
        }
    }

    public SolidCircleView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.shapeMode = 0;
        this.roundRectBounds = new RectF();
        b(attributeSet);
    }
}
