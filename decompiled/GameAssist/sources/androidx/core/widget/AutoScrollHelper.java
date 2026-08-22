package androidx.core.widget;

import android.content.res.Resources;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import androidx.core.view.ViewCompat;

/* loaded from: classes.dex */
public abstract class AutoScrollHelper implements View.OnTouchListener {
    private static final int x = ViewConfiguration.getTapTimeout();

    /* renamed from: i, reason: collision with root package name */
    final View f3527i;

    /* renamed from: j, reason: collision with root package name */
    private Runnable f3528j;

    /* renamed from: m, reason: collision with root package name */
    private int f3531m;

    /* renamed from: n, reason: collision with root package name */
    private int f3532n;

    /* renamed from: r, reason: collision with root package name */
    private boolean f3536r;

    /* renamed from: s, reason: collision with root package name */
    boolean f3537s;
    boolean t;
    boolean u;
    private boolean v;
    private boolean w;

    /* renamed from: c, reason: collision with root package name */
    final ClampedScroller f3525c = new ClampedScroller();

    /* renamed from: h, reason: collision with root package name */
    private final Interpolator f3526h = new AccelerateInterpolator();

    /* renamed from: k, reason: collision with root package name */
    private float[] f3529k = {0.0f, 0.0f};

    /* renamed from: l, reason: collision with root package name */
    private float[] f3530l = {Float.MAX_VALUE, Float.MAX_VALUE};

    /* renamed from: o, reason: collision with root package name */
    private float[] f3533o = {0.0f, 0.0f};

    /* renamed from: p, reason: collision with root package name */
    private float[] f3534p = {0.0f, 0.0f};

    /* renamed from: q, reason: collision with root package name */
    private float[] f3535q = {Float.MAX_VALUE, Float.MAX_VALUE};

    private static class ClampedScroller {

        /* renamed from: a, reason: collision with root package name */
        private int f3538a;

        /* renamed from: b, reason: collision with root package name */
        private int f3539b;

        /* renamed from: c, reason: collision with root package name */
        private float f3540c;

        /* renamed from: d, reason: collision with root package name */
        private float f3541d;

        /* renamed from: j, reason: collision with root package name */
        private float f3547j;

        /* renamed from: k, reason: collision with root package name */
        private int f3548k;

        /* renamed from: e, reason: collision with root package name */
        private long f3542e = Long.MIN_VALUE;

        /* renamed from: i, reason: collision with root package name */
        private long f3546i = -1;

        /* renamed from: f, reason: collision with root package name */
        private long f3543f = 0;

        /* renamed from: g, reason: collision with root package name */
        private int f3544g = 0;

        /* renamed from: h, reason: collision with root package name */
        private int f3545h = 0;

        ClampedScroller() {
        }

        private float e(long j2) {
            if (j2 < this.f3542e) {
                return 0.0f;
            }
            long j3 = this.f3546i;
            if (j3 < 0 || j2 < j3) {
                return AutoScrollHelper.e((j2 - r0) / this.f3538a, 0.0f, 1.0f) * 0.5f;
            }
            float f2 = this.f3547j;
            return (1.0f - f2) + (f2 * AutoScrollHelper.e((j2 - j3) / this.f3548k, 0.0f, 1.0f));
        }

        private float g(float f2) {
            return ((-4.0f) * f2 * f2) + (f2 * 4.0f);
        }

        public void a() {
            if (this.f3543f == 0) {
                throw new RuntimeException("Cannot compute scroll delta before calling start()");
            }
            long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            float g2 = g(e(currentAnimationTimeMillis));
            long j2 = currentAnimationTimeMillis - this.f3543f;
            this.f3543f = currentAnimationTimeMillis;
            float f2 = j2 * g2;
            this.f3544g = (int) (this.f3540c * f2);
            this.f3545h = (int) (f2 * this.f3541d);
        }

        public int b() {
            return this.f3544g;
        }

        public int c() {
            return this.f3545h;
        }

        public int d() {
            float f2 = this.f3540c;
            return (int) (f2 / Math.abs(f2));
        }

        public int f() {
            float f2 = this.f3541d;
            return (int) (f2 / Math.abs(f2));
        }

        public boolean h() {
            return this.f3546i > 0 && AnimationUtils.currentAnimationTimeMillis() > this.f3546i + ((long) this.f3548k);
        }

