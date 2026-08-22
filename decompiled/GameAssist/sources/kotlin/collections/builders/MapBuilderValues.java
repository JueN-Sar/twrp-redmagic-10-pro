package kotlin.collections.builders;

import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.AbstractMutableCollection;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableCollection;

@Metadata
/* loaded from: classes2.dex */
public final class MapBuilderValues<V> extends AbstractMutableCollection<V> implements Collection<V>, KMutableCollection {

    /* renamed from: c, reason: collision with root package name */
    private final MapBuilder f18378c;

    public MapBuilderValues(MapBuilder backing) {
        Intrinsics.e(backing, "backing");
        this.f18378c = backing;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean addAll(Collection elements) {
        Intrinsics.e(elements, "elements");
        throw new UnsupportedOperationException();
    }

    @Override // kotlin.collections.AbstractMutableCollection
    public int b() {
        return this.f18378c.size();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public void clear() {
        this.f18378c.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean contains(Object obj) {
        return this.f18378c.containsValue(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return this.f18378c.isEmpty();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public Iterator iterator() {
        return this.f18378c.M();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean remove(Object obj) {
        return this.f18378c.K(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean removeAll(Collection elements) {
        Intrinsics.e(elements, "elements");
        this.f18378c.j();
        return super.removeAll(elements);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean retainAll(Collection elements) {
        Intrinsics.e(elements, "elements");
        this.f18378c.j();
        return super.retainAll(elements);
    }
}
