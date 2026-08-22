package androidx.appcompat.graphics.drawable;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import androidx.annotation.RestrictTo;
import androidx.appcompat.R;
import androidx.core.graphics.drawable.DrawableCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public class DrawerArrowDrawable extends Drawable {

    /* renamed from: m, reason: collision with root package name */
    private static final float f405m = (float) Math.toRadians(45.0d);

    /* renamed from: a, reason: collision with root package name */
    private final Paint f406a;

    /* renamed from: b, reason: collision with root package name */
    private float f407b;

    /* renamed from: c, reason: collision with root package name */
    private float f408c;

    /* renamed from: d, reason: collision with root package name */
    private float f409d;

    /* renamed from: e, reason: collision with root package name */
    private float f410e;

    /* renamed from: f, reason: collision with root package name */
    private boolean f411f;

    /* renamed from: g, reason: collision with root package name */
    private final Path f412g;

    /* renamed from: h, reason: collision with root package name */
    private final int f413h;

    /* renamed from: i, reason: collision with root package name */
    private boolean f414i;

    /* renamed from: j, reason: collision with root package name */
    private float f415j;

    /* renamed from: k, reason: collision with root package name */
    private float f416k;

    /* renamed from: l, reason: collision with root package name */
    private int f417l;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface ArrowDirection {
    }

    public DrawerArrowDrawable(Context context) {
        Paint paint = new Paint();
        this.f406a = paint;
        this.f412g = new Path();
        this.f414i = false;
        this.f417l = 2;
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.MITER);
        paint.setStrokeCap(Paint.Cap.BUTT);
        paint.setAntiAlias(true);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(null, R.styleable.DrawerArrowToggle, R.attr.drawerArrowStyle, R.style.Base_Widget_AppCompat_DrawerArrowToggle);
        c(obtainStyledAttributes.getColor(R.styleable.DrawerArrowToggle_color, 0));
        b(obtainStyledAttributes.getDimension(R.styleable.DrawerArrowToggle_thickness, 0.0f));
        f(obtainStyledAttributes.getBoolean(R.styleable.DrawerArrowToggle_spinBars, true));
        d(Math.round(obtainStyledAttributes.getDimension(R.styleable.DrawerArrowToggle_gapBetweenBars, 0.0f)));
        this.f413h = obtainStyledAttributes.getDimensionPixelSize(R.styleable.DrawerArrowToggle_drawableSize, 0);
        this.f408c = Math.round(obtainStyledAttributes.getDimension(R.styleable.DrawerArrowToggle_barLength, 0.0f));
        this.f407b = Math.round(obtainStyledAttributes.getDimension(R.styleable.DrawerArrowToggle_arrowHeadLength, 0.0f));
        this.f409d = obtainStyledAttributes.getDimension(R.styleable.DrawerArrowToggle_arrowShaftLength, 0.0f);
        obtainStyledAttributes.recycle();
    }

    private static float a(float f2, float f3, float f4) {
        return f2 + ((f3 - f2) * f4);
    }

    public void b(float f2) {
        if (this.f406a.getStrokeWidth() != f2) {
            this.f406a.setStrokeWidth(f2);
            this.f416k = (float) ((f2 / 2.0f) * Math.cos(f405m));
            invalidateSelf();
        }
    }

    public void c(int i2) {
        if (i2 != this.f406a.getColor()) {
            this.f406a.setColor(i2);
            invalidateSelf();
        }
    }

    public void d(float f2) {
        if (f2 != this.f410e) {
            this.f410e = f2;
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        int i2 = this.f417l;
        boolean z = false;
        if (i2 != 0 && (i2 == 1 || (i2 == 3 ? DrawableCompat.f(this) == 0 : DrawableCompat.f(this) == 1))) {
            z = true;
        }
        float f2 = this.f407b;
        float a2 = a(this.f408c, (float) Math.sqrt(f2 * f2 * 2.0f), this.f415j);
        float a3 = a(this.f408c, this.f409d, this.f415j);
        float round = Math.round(a(0.0f, this.f416k, this.f415j));
        float a4 = a(0.0f, f405m, this.f415j);
        float a5 = a(z ? 0.0f : -180.0f, z ? 180.0f : 0.0f, this.f415j);
        double d2 = a2;
        double d3 = a4;
        boolean z2 = z;
        float round2 = Math.round(Math.cos(d3) * d2);
        float round3 = Math.round(d2 * Math.sin(d3));
        this.f412g.rewind();
        float a6 = a(this.f410e + this.f406a.getStrokeWidth(), -this.f416k, this.f415j);
        float f3 = (-a3) / 2.0f;
        this.f412g.moveTo(f3 + round, 0.0f);
        this.f412g.rLineTo(a3 - (round * 2.0f), 0.0f);
        this.f412g.moveTo(f3, a6);
        this.f412g.rLineTo(round2, round3);
        this.f412g.moveTo(f3, -a6);
        this.f412g.rLineTo(round2, -round3);
        this.f412g.close();
        canvas.save();
        float strokeWidth = this.f406a.getStrokeWidth();
        float height = bounds.height() - (3.0f * strokeWidth);
        canvas.translate(bounds.centerX(), ((((int) (height - (2.0f * r5))) / 4) * 2) + (strokeWidth * 1.5f) + this.f410e);
        if (this.f411f) {
            canvas.rotate(a5 * (this.f414i ^ z2 ? -1 : 1));
        } else if (z2) {
            canvas.rotate(180.0f);
        }
        canvas.drawPath(this.f412g, this.f406a);
        canvas.restore();
    }

    public void e(float f2) {
        if (this.f415j != f2) {
            this.f415j = f2;
            invalidateSelf();
        }
    }

    public void f(boolean z) {
        if (this.f411f != z) {
            this.f411f = z;
            invalidateSelf();
        }
    }

    public void g(boolean z) {
        if (this.f414i != z) {
            this.f414i = z;
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.f413h;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.f413h;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        if (i2 != this.f406a.getAlpha()) {
            this.f406a.setAlpha(i2);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f406a.setColorFilter(colorFilter);
        invalidateSelf();
    }
}
