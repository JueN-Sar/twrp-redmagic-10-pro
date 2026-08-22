package androidx.collection;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ULong;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMutableMap;

@Metadata
@SourceDebugExtension
/* loaded from: classes.dex */
public final class MutableScatterMap<K, V> extends ScatterMap<K, V> {

    /* renamed from: f, reason: collision with root package name */
    private int f1343f;

    @Metadata
    @SourceDebugExtension
    private final class MutableMapWrapper extends ScatterMap<K, V>.MapWrapper implements Map<K, V>, KMutableMap {

        /* renamed from: h, reason: collision with root package name */
        final /* synthetic */ MutableScatterMap f1344h;

        @Override // androidx.collection.ScatterMap.MapWrapper
        public Set a() {
            return new MutableScatterMap$MutableMapWrapper$entries$1(this.f1344h);
        }

        @Override // androidx.collection.ScatterMap.MapWrapper
        public Set b() {
            return new MutableScatterMap$MutableMapWrapper$keys$1(this.f1344h);
        }

        @Override // androidx.collection.ScatterMap.MapWrapper, java.util.Map
        public void clear() {
            this.f1344h.h();
        }

        @Override // androidx.collection.ScatterMap.MapWrapper
        public Collection d() {
            return new MutableScatterMap$MutableMapWrapper$values$1(this.f1344h);
        }

        @Override // androidx.collection.ScatterMap.MapWrapper, java.util.Map
        public Object put(Object obj, Object obj2) {
            return this.f1344h.n(obj, obj2);
        }

        @Override // androidx.collection.ScatterMap.MapWrapper, java.util.Map
        public void putAll(Map from) {
            Intrinsics.e(from, "from");
            for (Map.Entry<K, V> entry : from.entrySet()) {
                put(entry.getKey(), entry.getValue());
            }
        }

        @Override // androidx.collection.ScatterMap.MapWrapper, java.util.Map
        public Object remove(Object obj) {
            return this.f1344h.o(obj);
        }
    }

    public MutableScatterMap(int i2) {
        super(null);
        if (i2 < 0) {
            throw new IllegalArgumentException("Capacity must be a positive value.".toString());
        }
        m(ScatterMapKt.d(i2));
    }

    private final void g() {
        if (this.f1387d <= 8 || Long.compareUnsigned(ULong.d(ULong.d(this.f1388e) * 32), ULong.d(ULong.d(this.f1387d) * 25)) > 0) {
            q(ScatterMapKt.b(this.f1387d));
        } else {
            q(this.f1387d);
        }
    }

