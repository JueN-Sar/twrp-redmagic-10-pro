package com.zte.mifavor.utils;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import com.zte.extres.R;
import org.xmlpull.v1.XmlPullParser;

/* loaded from: classes2.dex */
public class ShadowDrawable extends Drawable {

    /* renamed from: a, reason: collision with root package name */
    private Path f17429a;

    /* renamed from: b, reason: collision with root package name */
    private Paint f17430b;

    /* renamed from: c, reason: collision with root package name */
    private int f17431c;

    /* renamed from: d, reason: collision with root package name */
    private int f17432d;

    /* renamed from: e, reason: collision with root package name */
    private int f17433e;

    /* renamed from: f, reason: collision with root package name */
    private int f17434f;

    /* renamed from: g, reason: collision with root package name */
    private int f17435g;

    /* renamed from: h, reason: collision with root package name */
    private final String f17436h = "ShadowDrawable";

    private void a(TypedArray typedArray, AttributeSet attributeSet) {
        this.f17434f = typedArray.getColor(R.styleable.ArrowDrawable_ad_bgColor, this.f17434f);
        this.f17435g = typedArray.getColor(R.styleable.ArrowDrawable_ad_shadowColor, this.f17435g);
        this.f17432d = typedArray.getDimensionPixelSize(R.styleable.ArrowDrawable_ad_shadowSize, this.f17432d);
        this.f17431c = typedArray.getDimensionPixelSize(R.styleable.ArrowDrawable_ad_radius, this.f17431c);
        this.f17433e = typedArray.getDimensionPixelSize(R.styleable.ArrowDrawable_ad_shadowOffsetY, this.f17433e);
        Log.d("ShadowDrawable", "initCustomAttr mBgColor:" + this.f17434f + " mShadowColor:" + this.f17435g + " mShadowSize:" + this.f17432d + " mRadius:" + this.f17431c + " mShadowoffsetY:" + this.f17433e);
        typedArray.recycle();
    }

    private void b(Resources resources) {
        Paint paint = new Paint(1);
        this.f17430b = paint;
        paint.setAntiAlias(true);
        this.f17434f = -1;
        this.f17435g = resources.getColor(R.color.arrow_drawable_background_color);
        this.f17431c = resources.getDimensionPixelOffset(R.dimen.arrow_drawable_radius);
        this.f17432d = resources.getDimensionPixelOffset(R.dimen.arrow_bg_shadow);
        this.f17433e = resources.getDimensionPixelOffset(R.dimen.loading_toast_bg_shadow_offset_y);
    }

    private TypedArray c(Resources resources, Resources.Theme theme, AttributeSet attributeSet, int[] iArr) {
        return theme == null ? resources.obtainAttributes(attributeSet, iArr) : theme.obtainStyledAttributes(attributeSet, iArr, 0, 0);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.f17429a != null) {
            int i2 = this.f17432d;
            if (i2 > 0) {
                this.f17430b.setShadowLayer(i2, 0.0f, this.f17433e, this.f17435g);
                this.f17430b.setColor(this.f17435g);
                canvas.drawPath(this.f17429a, this.f17430b);
            }
            this.f17430b.setMaskFilter(null);
            this.f17430b.setColor(this.f17434f);
            canvas.drawPath(this.f17429a, this.f17430b);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        TypedArray c2 = c(resources, theme, attributeSet, R.styleable.ArrowDrawable);
        b(resources);
        a(c2, attributeSet);
        super.inflate(resources, xmlPullParser, attributeSet, theme);
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        Path path = this.f17429a;
        if (path == null) {
            this.f17429a = new Path();
        } else {
            path.reset();
        }
        RectF rectF = new RectF(rect);
        int i2 = this.f17432d;
        rectF.inset(i2, i2);
        PointF pointF = new PointF();
        float max = Math.max(pointF.x, rectF.left + this.f17431c);
        pointF.x = max;
        pointF.x = Math.min(max, rectF.right - this.f17431c);
        float max2 = Math.max(pointF.y, rectF.top);
        pointF.y = max2;
        pointF.y = Math.min(max2, rectF.bottom);
        Path path2 = new Path();
        path2.moveTo(pointF.x, pointF.y);
        path2.lineTo(pointF.x, pointF.y);
        path2.lineTo(pointF.x, pointF.y);
        path2.lineTo(pointF.x, pointF.y);
        path2.close();
        Path path3 = this.f17429a;
        int i3 = this.f17431c;
        path3.addRoundRect(rectF, i3, i3, Path.Direction.CW);
        this.f17429a.addPath(path2);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        this.f17430b.setAlpha(i2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f17430b.setColorFilter(colorFilter);
    }
}
