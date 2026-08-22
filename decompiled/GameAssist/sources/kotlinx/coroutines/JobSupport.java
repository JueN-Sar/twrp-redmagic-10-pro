package kotlinx.coroutines;

import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Deprecated;
import kotlin.ExceptionsKt__ExceptionsKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.internal.LockFreeLinkedListKt;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.OpDescriptor;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.intrinsics.CancellableKt;
import kotlinx.coroutines.intrinsics.UndispatchedKt;
import kotlinx.coroutines.selects.SelectClause0;
import kotlinx.coroutines.selects.SelectInstance;
import org.jetbrains.annotations.NotNull;

@Deprecated
@Metadata
/* loaded from: classes2.dex */
public class JobSupport implements Job, ChildJob, ParentJob, SelectClause0 {

    /* renamed from: c, reason: collision with root package name */
    private static final /* synthetic */ AtomicReferenceFieldUpdater f18902c = AtomicReferenceFieldUpdater.newUpdater(JobSupport.class, Object.class, "_state");

    @NotNull
    private volatile /* synthetic */ Object _parentHandle;

    @NotNull
    private volatile /* synthetic */ Object _state;

    @Metadata
    private static final class AwaitContinuation<T> extends CancellableContinuationImpl<T> {

        /* renamed from: o, reason: collision with root package name */
        private final JobSupport f18905o;

        @Override // kotlinx.coroutines.CancellableContinuationImpl
        protected String I() {
            return "AwaitContinuation";
        }

        @Override // kotlinx.coroutines.CancellableContinuationImpl
        public Throwable v(Job job) {
            Throwable d2;
            Object u0 = this.f18905o.u0();
            return (!(u0 instanceof Finishing) || (d2 = ((Finishing) u0).d()) == null) ? u0 instanceof CompletedExceptionally ? ((CompletedExceptionally) u0).f18845a : job.s() : d2;
        }
    }

    @Metadata
    private static final class ChildCompletion extends JobNode {

        /* renamed from: k, reason: collision with root package name */
        private final JobSupport f18906k;

        /* renamed from: l, reason: collision with root package name */
        private final Finishing f18907l;

        /* renamed from: m, reason: collision with root package name */
        private final ChildHandleNode f18908m;

        /* renamed from: n, reason: collision with root package name */
        private final Object f18909n;

        public ChildCompletion(JobSupport jobSupport, Finishing finishing, ChildHandleNode childHandleNode, Object obj) {
            this.f18906k = jobSupport;
            this.f18907l = finishing;
            this.f18908m = childHandleNode;
            this.f18909n = obj;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object c(Object obj) {
            d0((Throwable) obj);
            return Unit.f18288a;
        }

        @Override // kotlinx.coroutines.CompletionHandlerBase
        public void d0(Throwable th) {
            this.f18906k.j0(this.f18907l, this.f18908m, this.f18909n);
        }
    }

    @Metadata
    private static final class Finishing implements Incomplete {

        @NotNull
        private volatile /* synthetic */ Object _exceptionsHolder = null;

        @NotNull
        private volatile /* synthetic */ int _isCompleting;

        @NotNull
        private volatile /* synthetic */ Object _rootCause;

        /* renamed from: c, reason: collision with root package name */
        private final NodeList f18910c;

        public Finishing(NodeList nodeList, boolean z, Throwable th) {
            this.f18910c = nodeList;
            this._isCompleting = z ? 1 : 0;
            this._rootCause = th;
        }

        private final ArrayList b() {
            return new ArrayList(4);
        }

        private final Object c() {
            return this._exceptionsHolder;
        }

        private final void k(Object obj) {
            this._exceptionsHolder = obj;
        }

        public final void a(Throwable th) {
            Throwable d2 = d();
            if (d2 == null) {
                l(th);
                return;
            }
            if (th == d2) {
                return;
            }
            Object c2 = c();
            if (c2 == null) {
                k(th);
                return;
            }
            if (c2 instanceof Throwable) {
                if (th == c2) {
                    return;
                }
                ArrayList b2 = b();
                b2.add(c2);
                b2.add(th);
                k(b2);
                return;
            }
            if (c2 instanceof ArrayList) {
                ((ArrayList) c2).add(th);
                return;
            }
            throw new IllegalStateException(("State is " + c2).toString());
        }

        public final Throwable d() {
            return (Throwable) this._rootCause;
        }

