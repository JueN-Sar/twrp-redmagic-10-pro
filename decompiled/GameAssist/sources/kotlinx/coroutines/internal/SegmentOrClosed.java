package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.Segment;

@Metadata
@JvmInline
/* loaded from: classes2.dex */
public final class SegmentOrClosed<S extends Segment<S>> {

    /* renamed from: a, reason: collision with root package name */
    private final Object f19402a;

    public static Object a(Object obj) {
        return obj;
    }

    public static boolean b(Object obj, Object obj2) {
        return (obj2 instanceof SegmentOrClosed) && Intrinsics.a(obj, ((SegmentOrClosed) obj2).g());
    }

    public static final Segment c(Object obj) {
        Symbol symbol;
        symbol = ConcurrentLinkedListKt.f19345a;
        if (obj == symbol) {
            throw new IllegalStateException("Does not contain segment".toString());
        }
        if (obj != null) {
            return (Segment) obj;
        }
        throw new NullPointerException("null cannot be cast to non-null type S of kotlinx.coroutines.internal.SegmentOrClosed");
    }

    public static int d(Object obj) {
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public static final boolean e(Object obj) {
        Symbol symbol;
        symbol = ConcurrentLinkedListKt.f19345a;
        return obj == symbol;
    }

    public static String f(Object obj) {
        return "SegmentOrClosed(value=" + obj + ')';
    }

    public boolean equals(Object obj) {
        return b(this.f19402a, obj);
    }

    public final /* synthetic */ Object g() {
        return this.f19402a;
    }

    public int hashCode() {
        return d(this.f19402a);
    }

    public String toString() {
        return f(this.f19402a);
    }
}
