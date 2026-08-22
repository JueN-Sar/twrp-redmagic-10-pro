package com.google.android.gms.internal.mlkit_common;

import java.util.Arrays;

/* loaded from: classes.dex */
public final class zzah {

    /* renamed from: a, reason: collision with root package name */
    Object[] f11420a = new Object[8];

    /* renamed from: b, reason: collision with root package name */
    int f11421b = 0;

    /* renamed from: c, reason: collision with root package name */
    zzag f11422c;

    public final zzah a(Object obj, Object obj2) {
        int i2 = this.f11421b + 1;
        Object[] objArr = this.f11420a;
        int length = objArr.length;
        int i3 = i2 + i2;
        if (i3 > length) {
            this.f11420a = Arrays.copyOf(objArr, zzaa.a(length, i3));
        }
        zzw.a(obj, obj2);
        Object[] objArr2 = this.f11420a;
        int i4 = this.f11421b;
        int i5 = i4 + i4;
        objArr2[i5] = obj;
        objArr2[i5 + 1] = obj2;
        this.f11421b = i4 + 1;
        return this;
    }

    public final zzai b() {
        zzag zzagVar = this.f11422c;
        if (zzagVar != null) {
            throw zzagVar.a();
        }
        zzaq g2 = zzaq.g(this.f11421b, this.f11420a, this);
        zzag zzagVar2 = this.f11422c;
        if (zzagVar2 == null) {
            return g2;
        }
        throw zzagVar2.a();
    }
}
