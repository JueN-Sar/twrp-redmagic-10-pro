package kotlin.collections.builders;

import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMutableIterator;
import kotlin.jvm.internal.markers.KMutableMap;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt___RangesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata
/* loaded from: classes2.dex */
public final class MapBuilder<K, V> implements Map<K, V>, Serializable, KMutableMap {

    @NotNull
    private static final Companion Companion = new Companion(null);
    private static final int INITIAL_CAPACITY = 8;
    private static final int INITIAL_MAX_PROBE_DISTANCE = 2;
    private static final int MAGIC = -1640531527;
    private static final int TOMBSTONE = -1;

    @Nullable
    private MapBuilderEntries<K, V> entriesView;

    @NotNull
    private int[] hashArray;
    private int hashShift;
    private boolean isReadOnly;

    @NotNull
    private K[] keysArray;

    @Nullable
    private MapBuilderKeys<K> keysView;
    private int length;
    private int maxProbeDistance;

    @NotNull
    private int[] presenceArray;
    private int size;

    @Nullable
    private V[] valuesArray;

    @Nullable
    private MapBuilderValues<V> valuesView;

    @Metadata
    private static final class Companion {
        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final int c(int i2) {
            int a2;
            a2 = RangesKt___RangesKt.a(i2, 1);
            return Integer.highestOneBit(a2 * 3);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final int d(int i2) {
            return Integer.numberOfLeadingZeros(i2) + 1;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata
    public static final class EntriesItr<K, V> extends Itr<K, V> implements Iterator<Map.Entry<K, V>>, KMutableIterator {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public EntriesItr(MapBuilder map) {
            super(map);
            Intrinsics.e(map, "map");
        }

        @Override // java.util.Iterator
        /* renamed from: h, reason: merged with bridge method [inline-methods] */
        public EntryRef next() {
            if (b() >= d().length) {
                throw new NoSuchElementException();
            }
            int b2 = b();
            f(b2 + 1);
            g(b2);
            EntryRef entryRef = new EntryRef(d(), c());
            e();
            return entryRef;
        }

        public final void i(StringBuilder sb) {
            Intrinsics.e(sb, "sb");
            if (b() >= d().length) {
                throw new NoSuchElementException();
            }
            int b2 = b();
            f(b2 + 1);
            g(b2);
            Object obj = d().keysArray[c()];
            if (Intrinsics.a(obj, d())) {
                sb.append("(this Map)");
            } else {
                sb.append(obj);
            }
            sb.append('=');
            Object[] objArr = d().valuesArray;
            Intrinsics.b(objArr);
            Object obj2 = objArr[c()];
            if (Intrinsics.a(obj2, d())) {
                sb.append("(this Map)");
            } else {
                sb.append(obj2);
            }
            e();
        }

        public final int j() {
            if (b() >= d().length) {
                throw new NoSuchElementException();
            }
            int b2 = b();
            f(b2 + 1);
            g(b2);
            Object obj = d().keysArray[c()];
            int hashCode = obj != null ? obj.hashCode() : 0;
            Object[] objArr = d().valuesArray;
            Intrinsics.b(objArr);
            Object obj2 = objArr[c()];
            int hashCode2 = hashCode ^ (obj2 != null ? obj2.hashCode() : 0);
            e();
            return hashCode2;
        }
    }

    @Metadata
    public static final class EntryRef<K, V> implements Map.Entry<K, V>, KMutableMap.Entry {

        /* renamed from: c, reason: collision with root package name */
        private final MapBuilder f18371c;

        /* renamed from: h, reason: collision with root package name */
        private final int f18372h;

        public EntryRef(MapBuilder map, int i2) {
            Intrinsics.e(map, "map");
            this.f18371c = map;
            this.f18372h = i2;
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                if (Intrinsics.a(entry.getKey(), getKey()) && Intrinsics.a(entry.getValue(), getValue())) {
                    return true;
                }
            }
            return false;
        }

        @Override // java.util.Map.Entry
        public Object getKey() {
            return this.f18371c.keysArray[this.f18372h];
        }

        @Override // java.util.Map.Entry
        public Object getValue() {
            Object[] objArr = this.f18371c.valuesArray;
            Intrinsics.b(objArr);
            return objArr[this.f18372h];
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            Object key = getKey();
            int hashCode = key != null ? key.hashCode() : 0;
            Object value = getValue();
            return hashCode ^ (value != null ? value.hashCode() : 0);
        }

        @Override // java.util.Map.Entry
        public Object setValue(Object obj) {
            this.f18371c.j();
            Object[] h2 = this.f18371c.h();
            int i2 = this.f18372h;
            Object obj2 = h2[i2];
            h2[i2] = obj;
            return obj2;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(getKey());
            sb.append('=');
            sb.append(getValue());
            return sb.toString();
        }
    }

    @Metadata
    @SourceDebugExtension
    public static class Itr<K, V> {

        /* renamed from: c, reason: collision with root package name */
        private final MapBuilder f18373c;

        /* renamed from: h, reason: collision with root package name */
        private int f18374h;

        /* renamed from: i, reason: collision with root package name */
        private int f18375i;

        public Itr(MapBuilder map) {
            Intrinsics.e(map, "map");
            this.f18373c = map;
            this.f18375i = -1;
            e();
        }

        public final int b() {
            return this.f18374h;
        }

        public final int c() {
            return this.f18375i;
        }

        public final MapBuilder d() {
            return this.f18373c;
        }

        public final void e() {
            while (this.f18374h < this.f18373c.length) {
                int[] iArr = this.f18373c.presenceArray;
                int i2 = this.f18374h;
                if (iArr[i2] >= 0) {
                    return;
                } else {
                    this.f18374h = i2 + 1;
                }
            }
        }

        public final void f(int i2) {
            this.f18374h = i2;
        }

        public final void g(int i2) {
            this.f18375i = i2;
        }

        public final boolean hasNext() {
            return this.f18374h < this.f18373c.length;
        }

        public final void remove() {
            if (this.f18375i == -1) {
                throw new IllegalStateException("Call next() before removing element from the iterator.".toString());
            }
            this.f18373c.j();
            this.f18373c.J(this.f18375i);
            this.f18375i = -1;
        }
    }

    @Metadata
    public static final class KeysItr<K, V> extends Itr<K, V> implements Iterator<K>, KMutableIterator {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public KeysItr(MapBuilder map) {
            super(map);
            Intrinsics.e(map, "map");
        }

        @Override // java.util.Iterator
        public Object next() {
            if (b() >= d().length) {
                throw new NoSuchElementException();
            }
            int b2 = b();
            f(b2 + 1);
            g(b2);
            Object obj = d().keysArray[c()];
            e();
            return obj;
        }
    }

    @Metadata
    public static final class ValuesItr<K, V> extends Itr<K, V> implements Iterator<V>, KMutableIterator {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ValuesItr(MapBuilder map) {
            super(map);
            Intrinsics.e(map, "map");
        }

        @Override // java.util.Iterator
        public Object next() {
            if (b() >= d().length) {
                throw new NoSuchElementException();
            }
            int b2 = b();
            f(b2 + 1);
            g(b2);
            Object[] objArr = d().valuesArray;
            Intrinsics.b(objArr);
            Object obj = objArr[c()];
            e();
            return obj;
        }
    }

    private MapBuilder(K[] kArr, V[] vArr, int[] iArr, int[] iArr2, int i2, int i3) {
        this.keysArray = kArr;
        this.valuesArray = vArr;
        this.presenceArray = iArr;
        this.hashArray = iArr2;
        this.maxProbeDistance = i2;
        this.length = i3;
        this.hashShift = Companion.d(v());
    }

    private final boolean C(Collection collection) {
        boolean z = false;
        if (collection.isEmpty()) {
            return false;
        }
        p(collection.size());
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (D((Map.Entry) it.next())) {
                z = true;
            }
        }
        return z;
    }

