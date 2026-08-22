package kotlin.text;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata
/* loaded from: classes2.dex */
public class StringsKt__StringNumberConversionsKt extends StringsKt__StringNumberConversionsJVMKt {
    public static Long f(String str) {
        Intrinsics.e(str, "<this>");
        return g(str, 10);
    }

    public static final Long g(String str, int i2) {
        boolean z;
        Intrinsics.e(str, "<this>");
        CharsKt__CharJVMKt.a(i2);
        int length = str.length();
        if (length == 0) {
            return null;
        }
        int i3 = 0;
        char charAt = str.charAt(0);
        long j2 = -9223372036854775807L;
        if (Intrinsics.f(charAt, 48) < 0) {
            z = true;
            if (length == 1) {
                return null;
            }
            if (charAt == '-') {
                j2 = Long.MIN_VALUE;
                i3 = 1;
            } else {
                if (charAt != '+') {
                    return null;
                }
                z = false;
                i3 = 1;
            }
        } else {
            z = false;
        }
        long j3 = -256204778801521550L;
        long j4 = 0;
        long j5 = -256204778801521550L;
        while (i3 < length) {
            int b2 = CharsKt__CharJVMKt.b(str.charAt(i3), i2);
            if (b2 < 0) {
                return null;
            }
            if (j4 < j5) {
                if (j5 == j3) {
                    j5 = j2 / i2;
                    if (j4 < j5) {
                    }
                }
                return null;
            }
            long j6 = j4 * i2;
            long j7 = b2;
            if (j6 < j2 + j7) {
                return null;
            }
            j4 = j6 - j7;
            i3++;
            j3 = -256204778801521550L;
        }
        return z ? Long.valueOf(j4) : Long.valueOf(-j4);
    }
}
