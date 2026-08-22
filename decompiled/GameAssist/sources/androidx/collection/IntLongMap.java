package androidx.collection;

import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes.dex */
public abstract class IntLongMap {

    /* renamed from: a, reason: collision with root package name */
    public long[] f1240a;

    /* renamed from: b, reason: collision with root package name */
    public int[] f1241b;

    /* renamed from: c, reason: collision with root package name */
    public long[] f1242c;

    /* renamed from: d, reason: collision with root package name */
    public int f1243d;

    /* renamed from: e, reason: collision with root package name */
    public int f1244e;

    public /* synthetic */ IntLongMap(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public final int a(int i2) {
        int hashCode = Integer.hashCode(i2) * (-862048943);
        int i3 = hashCode ^ (hashCode << 16);
        int i4 = i3 & 127;
        int i5 = this.f1243d;
        int i6 = (i3 >>> 7) & i5;
        int i7 = 0;
        while (true) {
            long[] jArr = this.f1240a;
            int i8 = i6 >> 3;
            int i9 = (i6 & 7) << 3;
            long j2 = ((jArr[i8 + 1] << (64 - i9)) & ((-i9) >> 63)) | (jArr[i8] >>> i9);
            long j3 = (i4 * 72340172838076673L) ^ j2;
            for (long j4 = (~j3) & (j3 - 72340172838076673L) & (-9187201950435737472L); j4 != 0; j4 &= j4 - 1) {
                int numberOfTrailingZeros = ((Long.numberOfTrailingZeros(j4) >> 3) + i6) & i5;
                if (this.f1241b[numberOfTrailingZeros] == i2) {
                    return numberOfTrailingZeros;
                }
            }
            if ((j2 & ((~j2) << 6) & (-9187201950435737472L)) != 0) {
                return -1;
            }
            i7 += 8;
            i6 = (i6 + i7) & i5;
        }
    }

    public final long b(int i2) {
        int a2 = a(i2);
        if (a2 >= 0) {
            return this.f1242c[a2];
        }
        throw new NoSuchElementException("Cannot find value for key " + i2);
    }

    public final int c() {
        return this.f1243d;
    }

    public final int d() {
        return this.f1244e;
    }

    public final boolean e() {
        return this.f1244e == 0;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof IntLongMap)) {
            return false;
        }
        IntLongMap intLongMap = (IntLongMap) obj;
        if (intLongMap.d() != d()) {
            return false;
        }
        int[] iArr = this.f1241b;
        long[] jArr = this.f1242c;
        long[] jArr2 = this.f1240a;
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
                            if (jArr[i5] != intLongMap.b(iArr[i5])) {
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
        int[] iArr = this.f1241b;
        long[] jArr = this.f1242c;
        long[] jArr2 = this.f1240a;
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
                            i4 += Integer.hashCode(iArr[i7]) ^ Long.hashCode(jArr[i7]);
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
        int[] iArr = this.f1241b;
        long[] jArr = this.f1242c;
        long[] jArr2 = this.f1240a;
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
                            int i9 = iArr[i8];
                            i3 = i4;
                            long j3 = jArr[i8];
                            sb.append(i9);
                            sb.append("=");
                            sb.append(j3);
                            i5++;
                            if (i5 < this.f1244e) {
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
                    int i10 = i4;
                    if (i6 != 8) {
                        break;
                    }
                    i2 = i10;
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

    private IntLongMap() {
        this.f1240a = ScatterMapKt.f1393a;
        this.f1241b = IntSetKt.a();
        this.f1242c = LongSetKt.a();
    }
}
