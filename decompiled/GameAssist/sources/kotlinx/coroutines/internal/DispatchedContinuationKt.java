package kotlinx.coroutines.internal;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CompletionStateKt;
import kotlinx.coroutines.CoroutineContextKt;
import kotlinx.coroutines.EventLoop;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.ThreadLocalEventLoop;
import kotlinx.coroutines.UndispatchedCoroutine;

@Metadata
/* loaded from: classes2.dex */
public final class DispatchedContinuationKt {

    /* renamed from: a, reason: collision with root package name */
    private static final Symbol f19354a = new Symbol("UNDEFINED");

    /* renamed from: b, reason: collision with root package name */
    public static final Symbol f19355b = new Symbol("REUSABLE_CLAIMED");

    public static final void b(Continuation continuation, Object obj, Function1 function1) {
        if (!(continuation instanceof DispatchedContinuation)) {
            continuation.g(obj);
            return;
        }
        DispatchedContinuation dispatchedContinuation = (DispatchedContinuation) continuation;
        Object b2 = CompletionStateKt.b(obj, function1);
        if (dispatchedContinuation.f19350j.l0(dispatchedContinuation.getContext())) {
            dispatchedContinuation.f19352l = b2;
            dispatchedContinuation.f18866i = 1;
            dispatchedContinuation.f19350j.j0(dispatchedContinuation.getContext(), dispatchedContinuation);
            return;
        }
        EventLoop a2 = ThreadLocalEventLoop.f18932a.a();
        if (a2.t0()) {
            dispatchedContinuation.f19352l = b2;
            dispatchedContinuation.f18866i = 1;
            a2.p0(dispatchedContinuation);
            return;
        }
        a2.r0(true);
        try {
            Job job = (Job) dispatchedContinuation.getContext().c(Job.f18898f);
            if (job == null || job.isActive()) {
                Continuation continuation2 = dispatchedContinuation.f19351k;
                Object obj2 = dispatchedContinuation.f19353m;
                CoroutineContext context = continuation2.getContext();
                Object c2 = ThreadContextKt.c(context, obj2);
                UndispatchedCoroutine g2 = c2 != ThreadContextKt.f19407a ? CoroutineContextKt.g(continuation2, context, c2) : null;
                try {
                    dispatchedContinuation.f19351k.g(obj);
                    Unit unit = Unit.f18288a;
                } finally {
                    if (g2 == null || g2.h1()) {
                        ThreadContextKt.a(context, c2);
                    }
                }
            } else {
                CancellationException s2 = job.s();
                dispatchedContinuation.a(b2, s2);
                Result.Companion companion = Result.Companion;
                dispatchedContinuation.g(Result.b(ResultKt.a(s2)));
            }
            while (a2.v0()) {
            }
        } finally {
            try {
            } finally {
            }
        }
    }

    public static /* synthetic */ void c(Continuation continuation, Object obj, Function1 function1, int i2, Object obj2) {
        if ((i2 & 2) != 0) {
            function1 = null;
        }
        b(continuation, obj, function1);
    }

    public static final boolean d(DispatchedContinuation dispatchedContinuation) {
        Unit unit = Unit.f18288a;
        EventLoop a2 = ThreadLocalEventLoop.f18932a.a();
        if (a2.u0()) {
            return false;
        }
        if (a2.t0()) {
            dispatchedContinuation.f19352l = unit;
            dispatchedContinuation.f18866i = 1;
            a2.p0(dispatchedContinuation);
            return true;
        }
        a2.r0(true);
        try {
            dispatchedContinuation.run();
            do {
            } while (a2.v0());
        } finally {
            try {
                return false;
            } finally {
            }
        }
        return false;
    }
}
