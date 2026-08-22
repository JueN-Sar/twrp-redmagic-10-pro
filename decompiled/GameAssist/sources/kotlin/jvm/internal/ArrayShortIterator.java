package kotlin.jvm.internal;

import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.ShortIterator;

@Metadata
/* loaded from: classes2.dex */
final class ArrayShortIterator extends ShortIterator {

    /* renamed from: c, reason: collision with root package name */
    private final short[] f18545c;

    /* renamed from: h, reason: collision with root package name */
    private int f18546h;

    public ArrayShortIterator(short[] array) {
        Intrinsics.e(array, "array");
        this.f18545c = array;
    }

    @Override // kotlin.collections.ShortIterator
    public short b() {
        try {
            short[] sArr = this.f18545c;
            int i2 = this.f18546h;
            this.f18546h = i2 + 1;
            return sArr[i2];
        } catch (ArrayIndexOutOfBoundsException e2) {
            this.f18546h--;
            throw new NoSuchElementException(e2.getMessage());
        }
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f18546h < this.f18545c.length;
    }
}
