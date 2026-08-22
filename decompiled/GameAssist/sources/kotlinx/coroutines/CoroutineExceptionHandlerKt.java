package kotlinx.coroutines;

import kotlin.ExceptionsKt__ExceptionsKt;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;

@Metadata
/* loaded from: classes2.dex */
public final class CoroutineExceptionHandlerKt {
    public static final void a(CoroutineContext coroutineContext, Throwable th) {
        try {
            CoroutineExceptionHandler coroutineExceptionHandler = (CoroutineExceptionHandler) coroutineContext.c(CoroutineExceptionHandler.f18849e);
            if (coroutineExceptionHandler != null) {
                coroutineExceptionHandler.P(coroutineContext, th);
            } else {
                CoroutineExceptionHandlerImplKt.a(coroutineContext, th);
            }
        } catch (Throwable th2) {
            CoroutineExceptionHandlerImplKt.a(coroutineContext, b(th, th2));
        }
    }

    public static final Throwable b(Throwable th, Throwable th2) {
        if (th == th2) {
            return th;
        }
        RuntimeException runtimeException = new RuntimeException("Exception while trying to handle coroutine exception", th2);
        ExceptionsKt__ExceptionsKt.a(runtimeException, th);
        return runtimeException;
    }
}
