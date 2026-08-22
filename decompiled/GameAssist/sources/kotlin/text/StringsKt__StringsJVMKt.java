package kotlin.text;

import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public class StringsKt__StringsJVMKt extends StringsKt__StringNumberConversionsKt {
    public static final boolean h(String str, String suffix, boolean z) {
        Intrinsics.e(str, "<this>");
        Intrinsics.e(suffix, "suffix");
        return !z ? str.endsWith(suffix) : k(str, str.length() - suffix.length(), suffix, 0, suffix.length(), true);
    }

    public static /* synthetic */ boolean i(String str, String str2, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        return h(str, str2, z);
    }

    public static final boolean j(CharSequence charSequence) {
        Intrinsics.e(charSequence, "<this>");
        if (charSequence.length() != 0) {
            Iterable q2 = StringsKt__StringsKt.q(charSequence);
            if (!(q2 instanceof Collection) || !((Collection) q2).isEmpty()) {
                Iterator it = q2.iterator();
                while (it.hasNext()) {
                    if (!CharsKt__CharJVMKt.c(charSequence.charAt(((IntIterator) it).nextInt()))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static final boolean k(String str, int i2, String other, int i3, int i4, boolean z) {
        Intrinsics.e(str, "<this>");
        Intrinsics.e(other, "other");
        return !z ? str.regionMatches(i2, other, i3, i4) : str.regionMatches(z, i2, other, i3, i4);
    }

    public static String l(CharSequence charSequence, int i2) {
        Intrinsics.e(charSequence, "<this>");
        if (i2 < 0) {
            throw new IllegalArgumentException(("Count 'n' must be non-negative, but was " + i2 + '.').toString());
        }
        if (i2 == 0) {
            return "";
        }
        if (i2 == 1) {
            return charSequence.toString();
        }
        int length = charSequence.length();
        if (length == 0) {
            return "";
        }
        if (length == 1) {
            char charAt = charSequence.charAt(0);
            char[] cArr = new char[i2];
            for (int i3 = 0; i3 < i2; i3++) {
                cArr[i3] = charAt;
            }
            return new String(cArr);
        }
        StringBuilder sb = new StringBuilder(charSequence.length() * i2);
        IntIterator it = new IntRange(1, i2).iterator();
        while (it.hasNext()) {
            it.nextInt();
            sb.append(charSequence);
        }
        String sb2 = sb.toString();
        Intrinsics.d(sb2, "{\n                    va…tring()\n                }");
        return sb2;
    }

    public static final String m(String str, String oldValue, String newValue, boolean z) {
        int a2;
        Intrinsics.e(str, "<this>");
        Intrinsics.e(oldValue, "oldValue");
        Intrinsics.e(newValue, "newValue");
        int i2 = 0;
        int s2 = StringsKt__StringsKt.s(str, oldValue, 0, z);
        if (s2 < 0) {
            return str;
        }
        int length = oldValue.length();
        a2 = RangesKt___RangesKt.a(length, 1);
        int length2 = (str.length() - length) + newValue.length();
        if (length2 < 0) {
            throw new OutOfMemoryError();
        }
        StringBuilder sb = new StringBuilder(length2);
        do {
            sb.append((CharSequence) str, i2, s2);
            sb.append(newValue);
            i2 = s2 + length;
            if (s2 >= str.length()) {
                break;
            }
            s2 = StringsKt__StringsKt.s(str, oldValue, s2 + a2, z);
        } while (s2 > 0);
        sb.append((CharSequence) str, i2, str.length());
        String sb2 = sb.toString();
        Intrinsics.d(sb2, "stringBuilder.append(this, i, length).toString()");
        return sb2;
    }

    public static /* synthetic */ String n(String str, String str2, String str3, boolean z, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z = false;
        }
        return m(str, str2, str3, z);
    }
}
