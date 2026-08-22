package kotlin;

import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@SinceKotlin
@Metadata
@JvmInline
@WasExperimental
/* loaded from: classes2.dex */
public final class UByte implements Comparable<UByte> {

    /* renamed from: h, reason: collision with root package name */
    public static final Companion f18267h = new Companion(null);

    /* renamed from: c, reason: collision with root package name */
    private final byte f18268c;

    @Metadata
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private /* synthetic */ UByte(byte b2) {
        this.f18268c = b2;
    }

    public static final /* synthetic */ UByte c(byte b2) {
        return new UByte(b2);
    }

    public static byte d(byte b2) {
        return b2;
    }

    public static boolean e(byte b2, Object obj) {
        return (obj instanceof UByte) && b2 == ((UByte) obj).j();
    }

    public static int f(byte b2) {
        return Byte.hashCode(b2);
    }

    public static String h(byte b2) {
        return String.valueOf(b2 & 255);
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(UByte uByte) {
        return Intrinsics.f(j() & 255, uByte.j() & 255);
    }

    public boolean equals(Object obj) {
        return e(this.f18268c, obj);
    }

    public int hashCode() {
        return f(this.f18268c);
    }

    public final /* synthetic */ byte j() {
        return this.f18268c;
    }

    public String toString() {
        return h(this.f18268c);
    }
}