    private final boolean D(Map.Entry entry) {
        int g2 = g(entry.getKey());
        Object[] h2 = h();
        if (g2 >= 0) {
            h2[g2] = entry.getValue();
            return true;
        }
        int i2 = (-g2) - 1;
        if (Intrinsics.a(entry.getValue(), h2[i2])) {
            return false;
        }
        h2[i2] = entry.getValue();
        return true;
    }

    private final boolean E(int i2) {
        int z = z(this.keysArray[i2]);
        int i3 = this.maxProbeDistance;
        while (true) {
            int[] iArr = this.hashArray;
            if (iArr[z] == 0) {
                iArr[z] = i2 + 1;
                this.presenceArray[i2] = z;
                return true;
            }
            i3--;
            if (i3 < 0) {
                return false;
            }
            z = z == 0 ? v() - 1 : z - 1;
        }
    }

    private final void F(int i2) {
        if (this.length > size()) {
            k();
        }
        int i3 = 0;
        if (i2 != v()) {
            this.hashArray = new int[i2];
            this.hashShift = Companion.d(i2);
        } else {
            ArraysKt___ArraysJvmKt.k(this.hashArray, 0, 0, v());
        }
        while (i3 < this.length) {
            int i4 = i3 + 1;
            if (!E(i3)) {
                throw new IllegalStateException("This cannot happen with fixed magic multiplier and grow-only hash array. Have object hashCodes changed?");
            }
            i3 = i4;
        }
    }

