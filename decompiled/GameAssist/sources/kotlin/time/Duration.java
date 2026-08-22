package kotlin.time;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.LongRange;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.text.StringsKt__StringsKt;

@SinceKotlin
@Metadata
@JvmInline
@WasExperimental
@SourceDebugExtension
/* loaded from: classes2.dex */
public final class Duration implements Comparable<Duration> {

    /* renamed from: h, reason: collision with root package name */
    public static final Companion f18801h = new Companion(null);

    /* renamed from: i, reason: collision with root package name */
    private static final long f18802i = j(0);

    /* renamed from: j, reason: collision with root package name */
    private static final long f18803j;

    /* renamed from: k, reason: collision with root package name */
    private static final long f18804k;

    /* renamed from: c, reason: collision with root package name */
    private final long f18805c;

    @Metadata
    public static final class Companion {
        private Companion() {
        }

        public final long a() {
            return Duration.f18802i;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        long i2;
        long i3;
        i2 = DurationKt.i(4611686018427387903L);
        f18803j = i2;
        i3 = DurationKt.i(-4611686018427387903L);
        f18804k = i3;
    }

    private static final long A(long j2) {
        return j2 >> 1;
    }

    public static int B(long j2) {
        return Long.hashCode(j2);
    }

    public static final boolean C(long j2) {
        return !F(j2);
    }

    private static final boolean D(long j2) {
        return (((int) j2) & 1) == 1;
    }

    private static final boolean E(long j2) {
        return (((int) j2) & 1) == 0;
    }

    public static final boolean F(long j2) {
        return j2 == f18803j || j2 == f18804k;
    }

    public static final boolean G(long j2) {
        return j2 < 0;
    }

    public static final long H(long j2, long j3) {
        return I(j2, L(j3));
    }

    public static final long I(long j2, long j3) {
        long j4;
        long l2;
        if (F(j2)) {
            if (C(j3) || (j3 ^ j2) >= 0) {
                return j2;
            }
            throw new IllegalArgumentException("Summing infinite durations of different signs yields an undefined result.");
        }
        if (F(j3)) {
            return j3;
        }
        if ((((int) j2) & 1) != (((int) j3) & 1)) {
            return D(j2) ? d(j2, A(j2), A(j3)) : d(j2, A(j3), A(j2));
        }
        long A = A(j2) + A(j3);
        if (E(j2)) {
            l2 = DurationKt.l(A);
            return l2;
        }
        j4 = DurationKt.j(A);
        return j4;
    }

    public static final long J(long j2, DurationUnit unit) {
        Intrinsics.e(unit, "unit");
        if (j2 == f18803j) {
            return Long.MAX_VALUE;
        }
        if (j2 == f18804k) {
            return Long.MIN_VALUE;
        }
        return DurationUnitKt__DurationUnitJvmKt.b(A(j2), z(j2), unit);
    }

