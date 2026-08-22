package kotlinx.coroutines.channels;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.ExceptionsKt__ExceptionsKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
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
import kotlinx.coroutines.internal.UndeliveredElementException;
import kotlinx.coroutines.intrinsics.CancellableKt;
import kotlinx.coroutines.intrinsics.UndispatchedKt;
import kotlinx.coroutines.selects.SelectClause2;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.SelectKt;
import org.jetbrains.annotations.NotNull;

@Metadata
/* loaded from: classes2.dex */
public abstract class AbstractSendChannel<E> implements SendChannel<E> {

    /* renamed from: i, reason: collision with root package name */
    private static final /* synthetic */ AtomicReferenceFieldUpdater f18977i = AtomicReferenceFieldUpdater.newUpdater(AbstractSendChannel.class, Object.class, "onCloseHandler");

    /* renamed from: c, reason: collision with root package name */
    protected final Function1 f18978c;

    /* renamed from: h, reason: collision with root package name */
    private final LockFreeLinkedListHead f18979h = new LockFreeLinkedListHead();

    @NotNull
    private volatile /* synthetic */ Object onCloseHandler = null;

    @Metadata
    public static final class SendBuffered<E> extends Send {

        /* renamed from: j, reason: collision with root package name */
        public final Object f18981j;

        public SendBuffered(Object obj) {
            this.f18981j = obj;
        }

        @Override // kotlinx.coroutines.channels.Send
        public void d0() {
        }

        @Override // kotlinx.coroutines.channels.Send
        public Object e0() {
            return this.f18981j;
        }

        @Override // kotlinx.coroutines.channels.Send
        public void f0(Closed closed) {
        }

        @Override // kotlinx.coroutines.channels.Send
        public Symbol g0(LockFreeLinkedListNode.PrepareOp prepareOp) {
            Symbol symbol = CancellableContinuationImplKt.f18835a;
            if (prepareOp != null) {
                prepareOp.d();
            }
            return symbol;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        public String toString() {
            return "SendBuffered@" + DebugStringsKt.b(this) + '(' + this.f18981j + ')';
        }
    }

    @Metadata
    private static class SendBufferedDesc<E> extends LockFreeLinkedListNode.AddLastDesc<SendBuffered<? extends E>> {
        public SendBufferedDesc(LockFreeLinkedListHead lockFreeLinkedListHead, Object obj) {
            super(lockFreeLinkedListHead, new SendBuffered(obj));
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        protected Object e(LockFreeLinkedListNode lockFreeLinkedListNode) {
            if (lockFreeLinkedListNode instanceof Closed) {
                return lockFreeLinkedListNode;
            }
            if (lockFreeLinkedListNode instanceof ReceiveOrClosed) {
                return AbstractChannelKt.f18973c;
            }
            return null;
        }
    }

    @Metadata
    private static final class SendSelect<E, R> extends Send implements DisposableHandle {

        /* renamed from: j, reason: collision with root package name */
        private final Object f18982j;

        /* renamed from: k, reason: collision with root package name */
        public final AbstractSendChannel f18983k;

        /* renamed from: l, reason: collision with root package name */
        public final SelectInstance f18984l;

        /* renamed from: m, reason: collision with root package name */
        public final Function2 f18985m;

        public SendSelect(Object obj, AbstractSendChannel abstractSendChannel, SelectInstance selectInstance, Function2 function2) {
            this.f18982j = obj;
            this.f18983k = abstractSendChannel;
            this.f18984l = selectInstance;
            this.f18985m = function2;
        }

        @Override // kotlinx.coroutines.channels.Send
        public void d0() {
            CancellableKt.e(this.f18985m, this.f18983k, this.f18984l.x(), null, 4, null);
        }

        @Override // kotlinx.coroutines.DisposableHandle
        public void dispose() {
            if (Y()) {
                h0();
            }
        }

        @Override // kotlinx.coroutines.channels.Send
        public Object e0() {
            return this.f18982j;
        }

