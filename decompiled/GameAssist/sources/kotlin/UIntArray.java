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
public final class UIntArray implements Collection<UInt>, KMappedMarker {

    /* renamed from: c, reason: collision with root package name */
    private final int[] f18274c;

    @Metadata
    private static final class Iterator implements java.util.Iterator<UInt>, KMappedMarker {

        /* renamed from: c, reason: collision with root package name */
        private final int[] f18275c;

        /* renamed from: h, reason: collision with root package name */
        private int f18276h;

        public Iterator(int[] array) {
            Intrinsics.e(array, "array");
            this.f18275c = array;
        }

        public int b() {
            int i2 = this.f18276h;
            int[] iArr = this.f18275c;
            if (i2 >= iArr.length) {
                throw new NoSuchElementException(String.valueOf(this.f18276h));
            }
            this.f18276h = i2 + 1;
            return UInt.d(iArr[i2]);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f18276h < this.f18275c.length;
        }

        @Override // java.util.Iterator
        public /* bridge */ /* synthetic */ UInt next() {
            return UInt.c(b());
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    public static boolean d(int[] iArr, int i2) {
        boolean u;
        u = ArraysKt___ArraysKt.u(iArr, i2);
        return u;
    }

    public static boolean f(int[] iArr, Collection elements) {
        boolean u;
        Intrinsics.e(elements, "elements");
        if (elements.isEmpty()) {
            return true;
        }
        for (Object obj : elements) {
            if (obj instanceof UInt) {
                u = ArraysKt___ArraysKt.u(iArr, ((UInt) obj).j());
                if (u) {
                }
            }
            return false;
        }
        return true;
    }

    public static boolean g(int[] iArr, Object obj) {
        return (obj instanceof UIntArray) && Intrinsics.a(iArr, ((UIntArray) obj).o());
    }

    public static final int h(int[] iArr, int i2) {
        return UInt.d(iArr[i2]);
    }

    public static int j(int[] iArr) {
        return iArr.length;
    }

    public static int k(int[] iArr) {
        return Arrays.hashCode(iArr);
    }

    public static boolean l(int[] iArr) {
        return iArr.length == 0;
    }

    public static java.util.Iterator m(int[] iArr) {
        return new Iterator(iArr);
    }

    public static String n(int[] iArr) {
        return "UIntArray(storage=" + Arrays.toString(iArr) + ')';
    }

    @Override // java.util.Collection
    public /* bridge */ /* synthetic */ boolean add(UInt uInt) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean addAll(Collection<? extends UInt> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean b(int i2) {
        return d(this.f18274c, i2);
    }

    @Override // java.util.Collection
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof UInt) {
            return b(((UInt) obj).j());
        }
        return false;
    }

    @Override // java.util.Collection
    public boolean containsAll(Collection elements) {
        Intrinsics.e(elements, "elements");
        return f(this.f18274c, elements);
    }

    @Override // java.util.Collection
    public boolean equals(Object obj) {
        return g(this.f18274c, obj);
    }

    @Override // java.util.Collection
    public int hashCode() {
        return k(this.f18274c);
    }

    @Override // java.util.Collection
    /* renamed from: i, reason: merged with bridge method [inline-methods] */
    public int size() {
        return j(this.f18274c);
    }

    @Override // java.util.Collection
    public boolean isEmpty() {
        return l(this.f18274c);
    }

    @Override // java.util.Collection, java.lang.Iterable
    public java.util.Iterator iterator() {
        return m(this.f18274c);
    }

    public final /* synthetic */ int[] o() {
        return this.f18274c;
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
        return n(this.f18274c);
    }

    @Override // java.util.Collection
    public Object[] toArray(Object[] array) {
        Intrinsics.e(array, "array");
        return CollectionToArray.b(this, array);
    }
}
