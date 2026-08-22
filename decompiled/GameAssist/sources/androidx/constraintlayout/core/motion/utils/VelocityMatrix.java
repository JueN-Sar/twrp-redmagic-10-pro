package androidx.constraintlayout.core.motion.utils;

/* loaded from: classes.dex */
public class VelocityMatrix {

    /* renamed from: a, reason: collision with root package name */
    float f1886a;

    /* renamed from: b, reason: collision with root package name */
    float f1887b;

    /* renamed from: c, reason: collision with root package name */
    float f1888c;

    /* renamed from: d, reason: collision with root package name */
    float f1889d;

    /* renamed from: e, reason: collision with root package name */
    float f1890e;

    /* renamed from: f, reason: collision with root package name */
    float f1891f;

    public void a(float f2, float f3, int i2, int i3, float[] fArr) {
        float f4 = fArr[0];
        float f5 = fArr[1];
        float f6 = (f3 - 0.5f) * 2.0f;
        float f7 = f4 + this.f1888c;
        float f8 = f5 + this.f1889d;
        float f9 = f7 + (this.f1886a * (f2 - 0.5f) * 2.0f);
        float f10 = f8 + (this.f1887b * f6);
        float radians = (float) Math.toRadians(this.f1891f);
        float radians2 = (float) Math.toRadians(this.f1890e);
        double d2 = radians;
        double d3 = i3 * f6;
        float sin = f9 + (((float) ((((-i2) * r7) * Math.sin(d2)) - (Math.cos(d2) * d3))) * radians2);
        float cos = f10 + (radians2 * ((float) (((i2 * r7) * Math.cos(d2)) - (d3 * Math.sin(d2)))));
        fArr[0] = sin;
        fArr[1] = cos;
    }

    public void b() {
        this.f1890e = 0.0f;
        this.f1889d = 0.0f;
        this.f1888c = 0.0f;
        this.f1887b = 0.0f;
        this.f1886a = 0.0f;
    }

    public void c(KeyCycleOscillator keyCycleOscillator, float f2) {
        if (keyCycleOscillator != null) {
            this.f1890e = keyCycleOscillator.b(f2);
        }
    }

    public void d(SplineSet splineSet, float f2) {
        if (splineSet != null) {
            this.f1890e = splineSet.b(f2);
            this.f1891f = splineSet.a(f2);
        }
    }

    public void e(KeyCycleOscillator keyCycleOscillator, KeyCycleOscillator keyCycleOscillator2, float f2) {
        if (keyCycleOscillator != null) {
            this.f1886a = keyCycleOscillator.b(f2);
        }
        if (keyCycleOscillator2 != null) {
            this.f1887b = keyCycleOscillator2.b(f2);
        }
    }

    public void f(SplineSet splineSet, SplineSet splineSet2, float f2) {
        if (splineSet != null) {
            this.f1886a = splineSet.b(f2);
        }
        if (splineSet2 != null) {
            this.f1887b = splineSet2.b(f2);
        }
    }

    public void g(KeyCycleOscillator keyCycleOscillator, KeyCycleOscillator keyCycleOscillator2, float f2) {
        if (keyCycleOscillator != null) {
            this.f1888c = keyCycleOscillator.b(f2);
        }
        if (keyCycleOscillator2 != null) {
            this.f1889d = keyCycleOscillator2.b(f2);
        }
    }

    public void h(SplineSet splineSet, SplineSet splineSet2, float f2) {
        if (splineSet != null) {
            this.f1888c = splineSet.b(f2);
        }
        if (splineSet2 != null) {
            this.f1889d = splineSet2.b(f2);
        }
    }
}
