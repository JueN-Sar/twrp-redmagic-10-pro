package cn.nubia.gameassist.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import cn.nubia.gameassist.R;

@SuppressLint({"AppCompatCustomView"})
/* loaded from: classes.dex */
public class StrokeTextView extends TextView {
    private final TextPaint mTextPaint;
    private final TextPaint mTextStrokePaint;

    public StrokeTextView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        canvas.drawText(getText().toString(), 0.0f, getBaseline(), this.mTextStrokePaint);
        canvas.drawText(getText().toString(), 0.0f, getBaseline(), this.mTextPaint);
    }

    public StrokeTextView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.StrokeTextView, i2, i2);
        int color = obtainStyledAttributes.getColor(R.styleable.StrokeTextView_stroke_color, -1);
        int dimensionPixelOffset = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.StrokeTextView_stroke_width, 0);
        obtainStyledAttributes.recycle();
        TextPaint paint = getPaint();
        this.mTextPaint = paint;
        paint.setTypeface(YouSheTextView.getYouSheHei());
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setColor(getCurrentTextColor());
        Paint.Align align = Paint.Align.LEFT;
        paint.setTextAlign(align);
        TextPaint textPaint = new TextPaint();
        this.mTextStrokePaint = textPaint;
        textPaint.setTypeface(YouSheTextView.getYouSheHei());
        textPaint.setTextSize(getTextSize());
        textPaint.setAntiAlias(true);
        textPaint.setColor(color);
        textPaint.setStyle(Paint.Style.STROKE);
        textPaint.setStrokeWidth(dimensionPixelOffset);
        textPaint.setDither(true);
        textPaint.setTextAlign(align);
        textPaint.setShadowLayer(getShadowRadius(), getShadowDx(), getShadowDy(), getShadowColor());
    }
}
