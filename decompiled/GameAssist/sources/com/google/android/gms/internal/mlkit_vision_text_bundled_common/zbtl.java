package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
final class zbtl implements zbwy {

    /* renamed from: a, reason: collision with root package name */
    private final zbtk f12957a;

    private zbtl(zbtk zbtkVar) {
        byte[] bArr = zbuo.f12985b;
        this.f12957a = zbtkVar;
        zbtkVar.f12956a = this;
    }

    public static zbtl M(zbtk zbtkVar) {
        zbtl zbtlVar = zbtkVar.f12956a;
        return zbtlVar != null ? zbtlVar : new zbtl(zbtkVar);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbwy
    public final void A(int i2, List list, boolean z) {
        int i3 = 0;
        if (!(list instanceof zbva)) {
            if (!z) {
                while (i3 < list.size()) {
                    this.f12957a.E(i2, ((Long) list.get(i3)).longValue());
                    i3++;
                }
                return;
            }
            this.f12957a.B(i2, 2);
            int i4 = 0;
            for (int i5 = 0; i5 < list.size(); i5++) {
                i4 += zbtk.e(((Long) list.get(i5)).longValue());
            }
            this.f12957a.D(i4);
            while (i3 < list.size()) {
                this.f12957a.F(((Long) list.get(i3)).longValue());
                i3++;
            }
            return;
        }
        zbva zbvaVar = (zbva) list;
        if (!z) {
            while (i3 < zbvaVar.size()) {
                this.f12957a.E(i2, zbvaVar.d(i3));
                i3++;
            }
            return;
        }
        this.f12957a.B(i2, 2);
        int i6 = 0;
        for (int i7 = 0; i7 < zbvaVar.size(); i7++) {
            i6 += zbtk.e(zbvaVar.d(i7));
        }
        this.f12957a.D(i6);
        while (i3 < zbvaVar.size()) {
            this.f12957a.F(zbvaVar.d(i3));
            i3++;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbwy
    public final void B(int i2, long j2) {
        this.f12957a.q(i2, j2);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbwy
    public final void C(int i2, long j2) {
        this.f12957a.E(i2, j2);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbwy
    public final void D(int i2, int i3) {
        this.f12957a.o(i2, i3);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbwy
    public final void E(int i2, List list, boolean z) {
        int i3 = 0;
        if (!(list instanceof zbtw)) {
            if (!z) {
                while (i3 < list.size()) {
                    this.f12957a.o(i2, Float.floatToRawIntBits(((Float) list.get(i3)).floatValue()));
                    i3++;
                }
                return;
            }
            this.f12957a.B(i2, 2);
            int i4 = 0;
            for (int i5 = 0; i5 < list.size(); i5++) {
                ((Float) list.get(i5)).floatValue();
                i4 += 4;
            }
            this.f12957a.D(i4);
            while (i3 < list.size()) {
                this.f12957a.p(Float.floatToRawIntBits(((Float) list.get(i3)).floatValue()));
                i3++;
            }
            return;
        }
        zbtw zbtwVar = (zbtw) list;
        if (!z) {
            while (i3 < zbtwVar.size()) {
                this.f12957a.o(i2, Float.floatToRawIntBits(zbtwVar.d(i3)));
                i3++;
            }
            return;
        }
        this.f12957a.B(i2, 2);
        int i6 = 0;
        for (int i7 = 0; i7 < zbtwVar.size(); i7++) {
            zbtwVar.d(i7);
            i6 += 4;
        }
        this.f12957a.D(i6);
        while (i3 < zbtwVar.size()) {
            this.f12957a.p(Float.floatToRawIntBits(zbtwVar.d(i3)));
            i3++;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbwy
    public final void F(int i2, int i3) {
        this.f12957a.s(i2, i3);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbwy
    public final void G(int i2, zbtc zbtcVar) {
        this.f12957a.m(i2, zbtcVar);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbwy
    public final void H(int i2, List list, boolean z) {
        int i3 = 0;
        if (!(list instanceof zbva)) {
            if (!z) {
                while (i3 < list.size()) {
                    this.f12957a.E(i2, ((Long) list.get(i3)).longValue());
                    i3++;
                }
                return;
            }
            this.f12957a.B(i2, 2);
            int i4 = 0;
            for (int i5 = 0; i5 < list.size(); i5++) {
                i4 += zbtk.e(((Long) list.get(i5)).longValue());
            }
            this.f12957a.D(i4);
            while (i3 < list.size()) {
                this.f12957a.F(((Long) list.get(i3)).longValue());
                i3++;
            }
            return;
        }
        zbva zbvaVar = (zbva) list;
        if (!z) {
            while (i3 < zbvaVar.size()) {
                this.f12957a.E(i2, zbvaVar.d(i3));
                i3++;
            }
            return;
        }
        this.f12957a.B(i2, 2);
        int i6 = 0;
        for (int i7 = 0; i7 < zbvaVar.size(); i7++) {
            i6 += zbtk.e(zbvaVar.d(i7));
        }
        this.f12957a.D(i6);
        while (i3 < zbvaVar.size()) {
            this.f12957a.F(zbvaVar.d(i3));
            i3++;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbwy
    public final void I(int i2, double d2) {
        this.f12957a.q(i2, Double.doubleToRawLongBits(d2));
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbwy
    public final void J(int i2, List list) {
        for (int i3 = 0; i3 < list.size(); i3++) {
            this.f12957a.m(i2, (zbtc) list.get(i3));
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbwy
    public final void K(int i2) {
        this.f12957a.B(i2, 3);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbwy
    public final void L(int i2, long j2) {
        zbtk zbtkVar = this.f12957a;
        zbtkVar.E(i2, (j2 >> 63) ^ (j2 + j2));
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbwy
    public final void a(int i2, int i3) {
        this.f12957a.C(i2, i3);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbwy
    public final void b(int i2, List list, boolean z) {
        int i3 = 0;
        if (!(list instanceof zbug)) {
            if (!z) {
                while (i3 < list.size()) {
                    this.f12957a.o(i2, ((Integer) list.get(i3)).intValue());
                    i3++;
                }
                return;
            }
            this.f12957a.B(i2, 2);
            int i4 = 0;
            for (int i5 = 0; i5 < list.size(); i5++) {
                ((Integer) list.get(i5)).intValue();
                i4 += 4;
            }
            this.f12957a.D(i4);
            while (i3 < list.size()) {
                this.f12957a.p(((Integer) list.get(i3)).intValue());
                i3++;
            }
            return;
        }
        zbug zbugVar = (zbug) list;
        if (!z) {
            while (i3 < zbugVar.size()) {
                this.f12957a.o(i2, zbugVar.d(i3));
                i3++;
            }
            return;
        }
        this.f12957a.B(i2, 2);
        int i6 = 0;
        for (int i7 = 0; i7 < zbugVar.size(); i7++) {
            zbugVar.d(i7);
            i6 += 4;
        }
        this.f12957a.D(i6);
        while (i3 < zbugVar.size()) {
            this.f12957a.p(zbugVar.d(i3));
            i3++;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbwy
    public final void c(int i2, long j2) {
        this.f12957a.q(i2, j2);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbwy
    public final void d(int i2, Object obj, zbvx zbvxVar) {
        zbtk zbtkVar = this.f12957a;
        zbtkVar.B(i2, 3);
        zbvxVar.d((zbvm) obj, zbtkVar.f12956a);
        zbtkVar.B(i2, 4);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbwy
    public final void e(int i2, List list, boolean z) {
        int i3 = 0;
        if (!(list instanceof zbva)) {
            if (!z) {
                while (i3 < list.size()) {
                    this.f12957a.q(i2, ((Long) list.get(i3)).longValue());
                    i3++;
                }
                return;
            }
            this.f12957a.B(i2, 2);
            int i4 = 0;
            for (int i5 = 0; i5 < list.size(); i5++) {
                ((Long) list.get(i5)).longValue();
                i4 += 8;
            }
            this.f12957a.D(i4);
            while (i3 < list.size()) {
                this.f12957a.r(((Long) list.get(i3)).longValue());
                i3++;
            }
            return;
        }
        zbva zbvaVar = (zbva) list;
        if (!z) {
            while (i3 < zbvaVar.size()) {
                this.f12957a.q(i2, zbvaVar.d(i3));
                i3++;
            }
            return;
        }
        this.f12957a.B(i2, 2);
        int i6 = 0;
        for (int i7 = 0; i7 < zbvaVar.size(); i7++) {
            zbvaVar.d(i7);
            i6 += 8;
        }
        this.f12957a.D(i6);
        while (i3 < zbvaVar.size()) {
            this.f12957a.r(zbvaVar.d(i3));
            i3++;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbwy
    public final void f(int i2, int i3) {
        this.f12957a.o(i2, i3);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbwy
    public final void g(int i2, List list, boolean z) {
        int i3 = 0;
        if (!(list instanceof zbug)) {
            if (!z) {
                while (i3 < list.size()) {
                    this.f12957a.C(i2, ((Integer) list.get(i3)).intValue());
                    i3++;
                }
                return;
            }
            this.f12957a.B(i2, 2);
            int i4 = 0;
            for (int i5 = 0; i5 < list.size(); i5++) {
                i4 += zbtk.d(((Integer) list.get(i5)).intValue());
            }
            this.f12957a.D(i4);
            while (i3 < list.size()) {
                this.f12957a.D(((Integer) list.get(i3)).intValue());
                i3++;
            }
            return;
        }
        zbug zbugVar = (zbug) list;
        if (!z) {
            while (i3 < zbugVar.size()) {
                this.f12957a.C(i2, zbugVar.d(i3));
                i3++;
            }
            return;
        }
        this.f12957a.B(i2, 2);
        int i6 = 0;
        for (int i7 = 0; i7 < zbugVar.size(); i7++) {
            i6 += zbtk.d(zbugVar.d(i7));
        }
        this.f12957a.D(i6);
        while (i3 < zbugVar.size()) {
            this.f12957a.D(zbugVar.d(i3));
            i3++;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbwy
    public final void h(int i2, List list, boolean z) {
        int i3 = 0;
        if (!(list instanceof zbva)) {
            if (!z) {
                while (i3 < list.size()) {
                    zbtk zbtkVar = this.f12957a;
                    long longValue = ((Long) list.get(i3)).longValue();
                    zbtkVar.E(i2, (longValue >> 63) ^ (longValue + longValue));
                    i3++;
                }
                return;
            }
            this.f12957a.B(i2, 2);
            int i4 = 0;
            for (int i5 = 0; i5 < list.size(); i5++) {
                long longValue2 = ((Long) list.get(i5)).longValue();
                i4 += zbtk.e((longValue2 >> 63) ^ (longValue2 + longValue2));
            }
            this.f12957a.D(i4);
            while (i3 < list.size()) {
                zbtk zbtkVar2 = this.f12957a;
                long longValue3 = ((Long) list.get(i3)).longValue();
                zbtkVar2.F((longValue3 >> 63) ^ (longValue3 + longValue3));
                i3++;
            }
            return;
        }
        zbva zbvaVar = (zbva) list;
        if (!z) {
            while (i3 < zbvaVar.size()) {
                zbtk zbtkVar3 = this.f12957a;
                long d2 = zbvaVar.d(i3);
                zbtkVar3.E(i2, (d2 >> 63) ^ (d2 + d2));
                i3++;
            }
            return;
        }
        this.f12957a.B(i2, 2);
        int i6 = 0;
        for (int i7 = 0; i7 < zbvaVar.size(); i7++) {
            long d3 = zbvaVar.d(i7);
            i6 += zbtk.e((d3 >> 63) ^ (d3 + d3));
        }
        this.f12957a.D(i6);
        while (i3 < zbvaVar.size()) {
            zbtk zbtkVar4 = this.f12957a;
            long d4 = zbvaVar.d(i3);
            zbtkVar4.F((d4 >> 63) ^ (d4 + d4));
            i3++;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbwy
    public final void i(int i2, List list) {
        int i3 = 0;
        if (!(list instanceof zbux)) {
            while (i3 < list.size()) {
                this.f12957a.z(i2, (String) list.get(i3));
                i3++;
            }
            return;
        }
        zbux zbuxVar = (zbux) list;
        while (i3 < list.size()) {
            Object a2 = zbuxVar.a();
            if (a2 instanceof String) {
                this.f12957a.z(i2, (String) a2);
            } else {
                this.f12957a.m(i2, (zbtc) a2);
            }
            i3++;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbwy
    public final void j(int i2, List list, boolean z) {
        int i3 = 0;
        if (!(list instanceof zbug)) {
            if (!z) {
                while (i3 < list.size()) {
                    zbtk zbtkVar = this.f12957a;
                    int intValue = ((Integer) list.get(i3)).intValue();
                    zbtkVar.C(i2, (intValue >> 31) ^ (intValue + intValue));
                    i3++;
                }
                return;
            }
            this.f12957a.B(i2, 2);
            int i4 = 0;
            for (int i5 = 0; i5 < list.size(); i5++) {
                int intValue2 = ((Integer) list.get(i5)).intValue();
                i4 += zbtk.d((intValue2 >> 31) ^ (intValue2 + intValue2));
            }
            this.f12957a.D(i4);
            while (i3 < list.size()) {
                zbtk zbtkVar2 = this.f12957a;
                int intValue3 = ((Integer) list.get(i3)).intValue();
                zbtkVar2.D((intValue3 >> 31) ^ (intValue3 + intValue3));
                i3++;
            }
            return;
        }
        zbug zbugVar = (zbug) list;
        if (!z) {
            while (i3 < zbugVar.size()) {
                zbtk zbtkVar3 = this.f12957a;
                int d2 = zbugVar.d(i3);
                zbtkVar3.C(i2, (d2 >> 31) ^ (d2 + d2));
                i3++;
            }
            return;
        }
        this.f12957a.B(i2, 2);
        int i6 = 0;
        for (int i7 = 0; i7 < zbugVar.size(); i7++) {
            int d3 = zbugVar.d(i7);
            i6 += zbtk.d((d3 >> 31) ^ (d3 + d3));
        }
        this.f12957a.D(i6);
        while (i3 < zbugVar.size()) {
            zbtk zbtkVar4 = this.f12957a;
            int d4 = zbugVar.d(i3);
            zbtkVar4.D((d4 >> 31) ^ (d4 + d4));
            i3++;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbwy
    public final void k(int i2, List list, boolean z) {
        int i3 = 0;
        if (!(list instanceof zbtm)) {
            if (!z) {
                while (i3 < list.size()) {
                    this.f12957a.q(i2, Double.doubleToRawLongBits(((Double) list.get(i3)).doubleValue()));
                    i3++;
                }
                return;
            }
            this.f12957a.B(i2, 2);
            int i4 = 0;
            for (int i5 = 0; i5 < list.size(); i5++) {
                ((Double) list.get(i5)).doubleValue();
                i4 += 8;
            }
            this.f12957a.D(i4);
            while (i3 < list.size()) {
                this.f12957a.r(Double.doubleToRawLongBits(((Double) list.get(i3)).doubleValue()));
                i3++;
            }
            return;
        }
        zbtm zbtmVar = (zbtm) list;
        if (!z) {
            while (i3 < zbtmVar.size()) {
                this.f12957a.q(i2, Double.doubleToRawLongBits(zbtmVar.d(i3)));
                i3++;
            }
            return;
        }
        this.f12957a.B(i2, 2);
        int i6 = 0;
        for (int i7 = 0; i7 < zbtmVar.size(); i7++) {
            zbtmVar.d(i7);
            i6 += 8;
        }
        this.f12957a.D(i6);
        while (i3 < zbtmVar.size()) {
            this.f12957a.r(Double.doubleToRawLongBits(zbtmVar.d(i3)));
            i3++;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbwy
    public final void l(int i2, int i3) {
        this.f12957a.s(i2, i3);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbwy
    public final void m(int i2, long j2) {
        this.f12957a.E(i2, j2);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbwy
    public final void n(int i2, float f2) {
        this.f12957a.o(i2, Float.floatToRawIntBits(f2));
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbwy
    public final void o(int i2, List list, boolean z) {
        int i3 = 0;
        if (!(list instanceof zbug)) {
            if (!z) {
                while (i3 < list.size()) {
                    this.f12957a.s(i2, ((Integer) list.get(i3)).intValue());
                    i3++;
                }
                return;
            }
            this.f12957a.B(i2, 2);
            int i4 = 0;
            for (int i5 = 0; i5 < list.size(); i5++) {
                i4 += zbtk.e(((Integer) list.get(i5)).intValue());
            }
            this.f12957a.D(i4);
            while (i3 < list.size()) {
                this.f12957a.t(((Integer) list.get(i3)).intValue());
                i3++;
            }
            return;
        }
        zbug zbugVar = (zbug) list;
        if (!z) {
            while (i3 < zbugVar.size()) {
                this.f12957a.s(i2, zbugVar.d(i3));
                i3++;
            }
            return;
        }
        this.f12957a.B(i2, 2);
        int i6 = 0;
        for (int i7 = 0; i7 < zbugVar.size(); i7++) {
            i6 += zbtk.e(zbugVar.d(i7));
        }
        this.f12957a.D(i6);
        while (i3 < zbugVar.size()) {
            this.f12957a.t(zbugVar.d(i3));
            i3++;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbwy
    public final void p(int i2, Object obj) {
        boolean z = obj instanceof zbtc;
        zbtk zbtkVar = this.f12957a;
        if (z) {
            zbtkVar.y(i2, (zbtc) obj);
        } else {
            zbtkVar.x(i2, (zbvm) obj);
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbwy
    public final void q(int i2, List list, boolean z) {
        int i3 = 0;
        if (!(list instanceof zbug)) {
            if (!z) {
                while (i3 < list.size()) {
                    this.f12957a.o(i2, ((Integer) list.get(i3)).intValue());
                    i3++;
                }
                return;
            }
            this.f12957a.B(i2, 2);
            int i4 = 0;
            for (int i5 = 0; i5 < list.size(); i5++) {
                ((Integer) list.get(i5)).intValue();
                i4 += 4;
            }
            this.f12957a.D(i4);
            while (i3 < list.size()) {
                this.f12957a.p(((Integer) list.get(i3)).intValue());
                i3++;
            }
            return;
        }
        zbug zbugVar = (zbug) list;
        if (!z) {
            while (i3 < zbugVar.size()) {
                this.f12957a.o(i2, zbugVar.d(i3));
                i3++;
            }
            return;
        }
        this.f12957a.B(i2, 2);
        int i6 = 0;
        for (int i7 = 0; i7 < zbugVar.size(); i7++) {
            zbugVar.d(i7);
            i6 += 4;
        }
        this.f12957a.D(i6);
        while (i3 < zbugVar.size()) {
            this.f12957a.p(zbugVar.d(i3));
            i3++;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbwy
    public final void r(int i2, Object obj, zbvx zbvxVar) {
        this.f12957a.v(i2, (zbvm) obj, zbvxVar);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbwy
    public final void s(int i2, List list, boolean z) {
        int i3 = 0;
        if (!(list instanceof zbss)) {
            if (!z) {
                while (i3 < list.size()) {
                    this.f12957a.k(i2, ((Boolean) list.get(i3)).booleanValue());
                    i3++;
                }
                return;
            }
            this.f12957a.B(i2, 2);
            int i4 = 0;
            for (int i5 = 0; i5 < list.size(); i5++) {
                ((Boolean) list.get(i5)).booleanValue();
                i4++;
            }
            this.f12957a.D(i4);
            while (i3 < list.size()) {
                this.f12957a.j(((Boolean) list.get(i3)).booleanValue() ? (byte) 1 : (byte) 0);
                i3++;
            }
            return;
        }
        zbss zbssVar = (zbss) list;
        if (!z) {
            while (i3 < zbssVar.size()) {
                this.f12957a.k(i2, zbssVar.f(i3));
                i3++;
            }
            return;
        }
        this.f12957a.B(i2, 2);
        int i6 = 0;
        for (int i7 = 0; i7 < zbssVar.size(); i7++) {
            zbssVar.f(i7);
            i6++;
        }
        this.f12957a.D(i6);
        while (i3 < zbssVar.size()) {
            this.f12957a.j(zbssVar.f(i3) ? (byte) 1 : (byte) 0);
            i3++;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbwy
    public final void t(int i2, String str) {
        this.f12957a.z(i2, str);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbwy
    public final void u(int i2, List list, boolean z) {
        int i3 = 0;
        if (!(list instanceof zbug)) {
            if (!z) {
                while (i3 < list.size()) {
                    this.f12957a.s(i2, ((Integer) list.get(i3)).intValue());
                    i3++;
                }
                return;
            }
            this.f12957a.B(i2, 2);
            int i4 = 0;
            for (int i5 = 0; i5 < list.size(); i5++) {
                i4 += zbtk.e(((Integer) list.get(i5)).intValue());
            }
            this.f12957a.D(i4);
            while (i3 < list.size()) {
                this.f12957a.t(((Integer) list.get(i3)).intValue());
                i3++;
            }
            return;
        }
        zbug zbugVar = (zbug) list;
        if (!z) {
            while (i3 < zbugVar.size()) {
                this.f12957a.s(i2, zbugVar.d(i3));
                i3++;
            }
            return;
        }
        this.f12957a.B(i2, 2);
        int i6 = 0;
        for (int i7 = 0; i7 < zbugVar.size(); i7++) {
            i6 += zbtk.e(zbugVar.d(i7));
        }
        this.f12957a.D(i6);
        while (i3 < zbugVar.size()) {
            this.f12957a.t(zbugVar.d(i3));
            i3++;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbwy
    public final void v(int i2, zbve zbveVar, Map map) {
        for (Map.Entry entry : map.entrySet()) {
            this.f12957a.B(i2, 2);
            this.f12957a.D(zbvf.b(zbveVar, entry.getKey(), entry.getValue()));
            zbvf.e(this.f12957a, zbveVar, entry.getKey(), entry.getValue());
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbwy
    public final void w(int i2) {
        this.f12957a.B(i2, 4);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbwy
    public final void x(int i2, int i3) {
        zbtk zbtkVar = this.f12957a;
        zbtkVar.C(i2, (i3 >> 31) ^ (i3 + i3));
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbwy
    public final void y(int i2, boolean z) {
        this.f12957a.k(i2, z);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbwy
    public final void z(int i2, List list, boolean z) {
        int i3 = 0;
        if (!(list instanceof zbva)) {
            if (!z) {
                while (i3 < list.size()) {
                    this.f12957a.q(i2, ((Long) list.get(i3)).longValue());
                    i3++;
                }
                return;
            }
            this.f12957a.B(i2, 2);
            int i4 = 0;
            for (int i5 = 0; i5 < list.size(); i5++) {
                ((Long) list.get(i5)).longValue();
                i4 += 8;
            }
            this.f12957a.D(i4);
            while (i3 < list.size()) {
                this.f12957a.r(((Long) list.get(i3)).longValue());
                i3++;
            }
            return;
        }
        zbva zbvaVar = (zbva) list;
        if (!z) {
            while (i3 < zbvaVar.size()) {
                this.f12957a.q(i2, zbvaVar.d(i3));
                i3++;
            }
            return;
        }
        this.f12957a.B(i2, 2);
        int i6 = 0;
        for (int i7 = 0; i7 < zbvaVar.size(); i7++) {
            zbvaVar.d(i7);
            i6 += 8;
        }
        this.f12957a.D(i6);
        while (i3 < zbvaVar.size()) {
            this.f12957a.r(zbvaVar.d(i3));
            i3++;
        }
    }
}
