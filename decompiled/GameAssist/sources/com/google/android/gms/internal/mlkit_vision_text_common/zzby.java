package com.google.android.gms.internal.mlkit_vision_text_common;

import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
class zzby extends zzck {

    /* renamed from: c, reason: collision with root package name */
    final Map f13128c;

    zzby(Map map) {
        map.getClass();
        this.f13128c = map;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public void clear() {
        this.f13128c.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        return this.f13128c.containsKey(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean isEmpty() {
        return this.f13128c.isEmpty();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator iterator() {
        return new zzbv(this.f13128c.entrySet().iterator());
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean remove(Object obj) {
        if (!contains(obj)) {
            return false;
        }
        this.f13128c.remove(obj);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.f13128c.size();
    }
}
