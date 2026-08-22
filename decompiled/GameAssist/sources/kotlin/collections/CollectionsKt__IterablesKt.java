package kotlin.collections;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata
/* loaded from: classes2.dex */
class CollectionsKt__IterablesKt extends CollectionsKt__CollectionsKt {
    public static int n(Iterable iterable, int i2) {
        Intrinsics.e(iterable, "<this>");
        return iterable instanceof Collection ? ((Collection) iterable).size() : i2;
    }
}