        public final boolean e() {
            return d() != null;
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [boolean, int] */
        public final boolean f() {
            return this._isCompleting;
        }

        public final boolean g() {
            Symbol symbol;
            Object c2 = c();
            symbol = JobSupportKt.f18915e;
            return c2 == symbol;
        }

        public final List h(Throwable th) {
            ArrayList arrayList;
            Symbol symbol;
            Object c2 = c();
            if (c2 == null) {
                arrayList = b();
            } else if (c2 instanceof Throwable) {
                ArrayList b2 = b();
                b2.add(c2);
                arrayList = b2;
            } else {
                if (!(c2 instanceof ArrayList)) {
                    throw new IllegalStateException(("State is " + c2).toString());
                }
                arrayList = (ArrayList) c2;
            }
            Throwable d2 = d();
            if (d2 != null) {
                arrayList.add(0, d2);
            }
            if (th != null && !Intrinsics.a(th, d2)) {
                arrayList.add(th);
            }
            symbol = JobSupportKt.f18915e;
            k(symbol);
            return arrayList;
        }

        @Override // kotlinx.coroutines.Incomplete
        public NodeList i() {
            return this.f18910c;
        }

        @Override // kotlinx.coroutines.Incomplete
        public boolean isActive() {
            return d() == null;
        }

        public final void j(boolean z) {
            this._isCompleting = z ? 1 : 0;
        }

        public final void l(Throwable th) {
            this._rootCause = th;
        }

        public String toString() {
            return "Finishing[cancelling=" + e() + ", completing=" + f() + ", rootCause=" + d() + ", exceptions=" + c() + ", list=" + i() + ']';
        }
    }

    public JobSupport(boolean z) {
        this._state = z ? JobSupportKt.f18917g : JobSupportKt.f18916f;
        this._parentHandle = null;
    }

    private final Object A0(Continuation continuation) {
        Continuation c2;
        Object d2;
        Object d3;
        c2 = IntrinsicsKt__IntrinsicsJvmKt.c(continuation);
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(c2, 1);
        cancellableContinuationImpl.z();
        CancellableContinuationKt.a(cancellableContinuationImpl, A(new ResumeOnCompletion(cancellableContinuationImpl)));
        Object w = cancellableContinuationImpl.w();
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        if (w == d2) {
            DebugProbesKt.c(continuation);
        }
        d3 = IntrinsicsKt__IntrinsicsKt.d();
        return w == d3 ? w : Unit.f18288a;
    }

    private final Object B0(Object obj) {
        Symbol symbol;
        Symbol symbol2;
        Symbol symbol3;
        Symbol symbol4;
        Symbol symbol5;
        Symbol symbol6;
        Throwable th = null;
        while (true) {
            Object u0 = u0();
            if (u0 instanceof Finishing) {
                synchronized (u0) {
                    if (((Finishing) u0).g()) {
                        symbol2 = JobSupportKt.f18914d;
                        return symbol2;
                    }
                    boolean e2 = ((Finishing) u0).e();
                    if (obj != null || !e2) {
                        if (th == null) {
                            th = k0(obj);
                        }
                        ((Finishing) u0).a(th);
                    }
                    Throwable d2 = e2 ^ true ? ((Finishing) u0).d() : null;
                    if (d2 != null) {
                        H0(((Finishing) u0).i(), d2);
                    }
                    symbol = JobSupportKt.f18911a;
                    return symbol;
                }
            }
            if (!(u0 instanceof Incomplete)) {
                symbol3 = JobSupportKt.f18914d;
                return symbol3;
            }
            if (th == null) {
                th = k0(obj);
            }
            Incomplete incomplete = (Incomplete) u0;
            if (!incomplete.isActive()) {
                Object Z0 = Z0(u0, new CompletedExceptionally(th, false, 2, null));
                symbol5 = JobSupportKt.f18911a;
                if (Z0 == symbol5) {
                    throw new IllegalStateException(("Cannot happen in " + u0).toString());
                }
                symbol6 = JobSupportKt.f18913c;
                if (Z0 != symbol6) {
                    return Z0;
                }
            } else if (Y0(incomplete, th)) {
                symbol4 = JobSupportKt.f18911a;
                return symbol4;
            }
        }
    }

    private final JobNode E0(Function1 function1, boolean z) {
        JobNode jobNode;
        if (z) {
            jobNode = function1 instanceof JobCancellingNode ? (JobCancellingNode) function1 : null;
            if (jobNode == null) {
                jobNode = new InvokeOnCancelling(function1);
            }
        } else {
            jobNode = function1 instanceof JobNode ? (JobNode) function1 : null;
            if (jobNode == null) {
                jobNode = new InvokeOnCompletion(function1);
            }
        }
        jobNode.f0(this);
        return jobNode;
    }

