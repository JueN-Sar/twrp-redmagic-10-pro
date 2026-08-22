package com.airbnb.lottie.parser.moshi;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/* loaded from: classes.dex */
final class LinkedHashTreeMap<K, V> extends AbstractMap<K, V> implements Serializable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Comparator<Comparable> NATURAL_ORDER = new Comparator<Comparable>() { // from class: com.airbnb.lottie.parser.moshi.LinkedHashTreeMap.1
        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(Comparable comparable, Comparable comparable2) {
            return comparable.compareTo(comparable2);
        }
    };
    Comparator<? super K> comparator;
    private LinkedHashTreeMap<K, V>.EntrySet entrySet;
    final Node<K, V> header;
    private LinkedHashTreeMap<K, V>.KeySet keySet;
    int modCount;
    int size;
    Node<K, V>[] table;
    int threshold;

    static final class AvlBuilder<K, V> {

        /* renamed from: a, reason: collision with root package name */
        private Node f9878a;

        /* renamed from: b, reason: collision with root package name */
        private int f9879b;

        /* renamed from: c, reason: collision with root package name */
        private int f9880c;

        /* renamed from: d, reason: collision with root package name */
        private int f9881d;

        AvlBuilder() {
        }

        void a(Node node) {
            node.f9893i = null;
            node.f9891c = null;
            node.f9892h = null;
            node.f9899o = 1;
            int i2 = this.f9879b;
            if (i2 > 0) {
                int i3 = this.f9881d;
                if ((i3 & 1) == 0) {
                    this.f9881d = i3 + 1;
                    this.f9879b = i2 - 1;
                    this.f9880c++;
                }
            }
            node.f9891c = this.f9878a;
            this.f9878a = node;
            int i4 = this.f9881d;
            int i5 = i4 + 1;
            this.f9881d = i5;
            int i6 = this.f9879b;
            if (i6 > 0 && (i5 & 1) == 0) {
                this.f9881d = i4 + 2;
                this.f9879b = i6 - 1;
                this.f9880c++;
            }
            int i7 = 4;
            while (true) {
                int i8 = i7 - 1;
                if ((this.f9881d & i8) != i8) {
                    return;
                }
                int i9 = this.f9880c;
                if (i9 == 0) {
                    Node node2 = this.f9878a;
                    Node node3 = node2.f9891c;
                    Node node4 = node3.f9891c;
                    node3.f9891c = node4.f9891c;
                    this.f9878a = node3;
                    node3.f9892h = node4;
                    node3.f9893i = node2;
                    node3.f9899o = node2.f9899o + 1;
                    node4.f9891c = node3;
                    node2.f9891c = node3;
                } else if (i9 == 1) {
                    Node node5 = this.f9878a;
                    Node node6 = node5.f9891c;
                    this.f9878a = node6;
                    node6.f9893i = node5;
                    node6.f9899o = node5.f9899o + 1;
                    node5.f9891c = node6;
                    this.f9880c = 0;
                } else if (i9 == 2) {
                    this.f9880c = 0;
                }
                i7 *= 2;
            }
        }

        void b(int i2) {
            this.f9879b = ((Integer.highestOneBit(i2) * 2) - 1) - i2;
            this.f9881d = 0;
            this.f9880c = 0;
            this.f9878a = null;
        }

        Node c() {
            Node node = this.f9878a;
            if (node.f9891c == null) {
                return node;
            }
            throw new IllegalStateException();
        }
    }

    static class AvlIterator<K, V> {

        /* renamed from: a, reason: collision with root package name */
        private Node f9882a;

        AvlIterator() {
        }

        public Node a() {
            Node node = this.f9882a;
            if (node == null) {
                return null;
            }
            Node node2 = node.f9891c;
            node.f9891c = null;
            Node node3 = node.f9893i;
            while (true) {
                Node node4 = node2;
                node2 = node3;
                if (node2 == null) {
                    this.f9882a = node4;
                    return node;
                }
                node2.f9891c = node4;
                node3 = node2.f9892h;
            }
        }

        void b(Node node) {
            Node node2 = null;
            while (node != null) {
                node.f9891c = node2;
                node2 = node;
                node = node.f9892h;
            }
            this.f9882a = node2;
        }
    }

    final class EntrySet extends AbstractSet<Map.Entry<K, V>> {
        EntrySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            LinkedHashTreeMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return (obj instanceof Map.Entry) && LinkedHashTreeMap.this.e((Map.Entry) obj) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator iterator() {
            return new LinkedHashTreeMap<K, V>.LinkedTreeMapIterator<Map.Entry<K, V>>() { // from class: com.airbnb.lottie.parser.moshi.LinkedHashTreeMap.EntrySet.1
                {
                    LinkedHashTreeMap linkedHashTreeMap = LinkedHashTreeMap.this;
                }

                @Override // java.util.Iterator
                /* renamed from: c, reason: merged with bridge method [inline-methods] */
                public Map.Entry next() {
                    return b();
                }
            };
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            Node e2;
            if (!(obj instanceof Map.Entry) || (e2 = LinkedHashTreeMap.this.e((Map.Entry) obj)) == null) {
                return false;
            }
            LinkedHashTreeMap.this.h(e2, true);
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return LinkedHashTreeMap.this.size;
        }
    }

    final class KeySet extends AbstractSet<K> {
        KeySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            LinkedHashTreeMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return LinkedHashTreeMap.this.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator iterator() {
            return new LinkedHashTreeMap<K, V>.LinkedTreeMapIterator<K>() { // from class: com.airbnb.lottie.parser.moshi.LinkedHashTreeMap.KeySet.1
                {
                    LinkedHashTreeMap linkedHashTreeMap = LinkedHashTreeMap.this;
                }

                @Override // java.util.Iterator
                public Object next() {
                    return b().f9896l;
                }
            };
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            return LinkedHashTreeMap.this.i(obj) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return LinkedHashTreeMap.this.size;
        }
    }

    abstract class LinkedTreeMapIterator<T> implements Iterator<T> {

        /* renamed from: c, reason: collision with root package name */
        Node f9887c;

        /* renamed from: h, reason: collision with root package name */
        Node f9888h = null;

        /* renamed from: i, reason: collision with root package name */
        int f9889i;

        LinkedTreeMapIterator() {
            this.f9887c = LinkedHashTreeMap.this.header.f9894j;
            this.f9889i = LinkedHashTreeMap.this.modCount;
        }

        final Node b() {
            Node<K, V> node = this.f9887c;
            LinkedHashTreeMap linkedHashTreeMap = LinkedHashTreeMap.this;
            if (node == linkedHashTreeMap.header) {
                throw new NoSuchElementException();
            }
            if (linkedHashTreeMap.modCount != this.f9889i) {
                throw new ConcurrentModificationException();
            }
            this.f9887c = node.f9894j;
            this.f9888h = node;
            return node;
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return this.f9887c != LinkedHashTreeMap.this.header;
        }

        @Override // java.util.Iterator
        public final void remove() {
            Node node = this.f9888h;
            if (node == null) {
                throw new IllegalStateException();
            }
            LinkedHashTreeMap.this.h(node, true);
            this.f9888h = null;
            this.f9889i = LinkedHashTreeMap.this.modCount;
        }
    }

    LinkedHashTreeMap() {
        this(null);
    }

    private void a() {
        Node<K, V>[] b2 = b(this.table);
        this.table = b2;
        this.threshold = (b2.length / 2) + (b2.length / 4);
    }

    static Node[] b(Node[] nodeArr) {
        int length = nodeArr.length;
        Node[] nodeArr2 = new Node[length * 2];
        AvlIterator avlIterator = new AvlIterator();
        AvlBuilder avlBuilder = new AvlBuilder();
        AvlBuilder avlBuilder2 = new AvlBuilder();
        for (int i2 = 0; i2 < length; i2++) {
            Node node = nodeArr[i2];
            if (node != null) {
                avlIterator.b(node);
                int i3 = 0;
                int i4 = 0;
                while (true) {
                    Node a2 = avlIterator.a();
                    if (a2 == null) {
                        break;
                    }
                    if ((a2.f9897m & length) == 0) {
                        i3++;
                    } else {
                        i4++;
                    }
                }
                avlBuilder.b(i3);
                avlBuilder2.b(i4);
                avlIterator.b(node);
                while (true) {
                    Node a3 = avlIterator.a();
                    if (a3 == null) {
                        break;
                    }
                    if ((a3.f9897m & length) == 0) {
                        avlBuilder.a(a3);
                    } else {
                        avlBuilder2.a(a3);
                    }
                }
                nodeArr2[i2] = i3 > 0 ? avlBuilder.c() : null;
                nodeArr2[i2 + length] = i4 > 0 ? avlBuilder2.c() : null;
            }
        }
        return nodeArr2;
    }

    private boolean c(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private void g(Node node, boolean z) {
        while (node != null) {
            Node node2 = node.f9892h;
            Node node3 = node.f9893i;
            int i2 = node2 != null ? node2.f9899o : 0;
            int i3 = node3 != null ? node3.f9899o : 0;
            int i4 = i2 - i3;
            if (i4 == -2) {
                Node node4 = node3.f9892h;
                Node node5 = node3.f9893i;
                int i5 = (node4 != null ? node4.f9899o : 0) - (node5 != null ? node5.f9899o : 0);
                if (i5 == -1 || (i5 == 0 && !z)) {
                    k(node);
                } else {
                    l(node3);
                    k(node);
                }
                if (z) {
                    return;
                }
            } else if (i4 == 2) {
                Node node6 = node2.f9892h;
                Node node7 = node2.f9893i;
                int i6 = (node6 != null ? node6.f9899o : 0) - (node7 != null ? node7.f9899o : 0);
                if (i6 == 1 || (i6 == 0 && !z)) {
                    l(node);
                } else {
                    k(node2);
                    l(node);
                }
                if (z) {
                    return;
                }
            } else if (i4 == 0) {
                node.f9899o = i2 + 1;
                if (z) {
                    return;
                }
            } else {
                node.f9899o = Math.max(i2, i3) + 1;
                if (!z) {
                    return;
                }
            }
            node = node.f9891c;
        }
    }

    private void j(Node node, Node node2) {
        Node node3 = node.f9891c;
        node.f9891c = null;
        if (node2 != null) {
            node2.f9891c = node3;
        }
        if (node3 == null) {
            int i2 = node.f9897m;
            ((Node<K, V>[]) this.table)[i2 & (r2.length - 1)] = node2;
        } else if (node3.f9892h == node) {
            node3.f9892h = node2;
        } else {
            node3.f9893i = node2;
        }
    }

    private void k(Node node) {
        Node node2 = node.f9892h;
        Node node3 = node.f9893i;
        Node node4 = node3.f9892h;
        Node node5 = node3.f9893i;
        node.f9893i = node4;
        if (node4 != null) {
            node4.f9891c = node;
        }
        j(node, node3);
        node3.f9892h = node;
        node.f9891c = node3;
        int max = Math.max(node2 != null ? node2.f9899o : 0, node4 != null ? node4.f9899o : 0) + 1;
        node.f9899o = max;
        node3.f9899o = Math.max(max, node5 != null ? node5.f9899o : 0) + 1;
    }

    private void l(Node node) {
        Node node2 = node.f9892h;
        Node node3 = node.f9893i;
        Node node4 = node2.f9892h;
        Node node5 = node2.f9893i;
        node.f9892h = node5;
        if (node5 != null) {
            node5.f9891c = node;
        }
        j(node, node2);
        node2.f9893i = node;
        node.f9891c = node2;
        int max = Math.max(node3 != null ? node3.f9899o : 0, node5 != null ? node5.f9899o : 0) + 1;
        node.f9899o = max;
        node2.f9899o = Math.max(max, node4 != null ? node4.f9899o : 0) + 1;
    }

    private static int m(int i2) {
        int i3 = i2 ^ ((i2 >>> 20) ^ (i2 >>> 12));
        return (i3 >>> 4) ^ ((i3 >>> 7) ^ i3);
    }

    private Object writeReplace() {
        return new LinkedHashMap(this);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        Arrays.fill(this.table, (Object) null);
        this.size = 0;
        this.modCount++;
        Node<K, V> node = this.header;
        Node<K, V> node2 = node.f9894j;
        while (node2 != node) {
            Node<K, V> node3 = node2.f9894j;
            node2.f9895k = null;
            node2.f9894j = null;
            node2 = node3;
        }
        node.f9895k = node;
        node.f9894j = node;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        return f(obj) != null;
    }

    Node d(Object obj, boolean z) {
        int i2;
        Node<K, V> node;
        Comparator<? super K> comparator = this.comparator;
        Node<K, V>[] nodeArr = this.table;
        int m2 = m(obj.hashCode());
        int length = (nodeArr.length - 1) & m2;
        Node<K, V> node2 = nodeArr[length];
        if (node2 != null) {
            Comparable comparable = comparator == NATURAL_ORDER ? (Comparable) obj : null;
            while (true) {
                i2 = comparable != null ? comparable.compareTo(node2.f9896l) : comparator.compare(obj, (Object) node2.f9896l);
                if (i2 == 0) {
                    return node2;
                }
                Node<K, V> node3 = i2 < 0 ? node2.f9892h : node2.f9893i;
                if (node3 == null) {
                    break;
                }
                node2 = node3;
            }
        } else {
            i2 = 0;
        }
        Node<K, V> node4 = node2;
        int i3 = i2;
        if (!z) {
            return null;
        }
        Node<K, V> node5 = this.header;
        if (node4 != null) {
            node = new Node<>(node4, obj, m2, node5, node5.f9895k);
            if (i3 < 0) {
                node4.f9892h = node;
            } else {
                node4.f9893i = node;
            }
            g(node4, true);
        } else {
            if (comparator == NATURAL_ORDER && !(obj instanceof Comparable)) {
                throw new ClassCastException(obj.getClass().getName() + " is not Comparable");
            }
            node = new Node<>(node4, obj, m2, node5, node5.f9895k);
            nodeArr[length] = node;
        }
        int i4 = this.size;
        this.size = i4 + 1;
        if (i4 > this.threshold) {
            a();
        }
        this.modCount++;
        return node;
    }

    Node e(Map.Entry entry) {
        Node f2 = f(entry.getKey());
        if (f2 == null || !c(f2.f9898n, entry.getValue())) {
            return null;
        }
        return f2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set entrySet() {
        LinkedHashTreeMap<K, V>.EntrySet entrySet = this.entrySet;
        if (entrySet != null) {
            return entrySet;
        }
        LinkedHashTreeMap<K, V>.EntrySet entrySet2 = new EntrySet();
        this.entrySet = entrySet2;
        return entrySet2;
    }

    Node f(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return d(obj, false);
        } catch (ClassCastException unused) {
            return null;
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Object get(Object obj) {
        Node f2 = f(obj);
        if (f2 != null) {
            return f2.f9898n;
        }
        return null;
    }

    void h(Node node, boolean z) {
        int i2;
        if (z) {
            Node node2 = node.f9895k;
            node2.f9894j = node.f9894j;
            node.f9894j.f9895k = node2;
            node.f9895k = null;
            node.f9894j = null;
        }
        Node node3 = node.f9892h;
        Node node4 = node.f9893i;
        Node node5 = node.f9891c;
        int i3 = 0;
        if (node3 == null || node4 == null) {
            if (node3 != null) {
                j(node, node3);
                node.f9892h = null;
            } else if (node4 != null) {
                j(node, node4);
                node.f9893i = null;
            } else {
                j(node, null);
            }
            g(node5, false);
            this.size--;
            this.modCount++;
            return;
        }
        Node b2 = node3.f9899o > node4.f9899o ? node3.b() : node4.a();
        h(b2, false);
        Node node6 = node.f9892h;
        if (node6 != null) {
            i2 = node6.f9899o;
            b2.f9892h = node6;
            node6.f9891c = b2;
            node.f9892h = null;
        } else {
            i2 = 0;
        }
        Node node7 = node.f9893i;
        if (node7 != null) {
            i3 = node7.f9899o;
            b2.f9893i = node7;
            node7.f9891c = b2;
            node.f9893i = null;
        }
        b2.f9899o = Math.max(i2, i3) + 1;
        j(node, b2);
    }

    Node i(Object obj) {
        Node f2 = f(obj);
        if (f2 != null) {
            h(f2, true);
        }
        return f2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set keySet() {
        LinkedHashTreeMap<K, V>.KeySet keySet = this.keySet;
        if (keySet != null) {
            return keySet;
        }
        LinkedHashTreeMap<K, V>.KeySet keySet2 = new KeySet();
        this.keySet = keySet2;
        return keySet2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Object put(Object obj, Object obj2) {
        if (obj == null) {
            throw new NullPointerException("key == null");
        }
        Node d2 = d(obj, true);
        Object obj3 = d2.f9898n;
        d2.f9898n = obj2;
        return obj3;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Object remove(Object obj) {
        Node i2 = i(obj);
        if (i2 != null) {
            return i2.f9898n;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this.size;
    }

    LinkedHashTreeMap(Comparator<? super K> comparator) {
        this.size = 0;
        this.modCount = 0;
        this.comparator = comparator == null ? NATURAL_ORDER : comparator;
        this.header = new Node<>();
        Node<K, V>[] nodeArr = new Node[16];
        this.table = nodeArr;
        this.threshold = (nodeArr.length / 2) + (nodeArr.length / 4);
    }

    static final class Node<K, V> implements Map.Entry<K, V> {

        /* renamed from: c, reason: collision with root package name */
        Node f9891c;

        /* renamed from: h, reason: collision with root package name */
        Node f9892h;

        /* renamed from: i, reason: collision with root package name */
        Node f9893i;

        /* renamed from: j, reason: collision with root package name */
        Node f9894j;

        /* renamed from: k, reason: collision with root package name */
        Node f9895k;

        /* renamed from: l, reason: collision with root package name */
        final Object f9896l;

        /* renamed from: m, reason: collision with root package name */
        final int f9897m;

        /* renamed from: n, reason: collision with root package name */
        Object f9898n;

        /* renamed from: o, reason: collision with root package name */
        int f9899o;

        Node() {
            this.f9896l = null;
            this.f9897m = -1;
            this.f9895k = this;
            this.f9894j = this;
        }

        public Node a() {
            Node<K, V> node = this.f9892h;
            while (true) {
                Node<K, V> node2 = node;
                Node<K, V> node3 = this;
                this = node2;
                if (this == null) {
                    return node3;
                }
                node = this.f9892h;
            }
        }

        public Node b() {
            Node<K, V> node = this.f9893i;
            while (true) {
                Node<K, V> node2 = node;
                Node<K, V> node3 = this;
                this = node2;
                if (this == null) {
                    return node3;
                }
                node = this.f9893i;
            }
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Object obj2 = this.f9896l;
            if (obj2 == null) {
                if (entry.getKey() != null) {
                    return false;
                }
            } else if (!obj2.equals(entry.getKey())) {
                return false;
            }
            Object obj3 = this.f9898n;
            if (obj3 == null) {
                if (entry.getValue() != null) {
                    return false;
                }
            } else if (!obj3.equals(entry.getValue())) {
                return false;
            }
            return true;
        }

        @Override // java.util.Map.Entry
        public Object getKey() {
            return this.f9896l;
        }

        @Override // java.util.Map.Entry
        public Object getValue() {
            return this.f9898n;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            Object obj = this.f9896l;
            int hashCode = obj == null ? 0 : obj.hashCode();
            Object obj2 = this.f9898n;
            return hashCode ^ (obj2 != null ? obj2.hashCode() : 0);
        }

        @Override // java.util.Map.Entry
        public Object setValue(Object obj) {
            Object obj2 = this.f9898n;
            this.f9898n = obj;
            return obj2;
        }

        public String toString() {
            return this.f9896l + "=" + this.f9898n;
        }

        Node(Node node, Object obj, int i2, Node node2, Node node3) {
            this.f9891c = node;
            this.f9896l = obj;
            this.f9897m = i2;
            this.f9899o = 1;
            this.f9894j = node2;
            this.f9895k = node3;
            node3.f9894j = this;
            node2.f9895k = this;
        }
    }
}
