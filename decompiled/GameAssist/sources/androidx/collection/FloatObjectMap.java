package androidx.collection;

import androidx.collection.internal.ContainerHelpersKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes.dex */
public abstract class FloatObjectMap<V> {

    /* renamed from: a, reason: collision with root package name */
    public long[] f1209a;

    /* renamed from: b, reason: collision with root package name */
    public float[] f1210b;

    /* renamed from: c, reason: collision with root package name */
    public Object[] f1211c;

    /* renamed from: d, reason: collision with root package name */
    public int f1212d;

    /* renamed from: e, reason: collision with root package name */
    public int f1213e;

    public /* synthetic */ FloatObjectMap(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public final boolean a(float f2) {
        int hashCode = Float.hashCode(f2) * (-862048943);
        int i2 = hashCode ^ (hashCode << 16);
        int i3 = i2 & 127;
        int i4 = this.f1212d;
        int i5 = (i2 >>> 7) & i4;
        int i6 = 0;
        while (true) {
            long[] jArr = this.f1209a;
            int i7 = i5 >> 3;
            int i8 = (i5 & 7) << 3;
            long j2 = ((jArr[i7 + 1] << (64 - i8)) & ((-i8) >> 63)) | (jArr[i7] >>> i8);
            long j3 = (i3 * 72340172838076673L) ^ j2;
            for (long j4 = (~j3) & (j3 - 72340172838076673L) & (-9187201950435737472L); j4 != 0; j4 &= j4 - 1) {
                int numberOfTrailingZeros = ((Long.numberOfTrailingZeros(j4) >> 3) + i5) & i4;
                if (this.f1210b[numberOfTrailingZeros] == f2) {
                    return numberOfTrailingZeros >= 0;
                }
            }
            if ((j2 & ((~j2) << 6) & (-9187201950435737472L)) != 0) {
                return false;
            }
            i6 += 8;
            i5 = (i5 + i6) & i4;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0062, code lost:
    
        if (((r4 & ((~r4) << 6)) & (-9187201950435737472L)) == 0) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0064, code lost:
    
        r10 = -1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object b(float r14) {
        /*
            r13 = this;
            int r0 = java.lang.Float.hashCode(r14)
            r1 = -862048943(0xffffffffcc9e2d51, float:-8.293031E7)
            int r0 = r0 * r1
            int r1 = r0 << 16
            r0 = r0 ^ r1
            r1 = r0 & 127(0x7f, float:1.78E-43)
            int r2 = r13.f1212d
            int r0 = r0 >>> 7
            r0 = r0 & r2
            r3 = 0
        L13:
            long[] r4 = r13.f1209a
            int r5 = r0 >> 3
            r6 = r0 & 7
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
            long r6 = (long) r1
            r8 = 72340172838076673(0x101010101010101, double:7.748604185489348E-304)
            long r6 = r6 * r8
            long r6 = r6 ^ r4
            long r8 = r6 - r8
            long r6 = ~r6
            long r6 = r6 & r8
            r8 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r6 = r6 & r8
        L3e:
            r10 = 0
            int r12 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r12 == 0) goto L5b
            int r10 = java.lang.Long.numberOfTrailingZeros(r6)
            int r10 = r10 >> 3
            int r10 = r10 + r0
            r10 = r10 & r2
            float[] r11 = r13.f1210b
            r11 = r11[r10]
            int r11 = (r11 > r14 ? 1 : (r11 == r14 ? 0 : -1))
            if (r11 != 0) goto L55
            goto L65
        L55:
            r10 = 1
            long r10 = r6 - r10
            long r6 = r6 & r10
            goto L3e
        L5b:
            long r6 = ~r4
            r12 = 6
            long r6 = r6 << r12
            long r4 = r4 & r6
            long r4 = r4 & r8
            int r4 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            if (r4 == 0) goto L6e
            r10 = -1
        L65:
            if (r10 < 0) goto L6c
            java.lang.Object[] r13 = r13.f1211c
            r13 = r13[r10]
            goto L6d
        L6c:
            r13 = 0
        L6d:
            return r13
        L6e:
            int r3 = r3 + 8
            int r0 = r0 + r3
            r0 = r0 & r2
            goto L13
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.FloatObjectMap.b(float):java.lang.Object");
    }

    public final int c() {
        return this.f1212d;
    }

    public final int d() {
        return this.f1213e;
    }

    public final boolean e() {
        return this.f1213e == 0;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FloatObjectMap)) {
            return false;
        }
        FloatObjectMap floatObjectMap = (FloatObjectMap) obj;
        if (floatObjectMap.d() != d()) {
            return false;
        }
        float[] fArr = this.f1210b;
        Object[] objArr = this.f1211c;
        long[] jArr = this.f1209a;
        int length = jArr.length - 2;
        if (length >= 0) {
            int i2 = 0;
            loop0: while (true) {
                long j2 = jArr[i2];
                if ((((~j2) << 7) & j2 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i3 = 8 - ((~(i2 - length)) >>> 31);
                    for (int i4 = 0; i4 < i3; i4++) {
                        if ((255 & j2) < 128) {
                            int i5 = (i2 << 3) + i4;
                            float f2 = fArr[i5];
                            Object obj2 = objArr[i5];
                            if (obj2 == null) {
                                if (floatObjectMap.b(f2) != null || !floatObjectMap.a(f2)) {
                                    break loop0;
                                }
                            } else if (!Intrinsics.a(obj2, floatObjectMap.b(f2))) {
                                return false;
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
            return false;
        }
        return true;
    }

    public int hashCode() {
        float[] fArr = this.f1210b;
        Object[] objArr = this.f1211c;
        long[] jArr = this.f1209a;
        int length = jArr.length - 2;
        int i2 = 0;
        if (length >= 0) {
            int i3 = 0;
            int i4 = 0;
            while (true) {
                long j2 = jArr[i3];
                if ((((~j2) << 7) & j2 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i5 = 8 - ((~(i3 - length)) >>> 31);
                    for (int i6 = 0; i6 < i5; i6++) {
                        if ((255 & j2) < 128) {
                            int i7 = (i3 << 3) + i6;
                            float f2 = fArr[i7];
                            Object obj = objArr[i7];
                            i4 += (obj != null ? obj.hashCode() : 0) ^ Float.hashCode(f2);
                        }
                        j2 >>= 8;
                    }
                    if (i5 != 8) {
                        return i4;
                    }
                }
                if (i3 == length) {
                    i2 = i4;
                    break;
                }
                i3++;
            }
        }
        return i2;
    }

    public String toString() {
        if (e()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        float[] fArr = this.f1210b;
        Object[] objArr = this.f1211c;
        long[] jArr = this.f1209a;
        int length = jArr.length - 2;
        if (length >= 0) {
            int i2 = 0;
            int i3 = 0;
            while (true) {
                long j2 = jArr[i2];
                if ((((~j2) << 7) & j2 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i4 = 8 - ((~(i2 - length)) >>> 31);
                    for (int i5 = 0; i5 < i4; i5++) {
                        if ((255 & j2) < 128) {
                            int i6 = (i2 << 3) + i5;
                            float f2 = fArr[i6];
                            Object obj = objArr[i6];
                            sb.append(f2);
                            sb.append("=");
                            if (obj == this) {
                                obj = "(this)";
                            }
                            sb.append(obj);
                            i3++;
                            if (i3 < this.f1213e) {
                                sb.append(',');
                                sb.append(' ');
                            }
                        }
                        j2 >>= 8;
                    }
                    if (i4 != 8) {
                        break;
                    }
                }
                if (i2 == length) {
                    break;
                }
                i2++;
            }
        }
        sb.append('}');
        String sb2 = sb.toString();
        Intrinsics.d(sb2, "s.append('}').toString()");
        return sb2;
    }

    private FloatObjectMap() {
        this.f1209a = ScatterMapKt.f1393a;
        this.f1210b = FloatSetKt.a();
        this.f1211c = ContainerHelpersKt.f1415c;
    }
}
