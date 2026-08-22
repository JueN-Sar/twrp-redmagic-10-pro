package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.internal.DispatchedContinuation;

@Metadata
/* loaded from: classes2.dex */
public final class DebugStringsKt {
    public static final String a(Object obj) {
        return obj.getClass().getSimpleName();
    }

    public static final String b(Object obj) {
        return Integer.toHexString(System.identityHashCode(obj));
    }

    public static final String c(Continuation continuation) {
        Object b2;
        if (continuation instanceof DispatchedContinuation) {
            return continuation.toString();
        }
        try {
            Result.Companion companion = Result.Companion;
            b2 = Result.b(continuation + '@' + b(continuation));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            b2 = Result.b(ResultKt.a(th));
        }
        if (Result.d(b2) != null) {
            b2 = continuation.getClass().getName() + '@' + b(continuation);
        }
        return (String) b2;
    }
}
