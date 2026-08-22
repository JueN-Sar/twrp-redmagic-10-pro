package com.google.android.gms.internal.mlkit_vision_text_common;

import java.util.Iterator;

/* loaded from: classes.dex */
final class zzch extends zzbn {
    private final transient zzbm zza;
    private final transient zzbk zzb;

    zzch(zzbm zzbmVar, zzbk zzbkVar) {
        this.zza = zzbmVar;
        this.zzb = zzbkVar;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzbf
    final int b(Object[] objArr, int i2) {
        return this.zzb.b(objArr, 0);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzbf, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object obj) {
        return this.zza.get(obj) != null;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzbn, com.google.android.gms.internal.mlkit_vision_text_common.zzbf
    /* renamed from: g */
    public final zzco iterator() {
        return this.zzb.listIterator(0);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzbn, com.google.android.gms.internal.mlkit_vision_text_common.zzbf, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final /* synthetic */ Iterator iterator() {
        return this.zzb.listIterator(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return 1;
    }
}
