package kotlin.collections;

import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.IntRange;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public class CollectionsKt__CollectionsKt extends CollectionsKt__CollectionsJVMKt {
    public static final Collection f(Object[] objArr) {
        Intrinsics.e(objArr, "<this>");
        return new ArrayAsCollection(objArr, false);
    }

    public static List g() {
        return EmptyList.INSTANCE;
    }

    public static IntRange h(Collection collection) {
        Intrinsics.e(collection, "<this>");
        return new IntRange(0, collection.size() - 1);
    }

    public static int i(List list) {
        Intrinsics.e(list, "<this>");
        return list.size() - 1;
    }

    public static List j(Object... elements) {
        Intrinsics.e(elements, "elements");
        return elements.length > 0 ? ArraysKt___ArraysJvmKt.c(elements) : g();
    }

    public static List k(List list) {
        Intrinsics.e(list, "<this>");
        int size = list.size();
        return size != 0 ? size != 1 ? list : CollectionsKt__CollectionsJVMKt.e(list.get(0)) : g();
    }

    public static void l() {
        throw new ArithmeticException("Count overflow has happened.");
    }

    public static void m() {
        throw new ArithmeticException("Index overflow has happened.");
    }
}
