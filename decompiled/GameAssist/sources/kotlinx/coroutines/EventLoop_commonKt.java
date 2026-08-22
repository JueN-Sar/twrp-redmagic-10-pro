package kotlinx.coroutines;

import kotlin.Metadata;
import kotlinx.coroutines.internal.Symbol;

@Metadata
/* loaded from: classes2.dex */
public final class EventLoop_commonKt {

    /* renamed from: a, reason: collision with root package name */
    private static final Symbol f18887a = new Symbol("REMOVED_TASK");

    /* renamed from: b, reason: collision with root package name */
    private static final Symbol f18888b = new Symbol("CLOSED_EMPTY");

    public static final long c(long j2) {
        return j2 / 1000000;
    }

    public static final long d(long j2) {
        if (j2 <= 0) {
            return 0L;
        }
        if (j2 >= 9223372036854L) {
            return Long.MAX_VALUE;
        }
        return 1000000 * j2;
    }
}
