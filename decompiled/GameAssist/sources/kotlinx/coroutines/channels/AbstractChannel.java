package kotlinx.coroutines.channels;

import java.util.ArrayList;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BeforeResumeCancelHandler;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CancellableContinuationImplKt;
import kotlinx.coroutines.CancellableContinuationKt;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.internal.AtomicKt;
import kotlinx.coroutines.internal.InlineList;
import kotlinx.coroutines.internal.LockFreeLinkedListHead;
import kotlinx.coroutines.internal.LockFreeLinkedListKt;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.LockFreeLinkedList_commonKt;
import kotlinx.coroutines.internal.OnUndeliveredElementKt;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.intrinsics.CancellableKt;
import kotlinx.coroutines.intrinsics.UndispatchedKt;
import kotlinx.coroutines.selects.SelectClause1;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.SelectKt;

@Metadata
/* loaded from: classes2.dex */
public abstract class AbstractChannel<E> extends AbstractSendChannel<E> implements Channel<E> {

    @Metadata
    private static final class Itr<E> implements ChannelIterator<E> {

        /* renamed from: a, reason: collision with root package name */
        public final AbstractChannel f18956a;

        /* renamed from: b, reason: collision with root package name */
        private Object f18957b = AbstractChannelKt.f18974d;

        public Itr(AbstractChannel abstractChannel) {
            this.f18956a = abstractChannel;
        }

        private final boolean b(Object obj) {
            if (!(obj instanceof Closed)) {
                return true;
            }
            Closed closed = (Closed) obj;
            if (closed.f19009j == null) {
                return false;
            }
            throw StackTraceRecoveryKt.a(closed.k0());
        }

        private final Object c(Continuation continuation) {
            Continuation c2;
            Object d2;
            c2 = IntrinsicsKt__IntrinsicsJvmKt.c(continuation);
            CancellableContinuationImpl b2 = CancellableContinuationKt.b(c2);
            ReceiveHasNext receiveHasNext = new ReceiveHasNext(this, b2);
            while (true) {
                if (this.f18956a.S(receiveHasNext)) {
                    this.f18956a.i0(b2, receiveHasNext);
                    break;
                }
                Object e0 = this.f18956a.e0();
                d(e0);
                if (e0 instanceof Closed) {
                    Closed closed = (Closed) e0;
                    if (closed.f19009j == null) {
                        Result.Companion companion = Result.Companion;
                        b2.g(Result.b(Boxing.a(false)));
                    } else {
                        Result.Companion companion2 = Result.Companion;
                        b2.g(Result.b(ResultKt.a(closed.k0())));
                    }
                } else if (e0 != AbstractChannelKt.f18974d) {
                    Boolean a2 = Boxing.a(true);
                    Function1 function1 = this.f18956a.f18978c;
                    b2.r(a2, function1 != null ? OnUndeliveredElementKt.a(function1, e0, b2.getContext()) : null);
                }
            }
            Object w = b2.w();
            d2 = IntrinsicsKt__IntrinsicsKt.d();
            if (w == d2) {
                DebugProbesKt.c(continuation);
            }
            return w;
        }

        @Override // kotlinx.coroutines.channels.ChannelIterator
        public Object a(Continuation continuation) {
            Object obj = this.f18957b;
            Symbol symbol = AbstractChannelKt.f18974d;
            if (obj != symbol) {
                return Boxing.a(b(obj));
            }
            Object e0 = this.f18956a.e0();
            this.f18957b = e0;
            return e0 != symbol ? Boxing.a(b(e0)) : c(continuation);
        }

        public final void d(Object obj) {
            this.f18957b = obj;
        }

        @Override // kotlinx.coroutines.channels.ChannelIterator
        public Object next() {
            Object obj = this.f18957b;
            if (obj instanceof Closed) {
                throw StackTraceRecoveryKt.a(((Closed) obj).k0());
            }
            Symbol symbol = AbstractChannelKt.f18974d;
            if (obj == symbol) {
                throw new IllegalStateException("'hasNext' should be called prior to 'next' invocation");
            }
            this.f18957b = symbol;
            return obj;
        }
    }

    @Metadata
    private static class ReceiveElement<E> extends Receive<E> {

        /* renamed from: j, reason: collision with root package name */
        public final CancellableContinuation f18958j;

        /* renamed from: k, reason: collision with root package name */
        public final int f18959k;

        public ReceiveElement(CancellableContinuation cancellableContinuation, int i2) {
            this.f18958j = cancellableContinuation;
            this.f18959k = i2;
        }

