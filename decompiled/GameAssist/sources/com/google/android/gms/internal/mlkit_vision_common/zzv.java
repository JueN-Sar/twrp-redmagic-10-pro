package com.google.android.gms.internal.mlkit_vision_common;

import com.zte.shared.wrapper.VirtualHandleWrapper;
import java.util.AbstractMap;

/* loaded from: classes.dex */
final class zzv extends zzp {
    final /* synthetic */ zzw zza;

    zzv(zzw zzwVar) {
        this.zza = zzwVar;
    }

    @Override // java.util.List
    public final /* bridge */ /* synthetic */ Object get(int i2) {
        int i3;
        Object[] objArr;
        Object[] objArr2;
        i3 = this.zza.zzc;
        zzf.a(i2, i3, VirtualHandleWrapper.KEY_INDEX);
        zzw zzwVar = this.zza;
        objArr = zzwVar.zzb;
        int i4 = i2 + i2;
        Object obj = objArr[i4];
        obj.getClass();
        objArr2 = zzwVar.zzb;
        Object obj2 = objArr2[i4 + 1];
        obj2.getClass();
        return new AbstractMap.SimpleImmutableEntry(obj, obj2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        int i2;
        i2 = this.zza.zzc;
        return i2;
    }
}
