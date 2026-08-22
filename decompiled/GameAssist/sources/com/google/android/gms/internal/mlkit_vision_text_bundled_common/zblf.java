package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import java.util.ListIterator;

/* loaded from: classes.dex */
abstract class zblf extends zble implements ListIterator {
    zblf(ListIterator listIterator) {
        super(listIterator);
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return ((ListIterator) this.f12858c).hasPrevious();
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return ((ListIterator) this.f12858c).nextIndex();
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        return b(((ListIterator) this.f12858c).previous());
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return ((ListIterator) this.f12858c).previousIndex();
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        throw new UnsupportedOperationException();
    }
}
