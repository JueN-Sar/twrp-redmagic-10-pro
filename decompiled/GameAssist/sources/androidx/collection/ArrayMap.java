package androidx.collection;

import androidx.collection.internal.ContainerHelpersKt;
import java.lang.reflect.Array;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/* loaded from: classes.dex */
public class ArrayMap<K, V> extends SimpleArrayMap<K, V> implements Map<K, V> {

    /* renamed from: j, reason: collision with root package name */
    EntrySet f1170j;

    /* renamed from: k, reason: collision with root package name */
    KeySet f1171k;

    /* renamed from: l, reason: collision with root package name */
    ValueCollection f1172l;

    final class EntrySet extends AbstractSet<Map.Entry<K, V>> {
        EntrySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator iterator() {
            return new MapIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return ArrayMap.this.size();
        }
    }

    final class KeyIterator extends IndexBasedArrayIterator<K> {
        KeyIterator() {
            super(ArrayMap.this.size());
        }

        @Override // androidx.collection.IndexBasedArrayIterator
        protected Object b(int i2) {
            return ArrayMap.this.f(i2);
        }

        @Override // androidx.collection.IndexBasedArrayIterator
        protected void c(int i2) {
            ArrayMap.this.h(i2);
        }
    }

    final class MapIterator implements Iterator<Map.Entry<K, V>>, Map.Entry<K, V> {

        /* renamed from: c, reason: collision with root package name */
        int f1176c;

        /* renamed from: h, reason: collision with root package name */
        int f1177h = -1;

        /* renamed from: i, reason: collision with root package name */
        boolean f1178i;

        MapIterator() {
            this.f1176c = ArrayMap.this.size() - 1;
        }

