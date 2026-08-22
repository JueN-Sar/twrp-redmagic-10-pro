package kotlin.comparisons;

import java.util.Comparator;
import kotlin.Metadata;

@Metadata
/* loaded from: classes2.dex */
final class ReversedComparator<T> implements Comparator<T> {

    /* renamed from: c, reason: collision with root package name */
    private final Comparator f18403c;

    @Override // java.util.Comparator
    public int compare(Object obj, Object obj2) {
        return this.f18403c.compare(obj2, obj);
    }

    @Override // java.util.Comparator
    public final Comparator reversed() {
        return this.f18403c;
    }
}
