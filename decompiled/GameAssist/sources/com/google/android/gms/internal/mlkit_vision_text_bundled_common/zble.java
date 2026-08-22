package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import java.util.Iterator;

/* loaded from: classes.dex */
abstract class zble implements Iterator {

    /* renamed from: c, reason: collision with root package name */
    final Iterator f12858c;

    zble(Iterator it) {
        it.getClass();
        this.f12858c = it;
    }

    abstract Object b(Object obj);

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f12858c.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        return b(this.f12858c.next());
    }

    @Override // java.util.Iterator
    public final void remove() {
        this.f12858c.remove();
    }
}
