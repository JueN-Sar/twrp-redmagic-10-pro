package com.google.android.gms.common.data;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import java.util.NoSuchElementException;

@KeepForSdk
/* loaded from: classes.dex */
public class SingleRefDataBufferIterator<T> extends DataBufferIterator<T> {

    /* renamed from: i, reason: collision with root package name */
    private Object f10916i;

    @Override // com.google.android.gms.common.data.DataBufferIterator, java.util.Iterator
    public final Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException("Cannot advance the iterator beyond " + this.f10894h);
        }
        int i2 = this.f10894h + 1;
        this.f10894h = i2;
        if (i2 == 0) {
            Object i3 = Preconditions.i(this.f10893c.get(0));
            this.f10916i = i3;
            if (!(i3 instanceof DataBufferRef)) {
                throw new IllegalStateException("DataBuffer reference of type " + String.valueOf(i3.getClass()) + " is not movable");
            }
        } else {
            ((DataBufferRef) Preconditions.i(this.f10916i)).a(this.f10894h);
        }
        return this.f10916i;
    }
}
