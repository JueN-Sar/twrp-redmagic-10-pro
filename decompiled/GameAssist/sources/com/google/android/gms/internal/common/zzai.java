package com.google.android.gms.internal.common;

import com.zte.shared.wrapper.VirtualHandleWrapper;
import org.jspecify.nullness.NullMarked;

@NullMarked
/* loaded from: classes.dex */
final class zzai extends zzag {
    static final zzag zza = new zzai(new Object[0], 0);
    final transient Object[] zzb;
    private final transient int zzc;

    zzai(Object[] objArr, int i2) {
        this.zzb = objArr;
        this.zzc = i2;
    }

    @Override // com.google.android.gms.internal.common.zzag, com.google.android.gms.internal.common.zzac
    final int b(Object[] objArr, int i2) {
        System.arraycopy(this.zzb, 0, objArr, 0, this.zzc);
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.common.zzac
    final int d() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.common.zzac
    final int f() {
        return 0;
    }

    @Override // java.util.List
    public final Object get(int i2) {
        zzs.a(i2, this.zzc, VirtualHandleWrapper.KEY_INDEX);
        Object obj = this.zzb[i2];
        obj.getClass();
        return obj;
    }

    @Override // com.google.android.gms.internal.common.zzac
    final boolean i() {
        return false;
    }

    @Override // com.google.android.gms.internal.common.zzac
    final Object[] j() {
        return this.zzb;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }
}
