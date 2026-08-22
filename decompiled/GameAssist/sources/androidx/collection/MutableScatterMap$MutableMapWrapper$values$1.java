package androidx.collection;

import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMutableCollection;

/* JADX INFO: Add missing generic type declarations: [V] */
@Metadata
@SourceDebugExtension
/* loaded from: classes.dex */
public final class MutableScatterMap$MutableMapWrapper$values$1<V> implements Collection<V>, KMutableCollection {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ MutableScatterMap f1353c;

    MutableScatterMap$MutableMapWrapper$values$1(MutableScatterMap mutableScatterMap) {
        this.f1353c = mutableScatterMap;
    }

    @Override // java.util.Collection
    public boolean add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public boolean addAll(Collection elements) {
        Intrinsics.e(elements, "elements");
        throw new UnsupportedOperationException();
    }

    public int b() {
        return this.f1353c.f1388e;
    }

    @Override // java.util.Collection
    public void clear() {
        this.f1353c.h();
    }

    @Override // java.util.Collection
    public boolean contains(Object obj) {
        return this.f1353c.b(obj);
    }

    @Override // java.util.Collection
    public boolean containsAll(Collection elements) {
        Intrinsics.e(elements, "elements");
        MutableScatterMap mutableScatterMap = this.f1353c;
        if (elements.isEmpty()) {
            return true;
        }
        Iterator it = elements.iterator();
        while (it.hasNext()) {
            if (!mutableScatterMap.b(it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.Collection
    public boolean isEmpty() {
        return this.f1353c.f();
    }

    @Override // java.util.Collection, java.lang.Iterable
    public Iterator iterator() {
        return new MutableScatterMap$MutableMapWrapper$values$1$iterator$1(this.f1353c);
    }

    @Override // java.util.Collection
    public boolean remove(Object obj) {
        MutableScatterMap mutableScatterMap = this.f1353c;
        long[] jArr = mutableScatterMap.f1384a;
        int length = jArr.length - 2;
        if (length >= 0) {
            int i2 = 0;
            while (true) {
                long j2 = jArr[i2];
                if ((((~j2) << 7) & j2 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i3 = 8 - ((~(i2 - length)) >>> 31);
                    for (int i4 = 0; i4 < i3; i4++) {
                        if ((255 & j2) < 128) {
                            int i5 = (i2 << 3) + i4;
                            if (Intrinsics.a(mutableScatterMap.f1386c[i5], obj)) {
                                mutableScatterMap.p(i5);
                                return true;
                            }
                        }
                        j2 >>= 8;
                    }
                    if (i3 != 8) {
                        break;
                    }
                }
                if (i2 == length) {
                    break;
                }
                i2++;
            }
        }
        return false;
    }

    @Override // java.util.Collection
    public boolean removeAll(Collection elements) {
        boolean x;
        Intrinsics.e(elements, "elements");
        MutableScatterMap mutableScatterMap = this.f1353c;
        long[] jArr = mutableScatterMap.f1384a;
        int length = jArr.length - 2;
        boolean z = false;
        if (length >= 0) {
            int i2 = 0;
            boolean z2 = false;
            while (true) {
                long j2 = jArr[i2];
                if ((((~j2) << 7) & j2 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i3 = 8 - ((~(i2 - length)) >>> 31);
                    for (int i4 = 0; i4 < i3; i4++) {
                        if ((255 & j2) < 128) {
                            int i5 = (i2 << 3) + i4;
                            x = CollectionsKt___CollectionsKt.x(elements, mutableScatterMap.f1386c[i5]);
                            if (x) {
                                mutableScatterMap.p(i5);
                                z2 = true;
                            }
                        }
                        j2 >>= 8;
                    }
                    if (i3 != 8) {
                        return z2;
                    }
                }
                if (i2 == length) {
                    z = z2;
                    break;
                }
                i2++;
            }
        }
        return z;
    }

    @Override // java.util.Collection
    public boolean retainAll(Collection elements) {
        boolean x;
        Intrinsics.e(elements, "elements");
        MutableScatterMap mutableScatterMap = this.f1353c;
        long[] jArr = mutableScatterMap.f1384a;
        int length = jArr.length - 2;
        boolean z = false;
        if (length >= 0) {
            int i2 = 0;
            boolean z2 = false;
            while (true) {
                long j2 = jArr[i2];
                if ((((~j2) << 7) & j2 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i3 = 8 - ((~(i2 - length)) >>> 31);
                    for (int i4 = 0; i4 < i3; i4++) {
                        if ((255 & j2) < 128) {
                            int i5 = (i2 << 3) + i4;
                            x = CollectionsKt___CollectionsKt.x(elements, mutableScatterMap.f1386c[i5]);
                            if (!x) {
                                mutableScatterMap.p(i5);
                                z2 = true;
                            }
                        }
                        j2 >>= 8;
                    }
                    if (i3 != 8) {
                        return z2;
                    }
                }
                if (i2 == length) {
                    z = z2;
                    break;
                }
                i2++;
            }
        }
        return z;
    }

    @Override // java.util.Collection
    public final /* bridge */ int size() {
        return b();
    }

    @Override // java.util.Collection
    public Object[] toArray() {
        return CollectionToArray.a(this);
    }

    @Override // java.util.Collection
    public Object[] toArray(Object[] array) {
        Intrinsics.e(array, "array");
        return CollectionToArray.b(this, array);
    }
}
