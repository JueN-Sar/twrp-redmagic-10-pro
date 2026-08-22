package kotlin;

import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMappedMarker;

@SinceKotlin
@Metadata
@ExperimentalUnsignedTypes
@JvmInline
@SourceDebugExtension
/* loaded from: classes2.dex */
public final class ULongArray implements Collection<ULong>, KMappedMarker {

    /* renamed from: c, reason: collision with root package name */
    private final long[] f18279c;

    @Metadata
    private static final class Iterator implements java.util.Iterator<ULong>, KMappedMarker {

        /* renamed from: c, reason: collision with root package name */
        private final long[] f18280c;

        /* renamed from: h, reason: collision with root package name */
        private int f18281h;

        public Iterator(long[] array) {
            Intrinsics.e(array, "array");
            this.f18280c = array;
        }

        public long b() {
            int i2 = this.f18281h;
            long[] jArr = this.f18280c;
            if (i2 >= jArr.length) {
                throw new NoSuchElementException(String.valueOf(this.f18281h));
            }
            this.f18281h = i2 + 1;
            return ULong.d(jArr[i2]);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f18281h < this.f18280c.length;
        }

        @Override // java.util.Iterator
        public /* bridge */ /* synthetic */ ULong next() {
            return ULong.c(b());
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    public static boolean d(long[] jArr, long j2) {
        boolean v;
        v = ArraysKt___ArraysKt.v(jArr, j2);
        return v;
    }

    public static boolean f(long[] jArr, Collection elements) {
        boolean v;
        Intrinsics.e(elements, "elements");
        if (elements.isEmpty()) {
            return true;
        }
        for (Object obj : elements) {
            if (obj instanceof ULong) {
                v = ArraysKt___ArraysKt.v(jArr, ((ULong) obj).j());
                if (v) {
                }
            }
            return false;
        }
        return true;
    }

    public static boolean g(long[] jArr, Object obj) {
        return (obj instanceof ULongArray) && Intrinsics.a(jArr, ((ULongArray) obj).o());
    }

    public static final long h(long[] jArr, int i2) {
        return ULong.d(jArr[i2]);
    }

    public static int j(long[] jArr) {
        return jArr.length;
    }

    public static int k(long[] jArr) {
        return Arrays.hashCode(jArr);
    }

    public static boolean l(long[] jArr) {
        return jArr.length == 0;
    }

    public static java.util.Iterator m(long[] jArr) {
        return new Iterator(jArr);
    }

    public static String n(long[] jArr) {
        return "ULongArray(storage=" + Arrays.toString(jArr) + ')';
    }

    @Override // java.util.Collection
    public /* bridge */ /* synthetic */ boolean add(ULong uLong) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean addAll(Collection<? extends ULong> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean b(long j2) {
        return d(this.f18279c, j2);
    }

    @Override // java.util.Collection
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof ULong) {
            return b(((ULong) obj).j());
        }
        return false;
    }

    @Override // java.util.Collection
    public boolean containsAll(Collection elements) {
        Intrinsics.e(elements, "elements");
        return f(this.f18279c, elements);
    }

    @Override // java.util.Collection
    public boolean equals(Object obj) {
        return g(this.f18279c, obj);
    }

    @Override // java.util.Collection
    public int hashCode() {
        return k(this.f18279c);
    }

    @Override // java.util.Collection
    /* renamed from: i, reason: merged with bridge method [inline-methods] */
    public int size() {
        return j(this.f18279c);
    }

    @Override // java.util.Collection
    public boolean isEmpty() {
        return l(this.f18279c);
    }

    @Override // java.util.Collection, java.lang.Iterable
    public java.util.Iterator iterator() {
        return m(this.f18279c);
    }

    public final /* synthetic */ long[] o() {
        return this.f18279c;
    }

    @Override // java.util.Collection
    public boolean remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean removeAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean retainAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public Object[] toArray() {
        return CollectionToArray.a(this);
    }

    public String toString() {
        return n(this.f18279c);
    }

    @Override // java.util.Collection
    public Object[] toArray(Object[] array) {
        Intrinsics.e(array, "array");
        return CollectionToArray.b(this, array);
    }
}
