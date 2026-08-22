package androidx.arch.core.internal;

import androidx.annotation.RestrictTo;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

@RestrictTo
/* loaded from: classes.dex */
public class SafeIterableMap<K, V> implements Iterable<Map.Entry<K, V>> {

    /* renamed from: c, reason: collision with root package name */
    Entry f1110c;

    /* renamed from: h, reason: collision with root package name */
    private Entry f1111h;

    /* renamed from: i, reason: collision with root package name */
    private final WeakHashMap f1112i = new WeakHashMap();

    /* renamed from: j, reason: collision with root package name */
    private int f1113j = 0;

    static class AscendingIterator<K, V> extends ListIterator<K, V> {
        AscendingIterator(Entry entry, Entry entry2) {
            super(entry, entry2);
        }

        @Override // androidx.arch.core.internal.SafeIterableMap.ListIterator
        Entry c(Entry entry) {
            return entry.f1117j;
        }

        @Override // androidx.arch.core.internal.SafeIterableMap.ListIterator
        Entry d(Entry entry) {
            return entry.f1116i;
        }
    }

    private static class DescendingIterator<K, V> extends ListIterator<K, V> {
        DescendingIterator(Entry entry, Entry entry2) {
            super(entry, entry2);
        }

        @Override // androidx.arch.core.internal.SafeIterableMap.ListIterator
        Entry c(Entry entry) {
            return entry.f1116i;
        }

        @Override // androidx.arch.core.internal.SafeIterableMap.ListIterator
        Entry d(Entry entry) {
            return entry.f1117j;
        }
    }

    static class Entry<K, V> implements Map.Entry<K, V> {

        /* renamed from: c, reason: collision with root package name */
        final Object f1114c;

        /* renamed from: h, reason: collision with root package name */
        final Object f1115h;

        /* renamed from: i, reason: collision with root package name */
        Entry f1116i;

        /* renamed from: j, reason: collision with root package name */
        Entry f1117j;

        Entry(Object obj, Object obj2) {
            this.f1114c = obj;
            this.f1115h = obj2;
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) obj;
            return this.f1114c.equals(entry.f1114c) && this.f1115h.equals(entry.f1115h);
        }

        @Override // java.util.Map.Entry
        public Object getKey() {
            return this.f1114c;
        }

        @Override // java.util.Map.Entry
        public Object getValue() {
            return this.f1115h;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            return this.f1115h.hashCode() ^ this.f1114c.hashCode();
        }

        @Override // java.util.Map.Entry
        public Object setValue(Object obj) {
            throw new UnsupportedOperationException("An entry modification is not supported");
        }

