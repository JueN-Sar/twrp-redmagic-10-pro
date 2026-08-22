package com.google.android.gms.internal.mlkit_common;

import java.util.Arrays;

/* loaded from: classes.dex */
public final class zzac extends zzz {
    public zzac() {
        super(4);
    }

    public final zzac b(Object obj) {
        obj.getClass();
        int i2 = this.f11858b + 1;
        Object[] objArr = this.f11857a;
        int length = objArr.length;
        if (length < i2) {
            this.f11857a = Arrays.copyOf(objArr, zzaa.a(length, i2));
            this.f11859c = false;
        } else if (this.f11859c) {
            this.f11857a = (Object[]) objArr.clone();
            this.f11859c = false;
        }
        Object[] objArr2 = this.f11857a;
        int i3 = this.f11858b;
        this.f11858b = i3 + 1;
        objArr2[i3] = obj;
        return this;
    }

    public final zzaf c() {
        this.f11859c = true;
        return zzaf.j(this.f11857a, this.f11858b);
    }
}
