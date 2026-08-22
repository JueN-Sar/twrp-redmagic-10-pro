package com.airbnb.lottie.utils;

import android.view.Choreographer;
import androidx.annotation.VisibleForTesting;
import com.airbnb.lottie.L;
import com.airbnb.lottie.LottieComposition;

/* loaded from: classes.dex */
public class LottieValueAnimator extends BaseLottieAnimator implements Choreographer.FrameCallback {

    /* renamed from: r, reason: collision with root package name */
    private LottieComposition f9930r;

    /* renamed from: j, reason: collision with root package name */
    private float f9922j = 1.0f;

    /* renamed from: k, reason: collision with root package name */
    private boolean f9923k = false;

    /* renamed from: l, reason: collision with root package name */
    private long f9924l = 0;

    /* renamed from: m, reason: collision with root package name */
    private float f9925m = 0.0f;

    /* renamed from: n, reason: collision with root package name */
    private float f9926n = 0.0f;

    /* renamed from: o, reason: collision with root package name */
    private int f9927o = 0;

    /* renamed from: p, reason: collision with root package name */
    private float f9928p = -2.1474836E9f;

    /* renamed from: q, reason: collision with root package name */
    private float f9929q = 2.1474836E9f;

    @VisibleForTesting
    protected boolean running = false;

    /* renamed from: s, reason: collision with root package name */
    private boolean f9931s = false;

    private void E() {
        if (this.f9930r == null) {
            return;
        }
        float f2 = this.f9926n;
        if (f2 < this.f9928p || f2 > this.f9929q) {
            throw new IllegalStateException(String.format("Frame must be [%f,%f]. It is %f", Float.valueOf(this.f9928p), Float.valueOf(this.f9929q), Float.valueOf(this.f9926n)));
        }
    }

    private float l() {
        LottieComposition lottieComposition = this.f9930r;
        if (lottieComposition == null) {
            return Float.MAX_VALUE;
        }
        return (1.0E9f / lottieComposition.i()) / Math.abs(this.f9922j);
    }

    private boolean p() {
        return o() < 0.0f;
    }

    public void A(float f2, float f3) {
        if (f2 > f3) {
            throw new IllegalArgumentException(String.format("minFrame (%s) must be <= maxFrame (%s)", Float.valueOf(f2), Float.valueOf(f3)));
        }
        LottieComposition lottieComposition = this.f9930r;
        float p2 = lottieComposition == null ? -3.4028235E38f : lottieComposition.p();
        LottieComposition lottieComposition2 = this.f9930r;
        float f4 = lottieComposition2 == null ? Float.MAX_VALUE : lottieComposition2.f();
        float b2 = MiscUtils.b(f2, p2, f4);
        float b3 = MiscUtils.b(f3, p2, f4);
        if (b2 == this.f9928p && b3 == this.f9929q) {
            return;
        }
        this.f9928p = b2;
        this.f9929q = b3;
        y((int) MiscUtils.b(this.f9926n, b2, b3));
    }

    public void B(int i2) {
        A(i2, (int) this.f9929q);
    }

    public void C(float f2) {
        this.f9922j = f2;
    }

    public void D(boolean z) {
        this.f9931s = z;
    }

    @Override // com.airbnb.lottie.utils.BaseLottieAnimator
    void a() {
        super.a();
        b(p());
    }

    @Override // android.animation.ValueAnimator, android.animation.Animator
    public void cancel() {
        a();
        t();
    }

    @Override // android.view.Choreographer.FrameCallback
    public void doFrame(long j2) {
        s();
        if (this.f9930r == null || !isRunning()) {
            return;
        }
        L.b("LottieValueAnimator#doFrame");
        float l2 = (this.f9924l != 0 ? j2 - r1 : 0L) / l();
        float f2 = this.f9925m;
        if (p()) {
            l2 = -l2;
        }
        float f3 = f2 + l2;
        boolean z = !MiscUtils.d(f3, n(), m());
        float f4 = this.f9925m;
        float b2 = MiscUtils.b(f3, n(), m());
        this.f9925m = b2;
        if (this.f9931s) {
            b2 = (float) Math.floor(b2);
        }
        this.f9926n = b2;
        this.f9924l = j2;
        if (!this.f9931s || this.f9925m != f4) {
            h();
        }
        if (z) {
            if (getRepeatCount() == -1 || this.f9927o < getRepeatCount()) {
                e();
                this.f9927o++;
                if (getRepeatMode() == 2) {
                    this.f9923k = !this.f9923k;
                    w();
                } else {
                    float m2 = p() ? m() : n();
                    this.f9925m = m2;
                    this.f9926n = m2;
                }
                this.f9924l = j2;
            } else {
                float n2 = this.f9922j < 0.0f ? n() : m();
                this.f9925m = n2;
                this.f9926n = n2;
                t();
                b(p());
            }
        }
        E();
        L.c("LottieValueAnimator#doFrame");
    }

