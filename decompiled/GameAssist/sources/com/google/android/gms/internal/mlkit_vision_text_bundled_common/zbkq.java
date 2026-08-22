package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import com.zte.shared.wrapper.VirtualHandleWrapper;
import java.util.NoSuchElementException;

/* loaded from: classes.dex */
abstract class zbkq extends zblh {

    /* renamed from: c, reason: collision with root package name */
    private final int f12849c;

    /* renamed from: h, reason: collision with root package name */
    private int f12850h;

    protected zbkq(int i2, int i3) {
        zbkj.b(i3, i2, VirtualHandleWrapper.KEY_INDEX);
        this.f12849c = i2;
        this.f12850h = i3;
    }

    protected abstract Object b(int i2);

    @Override // java.util.Iterator, java.util.ListIterator
    public final boolean hasNext() {
        return this.f12850h < this.f12849c;
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.f12850h > 0;
    }

    @Override // java.util.Iterator, java.util.ListIterator
    public final Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int i2 = this.f12850h;
        this.f12850h = i2 + 1;
        return b(i2);
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.f12850h;
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }
        int i2 = this.f12850h - 1;
        this.f12850h = i2;
        return b(i2);
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.f12850h - 1;
    }
}
