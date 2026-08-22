package androidx.dynamicanimation.animation;

import android.util.AndroidRuntimeException;
import androidx.dynamicanimation.animation.DynamicAnimation;

/* loaded from: classes.dex */
public final class SpringAnimation extends DynamicAnimation<SpringAnimation> {
    private SpringForce B;
    private float C;
    private boolean D;

    public SpringAnimation(Object obj, FloatPropertyCompat floatPropertyCompat) {
        super(obj, floatPropertyCompat);
        this.B = null;
        this.C = Float.MAX_VALUE;
        this.D = false;
    }

    private void r() {
        SpringForce springForce = this.B;
        if (springForce == null) {
            throw new UnsupportedOperationException("Incomplete SpringAnimation: Either final position or a spring force needs to be set.");
        }
        double a2 = springForce.a();
        if (a2 > this.f3663g) {
            throw new UnsupportedOperationException("Final position of the spring cannot be greater than the max value.");
        }
        if (a2 < this.f3664h) {
            throw new UnsupportedOperationException("Final position of the spring cannot be less than the min value.");
        }
    }

    @Override // androidx.dynamicanimation.animation.DynamicAnimation
    public void b() {
        super.b();
        float f2 = this.C;
        if (f2 != Float.MAX_VALUE) {
            SpringForce springForce = this.B;
            if (springForce == null) {
                this.B = new SpringForce(f2);
            } else {
                springForce.e(f2);
            }
            this.C = Float.MAX_VALUE;
        }
    }

    @Override // androidx.dynamicanimation.animation.DynamicAnimation
    public void k() {
        r();
        this.B.g(e());
        super.k();
    }

    @Override // androidx.dynamicanimation.animation.DynamicAnimation
    boolean m(long j2) {
        if (this.D) {
            float f2 = this.C;
            if (f2 != Float.MAX_VALUE) {
                this.B.e(f2);
                this.C = Float.MAX_VALUE;
            }
            this.f3658b = this.B.a();
            this.f3657a = 0.0f;
            this.D = false;
            return true;
        }
        if (this.C != Float.MAX_VALUE) {
            long j3 = j2 / 2;
            DynamicAnimation.MassState h2 = this.B.h(this.f3658b, this.f3657a, j3);
            this.B.e(this.C);
            this.C = Float.MAX_VALUE;
            DynamicAnimation.MassState h3 = this.B.h(h2.f3671a, h2.f3672b, j3);
            this.f3658b = h3.f3671a;
            this.f3657a = h3.f3672b;
        } else {
            DynamicAnimation.MassState h4 = this.B.h(this.f3658b, this.f3657a, j2);
            this.f3658b = h4.f3671a;
            this.f3657a = h4.f3672b;
        }
        float max = Math.max(this.f3658b, this.f3664h);
        this.f3658b = max;
        float min = Math.min(max, this.f3663g);
        this.f3658b = min;
        if (!q(min, this.f3657a)) {
            return false;
        }
        this.f3658b = this.B.a();
        this.f3657a = 0.0f;
        return true;
    }

    public void n(float f2) {
        if (f()) {
            this.C = f2;
            return;
        }
        if (this.B == null) {
            this.B = new SpringForce(f2);
        }
        this.B.e(f2);
        k();
    }

    public boolean o() {
        return this.B.f3680b > 0.0d;
    }

    public SpringForce p() {
        return this.B;
    }

    boolean q(float f2, float f3) {
        return this.B.c(f2, f3);
    }

    public SpringAnimation s(SpringForce springForce) {
        this.B = springForce;
        return this;
    }

    public void t() {
        if (!o()) {
            throw new UnsupportedOperationException("Spring animations can only come to an end when there is damping");
        }
        if (!getAnimationHandler().i()) {
            throw new AndroidRuntimeException("Animations may only be started on the same thread as the animation handler");
        }
        if (this.f3662f) {
            this.D = true;
        }
    }

    public SpringAnimation(Object obj, FloatPropertyCompat floatPropertyCompat, float f2) {
        super(obj, floatPropertyCompat);
        this.B = null;
        this.C = Float.MAX_VALUE;
        this.D = false;
        this.B = new SpringForce(f2);
    }
}