        @Override // kotlinx.coroutines.channels.Send
        public void f0(Closed closed) {
            if (this.f18984l.w()) {
                this.f18984l.A(closed.l0());
            }
        }

        @Override // kotlinx.coroutines.channels.Send
        public Symbol g0(LockFreeLinkedListNode.PrepareOp prepareOp) {
            return (Symbol) this.f18984l.v(prepareOp);
        }

        @Override // kotlinx.coroutines.channels.Send
        public void h0() {
            Function1 function1 = this.f18983k.f18978c;
            if (function1 != null) {
                OnUndeliveredElementKt.b(function1, e0(), this.f18984l.x().getContext());
            }
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        public String toString() {
            return "SendSelect@" + DebugStringsKt.b(this) + '(' + e0() + ")[" + this.f18983k + ", " + this.f18984l + ']';
        }
    }

    @Metadata
    protected static final class TryOfferDesc<E> extends LockFreeLinkedListNode.RemoveFirstDesc<ReceiveOrClosed<? super E>> {

        /* renamed from: e, reason: collision with root package name */
        public final Object f18986e;

        public TryOfferDesc(Object obj, LockFreeLinkedListHead lockFreeLinkedListHead) {
            super(lockFreeLinkedListHead);
            this.f18986e = obj;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.RemoveFirstDesc, kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        protected Object e(LockFreeLinkedListNode lockFreeLinkedListNode) {
            if (lockFreeLinkedListNode instanceof Closed) {
                return lockFreeLinkedListNode;
            }
            if (lockFreeLinkedListNode instanceof ReceiveOrClosed) {
                return null;
            }
            return AbstractChannelKt.f18973c;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        public Object j(LockFreeLinkedListNode.PrepareOp prepareOp) {
            Symbol F = ((ReceiveOrClosed) prepareOp.f19375a).F(this.f18986e, prepareOp);
            if (F == null) {
                return LockFreeLinkedList_commonKt.f19382a;
            }
            Object obj = AtomicKt.f19340b;
            if (F == obj) {
                return obj;
            }
            return null;
        }
    }

    public AbstractSendChannel(Function1 function1) {
        this.f18978c = function1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean B() {
        return !(this.f18979h.T() instanceof ReceiveOrClosed) && A();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void F(SelectInstance selectInstance, Object obj, Function2 function2) {
        while (!selectInstance.k()) {
            if (B()) {
                SendSelect sendSelect = new SendSelect(obj, this, selectInstance, function2);
                Object h2 = h(sendSelect);
                if (h2 == null) {
                    selectInstance.s(sendSelect);
                    return;
                }
                if (h2 instanceof Closed) {
                    throw StackTraceRecoveryKt.a(r(obj, (Closed) h2));
                }
                if (h2 != AbstractChannelKt.f18975e && !(h2 instanceof Receive)) {
                    throw new IllegalStateException(("enqueueSend returned " + h2 + ' ').toString());
                }
            }
            Object D = D(obj, selectInstance);
            if (D == SelectKt.d()) {
                return;
            }
            if (D != AbstractChannelKt.f18973c && D != AtomicKt.f19340b) {
                if (D == AbstractChannelKt.f18972b) {
                    UndispatchedKt.c(function2, this, selectInstance.x());
                    return;
                } else {
                    if (D instanceof Closed) {
                        throw StackTraceRecoveryKt.a(r(obj, (Closed) D));
                    }
                    throw new IllegalStateException(("offerSelectInternal returned " + D).toString());
                }
            }
        }
    }

    private final Object H(Object obj, Continuation continuation) {
        Continuation c2;
        Object d2;
        Object d3;
        c2 = IntrinsicsKt__IntrinsicsJvmKt.c(continuation);
        CancellableContinuationImpl b2 = CancellableContinuationKt.b(c2);
        while (true) {
            if (B()) {
                Send sendElement = this.f18978c == null ? new SendElement(obj, b2) : new SendElementWithUndeliveredHandler(obj, b2, this.f18978c);
                Object h2 = h(sendElement);
                if (h2 == null) {
                    CancellableContinuationKt.c(b2, sendElement);
                    break;
                }
                if (h2 instanceof Closed) {
                    s(b2, obj, (Closed) h2);
                    break;
                }
                if (h2 != AbstractChannelKt.f18975e && !(h2 instanceof Receive)) {
                    throw new IllegalStateException(("enqueueSend returned " + h2).toString());
                }
            }
            Object C = C(obj);
            if (C == AbstractChannelKt.f18972b) {
                Result.Companion companion = Result.Companion;
                b2.g(Result.b(Unit.f18288a));
                break;
            }
            if (C != AbstractChannelKt.f18973c) {
                if (!(C instanceof Closed)) {
                    throw new IllegalStateException(("offerInternal returned " + C).toString());
                }
                s(b2, obj, (Closed) C);
            }
        }
        Object w = b2.w();
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        if (w == d2) {
            DebugProbesKt.c(continuation);
        }
        d3 = IntrinsicsKt__IntrinsicsKt.d();
        return w == d3 ? w : Unit.f18288a;
    }

    private final int e() {
        LockFreeLinkedListHead lockFreeLinkedListHead = this.f18979h;
        int i2 = 0;
        for (LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) lockFreeLinkedListHead.S(); !Intrinsics.a(lockFreeLinkedListNode, lockFreeLinkedListHead); lockFreeLinkedListNode = lockFreeLinkedListNode.T()) {
            if (lockFreeLinkedListNode instanceof LockFreeLinkedListNode) {
                i2++;
            }
        }
        return i2;
    }

    private final String n() {
        String str;
        LockFreeLinkedListNode T = this.f18979h.T();
        if (T == this.f18979h) {
            return "EmptyQueue";
        }
        if (T instanceof Closed) {
            str = T.toString();
        } else if (T instanceof Receive) {
            str = "ReceiveQueued";
        } else if (T instanceof Send) {
            str = "SendQueued";
        } else {
            str = "UNEXPECTED:" + T;
        }
        LockFreeLinkedListNode U = this.f18979h.U();
        if (U == T) {
            return str;
        }
        String str2 = str + ",queueSize=" + e();
        if (!(U instanceof Closed)) {
            return str2;
        }
        return str2 + ",closedForSend=" + U;
    }

    private final void p(Closed closed) {
        Object b2 = InlineList.b(null, 1, null);
        while (true) {
            LockFreeLinkedListNode U = closed.U();
            Receive receive = U instanceof Receive ? (Receive) U : null;
            if (receive == null) {
                break;
            } else if (receive.Y()) {
                b2 = InlineList.e(b2, receive);
            } else {
                receive.V();
            }
        }
        if (b2 != null) {
            if (b2 instanceof ArrayList) {
                ArrayList arrayList = (ArrayList) b2;
                for (int size = arrayList.size() - 1; -1 < size; size--) {
                    ((Receive) arrayList.get(size)).f0(closed);
                }
            } else {
                ((Receive) b2).f0(closed);
            }
        }
        E(closed);
    }

    private final Throwable r(Object obj, Closed closed) {
        UndeliveredElementException d2;
        p(closed);
        Function1 function1 = this.f18978c;
        if (function1 == null || (d2 = OnUndeliveredElementKt.d(function1, obj, null, 2, null)) == null) {
            return closed.l0();
        }
        ExceptionsKt__ExceptionsKt.a(d2, closed.l0());
        throw d2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void s(Continuation continuation, Object obj, Closed closed) {
        UndeliveredElementException d2;
        p(closed);
        Throwable l0 = closed.l0();
        Function1 function1 = this.f18978c;
        if (function1 == null || (d2 = OnUndeliveredElementKt.d(function1, obj, null, 2, null)) == null) {
            Result.Companion companion = Result.Companion;
            continuation.g(Result.b(ResultKt.a(l0)));
        } else {
            ExceptionsKt__ExceptionsKt.a(d2, l0);
            Result.Companion companion2 = Result.Companion;
            continuation.g(Result.b(ResultKt.a(d2)));
        }
    }

    private final void t(Throwable th) {
        Symbol symbol;
        Object obj = this.onCloseHandler;
        if (obj == null || obj == (symbol = AbstractChannelKt.f18976f) || !f18977i.compareAndSet(this, obj, symbol)) {
            return;
        }
        ((Function1) TypeIntrinsics.a(obj, 1)).c(th);
    }

    protected abstract boolean A();

    protected Object C(Object obj) {
        ReceiveOrClosed I;
        do {
            I = I();
            if (I == null) {
                return AbstractChannelKt.f18973c;
            }
        } while (I.F(obj, null) == null);
        I.p(obj);
        return I.e();
    }

    protected Object D(Object obj, SelectInstance selectInstance) {
        TryOfferDesc g2 = g(obj);
        Object C = selectInstance.C(g2);
        if (C != null) {
            return C;
        }
        ReceiveOrClosed receiveOrClosed = (ReceiveOrClosed) g2.o();
        receiveOrClosed.p(obj);
        return receiveOrClosed.e();
    }

    protected void E(LockFreeLinkedListNode lockFreeLinkedListNode) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected final ReceiveOrClosed G(Object obj) {
        LockFreeLinkedListNode U;
        LockFreeLinkedListHead lockFreeLinkedListHead = this.f18979h;
        SendBuffered sendBuffered = new SendBuffered(obj);
        do {
            U = lockFreeLinkedListHead.U();
            if (U instanceof ReceiveOrClosed) {
                return (ReceiveOrClosed) U;
            }
        } while (!U.M(sendBuffered, lockFreeLinkedListHead));
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [kotlinx.coroutines.internal.LockFreeLinkedListNode] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3 */
    protected ReceiveOrClosed I() {
        ?? r0;
        LockFreeLinkedListNode a0;
        LockFreeLinkedListHead lockFreeLinkedListHead = this.f18979h;
        while (true) {
            r0 = (LockFreeLinkedListNode) lockFreeLinkedListHead.S();
            if (r0 != lockFreeLinkedListHead && (r0 instanceof ReceiveOrClosed)) {
                if (((((ReceiveOrClosed) r0) instanceof Closed) && !r0.X()) || (a0 = r0.a0()) == null) {
                    break;
                }
                a0.W();
            }
        }
        r0 = 0;
        return (ReceiveOrClosed) r0;
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public boolean J(Throwable th) {
        boolean z;
        Closed closed = new Closed(th);
        LockFreeLinkedListNode lockFreeLinkedListNode = this.f18979h;
        while (true) {
            LockFreeLinkedListNode U = lockFreeLinkedListNode.U();
            z = true;
            if (!(!(U instanceof Closed))) {
                z = false;
                break;
            }
            if (U.M(closed, lockFreeLinkedListNode)) {
                break;
            }
        }
        if (!z) {
            closed = (Closed) this.f18979h.U();
        }
        p(closed);
        if (z) {
            t(th);
        }
        return z;
    }

    protected final Send K() {
        LockFreeLinkedListNode lockFreeLinkedListNode;
        LockFreeLinkedListNode a0;
        LockFreeLinkedListHead lockFreeLinkedListHead = this.f18979h;
        while (true) {
            lockFreeLinkedListNode = (LockFreeLinkedListNode) lockFreeLinkedListHead.S();
            if (lockFreeLinkedListNode != lockFreeLinkedListHead && (lockFreeLinkedListNode instanceof Send)) {
                if (((((Send) lockFreeLinkedListNode) instanceof Closed) && !lockFreeLinkedListNode.X()) || (a0 = lockFreeLinkedListNode.a0()) == null) {
                    break;
                }
                a0.W();
            }
        }
        lockFreeLinkedListNode = null;
        return (Send) lockFreeLinkedListNode;
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public final Object M(Object obj, Continuation continuation) {
        Object d2;
        if (C(obj) == AbstractChannelKt.f18972b) {
            return Unit.f18288a;
        }
        Object H = H(obj, continuation);
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        return H == d2 ? H : Unit.f18288a;
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public final boolean N() {
        return k() != null;
    }

    protected final LockFreeLinkedListNode.AddLastDesc f(Object obj) {
        return new SendBufferedDesc(this.f18979h, obj);
    }

    protected final TryOfferDesc g(Object obj) {
        return new TryOfferDesc(obj, this.f18979h);
    }

    protected Object h(final Send send) {
        int c0;
        LockFreeLinkedListNode U;
        if (v()) {
            LockFreeLinkedListNode lockFreeLinkedListNode = this.f18979h;
            do {
                U = lockFreeLinkedListNode.U();
                if (U instanceof ReceiveOrClosed) {
                    return U;
                }
            } while (!U.M(send, lockFreeLinkedListNode));
            return null;
        }
        LockFreeLinkedListNode lockFreeLinkedListNode2 = this.f18979h;
        LockFreeLinkedListNode.CondAddOp condAddOp = new LockFreeLinkedListNode.CondAddOp(send) { // from class: kotlinx.coroutines.channels.AbstractSendChannel$enqueueSend$$inlined$addLastIfPrevAndIf$1
            @Override // kotlinx.coroutines.internal.AtomicOp
            /* renamed from: k, reason: merged with bridge method [inline-methods] */
            public Object i(LockFreeLinkedListNode lockFreeLinkedListNode3) {
                if (this.A()) {
                    return null;
                }
                return LockFreeLinkedListKt.a();
            }
        };
        do {
            LockFreeLinkedListNode U2 = lockFreeLinkedListNode2.U();
            if (U2 instanceof ReceiveOrClosed) {
                return U2;
            }
            c0 = U2.c0(send, lockFreeLinkedListNode2, condAddOp);
            if (c0 == 1) {
                return null;
            }
        } while (c0 != 2);
        return AbstractChannelKt.f18975e;
    }

    protected String i() {
        return "";
    }

    protected final Closed j() {
        LockFreeLinkedListNode T = this.f18979h.T();
        Closed closed = T instanceof Closed ? (Closed) T : null;
        if (closed == null) {
            return null;
        }
        p(closed);
        return closed;
    }

    protected final Closed k() {
        LockFreeLinkedListNode U = this.f18979h.U();
        Closed closed = U instanceof Closed ? (Closed) U : null;
        if (closed == null) {
            return null;
        }
        p(closed);
        return closed;
    }

    protected final LockFreeLinkedListHead m() {
        return this.f18979h;
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public final SelectClause2 o() {
        return new SelectClause2<E, SendChannel<? super E>>() { // from class: kotlinx.coroutines.channels.AbstractSendChannel$onSend$1
            @Override // kotlinx.coroutines.selects.SelectClause2
            public void v(SelectInstance selectInstance, Object obj, Function2 function2) {
                AbstractSendChannel.this.F(selectInstance, obj, function2);
            }
        };
    }

    public String toString() {
        return DebugStringsKt.a(this) + '@' + DebugStringsKt.b(this) + '{' + n() + '}' + i();
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public void u(Function1 function1) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f18977i;
        if (atomicReferenceFieldUpdater.compareAndSet(this, null, function1)) {
            Closed k2 = k();
            if (k2 == null || !atomicReferenceFieldUpdater.compareAndSet(this, function1, AbstractChannelKt.f18976f)) {
                return;
            }
            function1.c(k2.f19009j);
            return;
        }
        Object obj = this.onCloseHandler;
        if (obj == AbstractChannelKt.f18976f) {
            throw new IllegalStateException("Another handler was already registered and successfully invoked");
        }
        throw new IllegalStateException("Another handler was already registered: " + obj);
    }

    protected abstract boolean v();
}
