package kotlinx.coroutines.selects;

import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuationImplKt;
import kotlinx.coroutines.CompletedExceptionally;
import kotlinx.coroutines.CompletionStateKt;
import kotlinx.coroutines.CoroutineExceptionHandlerKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobCancellingNode;
import kotlinx.coroutines.internal.AtomicDesc;
import kotlinx.coroutines.internal.AtomicOp;
import kotlinx.coroutines.internal.LockFreeLinkedListHead;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.OpDescriptor;
import kotlinx.coroutines.intrinsics.CancellableKt;
import kotlinx.coroutines.intrinsics.UndispatchedKt;
import org.jetbrains.annotations.NotNull;

@Metadata
@PublishedApi
/* loaded from: classes2.dex */
public final class SelectBuilderImpl<R> extends LockFreeLinkedListHead implements SelectBuilder<R>, SelectInstance<R>, Continuation<R>, CoroutineStackFrame {

    /* renamed from: k, reason: collision with root package name */
    static final /* synthetic */ AtomicReferenceFieldUpdater f19477k = AtomicReferenceFieldUpdater.newUpdater(SelectBuilderImpl.class, Object.class, "_state");

    /* renamed from: l, reason: collision with root package name */
    private static final /* synthetic */ AtomicReferenceFieldUpdater f19478l = AtomicReferenceFieldUpdater.newUpdater(SelectBuilderImpl.class, Object.class, "_result");

    @NotNull
    private volatile /* synthetic */ Object _parentHandle;

    @NotNull
    private volatile /* synthetic */ Object _result;

    @NotNull
    volatile /* synthetic */ Object _state = SelectKt.e();

    /* renamed from: j, reason: collision with root package name */
    private final Continuation f19479j;

    @Metadata
    private static final class AtomicSelectOp extends AtomicOp<Object> {

        /* renamed from: b, reason: collision with root package name */
        public final SelectBuilderImpl f19482b;

        /* renamed from: c, reason: collision with root package name */
        public final AtomicDesc f19483c;

        /* renamed from: d, reason: collision with root package name */
        private final long f19484d;

        public AtomicSelectOp(SelectBuilderImpl selectBuilderImpl, AtomicDesc atomicDesc) {
            SeqNumber seqNumber;
            this.f19482b = selectBuilderImpl;
            this.f19483c = atomicDesc;
            seqNumber = SelectKt.f19492e;
            this.f19484d = seqNumber.a();
            atomicDesc.d(this);
        }

        private final void j(Object obj) {
            boolean z = obj == null;
            if (SelectBuilderImpl.f19477k.compareAndSet(this.f19482b, this, z ? null : SelectKt.e()) && z) {
                this.f19482b.g0();
            }
        }

        private final Object k() {
            SelectBuilderImpl selectBuilderImpl = this.f19482b;
            while (true) {
                Object obj = selectBuilderImpl._state;
                if (obj == this) {
                    return null;
                }
                if (obj instanceof OpDescriptor) {
                    ((OpDescriptor) obj).c(this.f19482b);
                } else {
                    if (obj != SelectKt.e()) {
                        return SelectKt.d();
                    }
                    if (SelectBuilderImpl.f19477k.compareAndSet(this.f19482b, SelectKt.e(), this)) {
                        return null;
                    }
                }
            }
        }

        private final void l() {
            SelectBuilderImpl.f19477k.compareAndSet(this.f19482b, this, SelectKt.e());
        }

        @Override // kotlinx.coroutines.internal.AtomicOp
        public void d(Object obj, Object obj2) {
            j(obj2);
            this.f19483c.a(this, obj2);
        }

        @Override // kotlinx.coroutines.internal.AtomicOp
        public long g() {
            return this.f19484d;
        }

        @Override // kotlinx.coroutines.internal.AtomicOp
        public Object i(Object obj) {
            Object k2;
            if (obj == null && (k2 = k()) != null) {
                return k2;
            }
            try {
                return this.f19483c.c(this);
            } catch (Throwable th) {
                if (obj == null) {
                    this.l();
                }
                throw th;
            }
        }

        @Override // kotlinx.coroutines.internal.OpDescriptor
        public String toString() {
            return "AtomicSelectOp(sequence=" + g() + ')';
        }
    }

    @Metadata
    private static final class DisposeNode extends LockFreeLinkedListNode {

        /* renamed from: j, reason: collision with root package name */
        public final DisposableHandle f19485j;

