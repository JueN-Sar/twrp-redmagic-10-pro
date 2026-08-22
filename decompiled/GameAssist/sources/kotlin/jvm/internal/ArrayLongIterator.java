package kotlin.jvm.internal;

import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.LongIterator;

@Metadata
/* loaded from: classes2.dex */
final class ArrayLongIterator extends LongIterator {

    /* renamed from: c, reason: collision with root package name */
    private final long[] f18543c;

    /* renamed from: h, reason: collision with root package name */
    private int f18544h;

    public ArrayLongIterator(long[] array) {
        Intrinsics.e(array, "array");
        this.f18543c = array;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f18544h < this.f18543c.length;
    }

    @Override // kotlin.collections.LongIterator
    public long nextLong() {
        try {
            long[] jArr = this.f18543c;
            int i2 = this.f18544h;
            this.f18544h = i2 + 1;
            return jArr[i2];
        } catch (ArrayIndexOutOfBoundsException e2) {
            this.f18544h--;
            throw new NoSuchElementException(e2.getMessage());
        }
    }
}
