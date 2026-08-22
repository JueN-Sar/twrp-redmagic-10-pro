package kotlin.collections;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
final class MutableMapWithDefaultImpl<K, V> implements MutableMapWithDefault<K, V> {

    /* renamed from: c, reason: collision with root package name */
    private final Map f18353c;

    public Set a() {
        return c().entrySet();
    }

    public Set b() {
        return c().keySet();
    }

    public Map c() {
        return this.f18353c;
    }

    @Override // java.util.Map
    public void clear() {
        c().clear();
    }

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        return c().containsKey(obj);
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        return c().containsValue(obj);
    }

    public int d() {
        return c().size();
    }

    public Collection e() {
        return c().values();
    }

    @Override // java.util.Map
    public final /* bridge */ Set entrySet() {
        return a();
    }

    @Override // java.util.Map
    public boolean equals(Object obj) {
        return c().equals(obj);
    }

    @Override // java.util.Map
    public Object get(Object obj) {
        return c().get(obj);
    }

    @Override // java.util.Map
    public int hashCode() {
        return c().hashCode();
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return c().isEmpty();
    }

    @Override // java.util.Map
    public final /* bridge */ Set keySet() {
        return b();
    }

    @Override // java.util.Map
    public Object put(Object obj, Object obj2) {
        return c().put(obj, obj2);
    }

    @Override // java.util.Map
    public void putAll(Map from) {
        Intrinsics.e(from, "from");
        c().putAll(from);
    }

    @Override // java.util.Map
    public Object remove(Object obj) {
        return c().remove(obj);
    }

    @Override // java.util.Map
    public final /* bridge */ int size() {
        return d();
    }

    public String toString() {
        return c().toString();
    }

    @Override // java.util.Map
    public final /* bridge */ Collection values() {
        return e();
    }
}
