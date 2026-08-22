package kotlin.comparisons;

import java.util.Comparator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata
/* loaded from: classes2.dex */
final class NaturalOrderComparator implements Comparator<Comparable<? super Object>> {

    /* renamed from: c, reason: collision with root package name */
    public static final NaturalOrderComparator f18401c = new NaturalOrderComparator();

    private NaturalOrderComparator() {
    }

    @Override // java.util.Comparator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compare(Comparable a2, Comparable b2) {
        Intrinsics.e(a2, "a");
        Intrinsics.e(b2, "b");
        return a2.compareTo(b2);
    }

    @Override // java.util.Comparator
    public final Comparator<Comparable<? super Object>> reversed() {
        return ReverseOrderComparator.f18402c;
    }
}
