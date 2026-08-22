package com.google.android.gms.internal.mlkit_vision_common;

import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
final class zzw extends zzs {
    private final transient zzr zza;
    private final transient Object[] zzb;
    private final transient int zzc;

    zzw(zzr zzrVar, Object[] objArr, int i2, int i3) {
        this.zza = zzrVar;
        this.zzb = objArr;
        this.zzc = i3;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzl
    final int b(Object[] objArr, int i2) {
        return i().b(objArr, 0);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzl, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (value != null && value.equals(this.zza.get(key))) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzs, com.google.android.gms.internal.mlkit_vision_common.zzl
    /* renamed from: g */
    public final zzab iterator() {
        return i().listIterator(0);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzs, com.google.android.gms.internal.mlkit_vision_common.zzl, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final /* synthetic */ Iterator iterator() {
        return i().listIterator(0);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzs
    final zzp j() {
        return new zzv(this);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zzc;
    }
}
