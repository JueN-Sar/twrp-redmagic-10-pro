package androidx.collection;

import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes.dex */
public abstract class LongLongMap {

    /* renamed from: a, reason: collision with root package name */
    public long[] f1273a;

    /* renamed from: b, reason: collision with root package name */
    public long[] f1274b;

    /* renamed from: c, reason: collision with root package name */
    public long[] f1275c;

    /* renamed from: d, reason: collision with root package name */
    public int f1276d;

    /* renamed from: e, reason: collision with root package name */
    public int f1277e;

    public /* synthetic */ LongLongMap(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public final int a(long j2) {
        int hashCode = Long.hashCode(j2) * (-862048943);
        int i2 = hashCode ^ (hashCode << 16);
        int i3 = i2 & 127;
        int i4 = this.f1276d;
        int i5 = (i2 >>> 7) & i4;
        int i6 = 0;
        while (true) {
            long[] jArr = this.f1273a;
            int i7 = i5 >> 3;
            int i8 = (i5 & 7) << 3;
            long j3 = ((jArr[i7 + 1] << (64 - i8)) & ((-i8) >> 63)) | (jArr[i7] >>> i8);
            long j4 = (i3 * 72340172838076673L) ^ j3;
            for (long j5 = (~j4) & (j4 - 72340172838076673L) & (-9187201950435737472L); j5 != 0; j5 &= j5 - 1) {
                int numberOfTrailingZeros = ((Long.numberOfTrailingZeros(j5) >> 3) + i5) & i4;
                if (this.f1274b[numberOfTrailingZeros] == j2) {
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

    public final long b(long j2) {
        int a2 = a(j2);
        if (a2 >= 0) {
            return this.f1275c[a2];
        }
        throw new NoSuchElementException("Cannot find value for key " + j2);
    }

    public final int c() {
        return this.f1276d;
    }

    public final int d() {
        return this.f1277e;
    }

    public final boolean e() {
        return this.f1277e == 0;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LongLongMap)) {
            return false;
        }
        LongLongMap longLongMap = (LongLongMap) obj;
        if (longLongMap.d() != d()) {
            return false;
        }
        long[] jArr = this.f1274b;
        long[] jArr2 = this.f1275c;
        long[] jArr3 = this.f1273a;
        int length = jArr3.length - 2;
        if (length >= 0) {
            int i2 = 0;
            while (true) {
                long j2 = jArr3[i2];
                if ((((~j2) << 7) & j2 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i3 = 8 - ((~(i2 - length)) >>> 31);
                    for (int i4 = 0; i4 < i3; i4++) {
                        if ((255 & j2) < 128) {
                            int i5 = (i2 << 3) + i4;
                            if (jArr2[i5] != longLongMap.b(jArr[i5])) {
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
        long[] jArr = this.f1274b;
        long[] jArr2 = this.f1275c;
        long[] jArr3 = this.f1273a;
        int length = jArr3.length - 2;
        int i2 = 0;
        if (length >= 0) {
            int i3 = 0;
            int i4 = 0;
            while (true) {
                long j2 = jArr3[i3];
                if ((((~j2) << 7) & j2 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i5 = 8 - ((~(i3 - length)) >>> 31);
                    for (int i6 = 0; i6 < i5; i6++) {
                        if ((255 & j2) < 128) {
                            int i7 = (i3 << 3) + i6;
                            i4 += Long.hashCode(jArr[i7]) ^ Long.hashCode(jArr2[i7]);
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
        int i4;
        int i5;
        if (e()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        long[] jArr = this.f1274b;
        long[] jArr2 = this.f1275c;
        long[] jArr3 = this.f1273a;
        int length = jArr3.length - 2;
        if (length >= 0) {
            int i6 = 0;
            int i7 = 0;
            while (true) {
                long j2 = jArr3[i6];
                if ((((~j2) << 7) & j2 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i8 = 8;
                    int i9 = 8 - ((~(i6 - length)) >>> 31);
                    int i10 = 0;
                    while (i10 < i9) {
                        if ((255 & j2) < 128) {
                            int i11 = (i6 << 3) + i10;
                            i3 = i6;
                            long j3 = jArr[i11];
                            i4 = i10;
                            long j4 = jArr2[i11];
                            sb.append(j3);
                            sb.append("=");
                            sb.append(j4);
                            i7++;
                            if (i7 < this.f1277e) {
                                sb.append(',');
                                sb.append(' ');
                            }
                            i5 = 8;
                        } else {
                            i3 = i6;
                            i4 = i10;
                            i5 = i8;
                        }
                        j2 >>= i5;
                        i10 = i4 + 1;
                        i8 = i5;
                        i6 = i3;
                    }
                    int i12 = i6;
                    if (i9 != i8) {
                        break;
                    }
                    i2 = i12;
                } else {
                    i2 = i6;
                }
                if (i2 == length) {
                    break;
                }
                i6 = i2 + 1;
            }
        }
        sb.append('}');
        String sb2 = sb.toString();
        Intrinsics.d(sb2, "s.append('}').toString()");
        return sb2;
    }

    private LongLongMap() {
        this.f1273a = ScatterMapKt.f1393a;
        this.f1274b = LongSetKt.a();
        this.f1275c = LongSetKt.a();
    }
}
