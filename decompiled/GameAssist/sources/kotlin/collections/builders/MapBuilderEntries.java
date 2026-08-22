package kotlin.collections.builders;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata
/* loaded from: classes2.dex */
public final class MapBuilderEntries<K, V> extends AbstractMapBuilderEntrySet<Map.Entry<K, V>, K, V> {

    /* renamed from: c, reason: collision with root package name */
    private final MapBuilder f18376c;

    public MapBuilderEntries(MapBuilder backing) {
        Intrinsics.e(backing, "backing");
        this.f18376c = backing;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean addAll(Collection elements) {
        Intrinsics.e(elements, "elements");
        throw new UnsupportedOperationException();
    }

    @Override // kotlin.collections.AbstractMutableSet
    public int b() {
        return this.f18376c.size();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public void clear() {
        this.f18376c.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean containsAll(Collection elements) {
        Intrinsics.e(elements, "elements");
        return this.f18376c.l(elements);
    }

    @Override // kotlin.collections.builders.AbstractMapBuilderEntrySet
    public boolean f(Map.Entry element) {
        Intrinsics.e(element, "element");
        return this.f18376c.m(element);
    }

    @Override // kotlin.collections.builders.AbstractMapBuilderEntrySet
    public boolean g(Map.Entry element) {
        Intrinsics.e(element, "element");
        return this.f18376c.G(element);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    /* renamed from: h, reason: merged with bridge method [inline-methods] */
    public boolean add(Map.Entry element) {
        Intrinsics.e(element, "element");
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean isEmpty() {
        return this.f18376c.isEmpty();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator iterator() {
        return this.f18376c.q();
    }

    @Override // java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean removeAll(Collection elements) {
        Intrinsics.e(elements, "elements");
        this.f18376c.j();
        return super.removeAll(elements);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean retainAll(Collection elements) {
        Intrinsics.e(elements, "elements");
        this.f18376c.j();
        return super.retainAll(elements);
    }
}
