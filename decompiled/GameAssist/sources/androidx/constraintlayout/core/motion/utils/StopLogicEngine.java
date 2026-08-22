package androidx.constraintlayout.core.motion.utils;

/* loaded from: classes.dex */
public class StopLogicEngine implements StopEngine {

    /* renamed from: a, reason: collision with root package name */
    private float f1848a;

    /* renamed from: b, reason: collision with root package name */
    private float f1849b;

    /* renamed from: c, reason: collision with root package name */
    private float f1850c;

    /* renamed from: d, reason: collision with root package name */
    private float f1851d;

    /* renamed from: e, reason: collision with root package name */
    private float f1852e;

    /* renamed from: f, reason: collision with root package name */
    private float f1853f;

    /* renamed from: g, reason: collision with root package name */
    private float f1854g;

    /* renamed from: h, reason: collision with root package name */
    private float f1855h;

    /* renamed from: i, reason: collision with root package name */
    private float f1856i;

    /* renamed from: j, reason: collision with root package name */
    private int f1857j;

    /* renamed from: k, reason: collision with root package name */
    private String f1858k;

    /* renamed from: m, reason: collision with root package name */
    private float f1860m;

    /* renamed from: n, reason: collision with root package name */
    private float f1861n;

    /* renamed from: o, reason: collision with root package name */
    private float f1862o;

    /* renamed from: l, reason: collision with root package name */
    private boolean f1859l = false;

    /* renamed from: p, reason: collision with root package name */
    private boolean f1863p = false;

    public static class Decelerate implements StopEngine {

        /* renamed from: a, reason: collision with root package name */
        private float f1864a;

        /* renamed from: b, reason: collision with root package name */
        private float f1865b;

        /* renamed from: c, reason: collision with root package name */
        private float f1866c;

        /* renamed from: d, reason: collision with root package name */
        private float f1867d;

        /* renamed from: e, reason: collision with root package name */
        private float f1868e;

        /* renamed from: f, reason: collision with root package name */
        private float f1869f;

        /* renamed from: g, reason: collision with root package name */
        private boolean f1870g;

        @Override // androidx.constraintlayout.core.motion.utils.StopEngine
        public float a() {
            return this.f1867d;
        }

        @Override // androidx.constraintlayout.core.motion.utils.StopEngine
        public boolean b() {
            return this.f1870g;
        }

        public float c(float f2) {
            if (f2 > this.f1868e) {
                return 0.0f;
            }
            float f3 = this.f1865b + (this.f1866c * f2);
            this.f1867d = f3;
            return f3;
        }

        @Override // androidx.constraintlayout.core.motion.utils.StopEngine
        public float getInterpolation(float f2) {
            if (f2 > this.f1868e) {
                this.f1870g = true;
                return this.f1864a;
            }
            c(f2);
            return this.f1869f + ((this.f1865b + ((this.f1866c * f2) / 2.0f)) * f2);
        }
    }

    private float c(float f2) {
        this.f1863p = false;
        float f3 = this.f1851d;
        if (f2 <= f3) {
            float f4 = this.f1848a;
            return (f4 * f2) + ((((this.f1849b - f4) * f2) * f2) / (f3 * 2.0f));
        }
        int i2 = this.f1857j;
        if (i2 == 1) {
            return this.f1854g;
        }
        float f5 = f2 - f3;
        float f6 = this.f1852e;
        if (f5 < f6) {
            float f7 = this.f1854g;
            float f8 = this.f1849b;
            return f7 + (f8 * f5) + ((((this.f1850c - f8) * f5) * f5) / (f6 * 2.0f));
        }
        if (i2 == 2) {
            return this.f1855h;
        }
        float f9 = f5 - f6;
        float f10 = this.f1853f;
        if (f9 > f10) {
            this.f1863p = true;
            return this.f1856i;
        }
        float f11 = this.f1855h;
        float f12 = this.f1850c;
        return (f11 + (f12 * f9)) - (((f12 * f9) * f9) / (f10 * 2.0f));
    }

