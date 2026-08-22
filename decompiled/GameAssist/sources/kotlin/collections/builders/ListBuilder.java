package kotlin.collections.builders;

import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import kotlin.Metadata;
import kotlin.collections.AbstractList;
import kotlin.collections.AbstractMutableList;
import kotlin.collections.ArrayDeque;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMutableList;
import kotlin.jvm.internal.markers.KMutableListIterator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata
/* loaded from: classes2.dex */
public final class ListBuilder<E> extends AbstractMutableList<E> implements List<E>, RandomAccess, Serializable, KMutableList {

    @NotNull
    private E[] array;

    @Nullable
    private final ListBuilder<E> backing;
    private boolean isReadOnly;
    private int length;
    private int offset;

    @Nullable
    private final ListBuilder<E> root;

    @Metadata
    @SourceDebugExtension
    private static final class Itr<E> implements ListIterator<E>, KMutableListIterator {

        /* renamed from: c, reason: collision with root package name */
        private final ListBuilder f18368c;

        /* renamed from: h, reason: collision with root package name */
        private int f18369h;

        /* renamed from: i, reason: collision with root package name */
        private int f18370i;

        public Itr(ListBuilder list, int i2) {
            Intrinsics.e(list, "list");
            this.f18368c = list;
            this.f18369h = i2;
            this.f18370i = -1;
        }

