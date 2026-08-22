package androidx.collection;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.jvm.internal.markers.KMutableSet;

/* JADX INFO: Add missing generic type declarations: [V, K] */
@Metadata
@SourceDebugExtension
/* loaded from: classes.dex */
public final class MutableScatterMap$MutableMapWrapper$entries$1<K, V> implements Set<Map.Entry<K, V>>, KMutableSet {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ MutableScatterMap f1345c;

    MutableScatterMap$MutableMapWrapper$entries$1(MutableScatterMap mutableScatterMap) {
        this.f1345c = mutableScatterMap;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean addAll(Collection elements) {
        Intrinsics.e(elements, "elements");
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Set, java.util.Collection
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public boolean add(Map.Entry element) {
        Intrinsics.e(element, "element");
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Set, java.util.Collection
    public void clear() {
        this.f1345c.h();
    }

    @Override // java.util.Set, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (TypeIntrinsics.d(obj)) {
            return d((Map.Entry) obj);
        }
        return false;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean containsAll(Collection elements) {
        Intrinsics.e(elements, "elements");
        MutableScatterMap mutableScatterMap = this.f1345c;
        if (elements.isEmpty()) {
            return true;
        }
        Iterator it = elements.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            if (!Intrinsics.a(mutableScatterMap.c(entry.getKey()), entry.getValue())) {
                return false;
            }
        }
        return true;
    }

    public boolean d(Map.Entry element) {
        Intrinsics.e(element, "element");
        return Intrinsics.a(this.f1345c.c(element.getKey()), element.getValue());
    }

    public int f() {
        return this.f1345c.f1388e;
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0078, code lost:
    
        if (((r9 & ((~r9) << 6)) & (-9187201950435737472L)) == 0) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x007a, code lost:
    
        r15 = -1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean g(java.util.Map.Entry r19) {
        /*
            r18 = this;
            r0 = r18
            java.lang.String r1 = "element"
            r2 = r19
            kotlin.jvm.internal.Intrinsics.e(r2, r1)
            androidx.collection.MutableScatterMap r1 = r0.f1345c
            java.lang.Object r3 = r19.getKey()
            if (r3 == 0) goto L16
            int r5 = r3.hashCode()
            goto L17
        L16:
            r5 = 0
        L17:
            r6 = -862048943(0xffffffffcc9e2d51, float:-8.293031E7)
            int r5 = r5 * r6
            int r6 = r5 << 16
            r5 = r5 ^ r6
            r6 = r5 & 127(0x7f, float:1.78E-43)
            int r7 = r1.f1387d
            int r5 = r5 >>> 7
            r5 = r5 & r7
            r8 = 0
        L26:
            long[] r9 = r1.f1384a
            int r10 = r5 >> 3
            r11 = r5 & 7
            int r11 = r11 << 3
            r12 = r9[r10]
            long r12 = r12 >>> r11
            r14 = 1
            int r10 = r10 + r14
            r9 = r9[r10]
            int r15 = 64 - r11
            long r9 = r9 << r15
            long r14 = (long) r11
            long r14 = -r14
            r11 = 63
            long r14 = r14 >> r11
            long r9 = r9 & r14
            long r9 = r9 | r12
            long r11 = (long) r6
            r13 = 72340172838076673(0x101010101010101, double:7.748604185489348E-304)
            long r11 = r11 * r13
            long r11 = r11 ^ r9
            long r13 = r11 - r13
            long r11 = ~r11
            long r11 = r11 & r13
            r13 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r11 = r11 & r13
        L51:
            r16 = 0
            int r15 = (r11 > r16 ? 1 : (r11 == r16 ? 0 : -1))
            if (r15 == 0) goto L71
            int r15 = java.lang.Long.numberOfTrailingZeros(r11)
            int r15 = r15 >> 3
            int r15 = r15 + r5
            r15 = r15 & r7
            java.lang.Object[] r4 = r1.f1385b
            r4 = r4[r15]
            boolean r4 = kotlin.jvm.internal.Intrinsics.a(r4, r3)
            if (r4 == 0) goto L6a
            goto L7b
        L6a:
            r16 = 1
            long r16 = r11 - r16
            long r11 = r11 & r16
            goto L51
        L71:
            long r11 = ~r9
            r4 = 6
            long r11 = r11 << r4
            long r9 = r9 & r11
            long r9 = r9 & r13
            int r4 = (r9 > r16 ? 1 : (r9 == r16 ? 0 : -1))
            if (r4 == 0) goto L96
            r15 = -1
        L7b:
            if (r15 < 0) goto L94
            androidx.collection.MutableScatterMap r1 = r0.f1345c
            java.lang.Object[] r1 = r1.f1386c
            r1 = r1[r15]
            java.lang.Object r2 = r19.getValue()
            boolean r1 = kotlin.jvm.internal.Intrinsics.a(r1, r2)
            if (r1 == 0) goto L94
            androidx.collection.MutableScatterMap r0 = r0.f1345c
            r0.p(r15)
            r0 = 1
            return r0
        L94:
            r4 = 0
            return r4
        L96:
            r4 = 0
            int r8 = r8 + 8
            int r5 = r5 + r8
            r5 = r5 & r7
            goto L26
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.MutableScatterMap$MutableMapWrapper$entries$1.g(java.util.Map$Entry):boolean");
    }

    @Override // java.util.Set, java.util.Collection
    public boolean isEmpty() {
        return this.f1345c.f();
    }

    @Override // java.util.Set, java.util.Collection, java.lang.Iterable
    public Iterator iterator() {
        return new MutableScatterMap$MutableMapWrapper$entries$1$iterator$1(this.f1345c);
    }

    @Override // java.util.Set, java.util.Collection
    public final /* bridge */ boolean remove(Object obj) {
        if (TypeIntrinsics.d(obj)) {
            return g((Map.Entry) obj);
        }
        return false;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean removeAll(Collection elements) {
        boolean z;
        Intrinsics.e(elements, "elements");
        MutableScatterMap mutableScatterMap = this.f1345c;
        long[] jArr = mutableScatterMap.f1384a;
        int length = jArr.length - 2;
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
                            Iterator it = elements.iterator();
                            while (true) {
                                if (it.hasNext()) {
                                    Map.Entry entry = (Map.Entry) it.next();
                                    if (Intrinsics.a(entry.getKey(), mutableScatterMap.f1385b[i5]) && Intrinsics.a(entry.getValue(), mutableScatterMap.f1386c[i5])) {
                                        mutableScatterMap.p(i5);
                                        z2 = true;
                                        break;
                                    }
                                }
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
        } else {
            z = false;
        }
        return z;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean retainAll(Collection elements) {
        boolean z;
        Intrinsics.e(elements, "elements");
        MutableScatterMap mutableScatterMap = this.f1345c;
        long[] jArr = mutableScatterMap.f1384a;
        int length = jArr.length - 2;
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
                            Iterator it = elements.iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    mutableScatterMap.p(i5);
                                    z2 = true;
                                    break;
                                }
                                Map.Entry entry = (Map.Entry) it.next();
                                if (!Intrinsics.a(entry.getKey(), mutableScatterMap.f1385b[i5]) || !Intrinsics.a(entry.getValue(), mutableScatterMap.f1386c[i5])) {
                                }
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
        } else {
            z = false;
        }
        return z;
    }

    @Override // java.util.Set, java.util.Collection
    public final /* bridge */ int size() {
        return f();
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
