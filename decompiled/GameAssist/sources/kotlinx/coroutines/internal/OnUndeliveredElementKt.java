package kotlinx.coroutines.internal;

import kotlin.ExceptionsKt__ExceptionsKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CoroutineExceptionHandlerKt;

@Metadata
/* loaded from: classes2.dex */
public final class OnUndeliveredElementKt {
    public static final Function1 a(final Function1 function1, final Object obj, final CoroutineContext coroutineContext) {
        return new Function1<Throwable, Unit>() { // from class: kotlinx.coroutines.internal.OnUndeliveredElementKt$bindCancellationFun$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object c(Object obj2) {
                d((Throwable) obj2);
                return Unit.f18288a;
            }

            public final void d(Throwable th) {
                OnUndeliveredElementKt.b(function1, obj, coroutineContext);
            }
        };
    }

    public static final void b(Function1 function1, Object obj, CoroutineContext coroutineContext) {
        UndeliveredElementException c2 = c(function1, obj, null);
        if (c2 != null) {
            CoroutineExceptionHandlerKt.a(coroutineContext, c2);
        }
    }

    public static final UndeliveredElementException c(Function1 function1, Object obj, UndeliveredElementException undeliveredElementException) {
        try {
            function1.c(obj);
        } catch (Throwable th) {
            if (undeliveredElementException == null || undeliveredElementException.getCause() == th) {
                return new UndeliveredElementException("Exception in undelivered element handler for " + obj, th);
            }
            ExceptionsKt__ExceptionsKt.a(undeliveredElementException, th);
        }
        return undeliveredElementException;
    }

    public static /* synthetic */ UndeliveredElementException d(Function1 function1, Object obj, UndeliveredElementException undeliveredElementException, int i2, Object obj2) {
        if ((i2 & 2) != 0) {
            undeliveredElementException = null;
        }
        return c(function1, obj, undeliveredElementException);
    }
}
