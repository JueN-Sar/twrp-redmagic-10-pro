package com.google.android.gms.internal.mlkit_vision_text_common;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* loaded from: classes.dex */
final class zzbr extends AbstractList implements RandomAccess, Serializable {
    final List zza;
    final zzu zzb;

    zzbr(List list, zzu zzuVar) {
        list.getClass();
        this.zza = list;
        this.zzb = zzuVar;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i2) {
        return this.zzb.a(this.zza.get(i2));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean isEmpty() {
        return this.zza.isEmpty();
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        return listIterator();
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator listIterator(int i2) {
        return new zzbq(this, this.zza.listIterator(i2));
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object remove(int i2) {
        return this.zzb.a(this.zza.remove(i2));
    }

    @Override // java.util.AbstractList
    protected final void removeRange(int i2, int i3) {
        this.zza.subList(i2, i3).clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zza.size();
    }
}
