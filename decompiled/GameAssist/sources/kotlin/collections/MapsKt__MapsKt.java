package kotlin.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public class MapsKt__MapsKt extends MapsKt__MapsJVMKt {
    public static Map f() {
        EmptyMap emptyMap = EmptyMap.INSTANCE;
        Intrinsics.c(emptyMap, "null cannot be cast to non-null type kotlin.collections.Map<K of kotlin.collections.MapsKt__MapsKt.emptyMap, V of kotlin.collections.MapsKt__MapsKt.emptyMap>");
        return emptyMap;
    }

    public static final Map g(Map map) {
        Map f2;
        Intrinsics.e(map, "<this>");
        int size = map.size();
        if (size != 0) {
            return size != 1 ? map : MapsKt__MapsJVMKt.e(map);
        }
        f2 = f();
        return f2;
    }

    public static final void h(Map map, Iterable pairs) {
        Intrinsics.e(map, "<this>");
        Intrinsics.e(pairs, "pairs");
        Iterator it = pairs.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            map.put(pair.a(), pair.b());
        }
    }

    public static Map i(Iterable iterable) {
        Map f2;
        int c2;
        Intrinsics.e(iterable, "<this>");
        if (!(iterable instanceof Collection)) {
            return g(j(iterable, new LinkedHashMap()));
        }
        Collection collection = (Collection) iterable;
        int size = collection.size();
        if (size == 0) {
            f2 = f();
            return f2;
        }
        if (size == 1) {
            return MapsKt__MapsJVMKt.d((Pair) (iterable instanceof List ? ((List) iterable).get(0) : iterable.iterator().next()));
        }
        c2 = MapsKt__MapsJVMKt.c(collection.size());
        return j(iterable, new LinkedHashMap(c2));
    }

    public static final Map j(Iterable iterable, Map destination) {
        Intrinsics.e(iterable, "<this>");
        Intrinsics.e(destination, "destination");
        h(destination, iterable);
        return destination;
    }

    public static Map k(Map map) {
        Map f2;
        Intrinsics.e(map, "<this>");
        int size = map.size();
        if (size != 0) {
            return size != 1 ? l(map) : MapsKt__MapsJVMKt.e(map);
        }
        f2 = f();
        return f2;
    }

    public static final Map l(Map map) {
        Intrinsics.e(map, "<this>");
        return new LinkedHashMap(map);
    }
}
