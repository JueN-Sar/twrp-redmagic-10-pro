package androidx.collection;

import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@JvmInline
@SourceDebugExtension
/* loaded from: classes.dex */
public final class FloatFloatPair {

    /* renamed from: a, reason: collision with root package name */
    public final long f1193a;

    public static boolean a(long j2, Object obj) {
        return (obj instanceof FloatFloatPair) && j2 == ((FloatFloatPair) obj).d();
    }

    public static int b(long j2) {
        return Long.hashCode(j2);
    }

    public static String c(long j2) {
        return '(' + Float.intBitsToFloat((int) (j2 >> 32)) + ", " + Float.intBitsToFloat((int) (j2 & 4294967295L)) + ')';
    }

    public final /* synthetic */ long d() {
        return this.f1193a;
    }

    public boolean equals(Object obj) {
        return a(this.f1193a, obj);
    }

    public int hashCode() {
        return b(this.f1193a);
    }

    public String toString() {
        return c(this.f1193a);
    }
}
