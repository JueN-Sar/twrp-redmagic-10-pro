package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.internal.DispatchedContinuation;
import kotlinx.coroutines.internal.ThreadContextKt;

@Metadata
/* loaded from: classes2.dex */
public final class DispatchedTaskKt {
    public static final void a(DispatchedTask dispatchedTask, int i2) {
        Continuation b2 = dispatchedTask.b();
        boolean z = i2 == 4;
        if (z || !(b2 instanceof DispatchedContinuation) || b(i2) != b(dispatchedTask.f18866i)) {
            d(dispatchedTask, b2, z);
            return;
        }
        CoroutineDispatcher coroutineDispatcher = ((DispatchedContinuation) b2).f19350j;
        CoroutineContext context = b2.getContext();
        if (coroutineDispatcher.l0(context)) {
            coroutineDispatcher.j0(context, dispatchedTask);
        } else {
            e(dispatchedTask);
        }
    }

    public static final boolean b(int i2) {
        return i2 == 1 || i2 == 2;
    }

    public static final boolean c(int i2) {
        return i2 == 2;
    }

    public static final void d(DispatchedTask dispatchedTask, Continuation continuation, boolean z) {
        Object e2;
        Object i2 = dispatchedTask.i();
        Throwable c2 = dispatchedTask.c(i2);
        if (c2 != null) {
            Result.Companion companion = Result.Companion;
            e2 = ResultKt.a(c2);
        } else {
            Result.Companion companion2 = Result.Companion;
            e2 = dispatchedTask.e(i2);
        }
        Object b2 = Result.b(e2);
        if (!z) {
            continuation.g(b2);
            return;
        }
        DispatchedContinuation dispatchedContinuation = (DispatchedContinuation) continuation;
        Continuation continuation2 = dispatchedContinuation.f19351k;
        Object obj = dispatchedContinuation.f19353m;
        CoroutineContext context = continuation2.getContext();
        Object c3 = ThreadContextKt.c(context, obj);
        UndispatchedCoroutine g2 = c3 != ThreadContextKt.f19407a ? CoroutineContextKt.g(continuation2, context, c3) : null;
        try {
            dispatchedContinuation.f19351k.g(b2);
            Unit unit = Unit.f18288a;
        } finally {
            if (g2 == null || g2.h1()) {
                ThreadContextKt.a(context, c3);
            }
        }
    }

    private static final void e(DispatchedTask dispatchedTask) {
        EventLoop a2 = ThreadLocalEventLoop.f18932a.a();
        if (a2.t0()) {
            a2.p0(dispatchedTask);
            return;
        }
        a2.r0(true);
        try {
            d(dispatchedTask, dispatchedTask.b(), true);
            do {
            } while (a2.v0());
        } finally {
            try {
            } finally {
            }
        }
    }
}
