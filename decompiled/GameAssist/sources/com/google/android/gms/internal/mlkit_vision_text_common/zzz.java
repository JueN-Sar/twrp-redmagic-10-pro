package com.google.android.gms.internal.mlkit_vision_text_common;

import com.zte.shared.wrapper.VirtualHandleWrapper;
import java.util.NoSuchElementException;

/* loaded from: classes.dex */
abstract class zzz extends zzcp {

    /* renamed from: c, reason: collision with root package name */
    private final int f13631c;

    /* renamed from: h, reason: collision with root package name */
    private int f13632h;

    protected zzz(int i2, int i3) {
        zzx.b(i3, i2, VirtualHandleWrapper.KEY_INDEX);
        this.f13631c = i2;
        this.f13632h = i3;
    }

    protected abstract Object b(int i2);

    @Override // java.util.Iterator, java.util.ListIterator
    public final boolean hasNext() {
        return this.f13632h < this.f13631c;
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.f13632h > 0;
    }

    @Override // java.util.Iterator, java.util.ListIterator
    public final Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int i2 = this.f13632h;
        this.f13632h = i2 + 1;
        return b(i2);
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.f13632h;
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }
        int i2 = this.f13632h - 1;
        this.f13632h = i2;
        return b(i2);
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.f13632h - 1;
    }
}
