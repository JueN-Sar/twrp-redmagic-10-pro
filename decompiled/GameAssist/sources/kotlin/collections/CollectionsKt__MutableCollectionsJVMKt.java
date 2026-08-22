package kotlin.collections;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata
/* loaded from: classes2.dex */
public class CollectionsKt__MutableCollectionsJVMKt extends CollectionsKt__IteratorsKt {
    public static void o(List list) {
        Intrinsics.e(list, "<this>");
        if (list.size() > 1) {
            Collections.sort(list);
        }
    }

    public static void p(List list, Comparator comparator) {
        Intrinsics.e(list, "<this>");
        Intrinsics.e(comparator, "comparator");
        if (list.size() > 1) {
            Collections.sort(list, comparator);
        }
    }
}
