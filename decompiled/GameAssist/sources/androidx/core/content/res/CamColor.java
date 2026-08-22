package androidx.core.content.res;

import androidx.annotation.RestrictTo;
import androidx.core.graphics.ColorUtils;

@RestrictTo
/* loaded from: classes.dex */
public class CamColor {

    /* renamed from: a, reason: collision with root package name */
    private final float f2860a;

    /* renamed from: b, reason: collision with root package name */
    private final float f2861b;

    /* renamed from: c, reason: collision with root package name */
    private final float f2862c;

    /* renamed from: d, reason: collision with root package name */
    private final float f2863d;

    /* renamed from: e, reason: collision with root package name */
    private final float f2864e;

    /* renamed from: f, reason: collision with root package name */
    private final float f2865f;

    /* renamed from: g, reason: collision with root package name */
    private final float f2866g;

    /* renamed from: h, reason: collision with root package name */
    private final float f2867h;

    /* renamed from: i, reason: collision with root package name */
    private final float f2868i;

    CamColor(float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10) {
        this.f2860a = f2;
        this.f2861b = f3;
        this.f2862c = f4;
        this.f2863d = f5;
        this.f2864e = f6;
        this.f2865f = f7;
        this.f2866g = f8;
        this.f2867h = f9;
        this.f2868i = f10;
    }

    private static CamColor b(float f2, float f3, float f4) {
        float f5 = 100.0f;
        float f6 = 1000.0f;
        float f7 = 0.0f;
        CamColor camColor = null;
        float f8 = 1000.0f;
        while (Math.abs(f7 - f5) > 0.01f) {
            float f9 = ((f5 - f7) / 2.0f) + f7;
            int p2 = e(f9, f3, f2).p();
            float b2 = CamUtils.b(p2);
            float abs = Math.abs(f4 - b2);
            if (abs < 0.2f) {
                CamColor c2 = c(p2);
                float a2 = c2.a(e(c2.k(), c2.i(), f2));
                if (a2 <= 1.0f) {
                    camColor = c2;
                    f6 = abs;
                    f8 = a2;
                }
            }
            if (f6 == 0.0f && f8 == 0.0f) {
                break;
            }
            if (b2 < f4) {
                f7 = f9;
            } else {
                f5 = f9;
            }
        }
        return camColor;
    }

    static CamColor c(int i2) {
        float[] fArr = new float[7];
        float[] fArr2 = new float[3];
        d(i2, ViewingConditions.f2900k, fArr, fArr2);
        return new CamColor(fArr2[0], fArr2[1], fArr[0], fArr[1], fArr[2], fArr[3], fArr[4], fArr[5], fArr[6]);
    }

