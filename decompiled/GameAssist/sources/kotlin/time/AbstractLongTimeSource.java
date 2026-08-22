package kotlin.time;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.time.ComparableTimeMark;
import kotlin.time.TimeSource;

@SinceKotlin
@Metadata
@ExperimentalTime
/* loaded from: classes2.dex */
public abstract class AbstractLongTimeSource implements TimeSource.WithComparableMarks {

    /* renamed from: a, reason: collision with root package name */
    private final DurationUnit f18797a;

    @Metadata
    @SourceDebugExtension
    private static final class LongTimeMark implements ComparableTimeMark {

        /* renamed from: c, reason: collision with root package name */
        private final long f18798c;

        /* renamed from: h, reason: collision with root package name */
        private final AbstractLongTimeSource f18799h;

        /* renamed from: i, reason: collision with root package name */
        private final long f18800i;

        @Override // java.lang.Comparable
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public int compareTo(ComparableTimeMark comparableTimeMark) {
            return ComparableTimeMark.DefaultImpls.a(this, comparableTimeMark);
        }

        public final long d() {
            if (Duration.F(this.f18800i)) {
                return this.f18800i;
            }
            DurationUnit a2 = this.f18799h.a();
            DurationUnit durationUnit = DurationUnit.MILLISECONDS;
            if (a2.compareTo(durationUnit) >= 0) {
                return Duration.I(DurationKt.q(this.f18798c, a2), this.f18800i);
            }
            long b2 = DurationUnitKt__DurationUnitJvmKt.b(1L, durationUnit, a2);
            long j2 = this.f18798c;
            long j3 = j2 / b2;
            long j4 = j2 % b2;
            long j5 = this.f18800i;
            long v = Duration.v(j5);
            return Duration.I(Duration.I(Duration.I(DurationKt.q(j4, a2), DurationKt.p(Duration.x(j5) % 1000000, DurationUnit.NANOSECONDS)), DurationKt.q(j3 + (r12 / 1000000), durationUnit)), DurationKt.q(v, DurationUnit.SECONDS));
        }

        public boolean equals(Object obj) {
            return (obj instanceof LongTimeMark) && Intrinsics.a(this.f18799h, ((LongTimeMark) obj).f18799h) && Duration.n(g((ComparableTimeMark) obj), Duration.f18801h.a());
        }

        @Override // kotlin.time.ComparableTimeMark
        public long g(ComparableTimeMark other) {
            Intrinsics.e(other, "other");
            if (other instanceof LongTimeMark) {
                LongTimeMark longTimeMark = (LongTimeMark) other;
                if (Intrinsics.a(this.f18799h, longTimeMark.f18799h)) {
                    if (Duration.n(this.f18800i, longTimeMark.f18800i) && Duration.F(this.f18800i)) {
                        return Duration.f18801h.a();
                    }
                    long H = Duration.H(this.f18800i, longTimeMark.f18800i);
                    long q2 = DurationKt.q(this.f18798c - longTimeMark.f18798c, this.f18799h.a());
                    return Duration.n(q2, Duration.L(H)) ? Duration.f18801h.a() : Duration.I(q2, H);
                }
            }
            throw new IllegalArgumentException("Subtracting or comparing time marks from different time sources is not possible: " + this + " and " + other);
        }

        public int hashCode() {
            return Duration.B(d());
        }

        public String toString() {
            return "LongTimeMark(" + this.f18798c + DurationUnitKt__DurationUnitKt.d(this.f18799h.a()) + " + " + ((Object) Duration.K(this.f18800i)) + " (=" + ((Object) Duration.K(d())) + "), " + this.f18799h + ')';
        }
    }

    protected final DurationUnit a() {
        return this.f18797a;
    }
}
