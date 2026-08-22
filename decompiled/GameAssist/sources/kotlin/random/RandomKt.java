package kotlin.random;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public final class RandomKt {
    public static final String a(Object from, Object until) {
        Intrinsics.e(from, "from");
        Intrinsics.e(until, "until");
        return "Random range is empty: [" + from + ", " + until + ").";
    }

    public static final void b(int i2, int i3) {
        if (i3 <= i2) {
            throw new IllegalArgumentException(a(Integer.valueOf(i2), Integer.valueOf(i3)).toString());
        }
    }

    public static final int c(int i2) {
        return 31 - Integer.numberOfLeadingZeros(i2);
    }

    public static final int d(int i2, int i3) {
        return (i2 >>> (32 - i3)) & ((-i3) >> 31);
    }
}
