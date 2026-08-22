package kotlin.collections;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata
/* loaded from: classes2.dex */
public class CollectionsKt__MutableCollectionsKt extends CollectionsKt__MutableCollectionsJVMKt {
    public static Collection q(Iterable iterable) {
        List L;
        Intrinsics.e(iterable, "<this>");
        if (iterable instanceof Collection) {
            return (Collection) iterable;
        }
        L = CollectionsKt___CollectionsKt.L(iterable);
        return L;
    }

    public static Object r(List list) {
        Intrinsics.e(list, "<this>");
        if (list.isEmpty()) {
            throw new NoSuchElementException("List is empty.");
        }
        return list.remove(CollectionsKt__CollectionsKt.i(list));
    }
}
