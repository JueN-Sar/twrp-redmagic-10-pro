package com.google.android.gms.internal.mlkit_vision_text_common;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
final class zzad extends zzca {

    /* renamed from: j, reason: collision with root package name */
    final transient Map f13093j;

    /* renamed from: k, reason: collision with root package name */
    final /* synthetic */ zzal f13094k;

    zzad(zzal zzalVar, Map map) {
        this.f13094k = zzalVar;
        this.f13093j = map;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzca
    protected final Set a() {
        return new zzab(this);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        Map map;
        zzal zzalVar = this.f13094k;
        Map map2 = this.f13093j;
        map = zzalVar.zza;
        if (map2 == map) {
            zzalVar.o();
        } else {
            zzbo.a(new zzac(this));
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(Object obj) {
        return zzcb.b(this.f13093j, obj);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean equals(Object obj) {
        return this == obj || this.f13093j.equals(obj);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final /* bridge */ /* synthetic */ Object get(Object obj) {
        Collection collection = (Collection) zzcb.a(this.f13093j, obj);
        if (collection == null) {
            return null;
        }
        return this.f13094k.h(obj, collection);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int hashCode() {
        return this.f13093j.hashCode();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzca, java.util.AbstractMap, java.util.Map
    public final Set keySet() {
        return this.f13094k.d();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final /* bridge */ /* synthetic */ Object remove(Object obj) {
        int i2;
        Collection collection = (Collection) this.f13093j.remove(obj);
        if (collection == null) {
            return null;
        }
        Collection g2 = this.f13094k.g();
        g2.addAll(collection);
        zzal zzalVar = this.f13094k;
        i2 = zzalVar.zzb;
        zzalVar.zzb = i2 - collection.size();
        collection.clear();
        return g2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        return this.f13093j.size();
    }

    @Override // java.util.AbstractMap
    public final String toString() {
        return this.f13093j.toString();
    }
}
