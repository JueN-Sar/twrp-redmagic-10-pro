package com.google.android.gms.internal.mlkit_vision_text_common;

import com.zte.shared.wrapper.VirtualHandleWrapper;
import java.util.Objects;

/* loaded from: classes.dex */
final class zzce extends zzbk {
    static final zzbk zza = new zzce(new Object[0], 0);
    final transient Object[] zzb;
    private final transient int zzc;

    zzce(Object[] objArr, int i2) {
        this.zzb = objArr;
        this.zzc = i2;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzbk, com.google.android.gms.internal.mlkit_vision_text_common.zzbf
    final int b(Object[] objArr, int i2) {
        System.arraycopy(this.zzb, 0, objArr, 0, this.zzc);
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzbf
    final int d() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzbf
    final int f() {
        return 0;
    }

    @Override // java.util.List
    public final Object get(int i2) {
        zzx.a(i2, this.zzc, VirtualHandleWrapper.KEY_INDEX);
        Object obj = this.zzb[i2];
        Objects.requireNonNull(obj);
        return obj;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzbf
    final Object[] h() {
        return this.zzb;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }
}
