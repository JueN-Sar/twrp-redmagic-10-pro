package kotlin;

import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@SinceKotlin
@Metadata
@JvmInline
@WasExperimental
/* loaded from: classes2.dex */
public final class UShort implements Comparable<UShort> {

    /* renamed from: h, reason: collision with root package name */
    public static final Companion f18283h = new Companion(null);

    /* renamed from: c, reason: collision with root package name */
    private final short f18284c;

    @Metadata
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private /* synthetic */ UShort(short s2) {
        this.f18284c = s2;
    }

    public static final /* synthetic */ UShort c(short s2) {
        return new UShort(s2);
    }

    public static short d(short s2) {
        return s2;
    }

    public static boolean e(short s2, Object obj) {
        return (obj instanceof UShort) && s2 == ((UShort) obj).j();
    }

    public static int f(short s2) {
        return Short.hashCode(s2);
    }

    public static String h(short s2) {
        return String.valueOf(s2 & 65535);
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(UShort uShort) {
        return Intrinsics.f(j() & 65535, uShort.j() & 65535);
    }

    public boolean equals(Object obj) {
        return e(this.f18284c, obj);
    }

    public int hashCode() {
        return f(this.f18284c);
    }

    public final /* synthetic */ short j() {
        return this.f18284c;
    }

    public String toString() {
        return h(this.f18284c);
    }
}
