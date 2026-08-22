package com.zte.mifavor.animation;

import android.os.SystemClock;
import com.zte.mifavor.animation.DynamicAnimation;

/* loaded from: classes2.dex */
public final class SpringAnimationZTEX extends DynamicAnimation<SpringAnimationZTEX> {
    private float B;
    private long C;
    private float D;
    private long E;
    private float F;
    private long G;
    private boolean H;
    private float I;
    private SpringForce J;

    private void n() {
        if (this.E <= 1 || this.G < 1 || SystemClock.elapsedRealtime() - this.E <= this.G) {
            return;
        }
        this.E = 0L;
        this.G = 0L;
        l(this.F, this.B, this.D);
    }

    private void o() {
        SpringForce springForce = this.J;
        if (springForce == null) {
            throw new UnsupportedOperationException("Incomplete SpringAnimation: Either final position or a spring force needs to be set.");
        }
        double b2 = springForce.b();
        if (b2 > this.f17280d) {
            throw new UnsupportedOperationException("Final position of the spring cannot be greater than the max value.");
        }
        if (b2 < this.f17281e) {
            throw new UnsupportedOperationException("Final position of the spring cannot be less than the min value.");
        }
    }

    @Override // com.zte.mifavor.animation.DynamicAnimation
    public void h() {
        o();
        this.J.j(d());
        super.h();
    }

    @Override // com.zte.mifavor.animation.DynamicAnimation
    boolean j(long j2) {
        if (this.H) {
            if (Float.compare(this.I, Float.MAX_VALUE) != 0) {
                this.J.h(this.I);
                this.I = Float.MAX_VALUE;
            }
            this.f17288l = this.J.b();
            this.f17289m = 0.0f;
            this.H = false;
            return true;
        }
        n();
        if (Float.compare(this.I, Float.MAX_VALUE) != 0) {
            long j3 = j2 / 2;
            DynamicAnimation.MassState m2 = this.J.m(this.f17288l, this.f17289m, j3);
            this.J.h(this.I);
            this.I = Float.MAX_VALUE;
            DynamicAnimation.MassState m3 = this.J.m(m2.f17291a, m2.f17292b, j3);
            this.f17288l = m3.f17291a;
            this.f17289m = m3.f17292b;
        } else {
            DynamicAnimation.MassState m4 = this.J.m(this.f17288l, this.f17289m, j2);
            this.f17288l = m4.f17291a;
            this.f17289m = m4.f17292b;
        }
        float max = Math.max(this.f17288l, this.f17281e);
        this.f17288l = max;
        float min = Math.min(max, this.f17280d);
        this.f17288l = min;
        if (!m(min, this.f17289m)) {
            return false;
        }
        this.f17288l = this.J.b();
        this.f17289m = 0.0f;
        return true;
    }

    public void k(float f2) {
        if (e()) {
            this.I = f2;
            return;
        }
        if (this.J == null) {
            this.J = new SpringForce(f2);
        }
        this.J.h(f2);
        h();
    }

    public void l(float f2, float f3, float f4) {
        SpringForce springForce = this.J;
        if (springForce == null) {
            SpringForce springForce2 = new SpringForce(f4);
            this.J = springForce2;
            springForce2.i(f2);
            this.J.g(f3);
            return;
        }
        springForce.f(this.C);
        this.J.a(f2, f3);
        if (e()) {
            k(f4);
        } else {
            this.J.h(f4);
        }
    }

    boolean m(float f2, float f3) {
        return this.J.d(f2, f3);
    }
}