    private final int i(int i2) {
        int i3 = this.f1387d;
        int i4 = i2 & i3;
        int i5 = 0;
        while (true) {
            long[] jArr = this.f1384a;
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

    private final void k() {
        this.f1343f = ScatterMapKt.a(d()) - this.f1388e;
    }

    private final void l(int i2) {
        long[] jArr;
        if (i2 == 0) {
            jArr = ScatterMapKt.f1393a;
        } else {
            jArr = new long[((i2 + 15) & (-8)) >> 3];
            ArraysKt___ArraysJvmKt.o(jArr, -9187201950435737472L, 0, 0, 6, null);
        }
        this.f1384a = jArr;
        int i3 = i2 >> 3;
        long j2 = 255 << ((i2 & 7) << 3);
        jArr[i3] = (jArr[i3] & (~j2)) | j2;
        k();
    }

    private final void m(int i2) {
        int max = i2 > 0 ? Math.max(7, ScatterMapKt.c(i2)) : 0;
        this.f1387d = max;
        l(max);
        this.f1385b = new Object[max];
        this.f1386c = new Object[max];
    }

    private final void q(int i2) {
        int i3;
        long[] jArr = this.f1384a;
        Object[] objArr = this.f1385b;
        Object[] objArr2 = this.f1386c;
        int i4 = this.f1387d;
        m(i2);
        Object[] objArr3 = this.f1385b;
        Object[] objArr4 = this.f1386c;
        int i5 = 0;
        while (i5 < i4) {
            if (((jArr[i5 >> 3] >> ((i5 & 7) << 3)) & 255) < 128) {
                Object obj = objArr[i5];
                int hashCode = (obj != null ? obj.hashCode() : 0) * (-862048943);
                int i6 = hashCode ^ (hashCode << 16);
                int i7 = i(i6 >>> 7);
                long j2 = i6 & 127;
                long[] jArr2 = this.f1384a;
                int i8 = i7 >> 3;
                int i9 = (i7 & 7) << 3;
                i3 = i5;
                jArr2[i8] = (jArr2[i8] & (~(255 << i9))) | (j2 << i9);
                int i10 = this.f1387d;
                int i11 = ((i7 - 7) & i10) + (i10 & 7);
                int i12 = i11 >> 3;
                int i13 = (i11 & 7) << 3;
                jArr2[i12] = (jArr2[i12] & (~(255 << i13))) | (j2 << i13);
                objArr3[i7] = obj;
                objArr4[i7] = objArr2[i3];
            } else {
                i3 = i5;
            }
            i5 = i3 + 1;
        }
    }

    public final void h() {
        this.f1388e = 0;
        long[] jArr = this.f1384a;
        if (jArr != ScatterMapKt.f1393a) {
            ArraysKt___ArraysJvmKt.o(jArr, -9187201950435737472L, 0, 0, 6, null);
            long[] jArr2 = this.f1384a;
            int i2 = this.f1387d;
            int i3 = i2 >> 3;
            long j2 = 255 << ((i2 & 7) << 3);
            jArr2[i3] = (jArr2[i3] & (~j2)) | j2;
        }
        ArraysKt___ArraysJvmKt.m(this.f1386c, null, 0, this.f1387d);
        ArraysKt___ArraysJvmKt.m(this.f1385b, null, 0, this.f1387d);
        k();
    }

    public final int j(Object obj) {
        int hashCode = (obj != null ? obj.hashCode() : 0) * (-862048943);
        int i2 = hashCode ^ (hashCode << 16);
        int i3 = i2 >>> 7;
        int i4 = i2 & 127;
        int i5 = this.f1387d;
        int i6 = i3 & i5;
        int i7 = 0;
        while (true) {
            long[] jArr = this.f1384a;
            int i8 = i6 >> 3;
            int i9 = (i6 & 7) << 3;
            long j2 = ((jArr[i8 + 1] << (64 - i9)) & ((-i9) >> 63)) | (jArr[i8] >>> i9);
            long j3 = i4;
            int i10 = i4;
            long j4 = j2 ^ (j3 * 72340172838076673L);
            for (long j5 = (~j4) & (j4 - 72340172838076673L) & (-9187201950435737472L); j5 != 0; j5 &= j5 - 1) {
                int numberOfTrailingZeros = (i6 + (Long.numberOfTrailingZeros(j5) >> 3)) & i5;
                if (Intrinsics.a(this.f1385b[numberOfTrailingZeros], obj)) {
                    return numberOfTrailingZeros;
                }
            }
            if ((((~j2) << 6) & j2 & (-9187201950435737472L)) != 0) {
                int i11 = i(i3);
                if (this.f1343f == 0 && ((this.f1384a[i11 >> 3] >> ((i11 & 7) << 3)) & 255) != 254) {
                    g();
                    i11 = i(i3);
                }
                this.f1388e++;
                int i12 = this.f1343f;
                long[] jArr2 = this.f1384a;
                int i13 = i11 >> 3;
                long j6 = jArr2[i13];
                int i14 = (i11 & 7) << 3;
                this.f1343f = i12 - (((j6 >> i14) & 255) == 128 ? 1 : 0);
                jArr2[i13] = (j6 & (~(255 << i14))) | (j3 << i14);
                int i15 = this.f1387d;
                int i16 = ((i11 - 7) & i15) + (i15 & 7);
                int i17 = i16 >> 3;
                int i18 = (i16 & 7) << 3;
                jArr2[i17] = ((~(255 << i18)) & jArr2[i17]) | (j3 << i18);
                return ~i11;
            }
            i7 += 8;
            i6 = (i6 + i7) & i5;
            i4 = i10;
        }
    }

    public final Object n(Object obj, Object obj2) {
        int j2 = j(obj);
        if (j2 < 0) {
            j2 = ~j2;
        }
        Object[] objArr = this.f1386c;
        Object obj3 = objArr[j2];
        this.f1385b[j2] = obj;
        objArr[j2] = obj2;
        return obj3;
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0068, code lost:
    
        if (((r4 & ((~r4) << 6)) & (-9187201950435737472L)) == 0) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006a, code lost:
    
        r10 = -1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object o(java.lang.Object r14) {
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
            int r3 = r13.f1387d
            int r1 = r1 >>> 7
        L16:
            r1 = r1 & r3
            long[] r4 = r13.f1384a
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
            java.lang.Object[] r11 = r13.f1385b
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
            if (r4 == 0) goto L74
            r10 = -1
        L6b:
            if (r10 < 0) goto L72
            java.lang.Object r13 = r13.p(r10)
            return r13
        L72:
            r13 = 0
            return r13
        L74:
            int r0 = r0 + 8
            int r1 = r1 + r0
            goto L16
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.MutableScatterMap.o(java.lang.Object):java.lang.Object");
    }

    public final Object p(int i2) {
        this.f1388e--;
        long[] jArr = this.f1384a;
        int i3 = i2 >> 3;
        int i4 = (i2 & 7) << 3;
        jArr[i3] = (jArr[i3] & (~(255 << i4))) | (254 << i4);
        int i5 = this.f1387d;
        int i6 = ((i2 - 7) & i5) + (i5 & 7);
        int i7 = i6 >> 3;
        int i8 = (i6 & 7) << 3;
        jArr[i7] = (jArr[i7] & (~(255 << i8))) | (254 << i8);
        this.f1385b[i2] = null;
        Object[] objArr = this.f1386c;
        Object obj = objArr[i2];
        objArr[i2] = null;
        return obj;
    }
}