        @Override // kotlinx.coroutines.channels.ReceiveOrClosed
        public Symbol F(Object obj, LockFreeLinkedListNode.PrepareOp prepareOp) {
            if (this.f18958j.D(g0(obj), prepareOp != null ? prepareOp.f19377c : null, e0(obj)) == null) {
                return null;
            }
            if (prepareOp != null) {
                prepareOp.d();
            }
            return CancellableContinuationImplKt.f18835a;
        }

        @Override // kotlinx.coroutines.channels.Receive
        public void f0(Closed closed) {
            if (this.f18959k == 1) {
                this.f18958j.g(Result.b(ChannelResult.b(ChannelResult.f19005b.a(closed.f19009j))));
                return;
            }
            CancellableContinuation cancellableContinuation = this.f18958j;
            Result.Companion companion = Result.Companion;
            cancellableContinuation.g(Result.b(ResultKt.a(closed.k0())));
        }

        public final Object g0(Object obj) {
            return this.f18959k == 1 ? ChannelResult.b(ChannelResult.f19005b.c(obj)) : obj;
        }

        @Override // kotlinx.coroutines.channels.ReceiveOrClosed
        public void p(Object obj) {
            this.f18958j.O(CancellableContinuationImplKt.f18835a);
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        public String toString() {
            return "ReceiveElement@" + DebugStringsKt.b(this) + "[receiveMode=" + this.f18959k + ']';
        }
    }

    @Metadata
    private static final class ReceiveElementWithUndeliveredHandler<E> extends ReceiveElement<E> {

        /* renamed from: l, reason: collision with root package name */
        public final Function1 f18960l;

        public ReceiveElementWithUndeliveredHandler(CancellableContinuation cancellableContinuation, int i2, Function1 function1) {
            super(cancellableContinuation, i2);
            this.f18960l = function1;
        }

        @Override // kotlinx.coroutines.channels.Receive
        public Function1 e0(Object obj) {
            return OnUndeliveredElementKt.a(this.f18960l, obj, this.f18958j.getContext());
        }
    }

    @Metadata
    private static class ReceiveHasNext<E> extends Receive<E> {

        /* renamed from: j, reason: collision with root package name */
        public final Itr f18961j;

        /* renamed from: k, reason: collision with root package name */
        public final CancellableContinuation f18962k;

        public ReceiveHasNext(Itr itr, CancellableContinuation cancellableContinuation) {
            this.f18961j = itr;
            this.f18962k = cancellableContinuation;
        }

        @Override // kotlinx.coroutines.channels.ReceiveOrClosed
        public Symbol F(Object obj, LockFreeLinkedListNode.PrepareOp prepareOp) {
            if (this.f18962k.D(Boolean.TRUE, prepareOp != null ? prepareOp.f19377c : null, e0(obj)) == null) {
                return null;
            }
            if (prepareOp != null) {
                prepareOp.d();
            }
            return CancellableContinuationImplKt.f18835a;
        }

        @Override // kotlinx.coroutines.channels.Receive
        public Function1 e0(Object obj) {
            Function1 function1 = this.f18961j.f18956a.f18978c;
            if (function1 != null) {
                return OnUndeliveredElementKt.a(function1, obj, this.f18962k.getContext());
            }
            return null;
        }

        @Override // kotlinx.coroutines.channels.Receive
        public void f0(Closed closed) {
            Object a2 = closed.f19009j == null ? CancellableContinuation.DefaultImpls.a(this.f18962k, Boolean.FALSE, null, 2, null) : this.f18962k.n(closed.k0());
            if (a2 != null) {
                this.f18961j.d(closed);
                this.f18962k.O(a2);
            }
        }

        @Override // kotlinx.coroutines.channels.ReceiveOrClosed
        public void p(Object obj) {
            this.f18961j.d(obj);
            this.f18962k.O(CancellableContinuationImplKt.f18835a);
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        public String toString() {
            return "ReceiveHasNext@" + DebugStringsKt.b(this);
        }
    }

    @Metadata
    private static final class ReceiveSelect<R, E> extends Receive<E> implements DisposableHandle {

        /* renamed from: j, reason: collision with root package name */
        public final AbstractChannel f18963j;

        /* renamed from: k, reason: collision with root package name */
        public final SelectInstance f18964k;

        /* renamed from: l, reason: collision with root package name */
        public final Function2 f18965l;

        /* renamed from: m, reason: collision with root package name */
        public final int f18966m;

