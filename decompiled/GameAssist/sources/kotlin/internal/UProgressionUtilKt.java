package kotlin.internal;

import kotlin.Metadata;
import kotlin.UInt;
import kotlin.ULong;

@Metadata
/* loaded from: classes2.dex */
public final class UProgressionUtilKt {
    private static final int a(int i2, int i3, int i4) {
        int remainderUnsigned = Integer.remainderUnsigned(i2, i4);
        int remainderUnsigned2 = Integer.remainderUnsigned(i3, i4);
        int compareUnsigned = Integer.compareUnsigned(remainderUnsigned, remainderUnsigned2);
        int d2 = UInt.d(remainderUnsigned - remainderUnsigned2);
        return compareUnsigned >= 0 ? d2 : UInt.d(d2 + i4);
    }

    private static final long b(long j2, long j3, long j4) {
        long remainderUnsigned = Long.remainderUnsigned(j2, j4);
        long remainderUnsigned2 = Long.remainderUnsigned(j3, j4);
        int compareUnsigned = Long.compareUnsigned(remainderUnsigned, remainderUnsigned2);
        long d2 = ULong.d(remainderUnsigned - remainderUnsigned2);
        return compareUnsigned >= 0 ? d2 : ULong.d(d2 + j4);
    }

    public static final long c(long j2, long j3, long j4) {
        if (j4 > 0) {
            return Long.compareUnsigned(j2, j3) >= 0 ? j3 : ULong.d(j3 - b(j3, j2, ULong.d(j4)));
        }
        if (j4 < 0) {
            return Long.compareUnsigned(j2, j3) <= 0 ? j3 : ULong.d(j3 + b(j2, j3, ULong.d(-j4)));
        }
        throw new IllegalArgumentException("Step is zero.");
    }

    public static final int d(int i2, int i3, int i4) {
        if (i4 > 0) {
            return Integer.compareUnsigned(i2, i3) >= 0 ? i3 : UInt.d(i3 - a(i3, i2, UInt.d(i4)));
        }
        if (i4 < 0) {
            return Integer.compareUnsigned(i2, i3) <= 0 ? i3 : UInt.d(i3 + a(i2, i3, UInt.d(-i4)));
        }
        throw new IllegalArgumentException("Step is zero.");
    }
}