    private final ChildHandleNode G0(LockFreeLinkedListNode lockFreeLinkedListNode) {
        while (lockFreeLinkedListNode.X()) {
            lockFreeLinkedListNode = lockFreeLinkedListNode.U();
        }
        while (true) {
            lockFreeLinkedListNode = lockFreeLinkedListNode.T();
            if (!lockFreeLinkedListNode.X()) {
                if (lockFreeLinkedListNode instanceof ChildHandleNode) {
                    return (ChildHandleNode) lockFreeLinkedListNode;
                }
                if (lockFreeLinkedListNode instanceof NodeList) {
                    return null;
                }
            }
        }
    }

    private final void H0(NodeList nodeList, Throwable th) {
        J0(th);
        CompletionHandlerException completionHandlerException = null;
        for (LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) nodeList.S(); !Intrinsics.a(lockFreeLinkedListNode, nodeList); lockFreeLinkedListNode = lockFreeLinkedListNode.T()) {
            if (lockFreeLinkedListNode instanceof JobCancellingNode) {
                JobNode jobNode = (JobNode) lockFreeLinkedListNode;
                try {
                    jobNode.d0(th);
                } catch (Throwable th2) {
                    if (completionHandlerException != null) {
                        ExceptionsKt__ExceptionsKt.a(completionHandlerException, th2);
                    } else {
                        completionHandlerException = new CompletionHandlerException("Exception in completion handler " + jobNode + " for " + this, th2);
                        Unit unit = Unit.f18288a;
                    }
                }
            }
        }
        if (completionHandlerException != null) {
            w0(completionHandlerException);
        }
        c0(th);
    }

    private final void I0(NodeList nodeList, Throwable th) {
        CompletionHandlerException completionHandlerException = null;
        for (LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) nodeList.S(); !Intrinsics.a(lockFreeLinkedListNode, nodeList); lockFreeLinkedListNode = lockFreeLinkedListNode.T()) {
            if (lockFreeLinkedListNode instanceof JobNode) {
                JobNode jobNode = (JobNode) lockFreeLinkedListNode;
                try {
                    jobNode.d0(th);
                } catch (Throwable th2) {
                    if (completionHandlerException != null) {
                        ExceptionsKt__ExceptionsKt.a(completionHandlerException, th2);
                    } else {
                        completionHandlerException = new CompletionHandlerException("Exception in completion handler " + jobNode + " for " + this, th2);
                        Unit unit = Unit.f18288a;
                    }
                }
            }
        }
        if (completionHandlerException != null) {
            w0(completionHandlerException);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v2, types: [kotlinx.coroutines.InactiveNodeList] */
    private final void M0(Empty empty) {
        NodeList nodeList = new NodeList();
        if (!empty.isActive()) {
            nodeList = new InactiveNodeList(nodeList);
        }
        f18902c.compareAndSet(this, empty, nodeList);
    }

    private final void N0(JobNode jobNode) {
        jobNode.N(new NodeList());
        f18902c.compareAndSet(this, jobNode, jobNode.T());
    }

    private final int S0(Object obj) {
        Empty empty;
        if (!(obj instanceof Empty)) {
            if (!(obj instanceof InactiveNodeList)) {
                return 0;
            }
            if (!f18902c.compareAndSet(this, obj, ((InactiveNodeList) obj).i())) {
                return -1;
            }
            L0();
            return 1;
        }
        if (((Empty) obj).isActive()) {
            return 0;
        }
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f18902c;
        empty = JobSupportKt.f18917g;
        if (!atomicReferenceFieldUpdater.compareAndSet(this, obj, empty)) {
            return -1;
        }
        L0();
        return 1;
    }

    private final String T0(Object obj) {
        if (!(obj instanceof Finishing)) {
            return obj instanceof Incomplete ? ((Incomplete) obj).isActive() ? "Active" : "New" : obj instanceof CompletedExceptionally ? "Cancelled" : "Completed";
        }
        Finishing finishing = (Finishing) obj;
        return finishing.e() ? "Cancelling" : finishing.f() ? "Completing" : "Active";
    }

    private final boolean U(final Object obj, NodeList nodeList, final JobNode jobNode) {
        int c0;
        LockFreeLinkedListNode.CondAddOp condAddOp = new LockFreeLinkedListNode.CondAddOp(jobNode) { // from class: kotlinx.coroutines.JobSupport$addLastAtomic$$inlined$addLastIf$1
            @Override // kotlinx.coroutines.internal.AtomicOp
            /* renamed from: k, reason: merged with bridge method [inline-methods] */
            public Object i(LockFreeLinkedListNode lockFreeLinkedListNode) {
                if (this.u0() == obj) {
                    return null;
                }
                return LockFreeLinkedListKt.a();
            }
        };
        do {
            c0 = nodeList.U().c0(jobNode, nodeList, condAddOp);
            if (c0 == 1) {
                return true;
            }
        } while (c0 != 2);
        return false;
    }

    private final void V(Throwable th, List list) {
        if (list.size() <= 1) {
            return;
        }
        Set newSetFromMap = Collections.newSetFromMap(new IdentityHashMap(list.size()));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Throwable th2 = (Throwable) it.next();
            if (th2 != th && th2 != th && !(th2 instanceof CancellationException) && newSetFromMap.add(th2)) {
                ExceptionsKt__ExceptionsKt.a(th, th2);
            }
        }
    }

