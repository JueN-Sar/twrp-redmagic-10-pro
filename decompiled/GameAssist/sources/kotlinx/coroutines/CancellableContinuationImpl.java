package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.internal.DispatchedContinuation;
import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.NotNull;

@Metadata
@PublishedApi
/* loaded from: classes2.dex */
public class CancellableContinuationImpl<T> extends DispatchedTask<T> implements CancellableContinuation<T>, CoroutineStackFrame {

    /* renamed from: m, reason: collision with root package name */
    private static final /* synthetic */ AtomicIntegerFieldUpdater f18830m = AtomicIntegerFieldUpdater.newUpdater(CancellableContinuationImpl.class, "_decision");

    /* renamed from: n, reason: collision with root package name */
    private static final /* synthetic */ AtomicReferenceFieldUpdater f18831n = AtomicReferenceFieldUpdater.newUpdater(CancellableContinuationImpl.class, Object.class, "_state");

    @NotNull
    private volatile /* synthetic */ int _decision;

    @NotNull
    private volatile /* synthetic */ Object _state;

    /* renamed from: j, reason: collision with root package name */
    private final Continuation f18832j;

    /* renamed from: k, reason: collision with root package name */
    private final CoroutineContext f18833k;

    /* renamed from: l, reason: collision with root package name */
    private DisposableHandle f18834l;

    public CancellableContinuationImpl(Continuation continuation, int i2) {
        super(i2);
        this.f18832j = continuation;
        this.f18833k = continuation.getContext();
        this._decision = 0;
        this._state = Active.f18819c;
    }

    private final DisposableHandle A() {
        Job job = (Job) getContext().c(Job.f18898f);
        if (job == null) {
            return null;
        }
        DisposableHandle d2 = Job.DefaultImpls.d(job, true, false, new ChildContinuation(this), 2, null);
        this.f18834l = d2;
        return d2;
    }

    private final boolean E() {
        return DispatchedTaskKt.c(this.f18866i) && ((DispatchedContinuation) this.f18832j).p();
    }

    private final CancelHandler F(Function1 function1) {
        return function1 instanceof CancelHandler ? (CancelHandler) function1 : new InvokeOnCancel(function1);
    }

    private final void G(Function1 function1, Object obj) {
        throw new IllegalStateException(("It's prohibited to register multiple handlers, tried to register " + function1 + ", already has " + obj).toString());
    }

    private final void K() {
        Throwable t;
        Continuation continuation = this.f18832j;
        DispatchedContinuation dispatchedContinuation = continuation instanceof DispatchedContinuation ? (DispatchedContinuation) continuation : null;
        if (dispatchedContinuation == null || (t = dispatchedContinuation.t(this)) == null) {
            return;
        }
        s();
        p(t);
    }

    private final void M(Object obj, int i2, Function1 function1) {
        Object obj2;
        do {
            obj2 = this._state;
            if (!(obj2 instanceof NotCompleted)) {
                if (obj2 instanceof CancelledContinuation) {
                    CancelledContinuation cancelledContinuation = (CancelledContinuation) obj2;
                    if (cancelledContinuation.c()) {
                        if (function1 != null) {
                            o(function1, cancelledContinuation.f18845a);
                            return;
                        }
                        return;
                    }
                }
                j(obj);
                throw new KotlinNothingValueException();
            }
        } while (!f18831n.compareAndSet(this, obj2, P((NotCompleted) obj2, obj, i2, function1, null)));
        t();
        u(i2);
    }

    static /* synthetic */ void N(CancellableContinuationImpl cancellableContinuationImpl, Object obj, int i2, Function1 function1, int i3, Object obj2) {
        if (obj2 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: resumeImpl");
        }
        if ((i3 & 4) != 0) {
            function1 = null;
        }
        cancellableContinuationImpl.M(obj, i2, function1);
    }

    private final Object P(NotCompleted notCompleted, Object obj, int i2, Function1 function1, Object obj2) {
        if (obj instanceof CompletedExceptionally) {
            return obj;
        }
        if (!DispatchedTaskKt.b(i2) && obj2 == null) {
            return obj;
        }
        if (function1 != null || (((notCompleted instanceof CancelHandler) && !(notCompleted instanceof BeforeResumeCancelHandler)) || obj2 != null)) {
            return new CompletedContinuation(obj, notCompleted instanceof CancelHandler ? (CancelHandler) notCompleted : null, function1, obj2, null, 16, null);
        }
        return obj;
    }

    private final boolean Q() {
        do {
            int i2 = this._decision;
            if (i2 != 0) {
                if (i2 == 1) {
                    return false;
                }
                throw new IllegalStateException("Already resumed".toString());
            }
        } while (!f18830m.compareAndSet(this, 0, 2));
        return true;
    }

