package androidx.dynamicanimation.animation;

import androidx.dynamicanimation.animation.DynamicAnimation;

/* loaded from: classes.dex */
public final class FlingAnimation extends DynamicAnimation<FlingAnimation> {
    private final DragForce B;

    static final class DragForce implements Force {

        /* renamed from: a, reason: collision with root package name */
        private float f3673a;

        /* renamed from: b, reason: collision with root package name */
        private float f3674b;

        /* renamed from: c, reason: collision with root package name */
        private final DynamicAnimation.MassState f3675c;

        public boolean a(float f2, float f3) {
            return Math.abs(f3) < this.f3674b;
        }

        DynamicAnimation.MassState b(float f2, float f3, long j2) {
            this.f3675c.f3672b = (float) (f3 * Math.exp((j2 / 1000.0f) * this.f3673a));
            DynamicAnimation.MassState massState = this.f3675c;
            float f4 = massState.f3672b;
            float f5 = f2 + ((f4 - f3) / this.f3673a);
            massState.f3671a = f5;
            if (a(f5, f4)) {
                this.f3675c.f3672b = 0.0f;
            }
            return this.f3675c;
        }
    }

    @Override // androidx.dynamicanimation.animation.DynamicAnimation
    boolean m(long j2) {
        DynamicAnimation.MassState b2 = this.B.b(this.f3658b, this.f3657a, j2);
        float f2 = b2.f3671a;
        this.f3658b = f2;
        float f3 = b2.f3672b;
        this.f3657a = f3;
        float f4 = this.f3664h;
        if (f2 < f4) {
            this.f3658b = f4;
            return true;
        }
        float f5 = this.f3663g;
        if (f2 <= f5) {
            return n(f2, f3);
        }
        this.f3658b = f5;
        return true;
    }

    boolean n(float f2, float f3) {
        return f2 >= this.f3663g || f2 <= this.f3664h || this.B.a(f2, f3);
    }
}
