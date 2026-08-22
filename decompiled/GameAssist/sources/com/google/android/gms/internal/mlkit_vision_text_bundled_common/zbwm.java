package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import java.util.Arrays;

/* loaded from: classes.dex */
public final class zbwm {

    /* renamed from: f, reason: collision with root package name */
    private static final zbwm f13049f = new zbwm(0, new int[0], new Object[0], false);

    /* renamed from: a, reason: collision with root package name */
    private int f13050a;

    /* renamed from: b, reason: collision with root package name */
    private int[] f13051b;

    /* renamed from: c, reason: collision with root package name */
    private Object[] f13052c;

    /* renamed from: d, reason: collision with root package name */
    private int f13053d = -1;

    /* renamed from: e, reason: collision with root package name */
    private boolean f13054e;

    private zbwm(int i2, int[] iArr, Object[] objArr, boolean z) {
        this.f13050a = i2;
        this.f13051b = iArr;
        this.f13052c = objArr;
        this.f13054e = z;
    }

    public static zbwm c() {
        return f13049f;
    }

    static zbwm e(zbwm zbwmVar, zbwm zbwmVar2) {
        int i2 = zbwmVar.f13050a + zbwmVar2.f13050a;
        int[] copyOf = Arrays.copyOf(zbwmVar.f13051b, i2);
        System.arraycopy(zbwmVar2.f13051b, 0, copyOf, zbwmVar.f13050a, zbwmVar2.f13050a);
        Object[] copyOf2 = Arrays.copyOf(zbwmVar.f13052c, i2);
        System.arraycopy(zbwmVar2.f13052c, 0, copyOf2, zbwmVar.f13050a, zbwmVar2.f13050a);
        return new zbwm(i2, copyOf, copyOf2, true);
    }

    static zbwm f() {
        return new zbwm(0, new int[8], new Object[8], true);
    }

    private final void m(int i2) {
        int[] iArr = this.f13051b;
        if (i2 > iArr.length) {
            int i3 = this.f13050a;
            int i4 = i3 + (i3 / 2);
            if (i4 >= i2) {
                i2 = i4;
            }
            if (i2 < 8) {
                i2 = 8;
            }
            this.f13051b = Arrays.copyOf(iArr, i2);
            this.f13052c = Arrays.copyOf(this.f13052c, i2);
        }
    }

    public final int a() {
        int d2;
        int e2;
        int i2;
        int i3 = this.f13053d;
        if (i3 != -1) {
            return i3;
        }
        int i4 = 0;
        for (int i5 = 0; i5 < this.f13050a; i5++) {
            int i6 = this.f13051b[i5];
            int i7 = i6 >>> 3;
            int i8 = i6 & 7;
            if (i8 != 0) {
                if (i8 == 1) {
                    ((Long) this.f13052c[i5]).longValue();
                    i2 = zbtk.d(i7 << 3) + 8;
                } else if (i8 == 2) {
                    int i9 = i7 << 3;
                    zbtc zbtcVar = (zbtc) this.f13052c[i5];
                    int d3 = zbtk.d(i9);
                    int f2 = zbtcVar.f();
                    i2 = d3 + zbtk.d(f2) + f2;
                } else if (i8 == 3) {
                    int d4 = zbtk.d(i7 << 3);
                    d2 = d4 + d4;
                    e2 = ((zbwm) this.f13052c[i5]).a();
                } else {
                    if (i8 != 5) {
                        throw new IllegalStateException(new zbup("Protocol message tag had invalid wire type."));
                    }
                    ((Integer) this.f13052c[i5]).intValue();
                    i2 = zbtk.d(i7 << 3) + 4;
                }
                i4 += i2;
            } else {
                int i10 = i7 << 3;
                long longValue = ((Long) this.f13052c[i5]).longValue();
                d2 = zbtk.d(i10);
                e2 = zbtk.e(longValue);
            }
            i2 = d2 + e2;
            i4 += i2;
        }
        this.f13053d = i4;
        return i4;
    }

