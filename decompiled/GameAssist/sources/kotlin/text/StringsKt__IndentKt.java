package kotlin.text;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public class StringsKt__IndentKt extends StringsKt__AppendableKt {
    private static final Function1 b(final String str) {
        return str.length() == 0 ? new Function1<String, String>() { // from class: kotlin.text.StringsKt__IndentKt$getIndentFunction$1
            @Override // kotlin.jvm.functions.Function1
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public final String c(String line) {
                Intrinsics.e(line, "line");
                return line;
            }
        } : new Function1<String, String>() { // from class: kotlin.text.StringsKt__IndentKt$getIndentFunction$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public final String c(String line) {
                Intrinsics.e(line, "line");
                return str + line;
            }
        };
    }

    private static final int c(String str) {
        int length = str.length();
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                i2 = -1;
                break;
            }
            if (!CharsKt__CharJVMKt.c(str.charAt(i2))) {
                break;
            }
            i2++;
        }
        return i2 == -1 ? str.length() : i2;
    }

    public static final String d(String str, String newIndent) {
        Comparable G;
        String str2;
        Intrinsics.e(str, "<this>");
        Intrinsics.e(newIndent, "newIndent");
        List E = StringsKt__StringsKt.E(str);
        ArrayList arrayList = new ArrayList();
        for (Object obj : E) {
            if (!StringsKt__StringsJVMKt.j((String) obj)) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.n(arrayList, 10));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(Integer.valueOf(c((String) it.next())));
        }
        G = CollectionsKt___CollectionsKt.G(arrayList2);
        Integer num = (Integer) G;
        int i2 = 0;
        int intValue = num != null ? num.intValue() : 0;
        int length = str.length() + (newIndent.length() * E.size());
        Function1 b2 = b(newIndent);
        int i3 = CollectionsKt__CollectionsKt.i(E);
        ArrayList arrayList3 = new ArrayList();
        for (Object obj2 : E) {
            int i4 = i2 + 1;
            if (i2 < 0) {
                CollectionsKt__CollectionsKt.m();
            }
            String str3 = (String) obj2;
            if ((i2 == 0 || i2 == i3) && StringsKt__StringsJVMKt.j(str3)) {
                str3 = null;
            } else {
                String Q = StringsKt___StringsKt.Q(str3, intValue);
                if (Q != null && (str2 = (String) b2.c(Q)) != null) {
                    str3 = str2;
                }
            }
            if (str3 != null) {
                arrayList3.add(str3);
            }
            i2 = i4;
        }
        String sb = ((StringBuilder) CollectionsKt.C(arrayList3, new StringBuilder(length), "\n", null, null, 0, null, null, 124, null)).toString();
        Intrinsics.d(sb, "mapIndexedNotNull { inde…\"\\n\")\n        .toString()");
        return sb;
    }

    public static String e(String str) {
        Intrinsics.e(str, "<this>");
        return d(str, "");
    }
}
