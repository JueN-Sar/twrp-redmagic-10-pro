package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import java.io.Serializable;
import java.util.AbstractSequentialList;
import java.util.List;
import java.util.ListIterator;

/* loaded from: classes.dex */
final class zblb extends AbstractSequentialList implements Serializable {
    final List zba;
    final zbkf zbb;

    zblb(List list, zbkf zbkfVar) {
        list.getClass();
        this.zba = list;
        this.zbb = zbkfVar;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean isEmpty() {
        return this.zba.isEmpty();
    }

    @Override // java.util.AbstractSequentialList, java.util.AbstractList, java.util.List
    public final ListIterator listIterator(int i2) {
        return new zbla(this, this.zba.listIterator(i2));
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