        @Override // java.util.ListIterator
        public void add(Object obj) {
            ListBuilder listBuilder = this.f18368c;
            int i2 = this.f18369h;
            this.f18369h = i2 + 1;
            listBuilder.add(i2, obj);
            this.f18370i = -1;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public boolean hasNext() {
            return this.f18369h < this.f18368c.length;
        }

        @Override // java.util.ListIterator
        public boolean hasPrevious() {
            return this.f18369h > 0;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public Object next() {
            if (this.f18369h >= this.f18368c.length) {
                throw new NoSuchElementException();
            }
            int i2 = this.f18369h;
            this.f18369h = i2 + 1;
            this.f18370i = i2;
            return this.f18368c.array[this.f18368c.offset + this.f18370i];
        }

        @Override // java.util.ListIterator
        public int nextIndex() {
            return this.f18369h;
        }

        @Override // java.util.ListIterator
        public Object previous() {
            int i2 = this.f18369h;
            if (i2 <= 0) {
                throw new NoSuchElementException();
            }
            int i3 = i2 - 1;
            this.f18369h = i3;
            this.f18370i = i3;
            return this.f18368c.array[this.f18368c.offset + this.f18370i];
        }

        @Override // java.util.ListIterator
        public int previousIndex() {
            return this.f18369h - 1;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public void remove() {
            int i2 = this.f18370i;
            if (i2 == -1) {
                throw new IllegalStateException("Call next() or previous() before removing element from the iterator.".toString());
            }
            this.f18368c.remove(i2);
            this.f18369h = this.f18370i;
            this.f18370i = -1;
        }

        @Override // java.util.ListIterator
        public void set(Object obj) {
            int i2 = this.f18370i;
            if (i2 == -1) {
                throw new IllegalStateException("Call next() or previous() before replacing element from the iterator.".toString());
            }
            this.f18368c.set(i2, obj);
        }
    }

    private ListBuilder(E[] eArr, int i2, int i3, boolean z, ListBuilder<E> listBuilder, ListBuilder<E> listBuilder2) {
        this.array = eArr;
        this.offset = i2;
        this.length = i3;
        this.isReadOnly = z;
        this.backing = listBuilder;
        this.root = listBuilder2;
    }

    private final void i(int i2, Collection collection, int i3) {
        ListBuilder<E> listBuilder = this.backing;
        if (listBuilder != null) {
            listBuilder.i(i2, collection, i3);
            this.array = this.backing.array;
            this.length += i3;
        } else {
            p(i2, i3);
            Iterator<E> it = collection.iterator();
            for (int i4 = 0; i4 < i3; i4++) {
                this.array[i2 + i4] = it.next();
            }
        }
    }

    private final void j(int i2, Object obj) {
        ListBuilder<E> listBuilder = this.backing;
        if (listBuilder == null) {
            p(i2, 1);
            ((E[]) this.array)[i2] = obj;
        } else {
            listBuilder.j(i2, obj);
            this.array = this.backing.array;
            this.length++;
        }
    }

    private final void l() {
        if (q()) {
            throw new UnsupportedOperationException();
        }
    }

    private final boolean m(List list) {
        boolean h2;
        h2 = ListBuilderKt.h(this.array, this.offset, this.length, list);
        return h2;
    }

    private final void n(int i2) {
        if (this.backing != null) {
            throw new IllegalStateException();
        }
        if (i2 < 0) {
            throw new OutOfMemoryError();
        }
        E[] eArr = this.array;
        if (i2 > eArr.length) {
            this.array = (E[]) ListBuilderKt.e(this.array, ArrayDeque.f18309j.a(eArr.length, i2));
        }
    }

    private final void o(int i2) {
        n(this.length + i2);
    }

    private final void p(int i2, int i3) {
        o(i3);
        E[] eArr = this.array;
        ArraysKt___ArraysJvmKt.g(eArr, eArr, i2 + i3, i2, this.offset + this.length);
        this.length += i3;
    }

    private final boolean q() {
        ListBuilder<E> listBuilder;
        return this.isReadOnly || ((listBuilder = this.root) != null && listBuilder.isReadOnly);
    }

    private final Object r(int i2) {
        ListBuilder<E> listBuilder = this.backing;
        if (listBuilder != null) {
            this.length--;
            return listBuilder.r(i2);
        }
        E[] eArr = this.array;
        E e2 = eArr[i2];
        ArraysKt___ArraysJvmKt.g(eArr, eArr, i2, i2 + 1, this.offset + this.length);
        ListBuilderKt.f(this.array, (this.offset + this.length) - 1);
        this.length--;
        return e2;
    }

    private final void s(int i2, int i3) {
        ListBuilder<E> listBuilder = this.backing;
        if (listBuilder != null) {
            listBuilder.s(i2, i3);
        } else {
            E[] eArr = this.array;
            ArraysKt___ArraysJvmKt.g(eArr, eArr, i2, i2 + i3, this.length);
            E[] eArr2 = this.array;
            int i4 = this.length;
            ListBuilderKt.g(eArr2, i4 - i3, i4);
        }
        this.length -= i3;
    }

    private final int t(int i2, int i3, Collection collection, boolean z) {
        ListBuilder<E> listBuilder = this.backing;
        if (listBuilder != null) {
            int t = listBuilder.t(i2, i3, collection, z);
            this.length -= t;
            return t;
        }
        int i4 = 0;
        int i5 = 0;
        while (i4 < i3) {
            int i6 = i2 + i4;
            if (collection.contains(this.array[i6]) == z) {
                E[] eArr = this.array;
                i4++;
                eArr[i5 + i2] = eArr[i6];
                i5++;
            } else {
                i4++;
            }
        }
        int i7 = i3 - i5;
        E[] eArr2 = this.array;
        ArraysKt___ArraysJvmKt.g(eArr2, eArr2, i2 + i5, i3 + i2, this.length);
        E[] eArr3 = this.array;
        int i8 = this.length;
        ListBuilderKt.g(eArr3, i8 - i7, i8);
        this.length -= i7;
        return i7;
    }

    private final Object writeReplace() {
        if (q()) {
            return new SerializedCollection(this, 0);
        }
        throw new NotSerializableException("The list cannot be serialized while it is being built.");
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(Object obj) {
        l();
        j(this.offset + this.length, obj);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(Collection elements) {
        Intrinsics.e(elements, "elements");
        l();
        int size = elements.size();
        i(this.offset + this.length, elements, size);
        return size > 0;
    }

    @Override // kotlin.collections.AbstractMutableList
    public int b() {
        return this.length;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        l();
        s(this.offset, this.length);
    }

    @Override // kotlin.collections.AbstractMutableList
    public Object d(int i2) {
        l();
        AbstractList.Companion.b(i2, this.length);
        return r(this.offset + i2);
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof List) && m((List) obj));
    }

    @Override // java.util.AbstractList, java.util.List
    public Object get(int i2) {
        AbstractList.Companion.b(i2, this.length);
        return this.array[this.offset + i2];
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public int hashCode() {
        int i2;
        i2 = ListBuilderKt.i(this.array, this.offset, this.length);
        return i2;
    }

    @Override // java.util.AbstractList, java.util.List
    public int indexOf(Object obj) {
        for (int i2 = 0; i2 < this.length; i2++) {
            if (Intrinsics.a(this.array[this.offset + i2], obj)) {
                return i2;
            }
        }
        return -1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean isEmpty() {
        return this.length == 0;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public Iterator iterator() {
        return new Itr(this, 0);
    }

    public final List k() {
        if (this.backing != null) {
            throw new IllegalStateException();
        }
        l();
        this.isReadOnly = true;
        return this;
    }

    @Override // java.util.AbstractList, java.util.List
    public int lastIndexOf(Object obj) {
        for (int i2 = this.length - 1; i2 >= 0; i2--) {
            if (Intrinsics.a(this.array[this.offset + i2], obj)) {
                return i2;
            }
        }
        return -1;
    }

    @Override // java.util.AbstractList, java.util.List
    public ListIterator listIterator() {
        return new Itr(this, 0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean remove(Object obj) {
        l();
        int indexOf = indexOf(obj);
        if (indexOf >= 0) {
            remove(indexOf);
        }
        return indexOf >= 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean removeAll(Collection elements) {
        Intrinsics.e(elements, "elements");
        l();
        return t(this.offset, this.length, elements, false) > 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean retainAll(Collection elements) {
        Intrinsics.e(elements, "elements");
        l();
        return t(this.offset, this.length, elements, true) > 0;
    }

    @Override // java.util.AbstractList, java.util.List
    public Object set(int i2, Object obj) {
        l();
        AbstractList.Companion.b(i2, this.length);
        Object[] objArr = this.array;
        int i3 = this.offset;
        Object obj2 = objArr[i3 + i2];
        objArr[i3 + i2] = obj;
        return obj2;
    }

    @Override // java.util.AbstractList, java.util.List
    public List subList(int i2, int i3) {
        AbstractList.Companion.d(i2, i3, this.length);
        E[] eArr = this.array;
        int i4 = this.offset + i2;
        int i5 = i3 - i2;
        boolean z = this.isReadOnly;
        ListBuilder<E> listBuilder = this.root;
        return new ListBuilder(eArr, i4, i5, z, this, listBuilder == null ? this : listBuilder);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public Object[] toArray(Object[] destination) {
        Intrinsics.e(destination, "destination");
        int length = destination.length;
        int i2 = this.length;
        if (length < i2) {
            E[] eArr = this.array;
            int i3 = this.offset;
            Object[] copyOfRange = Arrays.copyOfRange(eArr, i3, i2 + i3, destination.getClass());
            Intrinsics.d(copyOfRange, "copyOfRange(array, offse…h, destination.javaClass)");
            return copyOfRange;
        }
        E[] eArr2 = this.array;
        int i4 = this.offset;
        ArraysKt___ArraysJvmKt.g(eArr2, destination, 0, i4, i2 + i4);
        int length2 = destination.length;
        int i5 = this.length;
        if (length2 > i5) {
            destination[i5] = null;
        }
        return destination;
    }

    @Override // java.util.AbstractCollection
    public String toString() {
        String j2;
        j2 = ListBuilderKt.j(this.array, this.offset, this.length);
        return j2;
    }

    @Override // java.util.AbstractList, java.util.List
    public ListIterator listIterator(int i2) {
        AbstractList.Companion.c(i2, this.length);
        return new Itr(this, i2);
    }

    @Override // java.util.AbstractList, java.util.List
    public void add(int i2, Object obj) {
        l();
        AbstractList.Companion.c(i2, this.length);
        j(this.offset + i2, obj);
    }

    @Override // java.util.AbstractList, java.util.List
    public boolean addAll(int i2, Collection elements) {
        Intrinsics.e(elements, "elements");
        l();
        AbstractList.Companion.c(i2, this.length);
        int size = elements.size();
        i(this.offset + i2, elements, size);
        return size > 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public Object[] toArray() {
        Object[] j2;
        E[] eArr = this.array;
        int i2 = this.offset;
        j2 = ArraysKt___ArraysJvmKt.j(eArr, i2, this.length + i2);
        return j2;
    }

    public ListBuilder() {
        this(10);
    }

    public ListBuilder(int i2) {
        this(ListBuilderKt.d(i2), 0, 0, false, null, null);
    }
}