    static void d(int i2, ViewingConditions viewingConditions, float[] fArr, float[] fArr2) {
        CamUtils.f(i2, fArr2);
        float[][] fArr3 = CamUtils.f2869a;
        float f2 = fArr2[0];
        float[] fArr4 = fArr3[0];
        float f3 = fArr4[0] * f2;
        float f4 = fArr2[1];
        float f5 = f3 + (fArr4[1] * f4);
        float f6 = fArr2[2];
        float f7 = f5 + (fArr4[2] * f6);
        float[] fArr5 = fArr3[1];
        float f8 = (fArr5[0] * f2) + (fArr5[1] * f4) + (fArr5[2] * f6);
        float[] fArr6 = fArr3[2];
        float f9 = (f2 * fArr6[0]) + (f4 * fArr6[1]) + (f6 * fArr6[2]);
        float f10 = viewingConditions.i()[0] * f7;
        float f11 = viewingConditions.i()[1] * f8;
        float f12 = viewingConditions.i()[2] * f9;
        float pow = (float) Math.pow((viewingConditions.c() * Math.abs(f10)) / 100.0d, 0.42d);
        float pow2 = (float) Math.pow((viewingConditions.c() * Math.abs(f11)) / 100.0d, 0.42d);
        float pow3 = (float) Math.pow((viewingConditions.c() * Math.abs(f12)) / 100.0d, 0.42d);
        float signum = ((Math.signum(f10) * 400.0f) * pow) / (pow + 27.13f);
        float signum2 = ((Math.signum(f11) * 400.0f) * pow2) / (pow2 + 27.13f);
        float signum3 = ((Math.signum(f12) * 400.0f) * pow3) / (pow3 + 27.13f);
        double d2 = signum3;
        float f13 = ((float) (((signum * 11.0d) + (signum2 * (-12.0d))) + d2)) / 11.0f;
        float f14 = ((float) ((signum + signum2) - (d2 * 2.0d))) / 9.0f;
        float f15 = signum2 * 20.0f;
        float f16 = (((signum * 20.0f) + f15) + (21.0f * signum3)) / 20.0f;
        float f17 = (((signum * 40.0f) + f15) + signum3) / 20.0f;
        float atan2 = (((float) Math.atan2(f14, f13)) * 180.0f) / 3.1415927f;
        if (atan2 < 0.0f) {
            atan2 += 360.0f;
        } else if (atan2 >= 360.0f) {
            atan2 -= 360.0f;
        }
        float f18 = (3.1415927f * atan2) / 180.0f;
        float pow4 = ((float) Math.pow((f17 * viewingConditions.f()) / viewingConditions.a(), viewingConditions.b() * viewingConditions.j())) * 100.0f;
        float b2 = (4.0f / viewingConditions.b()) * ((float) Math.sqrt(pow4 / 100.0f)) * (viewingConditions.a() + 4.0f) * viewingConditions.d();
        float sqrt = ((float) Math.sqrt(pow4 / 100.0d)) * ((float) Math.pow(1.64d - Math.pow(0.29d, viewingConditions.e()), 0.73d)) * ((float) Math.pow((((((((float) (Math.cos((((((double) atan2) < 20.14d ? 360.0f + atan2 : atan2) * 3.141592653589793d) / 180.0d) + 2.0d) + 3.8d)) * 0.25f) * 3846.1538f) * viewingConditions.g()) * viewingConditions.h()) * ((float) Math.sqrt((f13 * f13) + (f14 * f14)))) / (f16 + 0.305f), 0.9d));
        float d3 = viewingConditions.d() * sqrt;
        float sqrt2 = ((float) Math.sqrt((r7 * viewingConditions.b()) / (viewingConditions.a() + 4.0f))) * 50.0f;
        float f19 = (1.7f * pow4) / ((0.007f * pow4) + 1.0f);
        float log = ((float) Math.log((0.0228f * d3) + 1.0f)) * 43.85965f;
        double d4 = f18;
        float cos = ((float) Math.cos(d4)) * log;
        float sin = log * ((float) Math.sin(d4));
        fArr2[0] = atan2;
        fArr2[1] = sqrt;
        if (fArr != null) {
            fArr[0] = pow4;
            fArr[1] = b2;
            fArr[2] = d3;
            fArr[3] = sqrt2;
            fArr[4] = f19;
            fArr[5] = cos;
            fArr[6] = sin;
        }
    }

    private static CamColor e(float f2, float f3, float f4) {
        return f(f2, f3, f4, ViewingConditions.f2900k);
    }

    private static CamColor f(float f2, float f3, float f4, ViewingConditions viewingConditions) {
        float b2 = (4.0f / viewingConditions.b()) * ((float) Math.sqrt(f2 / 100.0d)) * (viewingConditions.a() + 4.0f) * viewingConditions.d();
        float d2 = f3 * viewingConditions.d();
        float sqrt = ((float) Math.sqrt(((f3 / ((float) Math.sqrt(r4))) * viewingConditions.b()) / (viewingConditions.a() + 4.0f))) * 50.0f;
        float f5 = (1.7f * f2) / ((0.007f * f2) + 1.0f);
        float log = ((float) Math.log((d2 * 0.0228d) + 1.0d)) * 43.85965f;
        double d3 = (3.1415927f * f4) / 180.0f;
        return new CamColor(f4, f3, f2, b2, d2, sqrt, f5, log * ((float) Math.cos(d3)), log * ((float) Math.sin(d3)));
    }

    public static int m(float f2, float f3, float f4) {
        return n(f2, f3, f4, ViewingConditions.f2900k);
    }