    private final void H(int i2) {
        int c2;
        c2 = RangesKt___RangesKt.c(this.maxProbeDistance * 2, v() / 2);
        int i3 = c2;
        int i4 = 0;
        int i5 = i2;
        do {
            i2 = i2 == 0 ? v() - 1 : i2 - 1;
            i4++;
            if (i4 > this.maxProbeDistance) {
                this.hashArray[i5] = 0;
                return;
            }
            int[] iArr = this.hashArray;
            int i6 = iArr[i2];
            if (i6 == 0) {
                iArr[i5] = 0;
                return;
            }
            if (i6 < 0) {
                iArr[i5] = -1;
            } else {
                int i7 = i6 - 1;
                if (((z(this.keysArray[i7]) - i2) & (v() - 1)) >= i4) {
                    this.hashArray[i5] = i6;
                    this.presenceArray[i7] = i5;
                }
                i3--;
            }
            i5 = i2;
            i4 = 0;
            i3--;
        } while (i3 >= 0);
        this.hashArray[i5] = -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void J(int i2) {
        ListBuilderKt.f(this.keysArray, i2);
        H(this.presenceArray[i2]);
        this.presenceArray[i2] = -1;
        this.size = size() - 1;
    }

    private final boolean L(int i2) {
        int t = t();
        int i3 = this.length;
        int i4 = t - i3;
        int size = i3 - size();
        return i4 < i2 && i4 + size >= i2 && size >= t() / 4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object[] h() {
        V[] vArr = this.valuesArray;
        if (vArr != null) {
            return vArr;
        }
        V[] vArr2 = (V[]) ListBuilderKt.d(t());
        this.valuesArray = vArr2;
        return vArr2;
    }

    private final void k() {
        int i2;
        V[] vArr = this.valuesArray;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            i2 = this.length;
            if (i3 >= i2) {
                break;
            }
            if (this.presenceArray[i3] >= 0) {
                K[] kArr = this.keysArray;
                kArr[i4] = kArr[i3];
                if (vArr != null) {
                    vArr[i4] = vArr[i3];
                }
                i4++;
            }
            i3++;
        }
        ListBuilderKt.g(this.keysArray, i4, i2);
        if (vArr != null) {
            ListBuilderKt.g(vArr, i4, this.length);
        }
        this.length = i4;
    }

    private final boolean n(Map map) {
        return size() == map.size() && l(map.entrySet());
    }

    private final void o(int i2) {
        if (i2 < 0) {
            throw new OutOfMemoryError();
        }
        if (i2 > t()) {
            int t = (t() * 3) / 2;
            if (i2 <= t) {
                i2 = t;
            }
            this.keysArray = (K[]) ListBuilderKt.e(this.keysArray, i2);
            V[] vArr = this.valuesArray;
            this.valuesArray = vArr != null ? (V[]) ListBuilderKt.e(vArr, i2) : null;
            int[] copyOf = Arrays.copyOf(this.presenceArray, i2);
            Intrinsics.d(copyOf, "copyOf(this, newSize)");
            this.presenceArray = copyOf;
            int c2 = Companion.c(i2);
            if (c2 > v()) {
                F(c2);
            }
        }
    }

    private final void p(int i2) {
        if (L(i2)) {
            F(v());
        } else {
            o(this.length + i2);
        }
    }

    private final int r(Object obj) {
        int z = z(obj);
        int i2 = this.maxProbeDistance;
        while (true) {
            int i3 = this.hashArray[z];
            if (i3 == 0) {
                return -1;
            }
            if (i3 > 0) {
                int i4 = i3 - 1;
                if (Intrinsics.a(this.keysArray[i4], obj)) {
                    return i4;
                }
            }
            i2--;
            if (i2 < 0) {
                return -1;
            }
            z = z == 0 ? v() - 1 : z - 1;
        }
    }

    private final int s(Object obj) {
        int i2 = this.length;
        while (true) {
            i2--;
            if (i2 < 0) {
                return -1;
            }
            if (this.presenceArray[i2] >= 0) {
                V[] vArr = this.valuesArray;
                Intrinsics.b(vArr);
                if (Intrinsics.a(vArr[i2], obj)) {
                    return i2;
                }
            }
        }
    }

    private final int v() {
        return this.hashArray.length;
    }

    private final Object writeReplace() {
        if (this.isReadOnly) {
            return new SerializedMap(this);
        }
        throw new NotSerializableException("The map cannot be serialized while it is being built.");
    }

    private final int z(Object obj) {
        return ((obj != null ? obj.hashCode() : 0) * MAGIC) >>> this.hashShift;
    }

    public final boolean A() {
        return this.isReadOnly;
    }

    public final KeysItr B() {
        return new KeysItr(this);
    }

    public final boolean G(Map.Entry entry) {
        Intrinsics.e(entry, "entry");
        j();
        int r2 = r(entry.getKey());
        if (r2 < 0) {
            return false;
        }
        V[] vArr = this.valuesArray;
        Intrinsics.b(vArr);
        if (!Intrinsics.a(vArr[r2], entry.getValue())) {
            return false;
        }
        J(r2);
        return true;
    }

    public final int I(Object obj) {
        j();
        int r2 = r(obj);
        if (r2 < 0) {
            return -1;
        }
        J(r2);
        return r2;
    }

    public final boolean K(Object obj) {
        j();
        int s2 = s(obj);
        if (s2 < 0) {
            return false;
        }
        J(s2);
        return true;
    }

    public final ValuesItr M() {
        return new ValuesItr(this);
    }

    @Override // java.util.Map
    public void clear() {
        j();
        IntIterator it = new IntRange(0, this.length - 1).iterator();
        while (it.hasNext()) {
            int nextInt = it.nextInt();
            int[] iArr = this.presenceArray;
            int i2 = iArr[nextInt];
            if (i2 >= 0) {
                this.hashArray[i2] = 0;
                iArr[nextInt] = -1;
            }
        }
        ListBuilderKt.g(this.keysArray, 0, this.length);
        V[] vArr = this.valuesArray;
        if (vArr != null) {
            ListBuilderKt.g(vArr, 0, this.length);
        }
        this.size = 0;
        this.length = 0;
    }

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        return r(obj) >= 0;
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        return s(obj) >= 0;
    }

