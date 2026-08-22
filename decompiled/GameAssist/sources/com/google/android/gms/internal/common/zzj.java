package com.google.android.gms.internal.common;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.jspecify.nullness.NullMarked;

@NullMarked
/* loaded from: classes.dex */
abstract class zzj implements Iterator {

    /* renamed from: c, reason: collision with root package name */
    private Object f11397c;

    /* renamed from: h, reason: collision with root package name */
    private int f11398h = 2;

    protected zzj() {
    }

    protected abstract Object b();

    protected final Object c() {
        this.f11398h = 3;
        return null;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        int i2 = this.f11398h;
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
            this.f11398h = 4;
            this.f11397c = b();
            if (this.f11398h != 3) {
                this.f11398h = 1;
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
        this.f11398h = 2;
        Object obj = this.f11397c;
        this.f11397c = null;
        return obj;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
