package com.google.android.material.carousel;

import androidx.core.math.MathUtils;

/* loaded from: classes.dex */
final class Arrangement {

    /* renamed from: a, reason: collision with root package name */
    final int f14126a;

    /* renamed from: b, reason: collision with root package name */
    float f14127b;

    /* renamed from: c, reason: collision with root package name */
    int f14128c;

    /* renamed from: d, reason: collision with root package name */
    int f14129d;

    /* renamed from: e, reason: collision with root package name */
    float f14130e;

    /* renamed from: f, reason: collision with root package name */
    float f14131f;

    /* renamed from: g, reason: collision with root package name */
    final int f14132g;

    /* renamed from: h, reason: collision with root package name */
    final float f14133h;

    Arrangement(int i2, float f2, float f3, float f4, int i3, float f5, int i4, float f6, int i5, float f7) {
        this.f14126a = i2;
        this.f14127b = MathUtils.a(f2, f3, f4);
        this.f14128c = i3;
        this.f14130e = f5;
        this.f14129d = i4;
        this.f14131f = f6;
        this.f14132g = i5;
        d(f7, f3, f4, f6);
        this.f14133h = b(f6);
    }

    private float a(float f2, int i2, float f3, int i3, int i4) {
        if (i2 <= 0) {
            f3 = 0.0f;
        }
        float f4 = i2;
        float f5 = i3 / 2.0f;
        return (f2 - ((f4 + f5) * f3)) / (i4 + f5);
    }

    private float b(float f2) {
        if (g()) {
            return Math.abs(f2 - this.f14131f) * this.f14126a;
        }
        return Float.MAX_VALUE;
    }

    static Arrangement c(float f2, float f3, float f4, float f5, int[] iArr, float f6, int[] iArr2, float f7, int[] iArr3) {
        Arrangement arrangement = null;
        int i2 = 1;
        for (int i3 : iArr3) {
            int length = iArr2.length;
            int i4 = 0;
            while (i4 < length) {
                int i5 = iArr2[i4];
                int length2 = iArr.length;
                int i6 = 0;
                while (i6 < length2) {
                    int i7 = i6;
                    int i8 = length2;
                    int i9 = i4;
                    int i10 = length;
                    Arrangement arrangement2 = new Arrangement(i2, f3, f4, f5, iArr[i6], f6, i5, f7, i3, f2);
                    if (arrangement == null || arrangement2.f14133h < arrangement.f14133h) {
                        if (arrangement2.f14133h == 0.0f) {
                            return arrangement2;
                        }
                        arrangement = arrangement2;
                    }
                    i2++;
                    i6 = i7 + 1;
                    length2 = i8;
                    i4 = i9;
                    length = i10;
                }
                i4++;
            }
        }
        return arrangement;
    }

    private void d(float f2, float f3, float f4, float f5) {
        float f6 = f2 - f();
        int i2 = this.f14128c;
        if (i2 > 0 && f6 > 0.0f) {
            float f7 = this.f14127b;
            this.f14127b = f7 + Math.min(f6 / i2, f4 - f7);
        } else if (i2 > 0 && f6 < 0.0f) {
            float f8 = this.f14127b;
            this.f14127b = f8 + Math.max(f6 / i2, f3 - f8);
        }
        int i3 = this.f14128c;
        float f9 = i3 > 0 ? this.f14127b : 0.0f;
        this.f14127b = f9;
        float a2 = a(f2, i3, f9, this.f14129d, this.f14132g);
        this.f14131f = a2;
        float f10 = (this.f14127b + a2) / 2.0f;
        this.f14130e = f10;
        int i4 = this.f14129d;
        if (i4 <= 0 || a2 == f5) {
            return;
        }
        float f11 = (f5 - a2) * this.f14132g;
        float min = Math.min(Math.abs(f11), f10 * 0.1f * i4);
        if (f11 > 0.0f) {
            this.f14130e -= min / this.f14129d;
            this.f14131f += min / this.f14132g;
        } else {
            this.f14130e += min / this.f14129d;
            this.f14131f -= min / this.f14132g;
        }
    }

    private float f() {
        return (this.f14131f * this.f14132g) + (this.f14130e * this.f14129d) + (this.f14127b * this.f14128c);
    }

    private boolean g() {
        int i2 = this.f14132g;
        if (i2 <= 0 || this.f14128c <= 0 || this.f14129d <= 0) {
            return i2 <= 0 || this.f14128c <= 0 || this.f14131f > this.f14127b;
        }
        float f2 = this.f14131f;
        float f3 = this.f14130e;
        return f2 > f3 && f3 > this.f14127b;
    }

    int e() {
        return this.f14128c + this.f14129d + this.f14132g;
    }

    public String toString() {
        return "Arrangement [priority=" + this.f14126a + ", smallCount=" + this.f14128c + ", smallSize=" + this.f14127b + ", mediumCount=" + this.f14129d + ", mediumSize=" + this.f14130e + ", largeCount=" + this.f14132g + ", largeSize=" + this.f14131f + ", cost=" + this.f14133h + "]";
    }
}
