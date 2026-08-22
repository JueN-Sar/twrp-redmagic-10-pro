package kotlin.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMappedMarker;

@SinceKotlin
@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public abstract class AbstractMap<K, V> implements Map<K, V>, KMappedMarker {

    /* renamed from: i, reason: collision with root package name */
    public static final Companion f18299i = new Companion(null);

    /* renamed from: c, reason: collision with root package name */
    private volatile Set f18300c;

    /* renamed from: h, reason: collision with root package name */
    private volatile Collection f18301h;

    @Metadata
    @SourceDebugExtension
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private final Map.Entry g(Object obj) {
        Object obj2;
        Iterator it = entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                obj2 = null;
                break;
            }
            obj2 = it.next();
            if (Intrinsics.a(((Map.Entry) obj2).getKey(), obj)) {
                break;
            }
        }
        return (Map.Entry) obj2;
    }

    private final String h(Object obj) {
        return obj == this ? "(this Map)" : String.valueOf(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String i(Map.Entry entry) {
        return h(entry.getKey()) + '=' + h(entry.getValue());
    }

    public final boolean b(Map.Entry entry) {
        if (entry == null) {
            return false;
        }
        Object key = entry.getKey();
        Object value = entry.getValue();
        Intrinsics.c(this, "null cannot be cast to non-null type kotlin.collections.Map<K of kotlin.collections.MapsKt__MapsKt.get, V of kotlin.collections.MapsKt__MapsKt.get>");
        V v = get(key);
        if (!Intrinsics.a(value, v)) {
            return false;
        }
        if (v != null) {
            return true;
        }
        Intrinsics.c(this, "null cannot be cast to non-null type kotlin.collections.Map<K of kotlin.collections.MapsKt__MapsKt.containsKey, *>");
        return containsKey(key);
    }

    public abstract Set c();

    @Override // java.util.Map
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        return g(obj) != null;
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        Set entrySet = entrySet();
        if ((entrySet instanceof Collection) && entrySet.isEmpty()) {
            return false;
        }
        Iterator it = entrySet.iterator();
        while (it.hasNext()) {
            if (Intrinsics.a(((Map.Entry) it.next()).getValue(), obj)) {
                return true;
            }
        }
        return false;
    }

    public Set d() {
        if (this.f18300c == null) {
            this.f18300c = new AbstractSet<K>() { // from class: kotlin.collections.AbstractMap$keys$1
                @Override // kotlin.collections.AbstractCollection
                public int b() {
                    return AbstractMap.this.size();
                }

                @Override // kotlin.collections.AbstractCollection, java.util.Collection
                public boolean contains(Object obj) {
                    return AbstractMap.this.containsKey(obj);
                }

                @Override // kotlin.collections.AbstractSet, java.util.Collection, java.lang.Iterable, java.util.Set
                public Iterator iterator() {
                    return new AbstractMap$keys$1$iterator$1(AbstractMap.this.entrySet().iterator());
                }
            };
        }
        Set set = this.f18300c;
        Intrinsics.b(set);
        return set;
    }

    public int e() {
        return entrySet().size();
    }

    @Override // java.util.Map
    public final /* bridge */ Set entrySet() {
        return c();
    }

    @Override // java.util.Map
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Map)) {
            return false;
        }
        Map map = (Map) obj;
        if (size() != map.size()) {
            return false;
        }
        Set<Map.Entry<K, V>> entrySet = map.entrySet();
        if ((entrySet instanceof Collection) && entrySet.isEmpty()) {
            return true;
        }
        Iterator<T> it = entrySet.iterator();
        while (it.hasNext()) {
            if (!b((Map.Entry) it.next())) {
                return false;
            }
        }
        return true;
    }

    public Collection f() {
        if (this.f18301h == null) {
            this.f18301h = new AbstractCollection<V>() { // from class: kotlin.collections.AbstractMap$values$1
                @Override // kotlin.collections.AbstractCollection
                public int b() {
                    return AbstractMap.this.size();
                }

                @Override // kotlin.collections.AbstractCollection, java.util.Collection
                public boolean contains(Object obj) {
                    return AbstractMap.this.containsValue(obj);
                }

                @Override // java.util.Collection, java.lang.Iterable
                public Iterator iterator() {
                    return new AbstractMap$values$1$iterator$1(AbstractMap.this.entrySet().iterator());
                }
            };
        }
        Collection collection = this.f18301h;
        Intrinsics.b(collection);
        return collection;
    }

    @Override // java.util.Map
    public Object get(Object obj) {
        Map.Entry g2 = g(obj);
        if (g2 != null) {
            return g2.getValue();
        }
        return null;
    }

    @Override // java.util.Map
    public int hashCode() {
        return entrySet().hashCode();
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.Map
    public final /* bridge */ Set keySet() {
        return d();
    }

    @Override // java.util.Map
    public Object put(Object obj, Object obj2) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Map
    public void putAll(Map map) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Map
    public Object remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Map
    public final /* bridge */ int size() {
        return e();
    }

    public String toString() {
        String E;
        E = CollectionsKt___CollectionsKt.E(entrySet(), ", ", "{", "}", 0, null, new Function1<Map.Entry<? extends K, ? extends V>, CharSequence>(this) { // from class: kotlin.collections.AbstractMap$toString$1
            final /* synthetic */ AbstractMap<K, V> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function1
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public final CharSequence c(Map.Entry it) {
                String i2;
                Intrinsics.e(it, "it");
                i2 = this.this$0.i(it);
                return i2;
            }
        }, 24, null);
        return E;
    }

    @Override // java.util.Map
    public final /* bridge */ Collection values() {
        return f();
    }
}
