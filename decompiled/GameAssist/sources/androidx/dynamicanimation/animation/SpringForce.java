package androidx.dynamicanimation.animation;

import androidx.dynamicanimation.animation.DynamicAnimation;

/* loaded from: classes.dex */
public final class SpringForce implements Force {

    /* renamed from: a, reason: collision with root package name */
    double f3679a;

    /* renamed from: b, reason: collision with root package name */
    double f3680b;

    /* renamed from: c, reason: collision with root package name */
    private boolean f3681c;

    /* renamed from: d, reason: collision with root package name */
    private double f3682d;

    /* renamed from: e, reason: collision with root package name */
    private double f3683e;

    /* renamed from: f, reason: collision with root package name */
    private double f3684f;

    /* renamed from: g, reason: collision with root package name */
    private double f3685g;

    /* renamed from: h, reason: collision with root package name */
    private double f3686h;

    /* renamed from: i, reason: collision with root package name */
    private double f3687i;

    /* renamed from: j, reason: collision with root package name */
    private final DynamicAnimation.MassState f3688j;

    public SpringForce() {
        this.f3679a = Math.sqrt(1500.0d);
        this.f3680b = 0.5d;
        this.f3681c = false;
        this.f3687i = Double.MAX_VALUE;
        this.f3688j = new DynamicAnimation.MassState();
    }

    private void b() {
        if (this.f3681c) {
            return;
        }
        if (this.f3687i == Double.MAX_VALUE) {
            throw new IllegalStateException("Error: Final position of the spring must be set before the animation starts");
        }
        double d2 = this.f3680b;
        if (d2 > 1.0d) {
            double d3 = this.f3679a;
            this.f3684f = ((-d2) * d3) + (d3 * Math.sqrt((d2 * d2) - 1.0d));
            double d4 = this.f3680b;
            double d5 = this.f3679a;
            this.f3685g = ((-d4) * d5) - (d5 * Math.sqrt((d4 * d4) - 1.0d));
        } else if (d2 >= 0.0d && d2 < 1.0d) {
            this.f3686h = this.f3679a * Math.sqrt(1.0d - (d2 * d2));
        }
        this.f3681c = true;
    }

    public float a() {
        return (float) this.f3687i;
    }

    public boolean c(float f2, float f3) {
        return ((double) Math.abs(f3)) < this.f3683e && ((double) Math.abs(f2 - a())) < this.f3682d;
    }

    public SpringForce d(float f2) {
        if (f2 < 0.0f) {
            throw new IllegalArgumentException("Damping ratio must be non-negative");
        }
        this.f3680b = f2;
        this.f3681c = false;
        return this;
    }

    public SpringForce e(float f2) {
        this.f3687i = f2;
        return this;
    }

    public SpringForce f(float f2) {
        if (f2 <= 0.0f) {
            throw new IllegalArgumentException("Spring stiffness constant must be positive.");
        }
        this.f3679a = Math.sqrt(f2);
        this.f3681c = false;
        return this;
    }

    void g(double d2) {
        double abs = Math.abs(d2);
        this.f3682d = abs;
        this.f3683e = abs * 62.5d;
    }

    DynamicAnimation.MassState h(double d2, double d3, long j2) {
        double cos;
        double d4;
        b();
        double d5 = j2 / 1000.0d;
        double d6 = d2 - this.f3687i;
        double d7 = this.f3680b;
        if (d7 > 1.0d) {
            double d8 = this.f3685g;
            double d9 = this.f3684f;
            double d10 = d6 - (((d8 * d6) - d3) / (d8 - d9));
            double d11 = ((d6 * d8) - d3) / (d8 - d9);
            d4 = (Math.pow(2.718281828459045d, d8 * d5) * d10) + (Math.pow(2.718281828459045d, this.f3684f * d5) * d11);
            double d12 = this.f3685g;
            double pow = d10 * d12 * Math.pow(2.718281828459045d, d12 * d5);
            double d13 = this.f3684f;
            cos = pow + (d11 * d13 * Math.pow(2.718281828459045d, d13 * d5));
        } else if (d7 == 1.0d) {
            double d14 = this.f3679a;
            double d15 = d3 + (d14 * d6);
            double d16 = d6 + (d15 * d5);
            d4 = Math.pow(2.718281828459045d, (-d14) * d5) * d16;
            double pow2 = d16 * Math.pow(2.718281828459045d, (-this.f3679a) * d5);
            double d17 = this.f3679a;
            cos = (d15 * Math.pow(2.718281828459045d, (-d17) * d5)) + (pow2 * (-d17));
        } else {
            double d18 = 1.0d / this.f3686h;
            double d19 = this.f3679a;
            double d20 = d18 * ((d7 * d19 * d6) + d3);
            double pow3 = Math.pow(2.718281828459045d, (-d7) * d19 * d5) * ((Math.cos(this.f3686h * d5) * d6) + (Math.sin(this.f3686h * d5) * d20));
            double d21 = this.f3679a;
            double d22 = this.f3680b;
            double d23 = (-d21) * pow3 * d22;
            double pow4 = Math.pow(2.718281828459045d, (-d22) * d21 * d5);
            double d24 = this.f3686h;
            double sin = (-d24) * d6 * Math.sin(d24 * d5);
            double d25 = this.f3686h;
            cos = d23 + (pow4 * (sin + (d20 * d25 * Math.cos(d25 * d5))));
            d4 = pow3;
        }
        DynamicAnimation.MassState massState = this.f3688j;
        massState.f3671a = (float) (d4 + this.f3687i);
        massState.f3672b = (float) cos;
        return massState;
    }

    public SpringForce(float f2) {
        this.f3679a = Math.sqrt(1500.0d);
        this.f3680b = 0.5d;
        this.f3681c = false;
        this.f3687i = Double.MAX_VALUE;
        this.f3688j = new DynamicAnimation.MassState();
        this.f3687i = f2;
    }
}