    private void f(float f2, float f3, float f4, float f5, float f6) {
        this.f1863p = false;
        this.f1856i = f3;
        if (f2 == 0.0f) {
            f2 = 1.0E-4f;
        }
        float f7 = f2 / f4;
        float f8 = (f7 * f2) / 2.0f;
        if (f2 < 0.0f) {
            float sqrt = (float) Math.sqrt((f3 - ((((-f2) / f4) * f2) / 2.0f)) * f4);
            if (sqrt < f5) {
                this.f1858k = "backward accelerate, decelerate";
                this.f1857j = 2;
                this.f1848a = f2;
                this.f1849b = sqrt;
                this.f1850c = 0.0f;
                float f9 = (sqrt - f2) / f4;
                this.f1851d = f9;
                this.f1852e = sqrt / f4;
                this.f1854g = ((f2 + sqrt) * f9) / 2.0f;
                this.f1855h = f3;
                this.f1856i = f3;
                return;
            }
            this.f1858k = "backward accelerate cruse decelerate";
            this.f1857j = 3;
            this.f1848a = f2;
            this.f1849b = f5;
            this.f1850c = f5;
            float f10 = (f5 - f2) / f4;
            this.f1851d = f10;
            float f11 = f5 / f4;
            this.f1853f = f11;
            float f12 = ((f2 + f5) * f10) / 2.0f;
            float f13 = (f11 * f5) / 2.0f;
            this.f1852e = ((f3 - f12) - f13) / f5;
            this.f1854g = f12;
            this.f1855h = f3 - f13;
            this.f1856i = f3;
            return;
        }
        if (f8 >= f3) {
            this.f1858k = "hard stop";
            this.f1857j = 1;
            this.f1848a = f2;
            this.f1849b = 0.0f;
            this.f1854g = f3;
            this.f1851d = (2.0f * f3) / f2;
            return;
        }
        float f14 = f3 - f8;
        float f15 = f14 / f2;
        if (f15 + f7 < f6) {
            this.f1858k = "cruse decelerate";
            this.f1857j = 2;
            this.f1848a = f2;
            this.f1849b = f2;
            this.f1850c = 0.0f;
            this.f1854g = f14;
            this.f1855h = f3;
            this.f1851d = f15;
            this.f1852e = f7;
            return;
        }
        float sqrt2 = (float) Math.sqrt((f4 * f3) + ((f2 * f2) / 2.0f));
        float f16 = (sqrt2 - f2) / f4;
        this.f1851d = f16;
        float f17 = sqrt2 / f4;
        this.f1852e = f17;
        if (sqrt2 < f5) {
            this.f1858k = "accelerate decelerate";
            this.f1857j = 2;
            this.f1848a = f2;
            this.f1849b = sqrt2;
            this.f1850c = 0.0f;
            this.f1851d = f16;
            this.f1852e = f17;
            this.f1854g = ((f2 + sqrt2) * f16) / 2.0f;
            this.f1855h = f3;
            return;
        }
        this.f1858k = "accelerate cruse decelerate";
        this.f1857j = 3;
        this.f1848a = f2;
        this.f1849b = f5;
        this.f1850c = f5;
        float f18 = (f5 - f2) / f4;
        this.f1851d = f18;
        float f19 = f5 / f4;
        this.f1853f = f19;
        float f20 = ((f2 + f5) * f18) / 2.0f;
        float f21 = (f19 * f5) / 2.0f;
        this.f1852e = ((f3 - f20) - f21) / f5;
        this.f1854g = f20;
        this.f1855h = f3 - f21;
        this.f1856i = f3;
    }

    @Override // androidx.constraintlayout.core.motion.utils.StopEngine
    public float a() {
        return this.f1859l ? -e(this.f1862o) : e(this.f1862o);
    }

    @Override // androidx.constraintlayout.core.motion.utils.StopEngine
    public boolean b() {
        return a() < 1.0E-5f && Math.abs(this.f1856i - this.f1861n) < 1.0E-5f;
    }

    public void d(float f2, float f3, float f4, float f5, float f6, float f7) {
        this.f1863p = false;
        this.f1860m = f2;
        boolean z = f2 > f3;
        this.f1859l = z;
        if (z) {
            f(-f4, f2 - f3, f6, f7, f5);
        } else {
            f(f4, f3 - f2, f6, f7, f5);
        }
    }

    public float e(float f2) {
        float f3;
        float f4;
        float f5 = this.f1851d;
        if (f2 <= f5) {
            f3 = this.f1848a;
            f4 = this.f1849b;
        } else {
            int i2 = this.f1857j;
            if (i2 == 1) {
                return 0.0f;
            }
            f2 -= f5;
            f5 = this.f1852e;
            if (f2 >= f5) {
                if (i2 == 2) {
                    return 0.0f;
                }
                float f6 = f2 - f5;
                float f7 = this.f1853f;
                if (f6 >= f7) {
                    return 0.0f;
                }
                float f8 = this.f1850c;
                return f8 - ((f6 * f8) / f7);
            }
            f3 = this.f1849b;
            f4 = this.f1850c;
        }
        return f3 + (((f4 - f3) * f2) / f5);
    }

    @Override // androidx.constraintlayout.core.motion.utils.StopEngine
    public float getInterpolation(float f2) {
        float c2 = c(f2);
        this.f1861n = c2;
        this.f1862o = f2;
        boolean z = this.f1859l;
        float f3 = this.f1860m;
        return z ? f3 - c2 : f3 + c2;
    }
}
