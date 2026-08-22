package kotlin;

import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;

@SinceKotlin
@Metadata
@JvmInline
@WasExperimental
/* loaded from: classes2.dex */
public final class UInt implements Comparable<UInt> {

    /* renamed from: h, reason: collision with root package name */
    public static final Companion f18272h = new Companion(null);

    /* renamed from: c, reason: collision with root package name */
    private final int f18273c;

    @Metadata
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private /* synthetic */ UInt(int i2) {
        this.f18273c = i2;
    }

    public static final /* synthetic */ UInt c(int i2) {
        return new UInt(i2);
    }

    public static int d(int i2) {
        return i2;
    }

    public static boolean e(int i2, Object obj) {
        return (obj instanceof UInt) && i2 == ((UInt) obj).j();
    }

    public static int f(int i2) {
        return Integer.hashCode(i2);
    }

    public static String h(int i2) {
        return String.valueOf(i2 & 4294967295L);
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(UInt uInt) {
        return UnsignedKt.a(j(), uInt.j());
    }

    public boolean equals(Object obj) {
        return e(this.f18273c, obj);
    }

    public int hashCode() {
        return f(this.f18273c);
    }

    public final /* synthetic */ int j() {
        return this.f18273c;
    }

    public String toString() {
        return h(this.f18273c);
    }
}
