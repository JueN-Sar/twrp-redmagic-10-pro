package kotlin.time;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.ComparableTimeMark;

@SinceKotlin
@Metadata
@ExperimentalTime
/* loaded from: classes2.dex */
public interface TimeSource {

    @Metadata
    public static final class Companion {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ Companion f18812a = new Companion();

        private Companion() {
        }
    }

    @Metadata
    public static final class Monotonic implements WithComparableMarks {

        /* renamed from: a, reason: collision with root package name */
        public static final Monotonic f18813a = new Monotonic();

        @SinceKotlin
        @Metadata
        @JvmInline
        @ExperimentalTime
        public static final class ValueTimeMark implements ComparableTimeMark {

            /* renamed from: c, reason: collision with root package name */
            private final long f18814c;

            public static boolean d(long j2, Object obj) {
                return (obj instanceof ValueTimeMark) && j2 == ((ValueTimeMark) obj).l();
            }

            public static int e(long j2) {
                return Long.hashCode(j2);
            }

            public static final long f(long j2, long j3) {
                return MonotonicTimeSource.f18810a.a(j2, j3);
            }

            public static long h(long j2, ComparableTimeMark other) {
                Intrinsics.e(other, "other");
                if (other instanceof ValueTimeMark) {
                    return f(j2, ((ValueTimeMark) other).l());
                }
                throw new IllegalArgumentException("Subtracting or comparing time marks from different time sources is not possible: " + ((Object) j(j2)) + " and " + other);
            }

            public static String j(long j2) {
                return "ValueTimeMark(reading=" + j2 + ')';
            }

            @Override // java.lang.Comparable
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public int compareTo(ComparableTimeMark comparableTimeMark) {
                return ComparableTimeMark.DefaultImpls.a(this, comparableTimeMark);
            }

            public boolean equals(Object obj) {
                return d(this.f18814c, obj);
            }

            @Override // kotlin.time.ComparableTimeMark
            public long g(ComparableTimeMark other) {
                Intrinsics.e(other, "other");
                return h(this.f18814c, other);
            }

            public int hashCode() {
                return e(this.f18814c);
            }

            public final /* synthetic */ long l() {
                return this.f18814c;
            }

            public String toString() {
                return j(this.f18814c);
            }
        }

        private Monotonic() {
        }

        public String toString() {
            return MonotonicTimeSource.f18810a.toString();
        }
    }

    @SinceKotlin
    @Metadata
    @ExperimentalTime
    public interface WithComparableMarks extends TimeSource {
    }
}
