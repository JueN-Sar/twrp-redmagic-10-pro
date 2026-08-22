package com.google.android.gms.internal.mlkit_vision_common;

import com.zte.shared.wrapper.VirtualHandleWrapper;

/* loaded from: classes.dex */
final class zzy extends zzp {
    private final transient Object[] zza;
    private final transient int zzb;
    private final transient int zzc;

    zzy(Object[] objArr, int i2, int i3) {
        this.zza = objArr;
        this.zzb = i2;
        this.zzc = i3;
    }

    @Override // java.util.List
    public final Object get(int i2) {
        zzf.a(i2, this.zzc, VirtualHandleWrapper.KEY_INDEX);
        Object obj = this.zza[i2 + i2 + this.zzb];
        obj.getClass();
        return obj;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }
}
