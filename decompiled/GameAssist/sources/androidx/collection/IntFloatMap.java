package androidx.collection;

import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes.dex */
public abstract class IntFloatMap {

    /* renamed from: a, reason: collision with root package name */
    public long[] f1224a;

    /* renamed from: b, reason: collision with root package name */
    public int[] f1225b;

    /* renamed from: c, reason: collision with root package name */
    public float[] f1226c;

    /* renamed from: d, reason: collision with root package name */
    public int f1227d;

    /* renamed from: e, reason: collision with root package name */
    public int f1228e;

    public /* synthetic */ IntFloatMap(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public final int a(int i2) {
        int hashCode = Integer.hashCode(i2) * (-862048943);
        int i3 = hashCode ^ (hashCode << 16);
        int i4 = i3 & 127;
        int i5 = this.f1227d;
        int i6 = (i3 >>> 7) & i5;
        int i7 = 0;
        while (true) {
            long[] jArr = this.f1224a;
            int i8 = i6 >> 3;
            int i9 = (i6 & 7) << 3;
            long j2 = ((jArr[i8 + 1] << (64 - i9)) & ((-i9) >> 63)) | (jArr[i8] >>> i9);
            long j3 = (i4 * 72340172838076673L) ^ j2;
            for (long j4 = (~j3) & (j3 - 72340172838076673L) & (-9187201950435737472L); j4 != 0; j4 &= j4 - 1) {
                int numberOfTrailingZeros = ((Long.numberOfTrailingZeros(j4) >> 3) + i6) & i5;
                if (this.f1225b[numberOfTrailingZeros] == i2) {
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

    public final float b(int i2) {
        int a2 = a(i2);
        if (a2 >= 0) {
            return this.f1226c[a2];
        }
        throw new NoSuchElementException("Cannot find value for key " + i2);
    }

    public final int c() {
        return this.f1227d;
    }

    public final int d() {
        return this.f1228e;
    }

    public final boolean e() {
        return this.f1228e == 0;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof IntFloatMap)) {
            return false;
        }
        IntFloatMap intFloatMap = (IntFloatMap) obj;
        if (intFloatMap.d() != d()) {
            return false;
        }
        int[] iArr = this.f1225b;
        float[] fArr = this.f1226c;
        long[] jArr = this.f1224a;
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
                            if (fArr[i5] != intFloatMap.b(iArr[i5])) {
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
        int[] iArr = this.f1225b;
        float[] fArr = this.f1226c;
        long[] jArr = this.f1224a;
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
                            int i8 = iArr[i7];
                            i4 += Float.hashCode(fArr[i7]) ^ Integer.hashCode(i8);
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
        int[] iArr = this.f1225b;
        float[] fArr = this.f1226c;
        long[] jArr = this.f1224a;
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
                            int i7 = iArr[i6];
                            float f2 = fArr[i6];
                            sb.append(i7);
                            sb.append("=");
                            sb.append(f2);
                            i3++;
                            if (i3 < this.f1228e) {
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

    private IntFloatMap() {
        this.f1224a = ScatterMapKt.f1393a;
        this.f1225b = IntSetKt.a();
        this.f1226c = FloatSetKt.a();
    }
}