        @Override // java.util.Iterator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public Map.Entry next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            this.f1177h++;
            this.f1178i = true;
            return this;
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            if (!this.f1178i) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            }
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            return ContainerHelpersKt.c(entry.getKey(), ArrayMap.this.f(this.f1177h)) && ContainerHelpersKt.c(entry.getValue(), ArrayMap.this.j(this.f1177h));
        }

        @Override // java.util.Map.Entry
        public Object getKey() {
            if (this.f1178i) {
                return ArrayMap.this.f(this.f1177h);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        @Override // java.util.Map.Entry
        public Object getValue() {
            if (this.f1178i) {
                return ArrayMap.this.j(this.f1177h);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f1177h < this.f1176c;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            if (!this.f1178i) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            }
            Object f2 = ArrayMap.this.f(this.f1177h);
            Object j2 = ArrayMap.this.j(this.f1177h);
            return (f2 == null ? 0 : f2.hashCode()) ^ (j2 != null ? j2.hashCode() : 0);
        }

        @Override // java.util.Iterator
        public void remove() {
            if (!this.f1178i) {
                throw new IllegalStateException();
            }
            ArrayMap.this.h(this.f1177h);
            this.f1177h--;
            this.f1176c--;
            this.f1178i = false;
        }

        @Override // java.util.Map.Entry
        public Object setValue(Object obj) {
            if (this.f1178i) {
                return ArrayMap.this.i(this.f1177h, obj);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        public String toString() {
            return getKey() + "=" + getValue();
        }
    }

    final class ValueIterator extends IndexBasedArrayIterator<V> {
        ValueIterator() {
            super(ArrayMap.this.size());
        }

        @Override // androidx.collection.IndexBasedArrayIterator
        protected Object b(int i2) {
            return ArrayMap.this.j(i2);
        }

        @Override // androidx.collection.IndexBasedArrayIterator
        protected void c(int i2) {
            ArrayMap.this.h(i2);
        }
    }

    public ArrayMap() {
    }

    static boolean l(Set set, Object obj) {
        if (set == obj) {
            return true;
        }
        if (obj instanceof Set) {
            Set set2 = (Set) obj;
            try {
                if (set.size() == set2.size()) {
                    if (set.containsAll(set2)) {
                        return true;
                    }
                }
                return false;
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    @Override // androidx.collection.SimpleArrayMap, java.util.Map
    public boolean containsKey(Object obj) {
        return super.containsKey(obj);
    }

    @Override // androidx.collection.SimpleArrayMap, java.util.Map
    public boolean containsValue(Object obj) {
        return super.containsValue(obj);
    }

    @Override // java.util.Map
    public Set entrySet() {
        EntrySet entrySet = this.f1170j;
        if (entrySet != null) {
            return entrySet;
        }
        EntrySet entrySet2 = new EntrySet();
        this.f1170j = entrySet2;
        return entrySet2;
    }

    @Override // androidx.collection.SimpleArrayMap, java.util.Map
    public Object get(Object obj) {
        return super.get(obj);
    }

    public boolean k(Collection collection) {
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (!containsKey(it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.Map
    public Set keySet() {
        KeySet keySet = this.f1171k;
        if (keySet != null) {
            return keySet;
        }
        KeySet keySet2 = new KeySet();
        this.f1171k = keySet2;
        return keySet2;
    }

    public boolean m(Collection collection) {
        int size = size();
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            remove(it.next());
        }
        return size != size();
    }

    public boolean n(Collection collection) {
        int size = size();
        for (int size2 = size() - 1; size2 >= 0; size2--) {
            if (!collection.contains(f(size2))) {
                h(size2);
            }
        }
        return size != size();
    }

    @Override // java.util.Map
    public void putAll(Map map) {
        b(size() + map.size());
        for (Map.Entry<K, V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // androidx.collection.SimpleArrayMap, java.util.Map
    public Object remove(Object obj) {
        return super.remove(obj);
    }

    @Override // java.util.Map
    public Collection values() {
        ValueCollection valueCollection = this.f1172l;
        if (valueCollection != null) {
            return valueCollection;
        }
        ValueCollection valueCollection2 = new ValueCollection();
        this.f1172l = valueCollection2;
        return valueCollection2;
    }

    public ArrayMap(int i2) {
        super(i2);
    }

    final class KeySet implements Set<K> {
        KeySet() {
        }

        @Override // java.util.Set, java.util.Collection
        public boolean add(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public boolean addAll(Collection collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public void clear() {
            ArrayMap.this.clear();
        }

        @Override // java.util.Set, java.util.Collection
        public boolean contains(Object obj) {
            return ArrayMap.this.containsKey(obj);
        }

        @Override // java.util.Set, java.util.Collection
        public boolean containsAll(Collection collection) {
            return ArrayMap.this.k(collection);
        }

        @Override // java.util.Set, java.util.Collection
        public boolean equals(Object obj) {
            return ArrayMap.l(this, obj);
        }

        @Override // java.util.Set, java.util.Collection
        public int hashCode() {
            int i2 = 0;
            for (int size = ArrayMap.this.size() - 1; size >= 0; size--) {
                Object f2 = ArrayMap.this.f(size);
                i2 += f2 == null ? 0 : f2.hashCode();
            }
            return i2;
        }

        @Override // java.util.Set, java.util.Collection
        public boolean isEmpty() {
            return ArrayMap.this.isEmpty();
        }

        @Override // java.util.Set, java.util.Collection, java.lang.Iterable
        public Iterator iterator() {
            return new KeyIterator();
        }

        @Override // java.util.Set, java.util.Collection
        public boolean remove(Object obj) {
            int d2 = ArrayMap.this.d(obj);
            if (d2 < 0) {
                return false;
            }
            ArrayMap.this.h(d2);
            return true;
        }

        @Override // java.util.Set, java.util.Collection
        public boolean removeAll(Collection collection) {
            return ArrayMap.this.m(collection);
        }

        @Override // java.util.Set, java.util.Collection
        public boolean retainAll(Collection collection) {
            return ArrayMap.this.n(collection);
        }

        @Override // java.util.Set, java.util.Collection
        public int size() {
            return ArrayMap.this.size();
        }

        @Override // java.util.Set, java.util.Collection
        public Object[] toArray() {
            int size = ArrayMap.this.size();
            Object[] objArr = new Object[size];
            for (int i2 = 0; i2 < size; i2++) {
                objArr[i2] = ArrayMap.this.f(i2);
            }
            return objArr;
        }

        @Override // java.util.Set, java.util.Collection
        public Object[] toArray(Object[] objArr) {
            int size = size();
            if (objArr.length < size) {
                objArr = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), size);
            }
            for (int i2 = 0; i2 < size; i2++) {
                objArr[i2] = ArrayMap.this.f(i2);
            }
            if (objArr.length > size) {
                objArr[size] = null;
            }
            return objArr;
        }
    }

    final class ValueCollection implements Collection<V> {
        ValueCollection() {
        }

        @Override // java.util.Collection
        public boolean add(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection
        public boolean addAll(Collection collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection
        public void clear() {
            ArrayMap.this.clear();
        }

        @Override // java.util.Collection
        public boolean contains(Object obj) {
            return ArrayMap.this.a(obj) >= 0;
        }

        @Override // java.util.Collection
        public boolean containsAll(Collection collection) {
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                if (!contains(it.next())) {
                    return false;
                }
            }
            return true;
        }

        @Override // java.util.Collection
        public boolean isEmpty() {
            return ArrayMap.this.isEmpty();
        }

        @Override // java.util.Collection, java.lang.Iterable
        public Iterator iterator() {
            return new ValueIterator();
        }

        @Override // java.util.Collection
        public boolean remove(Object obj) {
            int a2 = ArrayMap.this.a(obj);
            if (a2 < 0) {
                return false;
            }
            ArrayMap.this.h(a2);
            return true;
        }

        @Override // java.util.Collection
        public boolean removeAll(Collection collection) {
            int size = ArrayMap.this.size();
            int i2 = 0;
            boolean z = false;
            while (i2 < size) {
                if (collection.contains(ArrayMap.this.j(i2))) {
                    ArrayMap.this.h(i2);
                    i2--;
                    size--;
                    z = true;
                }
                i2++;
            }
            return z;
        }

        @Override // java.util.Collection
        public boolean retainAll(Collection collection) {
            int size = ArrayMap.this.size();
            int i2 = 0;
            boolean z = false;
            while (i2 < size) {
                if (!collection.contains(ArrayMap.this.j(i2))) {
                    ArrayMap.this.h(i2);
                    i2--;
                    size--;
                    z = true;
                }
                i2++;
            }
            return z;
        }

        @Override // java.util.Collection
        public int size() {
            return ArrayMap.this.size();
        }

        @Override // java.util.Collection
        public Object[] toArray() {
            int size = ArrayMap.this.size();
            Object[] objArr = new Object[size];
            for (int i2 = 0; i2 < size; i2++) {
                objArr[i2] = ArrayMap.this.j(i2);
            }
            return objArr;
        }

        @Override // java.util.Collection
        public Object[] toArray(Object[] objArr) {
            int size = size();
            if (objArr.length < size) {
                objArr = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), size);
            }
            for (int i2 = 0; i2 < size; i2++) {
                objArr[i2] = ArrayMap.this.j(i2);
            }
            if (objArr.length > size) {
                objArr[size] = null;
            }
            return objArr;
        }
    }

    public ArrayMap(SimpleArrayMap simpleArrayMap) {
        super(simpleArrayMap);
    }
}
