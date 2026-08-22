package com.google.android.gms.internal.mlkit_vision_text_common;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.RandomAccess;
import java.util.Set;

/* loaded from: classes.dex */
abstract class zzal extends zzan implements Serializable {
    private transient Map zza;
    private transient int zzb;

    protected zzal(Map map) {
        if (!map.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.zza = map;
    }

    static /* bridge */ /* synthetic */ void n(zzal zzalVar, Object obj) {
        Object obj2;
        Map map = zzalVar.zza;
        map.getClass();
        try {
            obj2 = map.remove(obj);
        } catch (ClassCastException | NullPointerException unused) {
            obj2 = null;
        }
        Collection collection = (Collection) obj2;
        if (collection != null) {
            int size = collection.size();
            collection.clear();
            zzalVar.zzb -= size;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzcc
    public final boolean a(Object obj, Object obj2) {
        Collection collection = (Collection) this.zza.get(obj);
        if (collection != null) {
            if (!collection.add(obj2)) {
                return false;
            }
            this.zzb++;
            return true;
        }
        Collection g2 = g();
        if (!g2.add(obj2)) {
            throw new AssertionError("New Collection violated the Collection spec");
        }
        this.zzb++;
        this.zza.put(obj, g2);
        return true;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzan
    final Map e() {
        return new zzad(this, this.zza);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzan
    final Set f() {
        return new zzaf(this, this.zza);
    }

    abstract Collection g();

    Collection h(Object obj, Collection collection) {
        throw null;
    }

    public final Collection j(Object obj) {
        Collection collection = (Collection) this.zza.get(obj);
        if (collection == null) {
            collection = g();
        }
        return h(obj, collection);
    }

    final List k(Object obj, List list, zzai zzaiVar) {
        return list instanceof RandomAccess ? new zzag(this, obj, list, zzaiVar) : new zzak(this, obj, list, zzaiVar);
    }

    public final void o() {
        Iterator it = this.zza.values().iterator();
        while (it.hasNext()) {
            ((Collection) it.next()).clear();
        }
        this.zza.clear();
        this.zzb = 0;
    }
}