    @Override // java.util.Map
    public final /* bridge */ Set entrySet() {
        return u();
    }

    @Override // java.util.Map
    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof Map) && n((Map) obj));
    }

    public final int g(Object obj) {
        int c2;
        j();
        while (true) {
            int z = z(obj);
            c2 = RangesKt___RangesKt.c(this.maxProbeDistance * 2, v() / 2);
            int i2 = 0;
            while (true) {
                int i3 = this.hashArray[z];
                if (i3 <= 0) {
                    if (this.length < t()) {
                        int i4 = this.length;
                        int i5 = i4 + 1;
                        this.length = i5;
                        ((K[]) this.keysArray)[i4] = obj;
                        this.presenceArray[i4] = z;
                        this.hashArray[z] = i5;
                        this.size = size() + 1;
                        if (i2 > this.maxProbeDistance) {
                            this.maxProbeDistance = i2;
                        }
                        return i4;
                    }
                    p(1);
                } else {
                    if (Intrinsics.a(this.keysArray[i3 - 1], obj)) {
                        return -i3;
                    }
                    i2++;
                    if (i2 > c2) {
                        F(v() * 2);
                        break;
                    }
                    z = z == 0 ? v() - 1 : z - 1;
                }
            }
        }
    }

    @Override // java.util.Map
    public Object get(Object obj) {
        int r2 = r(obj);
        if (r2 < 0) {
            return null;
        }
        V[] vArr = this.valuesArray;
        Intrinsics.b(vArr);
        return vArr[r2];
    }

    @Override // java.util.Map
    public int hashCode() {
        EntriesItr q2 = q();
        int i2 = 0;
        while (q2.hasNext()) {
            i2 += q2.j();
        }
        return i2;
    }

    public final Map i() {
        j();
        this.isReadOnly = true;
        return this;
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return size() == 0;
    }

    public final void j() {
        if (this.isReadOnly) {
            throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.Map
    public final /* bridge */ Set keySet() {
        return w();
    }

    public final boolean l(Collection m2) {
        Intrinsics.e(m2, "m");
        for (Object obj : m2) {
            if (obj != null) {
                try {
                    if (!m((Map.Entry) obj)) {
                    }
                } catch (ClassCastException unused) {
                }
            }
            return false;
        }
        return true;
    }

    public final boolean m(Map.Entry entry) {
        Intrinsics.e(entry, "entry");
        int r2 = r(entry.getKey());
        if (r2 < 0) {
            return false;
        }
        V[] vArr = this.valuesArray;
        Intrinsics.b(vArr);
        return Intrinsics.a(vArr[r2], entry.getValue());
    }

    @Override // java.util.Map
    public Object put(Object obj, Object obj2) {
        j();
        int g2 = g(obj);
        Object[] h2 = h();
        if (g2 >= 0) {
            h2[g2] = obj2;
            return null;
        }
        int i2 = (-g2) - 1;
        Object obj3 = h2[i2];
        h2[i2] = obj2;
        return obj3;
    }

    @Override // java.util.Map
    public void putAll(Map from) {
        Intrinsics.e(from, "from");
        j();
        C(from.entrySet());
    }

    public final EntriesItr q() {
        return new EntriesItr(this);
    }

    @Override // java.util.Map
    public Object remove(Object obj) {
        int I = I(obj);
        if (I < 0) {
            return null;
        }
        V[] vArr = this.valuesArray;
        Intrinsics.b(vArr);
        V v = vArr[I];
        ListBuilderKt.f(vArr, I);
        return v;
    }

    @Override // java.util.Map
    public final /* bridge */ int size() {
        return x();
    }

    public final int t() {
        return this.keysArray.length;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder((size() * 3) + 2);
        sb.append("{");
        EntriesItr q2 = q();
        int i2 = 0;
        while (q2.hasNext()) {
            if (i2 > 0) {
                sb.append(", ");
            }
            q2.i(sb);
            i2++;
        }
        sb.append("}");
        String sb2 = sb.toString();
        Intrinsics.d(sb2, "sb.toString()");
        return sb2;
    }

    public Set u() {
        MapBuilderEntries<K, V> mapBuilderEntries = this.entriesView;
        if (mapBuilderEntries != null) {
            return mapBuilderEntries;
        }
        MapBuilderEntries<K, V> mapBuilderEntries2 = new MapBuilderEntries<>(this);
        this.entriesView = mapBuilderEntries2;
        return mapBuilderEntries2;
    }

    @Override // java.util.Map
    public final /* bridge */ Collection values() {
        return y();
    }

    public Set w() {
        MapBuilderKeys<K> mapBuilderKeys = this.keysView;
        if (mapBuilderKeys != null) {
            return mapBuilderKeys;
        }
        MapBuilderKeys<K> mapBuilderKeys2 = new MapBuilderKeys<>(this);
        this.keysView = mapBuilderKeys2;
        return mapBuilderKeys2;
    }

    public int x() {
        return this.size;
    }

    public Collection y() {
        MapBuilderValues<V> mapBuilderValues = this.valuesView;
        if (mapBuilderValues != null) {
            return mapBuilderValues;
        }
        MapBuilderValues<V> mapBuilderValues2 = new MapBuilderValues<>(this);
        this.valuesView = mapBuilderValues2;
        return mapBuilderValues2;
    }

    public MapBuilder() {
        this(8);
    }

    public MapBuilder(int i2) {
        this(ListBuilderKt.d(i2), null, new int[i2], new int[Companion.c(i2)], 2, 0);
    }
}