        public String toString() {
            return this.f1114c + "=" + this.f1115h;
        }
    }

    @RestrictTo
    public class IteratorWithAdditions extends SupportRemove<K, V> implements Iterator<Map.Entry<K, V>> {

        /* renamed from: c, reason: collision with root package name */
        private Entry f1118c;

        /* renamed from: h, reason: collision with root package name */
        private boolean f1119h = true;

        IteratorWithAdditions() {
        }

        @Override // androidx.arch.core.internal.SafeIterableMap.SupportRemove
        void b(Entry entry) {
            Entry entry2 = this.f1118c;
            if (entry == entry2) {
                Entry entry3 = entry2.f1117j;
                this.f1118c = entry3;
                this.f1119h = entry3 == null;
            }
        }

        @Override // java.util.Iterator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public Map.Entry next() {
            if (this.f1119h) {
                this.f1119h = false;
                this.f1118c = SafeIterableMap.this.f1110c;
            } else {
                Entry entry = this.f1118c;
                this.f1118c = entry != null ? entry.f1116i : null;
            }
            return this.f1118c;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.f1119h) {
                return SafeIterableMap.this.f1110c != null;
            }
            Entry entry = this.f1118c;
            return (entry == null || entry.f1116i == null) ? false : true;
        }
    }

    private static abstract class ListIterator<K, V> extends SupportRemove<K, V> implements Iterator<Map.Entry<K, V>> {

        /* renamed from: c, reason: collision with root package name */
        Entry f1121c;

        /* renamed from: h, reason: collision with root package name */
        Entry f1122h;

        ListIterator(Entry entry, Entry entry2) {
            this.f1121c = entry2;
            this.f1122h = entry;
        }

        private Entry f() {
            Entry entry = this.f1122h;
            Entry entry2 = this.f1121c;
            if (entry == entry2 || entry2 == null) {
                return null;
            }
            return d(entry);
        }

        @Override // androidx.arch.core.internal.SafeIterableMap.SupportRemove
        public void b(Entry entry) {
            if (this.f1121c == entry && entry == this.f1122h) {
                this.f1122h = null;
                this.f1121c = null;
            }
            Entry entry2 = this.f1121c;
            if (entry2 == entry) {
                this.f1121c = c(entry2);
            }
            if (this.f1122h == entry) {
                this.f1122h = f();
            }
        }

        abstract Entry c(Entry entry);

        abstract Entry d(Entry entry);

        @Override // java.util.Iterator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public Map.Entry next() {
            Entry entry = this.f1122h;
            this.f1122h = f();
            return entry;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f1122h != null;
        }
    }

    @RestrictTo
    public static abstract class SupportRemove<K, V> {
        abstract void b(Entry entry);
    }

    public Map.Entry b() {
        return this.f1110c;
    }

    protected Entry d(Object obj) {
        Entry entry = this.f1110c;
        while (entry != null && !entry.f1114c.equals(obj)) {
            entry = entry.f1116i;
        }
        return entry;
    }

    public Iterator descendingIterator() {
        DescendingIterator descendingIterator = new DescendingIterator(this.f1111h, this.f1110c);
        this.f1112i.put(descendingIterator, Boolean.FALSE);
        return descendingIterator;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SafeIterableMap)) {
            return false;
        }
        SafeIterableMap safeIterableMap = (SafeIterableMap) obj;
        if (size() != safeIterableMap.size()) {
            return false;
        }
        Iterator it = iterator();
        Iterator it2 = safeIterableMap.iterator();
        while (it.hasNext() && it2.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Object next = it2.next();
            if ((entry == null && next != null) || (entry != null && !entry.equals(next))) {
                return false;
            }
        }
        return (it.hasNext() || it2.hasNext()) ? false : true;
    }

    public IteratorWithAdditions f() {
        IteratorWithAdditions iteratorWithAdditions = new IteratorWithAdditions();
        this.f1112i.put(iteratorWithAdditions, Boolean.FALSE);
        return iteratorWithAdditions;
    }

    public Map.Entry g() {
        return this.f1111h;
    }

    Entry h(Object obj, Object obj2) {
        Entry entry = new Entry(obj, obj2);
        this.f1113j++;
        Entry entry2 = this.f1111h;
        if (entry2 == null) {
            this.f1110c = entry;
            this.f1111h = entry;
            return entry;
        }
        entry2.f1116i = entry;
        entry.f1117j = entry2;
        this.f1111h = entry;
        return entry;
    }

    public int hashCode() {
        Iterator it = iterator();
        int i2 = 0;
        while (it.hasNext()) {
            i2 += ((Map.Entry) it.next()).hashCode();
        }
        return i2;
    }

    public Object i(Object obj, Object obj2) {
        Entry d2 = d(obj);
        if (d2 != null) {
            return d2.f1115h;
        }
        h(obj, obj2);
        return null;
    }

    @Override // java.lang.Iterable
    public Iterator iterator() {
        AscendingIterator ascendingIterator = new AscendingIterator(this.f1110c, this.f1111h);
        this.f1112i.put(ascendingIterator, Boolean.FALSE);
        return ascendingIterator;
    }

    public Object j(Object obj) {
        Entry d2 = d(obj);
        if (d2 == null) {
            return null;
        }
        this.f1113j--;
        if (!this.f1112i.isEmpty()) {
            Iterator<K> it = this.f1112i.keySet().iterator();
            while (it.hasNext()) {
                ((SupportRemove) it.next()).b(d2);
            }
        }
        Entry entry = d2.f1117j;
        if (entry != null) {
            entry.f1116i = d2.f1116i;
        } else {
            this.f1110c = d2.f1116i;
        }
        Entry entry2 = d2.f1116i;
        if (entry2 != null) {
            entry2.f1117j = entry;
        } else {
            this.f1111h = entry;
        }
        d2.f1116i = null;
        d2.f1117j = null;
        return d2.f1115h;
    }

    public int size() {
        return this.f1113j;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Iterator it = iterator();
        while (it.hasNext()) {
            sb.append(((Map.Entry) it.next()).toString());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
