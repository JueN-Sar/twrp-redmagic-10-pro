package kotlin.ranges;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UInt;
import kotlin.WasExperimental;
import kotlin.jvm.internal.DefaultConstructorMarker;

@SinceKotlin
@Metadata
@WasExperimental
/* loaded from: classes2.dex */
public final class UIntRange extends UIntProgression implements ClosedRange<UInt>, OpenEndRange<UInt> {

    /* renamed from: k, reason: collision with root package name */
    public static final Companion f18641k;

    /* renamed from: l, reason: collision with root package name */
    private static final UIntRange f18642l;

    @Metadata
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        DefaultConstructorMarker defaultConstructorMarker = null;
        f18641k = new Companion(defaultConstructorMarker);
        f18642l = new UIntRange(-1, 0, defaultConstructorMarker);
    }

    public /* synthetic */ UIntRange(int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(i2, i3);
    }

    @Override // kotlin.ranges.ClosedRange
    public /* bridge */ /* synthetic */ Comparable b() {
        return UInt.c(k());
    }

    @Override // kotlin.ranges.ClosedRange
    public /* bridge */ /* synthetic */ Comparable d() {
        return UInt.c(j());
    }

    @Override // kotlin.ranges.UIntProgression
    public boolean equals(Object obj) {
        if (obj instanceof UIntRange) {
            if (!isEmpty() || !((UIntRange) obj).isEmpty()) {
                UIntRange uIntRange = (UIntRange) obj;
                if (g() != uIntRange.g() || h() != uIntRange.h()) {
                }
            }
            return true;
        }
        return false;
    }

    @Override // kotlin.ranges.OpenEndRange
    public /* bridge */ /* synthetic */ Comparable f() {
        return UInt.c(i());
    }

    @Override // kotlin.ranges.UIntProgression
    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return h() + (g() * 31);
    }

    public int i() {
        if (h() != -1) {
            return UInt.d(h() + 1);
        }
        throw new IllegalStateException("Cannot return the exclusive upper bound of a range that includes MAX_VALUE.".toString());
    }

    @Override // kotlin.ranges.UIntProgression
    public boolean isEmpty() {
        return Integer.compareUnsigned(g(), h()) > 0;
    }

    public int j() {
        return h();
    }

    public int k() {
        return g();
    }

    @Override // kotlin.ranges.UIntProgression
    public String toString() {
        return ((Object) UInt.h(g())) + ".." + ((Object) UInt.h(h()));
    }

    private UIntRange(int i2, int i3) {
        super(i2, i3, 1, null);
    }
}
