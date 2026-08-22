package com.google.android.material.circularreveal;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.google.android.material.circularreveal.CircularRevealWidget;
import com.google.android.material.math.MathUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public class CircularRevealHelper {

    /* renamed from: j, reason: collision with root package name */
    public static final int f14202j = 2;

    /* renamed from: a, reason: collision with root package name */
    private final Delegate f14203a;

    /* renamed from: b, reason: collision with root package name */
    private final View f14204b;

    /* renamed from: c, reason: collision with root package name */
    private final Path f14205c;

    /* renamed from: d, reason: collision with root package name */
    private final Paint f14206d;

    /* renamed from: e, reason: collision with root package name */
    private final Paint f14207e;

    /* renamed from: f, reason: collision with root package name */
    private CircularRevealWidget.RevealInfo f14208f;

    /* renamed from: g, reason: collision with root package name */
    private Drawable f14209g;

    /* renamed from: h, reason: collision with root package name */
    private boolean f14210h;

    /* renamed from: i, reason: collision with root package name */
    private boolean f14211i;

    public interface Delegate {
        void f(Canvas canvas);

        boolean j();
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Strategy {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public CircularRevealHelper(Delegate delegate) {
        this.f14203a = delegate;
        View view = (View) delegate;
        this.f14204b = view;
        view.setWillNotDraw(false);
        this.f14205c = new Path();
        this.f14206d = new Paint(7);
        Paint paint = new Paint(1);
        this.f14207e = paint;
        paint.setColor(0);
    }

    private void d(Canvas canvas) {
        if (o()) {
            Rect bounds = this.f14209g.getBounds();
            float width = this.f14208f.f14216a - (bounds.width() / 2.0f);
            float height = this.f14208f.f14217b - (bounds.height() / 2.0f);
            canvas.translate(width, height);
            this.f14209g.draw(canvas);
            canvas.translate(-width, -height);
        }
    }

    private float g(CircularRevealWidget.RevealInfo revealInfo) {
        return MathUtils.b(revealInfo.f14216a, revealInfo.f14217b, 0.0f, 0.0f, this.f14204b.getWidth(), this.f14204b.getHeight());
    }

    private void i() {
        if (f14202j == 1) {
            this.f14205c.rewind();
            CircularRevealWidget.RevealInfo revealInfo = this.f14208f;
            if (revealInfo != null) {
                this.f14205c.addCircle(revealInfo.f14216a, revealInfo.f14217b, revealInfo.f14218c, Path.Direction.CW);
            }
        }
        this.f14204b.invalidate();
    }

    private boolean n() {
        CircularRevealWidget.RevealInfo revealInfo = this.f14208f;
        boolean z = revealInfo == null || revealInfo.a();
        return f14202j == 0 ? !z && this.f14211i : !z;
    }

    private boolean o() {
        return (this.f14210h || this.f14209g == null || this.f14208f == null) ? false : true;
    }

    private boolean p() {
        return (this.f14210h || Color.alpha(this.f14207e.getColor()) == 0) ? false : true;
    }

    public void a() {
        if (f14202j == 0) {
            this.f14210h = true;
            this.f14211i = false;
            this.f14204b.buildDrawingCache();
            Bitmap drawingCache = this.f14204b.getDrawingCache();
            if (drawingCache == null && this.f14204b.getWidth() != 0 && this.f14204b.getHeight() != 0) {
                drawingCache = Bitmap.createBitmap(this.f14204b.getWidth(), this.f14204b.getHeight(), Bitmap.Config.ARGB_8888);
                this.f14204b.draw(new Canvas(drawingCache));
            }
            if (drawingCache != null) {
                Paint paint = this.f14206d;
                Shader.TileMode tileMode = Shader.TileMode.CLAMP;
                paint.setShader(new BitmapShader(drawingCache, tileMode, tileMode));
            }
            this.f14210h = false;
            this.f14211i = true;
        }
    }

    public void b() {
        if (f14202j == 0) {
            this.f14211i = false;
            this.f14204b.destroyDrawingCache();
            this.f14206d.setShader(null);
            this.f14204b.invalidate();
        }
    }

    public void c(Canvas canvas) {
        if (n()) {
            int i2 = f14202j;
            if (i2 == 0) {
                CircularRevealWidget.RevealInfo revealInfo = this.f14208f;
                canvas.drawCircle(revealInfo.f14216a, revealInfo.f14217b, revealInfo.f14218c, this.f14206d);
                if (p()) {
                    CircularRevealWidget.RevealInfo revealInfo2 = this.f14208f;
                    canvas.drawCircle(revealInfo2.f14216a, revealInfo2.f14217b, revealInfo2.f14218c, this.f14207e);
                }
            } else if (i2 == 1) {
                int save = canvas.save();
                canvas.clipPath(this.f14205c);
                this.f14203a.f(canvas);
                if (p()) {
                    canvas.drawRect(0.0f, 0.0f, this.f14204b.getWidth(), this.f14204b.getHeight(), this.f14207e);
                }
                canvas.restoreToCount(save);
            } else {
                if (i2 != 2) {
                    throw new IllegalStateException("Unsupported strategy " + i2);
                }
                this.f14203a.f(canvas);
                if (p()) {
                    canvas.drawRect(0.0f, 0.0f, this.f14204b.getWidth(), this.f14204b.getHeight(), this.f14207e);
                }
            }
        } else {
            this.f14203a.f(canvas);
            if (p()) {
                canvas.drawRect(0.0f, 0.0f, this.f14204b.getWidth(), this.f14204b.getHeight(), this.f14207e);
            }
        }
        d(canvas);
    }

    public Drawable e() {
        return this.f14209g;
    }

    public int f() {
        return this.f14207e.getColor();
    }

    public CircularRevealWidget.RevealInfo h() {
        CircularRevealWidget.RevealInfo revealInfo = this.f14208f;
        if (revealInfo == null) {
            return null;
        }
        CircularRevealWidget.RevealInfo revealInfo2 = new CircularRevealWidget.RevealInfo(revealInfo);
        if (revealInfo2.a()) {
            revealInfo2.f14218c = g(revealInfo2);
        }
        return revealInfo2;
    }

    public boolean j() {
        return this.f14203a.j() && !n();
    }

    public void k(Drawable drawable) {
        this.f14209g = drawable;
        this.f14204b.invalidate();
    }

    public void l(int i2) {
        this.f14207e.setColor(i2);
        this.f14204b.invalidate();
    }

    public void m(CircularRevealWidget.RevealInfo revealInfo) {
        if (revealInfo == null) {
            this.f14208f = null;
        } else {
            CircularRevealWidget.RevealInfo revealInfo2 = this.f14208f;
            if (revealInfo2 == null) {
                this.f14208f = new CircularRevealWidget.RevealInfo(revealInfo);
            } else {
                revealInfo2.c(revealInfo);
            }
            if (MathUtils.c(revealInfo.f14218c, g(revealInfo), 1.0E-4f)) {
                this.f14208f.f14218c = Float.MAX_VALUE;
            }
        }
        i();
    }
}
