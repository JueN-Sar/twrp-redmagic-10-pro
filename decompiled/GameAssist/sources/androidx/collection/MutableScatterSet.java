package androidx.collection;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ULong;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMutableSet;

@Metadata
@SourceDebugExtension
/* loaded from: classes.dex */
public final class MutableScatterSet<E> extends ScatterSet<E> {

    /* renamed from: e, reason: collision with root package name */
    private int f1357e;

    @Metadata
    @SourceDebugExtension
    private final class MutableSetWrapper extends ScatterSet<E>.SetWrapper implements Set<E>, KMutableSet {

        /* renamed from: h, reason: collision with root package name */
        final /* synthetic */ MutableScatterSet f1358h;

        @Override // androidx.collection.ScatterSet.SetWrapper, java.util.Set, java.util.Collection
        public boolean add(Object obj) {
            return this.f1358h.g(obj);
        }

        @Override // androidx.collection.ScatterSet.SetWrapper, java.util.Set, java.util.Collection
        public boolean addAll(Collection elements) {
            Intrinsics.e(elements, "elements");
            return this.f1358h.h(elements);
        }

        @Override // androidx.collection.ScatterSet.SetWrapper, java.util.Set, java.util.Collection
        public void clear() {
            this.f1358h.j();
        }

        @Override // androidx.collection.ScatterSet.SetWrapper, java.util.Set, java.util.Collection, java.lang.Iterable
        public Iterator iterator() {
            return new MutableScatterSet$MutableSetWrapper$iterator$1(this.f1358h);
        }

        @Override // androidx.collection.ScatterSet.SetWrapper, java.util.Set, java.util.Collection
        public boolean remove(Object obj) {
            return this.f1358h.s(obj);
        }

        @Override // androidx.collection.ScatterSet.SetWrapper, java.util.Set, java.util.Collection
        public boolean removeAll(Collection elements) {
            Intrinsics.e(elements, "elements");
            int c2 = this.f1358h.c();
            Iterator<E> it = elements.iterator();
            while (it.hasNext()) {
                this.f1358h.p(it.next());
            }
            return c2 != this.f1358h.c();
        }