        public ReceiveSelect(AbstractChannel abstractChannel, SelectInstance selectInstance, Function2 function2, int i2) {
            this.f18963j = abstractChannel;
            this.f18964k = selectInstance;
            this.f18965l = function2;
            this.f18966m = i2;
        }

        @Override // kotlinx.coroutines.channels.ReceiveOrClosed
        public Symbol F(Object obj, LockFreeLinkedListNode.PrepareOp prepareOp) {
            return (Symbol) this.f18964k.v(prepareOp);
        }

        @Override // kotlinx.coroutines.DisposableHandle
        public void dispose() {
            if (Y()) {
                this.f18963j.c0();
            }
        }

        @Override // kotlinx.coroutines.channels.Receive
        public Function1 e0(Object obj) {
            Function1 function1 = this.f18963j.f18978c;
            if (function1 != null) {
                return OnUndeliveredElementKt.a(function1, obj, this.f18964k.x().getContext());
            }
            return null;
        }

        @Override // kotlinx.coroutines.channels.Receive
        public void f0(Closed closed) {
            if (this.f18964k.w()) {
                int i2 = this.f18966m;
                if (i2 == 0) {
                    this.f18964k.A(closed.k0());
                } else {
                    if (i2 != 1) {
                        return;
                    }
                    CancellableKt.e(this.f18965l, ChannelResult.b(ChannelResult.f19005b.a(closed.f19009j)), this.f18964k.x(), null, 4, null);
                }
            }
        }

        @Override // kotlinx.coroutines.channels.ReceiveOrClosed
        public void p(Object obj) {
            CancellableKt.d(this.f18965l, this.f18966m == 1 ? ChannelResult.b(ChannelResult.f19005b.c(obj)) : obj, this.f18964k.x(), e0(obj));
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        public String toString() {
            return "ReceiveSelect@" + DebugStringsKt.b(this) + '[' + this.f18964k + ",receiveMode=" + this.f18966m + ']';
        }
    }

    @Metadata
    private final class RemoveReceiveOnCancel extends BeforeResumeCancelHandler {

        /* renamed from: c, reason: collision with root package name */
        private final Receive f18967c;

        public RemoveReceiveOnCancel(Receive receive) {
            this.f18967c = receive;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object c(Object obj) {
            d((Throwable) obj);
            return Unit.f18288a;
        }

        @Override // kotlinx.coroutines.CancelHandlerBase
        public void d(Throwable th) {
            if (this.f18967c.Y()) {
                AbstractChannel.this.c0();
            }
        }

        public String toString() {
            return "RemoveReceiveOnCancel[" + this.f18967c + ']';
        }
    }

