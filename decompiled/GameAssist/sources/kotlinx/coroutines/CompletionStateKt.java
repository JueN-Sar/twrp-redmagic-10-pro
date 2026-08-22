package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;

@Metadata
/* loaded from: classes2.dex */
public final class CompletionStateKt {
    public static final Object a(Object obj, Continuation continuation) {
        if (!(obj instanceof CompletedExceptionally)) {
            return Result.b(obj);
        }
        Result.Companion companion = Result.Companion;
        return Result.b(ResultKt.a(((CompletedExceptionally) obj).f18845a));
    }

    public static final Object b(Object obj, Function1 function1) {
        Throwable d2 = Result.d(obj);
        return d2 == null ? function1 != null ? new CompletedWithCancellation(obj, function1) : obj : new CompletedExceptionally(d2, false, 2, null);
    }

    public static final Object c(Object obj, CancellableContinuation cancellableContinuation) {
        Throwable d2 = Result.d(obj);
        return d2 == null ? obj : new CompletedExceptionally(d2, false, 2, null);
    }

    public static /* synthetic */ Object d(Object obj, Function1 function1, int i2, Object obj2) {
        if ((i2 & 1) != 0) {
            function1 = null;
        }
        return b(obj, function1);
    }
}