    public static String K(long j2) {
        if (j2 == 0) {
            return "0s";
        }
        if (j2 == f18803j) {
            return "Infinity";
        }
        if (j2 == f18804k) {
            return "-Infinity";
        }
        boolean G = G(j2);
        StringBuilder sb = new StringBuilder();
        if (G) {
            sb.append('-');
        }
        long o2 = o(j2);
        long r2 = r(o2);
        int p2 = p(o2);
        int w = w(o2);
        int y = y(o2);
        int x = x(o2);
        int i2 = 0;
        boolean z = r2 != 0;
        boolean z2 = p2 != 0;
        boolean z3 = w != 0;
        boolean z4 = (y == 0 && x == 0) ? false : true;
        if (z) {
            sb.append(r2);
            sb.append('d');
            i2 = 1;
        }
        if (z2 || (z && (z3 || z4))) {
            int i3 = i2 + 1;
            if (i2 > 0) {
                sb.append(' ');
            }
            sb.append(p2);
            sb.append('h');
            i2 = i3;
        }
        if (z3 || (z4 && (z2 || z))) {
            int i4 = i2 + 1;
            if (i2 > 0) {
                sb.append(' ');
            }
            sb.append(w);
            sb.append('m');
            i2 = i4;
        }
        if (z4) {
            int i5 = i2 + 1;
            if (i2 > 0) {
                sb.append(' ');
            }
            if (y != 0 || z || z2 || z3) {
                e(j2, sb, y, x, 9, "s", false);
            } else if (x >= 1000000) {
                e(j2, sb, x / 1000000, x % 1000000, 6, "ms", false);
            } else if (x >= 1000) {
                e(j2, sb, x / 1000, x % 1000, 3, "us", false);
            } else {
                sb.append(x);
                sb.append("ns");
            }
            i2 = i5;
        }
        if (G && i2 > 1) {
            sb.insert(1, '(').append(')');
        }
        String sb2 = sb.toString();
        Intrinsics.d(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    public static final long L(long j2) {
        long h2;
        h2 = DurationKt.h(-A(j2), ((int) j2) & 1);
        return h2;
    }

    private static final long d(long j2, long j3, long j4) {
        long n2;
        long f2;
        long i2;
        long m2;
        long m3;
        long k2;
        n2 = DurationKt.n(j4);
        long j5 = j3 + n2;
        if (!new LongRange(-4611686018426L, 4611686018426L).j(j5)) {
            f2 = RangesKt___RangesKt.f(j5, -4611686018427387903L, 4611686018427387903L);
            i2 = DurationKt.i(f2);
            return i2;
        }
        m2 = DurationKt.m(n2);
        long j6 = j4 - m2;
        m3 = DurationKt.m(j5);
        k2 = DurationKt.k(m3 + j6);
        return k2;
    }

    private static final void e(long j2, StringBuilder sb, int i2, int i3, int i4, String str, boolean z) {
        String G;
        sb.append(i2);
        if (i3 != 0) {
            sb.append('.');
            G = StringsKt__StringsKt.G(String.valueOf(i3), i4, '0');
            int i5 = -1;
            int length = G.length() - 1;
            if (length >= 0) {
                while (true) {
                    int i6 = length - 1;
                    if (G.charAt(length) != '0') {
                        i5 = length;
                        break;
                    } else if (i6 < 0) {
                        break;
                    } else {
                        length = i6;
                    }
                }
            }
            int i7 = i5 + 1;
            if (z || i7 >= 3) {
                sb.append((CharSequence) G, 0, ((i5 + 3) / 3) * 3);
                Intrinsics.d(sb, "this.append(value, startIndex, endIndex)");
            } else {
                sb.append((CharSequence) G, 0, i7);
                Intrinsics.d(sb, "this.append(value, startIndex, endIndex)");
            }
        }
        sb.append(str);
    }

    public static int h(long j2, long j3) {
        long j4 = j2 ^ j3;
        if (j4 < 0 || (((int) j4) & 1) == 0) {
            return Intrinsics.g(j2, j3);
        }
        int i2 = (((int) j2) & 1) - (((int) j3) & 1);
        return G(j2) ? -i2 : i2;
    }

    public static long j(long j2) {
        if (DurationJvmKt.a()) {
            if (E(j2)) {
                if (!new LongRange(-4611686018426999999L, 4611686018426999999L).j(A(j2))) {
                    throw new AssertionError(A(j2) + " ns is out of nanoseconds range");
                }
            } else {
                if (!new LongRange(-4611686018427387903L, 4611686018427387903L).j(A(j2))) {
                    throw new AssertionError(A(j2) + " ms is out of milliseconds range");
                }
                if (new LongRange(-4611686018426L, 4611686018426L).j(A(j2))) {
                    throw new AssertionError(A(j2) + " ms is denormalized");
                }
            }
        }
        return j2;
    }

    public static boolean l(long j2, Object obj) {
        return (obj instanceof Duration) && j2 == ((Duration) obj).M();
    }

    public static final boolean n(long j2, long j3) {
        return j2 == j3;
    }

    public static final long o(long j2) {
        return G(j2) ? L(j2) : j2;
    }

    public static final int p(long j2) {
        if (F(j2)) {
            return 0;
        }
        return (int) (s(j2) % 24);
    }

    public static final long r(long j2) {
        return J(j2, DurationUnit.DAYS);
    }

    public static final long s(long j2) {
        return J(j2, DurationUnit.HOURS);
    }

    public static final long t(long j2) {
        return (D(j2) && C(j2)) ? A(j2) : J(j2, DurationUnit.MILLISECONDS);
    }

    public static final long u(long j2) {
        return J(j2, DurationUnit.MINUTES);
    }

    public static final long v(long j2) {
        return J(j2, DurationUnit.SECONDS);
    }

    public static final int w(long j2) {
        if (F(j2)) {
            return 0;
        }
        return (int) (u(j2) % 60);
    }

    public static final int x(long j2) {
        if (F(j2)) {
            return 0;
        }
        return (int) (D(j2) ? DurationKt.m(A(j2) % 1000) : A(j2) % 1000000000);
    }

    public static final int y(long j2) {
        if (F(j2)) {
            return 0;
        }
        return (int) (v(j2) % 60);
    }

    private static final DurationUnit z(long j2) {
        return E(j2) ? DurationUnit.NANOSECONDS : DurationUnit.MILLISECONDS;
    }

    public final /* synthetic */ long M() {
        return this.f18805c;
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(Duration duration) {
        return f(duration.M());
    }

    public boolean equals(Object obj) {
        return l(this.f18805c, obj);
    }

    public int f(long j2) {
        return h(this.f18805c, j2);
    }

    public int hashCode() {
        return B(this.f18805c);
    }

    public String toString() {
        return K(this.f18805c);
    }
}
