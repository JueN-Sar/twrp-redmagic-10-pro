package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* loaded from: classes.dex */
final class zbkz extends AbstractList implements RandomAccess, Serializable {
    final List zba;
    final zbkf zbb;

    zbkz(List list, zbkf zbkfVar) {
        list.getClass();
        this.zba = list;
        this.zbb = zbkfVar;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i2) {
        return this.zbb.a(this.zba.get(i2));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean isEmpty() {
        return this.zba.isEmpty();
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        return listIterator();
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator listIterator(int i2) {
        return new zbky(this, this.zba.listIterator(i2));
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object remove(int i2) {
        return this.zbb.a(this.zba.remove(i2));
    }

    @Override // java.util.AbstractList
    protected final void removeRange(int i2, int i3) {
        this.zba.subList(i2, i3).clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zba.size();
    }
}
