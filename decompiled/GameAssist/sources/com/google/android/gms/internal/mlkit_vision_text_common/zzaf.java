package com.google.android.gms.internal.mlkit_vision_text_common;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
final class zzaf extends zzby {

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ zzal f13098h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzaf(zzal zzalVar, Map map) {
        super(map);
        this.f13098h = zzalVar;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzby, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        zzbo.a(iterator());
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean containsAll(Collection collection) {
        return this.f13128c.keySet().containsAll(collection);
    }

    @Override // java.util.AbstractSet, java.util.Collection, java.util.Set
    public final boolean equals(Object obj) {
        return this == obj || this.f13128c.keySet().equals(obj);
    }

    @Override // java.util.AbstractSet, java.util.Collection, java.util.Set
    public final int hashCode() {
        return this.f13128c.keySet().hashCode();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzby, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new zzae(this, this.f13128c.entrySet().iterator());
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzby, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        int i2;
        Collection collection = (Collection) this.f13128c.remove(obj);
        if (collection == null) {
            return false;
        }
        int size = collection.size();
        collection.clear();
        zzal zzalVar = this.f13098h;
        i2 = zzalVar.zzb;
        zzalVar.zzb = i2 - size;
        return size > 0;
    }
}
