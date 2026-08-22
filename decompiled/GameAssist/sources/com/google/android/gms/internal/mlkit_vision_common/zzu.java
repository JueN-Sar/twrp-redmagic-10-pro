package com.google.android.gms.internal.mlkit_vision_common;

import com.zte.shared.wrapper.VirtualHandleWrapper;

/* loaded from: classes.dex */
final class zzu extends zzp {
    static final zzp zza = new zzu(new Object[0], 0);
    final transient Object[] zzb;
    private final transient int zzc;

    zzu(Object[] objArr, int i2) {
        this.zzb = objArr;
        this.zzc = i2;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzp, com.google.android.gms.internal.mlkit_vision_common.zzl
    final int b(Object[] objArr, int i2) {
        System.arraycopy(this.zzb, 0, objArr, 0, this.zzc);
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzl
    final int d() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzl
    final int f() {
        return 0;
    }

    @Override // java.util.List
    public final Object get(int i2) {
        zzf.a(i2, this.zzc, VirtualHandleWrapper.KEY_INDEX);
        Object obj = this.zzb[i2];
        obj.getClass();
        return obj;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzl
    final Object[] h() {
        return this.zzb;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }
}