        public DisposeNode(DisposableHandle disposableHandle) {
            this.f19485j = disposableHandle;
        }
    }

    @Metadata
    private static final class PairSelectOp extends OpDescriptor {

        /* renamed from: a, reason: collision with root package name */
        public final LockFreeLinkedListNode.PrepareOp f19486a;

        public PairSelectOp(LockFreeLinkedListNode.PrepareOp prepareOp) {
            this.f19486a = prepareOp;
        }

        @Override // kotlinx.coroutines.internal.OpDescriptor
        public AtomicOp a() {
            return this.f19486a.a();
        }

        @Override // kotlinx.coroutines.internal.OpDescriptor
        public Object c(Object obj) {
            if (obj == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.selects.SelectBuilderImpl<*>");
            }
            SelectBuilderImpl selectBuilderImpl = (SelectBuilderImpl) obj;
            this.f19486a.d();
            Object e2 = this.f19486a.a().e(null);
            SelectBuilderImpl.f19477k.compareAndSet(selectBuilderImpl, this, e2 == null ? this.f19486a.f19377c : SelectKt.e());
            return e2;
        }
    }

    @Metadata
    private final class SelectOnCancelling extends JobCancellingNode {
        public SelectOnCancelling() {
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object c(Object obj) {
            d0((Throwable) obj);
            return Unit.f18288a;
        }

        @Override // kotlinx.coroutines.CompletionHandlerBase
        public void d0(Throwable th) {
            if (SelectBuilderImpl.this.w()) {
                SelectBuilderImpl.this.A(e0().s());
            }
        }
    }

    public SelectBuilderImpl(Continuation continuation) {
        Object obj;
        this.f19479j = continuation;
        obj = SelectKt.f19490c;
        this._result = obj;
        this._parentHandle = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void g0() {
        DisposableHandle h0 = h0();
        if (h0 != null) {
            h0.dispose();
        }
        for (LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) S(); !Intrinsics.a(lockFreeLinkedListNode, this); lockFreeLinkedListNode = lockFreeLinkedListNode.T()) {
            if (lockFreeLinkedListNode instanceof DisposeNode) {
                ((DisposeNode) lockFreeLinkedListNode).f19485j.dispose();
            }
        }
    }

    private final DisposableHandle h0() {
        return (DisposableHandle) this._parentHandle;
    }

    private final void k0() {
        Job job = (Job) getContext().c(Job.f18898f);
        if (job == null) {
            return;
        }
        DisposableHandle d2 = Job.DefaultImpls.d(job, true, false, new SelectOnCancelling(), 2, null);
        l0(d2);
        if (k()) {
            d2.dispose();
        }
    }

    private final void l0(DisposableHandle disposableHandle) {
        this._parentHandle = disposableHandle;
    }

