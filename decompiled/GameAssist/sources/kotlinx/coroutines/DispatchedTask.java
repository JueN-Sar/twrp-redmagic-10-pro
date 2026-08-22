package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import kotlin.ExceptionsKt__ExceptionsKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.DispatchedContinuation;
import kotlinx.coroutines.internal.ThreadContextKt;
import kotlinx.coroutines.scheduling.Task;
import kotlinx.coroutines.scheduling.TaskContext;

@Metadata
/* loaded from: classes2.dex */
public abstract class DispatchedTask<T> extends Task {

    /* renamed from: i, reason: collision with root package name */
    public int f18866i;

    public DispatchedTask(int i2) {
        this.f18866i = i2;
    }

    public void a(Object obj, Throwable th) {
    }

    public abstract Continuation b();

    public Throwable c(Object obj) {
        CompletedExceptionally completedExceptionally = obj instanceof CompletedExceptionally ? (CompletedExceptionally) obj : null;
        if (completedExceptionally != null) {
            return completedExceptionally.f18845a;
        }
        return null;
    }

    public Object e(Object obj) {
        return obj;
    }

    public final void h(Throwable th, Throwable th2) {
        if (th == null && th2 == null) {
            return;
        }
        if (th != null && th2 != null) {
            ExceptionsKt__ExceptionsKt.a(th, th2);
        }
        if (th == null) {
            th = th2;
        }
        Intrinsics.b(th);
        CoroutineExceptionHandlerKt.a(b().getContext(), new CoroutinesInternalError("Fatal exception in coroutines machinery for " + this + ". Please read KDoc to 'handleFatalException' method and report this incident to maintainers", th));
    }

    public abstract Object i();

    @Override // java.lang.Runnable
    public final void run() {
        Object b2;
        Object b3;
        TaskContext taskContext = this.f19461h;
        try {
            DispatchedContinuation dispatchedContinuation = (DispatchedContinuation) b();
            Continuation continuation = dispatchedContinuation.f19351k;
            Object obj = dispatchedContinuation.f19353m;
            CoroutineContext context = continuation.getContext();
            Object c2 = ThreadContextKt.c(context, obj);
            UndispatchedCoroutine g2 = c2 != ThreadContextKt.f19407a ? CoroutineContextKt.g(continuation, context, c2) : null;
            try {
                CoroutineContext context2 = continuation.getContext();
                Object i2 = i();
                Throwable c3 = c(i2);
                Job job = (c3 == null && DispatchedTaskKt.b(this.f18866i)) ? (Job) context2.c(Job.f18898f) : null;
                if (job != null && !job.isActive()) {
                    CancellationException s2 = job.s();
                    a(i2, s2);
                    Result.Companion companion = Result.Companion;
                    continuation.g(Result.b(ResultKt.a(s2)));
                } else if (c3 != null) {
                    Result.Companion companion2 = Result.Companion;
                    continuation.g(Result.b(ResultKt.a(c3)));
                } else {
                    Result.Companion companion3 = Result.Companion;
                    continuation.g(Result.b(e(i2)));
                }
                Unit unit = Unit.f18288a;
                if (g2 == null || g2.h1()) {
                    ThreadContextKt.a(context, c2);
                }
                try {
                    taskContext.W();
                    b3 = Result.b(Unit.f18288a);
                } catch (Throwable th) {
                    Result.Companion companion4 = Result.Companion;
                    b3 = Result.b(ResultKt.a(th));
                }
                h(null, Result.d(b3));
            } catch (Throwable th2) {
                if (g2 == null || g2.h1()) {
                    ThreadContextKt.a(context, c2);
                }
                throw th2;
            }
        } catch (Throwable th3) {
            try {
                Result.Companion companion5 = Result.Companion;
                taskContext.W();
                b2 = Result.b(Unit.f18288a);
            } catch (Throwable th4) {
                Result.Companion companion6 = Result.Companion;
                b2 = Result.b(ResultKt.a(th4));
            }
            h(th3, Result.d(b2));
        }
    }
}