    public void endAnimation() {
        t();
        b(p());
    }

    @Override // android.animation.ValueAnimator
    public float getAnimatedFraction() {
        float n2;
        float m2;
        float n3;
        if (this.f9930r == null) {
            return 0.0f;
        }
        if (p()) {
            n2 = m() - this.f9926n;
            m2 = m();
            n3 = n();
        } else {
            n2 = this.f9926n - n();
            m2 = m();
            n3 = n();
        }
        return n2 / (m2 - n3);
    }

    @Override // android.animation.ValueAnimator
    public Object getAnimatedValue() {
        return Float.valueOf(j());
    }

    @Override // android.animation.ValueAnimator, android.animation.Animator
    public long getDuration() {
        LottieComposition lottieComposition = this.f9930r;
        if (lottieComposition == null) {
            return 0L;
        }
        return (long) lottieComposition.d();
    }

    public void i() {
        this.f9930r = null;
        this.f9928p = -2.1474836E9f;
        this.f9929q = 2.1474836E9f;
    }

    @Override // android.animation.ValueAnimator, android.animation.Animator
    public boolean isRunning() {
        return this.running;
    }

    public float j() {
        LottieComposition lottieComposition = this.f9930r;
        if (lottieComposition == null) {
            return 0.0f;
        }
        return (this.f9926n - lottieComposition.p()) / (this.f9930r.f() - this.f9930r.p());
    }

    public float k() {
        return this.f9926n;
    }

    public float m() {
        LottieComposition lottieComposition = this.f9930r;
        if (lottieComposition == null) {
            return 0.0f;
        }
        float f2 = this.f9929q;
        return f2 == 2.1474836E9f ? lottieComposition.f() : f2;
    }

    public float n() {
        LottieComposition lottieComposition = this.f9930r;
        if (lottieComposition == null) {
            return 0.0f;
        }
        float f2 = this.f9928p;
        return f2 == -2.1474836E9f ? lottieComposition.p() : f2;
    }

    public float o() {
        return this.f9922j;
    }

    public void q() {
        t();
        c();
    }

    public void r() {
        this.running = true;
        g(p());
        y((int) (p() ? m() : n()));
        this.f9924l = 0L;
        this.f9927o = 0;
        s();
    }

    protected void s() {
        if (isRunning()) {
            u(false);
            Choreographer.getInstance().postFrameCallback(this);
        }
    }

    @Override // android.animation.ValueAnimator
    public void setRepeatMode(int i2) {
        super.setRepeatMode(i2);
        if (i2 == 2 || !this.f9923k) {
            return;
        }
        this.f9923k = false;
        w();
    }

    protected void t() {
        u(true);
    }

    protected void u(boolean z) {
        Choreographer.getInstance().removeFrameCallback(this);
        if (z) {
            this.running = false;
        }
    }

    public void v() {
        this.running = true;
        s();
        this.f9924l = 0L;
        if (p() && k() == n()) {
            y(m());
        } else if (!p() && k() == m()) {
            y(n());
        }
        f();
    }

    public void w() {
        C(-o());
    }

    public void x(LottieComposition lottieComposition) {
        boolean z = this.f9930r == null;
        this.f9930r = lottieComposition;
        if (z) {
            A(Math.max(this.f9928p, lottieComposition.p()), Math.min(this.f9929q, lottieComposition.f()));
        } else {
            A((int) lottieComposition.p(), (int) lottieComposition.f());
        }
        float f2 = this.f9926n;
        this.f9926n = 0.0f;
        this.f9925m = 0.0f;
        y((int) f2);
        h();
    }

    public void y(float f2) {
        if (this.f9925m == f2) {
            return;
        }
        float b2 = MiscUtils.b(f2, n(), m());
        this.f9925m = b2;
        if (this.f9931s) {
            b2 = (float) Math.floor(b2);
        }
        this.f9926n = b2;
        this.f9924l = 0L;
        h();
    }

    public void z(float f2) {
        A(this.f9928p, f2);
    }
}
