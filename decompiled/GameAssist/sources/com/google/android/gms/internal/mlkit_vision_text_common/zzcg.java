package com.google.android.gms.internal.mlkit_vision_text_common;

import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
final class zzcg extends zzbn {
    private final transient zzbm zza;
    private final transient Object[] zzb;
    private final transient int zzc = 1;

    zzcg(zzbm zzbmVar, Object[] objArr, int i2, int i3) {
        this.zza = zzbmVar;
        this.zzb = objArr;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzbf
    final int b(Object[] objArr, int i2) {
        return i().b(objArr, 0);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzbf, java.util.AbstractCollection, java.util.Collection
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

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzbn, com.google.android.gms.internal.mlkit_vision_text_common.zzbf
    /* renamed from: g */
    public final zzco iterator() {
        return i().listIterator(0);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzbn, com.google.android.gms.internal.mlkit_vision_text_common.zzbf, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final /* synthetic */ Iterator iterator() {
        return i().listIterator(0);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzbn
    final zzbk j() {
        return new zzcf(this);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zzc;
    }
}