    private final Symbol R(Object obj, Object obj2, Function1 function1) {
        Object obj3;
        do {
            obj3 = this._state;
            if (!(obj3 instanceof NotCompleted)) {
                if ((obj3 instanceof CompletedContinuation) && obj2 != null && ((CompletedContinuation) obj3).f18842d == obj2) {
                    return CancellableContinuationImplKt.f18835a;
                }
                return null;
            }
        } while (!f18831n.compareAndSet(this, obj3, P((NotCompleted) obj3, obj, this.f18866i, function1, obj2)));
        t();
        return CancellableContinuationImplKt.f18835a;
    }

    private final boolean S() {
        do {
            int i2 = this._decision;
            if (i2 != 0) {
                if (i2 == 2) {
                    return false;
                }
                throw new IllegalStateException("Already suspended".toString());
            }
        } while (!f18830m.compareAndSet(this, 0, 1));
        return true;
    }

    private final Void j(Object obj) {
        throw new IllegalStateException(("Already resumed, but proposed with update " + obj).toString());
    }

    private final void k(Function1 function1, Throwable th) {
        try {
            function1.c(th);
        } catch (Throwable th2) {
            CoroutineExceptionHandlerKt.a(getContext(), new CompletionHandlerException("Exception in invokeOnCancellation handler for " + this, th2));
        }
    }

    private final boolean q(Throwable th) {
        if (E()) {
            return ((DispatchedContinuation) this.f18832j).q(th);
        }
        return false;
    }

    private final void t() {
        if (E()) {
            return;
        }
        s();
    }

    private final void u(int i2) {
        if (Q()) {
            return;
        }
        DispatchedTaskKt.a(this, i2);
    }

