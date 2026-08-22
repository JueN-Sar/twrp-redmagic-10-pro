package com.google.android.gms.internal.mlkit_vision_text_common;

import com.zte.shared.wrapper.VirtualHandleWrapper;
import java.util.Objects;

/* loaded from: classes.dex */
final class zzci extends zzbk {
    private final transient Object[] zza;
    private final transient int zzb;
    private final transient int zzc = 1;

    zzci(Object[] objArr, int i2, int i3) {
        this.zza = objArr;
        this.zzb = i2;
    }

    @Override // java.util.List
    public final Object get(int i2) {
        zzx.a(i2, this.zzc, VirtualHandleWrapper.KEY_INDEX);
        Object obj = this.zza[i2 + i2 + this.zzb];
        Objects.requireNonNull(obj);
        return obj;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }
}
