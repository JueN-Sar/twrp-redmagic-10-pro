package kotlin.time;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.math.MathKt__MathJVMKt;
import kotlin.ranges.LongRange;
import kotlin.ranges.RangesKt___RangesKt;

@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public final class DurationKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final long h(long j2, int i2) {
        return Duration.j((j2 << 1) + i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long i(long j2) {
        return Duration.j((j2 << 1) + 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long j(long j2) {
        long f2;
        if (new LongRange(-4611686018426L, 4611686018426L).j(j2)) {
            return k(m(j2));
        }
        f2 = RangesKt___RangesKt.f(j2, -4611686018427387903L, 4611686018427387903L);
        return i(f2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long k(long j2) {
        return Duration.j(j2 << 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long l(long j2) {
        return new LongRange(-4611686018426999999L, 4611686018426999999L).j(j2) ? k(j2) : i(n(j2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long m(long j2) {
        return j2 * 1000000;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long n(long j2) {
        return j2 / 1000000;
    }

    public static final long o(double d2, DurationUnit unit) {
        long a2;
        long a3;
        Intrinsics.e(unit, "unit");
        double a4 = DurationUnitKt__DurationUnitJvmKt.a(d2, unit, DurationUnit.NANOSECONDS);
        if (!(!Double.isNaN(a4))) {
            throw new IllegalArgumentException("Duration value cannot be NaN.".toString());
        }
        a2 = MathKt__MathJVMKt.a(a4);
        if (new LongRange(-4611686018426999999L, 4611686018426999999L).j(a2)) {
            return k(a2);
        }
        a3 = MathKt__MathJVMKt.a(DurationUnitKt__DurationUnitJvmKt.a(d2, unit, DurationUnit.MILLISECONDS));
        return j(a3);
    }

    public static final long p(int i2, DurationUnit unit) {
        Intrinsics.e(unit, "unit");
        return unit.compareTo(DurationUnit.SECONDS) <= 0 ? k(DurationUnitKt__DurationUnitJvmKt.c(i2, unit, DurationUnit.NANOSECONDS)) : q(i2, unit);
    }

    public static final long q(long j2, DurationUnit unit) {
        long f2;
        Intrinsics.e(unit, "unit");
        DurationUnit durationUnit = DurationUnit.NANOSECONDS;
        long c2 = DurationUnitKt__DurationUnitJvmKt.c(4611686018426999999L, durationUnit, unit);
        if (new LongRange(-c2, c2).j(j2)) {
            return k(DurationUnitKt__DurationUnitJvmKt.c(j2, unit, durationUnit));
        }
        f2 = RangesKt___RangesKt.f(DurationUnitKt__DurationUnitJvmKt.b(j2, unit, DurationUnit.MILLISECONDS), -4611686018427387903L, 4611686018427387903L);
        return i(f2);
    }
}