        @Override // androidx.collection.ScatterSet.SetWrapper, java.util.Set, java.util.Collection
        public boolean retainAll(Collection elements) {
            Intrinsics.e(elements, "elements");
            MutableScatterSet mutableScatterSet = this.f1358h;
            long[] jArr = mutableScatterSet.f1395a;
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
                                if (!elements.contains(mutableScatterSet.f1396b[i5])) {
                                    mutableScatterSet.u(i5);
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
    }

    public MutableScatterSet(int i2) {
        super(null);
        if (i2 < 0) {
            throw new IllegalArgumentException("Capacity must be a positive value.".toString());
        }
        o(ScatterMapKt.d(i2));
    }

    private final void i() {
        if (this.f1397c <= 8 || Long.compareUnsigned(ULong.d(ULong.d(this.f1398d) * 32), ULong.d(ULong.d(this.f1397c) * 25)) > 0) {
            v(ScatterMapKt.b(this.f1397c));
        } else {
            t();
        }
    }

    private final int k(Object obj) {
        int hashCode = (obj != null ? obj.hashCode() : 0) * (-862048943);
        int i2 = hashCode ^ (hashCode << 16);
        int i3 = i2 >>> 7;
        int i4 = i2 & 127;
        int i5 = this.f1397c;
        int i6 = i3 & i5;
        int i7 = 0;
        while (true) {
            long[] jArr = this.f1395a;
            int i8 = i6 >> 3;
            int i9 = (i6 & 7) << 3;
            long j2 = ((jArr[i8 + 1] << (64 - i9)) & ((-i9) >> 63)) | (jArr[i8] >>> i9);
            long j3 = i4;
            int i10 = i4;
            long j4 = j2 ^ (j3 * 72340172838076673L);
            for (long j5 = (~j4) & (j4 - 72340172838076673L) & (-9187201950435737472L); j5 != 0; j5 &= j5 - 1) {
                int numberOfTrailingZeros = (i6 + (Long.numberOfTrailingZeros(j5) >> 3)) & i5;
                if (Intrinsics.a(this.f1396b[numberOfTrailingZeros], obj)) {
                    return numberOfTrailingZeros;
                }
            }
            if ((((~j2) << 6) & j2 & (-9187201950435737472L)) != 0) {
                int l2 = l(i3);
                if (this.f1357e == 0 && ((this.f1395a[l2 >> 3] >> ((l2 & 7) << 3)) & 255) != 254) {
                    i();
                    l2 = l(i3);
                }
                this.f1398d++;
                int i11 = this.f1357e;
                long[] jArr2 = this.f1395a;
                int i12 = l2 >> 3;
                long j6 = jArr2[i12];
                int i13 = (l2 & 7) << 3;
                this.f1357e = i11 - (((j6 >> i13) & 255) == 128 ? 1 : 0);
                jArr2[i12] = (j6 & (~(255 << i13))) | (j3 << i13);
                int i14 = this.f1397c;
                int i15 = ((l2 - 7) & i14) + (i14 & 7);
                int i16 = i15 >> 3;
                int i17 = (i15 & 7) << 3;
                jArr2[i16] = ((~(255 << i17)) & jArr2[i16]) | (j3 << i17);
                return l2;
            }
            i7 += 8;
            i6 = (i6 + i7) & i5;
            i4 = i10;
        }
    }

    private final int l(int i2) {
        int i3 = this.f1397c;
        int i4 = i2 & i3;
        int i5 = 0;
        while (true) {
            long[] jArr = this.f1395a;
            int i6 = i4 >> 3;
            int i7 = (i4 & 7) << 3;
            long j2 = ((jArr[i6 + 1] << (64 - i7)) & ((-i7) >> 63)) | (jArr[i6] >>> i7);
            long j3 = j2 & ((~j2) << 7) & (-9187201950435737472L);
            if (j3 != 0) {
                return (i4 + (Long.numberOfTrailingZeros(j3) >> 3)) & i3;
            }
            i5 += 8;
            i4 = (i4 + i5) & i3;
        }
    }

    private final void m() {
        this.f1357e = ScatterMapKt.a(b()) - this.f1398d;
    }

    private final void n(int i2) {
        long[] jArr;
        if (i2 == 0) {
            jArr = ScatterMapKt.f1393a;
        } else {
            jArr = new long[((i2 + 15) & (-8)) >> 3];
            ArraysKt___ArraysJvmKt.o(jArr, -9187201950435737472L, 0, 0, 6, null);
        }
        this.f1395a = jArr;
        int i3 = i2 >> 3;
        long j2 = 255 << ((i2 & 7) << 3);
        jArr[i3] = (jArr[i3] & (~j2)) | j2;
        m();
    }

    private final void o(int i2) {
        int max = i2 > 0 ? Math.max(7, ScatterMapKt.c(i2)) : 0;
        this.f1397c = max;
        n(max);
        this.f1396b = new Object[max];
    }

    private final void t() {
        long[] jArr = this.f1395a;
        int i2 = this.f1397c;
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            int i5 = i4 >> 3;
            int i6 = (i4 & 7) << 3;
            if (((jArr[i5] >> i6) & 255) == 254) {
                long[] jArr2 = this.f1395a;
                jArr2[i5] = (128 << i6) | (jArr2[i5] & (~(255 << i6)));
                int i7 = this.f1397c;
                int i8 = ((i4 - 7) & i7) + (i7 & 7);
                int i9 = i8 >> 3;
                int i10 = (i8 & 7) << 3;
                jArr2[i9] = ((~(255 << i10)) & jArr2[i9]) | (128 << i10);
                i3++;
            }
        }
        this.f1357e += i3;
    }

