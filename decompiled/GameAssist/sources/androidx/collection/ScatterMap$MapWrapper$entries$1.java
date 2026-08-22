package androidx.collection;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.sequences.SequencesKt__SequenceBuilderKt;

/* JADX INFO: Add missing generic type declarations: [V, K] */
@Metadata
@SourceDebugExtension
/* loaded from: classes.dex */
public final class ScatterMap$MapWrapper$entries$1<K, V> implements Set<Map.Entry<? extends K, ? extends V>>, KMappedMarker {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ ScatterMap f1390c;

    ScatterMap$MapWrapper$entries$1(ScatterMap scatterMap) {
        this.f1390c = scatterMap;
    }

    @Override // java.util.Set, java.util.Collection
    public /* bridge */ /* synthetic */ boolean add(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Set, java.util.Collection
    public boolean addAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean b(Map.Entry element) {
        Intrinsics.e(element, "element");
        return Intrinsics.a(this.f1390c.c(element.getKey()), element.getValue());
    }

    @Override // java.util.Set, java.util.Collection
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Set, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof Map.Entry) {
            return b((Map.Entry) obj);
        }
        return false;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean containsAll(Collection elements) {
        Intrinsics.e(elements, "elements");
        ScatterMap scatterMap = this.f1390c;
        if (elements.isEmpty()) {
            return true;
        }
        Iterator it = elements.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            if (!Intrinsics.a(scatterMap.c(entry.getKey()), entry.getValue())) {
                return false;
            }
        }
        return true;
    }

    public int d() {
        return this.f1390c.f1388e;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean isEmpty() {
        return this.f1390c.f();
    }

    @Override // java.util.Set, java.util.Collection, java.lang.Iterable
    public Iterator iterator() {
        Iterator a2;
        a2 = SequencesKt__SequenceBuilderKt.a(new ScatterMap$MapWrapper$entries$1$iterator$1(this.f1390c, null));
        return a2;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Set, java.util.Collection
    public boolean removeAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Set, java.util.Collection
    public boolean retainAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Set, java.util.Collection
    public final /* bridge */ int size() {
        return d();
    }

    @Override // java.util.Set, java.util.Collection
    public Object[] toArray() {
        return CollectionToArray.a(this);
    }

    @Override // java.util.Set, java.util.Collection
    public Object[] toArray(Object[] array) {
        Intrinsics.e(array, "array");
        return CollectionToArray.b(this, array);
    }
}
