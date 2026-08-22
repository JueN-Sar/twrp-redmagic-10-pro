package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* loaded from: classes.dex */
abstract class zbjz implements Iterator {

    /* renamed from: c, reason: collision with root package name */
    private Object f12838c;

    /* renamed from: h, reason: collision with root package name */
    private int f12839h = 2;

    protected zbjz() {
    }

    protected abstract Object b();

    protected final Object c() {
        this.f12839h = 3;
        return null;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        int i2 = this.f12839h;
        if (i2 == 4) {
            throw new IllegalStateException();
        }
        int i3 = i2 - 1;
        if (i2 == 0) {
            throw null;
        }
        if (i3 == 0) {
            return true;
        }
        if (i3 != 2) {
            this.f12839h = 4;
            this.f12838c = b();
            if (this.f12839h != 3) {
                this.f12839h = 1;
                return true;
            }
        }
        return false;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        this.f12839h = 2;
        Object obj = this.f12838c;
        this.f12838c = null;
        return obj;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
