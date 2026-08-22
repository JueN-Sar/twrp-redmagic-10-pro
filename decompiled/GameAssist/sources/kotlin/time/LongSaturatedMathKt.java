package kotlin.time;

import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.time.Duration;

@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public final class LongSaturatedMathKt {
    private static final long a(long j2, long j3) {
        long j4 = j2 - j3;
        if (((j4 ^ j2) & (~(j4 ^ j3))) >= 0) {
            Duration.Companion companion = Duration.f18801h;
            return DurationKt.q(j4, DurationUnit.NANOSECONDS);
        }
        long j5 = 1000000;
        long j6 = (j2 / j5) - (j3 / j5);
        long j7 = (j2 % j5) - (j3 % j5);
        Duration.Companion companion2 = Duration.f18801h;
        return Duration.I(DurationKt.q(j6, DurationUnit.MILLISECONDS), DurationKt.q(j7, DurationUnit.NANOSECONDS));
    }

    public static final long b(long j2, long j3) {
        return ((j3 - 1) | 1) == Long.MAX_VALUE ? j2 == j3 ? Duration.f18801h.a() : Duration.L(DurationKt.q(j3, DurationUnit.DAYS)) : (1 | (j2 - 1)) == Long.MAX_VALUE ? DurationKt.q(j2, DurationUnit.DAYS) : a(j2, j3);
    }
}
