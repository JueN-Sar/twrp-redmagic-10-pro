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
public final class UShortArray implements Collection<UShort>, KMappedMarker {

    /* renamed from: c, reason: collision with root package name */
    private final short[] f18285c;

    @Metadata
    private static final class Iterator implements java.util.Iterator<UShort>, KMappedMarker {

        /* renamed from: c, reason: collision with root package name */
        private final short[] f18286c;

        /* renamed from: h, reason: collision with root package name */
        private int f18287h;

        public Iterator(short[] array) {
            Intrinsics.e(array, "array");
            this.f18286c = array;
        }

        public short b() {
            int i2 = this.f18287h;
            short[] sArr = this.f18286c;
            if (i2 >= sArr.length) {
                throw new NoSuchElementException(String.valueOf(this.f18287h));
            }
            this.f18287h = i2 + 1;
            return UShort.d(sArr[i2]);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f18287h < this.f18286c.length;
        }

        @Override // java.util.Iterator
        public /* bridge */ /* synthetic */ UShort next() {
            return UShort.c(b());
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    public static boolean d(short[] sArr, short s2) {
        boolean x;
        x = ArraysKt___ArraysKt.x(sArr, s2);
        return x;
    }

    public static boolean f(short[] sArr, Collection elements) {
        boolean x;
        Intrinsics.e(elements, "elements");
        if (elements.isEmpty()) {
            return true;
        }
        for (Object obj : elements) {
            if (obj instanceof UShort) {
                x = ArraysKt___ArraysKt.x(sArr, ((UShort) obj).j());
                if (x) {
                }
            }
            return false;
        }
        return true;
    }

    public static boolean g(short[] sArr, Object obj) {
        return (obj instanceof UShortArray) && Intrinsics.a(sArr, ((UShortArray) obj).o());
    }

    public static final short h(short[] sArr, int i2) {
        return UShort.d(sArr[i2]);
    }

    public static int j(short[] sArr) {
        return sArr.length;
    }

    public static int k(short[] sArr) {
        return Arrays.hashCode(sArr);
    }

    public static boolean l(short[] sArr) {
        return sArr.length == 0;
    }

    public static java.util.Iterator m(short[] sArr) {
        return new Iterator(sArr);
    }

    public static String n(short[] sArr) {
        return "UShortArray(storage=" + Arrays.toString(sArr) + ')';
    }

    @Override // java.util.Collection
    public /* bridge */ /* synthetic */ boolean add(UShort uShort) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean addAll(Collection<? extends UShort> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean b(short s2) {
        return d(this.f18285c, s2);
    }

    @Override // java.util.Collection
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof UShort) {
            return b(((UShort) obj).j());
        }
        return false;
    }

    @Override // java.util.Collection
    public boolean containsAll(Collection elements) {
        Intrinsics.e(elements, "elements");
        return f(this.f18285c, elements);
    }

    @Override // java.util.Collection
    public boolean equals(Object obj) {
        return g(this.f18285c, obj);
    }

    @Override // java.util.Collection
    public int hashCode() {
        return k(this.f18285c);
    }

    @Override // java.util.Collection
    /* renamed from: i, reason: merged with bridge method [inline-methods] */
    public int size() {
        return j(this.f18285c);
    }

    @Override // java.util.Collection
    public boolean isEmpty() {
        return l(this.f18285c);
    }

    @Override // java.util.Collection, java.lang.Iterable
    public java.util.Iterator iterator() {
        return m(this.f18285c);
    }

    public final /* synthetic */ short[] o() {
        return this.f18285c;
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
        return n(this.f18285c);
    }

    @Override // java.util.Collection
    public Object[] toArray(Object[] array) {
        Intrinsics.e(array, "array");
        return CollectionToArray.b(this, array);
    }
}
