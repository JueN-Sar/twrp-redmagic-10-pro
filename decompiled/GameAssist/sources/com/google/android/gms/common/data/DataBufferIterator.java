package com.google.android.gms.common.data;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Iterator;
import java.util.NoSuchElementException;

@KeepForSdk
/* loaded from: classes.dex */
public class DataBufferIterator<T> implements Iterator<T> {

    /* renamed from: c, reason: collision with root package name */
    protected final DataBuffer f10893c;

    /* renamed from: h, reason: collision with root package name */
    protected int f10894h = -1;

    public DataBufferIterator(DataBuffer dataBuffer) {
        this.f10893c = (DataBuffer) Preconditions.i(dataBuffer);
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f10894h < this.f10893c.getCount() + (-1);
    }

    @Override // java.util.Iterator
    public Object next() {
        if (hasNext()) {
            DataBuffer dataBuffer = this.f10893c;
            int i2 = this.f10894h + 1;
            this.f10894h = i2;
            return dataBuffer.get(i2);
        }
        throw new NoSuchElementException("Cannot advance the iterator beyond " + this.f10894h);
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Cannot remove elements from a DataBufferIterator");
    }
}
