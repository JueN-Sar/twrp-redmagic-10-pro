package kotlin.collections;

import java.util.Arrays;
import java.util.Iterator;
import java.util.RandomAccess;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt___RangesKt;

@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
final class RingBuffer<T> extends AbstractList<T> implements RandomAccess {

    /* renamed from: c, reason: collision with root package name */
    private final Object[] f18356c;

    /* renamed from: h, reason: collision with root package name */
    private final int f18357h;

    /* renamed from: i, reason: collision with root package name */
    private int f18358i;

    /* renamed from: j, reason: collision with root package name */
    private int f18359j;

    public RingBuffer(Object[] buffer, int i2) {
        Intrinsics.e(buffer, "buffer");
        this.f18356c = buffer;
        if (i2 < 0) {
            throw new IllegalArgumentException(("ring buffer filled size should not be negative but it is " + i2).toString());
        }
        if (i2 <= buffer.length) {
            this.f18357h = buffer.length;
            this.f18359j = i2;
            return;
        }
        throw new IllegalArgumentException(("ring buffer filled size: " + i2 + " cannot be larger than the buffer size: " + buffer.length).toString());
    }

    @Override // kotlin.collections.AbstractCollection
    public int b() {
        return this.f18359j;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public Object get(int i2) {
        AbstractList.Companion.b(i2, size());
        return this.f18356c[(this.f18358i + i2) % this.f18357h];
    }

    public final void h(Object obj) {
        if (j()) {
            throw new IllegalStateException("ring buffer is full");
        }
        this.f18356c[(this.f18358i + size()) % this.f18357h] = obj;
        this.f18359j = size() + 1;
    }

    public final RingBuffer i(int i2) {
        int c2;
        Object[] array;
        int i3 = this.f18357h;
        c2 = RangesKt___RangesKt.c(i3 + (i3 >> 1) + 1, i2);
        if (this.f18358i == 0) {
            array = Arrays.copyOf(this.f18356c, c2);
            Intrinsics.d(array, "copyOf(this, newSize)");
        } else {
            array = toArray(new Object[c2]);
        }
        return new RingBuffer(array, size());
    }

    @Override // kotlin.collections.AbstractList, java.util.Collection, java.lang.Iterable, java.util.List
    public Iterator iterator() {
        return new AbstractIterator<T>() { // from class: kotlin.collections.RingBuffer$iterator$1

            /* renamed from: i, reason: collision with root package name */
            private int f18360i;

            /* renamed from: j, reason: collision with root package name */
            private int f18361j;

            {
                int i2;
                this.f18360i = RingBuffer.this.size();
                i2 = RingBuffer.this.f18358i;
                this.f18361j = i2;
            }

            @Override // kotlin.collections.AbstractIterator
            protected void b() {
                Object[] objArr;
                if (this.f18360i == 0) {
                    c();
                    return;
                }
                objArr = RingBuffer.this.f18356c;
                d(objArr[this.f18361j]);
                this.f18361j = (this.f18361j + 1) % RingBuffer.this.f18357h;
                this.f18360i--;
            }
        };
    }

    public final boolean j() {
        return size() == this.f18357h;
    }

    public final void k(int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException(("n shouldn't be negative but it is " + i2).toString());
        }
        if (i2 > size()) {
            throw new IllegalArgumentException(("n shouldn't be greater than the buffer size: n = " + i2 + ", size = " + size()).toString());
        }
        if (i2 > 0) {
            int i3 = this.f18358i;
            int i4 = (i3 + i2) % this.f18357h;
            if (i3 > i4) {
                ArraysKt___ArraysJvmKt.m(this.f18356c, null, i3, this.f18357h);
                ArraysKt___ArraysJvmKt.m(this.f18356c, null, 0, i4);
            } else {
                ArraysKt___ArraysJvmKt.m(this.f18356c, null, i3, i4);
            }
            this.f18358i = i4;
            this.f18359j = size() - i2;
        }
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public Object[] toArray(Object[] array) {
        Intrinsics.e(array, "array");
        if (array.length < size()) {
            array = Arrays.copyOf(array, size());
            Intrinsics.d(array, "copyOf(this, newSize)");
        }
        int size = size();
        int i2 = 0;
        int i3 = 0;
        for (int i4 = this.f18358i; i3 < size && i4 < this.f18357h; i4++) {
            array[i3] = this.f18356c[i4];
            i3++;
        }
        while (i3 < size) {
            array[i3] = this.f18356c[i2];
            i3++;
            i2++;
        }
        if (array.length > size()) {
            array[size()] = null;
        }
        return array;
    }

    public RingBuffer(int i2) {
        this(new Object[i2], 0);
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public Object[] toArray() {
        return toArray(new Object[size()]);
    }
}
