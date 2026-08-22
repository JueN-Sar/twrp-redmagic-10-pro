package kotlin.comparisons;

import java.util.Comparator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata
/* loaded from: classes2.dex */
final class ReverseOrderComparator implements Comparator<Comparable<? super Object>> {

    /* renamed from: c, reason: collision with root package name */
    public static final ReverseOrderComparator f18402c = new ReverseOrderComparator();

    private ReverseOrderComparator() {
    }

    @Override // java.util.Comparator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compare(Comparable a2, Comparable b2) {
        Intrinsics.e(a2, "a");
        Intrinsics.e(b2, "b");
        return b2.compareTo(a2);
    }

    @Override // java.util.Comparator
    public final Comparator<Comparable<? super Object>> reversed() {
        return NaturalOrderComparator.f18401c;
    }
}
