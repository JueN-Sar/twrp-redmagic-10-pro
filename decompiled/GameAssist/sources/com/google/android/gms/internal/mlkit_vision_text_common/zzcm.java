package com.google.android.gms.internal.mlkit_vision_text_common;

import java.util.Iterator;

/* loaded from: classes.dex */
abstract class zzcm implements Iterator {

    /* renamed from: c, reason: collision with root package name */
    final Iterator f13133c;

    zzcm(Iterator it) {
        it.getClass();
        this.f13133c = it;
    }

    abstract Object b(Object obj);

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f13133c.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        return b(this.f13133c.next());
    }

    @Override // java.util.Iterator
    public final void remove() {
        this.f13133c.remove();
    }
}
