package com.google.android.gms.internal.mlkit_common;

import com.zte.shared.wrapper.VirtualHandleWrapper;
import java.util.NoSuchElementException;

/* loaded from: classes.dex */
abstract class zzv extends zzat {

    /* renamed from: c, reason: collision with root package name */
    private final int f11855c;

    /* renamed from: h, reason: collision with root package name */
    private int f11856h;

    protected zzv(int i2, int i3) {
        zzt.b(i3, i2, VirtualHandleWrapper.KEY_INDEX);
        this.f11855c = i2;
        this.f11856h = i3;
    }

    protected abstract Object b(int i2);

    @Override // java.util.Iterator, java.util.ListIterator
    public final boolean hasNext() {
        return this.f11856h < this.f11855c;
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.f11856h > 0;
    }

    @Override // java.util.Iterator, java.util.ListIterator
    public final Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int i2 = this.f11856h;
        this.f11856h = i2 + 1;
        return b(i2);
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.f11856h;
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }
        int i2 = this.f11856h - 1;
        this.f11856h = i2;
        return b(i2);
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.f11856h - 1;
    }
}
