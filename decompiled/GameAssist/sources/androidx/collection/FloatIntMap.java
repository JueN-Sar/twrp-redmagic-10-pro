package androidx.collection;

import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes.dex */
public abstract class FloatIntMap {

    /* renamed from: a, reason: collision with root package name */
    public long[] f1194a;

    /* renamed from: b, reason: collision with root package name */
    public float[] f1195b;

    /* renamed from: c, reason: collision with root package name */
    public int[] f1196c;

    /* renamed from: d, reason: collision with root package name */
    public int f1197d;

    /* renamed from: e, reason: collision with root package name */
    public int f1198e;

    public /* synthetic */ FloatIntMap(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public final int a(float f2) {
        int hashCode = Float.hashCode(f2) * (-862048943);
        int i2 = hashCode ^ (hashCode << 16);
        int i3 = i2 & 127;
        int i4 = this.f1197d;
        int i5 = (i2 >>> 7) & i4;
        int i6 = 0;
        while (true) {
            long[] jArr = this.f1194a;
            int i7 = i5 >> 3;
            int i8 = (i5 & 7) << 3;
            long j2 = ((jArr[i7 + 1] << (64 - i8)) & ((-i8) >> 63)) | (jArr[i7] >>> i8);
            long j3 = (i3 * 72340172838076673L) ^ j2;
            for (long j4 = (~j3) & (j3 - 72340172838076673L) & (-9187201950435737472L); j4 != 0; j4 &= j4 - 1) {
                int numberOfTrailingZeros = ((Long.numberOfTrailingZeros(j4) >> 3) + i5) & i4;
                if (this.f1195b[numberOfTrailingZeros] == f2) {
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

    public final int b(float f2) {
        int a2 = a(f2);
        if (a2 >= 0) {
            return this.f1196c[a2];
        }
        throw new NoSuchElementException("Cannot find value for key " + f2);
    }

    public final int c() {
        return this.f1197d;
    }

    public final int d() {
        return this.f1198e;
    }

    public final boolean e() {
        return this.f1198e == 0;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FloatIntMap)) {
            return false;
        }
        FloatIntMap floatIntMap = (FloatIntMap) obj;
        if (floatIntMap.d() != d()) {
            return false;
        }
        float[] fArr = this.f1195b;
        int[] iArr = this.f1196c;
        long[] jArr = this.f1194a;
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
                            if (iArr[i5] != floatIntMap.b(fArr[i5])) {
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
        float[] fArr = this.f1195b;
        int[] iArr = this.f1196c;
        long[] jArr = this.f1194a;
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
                            i4 += Integer.hashCode(iArr[i7]) ^ Float.hashCode(f2);
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
        float[] fArr = this.f1195b;
        int[] iArr = this.f1196c;
        long[] jArr = this.f1194a;
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
                            int i7 = iArr[i6];
                            sb.append(f2);
                            sb.append("=");
                            sb.append(i7);
                            i3++;
                            if (i3 < this.f1198e) {
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

    private FloatIntMap() {
        this.f1194a = ScatterMapKt.f1393a;
        this.f1195b = FloatSetKt.a();
        this.f1196c = IntSetKt.a();
    }
}
