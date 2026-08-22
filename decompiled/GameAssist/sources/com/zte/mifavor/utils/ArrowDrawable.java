package com.zte.mifavor.utils;

import android.content.Context;
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
import com.zte.extres.R;

/* loaded from: classes2.dex */
public class ArrowDrawable extends Drawable {

    /* renamed from: a, reason: collision with root package name */
    private Path f17381a;

    /* renamed from: b, reason: collision with root package name */
    private Paint f17382b;

    /* renamed from: c, reason: collision with root package name */
    private int f17383c;

    /* renamed from: d, reason: collision with root package name */
    private int f17384d;

    /* renamed from: e, reason: collision with root package name */
    private int f17385e;

    /* renamed from: f, reason: collision with root package name */
    private int f17386f;

    /* renamed from: g, reason: collision with root package name */
    private int f17387g;

    /* renamed from: h, reason: collision with root package name */
    private int f17388h;

    /* renamed from: i, reason: collision with root package name */
    private int f17389i;

    /* renamed from: j, reason: collision with root package name */
    private int f17390j;

    /* renamed from: k, reason: collision with root package name */
    private int f17391k;

    public ArrowDrawable(Context context, AttributeSet attributeSet) {
        c(context);
        b(context, attributeSet);
    }

    private void a(int i2, TypedArray typedArray) {
        if (i2 == R.styleable.ArrowDrawable_ad_bgColor) {
            this.f17390j = typedArray.getColor(i2, this.f17390j);
            return;
        }
        if (i2 == R.styleable.ArrowDrawable_ad_shadowColor) {
            this.f17391k = typedArray.getColor(i2, this.f17391k);
            return;
        }
        if (i2 == R.styleable.ArrowDrawable_ad_arrowHeight) {
            this.f17383c = typedArray.getDimensionPixelSize(i2, this.f17383c);
            return;
        }
        if (i2 == R.styleable.ArrowDrawable_ad_shadowSize) {
            this.f17389i = typedArray.getDimensionPixelSize(i2, this.f17389i);
            return;
        }
        if (i2 == R.styleable.ArrowDrawable_ad_radius) {
            this.f17385e = typedArray.getDimensionPixelSize(i2, this.f17385e);
            return;
        }
        if (i2 == R.styleable.ArrowDrawable_ad_arrowExtraOffsetX) {
            this.f17387g = typedArray.getDimensionPixelSize(i2, this.f17387g);
        } else if (i2 == R.styleable.ArrowDrawable_ad_arrowExtraOffsetY) {
            this.f17388h = typedArray.getDimensionPixelSize(i2, this.f17388h);
        } else if (i2 == R.styleable.ArrowDrawable_ad_arrowGravity) {
            this.f17386f = typedArray.getInt(i2, this.f17386f);
        }
    }

