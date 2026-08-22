package androidx.collection;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMutableSet;

/* JADX INFO: Add missing generic type declarations: [K] */
@Metadata
@SourceDebugExtension
/* loaded from: classes.dex */
public final class MutableScatterMap$MutableMapWrapper$keys$1<K> implements Set<K>, KMutableSet {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ MutableScatterMap f1349c;

    MutableScatterMap$MutableMapWrapper$keys$1(MutableScatterMap mutableScatterMap) {
        this.f1349c = mutableScatterMap;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Set, java.util.Collection
    public boolean addAll(Collection elements) {
        Intrinsics.e(elements, "elements");
        throw new UnsupportedOperationException();
    }

    public int b() {
        return this.f1349c.f1388e;
    }

    @Override // java.util.Set, java.util.Collection
    public void clear() {
        this.f1349c.h();
    }

    @Override // java.util.Set, java.util.Collection
    public boolean contains(Object obj) {
        return this.f1349c.a(obj);
    }

    @Override // java.util.Set, java.util.Collection
    public boolean containsAll(Collection elements) {
        Intrinsics.e(elements, "elements");
        MutableScatterMap mutableScatterMap = this.f1349c;
        if (elements.isEmpty()) {
            return true;
        }
        Iterator it = elements.iterator();
        while (it.hasNext()) {
            if (!mutableScatterMap.a(it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean isEmpty() {
        return this.f1349c.f();
    }

    @Override // java.util.Set, java.util.Collection, java.lang.Iterable
    public Iterator iterator() {
        return new MutableScatterMap$MutableMapWrapper$keys$1$iterator$1(this.f1349c);
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x006f, code lost:
    
        if (((r8 & ((~r8) << 6)) & (-9187201950435737472L)) == 0) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0071, code lost:
    
        r12 = -1;
     */
    @Override // java.util.Set, java.util.Collection
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean remove(java.lang.Object r19) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            androidx.collection.MutableScatterMap r2 = r0.f1349c
            if (r1 == 0) goto Ld
            int r4 = r19.hashCode()
            goto Le
        Ld:
            r4 = 0
        Le:
            r5 = -862048943(0xffffffffcc9e2d51, float:-8.293031E7)
            int r4 = r4 * r5
            int r5 = r4 << 16
            r4 = r4 ^ r5
            r5 = r4 & 127(0x7f, float:1.78E-43)
            int r6 = r2.f1387d
            int r4 = r4 >>> 7
            r4 = r4 & r6
            r7 = 0
        L1d:
            long[] r8 = r2.f1384a
            int r9 = r4 >> 3
            r10 = r4 & 7
            int r10 = r10 << 3
            r11 = r8[r9]
            long r11 = r11 >>> r10
            r13 = 1
            int r9 = r9 + r13
            r8 = r8[r9]
            int r14 = 64 - r10
            long r8 = r8 << r14
            long r14 = (long) r10
            long r14 = -r14
            r10 = 63
            long r14 = r14 >> r10
            long r8 = r8 & r14
            long r8 = r8 | r11
            long r10 = (long) r5
            r14 = 72340172838076673(0x101010101010101, double:7.748604185489348E-304)
            long r10 = r10 * r14
            long r10 = r10 ^ r8
            long r14 = r10 - r14
            long r10 = ~r10
            long r10 = r10 & r14
            r14 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r10 = r10 & r14
        L48:
            r16 = 0
            int r12 = (r10 > r16 ? 1 : (r10 == r16 ? 0 : -1))
            if (r12 == 0) goto L68
            int r12 = java.lang.Long.numberOfTrailingZeros(r10)
            int r12 = r12 >> 3
            int r12 = r12 + r4
            r12 = r12 & r6
            java.lang.Object[] r3 = r2.f1385b
            r3 = r3[r12]
            boolean r3 = kotlin.jvm.internal.Intrinsics.a(r3, r1)
            if (r3 == 0) goto L61
            goto L72
        L61:
            r16 = 1
            long r16 = r10 - r16
            long r10 = r10 & r16
            goto L48
        L68:
            long r10 = ~r8
            r3 = 6
            long r10 = r10 << r3
            long r8 = r8 & r10
            long r8 = r8 & r14
            int r3 = (r8 > r16 ? 1 : (r8 == r16 ? 0 : -1))
            if (r3 == 0) goto L7c
            r12 = -1
        L72:
            if (r12 < 0) goto L7a
            androidx.collection.MutableScatterMap r0 = r0.f1349c
            r0.p(r12)
            return r13
        L7a:
            r3 = 0
            return r3
        L7c:
            r3 = 0
            int r7 = r7 + 8
            int r4 = r4 + r7
            r4 = r4 & r6
            goto L1d
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.MutableScatterMap$MutableMapWrapper$keys$1.remove(java.lang.Object):boolean");
    }

    @Override // java.util.Set, java.util.Collection
    public boolean removeAll(Collection elements) {
        boolean x;
        Intrinsics.e(elements, "elements");
        MutableScatterMap mutableScatterMap = this.f1349c;
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
                            x = CollectionsKt___CollectionsKt.x(elements, mutableScatterMap.f1385b[i5]);
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

    @Override // java.util.Set, java.util.Collection
    public boolean retainAll(Collection elements) {
        boolean x;
        Intrinsics.e(elements, "elements");
        MutableScatterMap mutableScatterMap = this.f1349c;
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
                            x = CollectionsKt___CollectionsKt.x(elements, mutableScatterMap.f1385b[i5]);
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