    static int n(float f2, float f3, float f4, ViewingConditions viewingConditions) {
        if (f3 < 1.0d || Math.round(f4) <= 0.0d || Math.round(f4) >= 100.0d) {
            return CamUtils.a(f4);
        }
        float min = f2 < 0.0f ? 0.0f : Math.min(360.0f, f2);
        CamColor camColor = null;
        boolean z = true;
        float f5 = 0.0f;
        float f6 = f3;
        while (Math.abs(f5 - f3) >= 0.4f) {
            CamColor b2 = b(min, f6, f4);
            if (!z) {
                if (b2 == null) {
                    f3 = f6;
                } else {
                    f5 = f6;
                    camColor = b2;
                }
                f6 = ((f3 - f5) / 2.0f) + f5;
            } else {
                if (b2 != null) {
                    return b2.o(viewingConditions);
                }
                f6 = ((f3 - f5) / 2.0f) + f5;
                z = false;
            }
        }
        return camColor == null ? CamUtils.a(f4) : camColor.o(viewingConditions);
    }

    float a(CamColor camColor) {
        float l2 = l() - camColor.l();
        float g2 = g() - camColor.g();
        float h2 = h() - camColor.h();
        return (float) (Math.pow(Math.sqrt((l2 * l2) + (g2 * g2) + (h2 * h2)), 0.63d) * 1.41d);
    }

    float g() {
        return this.f2867h;
    }

    float h() {
        return this.f2868i;
    }

    float i() {
        return this.f2861b;
    }

    float j() {
        return this.f2860a;
    }

    float k() {
        return this.f2862c;
    }

    float l() {
        return this.f2866g;
    }

    int o(ViewingConditions viewingConditions) {
        float pow = (float) Math.pow(((((double) i()) == 0.0d || ((double) k()) == 0.0d) ? 0.0f : i() / ((float) Math.sqrt(k() / 100.0d))) / Math.pow(1.64d - Math.pow(0.29d, viewingConditions.e()), 0.73d), 1.1111111111111112d);
        double j2 = (j() * 3.1415927f) / 180.0f;
        float cos = ((float) (Math.cos(2.0d + j2) + 3.8d)) * 0.25f;
        float a2 = viewingConditions.a() * ((float) Math.pow(k() / 100.0d, (1.0d / viewingConditions.b()) / viewingConditions.j()));
        float g2 = cos * 3846.1538f * viewingConditions.g() * viewingConditions.h();
        float f2 = a2 / viewingConditions.f();
        float sin = (float) Math.sin(j2);
        float cos2 = (float) Math.cos(j2);
        float f3 = (((0.305f + f2) * 23.0f) * pow) / (((g2 * 23.0f) + ((11.0f * pow) * cos2)) + ((pow * 108.0f) * sin));
        float f4 = cos2 * f3;
        float f5 = f3 * sin;
        float f6 = f2 * 460.0f;
        float f7 = (((451.0f * f4) + f6) + (288.0f * f5)) / 1403.0f;
        float f8 = ((f6 - (891.0f * f4)) - (261.0f * f5)) / 1403.0f;
        float signum = Math.signum(f7) * (100.0f / viewingConditions.c()) * ((float) Math.pow((float) Math.max(0.0d, (Math.abs(f7) * 27.13d) / (400.0d - Math.abs(f7))), 2.380952380952381d));
        float signum2 = Math.signum(f8) * (100.0f / viewingConditions.c()) * ((float) Math.pow((float) Math.max(0.0d, (Math.abs(f8) * 27.13d) / (400.0d - Math.abs(f8))), 2.380952380952381d));
        float signum3 = Math.signum(((f6 - (f4 * 220.0f)) - (f5 * 6300.0f)) / 1403.0f) * (100.0f / viewingConditions.c()) * ((float) Math.pow((float) Math.max(0.0d, (Math.abs(r8) * 27.13d) / (400.0d - Math.abs(r8))), 2.380952380952381d));
        float f9 = signum / viewingConditions.i()[0];
        float f10 = signum2 / viewingConditions.i()[1];
        float f11 = signum3 / viewingConditions.i()[2];
        float[][] fArr = CamUtils.f2870b;
        float[] fArr2 = fArr[0];
        float f12 = (fArr2[0] * f9) + (fArr2[1] * f10) + (fArr2[2] * f11);
        float[] fArr3 = fArr[1];
        float f13 = (fArr3[0] * f9) + (fArr3[1] * f10) + (fArr3[2] * f11);
        float[] fArr4 = fArr[2];
        return ColorUtils.b(f12, f13, (f9 * fArr4[0]) + (f10 * fArr4[1]) + (f11 * fArr4[2]));
    }

    int p() {
        return o(ViewingConditions.f2900k);
    }
}
