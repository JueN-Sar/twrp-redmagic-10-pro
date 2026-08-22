package androidx.constraintlayout.core.motion.utils;

/* loaded from: classes.dex */
public class SpringStopEngine implements StopEngine {

    /* renamed from: c, reason: collision with root package name */
    private double f1838c;

    /* renamed from: d, reason: collision with root package name */
    private double f1839d;

    /* renamed from: e, reason: collision with root package name */
    private double f1840e;

    /* renamed from: f, reason: collision with root package name */
    private float f1841f;

    /* renamed from: g, reason: collision with root package name */
    private float f1842g;

    /* renamed from: h, reason: collision with root package name */
    private float f1843h;

    /* renamed from: i, reason: collision with root package name */
    private float f1844i;

    /* renamed from: j, reason: collision with root package name */
    private float f1845j;

    /* renamed from: a, reason: collision with root package name */
    double f1836a = 0.5d;

    /* renamed from: b, reason: collision with root package name */
    private boolean f1837b = false;

    /* renamed from: k, reason: collision with root package name */
    private int f1846k = 0;

    private void c(double d2) {
        if (d2 <= 0.0d) {
            return;
        }
        double d3 = this.f1838c;
        double d4 = this.f1836a;
        int sqrt = (int) ((9.0d / ((Math.sqrt(d3 / this.f1844i) * d2) * 4.0d)) + 1.0d);
        double d5 = d2 / sqrt;
        int i2 = 0;
        while (i2 < sqrt) {
            float f2 = this.f1842g;
            double d6 = this.f1839d;
            float f3 = this.f1843h;
            double d7 = d3;
            double d8 = ((-d3) * (f2 - d6)) - (f3 * d4);
            float f4 = this.f1844i;
            double d9 = d4;
            double d10 = f3 + (((d8 / f4) * d5) / 2.0d);
            double d11 = ((((-((f2 + ((d5 * d10) / 2.0d)) - d6)) * d7) - (d10 * d9)) / f4) * d5;
            double d12 = f3 + (d11 / 2.0d);
            float f5 = f3 + ((float) d11);
            this.f1843h = f5;
            float f6 = f2 + ((float) (d12 * d5));
            this.f1842g = f6;
            int i3 = this.f1846k;
            if (i3 > 0) {
                if (f6 < 0.0f && (i3 & 1) == 1) {
                    this.f1842g = -f6;
                    this.f1843h = -f5;
                }
                float f7 = this.f1842g;
                if (f7 > 1.0f && (i3 & 2) == 2) {
                    this.f1842g = 2.0f - f7;
                    this.f1843h = -this.f1843h;
                }
            }
            i2++;
            d3 = d7;
            d4 = d9;
        }
    }

    @Override // androidx.constraintlayout.core.motion.utils.StopEngine
    public float a() {
        return 0.0f;
    }

    @Override // androidx.constraintlayout.core.motion.utils.StopEngine
    public boolean b() {
        double d2 = this.f1842g - this.f1839d;
        double d3 = this.f1838c;
        double d4 = this.f1843h;
        return Math.sqrt((((d4 * d4) * ((double) this.f1844i)) + ((d3 * d2) * d2)) / d3) <= ((double) this.f1845j);
    }

    public void d(float f2, float f3, float f4, float f5, float f6, float f7, float f8, int i2) {
        this.f1839d = f3;
        this.f1836a = f7;
        this.f1837b = false;
        this.f1842g = f2;
        this.f1840e = f4;
        this.f1838c = f6;
        this.f1844i = f5;
        this.f1845j = f8;
        this.f1846k = i2;
        this.f1841f = 0.0f;
    }

    @Override // androidx.constraintlayout.core.motion.utils.StopEngine
    public float getInterpolation(float f2) {
        c(f2 - this.f1841f);
        this.f1841f = f2;
        if (b()) {
            this.f1842g = (float) this.f1839d;
        }
        return this.f1842g;
    }
}
