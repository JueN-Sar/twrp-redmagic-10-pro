package kotlin.collections;

import java.util.List;
import kotlin.Metadata;
import kotlin.ranges.IntRange;

@Metadata
/* loaded from: classes2.dex */
class CollectionsKt__ReversedViewsKt extends CollectionsKt__MutableCollectionsKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final int u(List list, int i2) {
        if (new IntRange(0, CollectionsKt__CollectionsKt.i(list)).l(i2)) {
            return CollectionsKt__CollectionsKt.i(list) - i2;
        }
        throw new IndexOutOfBoundsException("Element index " + i2 + " must be in range [" + new IntRange(0, CollectionsKt__CollectionsKt.i(list)) + "].");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int v(List list, int i2) {
        if (new IntRange(0, list.size()).l(i2)) {
            return list.size() - i2;
        }
        throw new IndexOutOfBoundsException("Position index " + i2 + " must be in range [" + new IntRange(0, list.size()) + "].");
    }
}
