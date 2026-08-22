package androidx.collection;

import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes.dex */
public abstract class FloatLongMap {

    /* renamed from: a, reason: collision with root package name */
    public long[] f1203a;

    /* renamed from: b, reason: collision with root package name */
    public float[] f1204b;

    /* renamed from: c, reason: collision with root package name */
    public long[] f1205c;

    /* renamed from: d, reason: collision with root package name */
    public int f1206d;

    /* renamed from: e, reason: collision with root package name */
    public int f1207e;

    public /* synthetic */ FloatLongMap(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public final int a(float f2) {
        int hashCode = Float.hashCode(f2) * (-862048943);
        int i2 = hashCode ^ (hashCode << 16);
        int i3 = i2 & 127;
        int i4 = this.f1206d;
        int i5 = (i2 >>> 7) & i4;
        int i6 = 0;
        while (true) {
            long[] jArr = this.f1203a;
            int i7 = i5 >> 3;
            int i8 = (i5 & 7) << 3;
            long j2 = ((jArr[i7 + 1] << (64 - i8)) & ((-i8) >> 63)) | (jArr[i7] >>> i8);
            long j3 = (i3 * 72340172838076673L) ^ j2;
            for (long j4 = (~j3) & (j3 - 72340172838076673L) & (-9187201950435737472L); j4 != 0; j4 &= j4 - 1) {
                int numberOfTrailingZeros = ((Long.numberOfTrailingZeros(j4) >> 3) + i5) & i4;
                if (this.f1204b[numberOfTrailingZeros] == f2) {
                    return numberOfTrailingZeros;
                }
            }
            if ((j2 & ((~j2) << 6) & (-9187201950435737472L)) != 0) {
                return -1;
            }
            i6 += 8;
            i5 = (i5 + i6) & i4;
        }
    }

    public final long b(float f2) {
        int a2 = a(f2);
        if (a2 >= 0) {
            return this.f1205c[a2];
        }
        throw new NoSuchElementException("Cannot find value for key " + f2);
    }

    public final int c() {
        return this.f1206d;
    }

    public final int d() {
        return this.f1207e;
    }

    public final boolean e() {
        return this.f1207e == 0;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FloatLongMap)) {
            return false;
        }
        FloatLongMap floatLongMap = (FloatLongMap) obj;
        if (floatLongMap.d() != d()) {
            return false;
        }
        float[] fArr = this.f1204b;
        long[] jArr = this.f1205c;
        long[] jArr2 = this.f1203a;
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
                            if (jArr[i5] != floatLongMap.b(fArr[i5])) {
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
        float[] fArr = this.f1204b;
        long[] jArr = this.f1205c;
        long[] jArr2 = this.f1203a;
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
                            i4 += Float.hashCode(fArr[i7]) ^ Long.hashCode(jArr[i7]);
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
        float[] fArr = this.f1204b;
        long[] jArr = this.f1205c;
        long[] jArr2 = this.f1203a;
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
                            float f2 = fArr[i8];
                            i3 = i4;
                            long j3 = jArr[i8];
                            sb.append(f2);
                            sb.append("=");
                            sb.append(j3);
                            i5++;
                            if (i5 < this.f1207e) {
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

    private FloatLongMap() {
        this.f1203a = ScatterMapKt.f1393a;
        this.f1204b = FloatSetKt.a();
        this.f1205c = LongSetKt.a();
    }
}