    @Override // kotlinx.coroutines.selects.SelectInstance
    public void A(Throwable th) {
        Object obj;
        Object obj2;
        Object d2;
        Object d3;
        Object obj3;
        Continuation c2;
        while (true) {
            Object obj4 = this._result;
            obj = SelectKt.f19490c;
            if (obj4 == obj) {
                CompletedExceptionally completedExceptionally = new CompletedExceptionally(th, false, 2, null);
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f19478l;
                obj2 = SelectKt.f19490c;
                if (atomicReferenceFieldUpdater.compareAndSet(this, obj2, completedExceptionally)) {
                    return;
                }
            } else {
                d2 = IntrinsicsKt__IntrinsicsKt.d();
                if (obj4 != d2) {
                    throw new IllegalStateException("Already resumed");
                }
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = f19478l;
                d3 = IntrinsicsKt__IntrinsicsKt.d();
                obj3 = SelectKt.f19491d;
                if (atomicReferenceFieldUpdater2.compareAndSet(this, d3, obj3)) {
                    c2 = IntrinsicsKt__IntrinsicsJvmKt.c(this.f19479j);
                    Result.Companion companion = Result.Companion;
                    c2.g(Result.b(ResultKt.a(th)));
                    return;
                }
            }
        }
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public StackTraceElement B() {
        return null;
    }

    @Override // kotlinx.coroutines.selects.SelectInstance
    public Object C(AtomicDesc atomicDesc) {
        return new AtomicSelectOp(this, atomicDesc).c(null);
    }

    @Override // kotlinx.coroutines.selects.SelectBuilder
    public void G(SelectClause1 selectClause1, Function2 function2) {
        selectClause1.b(this, function2);
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public CoroutineStackFrame f() {
        Continuation continuation = this.f19479j;
        if (continuation instanceof CoroutineStackFrame) {
            return (CoroutineStackFrame) continuation;
        }
        return null;
    }

    @Override // kotlin.coroutines.Continuation
    public void g(Object obj) {
        Object obj2;
        Object obj3;
        Object d2;
        Object d3;
        Object obj4;
        while (true) {
            Object obj5 = this._result;
            obj2 = SelectKt.f19490c;
            if (obj5 == obj2) {
                Object d4 = CompletionStateKt.d(obj, null, 1, null);
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f19478l;
                obj3 = SelectKt.f19490c;
                if (atomicReferenceFieldUpdater.compareAndSet(this, obj3, d4)) {
                    return;
                }
            } else {
                d2 = IntrinsicsKt__IntrinsicsKt.d();
                if (obj5 != d2) {
                    throw new IllegalStateException("Already resumed");
                }
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = f19478l;
                d3 = IntrinsicsKt__IntrinsicsKt.d();
                obj4 = SelectKt.f19491d;
                if (atomicReferenceFieldUpdater2.compareAndSet(this, d3, obj4)) {
                    if (!Result.f(obj)) {
                        this.f19479j.g(obj);
                        return;
                    }
                    Continuation continuation = this.f19479j;
                    Throwable d5 = Result.d(obj);
                    Intrinsics.b(d5);
                    continuation.g(Result.b(ResultKt.a(d5)));
                    return;
                }
            }
        }
    }

    @Override // kotlin.coroutines.Continuation
    public CoroutineContext getContext() {
        return this.f19479j.getContext();
    }

    public final Object i0() {
        Object obj;
        Object obj2;
        Object obj3;
        Object d2;
        Object d3;
        if (!k()) {
            k0();
        }
        Object obj4 = this._result;
        obj = SelectKt.f19490c;
        if (obj4 == obj) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f19478l;
            obj3 = SelectKt.f19490c;
            d2 = IntrinsicsKt__IntrinsicsKt.d();
            if (atomicReferenceFieldUpdater.compareAndSet(this, obj3, d2)) {
                d3 = IntrinsicsKt__IntrinsicsKt.d();
                return d3;
            }
            obj4 = this._result;
        }
        obj2 = SelectKt.f19491d;
        if (obj4 == obj2) {
            throw new IllegalStateException("Already resumed");
        }
        if (obj4 instanceof CompletedExceptionally) {
            throw ((CompletedExceptionally) obj4).f18845a;
        }
        return obj4;
    }

    public final void j0(Throwable th) {
        if (w()) {
            Result.Companion companion = Result.Companion;
            g(Result.b(ResultKt.a(th)));
        } else {
            if (th instanceof CancellationException) {
                return;
            }
            Object i0 = i0();
            if ((i0 instanceof CompletedExceptionally) && ((CompletedExceptionally) i0).f18845a == th) {
                return;
            }
            CoroutineExceptionHandlerKt.a(getContext(), th);
        }
    }

    @Override // kotlinx.coroutines.selects.SelectInstance
    public boolean k() {
        while (true) {
            Object obj = this._state;
            if (obj == SelectKt.e()) {
                return false;
            }
            if (!(obj instanceof OpDescriptor)) {
                return true;
            }
            ((OpDescriptor) obj).c(this);
        }
    }

    @Override // kotlinx.coroutines.selects.SelectBuilder
    public void o(long j2, final Function1 function1) {
        if (j2 > 0) {
            s(DelayKt.c(getContext()).B(j2, new Runnable() { // from class: kotlinx.coroutines.selects.SelectBuilderImpl$onTimeout$$inlined$Runnable$1
                @Override // java.lang.Runnable
                public final void run() {
                    if (SelectBuilderImpl.this.w()) {
                        CancellableKt.c(function1, SelectBuilderImpl.this.x());
                    }
                }
            }, getContext()));
        } else if (w()) {
            UndispatchedKt.b(function1, x());
        }
    }

    @Override // kotlinx.coroutines.selects.SelectInstance
    public void s(DisposableHandle disposableHandle) {
        DisposeNode disposeNode = new DisposeNode(disposableHandle);
        if (!k()) {
            L(disposeNode);
            if (!k()) {
                return;
            }
        }
        disposableHandle.dispose();
    }

    @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
    public String toString() {
        return "SelectInstance(state=" + this._state + ", result=" + this._result + ')';
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x0030, code lost:
    
        g0();
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0035, code lost:
    
        return kotlinx.coroutines.CancellableContinuationImplKt.f18835a;
     */
    @Override // kotlinx.coroutines.selects.SelectInstance
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object v(kotlinx.coroutines.internal.LockFreeLinkedListNode.PrepareOp r4) {
        /*
            r3 = this;
        L0:
            java.lang.Object r0 = r3._state
            java.lang.Object r1 = kotlinx.coroutines.selects.SelectKt.e()
            r2 = 0
            if (r0 != r1) goto L36
            if (r4 != 0) goto L18
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r0 = kotlinx.coroutines.selects.SelectBuilderImpl.f19477k
            java.lang.Object r1 = kotlinx.coroutines.selects.SelectKt.e()
            boolean r0 = r0.compareAndSet(r3, r1, r2)
            if (r0 != 0) goto L30
            goto L0
        L18:
            kotlinx.coroutines.selects.SelectBuilderImpl$PairSelectOp r0 = new kotlinx.coroutines.selects.SelectBuilderImpl$PairSelectOp
            r0.<init>(r4)
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r1 = kotlinx.coroutines.selects.SelectBuilderImpl.f19477k
            java.lang.Object r2 = kotlinx.coroutines.selects.SelectKt.e()
            boolean r1 = r1.compareAndSet(r3, r2, r0)
            if (r1 == 0) goto L0
            java.lang.Object r4 = r0.c(r3)
            if (r4 == 0) goto L30
            return r4
        L30:
            r3.g0()
            kotlinx.coroutines.internal.Symbol r3 = kotlinx.coroutines.CancellableContinuationImplKt.f18835a
            return r3
        L36:
            boolean r1 = r0 instanceof kotlinx.coroutines.internal.OpDescriptor
            if (r1 == 0) goto L6a
            if (r4 == 0) goto L64
            kotlinx.coroutines.internal.AtomicOp r1 = r4.a()
            boolean r2 = r1 instanceof kotlinx.coroutines.selects.SelectBuilderImpl.AtomicSelectOp
            if (r2 == 0) goto L58
            r2 = r1
            kotlinx.coroutines.selects.SelectBuilderImpl$AtomicSelectOp r2 = (kotlinx.coroutines.selects.SelectBuilderImpl.AtomicSelectOp) r2
            kotlinx.coroutines.selects.SelectBuilderImpl r2 = r2.f19482b
            if (r2 == r3) goto L4c
            goto L58
        L4c:
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException
            java.lang.String r4 = "Cannot use matching select clauses on the same object"
            java.lang.String r4 = r4.toString()
            r3.<init>(r4)
            throw r3
        L58:
            r2 = r0
            kotlinx.coroutines.internal.OpDescriptor r2 = (kotlinx.coroutines.internal.OpDescriptor) r2
            boolean r1 = r1.b(r2)
            if (r1 == 0) goto L64
            java.lang.Object r3 = kotlinx.coroutines.internal.AtomicKt.f19340b
            return r3
        L64:
            kotlinx.coroutines.internal.OpDescriptor r0 = (kotlinx.coroutines.internal.OpDescriptor) r0
            r0.c(r3)
            goto L0
        L6a:
            if (r4 != 0) goto L6d
            return r2
        L6d:
            kotlinx.coroutines.internal.LockFreeLinkedListNode$AbstractAtomicDesc r3 = r4.f19377c
            if (r0 != r3) goto L74
            kotlinx.coroutines.internal.Symbol r3 = kotlinx.coroutines.CancellableContinuationImplKt.f18835a
            return r3
        L74:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.selects.SelectBuilderImpl.v(kotlinx.coroutines.internal.LockFreeLinkedListNode$PrepareOp):java.lang.Object");
    }

    @Override // kotlinx.coroutines.selects.SelectInstance
    public boolean w() {
        Object v = v(null);
        if (v == CancellableContinuationImplKt.f18835a) {
            return true;
        }
        if (v == null) {
            return false;
        }
        throw new IllegalStateException(("Unexpected trySelectIdempotent result " + v).toString());
    }

    @Override // kotlinx.coroutines.selects.SelectInstance
    public Continuation x() {
        return this;
    }
}