    @Metadata
    protected static final class TryPollDesc<E> extends LockFreeLinkedListNode.RemoveFirstDesc<Send> {
        public TryPollDesc(LockFreeLinkedListHead lockFreeLinkedListHead) {
            super(lockFreeLinkedListHead);
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.RemoveFirstDesc, kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        protected Object e(LockFreeLinkedListNode lockFreeLinkedListNode) {
            if (lockFreeLinkedListNode instanceof Closed) {
                return lockFreeLinkedListNode;
            }
            if (lockFreeLinkedListNode instanceof Send) {
                return null;
            }
            return AbstractChannelKt.f18974d;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        public Object j(LockFreeLinkedListNode.PrepareOp prepareOp) {
            Symbol g0 = ((Send) prepareOp.f19375a).g0(prepareOp);
            if (g0 == null) {
                return LockFreeLinkedList_commonKt.f19382a;
            }
            Object obj = AtomicKt.f19340b;
            if (g0 == obj) {
                return obj;
            }
            return null;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        public void k(LockFreeLinkedListNode lockFreeLinkedListNode) {
            ((Send) lockFreeLinkedListNode).h0();
        }
    }

    public AbstractChannel(Function1 function1) {
        super(function1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean S(Receive receive) {
        boolean T = T(receive);
        if (T) {
            d0();
        }
        return T;
    }

    private final boolean U(SelectInstance selectInstance, Function2 function2, int i2) {
        ReceiveSelect receiveSelect = new ReceiveSelect(this, selectInstance, function2, i2);
        boolean S = S(receiveSelect);
        if (S) {
            selectInstance.s(receiveSelect);
        }
        return S;
    }

    private final Object g0(int i2, Continuation continuation) {
        Continuation c2;
        Object d2;
        c2 = IntrinsicsKt__IntrinsicsJvmKt.c(continuation);
        CancellableContinuationImpl b2 = CancellableContinuationKt.b(c2);
        ReceiveElement receiveElement = this.f18978c == null ? new ReceiveElement(b2, i2) : new ReceiveElementWithUndeliveredHandler(b2, i2, this.f18978c);
        while (true) {
            if (S(receiveElement)) {
                i0(b2, receiveElement);
                break;
            }
            Object e0 = e0();
            if (e0 instanceof Closed) {
                receiveElement.f0((Closed) e0);
                break;
            }
            if (e0 != AbstractChannelKt.f18974d) {
                b2.r(receiveElement.g0(e0), receiveElement.e0(e0));
                break;
            }
        }
        Object w = b2.w();
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        if (w == d2) {
            DebugProbesKt.c(continuation);
        }
        return w;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void h0(SelectInstance selectInstance, int i2, Function2 function2) {
        while (!selectInstance.k()) {
            if (!Z()) {
                Object f0 = f0(selectInstance);
                if (f0 == SelectKt.d()) {
                    return;
                }
                if (f0 != AbstractChannelKt.f18974d && f0 != AtomicKt.f19340b) {
                    j0(function2, selectInstance, i2, f0);
                }
            } else if (U(selectInstance, function2, i2)) {
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void i0(CancellableContinuation cancellableContinuation, Receive receive) {
        cancellableContinuation.m(new RemoveReceiveOnCancel(receive));
    }

    private final void j0(Function2 function2, SelectInstance selectInstance, int i2, Object obj) {
        boolean z = obj instanceof Closed;
        if (!z) {
            if (i2 == 1) {
                UndispatchedKt.c(function2, ChannelResult.b(z ? ChannelResult.f19005b.a(((Closed) obj).f19009j) : ChannelResult.f19005b.c(obj)), selectInstance.x());
                return;
            } else {
                UndispatchedKt.c(function2, obj, selectInstance.x());
                return;
            }
        }
        if (i2 == 0) {
            throw StackTraceRecoveryKt.a(((Closed) obj).k0());
        }
        if (i2 == 1 && selectInstance.w()) {
            UndispatchedKt.c(function2, ChannelResult.b(ChannelResult.f19005b.a(((Closed) obj).f19009j)), selectInstance.x());
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    protected ReceiveOrClosed I() {
        ReceiveOrClosed I = super.I();
        if (I != null && !(I instanceof Closed)) {
            c0();
        }
        return I;
    }

    public final boolean Q(Throwable th) {
        boolean J = J(th);
        a0(J);
        return J;
    }

    protected final TryPollDesc R() {
        return new TryPollDesc(m());
    }

    protected boolean T(final Receive receive) {
        int c0;
        LockFreeLinkedListNode U;
        if (!W()) {
            LockFreeLinkedListNode m2 = m();
            LockFreeLinkedListNode.CondAddOp condAddOp = new LockFreeLinkedListNode.CondAddOp(receive) { // from class: kotlinx.coroutines.channels.AbstractChannel$enqueueReceiveInternal$$inlined$addLastIfPrevAndIf$1
                @Override // kotlinx.coroutines.internal.AtomicOp
                /* renamed from: k, reason: merged with bridge method [inline-methods] */
                public Object i(LockFreeLinkedListNode lockFreeLinkedListNode) {
                    if (this.X()) {
                        return null;
                    }
                    return LockFreeLinkedListKt.a();
                }
            };
            do {
                LockFreeLinkedListNode U2 = m2.U();
                if (!(!(U2 instanceof Send))) {
                    return false;
                }
                c0 = U2.c0(receive, m2, condAddOp);
                if (c0 != 1) {
                }
            } while (c0 != 2);
            return false;
        }
        LockFreeLinkedListNode m3 = m();
        do {
            U = m3.U();
            if (!(!(U instanceof Send))) {
                return false;
            }
        } while (!U.M(receive, m3));
        return true;
    }

    protected final boolean V() {
        return m().T() instanceof ReceiveOrClosed;
    }

    protected abstract boolean W();

    protected abstract boolean X();

    public boolean Y() {
        return j() != null && X();
    }

    protected final boolean Z() {
        return !(m().T() instanceof Send) && X();
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public final void a(CancellationException cancellationException) {
        if (Y()) {
            return;
        }
        if (cancellationException == null) {
            cancellationException = new CancellationException(DebugStringsKt.a(this) + " was cancelled");
        }
        Q(cancellationException);
    }

    protected void a0(boolean z) {
        Closed k2 = k();
        if (k2 == null) {
            throw new IllegalStateException("Cannot happen".toString());
        }
        Object b2 = InlineList.b(null, 1, null);
        while (true) {
            LockFreeLinkedListNode U = k2.U();
            if (U instanceof LockFreeLinkedListHead) {
                b0(b2, k2);
                return;
            } else if (U.Y()) {
                b2 = InlineList.e(b2, (Send) U);
            } else {
                U.V();
            }
        }
    }

    protected void b0(Object obj, Closed closed) {
        if (obj == null) {
            return;
        }
        if (!(obj instanceof ArrayList)) {
            ((Send) obj).f0(closed);
            return;
        }
        ArrayList arrayList = (ArrayList) obj;
        int size = arrayList.size();
        while (true) {
            size--;
            if (-1 >= size) {
                return;
            } else {
                ((Send) arrayList.get(size)).f0(closed);
            }
        }
    }

    protected void c0() {
    }

    protected void d0() {
    }

    protected Object e0() {
        while (true) {
            Send K = K();
            if (K == null) {
                return AbstractChannelKt.f18974d;
            }
            if (K.g0(null) != null) {
                K.d0();
                return K.e0();
            }
            K.h0();
        }
    }

    protected Object f0(SelectInstance selectInstance) {
        TryPollDesc R = R();
        Object C = selectInstance.C(R);
        if (C != null) {
            return C;
        }
        ((Send) R.o()).d0();
        return ((Send) R.o()).e0();
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public final ChannelIterator iterator() {
        return new Itr(this);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public final SelectClause1 w() {
        return new SelectClause1<E>() { // from class: kotlinx.coroutines.channels.AbstractChannel$onReceive$1
            @Override // kotlinx.coroutines.selects.SelectClause1
            public void b(SelectInstance selectInstance, Function2 function2) {
                AbstractChannel.this.h0(selectInstance, 0, function2);
            }
        };
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public final SelectClause1 x() {
        return new SelectClause1<ChannelResult<? extends E>>() { // from class: kotlinx.coroutines.channels.AbstractChannel$onReceiveCatching$1
            @Override // kotlinx.coroutines.selects.SelectClause1
            public void b(SelectInstance selectInstance, Function2 function2) {
                AbstractChannel.this.h0(selectInstance, 1, function2);
            }
        };
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public final Object y() {
        Object e0 = e0();
        return e0 == AbstractChannelKt.f18974d ? ChannelResult.f19005b.b() : e0 instanceof Closed ? ChannelResult.f19005b.a(((Closed) e0).f19009j) : ChannelResult.f19005b.c(e0);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    @Override // kotlinx.coroutines.channels.ReceiveChannel
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object z(kotlin.coroutines.Continuation r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof kotlinx.coroutines.channels.AbstractChannel$receiveCatching$1
            if (r0 == 0) goto L13
            r0 = r5
            kotlinx.coroutines.channels.AbstractChannel$receiveCatching$1 r0 = (kotlinx.coroutines.channels.AbstractChannel$receiveCatching$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.channels.AbstractChannel$receiveCatching$1 r0 = new kotlinx.coroutines.channels.AbstractChannel$receiveCatching$1
            r0.<init>(r4, r5)
        L18:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.d()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 != r3) goto L29
            kotlin.ResultKt.b(r5)
            goto L5b
        L29:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L31:
            kotlin.ResultKt.b(r5)
            java.lang.Object r5 = r4.e0()
            kotlinx.coroutines.internal.Symbol r2 = kotlinx.coroutines.channels.AbstractChannelKt.f18974d
            if (r5 == r2) goto L52
            boolean r4 = r5 instanceof kotlinx.coroutines.channels.Closed
            if (r4 == 0) goto L4b
            kotlinx.coroutines.channels.ChannelResult$Companion r4 = kotlinx.coroutines.channels.ChannelResult.f19005b
            kotlinx.coroutines.channels.Closed r5 = (kotlinx.coroutines.channels.Closed) r5
            java.lang.Throwable r5 = r5.f19009j
            java.lang.Object r4 = r4.a(r5)
            goto L51
        L4b:
            kotlinx.coroutines.channels.ChannelResult$Companion r4 = kotlinx.coroutines.channels.ChannelResult.f19005b
            java.lang.Object r4 = r4.c(r5)
        L51:
            return r4
        L52:
            r0.label = r3
            java.lang.Object r5 = r4.g0(r3, r0)
            if (r5 != r1) goto L5b
            return r1
        L5b:
            kotlinx.coroutines.channels.ChannelResult r5 = (kotlinx.coroutines.channels.ChannelResult) r5
            java.lang.Object r4 = r5.k()
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.AbstractChannel.z(kotlin.coroutines.Continuation):java.lang.Object");
    }
}