    public static /* synthetic */ CancellationException V0(JobSupport jobSupport, Throwable th, String str, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: toCancellationException");
        }
        if ((i2 & 1) != 0) {
            str = null;
        }
        return jobSupport.U0(th, str);
    }

    private final boolean X0(Incomplete incomplete, Object obj) {
        if (!f18902c.compareAndSet(this, incomplete, JobSupportKt.g(obj))) {
            return false;
        }
        J0(null);
        K0(obj);
        i0(incomplete, obj);
        return true;
    }

    private final boolean Y0(Incomplete incomplete, Throwable th) {
        NodeList s0 = s0(incomplete);
        if (s0 == null) {
            return false;
        }
        if (!f18902c.compareAndSet(this, incomplete, new Finishing(s0, false, th))) {
            return false;
        }
        H0(s0, th);
        return true;
    }

    private final Object Z0(Object obj, Object obj2) {
        Symbol symbol;
        Symbol symbol2;
        if (!(obj instanceof Incomplete)) {
            symbol2 = JobSupportKt.f18911a;
            return symbol2;
        }
        if ((!(obj instanceof Empty) && !(obj instanceof JobNode)) || (obj instanceof ChildHandleNode) || (obj2 instanceof CompletedExceptionally)) {
            return a1((Incomplete) obj, obj2);
        }
        if (X0((Incomplete) obj, obj2)) {
            return obj2;
        }
        symbol = JobSupportKt.f18913c;
        return symbol;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [T, java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r2v2 */
    private final Object a1(Incomplete incomplete, Object obj) {
        Symbol symbol;
        Symbol symbol2;
        Symbol symbol3;
        NodeList s0 = s0(incomplete);
        if (s0 == null) {
            symbol3 = JobSupportKt.f18913c;
            return symbol3;
        }
        Finishing finishing = incomplete instanceof Finishing ? (Finishing) incomplete : null;
        if (finishing == null) {
            finishing = new Finishing(s0, false, null);
        }
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        synchronized (finishing) {
            if (finishing.f()) {
                symbol2 = JobSupportKt.f18911a;
                return symbol2;
            }
            finishing.j(true);
            if (finishing != incomplete && !f18902c.compareAndSet(this, incomplete, finishing)) {
                symbol = JobSupportKt.f18913c;
                return symbol;
            }
            boolean e2 = finishing.e();
            CompletedExceptionally completedExceptionally = obj instanceof CompletedExceptionally ? (CompletedExceptionally) obj : null;
            if (completedExceptionally != null) {
                finishing.a(completedExceptionally.f18845a);
            }
            ?? d2 = true ^ e2 ? finishing.d() : 0;
            objectRef.element = d2;
            Unit unit = Unit.f18288a;
            if (d2 != 0) {
                H0(s0, d2);
            }
            ChildHandleNode m0 = m0(incomplete);
            return (m0 == null || !b1(finishing, m0, obj)) ? l0(finishing, obj) : JobSupportKt.f18912b;
        }
    }

    private final Object b0(Object obj) {
        Symbol symbol;
        Object Z0;
        Symbol symbol2;
        do {
            Object u0 = u0();
            if (!(u0 instanceof Incomplete) || ((u0 instanceof Finishing) && ((Finishing) u0).f())) {
                symbol = JobSupportKt.f18911a;
                return symbol;
            }
            Z0 = Z0(u0, new CompletedExceptionally(k0(obj), false, 2, null));
            symbol2 = JobSupportKt.f18913c;
        } while (Z0 == symbol2);
        return Z0;
    }

    private final boolean b1(Finishing finishing, ChildHandleNode childHandleNode, Object obj) {
        while (Job.DefaultImpls.d(childHandleNode.f18838k, false, false, new ChildCompletion(this, finishing, childHandleNode, obj), 1, null) == NonDisposableHandle.f18921c) {
            childHandleNode = G0(childHandleNode);
            if (childHandleNode == null) {
                return false;
            }
        }
        return true;
    }

    private final boolean c0(Throwable th) {
        if (y0()) {
            return true;
        }
        boolean z = th instanceof CancellationException;
        ChildHandle t0 = t0();
        return (t0 == null || t0 == NonDisposableHandle.f18921c) ? z : t0.h(th) || z;
    }

    private final void i0(Incomplete incomplete, Object obj) {
        ChildHandle t0 = t0();
        if (t0 != null) {
            t0.dispose();
            R0(NonDisposableHandle.f18921c);
        }
        CompletedExceptionally completedExceptionally = obj instanceof CompletedExceptionally ? (CompletedExceptionally) obj : null;
        Throwable th = completedExceptionally != null ? completedExceptionally.f18845a : null;
        if (!(incomplete instanceof JobNode)) {
            NodeList i2 = incomplete.i();
            if (i2 != null) {
                I0(i2, th);
                return;
            }
            return;
        }
        try {
            ((JobNode) incomplete).d0(th);
        } catch (Throwable th2) {
            w0(new CompletionHandlerException("Exception in completion handler " + incomplete + " for " + this, th2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void j0(Finishing finishing, ChildHandleNode childHandleNode, Object obj) {
        ChildHandleNode G0 = G0(childHandleNode);
        if (G0 == null || !b1(finishing, G0, obj)) {
            W(l0(finishing, obj));
        }
    }

    private final Throwable k0(Object obj) {
        if (obj == null || (obj instanceof Throwable)) {
            Throwable th = (Throwable) obj;
            return th == null ? new JobCancellationException(d0(), null, this) : th;
        }
        if (obj != null) {
            return ((ParentJob) obj).I();
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.ParentJob");
    }

    private final Object l0(Finishing finishing, Object obj) {
        boolean e2;
        Throwable p0;
        CompletedExceptionally completedExceptionally = obj instanceof CompletedExceptionally ? (CompletedExceptionally) obj : null;
        Throwable th = completedExceptionally != null ? completedExceptionally.f18845a : null;
        synchronized (finishing) {
            e2 = finishing.e();
            List h2 = finishing.h(th);
            p0 = p0(finishing, h2);
            if (p0 != null) {
                V(p0, h2);
            }
        }
        if (p0 != null && p0 != th) {
            obj = new CompletedExceptionally(p0, false, 2, null);
        }
        if (p0 != null && (c0(p0) || v0(p0))) {
            if (obj == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.CompletedExceptionally");
            }
            ((CompletedExceptionally) obj).b();
        }
        if (!e2) {
            J0(p0);
        }
        K0(obj);
        f18902c.compareAndSet(this, finishing, JobSupportKt.g(obj));
        i0(finishing, obj);
        return obj;
    }

    private final ChildHandleNode m0(Incomplete incomplete) {
        ChildHandleNode childHandleNode = incomplete instanceof ChildHandleNode ? (ChildHandleNode) incomplete : null;
        if (childHandleNode != null) {
            return childHandleNode;
        }
        NodeList i2 = incomplete.i();
        if (i2 != null) {
            return G0(i2);
        }
        return null;
    }

    private final Throwable o0(Object obj) {
        CompletedExceptionally completedExceptionally = obj instanceof CompletedExceptionally ? (CompletedExceptionally) obj : null;
        if (completedExceptionally != null) {
            return completedExceptionally.f18845a;
        }
        return null;
    }

    private final Throwable p0(Finishing finishing, List list) {
        Object obj;
        Object obj2 = null;
        if (list.isEmpty()) {
            if (finishing.e()) {
                return new JobCancellationException(d0(), null, this);
            }
            return null;
        }
        Iterator it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (!(((Throwable) obj) instanceof CancellationException)) {
                break;
            }
        }
        Throwable th = (Throwable) obj;
        if (th != null) {
            return th;
        }
        Throwable th2 = (Throwable) list.get(0);
        if (th2 instanceof TimeoutCancellationException) {
            Iterator it2 = list.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                Object next = it2.next();
                Throwable th3 = (Throwable) next;
                if (th3 != th2 && (th3 instanceof TimeoutCancellationException)) {
                    obj2 = next;
                    break;
                }
            }
            Throwable th4 = (Throwable) obj2;
            if (th4 != null) {
                return th4;
            }
        }
        return th2;
    }

    private final NodeList s0(Incomplete incomplete) {
        NodeList i2 = incomplete.i();
        if (i2 != null) {
            return i2;
        }
        if (incomplete instanceof Empty) {
            return new NodeList();
        }
        if (incomplete instanceof JobNode) {
            N0((JobNode) incomplete);
            return null;
        }
        throw new IllegalStateException(("State should have list: " + incomplete).toString());
    }

    private final boolean z0() {
        Object u0;
        do {
            u0 = u0();
            if (!(u0 instanceof Incomplete)) {
                return false;
            }
        } while (S0(u0) < 0);
        return true;
    }

    @Override // kotlinx.coroutines.Job
    public final DisposableHandle A(Function1 function1) {
        return p(false, true, function1);
    }

    public final boolean C0(Object obj) {
        Object Z0;
        Symbol symbol;
        Symbol symbol2;
        do {
            Z0 = Z0(u0(), obj);
            symbol = JobSupportKt.f18911a;
            if (Z0 == symbol) {
                return false;
            }
            if (Z0 == JobSupportKt.f18912b) {
                return true;
            }
            symbol2 = JobSupportKt.f18913c;
        } while (Z0 == symbol2);
        W(Z0);
        return true;
    }

    public final Object D0(Object obj) {
        Object Z0;
        Symbol symbol;
        Symbol symbol2;
        do {
            Z0 = Z0(u0(), obj);
            symbol = JobSupportKt.f18911a;
            if (Z0 == symbol) {
                throw new IllegalStateException("Job " + this + " is already complete or completing, but is being completed with " + obj, o0(obj));
            }
            symbol2 = JobSupportKt.f18913c;
        } while (Z0 == symbol2);
        return Z0;
    }

    public String F0() {
        return DebugStringsKt.a(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v11, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r1v7, types: [java.lang.Throwable] */
    @Override // kotlinx.coroutines.ParentJob
    public CancellationException I() {
        CancellationException cancellationException;
        Object u0 = u0();
        if (u0 instanceof Finishing) {
            cancellationException = ((Finishing) u0).d();
        } else if (u0 instanceof CompletedExceptionally) {
            cancellationException = ((CompletedExceptionally) u0).f18845a;
        } else {
            if (u0 instanceof Incomplete) {
                throw new IllegalStateException(("Cannot be cancelling child in this state: " + u0).toString());
            }
            cancellationException = null;
        }
        CancellationException cancellationException2 = cancellationException instanceof CancellationException ? cancellationException : null;
        if (cancellationException2 != null) {
            return cancellationException2;
        }
        return new JobCancellationException("Parent job is " + T0(u0), cancellationException, this);
    }

    protected void J0(Throwable th) {
    }

    protected void K0(Object obj) {
    }

    @Override // kotlinx.coroutines.Job
    public final boolean L() {
        return !(u0() instanceof Incomplete);
    }

    protected void L0() {
    }

    public final void O0(SelectInstance selectInstance, Function2 function2) {
        Object u0;
        do {
            u0 = u0();
            if (selectInstance.k()) {
                return;
            }
            if (!(u0 instanceof Incomplete)) {
                if (selectInstance.w()) {
                    if (u0 instanceof CompletedExceptionally) {
                        selectInstance.A(((CompletedExceptionally) u0).f18845a);
                        return;
                    } else {
                        UndispatchedKt.c(function2, JobSupportKt.h(u0), selectInstance.x());
                        return;
                    }
                }
                return;
            }
        } while (S0(u0) != 0);
        selectInstance.s(A(new SelectAwaitOnCompletion(selectInstance, function2)));
    }

    public final void P0(JobNode jobNode) {
        Object u0;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        Empty empty;
        do {
            u0 = u0();
            if (!(u0 instanceof JobNode)) {
                if (!(u0 instanceof Incomplete) || ((Incomplete) u0).i() == null) {
                    return;
                }
                jobNode.Y();
                return;
            }
            if (u0 != jobNode) {
                return;
            }
            atomicReferenceFieldUpdater = f18902c;
            empty = JobSupportKt.f18917g;
        } while (!atomicReferenceFieldUpdater.compareAndSet(this, u0, empty));
    }

    public final void Q0(SelectInstance selectInstance, Function2 function2) {
        Object u0 = u0();
        if (u0 instanceof CompletedExceptionally) {
            selectInstance.A(((CompletedExceptionally) u0).f18845a);
        } else {
            CancellableKt.e(function2, JobSupportKt.h(u0), selectInstance.x(), null, 4, null);
        }
    }

    @Override // kotlin.coroutines.CoroutineContext
    public CoroutineContext R(CoroutineContext coroutineContext) {
        return Job.DefaultImpls.f(this, coroutineContext);
    }

    public final void R0(ChildHandle childHandle) {
        this._parentHandle = childHandle;
    }

    @Override // kotlinx.coroutines.Job
    public final Object T(Continuation continuation) {
        Object d2;
        if (!z0()) {
            JobKt.g(continuation.getContext());
            return Unit.f18288a;
        }
        Object A0 = A0(continuation);
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        return A0 == d2 ? A0 : Unit.f18288a;
    }

    protected final CancellationException U0(Throwable th, String str) {
        CancellationException cancellationException = th instanceof CancellationException ? (CancellationException) th : null;
        if (cancellationException == null) {
            if (str == null) {
                str = d0();
            }
            cancellationException = new JobCancellationException(str, th, this);
        }
        return cancellationException;
    }

    protected void W(Object obj) {
    }

    public final String W0() {
        return F0() + '{' + T0(u0()) + '}';
    }

    public final boolean X(Throwable th) {
        return Z(th);
    }

    @Override // kotlin.coroutines.CoroutineContext
    public CoroutineContext Y(CoroutineContext.Key key) {
        return Job.DefaultImpls.e(this, key);
    }

    public final boolean Z(Object obj) {
        Object obj2;
        Symbol symbol;
        Symbol symbol2;
        Symbol symbol3;
        obj2 = JobSupportKt.f18911a;
        if (r0() && (obj2 = b0(obj)) == JobSupportKt.f18912b) {
            return true;
        }
        symbol = JobSupportKt.f18911a;
        if (obj2 == symbol) {
            obj2 = B0(obj);
        }
        symbol2 = JobSupportKt.f18911a;
        if (obj2 == symbol2 || obj2 == JobSupportKt.f18912b) {
            return true;
        }
        symbol3 = JobSupportKt.f18914d;
        if (obj2 == symbol3) {
            return false;
        }
        W(obj2);
        return true;
    }

    @Override // kotlinx.coroutines.Job
    public void a(CancellationException cancellationException) {
        if (cancellationException == null) {
            cancellationException = new JobCancellationException(d0(), null, this);
        }
        a0(cancellationException);
    }

    public void a0(Throwable th) {
        Z(th);
    }

    @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    public CoroutineContext.Element c(CoroutineContext.Key key) {
        return Job.DefaultImpls.c(this, key);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String d0() {
        return "Job was cancelled";
    }

    @Override // kotlin.coroutines.CoroutineContext
    public Object e0(Object obj, Function2 function2) {
        return Job.DefaultImpls.b(this, obj, function2);
    }

    public boolean g0(Throwable th) {
        if (th instanceof CancellationException) {
            return true;
        }
        return Z(th) && q0();
    }

    @Override // kotlin.coroutines.CoroutineContext.Element
    public final CoroutineContext.Key getKey() {
        return Job.f18898f;
    }

    @Override // kotlinx.coroutines.Job
    public final ChildHandle h0(ChildJob childJob) {
        return (ChildHandle) Job.DefaultImpls.d(this, true, false, new ChildHandleNode(childJob), 2, null);
    }

    @Override // kotlinx.coroutines.Job
    public boolean isActive() {
        Object u0 = u0();
        return (u0 instanceof Incomplete) && ((Incomplete) u0).isActive();
    }

    @Override // kotlinx.coroutines.Job
    public final boolean isCancelled() {
        Object u0 = u0();
        return (u0 instanceof CompletedExceptionally) || ((u0 instanceof Finishing) && ((Finishing) u0).e());
    }

    @Override // kotlinx.coroutines.selects.SelectClause0
    public final void k(SelectInstance selectInstance, Function1 function1) {
        Object u0;
        do {
            u0 = u0();
            if (selectInstance.k()) {
                return;
            }
            if (!(u0 instanceof Incomplete)) {
                if (selectInstance.w()) {
                    UndispatchedKt.b(function1, selectInstance.x());
                    return;
                }
                return;
            }
        } while (S0(u0) != 0);
        selectInstance.s(A(new SelectJoinOnCompletion(selectInstance, function1)));
    }

    public final Object n0() {
        Object u0 = u0();
        if (!(!(u0 instanceof Incomplete))) {
            throw new IllegalStateException("This job has not completed yet".toString());
        }
        if (u0 instanceof CompletedExceptionally) {
            throw ((CompletedExceptionally) u0).f18845a;
        }
        return JobSupportKt.h(u0);
    }

    @Override // kotlinx.coroutines.Job
    public final DisposableHandle p(boolean z, boolean z2, Function1 function1) {
        JobNode E0 = E0(function1, z);
        while (true) {
            Object u0 = u0();
            if (u0 instanceof Empty) {
                Empty empty = (Empty) u0;
                if (!empty.isActive()) {
                    M0(empty);
                } else if (f18902c.compareAndSet(this, u0, E0)) {
                    return E0;
                }
            } else {
                if (!(u0 instanceof Incomplete)) {
                    if (z2) {
                        CompletedExceptionally completedExceptionally = u0 instanceof CompletedExceptionally ? (CompletedExceptionally) u0 : null;
                        function1.c(completedExceptionally != null ? completedExceptionally.f18845a : null);
                    }
                    return NonDisposableHandle.f18921c;
                }
                NodeList i2 = ((Incomplete) u0).i();
                if (i2 != null) {
                    DisposableHandle disposableHandle = NonDisposableHandle.f18921c;
                    if (z && (u0 instanceof Finishing)) {
                        synchronized (u0) {
                            try {
                                r3 = ((Finishing) u0).d();
                                if (r3 != null) {
                                    if ((function1 instanceof ChildHandleNode) && !((Finishing) u0).f()) {
                                    }
                                    Unit unit = Unit.f18288a;
                                }
                                if (U(u0, i2, E0)) {
                                    if (r3 == null) {
                                        return E0;
                                    }
                                    disposableHandle = E0;
                                    Unit unit2 = Unit.f18288a;
                                }
                            } catch (Throwable th) {
                                throw th;
                            }
                        }
                    }
                    if (r3 != null) {
                        if (z2) {
                            function1.c(r3);
                        }
                        return disposableHandle;
                    }
                    if (U(u0, i2, E0)) {
                        return E0;
                    }
                } else {
                    if (u0 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.JobNode");
                    }
                    N0((JobNode) u0);
                }
            }
        }
    }

    public boolean q0() {
        return true;
    }

    public boolean r0() {
        return false;
    }

    @Override // kotlinx.coroutines.Job
    public final CancellationException s() {
        Object u0 = u0();
        if (!(u0 instanceof Finishing)) {
            if (u0 instanceof Incomplete) {
                throw new IllegalStateException(("Job is still new or active: " + this).toString());
            }
            if (u0 instanceof CompletedExceptionally) {
                return V0(this, ((CompletedExceptionally) u0).f18845a, null, 1, null);
            }
            return new JobCancellationException(DebugStringsKt.a(this) + " has completed normally", null, this);
        }
        Throwable d2 = ((Finishing) u0).d();
        if (d2 != null) {
            CancellationException U0 = U0(d2, DebugStringsKt.a(this) + " is cancelling");
            if (U0 != null) {
                return U0;
            }
        }
        throw new IllegalStateException(("Job is still new or active: " + this).toString());
    }

    @Override // kotlinx.coroutines.Job
    public final boolean start() {
        int S0;
        do {
            S0 = S0(u0());
            if (S0 == 0) {
                return false;
            }
        } while (S0 != 1);
        return true;
    }

    @Override // kotlinx.coroutines.ChildJob
    public final void t(ParentJob parentJob) {
        Z(parentJob);
    }

    public final ChildHandle t0() {
        return (ChildHandle) this._parentHandle;
    }

    public String toString() {
        return W0() + '@' + DebugStringsKt.b(this);
    }

    public final Object u0() {
        while (true) {
            Object obj = this._state;
            if (!(obj instanceof OpDescriptor)) {
                return obj;
            }
            ((OpDescriptor) obj).c(this);
        }
    }

    protected boolean v0(Throwable th) {
        return false;
    }

    public void w0(Throwable th) {
        throw th;
    }

    protected final void x0(Job job) {
        if (job == null) {
            R0(NonDisposableHandle.f18921c);
            return;
        }
        job.start();
        ChildHandle h0 = job.h0(this);
        R0(h0);
        if (L()) {
            h0.dispose();
            R0(NonDisposableHandle.f18921c);
        }
    }

    protected boolean y0() {
        return false;
    }
}
