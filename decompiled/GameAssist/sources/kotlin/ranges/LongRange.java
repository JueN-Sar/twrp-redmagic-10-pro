package kotlin.ranges;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata
/* loaded from: classes2.dex */
public final class LongRange extends LongProgression implements ClosedRange<Long>, OpenEndRange<Long> {

    /* renamed from: k, reason: collision with root package name */
    public static final Companion f18627k = new Companion(null);

    /* renamed from: l, reason: collision with root package name */
    private static final LongRange f18628l = new LongRange(1, 0);

    @Metadata
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public LongRange(long j2, long j3) {
        super(j2, j3, 1L);
    }

    @Override // kotlin.ranges.LongProgression
    public boolean equals(Object obj) {
        if (obj instanceof LongRange) {
            if (!isEmpty() || !((LongRange) obj).isEmpty()) {
                LongRange longRange = (LongRange) obj;
                if (g() != longRange.g() || h() != longRange.h()) {
                }
            }
            return true;
        }
        return false;
    }

    @Override // kotlin.ranges.LongProgression
    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return (int) ((31 * (g() ^ (g() >>> 32))) + (h() ^ (h() >>> 32)));
    }

    @Override // kotlin.ranges.LongProgression
    public boolean isEmpty() {
        return g() > h();
    }

    public boolean j(long j2) {
        return g() <= j2 && j2 <= h();
    }

    @Override // kotlin.ranges.OpenEndRange
    /* renamed from: k, reason: merged with bridge method [inline-methods] */
    public Long f() {
        if (h() != Long.MAX_VALUE) {
            return Long.valueOf(h() + 1);
        }
        throw new IllegalStateException("Cannot return the exclusive upper bound of a range that includes MAX_VALUE.".toString());
    }

    @Override // kotlin.ranges.ClosedRange
    /* renamed from: l, reason: merged with bridge method [inline-methods] */
    public Long d() {
        return Long.valueOf(h());
    }

    @Override // kotlin.ranges.ClosedRange
    /* renamed from: m, reason: merged with bridge method [inline-methods] */
    public Long b() {
        return Long.valueOf(g());
    }

    @Override // kotlin.ranges.LongProgression
    public String toString() {
        return g() + ".." + h();
    }
}