        public void i() {
            long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            this.f3548k = AutoScrollHelper.f((int) (currentAnimationTimeMillis - this.f3542e), 0, this.f3539b);
            this.f3547j = e(currentAnimationTimeMillis);
            this.f3546i = currentAnimationTimeMillis;
        }

        public void j(int i2) {
            this.f3539b = i2;
        }

        public void k(int i2) {
            this.f3538a = i2;
        }

        public void l(float f2, float f3) {
            this.f3540c = f2;
            this.f3541d = f3;
        }

        public void m() {
            long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            this.f3542e = currentAnimationTimeMillis;
            this.f3546i = -1L;
            this.f3543f = currentAnimationTimeMillis;
            this.f3547j = 0.5f;
            this.f3544g = 0;
            this.f3545h = 0;
        }
    }

    private class ScrollAnimationRunnable implements Runnable {
        ScrollAnimationRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AutoScrollHelper autoScrollHelper = AutoScrollHelper.this;
            if (autoScrollHelper.u) {
                if (autoScrollHelper.f3537s) {
                    autoScrollHelper.f3537s = false;
                    autoScrollHelper.f3525c.m();
                }
                ClampedScroller clampedScroller = AutoScrollHelper.this.f3525c;
                if (clampedScroller.h() || !AutoScrollHelper.this.u()) {
                    AutoScrollHelper.this.u = false;
                    return;
                }
                AutoScrollHelper autoScrollHelper2 = AutoScrollHelper.this;
                if (autoScrollHelper2.t) {
                    autoScrollHelper2.t = false;
                    autoScrollHelper2.c();
                }
                clampedScroller.a();
                AutoScrollHelper.this.j(clampedScroller.b(), clampedScroller.c());
                ViewCompat.a0(AutoScrollHelper.this.f3527i, this);
            }
        }
    }

    public AutoScrollHelper(View view) {
        this.f3527i = view;
        float f2 = Resources.getSystem().getDisplayMetrics().density;
        float f3 = (int) ((1575.0f * f2) + 0.5f);
        o(f3, f3);
        float f4 = (int) ((f2 * 315.0f) + 0.5f);
        p(f4, f4);
        l(1);
        n(Float.MAX_VALUE, Float.MAX_VALUE);
        s(0.2f, 0.2f);
        t(1.0f, 1.0f);
        k(x);
        r(500);
        q(500);
    }

    private float d(int i2, float f2, float f3, float f4) {
        float h2 = h(this.f3529k[i2], f3, this.f3530l[i2], f2);
        if (h2 == 0.0f) {
            return 0.0f;
        }
        float f5 = this.f3533o[i2];
        float f6 = this.f3534p[i2];
        float f7 = this.f3535q[i2];
        float f8 = f5 * f4;
        return h2 > 0.0f ? e(h2 * f8, f6, f7) : -e((-h2) * f8, f6, f7);
    }

    static float e(float f2, float f3, float f4) {
        return f2 > f4 ? f4 : f2 < f3 ? f3 : f2;
    }

    static int f(int i2, int i3, int i4) {
        return i2 > i4 ? i4 : i2 < i3 ? i3 : i2;
    }

    private float g(float f2, float f3) {
        if (f3 == 0.0f) {
            return 0.0f;
        }
        int i2 = this.f3531m;
        if (i2 == 0 || i2 == 1) {
            if (f2 < f3) {
                if (f2 >= 0.0f) {
                    return 1.0f - (f2 / f3);
                }
                if (this.u && i2 == 1) {
                    return 1.0f;
                }
            }
        } else if (i2 == 2 && f2 < 0.0f) {
            return f2 / (-f3);
        }
        return 0.0f;
    }

    private float h(float f2, float f3, float f4, float f5) {
        float interpolation;
        float e2 = e(f2 * f3, 0.0f, f4);
        float g2 = g(f3 - f5, e2) - g(f5, e2);
        if (g2 < 0.0f) {
            interpolation = -this.f3526h.getInterpolation(-g2);
        } else {
            if (g2 <= 0.0f) {
                return 0.0f;
            }
            interpolation = this.f3526h.getInterpolation(g2);
        }
        return e(interpolation, -1.0f, 1.0f);
    }

    private void i() {
        if (this.f3537s) {
            this.u = false;
        } else {
            this.f3525c.i();
        }
    }

    private void v() {
        int i2;
        if (this.f3528j == null) {
            this.f3528j = new ScrollAnimationRunnable();
        }
        this.u = true;
        this.f3537s = true;
        if (this.f3536r || (i2 = this.f3532n) <= 0) {
            this.f3528j.run();
        } else {
            ViewCompat.b0(this.f3527i, this.f3528j, i2);
        }
        this.f3536r = true;
    }

    public abstract boolean a(int i2);

    public abstract boolean b(int i2);

    void c() {
        long uptimeMillis = SystemClock.uptimeMillis();
        MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
        this.f3527i.onTouchEvent(obtain);
        obtain.recycle();
    }

    public abstract void j(int i2, int i3);

    public AutoScrollHelper k(int i2) {
        this.f3532n = i2;
        return this;
    }

    public AutoScrollHelper l(int i2) {
        this.f3531m = i2;
        return this;
    }

    public AutoScrollHelper m(boolean z) {
        if (this.v && !z) {
            i();
        }
        this.v = z;
        return this;
    }

    public AutoScrollHelper n(float f2, float f3) {
        float[] fArr = this.f3530l;
        fArr[0] = f2;
        fArr[1] = f3;
        return this;
    }

    public AutoScrollHelper o(float f2, float f3) {
        float[] fArr = this.f3535q;
        fArr[0] = f2 / 1000.0f;
        fArr[1] = f3 / 1000.0f;
        return this;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0013, code lost:
    
        if (r0 != 3) goto L20;
     */
    @Override // android.view.View.OnTouchListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouch(android.view.View r6, android.view.MotionEvent r7) {
        /*
            r5 = this;
            boolean r0 = r5.v
            r1 = 0
            if (r0 != 0) goto L6
            return r1
        L6:
            int r0 = r7.getActionMasked()
            r2 = 1
            if (r0 == 0) goto L1a
            if (r0 == r2) goto L16
            r3 = 2
            if (r0 == r3) goto L1e
            r6 = 3
            if (r0 == r6) goto L16
            goto L58
        L16:
            r5.i()
            goto L58
        L1a:
            r5.t = r2
            r5.f3536r = r1
        L1e:
            float r0 = r7.getX()
            int r3 = r6.getWidth()
            float r3 = (float) r3
            android.view.View r4 = r5.f3527i
            int r4 = r4.getWidth()
            float r4 = (float) r4
            float r0 = r5.d(r1, r0, r3, r4)
            float r7 = r7.getY()
            int r6 = r6.getHeight()
            float r6 = (float) r6
            android.view.View r3 = r5.f3527i
            int r3 = r3.getHeight()
            float r3 = (float) r3
            float r6 = r5.d(r2, r7, r6, r3)
            androidx.core.widget.AutoScrollHelper$ClampedScroller r7 = r5.f3525c
            r7.l(r0, r6)
            boolean r6 = r5.u
            if (r6 != 0) goto L58
            boolean r6 = r5.u()
            if (r6 == 0) goto L58
            r5.v()
        L58:
            boolean r6 = r5.w
            if (r6 == 0) goto L61
            boolean r5 = r5.u
            if (r5 == 0) goto L61
            r1 = r2
        L61:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.widget.AutoScrollHelper.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    public AutoScrollHelper p(float f2, float f3) {
        float[] fArr = this.f3534p;
        fArr[0] = f2 / 1000.0f;
        fArr[1] = f3 / 1000.0f;
        return this;
    }

    public AutoScrollHelper q(int i2) {
        this.f3525c.j(i2);
        return this;
    }

    public AutoScrollHelper r(int i2) {
        this.f3525c.k(i2);
        return this;
    }

    public AutoScrollHelper s(float f2, float f3) {
        float[] fArr = this.f3529k;
        fArr[0] = f2;
        fArr[1] = f3;
        return this;
    }

    public AutoScrollHelper t(float f2, float f3) {
        float[] fArr = this.f3533o;
        fArr[0] = f2 / 1000.0f;
        fArr[1] = f3 / 1000.0f;
        return this;
    }

    boolean u() {
        ClampedScroller clampedScroller = this.f3525c;
        int f2 = clampedScroller.f();
        int d2 = clampedScroller.d();
        return (f2 != 0 && b(f2)) || (d2 != 0 && a(d2));
    }
}