    private final void v(int i2) {
        int i3;
        long[] jArr = this.f1395a;
        Object[] objArr = this.f1396b;
        int i4 = this.f1397c;
        o(i2);
        Object[] objArr2 = this.f1396b;
        int i5 = 0;
        while (i5 < i4) {
            if (((jArr[i5 >> 3] >> ((i5 & 7) << 3)) & 255) < 128) {
                Object obj = objArr[i5];
                int hashCode = (obj != null ? obj.hashCode() : 0) * (-862048943);
                int i6 = hashCode ^ (hashCode << 16);
                int l2 = l(i6 >>> 7);
                long j2 = i6 & 127;
                long[] jArr2 = this.f1395a;
                int i7 = l2 >> 3;
                int i8 = (l2 & 7) << 3;
                i3 = i5;
                jArr2[i7] = (jArr2[i7] & (~(255 << i8))) | (j2 << i8);
                int i9 = this.f1397c;
                int i10 = ((l2 - 7) & i9) + (i9 & 7);
                int i11 = i10 >> 3;
                int i12 = (i10 & 7) << 3;
                jArr2[i11] = ((~(255 << i12)) & jArr2[i11]) | (j2 << i12);
                objArr2[l2] = obj;
            } else {
                i3 = i5;
            }
            i5 = i3 + 1;
        }
    }

    public final boolean g(Object obj) {
        int c2 = c();
        this.f1396b[k(obj)] = obj;
        return c() != c2;
    }

    public final boolean h(Iterable elements) {
        Intrinsics.e(elements, "elements");
        int c2 = c();
        q(elements);
        return c2 != c();
    }

