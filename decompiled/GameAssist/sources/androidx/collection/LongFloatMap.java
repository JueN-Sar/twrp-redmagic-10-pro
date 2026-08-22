package androidx.collection;

import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes.dex */
public abstract class LongFloatMap {

    /* renamed from: a, reason: collision with root package name */
    public long[] f1258a;

    /* renamed from: b, reason: collision with root package name */
    public long[] f1259b;

    /* renamed from: c, reason: collision with root package name */
    public float[] f1260c;

    /* renamed from: d, reason: collision with root package name */
    public int f1261d;

    /* renamed from: e, reason: collision with root package name */
    public int f1262e;

    public /* synthetic */ LongFloatMap(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public final int a(long j2) {
        int hashCode = Long.hashCode(j2) * (-862048943);
        int i2 = hashCode ^ (hashCode << 16);
        int i3 = i2 & 127;
        int i4 = this.f1261d;
        int i5 = (i2 >>> 7) & i4;
        int i6 = 0;
        while (true) {
            long[] jArr = this.f1258a;
            int i7 = i5 >> 3;
            int i8 = (i5 & 7) << 3;
            long j3 = ((jArr[i7 + 1] << (64 - i8)) & ((-i8) >> 63)) | (jArr[i7] >>> i8);
            long j4 = (i3 * 72340172838076673L) ^ j3;
            for (long j5 = (~j4) & (j4 - 72340172838076673L) & (-9187201950435737472L); j5 != 0; j5 &= j5 - 1) {
                int numberOfTrailingZeros = ((Long.numberOfTrailingZeros(j5) >> 3) + i5) & i4;
                if (this.f1259b[numberOfTrailingZeros] == j2) {
                    return numberOfTrailingZeros;
                }
            }
            if ((j3 & ((~j3) << 6) & (-9187201950435737472L)) != 0) {
                return -1;
            }
            i6 += 8;
            i5 = (i5 + i6) & i4;
        }
    }

    public final float b(long j2) {
        int a2 = a(j2);
        if (a2 >= 0) {
            return this.f1260c[a2];
        }
        throw new NoSuchElementException("Cannot find value for key " + j2);
    }

    public final int c() {
        return this.f1261d;
    }

    public final int d() {
        return this.f1262e;
    }

    public final boolean e() {
        return this.f1262e == 0;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LongFloatMap)) {
            return false;
        }
        LongFloatMap longFloatMap = (LongFloatMap) obj;
        if (longFloatMap.d() != d()) {
            return false;
        }
        long[] jArr = this.f1259b;
        float[] fArr = this.f1260c;
        long[] jArr2 = this.f1258a;
        int length = jArr2.length - 2;
        if (length >= 0) {
            int i2 = 0;
            while (true) {
                long j2 = jArr2[i2];
                if ((((~j2) << 7) & j2 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i3 = 8 - ((~(i2 - length)) >>> 31);
                    for (int i4 = 0; i4 < i3; i4++) {
                        if ((255 & j2) < 128) {
                            int i5 = (i2 << 3) + i4;
                            if (fArr[i5] != longFloatMap.b(jArr[i5])) {
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
        }
        return true;
    }

    public int hashCode() {
        long[] jArr = this.f1259b;
        float[] fArr = this.f1260c;
        long[] jArr2 = this.f1258a;
        int length = jArr2.length - 2;
        int i2 = 0;
        if (length >= 0) {
            int i3 = 0;
            int i4 = 0;
            while (true) {
                long j2 = jArr2[i3];
                if ((((~j2) << 7) & j2 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i5 = 8 - ((~(i3 - length)) >>> 31);
                    for (int i6 = 0; i6 < i5; i6++) {
                        if ((255 & j2) < 128) {
                            int i7 = (i3 << 3) + i6;
                            long j3 = jArr[i7];
                            i4 += Float.hashCode(fArr[i7]) ^ Long.hashCode(j3);
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
        int i2;
        int i3;
        if (e()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        long[] jArr = this.f1259b;
        float[] fArr = this.f1260c;
        long[] jArr2 = this.f1258a;
        int length = jArr2.length - 2;
        if (length >= 0) {
            int i4 = 0;
            int i5 = 0;
            while (true) {
                long j2 = jArr2[i4];
                if ((((~j2) << 7) & j2 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i6 = 8 - ((~(i4 - length)) >>> 31);
                    int i7 = 0;
                    while (i7 < i6) {
                        if ((255 & j2) < 128) {
                            int i8 = (i4 << 3) + i7;
                            i3 = i4;
                            long j3 = jArr[i8];
                            float f2 = fArr[i8];
                            sb.append(j3);
                            sb.append("=");
                            sb.append(f2);
                            i5++;
                            if (i5 < this.f1262e) {
                                sb.append(',');
                                sb.append(' ');
                            }
                        } else {
                            i3 = i4;
                        }
                        j2 >>= 8;
                        i7++;
                        i4 = i3;
                    }
                    int i9 = i4;
                    if (i6 != 8) {
                        break;
                    }
                    i2 = i9;
                } else {
                    i2 = i4;
                }
                if (i2 == length) {
                    break;
                }
                i4 = i2 + 1;
            }
        }
        sb.append('}');
        String sb2 = sb.toString();
        Intrinsics.d(sb2, "s.append('}').toString()");
        return sb2;
    }

    private LongFloatMap() {
        this.f1258a = ScatterMapKt.f1393a;
        this.f1259b = LongSetKt.a();
        this.f1260c = FloatSetKt.a();
    }
}
