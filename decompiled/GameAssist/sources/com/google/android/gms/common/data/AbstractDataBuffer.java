package com.google.android.gms.common.data;

import java.util.Iterator;

/* loaded from: classes.dex */
public abstract class AbstractDataBuffer<T> implements DataBuffer<T> {

    /* renamed from: c, reason: collision with root package name */
    protected final DataHolder f10886c;

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        release();
    }

    @Override // com.google.android.gms.common.data.DataBuffer
    public abstract Object get(int i2);

    @Override // com.google.android.gms.common.data.DataBuffer
    public int getCount() {
        DataHolder dataHolder = this.f10886c;
        if (dataHolder == null) {
            return 0;
        }
        return dataHolder.getCount();
    }

    @Override // java.lang.Iterable
    public Iterator iterator() {
        return new DataBufferIterator(this);
    }

    @Override // com.google.android.gms.common.api.Releasable
    public void release() {
        DataHolder dataHolder = this.f10886c;
        if (dataHolder != null) {
            dataHolder.close();
        }
    }
}
