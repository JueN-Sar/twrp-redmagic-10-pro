package androidx.swiperefreshlayout.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import androidx.annotation.RestrictTo;
import androidx.core.util.Preconditions;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public class CircularProgressDrawable extends Drawable implements Animatable {

    /* renamed from: m, reason: collision with root package name */
    private static final Interpolator f5367m = new LinearInterpolator();

    /* renamed from: n, reason: collision with root package name */
    private static final Interpolator f5368n = new FastOutSlowInInterpolator();

    /* renamed from: o, reason: collision with root package name */
    private static final int[] f5369o = {-16777216};

    /* renamed from: c, reason: collision with root package name */
    private final Ring f5370c;

    /* renamed from: h, reason: collision with root package name */
    private float f5371h;

    /* renamed from: i, reason: collision with root package name */
    private Resources f5372i;

    /* renamed from: j, reason: collision with root package name */
    private Animator f5373j;

    /* renamed from: k, reason: collision with root package name */
    float f5374k;

    /* renamed from: l, reason: collision with root package name */
    boolean f5375l;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface ProgressDrawableSize {
    }

    private static class Ring {

        /* renamed from: a, reason: collision with root package name */
        final RectF f5380a = new RectF();

        /* renamed from: b, reason: collision with root package name */
        final Paint f5381b;

        /* renamed from: c, reason: collision with root package name */
        final Paint f5382c;

        /* renamed from: d, reason: collision with root package name */
        final Paint f5383d;

        /* renamed from: e, reason: collision with root package name */
        float f5384e;

        /* renamed from: f, reason: collision with root package name */
        float f5385f;

        /* renamed from: g, reason: collision with root package name */
        float f5386g;

        /* renamed from: h, reason: collision with root package name */
        float f5387h;

        /* renamed from: i, reason: collision with root package name */
        int[] f5388i;

        /* renamed from: j, reason: collision with root package name */
        int f5389j;

        /* renamed from: k, reason: collision with root package name */
        float f5390k;

        /* renamed from: l, reason: collision with root package name */
        float f5391l;

        /* renamed from: m, reason: collision with root package name */
        float f5392m;

        /* renamed from: n, reason: collision with root package name */
        boolean f5393n;

        /* renamed from: o, reason: collision with root package name */
        Path f5394o;

        /* renamed from: p, reason: collision with root package name */
        float f5395p;

        /* renamed from: q, reason: collision with root package name */
        float f5396q;

        /* renamed from: r, reason: collision with root package name */
        int f5397r;

        /* renamed from: s, reason: collision with root package name */
        int f5398s;
        int t;
        int u;

        Ring() {
            Paint paint = new Paint();
            this.f5381b = paint;
            Paint paint2 = new Paint();
            this.f5382c = paint2;
            Paint paint3 = new Paint();
            this.f5383d = paint3;
            this.f5384e = 0.0f;
            this.f5385f = 0.0f;
            this.f5386g = 0.0f;
            this.f5387h = 5.0f;
            this.f5395p = 1.0f;
            this.t = 255;
            paint.setStrokeCap(Paint.Cap.SQUARE);
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.STROKE);
            paint2.setStyle(Paint.Style.FILL);
            paint2.setAntiAlias(true);
            paint3.setColor(0);
        }

        void A() {
            this.f5390k = this.f5384e;
            this.f5391l = this.f5385f;
            this.f5392m = this.f5386g;
        }

        void a(Canvas canvas, Rect rect) {
            RectF rectF = this.f5380a;
            float f2 = this.f5396q;
            float f3 = (this.f5387h / 2.0f) + f2;
            if (f2 <= 0.0f) {
                f3 = (Math.min(rect.width(), rect.height()) / 2.0f) - Math.max((this.f5397r * this.f5395p) / 2.0f, this.f5387h / 2.0f);
            }
            rectF.set(rect.centerX() - f3, rect.centerY() - f3, rect.centerX() + f3, rect.centerY() + f3);
            float f4 = this.f5384e;
            float f5 = this.f5386g;
            float f6 = (f4 + f5) * 360.0f;
            float f7 = ((this.f5385f + f5) * 360.0f) - f6;
            this.f5381b.setColor(this.u);
            this.f5381b.setAlpha(this.t);
            float f8 = this.f5387h / 2.0f;
            rectF.inset(f8, f8);
            canvas.drawCircle(rectF.centerX(), rectF.centerY(), rectF.width() / 2.0f, this.f5383d);
            float f9 = -f8;
            rectF.inset(f9, f9);
            canvas.drawArc(rectF, f6, f7, false, this.f5381b);
            b(canvas, f6, f7, rectF);
        }

        void b(Canvas canvas, float f2, float f3, RectF rectF) {
            if (this.f5393n) {
                Path path = this.f5394o;
                if (path == null) {
                    Path path2 = new Path();
                    this.f5394o = path2;
                    path2.setFillType(Path.FillType.EVEN_ODD);
                } else {
                    path.reset();
                }
                float min = Math.min(rectF.width(), rectF.height()) / 2.0f;
                float f4 = (this.f5397r * this.f5395p) / 2.0f;
                this.f5394o.moveTo(0.0f, 0.0f);
                this.f5394o.lineTo(this.f5397r * this.f5395p, 0.0f);
                Path path3 = this.f5394o;
                float f5 = this.f5397r;
                float f6 = this.f5395p;
                path3.lineTo((f5 * f6) / 2.0f, this.f5398s * f6);
                this.f5394o.offset((min + rectF.centerX()) - f4, rectF.centerY() + (this.f5387h / 2.0f));
                this.f5394o.close();
                this.f5382c.setColor(this.u);
                this.f5382c.setAlpha(this.t);
                canvas.save();
                canvas.rotate(f2 + f3, rectF.centerX(), rectF.centerY());
                canvas.drawPath(this.f5394o, this.f5382c);
                canvas.restore();
            }
        }

        int c() {
            return this.t;
        }

        float d() {
            return this.f5385f;
        }

        int e() {
            return this.f5388i[f()];
        }

        int f() {
            return (this.f5389j + 1) % this.f5388i.length;
        }

        float g() {
            return this.f5384e;
        }

        int h() {
            return this.f5388i[this.f5389j];
        }

        float i() {
            return this.f5391l;
        }

        float j() {
            return this.f5392m;
        }

        float k() {
            return this.f5390k;
        }

        void l() {
            t(f());
        }

        void m() {
            this.f5390k = 0.0f;
            this.f5391l = 0.0f;
            this.f5392m = 0.0f;
            y(0.0f);
            v(0.0f);
            w(0.0f);
        }

        void n(int i2) {
            this.t = i2;
        }

        void o(float f2, float f3) {
            this.f5397r = (int) f2;
            this.f5398s = (int) f3;
        }

        void p(float f2) {
            if (f2 != this.f5395p) {
                this.f5395p = f2;
            }
        }

        void q(float f2) {
            this.f5396q = f2;
        }

        void r(int i2) {
            this.u = i2;
        }

        void s(ColorFilter colorFilter) {
            this.f5381b.setColorFilter(colorFilter);
        }

        void t(int i2) {
            this.f5389j = i2;
            this.u = this.f5388i[i2];
        }

        void u(int[] iArr) {
            this.f5388i = iArr;
            t(0);
        }

        void v(float f2) {
            this.f5385f = f2;
        }

        void w(float f2) {
            this.f5386g = f2;
        }

        void x(boolean z) {
            if (this.f5393n != z) {
                this.f5393n = z;
            }
        }

        void y(float f2) {
            this.f5384e = f2;
        }

        void z(float f2) {
            this.f5387h = f2;
            this.f5381b.setStrokeWidth(f2);
        }
    }

    public CircularProgressDrawable(Context context) {
        this.f5372i = ((Context) Preconditions.h(context)).getResources();
        Ring ring = new Ring();
        this.f5370c = ring;
        ring.u(f5369o);
        k(2.5f);
        m();
    }

    private void a(float f2, Ring ring) {
        n(f2, ring);
        float floor = (float) (Math.floor(ring.j() / 0.8f) + 1.0d);
        ring.y(ring.k() + (((ring.i() - 0.01f) - ring.k()) * f2));
        ring.v(ring.i());
        ring.w(ring.j() + ((floor - ring.j()) * f2));
    }

    private int c(float f2, int i2, int i3) {
        return ((((i2 >> 24) & 255) + ((int) ((((i3 >> 24) & 255) - r5) * f2))) << 24) | ((((i2 >> 16) & 255) + ((int) ((((i3 >> 16) & 255) - r0) * f2))) << 16) | ((((i2 >> 8) & 255) + ((int) ((((i3 >> 8) & 255) - r1) * f2))) << 8) | ((i2 & 255) + ((int) (f2 * ((i3 & 255) - r7))));
    }

    private void h(float f2) {
        this.f5371h = f2;
    }

    private void i(float f2, float f3, float f4, float f5) {
        Ring ring = this.f5370c;
        float f6 = this.f5372i.getDisplayMetrics().density;
        ring.z(f3 * f6);
        ring.q(f2 * f6);
        ring.t(0);
        ring.o(f4 * f6, f5 * f6);
    }

    private void m() {
        final Ring ring = this.f5370c;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: androidx.swiperefreshlayout.widget.CircularProgressDrawable.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                CircularProgressDrawable.this.n(floatValue, ring);
                CircularProgressDrawable.this.b(floatValue, ring, false);
                CircularProgressDrawable.this.invalidateSelf();
            }
        });
        ofFloat.setRepeatCount(-1);
        ofFloat.setRepeatMode(1);
        ofFloat.setInterpolator(f5367m);
        ofFloat.addListener(new Animator.AnimatorListener() { // from class: androidx.swiperefreshlayout.widget.CircularProgressDrawable.2
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
                CircularProgressDrawable.this.b(1.0f, ring, true);
                ring.A();
                ring.l();
                CircularProgressDrawable circularProgressDrawable = CircularProgressDrawable.this;
                if (!circularProgressDrawable.f5375l) {
                    circularProgressDrawable.f5374k += 1.0f;
                    return;
                }
                circularProgressDrawable.f5375l = false;
                animator.cancel();
                animator.setDuration(1332L);
                animator.start();
                ring.x(false);
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                CircularProgressDrawable.this.f5374k = 0.0f;
            }
        });
        this.f5373j = ofFloat;
    }

    void b(float f2, Ring ring, boolean z) {
        float interpolation;
        float f3;
        if (this.f5375l) {
            a(f2, ring);
            return;
        }
        if (f2 != 1.0f || z) {
            float j2 = ring.j();
            if (f2 < 0.5f) {
                interpolation = ring.k();
                f3 = (f5368n.getInterpolation(f2 / 0.5f) * 0.79f) + 0.01f + interpolation;
            } else {
                float k2 = ring.k() + 0.79f;
                interpolation = k2 - (((1.0f - f5368n.getInterpolation((f2 - 0.5f) / 0.5f)) * 0.79f) + 0.01f);
                f3 = k2;
            }
            float f4 = j2 + (0.20999998f * f2);
            float f5 = (f2 + this.f5374k) * 216.0f;
            ring.y(interpolation);
            ring.v(f3);
            ring.w(f4);
            h(f5);
        }
    }

    public void d(boolean z) {
        this.f5370c.x(z);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        canvas.save();
        canvas.rotate(this.f5371h, bounds.exactCenterX(), bounds.exactCenterY());
        this.f5370c.a(canvas, bounds);
        canvas.restore();
    }

    public void e(float f2) {
        this.f5370c.p(f2);
        invalidateSelf();
    }

    public void f(int... iArr) {
        this.f5370c.u(iArr);
        this.f5370c.t(0);
        invalidateSelf();
    }

    public void g(float f2) {
        this.f5370c.w(f2);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.f5370c.c();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        return this.f5373j.isRunning();
    }

    public void j(float f2, float f3) {
        this.f5370c.y(f2);
        this.f5370c.v(f3);
        invalidateSelf();
    }

    public void k(float f2) {
        this.f5370c.z(f2);
        invalidateSelf();
    }

    public void l(int i2) {
        if (i2 == 0) {
            i(11.0f, 3.0f, 12.0f, 6.0f);
        } else {
            i(7.5f, 2.5f, 10.0f, 5.0f);
        }
        invalidateSelf();
    }

    void n(float f2, Ring ring) {
        if (f2 > 0.75f) {
            ring.r(c((f2 - 0.75f) / 0.25f, ring.h(), ring.e()));
        } else {
            ring.r(ring.h());
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        this.f5370c.n(i2);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f5370c.s(colorFilter);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        this.f5373j.cancel();
        this.f5370c.A();
        if (this.f5370c.d() != this.f5370c.g()) {
            this.f5375l = true;
            this.f5373j.setDuration(666L);
            this.f5373j.start();
        } else {
            this.f5370c.t(0);
            this.f5370c.m();
            this.f5373j.setDuration(1332L);
            this.f5373j.start();
        }
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        this.f5373j.cancel();
        h(0.0f);
        this.f5370c.x(false);
        this.f5370c.t(0);
        this.f5370c.m();
        invalidateSelf();
    }
}
