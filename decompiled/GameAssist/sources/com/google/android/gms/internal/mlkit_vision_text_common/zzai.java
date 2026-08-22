package com.google.android.gms.internal.mlkit_vision_text_common;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
class zzai extends AbstractCollection {

    /* renamed from: c, reason: collision with root package name */
    final Object f13102c;

    /* renamed from: h, reason: collision with root package name */
    Collection f13103h;

    /* renamed from: i, reason: collision with root package name */
    final zzai f13104i;

    /* renamed from: j, reason: collision with root package name */
    final Collection f13105j;

    /* renamed from: k, reason: collision with root package name */
    final /* synthetic */ zzal f13106k;

    zzai(zzal zzalVar, Object obj, Collection collection, zzai zzaiVar) {
        this.f13106k = zzalVar;
        this.f13102c = obj;
        this.f13103h = collection;
        this.f13104i = zzaiVar;
        this.f13105j = zzaiVar == null ? null : zzaiVar.f13103h;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean add(Object obj) {
        int i2;
        d();
        boolean isEmpty = this.f13103h.isEmpty();
        boolean add = this.f13103h.add(obj);
        if (add) {
            zzal zzalVar = this.f13106k;
            i2 = zzalVar.zzb;
            zzalVar.zzb = i2 + 1;
            if (isEmpty) {
                b();
                return true;
            }
        }
        return add;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean addAll(Collection collection) {
        int i2;
        if (collection.isEmpty()) {
            return false;
        }
        int size = size();
        boolean addAll = this.f13103h.addAll(collection);
        if (addAll) {
            int size2 = this.f13103h.size();
            zzal zzalVar = this.f13106k;
            i2 = zzalVar.zzb;
            zzalVar.zzb = i2 + (size2 - size);
            if (size == 0) {
                b();
                return true;
            }
        }
        return addAll;
    }

    final void b() {
        Map map;
        zzai zzaiVar = this.f13104i;
        if (zzaiVar != null) {
            zzaiVar.b();
            return;
        }
        zzal zzalVar = this.f13106k;
        Object obj = this.f13102c;
        map = zzalVar.zza;
        map.put(obj, this.f13103h);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final void clear() {
        int i2;
        int size = size();
        if (size == 0) {
            return;
        }
        this.f13103h.clear();
        zzal zzalVar = this.f13106k;
        i2 = zzalVar.zzb;
        zzalVar.zzb = i2 - size;
        f();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object obj) {
        d();
        return this.f13103h.contains(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean containsAll(Collection collection) {
        d();
        return this.f13103h.containsAll(collection);
    }

    final void d() {
        Map map;
        zzai zzaiVar = this.f13104i;
        if (zzaiVar != null) {
            zzaiVar.d();
            zzai zzaiVar2 = this.f13104i;
            if (zzaiVar2.f13103h != this.f13105j) {
                throw new ConcurrentModificationException();
            }
            return;
        }
        if (this.f13103h.isEmpty()) {
            zzal zzalVar = this.f13106k;
            Object obj = this.f13102c;
            map = zzalVar.zza;
            Collection collection = (Collection) map.get(obj);
            if (collection != null) {
                this.f13103h = collection;
            }
        }
    }

    @Override // java.util.Collection
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        d();
        return this.f13103h.equals(obj);
    }

    final void f() {
        Map map;
        zzai zzaiVar = this.f13104i;
        if (zzaiVar != null) {
            zzaiVar.f();
        } else if (this.f13103h.isEmpty()) {
            zzal zzalVar = this.f13106k;
            Object obj = this.f13102c;
            map = zzalVar.zza;
            map.remove(obj);
        }
    }

    @Override // java.util.Collection
    public final int hashCode() {
        d();
        return this.f13103h.hashCode();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        d();
        return new zzah(this);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean remove(Object obj) {
        int i2;
        d();
        boolean remove = this.f13103h.remove(obj);
        if (remove) {
            zzal zzalVar = this.f13106k;
            i2 = zzalVar.zzb;
            zzalVar.zzb = i2 - 1;
            f();
        }
        return remove;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean removeAll(Collection collection) {
        int i2;
        if (collection.isEmpty()) {
            return false;
        }
        int size = size();
        boolean removeAll = this.f13103h.removeAll(collection);
        if (removeAll) {
            int size2 = this.f13103h.size();
            zzal zzalVar = this.f13106k;
            int i3 = size2 - size;
            i2 = zzalVar.zzb;
            zzalVar.zzb = i2 + i3;
            f();
        }
        return removeAll;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean retainAll(Collection collection) {
        int i2;
        collection.getClass();
        int size = size();
        boolean retainAll = this.f13103h.retainAll(collection);
        if (retainAll) {
            int size2 = this.f13103h.size();
            zzal zzalVar = this.f13106k;
            int i3 = size2 - size;
            i2 = zzalVar.zzb;
            zzalVar.zzb = i2 + i3;
            f();
        }
        return retainAll;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final int size() {
        d();
        return this.f13103h.size();
    }

    @Override // java.util.AbstractCollection
    public final String toString() {
        d();
        return this.f13103h.toString();
    }
}
