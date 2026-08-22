package kotlin;

import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;

@SinceKotlin
@Metadata
@JvmInline
@WasExperimental
/* loaded from: classes2.dex */
public final class ULong implements Comparable<ULong> {

    /* renamed from: h, reason: collision with root package name */
    public static final Companion f18277h = new Companion(null);

    /* renamed from: c, reason: collision with root package name */
    private final long f18278c;

    @Metadata
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private /* synthetic */ ULong(long j2) {
        this.f18278c = j2;
    }

    public static final /* synthetic */ ULong c(long j2) {
        return new ULong(j2);
    }

    public static long d(long j2) {
        return j2;
    }

    public static boolean e(long j2, Object obj) {
        return (obj instanceof ULong) && j2 == ((ULong) obj).j();
    }

    public static int f(long j2) {
        return Long.hashCode(j2);
    }

    public static String h(long j2) {
        return UnsignedKt.c(j2);
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(ULong uLong) {
        return UnsignedKt.b(j(), uLong.j());
    }

    public boolean equals(Object obj) {
        return e(this.f18278c, obj);
    }

    public int hashCode() {
        return f(this.f18278c);
    }

    public final /* synthetic */ long j() {
        return this.f18278c;
    }

    public String toString() {
        return h(this.f18278c);
    }
}
