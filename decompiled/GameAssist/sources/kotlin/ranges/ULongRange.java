package kotlin.ranges;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.ULong;
import kotlin.WasExperimental;
import kotlin.jvm.internal.DefaultConstructorMarker;

@SinceKotlin
@Metadata
@WasExperimental
/* loaded from: classes2.dex */
public final class ULongRange extends ULongProgression implements ClosedRange<ULong>, OpenEndRange<ULong> {

    /* renamed from: k, reason: collision with root package name */
    public static final Companion f18651k = new Companion(null);

    /* renamed from: l, reason: collision with root package name */
    private static final ULongRange f18652l = new ULongRange(-1, 0, null);

    @Metadata
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public /* synthetic */ ULongRange(long j2, long j3, DefaultConstructorMarker defaultConstructorMarker) {
        this(j2, j3);
    }

    @Override // kotlin.ranges.ClosedRange
    public /* bridge */ /* synthetic */ Comparable b() {
        return ULong.c(k());
    }

    @Override // kotlin.ranges.ClosedRange
    public /* bridge */ /* synthetic */ Comparable d() {
        return ULong.c(j());
    }

    @Override // kotlin.ranges.ULongProgression
    public boolean equals(Object obj) {
        if (obj instanceof ULongRange) {
            if (!isEmpty() || !((ULongRange) obj).isEmpty()) {
                ULongRange uLongRange = (ULongRange) obj;
                if (g() != uLongRange.g() || h() != uLongRange.h()) {
                }
            }
            return true;
        }
        return false;
    }

    @Override // kotlin.ranges.OpenEndRange
    public /* bridge */ /* synthetic */ Comparable f() {
        return ULong.c(i());
    }

    @Override // kotlin.ranges.ULongProgression
    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return ((int) ULong.d(h() ^ ULong.d(h() >>> 32))) + (((int) ULong.d(g() ^ ULong.d(g() >>> 32))) * 31);
    }

    public long i() {
        if (h() != -1) {
            return ULong.d(h() + ULong.d(1 & 4294967295L));
        }
        throw new IllegalStateException("Cannot return the exclusive upper bound of a range that includes MAX_VALUE.".toString());
    }

    @Override // kotlin.ranges.ULongProgression
    public boolean isEmpty() {
        return Long.compareUnsigned(g(), h()) > 0;
    }

    public long j() {
        return h();
    }

    public long k() {
        return g();
    }

    @Override // kotlin.ranges.ULongProgression
    public String toString() {
        return ((Object) ULong.h(g())) + ".." + ((Object) ULong.h(h()));
    }

    private ULongRange(long j2, long j3) {
        super(j2, j3, 1L, null);
    }
}
