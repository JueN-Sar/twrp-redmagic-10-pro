package cn.nubia.gameassist.plugin.panel;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.theme.Theme;
import cn.nubia.gameassist.theme.ThemeController;
import cn.nubia.gameassist.theme.ThemeDrawable;
import cn.nubia.gameassist.theme.ThemeWidget;

/* loaded from: classes.dex */
public class CardAttrRectangle extends View implements ThemeWidget {
    private static final int RADIUS = 2;
    private static final String TAG = "CardAttrRectangle";
    private AttrDrawable mAttrDrawable;
    private Paint mColorPaint;
    private int mColorRectangleNum;
    private Context mContext;
    private Paint mGrayPaint;
    private RectF mRectF1;
    private RectF mRectF2;
    private RectF mRectF3;
    private Paint mStrokeColorPaint;
    private Paint mStrokeGrayPaint;
    private Theme mTheme;

    private class AttrDrawable extends ThemeDrawable {
        @Override // cn.nubia.gameassist.theme.ThemeDrawable, cn.nubia.gameassist.theme.ThemeWidget
        public void d(Theme theme) {
            super.d(theme);
            if (this.f7497k != null) {
                CardAttrRectangle.this.mColorPaint.setColor(this.f7497k.a(CardAttrRectangle.this.isSelected() ? 2 : 1, false));
                CardAttrRectangle.this.mStrokeColorPaint.setColor(this.f7497k.a(CardAttrRectangle.this.isSelected() ? 2 : 1, true));
                CardAttrRectangle.this.mGrayPaint.setColor(CardAttrRectangle.this.isSelected() ? -1 : 1728053247);
                CardAttrRectangle.this.invalidate();
            }
        }

        private AttrDrawable() {
        }
    }

    public CardAttrRectangle(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void e(Canvas canvas) {
        int i2 = this.mColorRectangleNum;
        if (i2 == 1) {
            canvas.drawRoundRect(this.mRectF1, 2.0f, 2.0f, this.mStrokeColorPaint);
            canvas.drawRoundRect(this.mRectF1, 2.0f, 2.0f, this.mColorPaint);
            canvas.drawRoundRect(this.mRectF2, 2.0f, 2.0f, this.mStrokeGrayPaint);
            canvas.drawRoundRect(this.mRectF2, 2.0f, 2.0f, this.mGrayPaint);
            canvas.drawRoundRect(this.mRectF3, 2.0f, 2.0f, this.mStrokeGrayPaint);
            canvas.drawRoundRect(this.mRectF3, 2.0f, 2.0f, this.mGrayPaint);
            return;
        }
        if (i2 == 2) {
            canvas.drawRoundRect(this.mRectF1, 2.0f, 2.0f, this.mStrokeColorPaint);
            canvas.drawRoundRect(this.mRectF1, 2.0f, 2.0f, this.mColorPaint);
            canvas.drawRoundRect(this.mRectF2, 2.0f, 2.0f, this.mStrokeColorPaint);
            canvas.drawRoundRect(this.mRectF2, 2.0f, 2.0f, this.mColorPaint);
            canvas.drawRoundRect(this.mRectF3, 2.0f, 2.0f, this.mStrokeGrayPaint);
            canvas.drawRoundRect(this.mRectF3, 2.0f, 2.0f, this.mGrayPaint);
            return;
        }
        if (i2 != 3) {
            canvas.drawRoundRect(this.mRectF1, 2.0f, 2.0f, this.mStrokeGrayPaint);
            canvas.drawRoundRect(this.mRectF1, 2.0f, 2.0f, this.mGrayPaint);
            canvas.drawRoundRect(this.mRectF2, 2.0f, 2.0f, this.mStrokeGrayPaint);
            canvas.drawRoundRect(this.mRectF2, 2.0f, 2.0f, this.mGrayPaint);
            canvas.drawRoundRect(this.mRectF3, 2.0f, 2.0f, this.mStrokeGrayPaint);
            canvas.drawRoundRect(this.mRectF3, 2.0f, 2.0f, this.mGrayPaint);
            return;
        }
        canvas.drawRoundRect(this.mRectF1, 2.0f, 2.0f, this.mStrokeColorPaint);
        canvas.drawRoundRect(this.mRectF1, 2.0f, 2.0f, this.mColorPaint);
        canvas.drawRoundRect(this.mRectF2, 2.0f, 2.0f, this.mStrokeColorPaint);
        canvas.drawRoundRect(this.mRectF2, 2.0f, 2.0f, this.mColorPaint);
        canvas.drawRoundRect(this.mRectF3, 2.0f, 2.0f, this.mStrokeColorPaint);
        canvas.drawRoundRect(this.mRectF3, 2.0f, 2.0f, this.mColorPaint);
    }

    @Override // cn.nubia.gameassist.theme.ThemeWidget
    public void d(Theme theme) {
        this.mTheme = theme;
        this.mAttrDrawable.d(theme);
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ThemeController.m().h(this);
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ThemeController.m().p(this);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        e(canvas);
    }

    public void setColorRectangleNum(int i2) {
        this.mColorRectangleNum = i2;
        invalidate();
    }

    public CardAttrRectangle(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public CardAttrRectangle(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.mAttrDrawable = new AttrDrawable();
        this.mContext = context;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CardAttrRectangle);
        this.mColorRectangleNum = obtainStyledAttributes.getInteger(R.styleable.CardAttrRectangle_colorRectangleNum, 0);
        obtainStyledAttributes.recycle();
        Paint paint = new Paint();
        this.mGrayPaint = paint;
        paint.setColor(1728053247);
        this.mGrayPaint.setAntiAlias(true);
        Paint paint2 = this.mGrayPaint;
        Paint.Style style = Paint.Style.FILL;
        paint2.setStyle(style);
        Paint paint3 = new Paint();
        this.mStrokeGrayPaint = paint3;
        paint3.setColor(-1711276033);
        this.mStrokeGrayPaint.setStrokeWidth(1.5f);
        this.mStrokeGrayPaint.setAntiAlias(true);
        Paint paint4 = this.mStrokeGrayPaint;
        Paint.Style style2 = Paint.Style.STROKE;
        paint4.setStyle(style2);
        Paint paint5 = new Paint();
        this.mColorPaint = paint5;
        paint5.setColor(1716059647);
        this.mColorPaint.setAntiAlias(true);
        this.mColorPaint.setStyle(style);
        Paint paint6 = new Paint();
        this.mStrokeColorPaint = paint6;
        paint6.setColor(-1723269633);
        this.mStrokeColorPaint.setAntiAlias(true);
        this.mStrokeColorPaint.setStrokeWidth(1.5f);
        this.mStrokeColorPaint.setStyle(style2);
        this.mRectF1 = new RectF(0.0f, 0.0f, 12.0f, 18.0f);
        this.mRectF2 = new RectF(18.0f, 0.0f, 30.0f, 18.0f);
        this.mRectF3 = new RectF(36.0f, 0.0f, 48.0f, 18.0f);
    }
}