    private void b(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ArrowDrawable);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i2 = 0; i2 < indexCount; i2++) {
            a(obtainStyledAttributes.getIndex(i2), obtainStyledAttributes);
        }
        obtainStyledAttributes.recycle();
    }

    private void c(Context context) {
        Paint paint = new Paint(1);
        this.f17382b = paint;
        paint.setAntiAlias(true);
        this.f17390j = -1;
        this.f17391k = context.getColor(R.color.arrow_drawable_background_color);
        this.f17383c = context.getResources().getDimensionPixelOffset(R.dimen.arrow_height);
        this.f17384d = context.getResources().getDimensionPixelOffset(R.dimen.arrow_width);
        this.f17385e = context.getResources().getDimensionPixelOffset(R.dimen.arrow_drawable_radius);
        this.f17389i = context.getResources().getDimensionPixelOffset(R.dimen.arrow_bg_shadow);
        this.f17387g = 0;
        this.f17388h = 0;
        this.f17386f = 144;
    }

    private boolean d(int i2) {
        return (this.f17386f & i2) == i2;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.f17381a != null) {
            int i2 = this.f17389i;
            if (i2 > 0) {
                if (this.f17383c == 0) {
                    this.f17382b.setShadowLayer(i2, 0.0f, 6.0f, this.f17391k);
                } else if (d(32)) {
                    this.f17382b.setShadowLayer(this.f17389i, 6.0f, 2.0f, this.f17391k);
                } else if (d(16)) {
                    this.f17382b.setShadowLayer(this.f17389i, 2.0f, -6.0f, this.f17391k);
                }
                this.f17382b.setColor(this.f17391k);
                canvas.drawPath(this.f17381a, this.f17382b);
            }
            this.f17382b.setMaskFilter(null);
            this.f17382b.setColor(this.f17390j);
            canvas.drawPath(this.f17381a, this.f17382b);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        Path path = this.f17381a;
        if (path == null) {
            this.f17381a = new Path();
        } else {
            path.reset();
        }
        RectF rectF = new RectF(rect);
        int i2 = this.f17389i;
        rectF.inset(i2, i2);
        PointF pointF = new PointF();
        if (d(32)) {
            float f2 = rectF.left + this.f17383c;
            rectF.left = f2;
            pointF.x = f2;
        } else if (d(64)) {
            pointF.x = rectF.left + this.f17383c;
        } else if (d(128)) {
            pointF.x = rect.width() / 2.0f;
        } else if (d(256)) {
            pointF.x = rectF.right - this.f17383c;
        } else if (d(512)) {
            float f3 = rectF.right - this.f17383c;
            rectF.right = f3;
            pointF.x = f3;
        }
        if (d(1)) {
            float f4 = rectF.top + this.f17383c;
            rectF.top = f4;
            pointF.y = f4;
        } else if (d(2)) {
            pointF.y = rectF.top + this.f17383c;
        } else if (d(4)) {
            pointF.y = rect.height() / 2.0f;
        } else if (d(8)) {
            pointF.y = rectF.bottom - this.f17383c;
        } else if (d(16)) {
            float f5 = rectF.bottom - this.f17383c;
            rectF.bottom = f5;
            pointF.y = f5;
        }
        pointF.x += this.f17387g;
        pointF.y += this.f17388h;
        if (d(64) || d(128) || d(256)) {
            float max = Math.max(pointF.x, rectF.left + this.f17385e + this.f17383c);
            pointF.x = max;
            pointF.x = Math.min(max, (rectF.right - this.f17385e) - this.f17383c);
        }
        if (d(32) || d(512)) {
            float max2 = Math.max(pointF.x, rectF.left);
            pointF.x = max2;
            pointF.x = Math.min(max2, rectF.right);
        }
        if (d(2) || d(4) || d(8)) {
            float max3 = Math.max(pointF.y, rectF.top + this.f17385e + this.f17383c);
            pointF.y = max3;
            pointF.y = Math.min(max3, (rectF.bottom - this.f17385e) - this.f17383c);
        }
        if (d(1) || d(16)) {
            float max4 = Math.max(pointF.y, rectF.top);
            pointF.y = max4;
            pointF.y = Math.min(max4, rectF.bottom);
        }
        Path path2 = new Path();
        if (d(32)) {
            path2.moveTo(pointF.x - this.f17383c, pointF.y);
            path2.lineTo(pointF.x, pointF.y - this.f17384d);
            path2.lineTo(pointF.x + this.f17383c, pointF.y);
            path2.lineTo(pointF.x, pointF.y + this.f17384d);
        }
        if (d(16)) {
            path2.moveTo(pointF.x - this.f17384d, pointF.y);
            path2.lineTo(pointF.x, pointF.y - this.f17383c);
            path2.lineTo(pointF.x + this.f17384d, pointF.y);
            path2.lineTo(pointF.x, pointF.y + this.f17383c);
        }
        path2.close();
        Path path3 = this.f17381a;
        int i3 = this.f17385e;
        path3.addRoundRect(rectF, i3, i3, Path.Direction.CW);
        this.f17381a.addPath(path2);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        this.f17382b.setAlpha(i2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f17382b.setColorFilter(colorFilter);
    }
}
