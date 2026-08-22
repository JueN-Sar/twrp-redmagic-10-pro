package kotlin.text;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public class StringsKt___StringsKt extends StringsKt___StringsJvmKt {
    public static final String Q(String str, int i2) {
        int c2;
        Intrinsics.e(str, "<this>");
        if (i2 >= 0) {
            c2 = RangesKt___RangesKt.c(i2, str.length());
            String substring = str.substring(c2);
            Intrinsics.d(substring, "this as java.lang.String).substring(startIndex)");
            return substring;
        }
        throw new IllegalArgumentException(("Requested character count " + i2 + " is less than zero.").toString());
    }

    public static String R(String str, int i2) {
        int a2;
        Intrinsics.e(str, "<this>");
        if (i2 >= 0) {
            a2 = RangesKt___RangesKt.a(str.length() - i2, 0);
            return S(str, a2);
        }
        throw new IllegalArgumentException(("Requested character count " + i2 + " is less than zero.").toString());
    }

    public static final String S(String str, int i2) {
        int c2;
        Intrinsics.e(str, "<this>");
        if (i2 >= 0) {
            c2 = RangesKt___RangesKt.c(i2, str.length());
            String substring = str.substring(0, c2);
            Intrinsics.d(substring, "this as java.lang.String…ing(startIndex, endIndex)");
            return substring;
        }
        throw new IllegalArgumentException(("Requested character count " + i2 + " is less than zero.").toString());
    }
}
