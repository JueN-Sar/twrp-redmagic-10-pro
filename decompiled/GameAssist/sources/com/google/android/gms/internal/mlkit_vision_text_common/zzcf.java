package com.google.android.gms.internal.mlkit_vision_text_common;

import com.zte.shared.wrapper.VirtualHandleWrapper;
import java.util.AbstractMap;
import java.util.Objects;

/* loaded from: classes.dex */
final class zzcf extends zzbk {
    final /* synthetic */ zzcg zza;

    zzcf(zzcg zzcgVar) {
        this.zza = zzcgVar;
    }

    @Override // java.util.List
    public final /* bridge */ /* synthetic */ Object get(int i2) {
        int i3;
        Object[] objArr;
        Object[] objArr2;
        i3 = this.zza.zzc;
        zzx.a(i2, i3, VirtualHandleWrapper.KEY_INDEX);
        objArr = this.zza.zzb;
        int i4 = i2 + i2;
        Object obj = objArr[i4];
        Objects.requireNonNull(obj);
        objArr2 = this.zza.zzb;
        Object obj2 = objArr2[i4 + 1];
        Objects.requireNonNull(obj2);
        return new AbstractMap.SimpleImmutableEntry(obj, obj2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        int i2;
        i2 = this.zza.zzc;
        return i2;
    }
}
