package kotlin.internal;

import kotlin.Metadata;

@Metadata
/* loaded from: classes2.dex */
public final class ProgressionUtilKt {
    private static final int a(int i2, int i3, int i4) {
        return e(e(i2, i4) - e(i3, i4), i4);
    }

    private static final long b(long j2, long j3, long j4) {
        return f(f(j2, j4) - f(j3, j4), j4);
    }

    public static final int c(int i2, int i3, int i4) {
        if (i4 > 0) {
            return i2 >= i3 ? i3 : i3 - a(i3, i2, i4);
        }
        if (i4 < 0) {
            return i2 <= i3 ? i3 : i3 + a(i2, i3, -i4);
        }
        throw new IllegalArgumentException("Step is zero.");
    }

    public static final long d(long j2, long j3, long j4) {
        if (j4 > 0) {
            return j2 >= j3 ? j3 : j3 - b(j3, j2, j4);
        }
        if (j4 < 0) {
            return j2 <= j3 ? j3 : j3 + b(j2, j3, -j4);
        }
        throw new IllegalArgumentException("Step is zero.");
    }

    private static final int e(int i2, int i3) {
        int i4 = i2 % i3;
        return i4 >= 0 ? i4 : i4 + i3;
    }

    private static final long f(long j2, long j3) {
        long j4 = j2 % j3;
        return j4 >= 0 ? j4 : j4 + j3;
    }
}
