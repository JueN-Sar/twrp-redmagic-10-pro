package kotlin.ranges;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata
/* loaded from: classes2.dex */
public final class IntRange extends IntProgression implements ClosedRange<Integer>, OpenEndRange<Integer> {

    /* renamed from: k, reason: collision with root package name */
    public static final Companion f18617k = new Companion(null);

    /* renamed from: l, reason: collision with root package name */
    private static final IntRange f18618l = new IntRange(1, 0);

    @Metadata
    public static final class Companion {
        private Companion() {
        }

        public final IntRange a() {
            return IntRange.f18618l;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public IntRange(int i2, int i3) {
        super(i2, i3, 1);
    }

    @Override // kotlin.ranges.IntProgression
    public boolean equals(Object obj) {
        if (obj instanceof IntRange) {
            if (!isEmpty() || !((IntRange) obj).isEmpty()) {
                IntRange intRange = (IntRange) obj;
                if (g() != intRange.g() || h() != intRange.h()) {
                }
            }
            return true;
        }
        return false;
    }

    @Override // kotlin.ranges.IntProgression
    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return h() + (g() * 31);
    }

    @Override // kotlin.ranges.IntProgression
    public boolean isEmpty() {
        return g() > h();
    }

    public boolean l(int i2) {
        return g() <= i2 && i2 <= h();
    }

    @Override // kotlin.ranges.OpenEndRange
    /* renamed from: m, reason: merged with bridge method [inline-methods] */
    public Integer f() {
        if (h() != Integer.MAX_VALUE) {
            return Integer.valueOf(h() + 1);
        }
        throw new IllegalStateException("Cannot return the exclusive upper bound of a range that includes MAX_VALUE.".toString());
    }

    @Override // kotlin.ranges.ClosedRange
    /* renamed from: n, reason: merged with bridge method [inline-methods] */
    public Integer d() {
        return Integer.valueOf(h());
    }

    @Override // kotlin.ranges.ClosedRange
    /* renamed from: o, reason: merged with bridge method [inline-methods] */
    public Integer b() {
        return Integer.valueOf(g());
    }

    @Override // kotlin.ranges.IntProgression
    public String toString() {
        return g() + ".." + h();
    }
}
