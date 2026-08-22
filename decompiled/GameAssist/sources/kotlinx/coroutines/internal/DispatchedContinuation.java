package kotlinx.coroutines.internal;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CompletedWithCancellation;
import kotlinx.coroutines.CompletionStateKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.DispatchedTask;
import kotlinx.coroutines.EventLoop;
import kotlinx.coroutines.ThreadLocalEventLoop;
import org.jetbrains.annotations.NotNull;

@Metadata
/* loaded from: classes2.dex */
public final class DispatchedContinuation<T> extends DispatchedTask<T> implements CoroutineStackFrame, Continuation<T> {

    /* renamed from: n, reason: collision with root package name */
    private static final /* synthetic */ AtomicReferenceFieldUpdater f19349n = AtomicReferenceFieldUpdater.newUpdater(DispatchedContinuation.class, Object.class, "_reusableCancellableContinuation");

    @NotNull
    private volatile /* synthetic */ Object _reusableCancellableContinuation;

    /* renamed from: j, reason: collision with root package name */
    public final CoroutineDispatcher f19350j;

    /* renamed from: k, reason: collision with root package name */
    public final Continuation f19351k;

    /* renamed from: l, reason: collision with root package name */
    public Object f19352l;

    /* renamed from: m, reason: collision with root package name */
    public final Object f19353m;

    public DispatchedContinuation(CoroutineDispatcher coroutineDispatcher, Continuation continuation) {
        super(-1);
        Symbol symbol;
        this.f19350j = coroutineDispatcher;
        this.f19351k = continuation;
        symbol = DispatchedContinuationKt.f19354a;
        this.f19352l = symbol;
        this.f19353m = ThreadContextKt.b(getContext());
        this._reusableCancellableContinuation = null;
    }

    private final CancellableContinuationImpl o() {
        Object obj = this._reusableCancellableContinuation;
        if (obj instanceof CancellableContinuationImpl) {
            return (CancellableContinuationImpl) obj;
        }
        return null;
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public StackTraceElement B() {
        return null;
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public void a(Object obj, Throwable th) {
        if (obj instanceof CompletedWithCancellation) {
            ((CompletedWithCancellation) obj).f18847b.c(th);
        }
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public Continuation b() {
        return this;
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public CoroutineStackFrame f() {
        Continuation continuation = this.f19351k;
        if (continuation instanceof CoroutineStackFrame) {
            return (CoroutineStackFrame) continuation;
        }
        return null;
    }

    @Override // kotlin.coroutines.Continuation
    public void g(Object obj) {
        CoroutineContext context = this.f19351k.getContext();
        Object d2 = CompletionStateKt.d(obj, null, 1, null);
        if (this.f19350j.l0(context)) {
            this.f19352l = d2;
            this.f18866i = 0;
            this.f19350j.j0(context, this);
            return;
        }
        EventLoop a2 = ThreadLocalEventLoop.f18932a.a();
        if (a2.t0()) {
            this.f19352l = d2;
            this.f18866i = 0;
            a2.p0(this);
            return;
        }
        a2.r0(true);
        try {
            CoroutineContext context2 = getContext();
            Object c2 = ThreadContextKt.c(context2, this.f19353m);
            try {
                this.f19351k.g(obj);
                Unit unit = Unit.f18288a;
                while (a2.v0()) {
                }
            } finally {
                ThreadContextKt.a(context2, c2);
            }
        } finally {
            try {
            } finally {
            }
        }
    }

    @Override // kotlin.coroutines.Continuation
    public CoroutineContext getContext() {
        return this.f19351k.getContext();
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public Object i() {
        Symbol symbol;
        Object obj = this.f19352l;
        symbol = DispatchedContinuationKt.f19354a;
        this.f19352l = symbol;
        return obj;
    }

    public final void j() {
        while (this._reusableCancellableContinuation == DispatchedContinuationKt.f19355b) {
        }
    }

    public final CancellableContinuationImpl k() {
        while (true) {
            Object obj = this._reusableCancellableContinuation;
            if (obj == null) {
                this._reusableCancellableContinuation = DispatchedContinuationKt.f19355b;
                return null;
            }
            if (obj instanceof CancellableContinuationImpl) {
                if (f19349n.compareAndSet(this, obj, DispatchedContinuationKt.f19355b)) {
                    return (CancellableContinuationImpl) obj;
                }
            } else if (obj != DispatchedContinuationKt.f19355b && !(obj instanceof Throwable)) {
                throw new IllegalStateException(("Inconsistent state " + obj).toString());
            }
        }
    }

    public final void l(CoroutineContext coroutineContext, Object obj) {
        this.f19352l = obj;
        this.f18866i = 1;
        this.f19350j.k0(coroutineContext, this);
    }

    public final boolean p() {
        return this._reusableCancellableContinuation != null;
    }

    public final boolean q(Throwable th) {
        while (true) {
            Object obj = this._reusableCancellableContinuation;
            Symbol symbol = DispatchedContinuationKt.f19355b;
            if (Intrinsics.a(obj, symbol)) {
                if (f19349n.compareAndSet(this, symbol, th)) {
                    return true;
                }
            } else {
                if (obj instanceof Throwable) {
                    return true;
                }
                if (f19349n.compareAndSet(this, obj, null)) {
                    return false;
                }
            }
        }
    }

    public final void s() {
        j();
        CancellableContinuationImpl o2 = o();
        if (o2 != null) {
            o2.s();
        }
    }

    public final Throwable t(CancellableContinuation cancellableContinuation) {
        Symbol symbol;
        do {
            Object obj = this._reusableCancellableContinuation;
            symbol = DispatchedContinuationKt.f19355b;
            if (obj != symbol) {
                if (obj instanceof Throwable) {
                    if (f19349n.compareAndSet(this, obj, null)) {
                        return (Throwable) obj;
                    }
                    throw new IllegalArgumentException("Failed requirement.".toString());
                }
                throw new IllegalStateException(("Inconsistent state " + obj).toString());
            }
        } while (!f19349n.compareAndSet(this, symbol, cancellableContinuation));
        return null;
    }

    public String toString() {
        return "DispatchedContinuation[" + this.f19350j + ", " + DebugStringsKt.c(this.f19351k) + ']';
    }
}
