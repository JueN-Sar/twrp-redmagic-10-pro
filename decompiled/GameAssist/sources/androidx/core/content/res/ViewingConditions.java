package androidx.core.content.res;

/* loaded from: classes.dex */
final class ViewingConditions {

    /* renamed from: k, reason: collision with root package name */
    static final ViewingConditions f2900k = k(CamUtils.f2871c, (float) ((CamUtils.h(50.0f) * 63.66197723675813d) / 100.0d), 50.0f, 2.0f, false);

    /* renamed from: a, reason: collision with root package name */
    private final float f2901a;

    /* renamed from: b, reason: collision with root package name */
    private final float f2902b;

    /* renamed from: c, reason: collision with root package name */
    private final float f2903c;

    /* renamed from: d, reason: collision with root package name */
    private final float f2904d;

    /* renamed from: e, reason: collision with root package name */
    private final float f2905e;

    /* renamed from: f, reason: collision with root package name */
    private final float f2906f;

    /* renamed from: g, reason: collision with root package name */
    private final float[] f2907g;

    /* renamed from: h, reason: collision with root package name */
    private final float f2908h;

    /* renamed from: i, reason: collision with root package name */
    private final float f2909i;

    /* renamed from: j, reason: collision with root package name */
    private final float f2910j;

    private ViewingConditions(float f2, float f3, float f4, float f5, float f6, float f7, float[] fArr, float f8, float f9, float f10) {
        this.f2906f = f2;
        this.f2901a = f3;
        this.f2902b = f4;
        this.f2903c = f5;
        this.f2904d = f6;
        this.f2905e = f7;
        this.f2907g = fArr;
        this.f2908h = f8;
        this.f2909i = f9;
        this.f2910j = f10;
    }

    static ViewingConditions k(float[] fArr, float f2, float f3, float f4, boolean z) {
        float[][] fArr2 = CamUtils.f2869a;
        float f5 = fArr[0];
        float[] fArr3 = fArr2[0];
        float f6 = fArr3[0] * f5;
        float f7 = fArr[1];
        float f8 = f6 + (fArr3[1] * f7);
        float f9 = fArr[2];
        float f10 = f8 + (fArr3[2] * f9);
        float[] fArr4 = fArr2[1];
        float f11 = (fArr4[0] * f5) + (fArr4[1] * f7) + (fArr4[2] * f9);
        float[] fArr5 = fArr2[2];
        float f12 = (f5 * fArr5[0]) + (f7 * fArr5[1]) + (f9 * fArr5[2]);
        float f13 = (f4 / 10.0f) + 0.8f;
        float d2 = ((double) f13) >= 0.9d ? CamUtils.d(0.59f, 0.69f, (f13 - 0.9f) * 10.0f) : CamUtils.d(0.525f, 0.59f, (f13 - 0.8f) * 10.0f);
        float exp = z ? 1.0f : (1.0f - (((float) Math.exp(((-f2) - 42.0f) / 92.0f)) * 0.2777778f)) * f13;
        double d3 = exp;
        if (d3 > 1.0d) {
            exp = 1.0f;
        } else if (d3 < 0.0d) {
            exp = 0.0f;
        }
        float[] fArr6 = {(((100.0f / f10) * exp) + 1.0f) - exp, (((100.0f / f11) * exp) + 1.0f) - exp, (((100.0f / f12) * exp) + 1.0f) - exp};
        float f14 = 1.0f / ((5.0f * f2) + 1.0f);
        float f15 = f14 * f14 * f14 * f14;
        float f16 = 1.0f - f15;
        float cbrt = (f15 * f2) + (0.1f * f16 * f16 * ((float) Math.cbrt(f2 * 5.0d)));
        float h2 = CamUtils.h(f3) / fArr[1];
        double d4 = h2;
        float sqrt = ((float) Math.sqrt(d4)) + 1.48f;
        float pow = 0.725f / ((float) Math.pow(d4, 0.2d));
        float[] fArr7 = {(float) Math.pow(((fArr6[0] * cbrt) * f10) / 100.0d, 0.42d), (float) Math.pow(((fArr6[1] * cbrt) * f11) / 100.0d, 0.42d), (float) Math.pow(((fArr6[2] * cbrt) * f12) / 100.0d, 0.42d)};
        float f17 = fArr7[0];
        float f18 = (f17 * 400.0f) / (f17 + 27.13f);
        float f19 = fArr7[1];
        float f20 = (f19 * 400.0f) / (f19 + 27.13f);
        float f21 = fArr7[2];
        float[] fArr8 = {f18, f20, (400.0f * f21) / (f21 + 27.13f)};
        return new ViewingConditions(h2, ((fArr8[0] * 2.0f) + fArr8[1] + (fArr8[2] * 0.05f)) * pow, pow, pow, d2, f13, fArr6, cbrt, (float) Math.pow(cbrt, 0.25d), sqrt);
    }

    float a() {
        return this.f2901a;
    }

    float b() {
        return this.f2904d;
    }

    float c() {
        return this.f2908h;
    }

    float d() {
        return this.f2909i;
    }

    float e() {
        return this.f2906f;
    }

    float f() {
        return this.f2902b;
    }

    float g() {
        return this.f2905e;
    }

    float h() {
        return this.f2903c;
    }

    float[] i() {
        return this.f2907g;
    }

    float j() {
        return this.f2910j;
    }
}
