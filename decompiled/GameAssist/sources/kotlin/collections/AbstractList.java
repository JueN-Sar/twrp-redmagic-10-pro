package kotlin.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;

@SinceKotlin
@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public abstract class AbstractList<E> extends AbstractCollection<E> implements List<E>, KMappedMarker {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @Metadata
    public static final class Companion {
        private Companion() {
        }

        public final void a(int i2, int i3, int i4) {
            if (i2 < 0 || i3 > i4) {
                throw new IndexOutOfBoundsException("startIndex: " + i2 + ", endIndex: " + i3 + ", size: " + i4);
            }
            if (i2 <= i3) {
                return;
            }
            throw new IllegalArgumentException("startIndex: " + i2 + " > endIndex: " + i3);
        }

        public final void b(int i2, int i3) {
            if (i2 < 0 || i2 >= i3) {
                throw new IndexOutOfBoundsException("index: " + i2 + ", size: " + i3);
            }
        }

        public final void c(int i2, int i3) {
            if (i2 < 0 || i2 > i3) {
                throw new IndexOutOfBoundsException("index: " + i2 + ", size: " + i3);
            }
        }

        public final void d(int i2, int i3, int i4) {
            if (i2 < 0 || i3 > i4) {
                throw new IndexOutOfBoundsException("fromIndex: " + i2 + ", toIndex: " + i3 + ", size: " + i4);
            }
            if (i2 <= i3) {
                return;
            }
            throw new IllegalArgumentException("fromIndex: " + i2 + " > toIndex: " + i3);
        }

        public final boolean e(Collection c2, Collection other) {
            Intrinsics.e(c2, "c");
            Intrinsics.e(other, "other");
            if (c2.size() != other.size()) {
                return false;
            }
            Iterator<E> it = other.iterator();
            Iterator<E> it2 = c2.iterator();
            while (it2.hasNext()) {
                if (!Intrinsics.a(it2.next(), it.next())) {
                    return false;
                }
            }
            return true;
        }

        public final int f(Collection c2) {
            Intrinsics.e(c2, "c");
            Iterator<E> it = c2.iterator();
            int i2 = 1;
            while (it.hasNext()) {
                E next = it.next();
                i2 = (i2 * 31) + (next != null ? next.hashCode() : 0);
            }
            return i2;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata
    private class IteratorImpl implements Iterator<E>, KMappedMarker {

        /* renamed from: c, reason: collision with root package name */
        private int f18293c;

        public IteratorImpl() {
        }

        protected final int b() {
            return this.f18293c;
        }

        protected final void c(int i2) {
            this.f18293c = i2;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f18293c < AbstractList.this.size();
        }

        @Override // java.util.Iterator
        public Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            AbstractList abstractList = AbstractList.this;
            int i2 = this.f18293c;
            this.f18293c = i2 + 1;
            return abstractList.get(i2);
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    @Metadata
    private class ListIteratorImpl extends AbstractList<E>.IteratorImpl implements ListIterator<E>, KMappedMarker {
        public ListIteratorImpl(int i2) {
            super();
            AbstractList.Companion.c(i2, AbstractList.this.size());
            c(i2);
        }

        @Override // java.util.ListIterator
        public void add(Object obj) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.ListIterator
        public boolean hasPrevious() {
            return b() > 0;
        }

        @Override // java.util.ListIterator
        public int nextIndex() {
            return b();
        }

        @Override // java.util.ListIterator
        public Object previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            AbstractList abstractList = AbstractList.this;
            c(b() - 1);
            return abstractList.get(b());
        }

        @Override // java.util.ListIterator
        public int previousIndex() {
            return b() - 1;
        }

        @Override // java.util.ListIterator
        public void set(Object obj) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    @Metadata
    private static final class SubList<E> extends AbstractList<E> implements RandomAccess {

        /* renamed from: c, reason: collision with root package name */
        private final AbstractList f18296c;

        /* renamed from: h, reason: collision with root package name */
        private final int f18297h;

        /* renamed from: i, reason: collision with root package name */
        private int f18298i;

        public SubList(AbstractList list, int i2, int i3) {
            Intrinsics.e(list, "list");
            this.f18296c = list;
            this.f18297h = i2;
            AbstractList.Companion.d(i2, i3, list.size());
            this.f18298i = i3 - i2;
        }

        @Override // kotlin.collections.AbstractCollection
        public int b() {
            return this.f18298i;
        }

        @Override // kotlin.collections.AbstractList, java.util.List
        public Object get(int i2) {
            AbstractList.Companion.b(i2, this.f18298i);
            return this.f18296c.get(this.f18297h + i2);
        }
    }

    protected AbstractList() {
    }

    @Override // java.util.List
    public void add(int i2, Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public boolean addAll(int i2, Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection, java.util.List
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof List) {
            return Companion.e(this, (Collection) obj);
        }
        return false;
    }

    @Override // java.util.List
    public abstract Object get(int i2);

    @Override // java.util.Collection, java.util.List
    public int hashCode() {
        return Companion.f(this);
    }

    @Override // java.util.List
    public int indexOf(Object obj) {
        Iterator<E> it = iterator();
        int i2 = 0;
        while (it.hasNext()) {
            if (Intrinsics.a(it.next(), obj)) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    @Override // java.util.Collection, java.lang.Iterable, java.util.List
    public Iterator iterator() {
        return new IteratorImpl();
    }

    @Override // java.util.List
    public int lastIndexOf(Object obj) {
        ListIterator<E> listIterator = listIterator(size());
        while (listIterator.hasPrevious()) {
            if (Intrinsics.a(listIterator.previous(), obj)) {
                return listIterator.nextIndex();
            }
        }
        return -1;
    }

    @Override // java.util.List
    public ListIterator listIterator() {
        return new ListIteratorImpl(0);
    }

    @Override // java.util.List
    public Object remove(int i2) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public Object set(int i2, Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public List subList(int i2, int i3) {
        return new SubList(this, i2, i3);
    }

    @Override // java.util.List
    public ListIterator listIterator(int i2) {
        return new ListIteratorImpl(i2);
    }
}