    private final String y() {
        Object x = x();
        return x instanceof NotCompleted ? "Active" : x instanceof CancelledContinuation ? "Cancelled" : "Completed";
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public StackTraceElement B() {
        return null;
    }

    public boolean C() {
        return !(x() instanceof NotCompleted);
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public Object D(Object obj, Object obj2, Function1 function1) {
        return R(obj, obj2, function1);
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public void H(CoroutineDispatcher coroutineDispatcher, Object obj) {
        Continuation continuation = this.f18832j;
        DispatchedContinuation dispatchedContinuation = continuation instanceof DispatchedContinuation ? (DispatchedContinuation) continuation : null;
        N(this, obj, (dispatchedContinuation != null ? dispatchedContinuation.f19350j : null) == coroutineDispatcher ? 4 : this.f18866i, null, 4, null);
    }

    protected String I() {
        return "CancellableContinuation";
    }

    public final void J(Throwable th) {
        if (q(th)) {
            return;
        }
        p(th);
        t();
    }

    public final boolean L() {
        Object obj = this._state;
        if ((obj instanceof CompletedContinuation) && ((CompletedContinuation) obj).f18842d != null) {
            s();
            return false;
        }
        this._decision = 0;
        this._state = Active.f18819c;
        return true;
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public void O(Object obj) {
        u(this.f18866i);
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public void a(Object obj, Throwable th) {
        while (true) {
            Object obj2 = this._state;
            if (obj2 instanceof NotCompleted) {
                throw new IllegalStateException("Not completed".toString());
            }
            if (obj2 instanceof CompletedExceptionally) {
                return;
            }
            if (obj2 instanceof CompletedContinuation) {
                CompletedContinuation completedContinuation = (CompletedContinuation) obj2;
                if (!(!completedContinuation.c())) {
                    throw new IllegalStateException("Must be called at most once".toString());
                }
                if (f18831n.compareAndSet(this, obj2, CompletedContinuation.b(completedContinuation, null, null, null, null, th, 15, null))) {
                    completedContinuation.d(this, th);
                    return;
                }
            } else if (f18831n.compareAndSet(this, obj2, new CompletedContinuation(obj2, null, null, null, th, 14, null))) {
                return;
            }
        }
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public final Continuation b() {
        return this.f18832j;
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public Throwable c(Object obj) {
        Throwable c2 = super.c(obj);
        if (c2 != null) {
            return c2;
        }
        return null;
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public Object d(Object obj, Object obj2) {
        return R(obj, obj2, null);
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public Object e(Object obj) {
        return obj instanceof CompletedContinuation ? ((CompletedContinuation) obj).f18839a : obj;
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public CoroutineStackFrame f() {
        Continuation continuation = this.f18832j;
        if (continuation instanceof CoroutineStackFrame) {
            return (CoroutineStackFrame) continuation;
        }
        return null;
    }

    @Override // kotlin.coroutines.Continuation
    public void g(Object obj) {
        N(this, CompletionStateKt.c(obj, this), this.f18866i, null, 4, null);
    }

    @Override // kotlin.coroutines.Continuation
    public CoroutineContext getContext() {
        return this.f18833k;
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public Object i() {
        return x();
    }

    public final void l(CancelHandler cancelHandler, Throwable th) {
        try {
            cancelHandler.d(th);
        } catch (Throwable th2) {
            CoroutineExceptionHandlerKt.a(getContext(), new CompletionHandlerException("Exception in invokeOnCancellation handler for " + this, th2));
        }
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public void m(Function1 function1) {
        CancelHandler F = F(function1);
        while (true) {
            Object obj = this._state;
            if (obj instanceof Active) {
                if (f18831n.compareAndSet(this, obj, F)) {
                    return;
                }
            } else if (obj instanceof CancelHandler) {
                G(function1, obj);
            } else {
                if (obj instanceof CompletedExceptionally) {
                    CompletedExceptionally completedExceptionally = (CompletedExceptionally) obj;
                    if (!completedExceptionally.b()) {
                        G(function1, obj);
                    }
                    if (obj instanceof CancelledContinuation) {
                        if (!(obj instanceof CompletedExceptionally)) {
                            completedExceptionally = null;
                        }
                        k(function1, completedExceptionally != null ? completedExceptionally.f18845a : null);
                        return;
                    }
                    return;
                }
                if (obj instanceof CompletedContinuation) {
                    CompletedContinuation completedContinuation = (CompletedContinuation) obj;
                    if (completedContinuation.f18840b != null) {
                        G(function1, obj);
                    }
                    if (F instanceof BeforeResumeCancelHandler) {
                        return;
                    }
                    if (completedContinuation.c()) {
                        k(function1, completedContinuation.f18843e);
                        return;
                    } else {
                        if (f18831n.compareAndSet(this, obj, CompletedContinuation.b(completedContinuation, null, F, null, null, null, 29, null))) {
                            return;
                        }
                    }
                } else {
                    if (F instanceof BeforeResumeCancelHandler) {
                        return;
                    }
                    if (f18831n.compareAndSet(this, obj, new CompletedContinuation(obj, F, null, null, null, 28, null))) {
                        return;
                    }
                }
            }
        }
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public Object n(Throwable th) {
        return R(new CompletedExceptionally(th, false, 2, null), null, null);
    }

    public final void o(Function1 function1, Throwable th) {
        try {
            function1.c(th);
        } catch (Throwable th2) {
            CoroutineExceptionHandlerKt.a(getContext(), new CompletionHandlerException("Exception in resume onCancellation handler for " + this, th2));
        }
    }

    public boolean p(Throwable th) {
        Object obj;
        boolean z;
        do {
            obj = this._state;
            if (!(obj instanceof NotCompleted)) {
                return false;
            }
            z = obj instanceof CancelHandler;
        } while (!f18831n.compareAndSet(this, obj, new CancelledContinuation(this, th, z)));
        CancelHandler cancelHandler = z ? (CancelHandler) obj : null;
        if (cancelHandler != null) {
            l(cancelHandler, th);
        }
        t();
        u(this.f18866i);
        return true;
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public void r(Object obj, Function1 function1) {
        M(obj, this.f18866i, function1);
    }

    public final void s() {
        DisposableHandle disposableHandle = this.f18834l;
        if (disposableHandle == null) {
            return;
        }
        disposableHandle.dispose();
        this.f18834l = NonDisposableHandle.f18921c;
    }

    public String toString() {
        return I() + '(' + DebugStringsKt.c(this.f18832j) + "){" + y() + "}@" + DebugStringsKt.b(this);
    }

    public Throwable v(Job job) {
        return job.s();
    }

    public final Object w() {
        Job job;
        Object d2;
        boolean E = E();
        if (S()) {
            if (this.f18834l == null) {
                A();
            }
            if (E) {
                K();
            }
            d2 = IntrinsicsKt__IntrinsicsKt.d();
            return d2;
        }
        if (E) {
            K();
        }
        Object x = x();
        if (x instanceof CompletedExceptionally) {
            throw ((CompletedExceptionally) x).f18845a;
        }
        if (!DispatchedTaskKt.b(this.f18866i) || (job = (Job) getContext().c(Job.f18898f)) == null || job.isActive()) {
            return e(x);
        }
        CancellationException s2 = job.s();
        a(x, s2);
        throw s2;
    }

    public final Object x() {
        return this._state;
    }

    public void z() {
        DisposableHandle A = A();
        if (A != null && C()) {
            A.dispose();
            this.f18834l = NonDisposableHandle.f18921c;
        }
    }
}
