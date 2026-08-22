package com.zte.mifavor.animation;

import com.zte.mifavor.animation.DynamicAnimation;

/* loaded from: classes2.dex */
public final class SpringForce implements Force {

    /* renamed from: c, reason: collision with root package name */
    private double f17298c;

    /* renamed from: e, reason: collision with root package name */
    private double f17300e;

    /* renamed from: f, reason: collision with root package name */
    private double f17301f;

    /* renamed from: g, reason: collision with root package name */
    private double f17302g;

    /* renamed from: p, reason: collision with root package name */
    private double f17311p;

    /* renamed from: q, reason: collision with root package name */
    private double f17312q;

    /* renamed from: k, reason: collision with root package name */
    double f17306k = Math.sqrt(1500.0d);

    /* renamed from: d, reason: collision with root package name */
    double f17299d = 0.5d;

    /* renamed from: h, reason: collision with root package name */
    private boolean f17303h = false;

    /* renamed from: a, reason: collision with root package name */
    private long f17296a = 0;

    /* renamed from: i, reason: collision with root package name */
    private boolean f17304i = false;

    /* renamed from: b, reason: collision with root package name */
    private long f17297b = 0;

    /* renamed from: m, reason: collision with root package name */
    private double f17308m = 0.0d;

    /* renamed from: l, reason: collision with root package name */
    private double f17307l = 0.0d;

    /* renamed from: o, reason: collision with root package name */
    private double f17310o = 0.0d;

    /* renamed from: n, reason: collision with root package name */
    private double f17309n = 0.0d;

    /* renamed from: j, reason: collision with root package name */
    private final DynamicAnimation.MassState f17305j = new DynamicAnimation.MassState();

    public SpringForce(float f2) {
        this.f17300e = Double.MAX_VALUE;
        this.f17300e = f2;
    }

    private void c() {
        if (this.f17303h) {
            return;
        }
        if (Double.compare(this.f17300e, Double.MAX_VALUE) == 0) {
            throw new IllegalStateException("Error: Final position of the spring must be set before the animation starts");
        }
        double d2 = this.f17299d;
        if (d2 > 1.0d) {
            double d3 = this.f17306k;
            this.f17302g = ((-d2) * d3) + (d3 * Math.sqrt((d2 * d2) - 1.0d));
            double d4 = this.f17299d;
            double d5 = this.f17306k;
            this.f17301f = ((-d4) * d5) - (d5 * Math.sqrt((d4 * d4) - 1.0d));
        } else if (d2 >= 0.0d && d2 < 1.0d) {
            this.f17298c = this.f17306k * Math.sqrt(1.0d - (d2 * d2));
        }
        this.f17303h = true;
    }

    private double e(double d2, double d3, float f2) {
        return ((d3 - d2) * f2) + d2;
    }

    private float k(float f2) {
        return f2 * f2 * (3.0f - (f2 * 2.0f));
    }

    private void l() {
        if (this.f17304i) {
            float currentTimeMillis = System.currentTimeMillis() - this.f17297b;
            long j2 = this.f17296a;
            if (currentTimeMillis >= j2) {
                this.f17304i = false;
                i((float) this.f17310o);
                g((float) this.f17309n);
            } else {
                float k2 = k(currentTimeMillis / j2);
                double e2 = e(this.f17308m, this.f17310o, k2);
                double e3 = e(this.f17307l, this.f17309n, k2);
                i((float) e2);
                g((float) e3);
            }
        }
    }

    public void a(float f2, float f3) {
        if (this.f17296a <= 0) {
            i(f2);
            g(f3);
            return;
        }
        double d2 = this.f17306k;
        this.f17308m = d2 * d2;
        this.f17307l = this.f17299d;
        this.f17310o = f2;
        this.f17309n = f3;
        this.f17304i = true;
        this.f17297b = System.currentTimeMillis();
    }

    public float b() {
        return (float) this.f17300e;
    }

    public boolean d(float f2, float f3) {
        return ((double) Math.abs(f3)) < this.f17312q && ((double) Math.abs(f2 - b())) < this.f17311p;
    }

    public SpringForce f(long j2) {
        if (j2 < 0) {
            throw new IllegalArgumentException("Blend duration must be non-negative");
        }
        this.f17296a = j2;
        return this;
    }

    public SpringForce g(float f2) {
        if (f2 < 0.0f) {
            throw new IllegalArgumentException("Damping ratio must be non-negative");
        }
        this.f17299d = f2;
        this.f17303h = false;
        return this;
    }

    public SpringForce h(float f2) {
        this.f17300e = f2;
        return this;
    }

    public SpringForce i(float f2) {
        if (f2 <= 0.0f) {
            throw new IllegalArgumentException("Spring stiffness constant must be positive.");
        }
        this.f17306k = Math.sqrt(f2);
        this.f17303h = false;
        return this;
    }

    public void j(double d2) {
        double abs = Math.abs(d2);
        this.f17311p = abs;
        this.f17312q = abs * 62.5d;
    }

    public DynamicAnimation.MassState m(double d2, double d3, long j2) {
        double pow;
        double d4;
        l();
        c();
        double d5 = j2 / 1.0E9d;
        double d6 = d2 - this.f17300e;
        double d7 = this.f17299d;
        if (d7 > 1.0d) {
            double d8 = this.f17301f;
            double d9 = this.f17302g;
            double d10 = d6 - (((d8 * d6) - d3) / (d8 - d9));
            double d11 = ((d6 * d8) - d3) / (d8 - d9);
            d4 = (Math.pow(2.718281828459045d, d8 * d5) * d10) + (Math.pow(2.718281828459045d, this.f17302g * d5) * d11);
            double d12 = this.f17301f;
            double pow2 = d10 * d12 * Math.pow(2.718281828459045d, d12 * d5);
            double d13 = this.f17302g;
            pow = pow2 + (d11 * d13 * Math.pow(2.718281828459045d, d13 * d5));
        } else if (d7 != 1.0d) {
            double d14 = 1.0d / this.f17298c;
            double d15 = this.f17306k;
            double d16 = d14 * ((d7 * d15 * d6) + d3);
            double pow3 = Math.pow(2.718281828459045d, (-d7) * d15 * d5) * ((Math.cos(this.f17298c * d5) * d6) + (Math.sin(this.f17298c * d5) * d16));
            double d17 = this.f17306k;
            double d18 = this.f17299d;
            double pow4 = Math.pow(2.718281828459045d, (-d18) * d17 * d5);
            double d19 = this.f17298c;
            pow = ((-d17) * pow3 * d18) + (pow4 * (((-d19) * d6 * Math.sin(d19 * d5)) + (Math.cos(this.f17298c * d5) * this.f17298c * d16)));
            d4 = pow3;
        } else {
            double d20 = this.f17306k;
            double d21 = d3 + (d20 * d6);
            double d22 = (d21 * d5) + d6;
            double pow5 = Math.pow(2.718281828459045d, (-d20) * d5) * d22;
            double pow6 = d22 * Math.pow(2.718281828459045d, (-this.f17306k) * d5);
            double d23 = this.f17306k;
            pow = (pow6 * (-d23)) + (Math.pow(2.718281828459045d, (-d23) * d5) * d21);
            d4 = pow5;
        }
        DynamicAnimation.MassState massState = this.f17305j;
        massState.f17291a = (float) (this.f17300e + d4);
        massState.f17292b = (float) pow;
        return massState;
    }
}