    public final void j() {
        this.f1398d = 0;
        long[] jArr = this.f1395a;
        if (jArr != ScatterMapKt.f1393a) {
            ArraysKt___ArraysJvmKt.o(jArr, -9187201950435737472L, 0, 0, 6, null);
            long[] jArr2 = this.f1395a;
            int i2 = this.f1397c;
            int i3 = i2 >> 3;
            long j2 = 255 << ((i2 & 7) << 3);
            jArr2[i3] = (jArr2[i3] & (~j2)) | j2;
        }
        ArraysKt___ArraysJvmKt.m(this.f1396b, null, 0, this.f1397c);
        m();
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0068, code lost:
    
        if (((r4 & ((~r4) << 6)) & (-9187201950435737472L)) == 0) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006a, code lost:
    
        r10 = -1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void p(java.lang.Object r14) {
        /*
            r13 = this;
            r0 = 0
            if (r14 == 0) goto L8
            int r1 = r14.hashCode()
            goto L9
        L8:
            r1 = r0
        L9:
            r2 = -862048943(0xffffffffcc9e2d51, float:-8.293031E7)
            int r1 = r1 * r2
            int r2 = r1 << 16
            r1 = r1 ^ r2
            r2 = r1 & 127(0x7f, float:1.78E-43)
            int r3 = r13.f1397c
            int r1 = r1 >>> 7
        L16:
            r1 = r1 & r3
            long[] r4 = r13.f1395a
            int r5 = r1 >> 3
            r6 = r1 & 7
            int r6 = r6 << 3
            r7 = r4[r5]
            long r7 = r7 >>> r6
            int r5 = r5 + 1
            r4 = r4[r5]
            int r9 = 64 - r6
            long r4 = r4 << r9
            long r9 = (long) r6
            long r9 = -r9
            r6 = 63
            long r9 = r9 >> r6
            long r4 = r4 & r9
            long r4 = r4 | r7
            long r6 = (long) r2
            r8 = 72340172838076673(0x101010101010101, double:7.748604185489348E-304)
            long r6 = r6 * r8
            long r6 = r6 ^ r4
            long r8 = r6 - r8
            long r6 = ~r6
            long r6 = r6 & r8
            r8 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r6 = r6 & r8
        L42:
            r10 = 0
            int r12 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r12 == 0) goto L61
            int r10 = java.lang.Long.numberOfTrailingZeros(r6)
            int r10 = r10 >> 3
            int r10 = r10 + r1
            r10 = r10 & r3
            java.lang.Object[] r11 = r13.f1396b
            r11 = r11[r10]
            boolean r11 = kotlin.jvm.internal.Intrinsics.a(r11, r14)
            if (r11 == 0) goto L5b
            goto L6b
        L5b:
            r10 = 1
            long r10 = r6 - r10
            long r6 = r6 & r10
            goto L42
        L61:
            long r6 = ~r4
            r12 = 6
            long r6 = r6 << r12
            long r4 = r4 & r6
            long r4 = r4 & r8
            int r4 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            if (r4 == 0) goto L71
            r10 = -1
        L6b:
            if (r10 < 0) goto L70
            r13.u(r10)
        L70:
            return
        L71:
            int r0 = r0 + 8
            int r1 = r1 + r0
            goto L16
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.MutableScatterSet.p(java.lang.Object):void");
    }

    public final void q(Iterable elements) {
        Intrinsics.e(elements, "elements");
        Iterator it = elements.iterator();
        while (it.hasNext()) {
            r(it.next());
        }
    }

    public final void r(Object obj) {
        this.f1396b[k(obj)] = obj;
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x006d, code lost:
    
        if (((r7 & ((~r7) << 6)) & (-9187201950435737472L)) == 0) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x006f, code lost:
    
        r11 = -1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean s(java.lang.Object r18) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r2 = 0
            if (r1 == 0) goto Lc
            int r3 = r18.hashCode()
            goto Ld
        Lc:
            r3 = r2
        Ld:
            r4 = -862048943(0xffffffffcc9e2d51, float:-8.293031E7)
            int r3 = r3 * r4
            int r4 = r3 << 16
            r3 = r3 ^ r4
            r4 = r3 & 127(0x7f, float:1.78E-43)
            int r5 = r0.f1397c
            int r3 = r3 >>> 7
            r3 = r3 & r5
            r6 = r2
        L1c:
            long[] r7 = r0.f1395a
            int r8 = r3 >> 3
            r9 = r3 & 7
            int r9 = r9 << 3
            r10 = r7[r8]
            long r10 = r10 >>> r9
            r12 = 1
            int r8 = r8 + r12
            r7 = r7[r8]
            int r13 = 64 - r9
            long r7 = r7 << r13
            long r13 = (long) r9
            long r13 = -r13
            r9 = 63
            long r13 = r13 >> r9
            long r7 = r7 & r13
            long r7 = r7 | r10
            long r9 = (long) r4
            r13 = 72340172838076673(0x101010101010101, double:7.748604185489348E-304)
            long r9 = r9 * r13
            long r9 = r9 ^ r7
            long r13 = r9 - r13
            long r9 = ~r9
            long r9 = r9 & r13
            r13 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r9 = r9 & r13
        L47:
            r15 = 0
            int r11 = (r9 > r15 ? 1 : (r9 == r15 ? 0 : -1))
            if (r11 == 0) goto L66
            int r11 = java.lang.Long.numberOfTrailingZeros(r9)
            int r11 = r11 >> 3
            int r11 = r11 + r3
            r11 = r11 & r5
            java.lang.Object[] r15 = r0.f1396b
            r15 = r15[r11]
            boolean r15 = kotlin.jvm.internal.Intrinsics.a(r15, r1)
            if (r15 == 0) goto L60
            goto L70
        L60:
            r15 = 1
            long r15 = r9 - r15
            long r9 = r9 & r15
            goto L47
        L66:
            long r9 = ~r7
            r11 = 6
            long r9 = r9 << r11
            long r7 = r7 & r9
            long r7 = r7 & r13
            int r7 = (r7 > r15 ? 1 : (r7 == r15 ? 0 : -1))
            if (r7 == 0) goto L79
            r11 = -1
        L70:
            if (r11 < 0) goto L73
            r2 = r12
        L73:
            if (r2 == 0) goto L78
            r0.u(r11)
        L78:
            return r2
        L79:
            int r6 = r6 + 8
            int r3 = r3 + r6
            r3 = r3 & r5
            goto L1c
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.MutableScatterSet.s(java.lang.Object):boolean");
    }

    public final void u(int i2) {
        this.f1398d--;
        long[] jArr = this.f1395a;
        int i3 = i2 >> 3;
        int i4 = (i2 & 7) << 3;
        jArr[i3] = (jArr[i3] & (~(255 << i4))) | (254 << i4);
        int i5 = this.f1397c;
        int i6 = ((i2 - 7) & i5) + (i5 & 7);
        int i7 = i6 >> 3;
        int i8 = (i6 & 7) << 3;
        jArr[i7] = (jArr[i7] & (~(255 << i8))) | (254 << i8);
        this.f1396b[i2] = null;
    }
}
