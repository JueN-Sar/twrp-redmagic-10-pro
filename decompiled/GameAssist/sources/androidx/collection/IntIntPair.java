package androidx.collection;

import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@JvmInline
@SourceDebugExtension
/* loaded from: classes.dex */
public final class IntIntPair {

    /* renamed from: a, reason: collision with root package name */
    public final long f1236a;

    public static boolean a(long j2, Object obj) {
        return (obj instanceof IntIntPair) && j2 == ((IntIntPair) obj).f();
    }

    public static final int b(long j2) {
        return (int) (j2 >> 32);
    }

    public static final int c(long j2) {
        return (int) (j2 & 4294967295L);
    }

    public static int d(long j2) {
        return Long.hashCode(j2);
    }

    public static String e(long j2) {
        return '(' + b(j2) + ", " + c(j2) + ')';
    }

    public boolean equals(Object obj) {
        return a(this.f1236a, obj);
    }

    public final /* synthetic */ long f() {
        return this.f1236a;
    }

    public int hashCode() {
        return d(this.f1236a);
    }

    public String toString() {
        return e(this.f1236a);
    }
}
