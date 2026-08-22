package androidx.collection;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.sequences.SequencesKt__SequenceBuilderKt;

/* JADX INFO: Add missing generic type declarations: [K] */
@Metadata
@SourceDebugExtension
/* loaded from: classes.dex */
public final class ScatterMap$MapWrapper$keys$1<K> implements Set<K>, KMappedMarker {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ ScatterMap f1391c;

    ScatterMap$MapWrapper$keys$1(ScatterMap scatterMap) {
        this.f1391c = scatterMap;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean add(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Set, java.util.Collection
    public boolean addAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public int b() {
        return this.f1391c.f1388e;
    }

    @Override // java.util.Set, java.util.Collection
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Set, java.util.Collection
    public boolean contains(Object obj) {
        return this.f1391c.a(obj);
    }

    @Override // java.util.Set, java.util.Collection
    public boolean containsAll(Collection elements) {
        Intrinsics.e(elements, "elements");
        ScatterMap scatterMap = this.f1391c;
        if (elements.isEmpty()) {
            return true;
        }
        Iterator it = elements.iterator();
        while (it.hasNext()) {
            if (!scatterMap.a(it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean isEmpty() {
        return this.f1391c.f();
    }

    @Override // java.util.Set, java.util.Collection, java.lang.Iterable
    public Iterator iterator() {
        Iterator a2;
        a2 = SequencesKt__SequenceBuilderKt.a(new ScatterMap$MapWrapper$keys$1$iterator$1(this.f1391c, null));
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
        return b();
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