    public final int b() {
        int i2 = this.f13053d;
        if (i2 != -1) {
            return i2;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < this.f13050a; i4++) {
            int i5 = this.f13051b[i4] >>> 3;
            zbtc zbtcVar = (zbtc) this.f13052c[i4];
            int d2 = zbtk.d(8);
            int d3 = zbtk.d(16) + zbtk.d(i5);
            int d4 = zbtk.d(24);
            int f2 = zbtcVar.f();
            i3 += d2 + d2 + d3 + d4 + zbtk.d(f2) + f2;
        }
        this.f13053d = i3;
        return i3;
    }

    final zbwm d(zbwm zbwmVar) {
        if (zbwmVar.equals(f13049f)) {
            return this;
        }
        g();
        int i2 = this.f13050a + zbwmVar.f13050a;
        m(i2);
        System.arraycopy(zbwmVar.f13051b, 0, this.f13051b, this.f13050a, zbwmVar.f13050a);
        System.arraycopy(zbwmVar.f13052c, 0, this.f13052c, this.f13050a, zbwmVar.f13050a);
        this.f13050a = i2;
        return this;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof zbwm)) {
            return false;
        }
        zbwm zbwmVar = (zbwm) obj;
        int i2 = this.f13050a;
        if (i2 == zbwmVar.f13050a) {
            int[] iArr = this.f13051b;
            int[] iArr2 = zbwmVar.f13051b;
            int i3 = 0;
            while (true) {
                if (i3 >= i2) {
                    Object[] objArr = this.f13052c;
                    Object[] objArr2 = zbwmVar.f13052c;
                    int i4 = this.f13050a;
                    for (int i5 = 0; i5 < i4; i5++) {
                        if (objArr[i5].equals(objArr2[i5])) {
                        }
                    }
                    return true;
                }
                if (iArr[i3] != iArr2[i3]) {
                    break;
                }
                i3++;
            }
        }
        return false;
    }

    final void g() {
        if (!this.f13054e) {
            throw new UnsupportedOperationException();
        }
    }

    public final void h() {
        if (this.f13054e) {
            this.f13054e = false;
        }
    }

    public final int hashCode() {
        int i2 = this.f13050a;
        int i3 = i2 + 527;
        int[] iArr = this.f13051b;
        int i4 = 17;
        int i5 = 17;
        for (int i6 = 0; i6 < i2; i6++) {
            i5 = (i5 * 31) + iArr[i6];
        }
        int i7 = ((i3 * 31) + i5) * 31;
        Object[] objArr = this.f13052c;
        int i8 = this.f13050a;
        for (int i9 = 0; i9 < i8; i9++) {
            i4 = (i4 * 31) + objArr[i9].hashCode();
        }
        return i7 + i4;
    }

    final void i(StringBuilder sb, int i2) {
        for (int i3 = 0; i3 < this.f13050a; i3++) {
            zbvo.b(sb, i2, String.valueOf(this.f13051b[i3] >>> 3), this.f13052c[i3]);
        }
    }

    final void j(int i2, Object obj) {
        g();
        m(this.f13050a + 1);
        int[] iArr = this.f13051b;
        int i3 = this.f13050a;
        iArr[i3] = i2;
        this.f13052c[i3] = obj;
        this.f13050a = i3 + 1;
    }

    final void k(zbwy zbwyVar) {
        for (int i2 = 0; i2 < this.f13050a; i2++) {
            zbwyVar.p(this.f13051b[i2] >>> 3, this.f13052c[i2]);
        }
    }

    public final void l(zbwy zbwyVar) {
        if (this.f13050a != 0) {
            for (int i2 = 0; i2 < this.f13050a; i2++) {
                int i3 = this.f13051b[i2];
                Object obj = this.f13052c[i2];
                int i4 = i3 & 7;
                int i5 = i3 >>> 3;
                if (i4 == 0) {
                    zbwyVar.C(i5, ((Long) obj).longValue());
                } else if (i4 == 1) {
                    zbwyVar.c(i5, ((Long) obj).longValue());
                } else if (i4 == 2) {
                    zbwyVar.G(i5, (zbtc) obj);
                } else if (i4 == 3) {
                    zbwyVar.K(i5);
                    ((zbwm) obj).l(zbwyVar);
                    zbwyVar.w(i5);
                } else {
                    if (i4 != 5) {
                        throw new RuntimeException(new zbup("Protocol message tag had invalid wire type."));
                    }
                    zbwyVar.f(i5, ((Integer) obj).intValue());
                }
            }
        }
    }
}
