package com.google.android.gms.internal.mlkit_vision_text_common;

import java.io.Serializable;
import java.util.AbstractSequentialList;
import java.util.List;
import java.util.ListIterator;

/* loaded from: classes.dex */
final class zzbt extends AbstractSequentialList implements Serializable {
    final List zza;
    final zzu zzb;

    zzbt(List list, zzu zzuVar) {
        list.getClass();
        this.zza = list;
        this.zzb = zzuVar;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean isEmpty() {
        return this.zza.isEmpty();
    }

    @Override // java.util.AbstractSequentialList, java.util.AbstractList, java.util.List
    public final ListIterator listIterator(int i2) {
        return new zzbs(this, this.zza.listIterator(i2));
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
