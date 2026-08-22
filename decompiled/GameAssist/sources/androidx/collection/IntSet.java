package androidx.collection;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes.dex */
public abstract class IntSet {

    /* renamed from: a, reason: collision with root package name */
    public long[] f1252a;

    /* renamed from: b, reason: collision with root package name */
    public int[] f1253b;

    /* renamed from: c, reason: collision with root package name */
    public int f1254c;

    /* renamed from: d, reason: collision with root package name */
    public int f1255d;

    public /* synthetic */ IntSet(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public static /* synthetic */ String d(IntSet intSet, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i2, CharSequence charSequence4, int i3, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: joinToString");
        }
        if ((i3 & 1) != 0) {
            charSequence = ", ";
        }
        CharSequence charSequence5 = (i3 & 2) != 0 ? "" : charSequence2;
        CharSequence charSequence6 = (i3 & 4) == 0 ? charSequence3 : "";
        if ((i3 & 8) != 0) {
            i2 = -1;
        }
        int i4 = i2;
        if ((i3 & 16) != 0) {
            charSequence4 = "...";
        }
        return intSet.c(charSequence, charSequence5, charSequence6, i4, charSequence4);
    }

    public final boolean a(int i2) {
        int hashCode = Integer.hashCode(i2) * (-862048943);
        int i3 = hashCode ^ (hashCode << 16);
        int i4 = i3 & 127;
        int i5 = this.f1254c;
        int i6 = (i3 >>> 7) & i5;
        int i7 = 0;
        while (true) {
            long[] jArr = this.f1252a;
            int i8 = i6 >> 3;
            int i9 = (i6 & 7) << 3;
            long j2 = ((jArr[i8 + 1] << (64 - i9)) & ((-i9) >> 63)) | (jArr[i8] >>> i9);
            long j3 = (i4 * 72340172838076673L) ^ j2;
            for (long j4 = (~j3) & (j3 - 72340172838076673L) & (-9187201950435737472L); j4 != 0; j4 &= j4 - 1) {
                int numberOfTrailingZeros = ((Long.numberOfTrailingZeros(j4) >> 3) + i6) & i5;
                if (this.f1253b[numberOfTrailingZeros] == i2) {
                    return numberOfTrailingZeros >= 0;
                }
            }
            if ((j2 & ((~j2) << 6) & (-9187201950435737472L)) != 0) {
                return false;
            }
            i7 += 8;
            i6 = (i6 + i7) & i5;
        }
    }

    public final int b() {
        return this.f1254c;
    }

    public final String c(CharSequence separator, CharSequence prefix, CharSequence postfix, int i2, CharSequence truncated) {
        Intrinsics.e(separator, "separator");
        Intrinsics.e(prefix, "prefix");
        Intrinsics.e(postfix, "postfix");
        Intrinsics.e(truncated, "truncated");
        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        int[] iArr = this.f1253b;
        long[] jArr = this.f1252a;
        int length = jArr.length - 2;
        if (length >= 0) {
            int i3 = 0;
            int i4 = 0;
            loop0: while (true) {
                long j2 = jArr[i3];
                if ((((~j2) << 7) & j2 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i5 = 8 - ((~(i3 - length)) >>> 31);
                    for (int i6 = 0; i6 < i5; i6++) {
                        if ((255 & j2) < 128) {
                            int i7 = iArr[(i3 << 3) + i6];
                            if (i4 == i2) {
                                sb.append(truncated);
                                break loop0;
                            }
                            if (i4 != 0) {
                                sb.append(separator);
                            }
                            sb.append(i7);
                            i4++;
                        }
                        j2 >>= 8;
                    }
                    if (i5 != 8) {
                        break;
                    }
                }
                if (i3 == length) {
                    break;
                }
                i3++;
            }
            String sb2 = sb.toString();
            Intrinsics.d(sb2, "StringBuilder().apply(builderAction).toString()");
            return sb2;
        }
        sb.append(postfix);
        String sb22 = sb.toString();
        Intrinsics.d(sb22, "StringBuilder().apply(builderAction).toString()");
        return sb22;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof IntSet)) {
            return false;
        }
        IntSet intSet = (IntSet) obj;
        if (intSet.f1255d != this.f1255d) {
            return false;
        }
        int[] iArr = this.f1253b;
        long[] jArr = this.f1252a;
        int length = jArr.length - 2;
        if (length >= 0) {
            int i2 = 0;
            while (true) {
                long j2 = jArr[i2];
                if ((((~j2) << 7) & j2 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i3 = 8 - ((~(i2 - length)) >>> 31);
                    for (int i4 = 0; i4 < i3; i4++) {
                        if ((255 & j2) < 128 && !intSet.a(iArr[(i2 << 3) + i4])) {
                            return false;
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
        int[] iArr = this.f1253b;
        long[] jArr = this.f1252a;
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
                            i4 += Integer.hashCode(iArr[(i3 << 3) + i6]);
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
        return d(this, null, "[", "]", 0, null, 25, null);
    }

    private IntSet() {
        this.f1252a = ScatterMapKt.f1393a;
        this.f1253b = IntSetKt.a();
    }
}
