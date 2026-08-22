package androidx.collection;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMutableList;
import kotlin.jvm.internal.markers.KMutableListIterator;

@Metadata
@SourceDebugExtension
/* loaded from: classes.dex */
public final class MutableObjectList<E> extends ObjectList<E> {

    @Metadata
    private static final class MutableObjectListIterator<T> implements ListIterator<T>, KMutableListIterator {

        /* renamed from: c, reason: collision with root package name */
        private final List f1336c;

        /* renamed from: h, reason: collision with root package name */
        private int f1337h;

        public MutableObjectListIterator(List list, int i2) {
            Intrinsics.e(list, "list");
            this.f1336c = list;
            this.f1337h = i2 - 1;
        }

        @Override // java.util.ListIterator
        public void add(Object obj) {
            List list = this.f1336c;
            int i2 = this.f1337h + 1;
            this.f1337h = i2;
            list.add(i2, obj);
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public boolean hasNext() {
            return this.f1337h < this.f1336c.size() - 1;
        }

        @Override // java.util.ListIterator
        public boolean hasPrevious() {
            return this.f1337h >= 0;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public Object next() {
            List list = this.f1336c;
            int i2 = this.f1337h + 1;
            this.f1337h = i2;
            return list.get(i2);
        }

        @Override // java.util.ListIterator
        public int nextIndex() {
            return this.f1337h + 1;
        }

        @Override // java.util.ListIterator
        public Object previous() {
            List list = this.f1336c;
            int i2 = this.f1337h;
            this.f1337h = i2 - 1;
            return list.get(i2);
        }

        @Override // java.util.ListIterator
        public int previousIndex() {
            return this.f1337h;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public void remove() {
            this.f1336c.remove(this.f1337h);
            this.f1337h--;
        }

        @Override // java.util.ListIterator
        public void set(Object obj) {
            this.f1336c.set(this.f1337h, obj);
        }
    }

    @Metadata
    private static final class ObjectListMutableList<T> implements List<T>, KMutableList {

        /* renamed from: c, reason: collision with root package name */
        private final MutableObjectList f1338c;

        @Override // java.util.List, java.util.Collection
        public boolean add(Object obj) {
            return this.f1338c.k(obj);
        }

        @Override // java.util.List
        public boolean addAll(int i2, Collection elements) {
            Intrinsics.e(elements, "elements");
            return this.f1338c.l(i2, elements);
        }

        public int b() {
            return this.f1338c.d();
        }

        @Override // java.util.List, java.util.Collection
        public void clear() {
            this.f1338c.n();
        }

        @Override // java.util.List, java.util.Collection
        public boolean contains(Object obj) {
            return this.f1338c.a(obj);
        }

        @Override // java.util.List, java.util.Collection
        public boolean containsAll(Collection elements) {
            Intrinsics.e(elements, "elements");
            return this.f1338c.b(elements);
        }

        public Object d(int i2) {
            ObjectListKt.d(this, i2);
            return this.f1338c.t(i2);
        }

        @Override // java.util.List
        public Object get(int i2) {
            ObjectListKt.d(this, i2);
            return this.f1338c.c(i2);
        }

        @Override // java.util.List
        public int indexOf(Object obj) {
            return this.f1338c.e(obj);
        }

        @Override // java.util.List, java.util.Collection
        public boolean isEmpty() {
            return this.f1338c.f();
        }

        @Override // java.util.List, java.util.Collection, java.lang.Iterable
        public Iterator iterator() {
            return new MutableObjectListIterator(this, 0);
        }

        @Override // java.util.List
        public int lastIndexOf(Object obj) {
            return this.f1338c.i(obj);
        }

        @Override // java.util.List
        public ListIterator listIterator() {
            return new MutableObjectListIterator(this, 0);
        }

        @Override // java.util.List
        public final /* bridge */ Object remove(int i2) {
            return d(i2);
        }

        @Override // java.util.List, java.util.Collection
        public boolean removeAll(Collection elements) {
            Intrinsics.e(elements, "elements");
            return this.f1338c.s(elements);
        }

        @Override // java.util.List, java.util.Collection
        public boolean retainAll(Collection elements) {
            Intrinsics.e(elements, "elements");
            return this.f1338c.u(elements);
        }

        @Override // java.util.List
        public Object set(int i2, Object obj) {
            ObjectListKt.d(this, i2);
            return this.f1338c.v(i2, obj);
        }

        @Override // java.util.List, java.util.Collection
        public final /* bridge */ int size() {
            return b();
        }

        @Override // java.util.List
        public List subList(int i2, int i3) {
            ObjectListKt.e(this, i2, i3);
            return new SubList(this, i2, i3);
        }

        @Override // java.util.List, java.util.Collection
        public Object[] toArray() {
            return CollectionToArray.a(this);
        }

        @Override // java.util.List
        public void add(int i2, Object obj) {
            this.f1338c.j(i2, obj);
        }

        @Override // java.util.List, java.util.Collection
        public boolean addAll(Collection elements) {
            Intrinsics.e(elements, "elements");
            return this.f1338c.m(elements);
        }

        @Override // java.util.List
        public ListIterator listIterator(int i2) {
            return new MutableObjectListIterator(this, i2);
        }

        @Override // java.util.List, java.util.Collection
        public boolean remove(Object obj) {
            return this.f1338c.r(obj);
        }

        @Override // java.util.List, java.util.Collection
        public Object[] toArray(Object[] array) {
            Intrinsics.e(array, "array");
            return CollectionToArray.b(this, array);
        }
    }

    @Metadata
    @SourceDebugExtension
    private static final class SubList<T> implements List<T>, KMutableList {

        /* renamed from: c, reason: collision with root package name */
        private final List f1339c;

        /* renamed from: h, reason: collision with root package name */
        private final int f1340h;

        /* renamed from: i, reason: collision with root package name */
        private int f1341i;

        public SubList(List list, int i2, int i3) {
            Intrinsics.e(list, "list");
            this.f1339c = list;
            this.f1340h = i2;
            this.f1341i = i3;
        }

        @Override // java.util.List, java.util.Collection
        public boolean add(Object obj) {
            List list = this.f1339c;
            int i2 = this.f1341i;
            this.f1341i = i2 + 1;
            list.add(i2, obj);
            return true;
        }

        @Override // java.util.List
        public boolean addAll(int i2, Collection elements) {
            Intrinsics.e(elements, "elements");
            this.f1339c.addAll(i2 + this.f1340h, elements);
            this.f1341i += elements.size();
            return elements.size() > 0;
        }

        public int b() {
            return this.f1341i - this.f1340h;
        }

        @Override // java.util.List, java.util.Collection
        public void clear() {
            int i2 = this.f1341i - 1;
            int i3 = this.f1340h;
            if (i3 <= i2) {
                while (true) {
                    this.f1339c.remove(i2);
                    if (i2 == i3) {
                        break;
                    } else {
                        i2--;
                    }
                }
            }
            this.f1341i = this.f1340h;
        }

        @Override // java.util.List, java.util.Collection
        public boolean contains(Object obj) {
            int i2 = this.f1341i;
            for (int i3 = this.f1340h; i3 < i2; i3++) {
                if (Intrinsics.a(this.f1339c.get(i3), obj)) {
                    return true;
                }
            }
            return false;
        }

        @Override // java.util.List, java.util.Collection
        public boolean containsAll(Collection elements) {
            Intrinsics.e(elements, "elements");
            Iterator<T> it = elements.iterator();
            while (it.hasNext()) {
                if (!contains(it.next())) {
                    return false;
                }
            }
            return true;
        }

        public Object d(int i2) {
            ObjectListKt.d(this, i2);
            this.f1341i--;
            return this.f1339c.remove(i2 + this.f1340h);
        }

        @Override // java.util.List
        public Object get(int i2) {
            ObjectListKt.d(this, i2);
            return this.f1339c.get(i2 + this.f1340h);
        }

        @Override // java.util.List
        public int indexOf(Object obj) {
            int i2 = this.f1341i;
            for (int i3 = this.f1340h; i3 < i2; i3++) {
                if (Intrinsics.a(this.f1339c.get(i3), obj)) {
                    return i3 - this.f1340h;
                }
            }
            return -1;
        }

        @Override // java.util.List, java.util.Collection
        public boolean isEmpty() {
            return this.f1341i == this.f1340h;
        }

        @Override // java.util.List, java.util.Collection, java.lang.Iterable
        public Iterator iterator() {
            return new MutableObjectListIterator(this, 0);
        }

        @Override // java.util.List
        public int lastIndexOf(Object obj) {
            int i2 = this.f1341i - 1;
            int i3 = this.f1340h;
            if (i3 > i2) {
                return -1;
            }
            while (!Intrinsics.a(this.f1339c.get(i2), obj)) {
                if (i2 == i3) {
                    return -1;
                }
                i2--;
            }
            return i2 - this.f1340h;
        }

        @Override // java.util.List
        public ListIterator listIterator() {
            return new MutableObjectListIterator(this, 0);
        }

        @Override // java.util.List
        public final /* bridge */ Object remove(int i2) {
            return d(i2);
        }

        @Override // java.util.List, java.util.Collection
        public boolean removeAll(Collection elements) {
            Intrinsics.e(elements, "elements");
            int i2 = this.f1341i;
            Iterator<T> it = elements.iterator();
            while (it.hasNext()) {
                remove(it.next());
            }
            return i2 != this.f1341i;
        }

        @Override // java.util.List, java.util.Collection
        public boolean retainAll(Collection elements) {
            Intrinsics.e(elements, "elements");
            int i2 = this.f1341i;
            int i3 = i2 - 1;
            int i4 = this.f1340h;
            if (i4 <= i3) {
                while (true) {
                    if (!elements.contains(this.f1339c.get(i3))) {
                        this.f1339c.remove(i3);
                        this.f1341i--;
                    }
                    if (i3 == i4) {
                        break;
                    }
                    i3--;
                }
            }
            return i2 != this.f1341i;
        }

        @Override // java.util.List
        public Object set(int i2, Object obj) {
            ObjectListKt.d(this, i2);
            return this.f1339c.set(i2 + this.f1340h, obj);
        }

        @Override // java.util.List, java.util.Collection
        public final /* bridge */ int size() {
            return b();
        }

        @Override // java.util.List
        public List subList(int i2, int i3) {
            ObjectListKt.e(this, i2, i3);
            return new SubList(this, i2, i3);
        }

        @Override // java.util.List, java.util.Collection
        public Object[] toArray() {
            return CollectionToArray.a(this);
        }

        @Override // java.util.List
        public void add(int i2, Object obj) {
            this.f1339c.add(i2 + this.f1340h, obj);
            this.f1341i++;
        }

        @Override // java.util.List
        public ListIterator listIterator(int i2) {
            return new MutableObjectListIterator(this, i2);
        }

        @Override // java.util.List, java.util.Collection
        public boolean remove(Object obj) {
            int i2 = this.f1341i;
            for (int i3 = this.f1340h; i3 < i2; i3++) {
                if (Intrinsics.a(this.f1339c.get(i3), obj)) {
                    this.f1339c.remove(i3);
                    this.f1341i--;
                    return true;
                }
            }
            return false;
        }

        @Override // java.util.List, java.util.Collection
        public Object[] toArray(Object[] array) {
            Intrinsics.e(array, "array");
            return CollectionToArray.b(this, array);
        }

        @Override // java.util.List, java.util.Collection
        public boolean addAll(Collection elements) {
            Intrinsics.e(elements, "elements");
            this.f1339c.addAll(this.f1341i, elements);
            this.f1341i += elements.size();
            return elements.size() > 0;
        }
    }

    public MutableObjectList(int i2) {
        super(i2, null);
    }

    public final void j(int i2, Object obj) {
        int i3;
        if (i2 < 0 || i2 > (i3 = this.f1375b)) {
            throw new IndexOutOfBoundsException("Index " + i2 + " must be in 0.." + this.f1375b);
        }
        o(i3 + 1);
        Object[] objArr = this.f1374a;
        int i4 = this.f1375b;
        if (i2 != i4) {
            ArraysKt___ArraysJvmKt.g(objArr, objArr, i2 + 1, i2, i4);
        }
        objArr[i2] = obj;
        this.f1375b++;
    }

    public final boolean k(Object obj) {
        o(this.f1375b + 1);
        Object[] objArr = this.f1374a;
        int i2 = this.f1375b;
        objArr[i2] = obj;
        this.f1375b = i2 + 1;
        return true;
    }

    public final boolean l(int i2, Collection elements) {
        Intrinsics.e(elements, "elements");
        if (i2 < 0 || i2 > this.f1375b) {
            throw new IndexOutOfBoundsException("Index " + i2 + " must be in 0.." + this.f1375b);
        }
        int i3 = 0;
        if (elements.isEmpty()) {
            return false;
        }
        o(this.f1375b + elements.size());
        Object[] objArr = this.f1374a;
        if (i2 != this.f1375b) {
            ArraysKt___ArraysJvmKt.g(objArr, objArr, elements.size() + i2, i2, this.f1375b);
        }
        for (Object obj : elements) {
            int i4 = i3 + 1;
            if (i3 < 0) {
                CollectionsKt__CollectionsKt.m();
            }
            objArr[i3 + i2] = obj;
            i3 = i4;
        }
        this.f1375b += elements.size();
        return true;
    }

    public final boolean m(Iterable elements) {
        Intrinsics.e(elements, "elements");
        int i2 = this.f1375b;
        q(elements);
        return i2 != this.f1375b;
    }

    public final void n() {
        ArraysKt___ArraysJvmKt.m(this.f1374a, null, 0, this.f1375b);
        this.f1375b = 0;
    }

    public final void o(int i2) {
        Object[] objArr = this.f1374a;
        if (objArr.length < i2) {
            Object[] copyOf = Arrays.copyOf(objArr, Math.max(i2, (objArr.length * 3) / 2));
            Intrinsics.d(copyOf, "copyOf(this, newSize)");
            this.f1374a = copyOf;
        }
    }

    public final void p(Iterable elements) {
        Intrinsics.e(elements, "elements");
        Iterator it = elements.iterator();
        while (it.hasNext()) {
            r(it.next());
        }
    }

    public final void q(Iterable elements) {
        Intrinsics.e(elements, "elements");
        Iterator it = elements.iterator();
        while (it.hasNext()) {
            k(it.next());
        }
    }

    public final boolean r(Object obj) {
        int e2 = e(obj);
        if (e2 < 0) {
            return false;
        }
        t(e2);
        return true;
    }

    public final boolean s(Iterable elements) {
        Intrinsics.e(elements, "elements");
        int i2 = this.f1375b;
        p(elements);
        return i2 != this.f1375b;
    }

    public final Object t(int i2) {
        int i3;
        if (i2 < 0 || i2 >= (i3 = this.f1375b)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Index ");
            sb.append(i2);
            sb.append(" must be in 0..");
            sb.append(this.f1375b - 1);
            throw new IndexOutOfBoundsException(sb.toString());
        }
        Object[] objArr = this.f1374a;
        Object obj = objArr[i2];
        if (i2 != i3 - 1) {
            ArraysKt___ArraysJvmKt.g(objArr, objArr, i2, i2 + 1, i3);
        }
        int i4 = this.f1375b - 1;
        this.f1375b = i4;
        objArr[i4] = null;
        return obj;
    }

    public final boolean u(Collection elements) {
        Intrinsics.e(elements, "elements");
        int i2 = this.f1375b;
        Object[] objArr = this.f1374a;
        for (int i3 = i2 - 1; -1 < i3; i3--) {
            if (!elements.contains(objArr[i3])) {
                t(i3);
            }
        }
        return i2 != this.f1375b;
    }

    public final Object v(int i2, Object obj) {
        if (i2 >= 0 && i2 < this.f1375b) {
            Object[] objArr = this.f1374a;
            Object obj2 = objArr[i2];
            objArr[i2] = obj;
            return obj2;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("set index ");
        sb.append(i2);
        sb.append(" must be between 0 .. ");
        sb.append(this.f1375b - 1);
        throw new IndexOutOfBoundsException(sb.toString());
    }
}
