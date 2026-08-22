package com.google.android.gms.common.api;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataBuffer;
import java.util.Iterator;

@KeepForSdk
/* loaded from: classes.dex */
public class DataBufferResponse<T, R extends AbstractDataBuffer<T> & Result> extends Response<R> implements DataBuffer<T> {
    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        ((AbstractDataBuffer) d()).close();
    }

    @Override // com.google.android.gms.common.data.DataBuffer
    public final Object get(int i2) {
        return ((AbstractDataBuffer) d()).get(i2);
    }

    @Override // com.google.android.gms.common.data.DataBuffer
    public final int getCount() {
        return ((AbstractDataBuffer) d()).getCount();
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return ((AbstractDataBuffer) d()).iterator();
    }

    @Override // com.google.android.gms.common.api.Releasable
    public final void release() {
        ((AbstractDataBuffer) d()).release();
    }
}
