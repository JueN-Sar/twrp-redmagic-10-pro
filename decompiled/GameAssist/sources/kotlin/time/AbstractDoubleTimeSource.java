package kotlin.time;

import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.ComparableTimeMark;
import kotlin.time.TimeSource;

@SinceKotlin
@Metadata
@Deprecated
@ExperimentalTime
/* loaded from: classes2.dex */
public abstract class AbstractDoubleTimeSource implements TimeSource.WithComparableMarks {

    /* renamed from: a, reason: collision with root package name */
    private final DurationUnit f18793a;

    @Metadata
    private static final class DoubleTimeMark implements ComparableTimeMark {

        /* renamed from: c, reason: collision with root package name */
        private final double f18794c;

        /* renamed from: h, reason: collision with root package name */
        private final AbstractDoubleTimeSource f18795h;

        /* renamed from: i, reason: collision with root package name */
        private final long f18796i;

        @Override // java.lang.Comparable
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public int compareTo(ComparableTimeMark comparableTimeMark) {
            return ComparableTimeMark.DefaultImpls.a(this, comparableTimeMark);
        }

        public boolean equals(Object obj) {
            return (obj instanceof DoubleTimeMark) && Intrinsics.a(this.f18795h, ((DoubleTimeMark) obj).f18795h) && Duration.n(g((ComparableTimeMark) obj), Duration.f18801h.a());
        }

        @Override // kotlin.time.ComparableTimeMark
        public long g(ComparableTimeMark other) {
            Intrinsics.e(other, "other");
            if (other instanceof DoubleTimeMark) {
                DoubleTimeMark doubleTimeMark = (DoubleTimeMark) other;
                if (Intrinsics.a(this.f18795h, doubleTimeMark.f18795h)) {
                    if (Duration.n(this.f18796i, doubleTimeMark.f18796i) && Duration.F(this.f18796i)) {
                        return Duration.f18801h.a();
                    }
                    long H = Duration.H(this.f18796i, doubleTimeMark.f18796i);
                    long o2 = DurationKt.o(this.f18794c - doubleTimeMark.f18794c, this.f18795h.a());
                    return Duration.n(o2, Duration.L(H)) ? Duration.f18801h.a() : Duration.I(o2, H);
                }
            }
            throw new IllegalArgumentException("Subtracting or comparing time marks from different time sources is not possible: " + this + " and " + other);
        }

        public int hashCode() {
            return Duration.B(Duration.I(DurationKt.o(this.f18794c, this.f18795h.a()), this.f18796i));
        }

        public String toString() {
            return "DoubleTimeMark(" + this.f18794c + DurationUnitKt__DurationUnitKt.d(this.f18795h.a()) + " + " + ((Object) Duration.K(this.f18796i)) + ", " + this.f18795h + ')';
        }
    }

    protected final DurationUnit a() {
        return this.f18793a;
    }
}
