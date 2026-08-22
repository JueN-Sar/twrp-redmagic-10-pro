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
public final class UByteArray implements Collection<UByte>, KMappedMarker {

    /* renamed from: c, reason: collision with root package name */
    private final byte[] f18269c;

    @Metadata
    private static final class Iterator implements java.util.Iterator<UByte>, KMappedMarker {

        /* renamed from: c, reason: collision with root package name */
        private final byte[] f18270c;

        /* renamed from: h, reason: collision with root package name */
        private int f18271h;

        public Iterator(byte[] array) {
            Intrinsics.e(array, "array");
            this.f18270c = array;
        }

        public byte b() {
            int i2 = this.f18271h;
            byte[] bArr = this.f18270c;
            if (i2 >= bArr.length) {
                throw new NoSuchElementException(String.valueOf(this.f18271h));
            }
            this.f18271h = i2 + 1;
            return UByte.d(bArr[i2]);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f18271h < this.f18270c.length;
        }

        @Override // java.util.Iterator
        public /* bridge */ /* synthetic */ UByte next() {
            return UByte.c(b());
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    public static boolean d(byte[] bArr, byte b2) {
        boolean s2;
        s2 = ArraysKt___ArraysKt.s(bArr, b2);
        return s2;
    }

    public static boolean f(byte[] bArr, Collection elements) {
        boolean s2;
        Intrinsics.e(elements, "elements");
        if (elements.isEmpty()) {
            return true;
        }
        for (Object obj : elements) {
            if (obj instanceof UByte) {
                s2 = ArraysKt___ArraysKt.s(bArr, ((UByte) obj).j());
                if (s2) {
                }
            }
            return false;
        }
        return true;
    }

    public static boolean g(byte[] bArr, Object obj) {
        return (obj instanceof UByteArray) && Intrinsics.a(bArr, ((UByteArray) obj).o());
    }

    public static final byte h(byte[] bArr, int i2) {
        return UByte.d(bArr[i2]);
    }

    public static int j(byte[] bArr) {
        return bArr.length;
    }

    public static int k(byte[] bArr) {
        return Arrays.hashCode(bArr);
    }

    public static boolean l(byte[] bArr) {
        return bArr.length == 0;
    }

    public static java.util.Iterator m(byte[] bArr) {
        return new Iterator(bArr);
    }

    public static String n(byte[] bArr) {
        return "UByteArray(storage=" + Arrays.toString(bArr) + ')';
    }

    @Override // java.util.Collection
    public /* bridge */ /* synthetic */ boolean add(UByte uByte) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean addAll(Collection<? extends UByte> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean b(byte b2) {
        return d(this.f18269c, b2);
    }

    @Override // java.util.Collection
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof UByte) {
            return b(((UByte) obj).j());
        }
        return false;
    }

    @Override // java.util.Collection
    public boolean containsAll(Collection elements) {
        Intrinsics.e(elements, "elements");
        return f(this.f18269c, elements);
    }

    @Override // java.util.Collection
    public boolean equals(Object obj) {
        return g(this.f18269c, obj);
    }

    @Override // java.util.Collection
    public int hashCode() {
        return k(this.f18269c);
    }

    @Override // java.util.Collection
    /* renamed from: i, reason: merged with bridge method [inline-methods] */
    public int size() {
        return j(this.f18269c);
    }

    @Override // java.util.Collection
    public boolean isEmpty() {
        return l(this.f18269c);
    }

    @Override // java.util.Collection, java.lang.Iterable
    public java.util.Iterator iterator() {
        return m(this.f18269c);
    }

    public final /* synthetic */ byte[] o() {
        return this.f18269c;
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
        return n(this.f18269c);
    }

    @Override // java.util.Collection
    public Object[] toArray(Object[] array) {
        Intrinsics.e(array, "array");
        return CollectionToArray.b(this, array);
    }
}
