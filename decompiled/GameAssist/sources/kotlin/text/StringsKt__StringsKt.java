package kotlin.text;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CharIterator;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.IntProgression;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt___SequencesKt;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public class StringsKt__StringsKt extends StringsKt__StringsJVMKt {
    public static /* synthetic */ int A(CharSequence charSequence, char c2, int i2, boolean z, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = r(charSequence);
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        return y(charSequence, c2, i2, z);
    }

    public static /* synthetic */ int B(CharSequence charSequence, String str, int i2, boolean z, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = r(charSequence);
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        return z(charSequence, str, i2, z);
    }

    public static final int C(CharSequence charSequence, char[] chars, int i2, boolean z) {
        int c2;
        Intrinsics.e(charSequence, "<this>");
        Intrinsics.e(chars, "chars");
        if (!z && chars.length == 1 && (charSequence instanceof String)) {
            return ((String) charSequence).lastIndexOf(ArraysKt___ArraysKt.S(chars), i2);
        }
        for (c2 = RangesKt___RangesKt.c(i2, r(charSequence)); -1 < c2; c2--) {
            char charAt = charSequence.charAt(c2);
            for (char c3 : chars) {
                if (CharsKt__CharKt.d(c3, charAt, z)) {
                    return c2;
                }
            }
        }
        return -1;
    }

    public static final Sequence D(CharSequence charSequence) {
        Intrinsics.e(charSequence, "<this>");
        return M(charSequence, new String[]{"\r\n", "\n", "\r"}, false, 0, 6, null);
    }

    public static final List E(CharSequence charSequence) {
        List l2;
        Intrinsics.e(charSequence, "<this>");
        l2 = SequencesKt___SequencesKt.l(D(charSequence));
        return l2;
    }

    public static final CharSequence F(CharSequence charSequence, int i2, char c2) {
        Intrinsics.e(charSequence, "<this>");
        if (i2 < 0) {
            throw new IllegalArgumentException("Desired length " + i2 + " is less than zero.");
        }
        if (i2 <= charSequence.length()) {
            return charSequence.subSequence(0, charSequence.length());
        }
        StringBuilder sb = new StringBuilder(i2);
        IntIterator it = new IntRange(1, i2 - charSequence.length()).iterator();
        while (it.hasNext()) {
            it.nextInt();
            sb.append(c2);
        }
        sb.append(charSequence);
        return sb;
    }

    public static String G(String str, int i2, char c2) {
        Intrinsics.e(str, "<this>");
        return F(str, i2, c2).toString();
    }

    private static final Sequence H(CharSequence charSequence, String[] strArr, int i2, final boolean z, int i3) {
        K(i3);
        final List c2 = ArraysKt___ArraysJvmKt.c(strArr);
        return new DelimitedRangesSequence(charSequence, i2, i3, new Function2<CharSequence, Integer, Pair<? extends Integer, ? extends Integer>>() { // from class: kotlin.text.StringsKt__StringsKt$rangesDelimitedBy$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            public final Pair d(CharSequence $receiver, int i4) {
                Pair p2;
                Intrinsics.e($receiver, "$this$$receiver");
                p2 = StringsKt__StringsKt.p($receiver, c2, i4, z, false);
                if (p2 != null) {
                    return TuplesKt.a(p2.c(), Integer.valueOf(((String) p2.d()).length()));
                }
                return null;
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object y(Object obj, Object obj2) {
                return d((CharSequence) obj, ((Number) obj2).intValue());
            }
        });
    }

    static /* synthetic */ Sequence I(CharSequence charSequence, String[] strArr, int i2, boolean z, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i2 = 0;
        }
        if ((i4 & 4) != 0) {
            z = false;
        }
        if ((i4 & 8) != 0) {
            i3 = 0;
        }
        return H(charSequence, strArr, i2, z, i3);
    }

    public static final boolean J(CharSequence charSequence, int i2, CharSequence other, int i3, int i4, boolean z) {
        Intrinsics.e(charSequence, "<this>");
        Intrinsics.e(other, "other");
        if (i3 < 0 || i2 < 0 || i2 > charSequence.length() - i4 || i3 > other.length() - i4) {
            return false;
        }
        for (int i5 = 0; i5 < i4; i5++) {
            if (!CharsKt__CharKt.d(charSequence.charAt(i2 + i5), other.charAt(i3 + i5), z)) {
                return false;
            }
        }
        return true;
    }

    public static final void K(int i2) {
        if (i2 >= 0) {
            return;
        }
        throw new IllegalArgumentException(("Limit must be non-negative, but was " + i2).toString());
    }

    public static final Sequence L(final CharSequence charSequence, String[] delimiters, boolean z, int i2) {
        Sequence j2;
        Intrinsics.e(charSequence, "<this>");
        Intrinsics.e(delimiters, "delimiters");
        j2 = SequencesKt___SequencesKt.j(I(charSequence, delimiters, 0, z, i2, 2, null), new Function1<IntRange, String>() { // from class: kotlin.text.StringsKt__StringsKt$splitToSequence$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public final String c(IntRange it) {
                Intrinsics.e(it, "it");
                return StringsKt__StringsKt.N(charSequence, it);
            }
        });
        return j2;
    }

    public static /* synthetic */ Sequence M(CharSequence charSequence, String[] strArr, boolean z, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            z = false;
        }
        if ((i3 & 4) != 0) {
            i2 = 0;
        }
        return L(charSequence, strArr, z, i2);
    }

    public static final String N(CharSequence charSequence, IntRange range) {
        Intrinsics.e(charSequence, "<this>");
        Intrinsics.e(range, "range");
        return charSequence.subSequence(range.b().intValue(), range.d().intValue() + 1).toString();
    }

    public static final String O(String str, char c2, String missingDelimiterValue) {
        Intrinsics.e(str, "<this>");
        Intrinsics.e(missingDelimiterValue, "missingDelimiterValue");
        int A = A(str, c2, 0, false, 6, null);
        if (A == -1) {
            return missingDelimiterValue;
        }
        String substring = str.substring(A + 1, str.length());
        Intrinsics.d(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        return substring;
    }

    public static /* synthetic */ String P(String str, char c2, String str2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            str2 = str;
        }
        return O(str, c2, str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Pair p(CharSequence charSequence, Collection collection, int i2, boolean z, boolean z2) {
        int c2;
        IntProgression g2;
        Object obj;
        Object obj2;
        int a2;
        Object H;
        if (!z && collection.size() == 1) {
            H = CollectionsKt___CollectionsKt.H(collection);
            String str = (String) H;
            int v = !z2 ? v(charSequence, str, i2, false, 4, null) : B(charSequence, str, i2, false, 4, null);
            if (v < 0) {
                return null;
            }
            return TuplesKt.a(Integer.valueOf(v), str);
        }
        if (z2) {
            c2 = RangesKt___RangesKt.c(i2, r(charSequence));
            g2 = RangesKt___RangesKt.g(c2, 0);
        } else {
            a2 = RangesKt___RangesKt.a(i2, 0);
            g2 = new IntRange(a2, charSequence.length());
        }
        if (charSequence instanceof String) {
            int g3 = g2.g();
            int h2 = g2.h();
            int i3 = g2.i();
            if ((i3 > 0 && g3 <= h2) || (i3 < 0 && h2 <= g3)) {
                while (true) {
                    Iterator it = collection.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            obj2 = null;
                            break;
                        }
                        obj2 = it.next();
                        String str2 = (String) obj2;
                        if (StringsKt__StringsJVMKt.k(str2, 0, (String) charSequence, g3, str2.length(), z)) {
                            break;
                        }
                    }
                    String str3 = (String) obj2;
                    if (str3 == null) {
                        if (g3 == h2) {
                            break;
                        }
                        g3 += i3;
                    } else {
                        return TuplesKt.a(Integer.valueOf(g3), str3);
                    }
                }
            }
        } else {
            int g4 = g2.g();
            int h3 = g2.h();
            int i4 = g2.i();
            if ((i4 > 0 && g4 <= h3) || (i4 < 0 && h3 <= g4)) {
                while (true) {
                    Iterator it2 = collection.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            obj = null;
                            break;
                        }
                        obj = it2.next();
                        String str4 = (String) obj;
                        if (J(str4, 0, charSequence, g4, str4.length(), z)) {
                            break;
                        }
                    }
                    String str5 = (String) obj;
                    if (str5 == null) {
                        if (g4 == h3) {
                            break;
                        }
                        g4 += i4;
                    } else {
                        return TuplesKt.a(Integer.valueOf(g4), str5);
                    }
                }
            }
        }
        return null;
    }

    public static final IntRange q(CharSequence charSequence) {
        Intrinsics.e(charSequence, "<this>");
        return new IntRange(0, charSequence.length() - 1);
    }

    public static final int r(CharSequence charSequence) {
        Intrinsics.e(charSequence, "<this>");
        return charSequence.length() - 1;
    }

    public static final int s(CharSequence charSequence, String string, int i2, boolean z) {
        Intrinsics.e(charSequence, "<this>");
        Intrinsics.e(string, "string");
        return (z || !(charSequence instanceof String)) ? u(charSequence, string, i2, charSequence.length(), z, false, 16, null) : ((String) charSequence).indexOf(string, i2);
    }

    private static final int t(CharSequence charSequence, CharSequence charSequence2, int i2, int i3, boolean z, boolean z2) {
        int c2;
        int a2;
        IntProgression g2;
        int a3;
        int c3;
        if (z2) {
            c2 = RangesKt___RangesKt.c(i2, r(charSequence));
            a2 = RangesKt___RangesKt.a(i3, 0);
            g2 = RangesKt___RangesKt.g(c2, a2);
        } else {
            a3 = RangesKt___RangesKt.a(i2, 0);
            c3 = RangesKt___RangesKt.c(i3, charSequence.length());
            g2 = new IntRange(a3, c3);
        }
        if ((charSequence instanceof String) && (charSequence2 instanceof String)) {
            int g3 = g2.g();
            int h2 = g2.h();
            int i4 = g2.i();
            if ((i4 <= 0 || g3 > h2) && (i4 >= 0 || h2 > g3)) {
                return -1;
            }
            while (!StringsKt__StringsJVMKt.k((String) charSequence2, 0, (String) charSequence, g3, charSequence2.length(), z)) {
                if (g3 == h2) {
                    return -1;
                }
                g3 += i4;
            }
            return g3;
        }
        int g4 = g2.g();
        int h3 = g2.h();
        int i5 = g2.i();
        if ((i5 <= 0 || g4 > h3) && (i5 >= 0 || h3 > g4)) {
            return -1;
        }
        while (!J(charSequence2, 0, charSequence, g4, charSequence2.length(), z)) {
            if (g4 == h3) {
                return -1;
            }
            g4 += i5;
        }
        return g4;
    }

    static /* synthetic */ int u(CharSequence charSequence, CharSequence charSequence2, int i2, int i3, boolean z, boolean z2, int i4, Object obj) {
        if ((i4 & 16) != 0) {
            z2 = false;
        }
        return t(charSequence, charSequence2, i2, i3, z, z2);
    }

    public static /* synthetic */ int v(CharSequence charSequence, String str, int i2, boolean z, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = 0;
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        return s(charSequence, str, i2, z);
    }

    public static final int w(CharSequence charSequence, char[] chars, int i2, boolean z) {
        int a2;
        Intrinsics.e(charSequence, "<this>");
        Intrinsics.e(chars, "chars");
        if (!z && chars.length == 1 && (charSequence instanceof String)) {
            return ((String) charSequence).indexOf(ArraysKt___ArraysKt.S(chars), i2);
        }
        a2 = RangesKt___RangesKt.a(i2, 0);
        IntIterator it = new IntRange(a2, r(charSequence)).iterator();
        while (it.hasNext()) {
            int nextInt = it.nextInt();
            char charAt = charSequence.charAt(nextInt);
            for (char c2 : chars) {
                if (CharsKt__CharKt.d(c2, charAt, z)) {
                    return nextInt;
                }
            }
        }
        return -1;
    }

    public static final CharIterator x(final CharSequence charSequence) {
        Intrinsics.e(charSequence, "<this>");
        return new CharIterator() { // from class: kotlin.text.StringsKt__StringsKt$iterator$1

            /* renamed from: c, reason: collision with root package name */
            private int f18786c;

            @Override // kotlin.collections.CharIterator
            public char b() {
                CharSequence charSequence2 = charSequence;
                int i2 = this.f18786c;
                this.f18786c = i2 + 1;
                return charSequence2.charAt(i2);
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.f18786c < charSequence.length();
            }
        };
    }

    public static final int y(CharSequence charSequence, char c2, int i2, boolean z) {
        Intrinsics.e(charSequence, "<this>");
        return (z || !(charSequence instanceof String)) ? C(charSequence, new char[]{c2}, i2, z) : ((String) charSequence).lastIndexOf(c2, i2);
    }

    public static final int z(CharSequence charSequence, String string, int i2, boolean z) {
        Intrinsics.e(charSequence, "<this>");
        Intrinsics.e(string, "string");
        return (z || !(charSequence instanceof String)) ? t(charSequence, string, i2, 0, z, true) : ((String) charSequence).lastIndexOf(string, i2);
    }
}
