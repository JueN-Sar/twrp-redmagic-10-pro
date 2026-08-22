package kotlin;

import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt__CharJVMKt;

@Metadata
@JvmName
/* loaded from: classes2.dex */
public final class UnsignedKt {
    public static final int a(int i2, int i3) {
        return Intrinsics.f(i2 ^ Integer.MIN_VALUE, i3 ^ Integer.MIN_VALUE);
    }

    public static final int b(long j2, long j3) {
        return Intrinsics.g(j2 ^ Long.MIN_VALUE, j3 ^ Long.MIN_VALUE);
    }

    public static final String c(long j2) {
        return d(j2, 10);
    }

    public static final String d(long j2, int i2) {
        int a2;
        int a3;
        int a4;
        if (j2 >= 0) {
            a4 = CharsKt__CharJVMKt.a(i2);
            String l2 = Long.toString(j2, a4);
            Intrinsics.d(l2, "toString(this, checkRadix(radix))");
            return l2;
        }
        long j3 = i2;
        long j4 = ((j2 >>> 1) / j3) << 1;
        long j5 = j2 - (j4 * j3);
        if (j5 >= j3) {
            j5 -= j3;
            j4++;
        }
        StringBuilder sb = new StringBuilder();
        a2 = CharsKt__CharJVMKt.a(i2);
        String l3 = Long.toString(j4, a2);
        Intrinsics.d(l3, "toString(this, checkRadix(radix))");
        sb.append(l3);
        a3 = CharsKt__CharJVMKt.a(i2);
        String l4 = Long.toString(j5, a3);
        Intrinsics.d(l4, "toString(this, checkRadix(radix))");
        sb.append(l4);
        return sb.toString();
    }
}
