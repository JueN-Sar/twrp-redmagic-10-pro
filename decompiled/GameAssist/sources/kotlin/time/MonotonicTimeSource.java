package kotlin.time;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.time.TimeSource;

@SinceKotlin
@Metadata
@ExperimentalTime
/* loaded from: classes2.dex */
public final class MonotonicTimeSource implements TimeSource.WithComparableMarks {

    /* renamed from: a, reason: collision with root package name */
    public static final MonotonicTimeSource f18810a = new MonotonicTimeSource();

    /* renamed from: b, reason: collision with root package name */
    private static final long f18811b = System.nanoTime();

    private MonotonicTimeSource() {
    }

    public final long a(long j2, long j3) {
        return LongSaturatedMathKt.b(j2, j3);
    }

    public String toString() {
        return "TimeSource(System.nanoTime())";
    }
}
