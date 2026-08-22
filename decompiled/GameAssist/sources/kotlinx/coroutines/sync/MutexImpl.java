package kotlinx.coroutines.sync;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImplKt;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.internal.AtomicDesc;
import kotlinx.coroutines.internal.AtomicKt;
import kotlinx.coroutines.internal.AtomicOp;
import kotlinx.coroutines.internal.LockFreeLinkedListHead;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.OpDescriptor;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.intrinsics.CancellableKt;
import kotlinx.coroutines.intrinsics.UndispatchedKt;
import kotlinx.coroutines.selects.SelectClause2;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.SelectKt;
import org.jetbrains.annotations.NotNull;

@Metadata
/* loaded from: classes2.dex */
public final class MutexImpl implements Mutex, SelectClause2<Object, Mutex> {

    /* renamed from: c, reason: collision with root package name */
    static final /* synthetic */ AtomicReferenceFieldUpdater f19499c = AtomicReferenceFieldUpdater.newUpdater(MutexImpl.class, Object.class, "_state");

    @NotNull
    volatile /* synthetic */ Object _state;

    @Metadata
    private final class LockCont extends LockWaiter {

        /* renamed from: m, reason: collision with root package name */
        private final CancellableContinuation f19500m;

        public LockCont(Object obj, CancellableContinuation cancellableContinuation) {
            super(obj);
            this.f19500m = cancellableContinuation;
        }

        @Override // kotlinx.coroutines.sync.MutexImpl.LockWaiter
        public void d0() {
            this.f19500m.O(CancellableContinuationImplKt.f18835a);
        }

        @Override // kotlinx.coroutines.sync.MutexImpl.LockWaiter
        public boolean f0() {
            if (!e0()) {
                return false;
            }
            CancellableContinuation cancellableContinuation = this.f19500m;
            Unit unit = Unit.f18288a;
            final MutexImpl mutexImpl = MutexImpl.this;
            return cancellableContinuation.D(unit, null, new Function1<Throwable, Unit>() { // from class: kotlinx.coroutines.sync.MutexImpl$LockCont$tryResumeLockWaiter$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object c(Object obj) {
                    d((Throwable) obj);
                    return Unit.f18288a;
                }

                public final void d(Throwable th) {
                    MutexImpl.this.b(this.f19506j);
                }
            }) != null;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        public String toString() {
            return "LockCont[" + this.f19506j + ", " + this.f19500m + "] for " + MutexImpl.this;
        }
    }

    @Metadata
    private final class LockSelect<R> extends LockWaiter {

        /* renamed from: m, reason: collision with root package name */
        public final SelectInstance f19502m;

        /* renamed from: n, reason: collision with root package name */
        public final Function2 f19503n;

        public LockSelect(Object obj, SelectInstance selectInstance, Function2 function2) {
            super(obj);
            this.f19502m = selectInstance;
            this.f19503n = function2;
        }

        @Override // kotlinx.coroutines.sync.MutexImpl.LockWaiter
        public void d0() {
            Function2 function2 = this.f19503n;
            MutexImpl mutexImpl = MutexImpl.this;
            Continuation x = this.f19502m.x();
            final MutexImpl mutexImpl2 = MutexImpl.this;
            CancellableKt.d(function2, mutexImpl, x, new Function1<Throwable, Unit>() { // from class: kotlinx.coroutines.sync.MutexImpl$LockSelect$completeResumeLockWaiter$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object c(Object obj) {
                    d((Throwable) obj);
                    return Unit.f18288a;
                }

                public final void d(Throwable th) {
                    MutexImpl.this.b(this.f19506j);
                }
            });
        }

        @Override // kotlinx.coroutines.sync.MutexImpl.LockWaiter
        public boolean f0() {
            return e0() && this.f19502m.w();
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        public String toString() {
            return "LockSelect[" + this.f19506j + ", " + this.f19502m + "] for " + MutexImpl.this;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Metadata
    abstract class LockWaiter extends LockFreeLinkedListNode implements DisposableHandle {

        /* renamed from: l, reason: collision with root package name */
        private static final /* synthetic */ AtomicIntegerFieldUpdater f19505l = AtomicIntegerFieldUpdater.newUpdater(LockWaiter.class, "isTaken");

        @NotNull
        private volatile /* synthetic */ int isTaken = 0;

        /* renamed from: j, reason: collision with root package name */
        public final Object f19506j;

        public LockWaiter(Object obj) {
            this.f19506j = obj;
        }

        public abstract void d0();

        @Override // kotlinx.coroutines.DisposableHandle
        public final void dispose() {
            Y();
        }

        public final boolean e0() {
            return f19505l.compareAndSet(this, 0, 1);
        }

        public abstract boolean f0();
    }

    @Metadata
    private static final class LockedQueue extends LockFreeLinkedListHead {

        @JvmField
        @NotNull
        public volatile Object owner;

        public LockedQueue(Object obj) {
            this.owner = obj;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        public String toString() {
            return "LockedQueue[" + this.owner + ']';
        }
    }

    @Metadata
    private static final class TryLockDesc extends AtomicDesc {

        /* renamed from: b, reason: collision with root package name */
        public final MutexImpl f19508b;

        /* renamed from: c, reason: collision with root package name */
        public final Object f19509c;

        @Metadata
        private final class PrepareOp extends OpDescriptor {

            /* renamed from: a, reason: collision with root package name */
            private final AtomicOp f19510a;

            public PrepareOp(AtomicOp atomicOp) {
                this.f19510a = atomicOp;
            }

            @Override // kotlinx.coroutines.internal.OpDescriptor
            public AtomicOp a() {
                return this.f19510a;
            }

            @Override // kotlinx.coroutines.internal.OpDescriptor
            public Object c(Object obj) {
                Object a2 = a().h() ? MutexKt.f19518f : a();
                if (obj == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.sync.MutexImpl");
                }
                MutexImpl.f19499c.compareAndSet((MutexImpl) obj, this, a2);
                return null;
            }
        }

        public TryLockDesc(MutexImpl mutexImpl, Object obj) {
            this.f19508b = mutexImpl;
            this.f19509c = obj;
        }

        @Override // kotlinx.coroutines.internal.AtomicDesc
        public void a(AtomicOp atomicOp, Object obj) {
            Empty empty;
            if (obj != null) {
                empty = MutexKt.f19518f;
            } else {
                Object obj2 = this.f19509c;
                empty = obj2 == null ? MutexKt.f19517e : new Empty(obj2);
            }
            MutexImpl.f19499c.compareAndSet(this.f19508b, atomicOp, empty);
        }

        @Override // kotlinx.coroutines.internal.AtomicDesc
        public Object c(AtomicOp atomicOp) {
            Empty empty;
            Symbol symbol;
            PrepareOp prepareOp = new PrepareOp(atomicOp);
            MutexImpl mutexImpl = this.f19508b;
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = MutexImpl.f19499c;
            empty = MutexKt.f19518f;
            if (atomicReferenceFieldUpdater.compareAndSet(mutexImpl, empty, prepareOp)) {
                return prepareOp.c(this.f19508b);
            }
            symbol = MutexKt.f19513a;
            return symbol;
        }
    }

    @Metadata
    private static final class UnlockOp extends AtomicOp<MutexImpl> {

        /* renamed from: b, reason: collision with root package name */
        public final LockedQueue f19512b;

        public UnlockOp(LockedQueue lockedQueue) {
            this.f19512b = lockedQueue;
        }

        @Override // kotlinx.coroutines.internal.AtomicOp
        /* renamed from: j, reason: merged with bridge method [inline-methods] */
        public void d(MutexImpl mutexImpl, Object obj) {
            MutexImpl.f19499c.compareAndSet(mutexImpl, this, obj == null ? MutexKt.f19518f : this.f19512b);
        }

        @Override // kotlinx.coroutines.internal.AtomicOp
        /* renamed from: k, reason: merged with bridge method [inline-methods] */
        public Object i(MutexImpl mutexImpl) {
            Symbol symbol;
            if (this.f19512b.d0()) {
                return null;
            }
            symbol = MutexKt.f19514b;
            return symbol;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x0069, code lost:
    
        kotlinx.coroutines.CancellableContinuationKt.c(r0, r1);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final java.lang.Object c(final java.lang.Object r7, kotlin.coroutines.Continuation r8) {
        /*
            r6 = this;
            kotlin.coroutines.Continuation r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.c(r8)
            kotlinx.coroutines.CancellableContinuationImpl r0 = kotlinx.coroutines.CancellableContinuationKt.b(r0)
            kotlinx.coroutines.sync.MutexImpl$LockCont r1 = new kotlinx.coroutines.sync.MutexImpl$LockCont
            r1.<init>(r7, r0)
        Ld:
            java.lang.Object r2 = r6._state
            boolean r3 = r2 instanceof kotlinx.coroutines.sync.Empty
            if (r3 == 0) goto L4a
            r3 = r2
            kotlinx.coroutines.sync.Empty r3 = (kotlinx.coroutines.sync.Empty) r3
            java.lang.Object r4 = r3.f19498a
            kotlinx.coroutines.internal.Symbol r5 = kotlinx.coroutines.sync.MutexKt.e()
            if (r4 == r5) goto L2b
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r4 = kotlinx.coroutines.sync.MutexImpl.f19499c
            kotlinx.coroutines.sync.MutexImpl$LockedQueue r5 = new kotlinx.coroutines.sync.MutexImpl$LockedQueue
            java.lang.Object r3 = r3.f19498a
            r5.<init>(r3)
            r4.compareAndSet(r6, r2, r5)
            goto Ld
        L2b:
            if (r7 != 0) goto L32
            kotlinx.coroutines.sync.Empty r3 = kotlinx.coroutines.sync.MutexKt.a()
            goto L37
        L32:
            kotlinx.coroutines.sync.Empty r3 = new kotlinx.coroutines.sync.Empty
            r3.<init>(r7)
        L37:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r4 = kotlinx.coroutines.sync.MutexImpl.f19499c
            boolean r2 = r4.compareAndSet(r6, r2, r3)
            if (r2 == 0) goto Ld
            kotlin.Unit r1 = kotlin.Unit.f18288a
            kotlinx.coroutines.sync.MutexImpl$lockSuspend$2$1$1 r2 = new kotlinx.coroutines.sync.MutexImpl$lockSuspend$2$1$1
            r2.<init>()
            r0.r(r1, r2)
            goto L6c
        L4a:
            boolean r3 = r2 instanceof kotlinx.coroutines.sync.MutexImpl.LockedQueue
            if (r3 == 0) goto L9e
            r3 = r2
            kotlinx.coroutines.sync.MutexImpl$LockedQueue r3 = (kotlinx.coroutines.sync.MutexImpl.LockedQueue) r3
            java.lang.Object r4 = r3.owner
            if (r4 == r7) goto L83
            r3.L(r1)
            java.lang.Object r3 = r6._state
            if (r3 == r2) goto L69
            boolean r2 = r1.e0()
            if (r2 != 0) goto L63
            goto L69
        L63:
            kotlinx.coroutines.sync.MutexImpl$LockCont r1 = new kotlinx.coroutines.sync.MutexImpl$LockCont
            r1.<init>(r7, r0)
            goto Ld
        L69:
            kotlinx.coroutines.CancellableContinuationKt.c(r0, r1)
        L6c:
            java.lang.Object r6 = r0.w()
            java.lang.Object r7 = kotlin.coroutines.intrinsics.IntrinsicsKt.d()
            if (r6 != r7) goto L79
            kotlin.coroutines.jvm.internal.DebugProbesKt.c(r8)
        L79:
            java.lang.Object r7 = kotlin.coroutines.intrinsics.IntrinsicsKt.d()
            if (r6 != r7) goto L80
            return r6
        L80:
            kotlin.Unit r6 = kotlin.Unit.f18288a
            return r6
        L83:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r8 = "Already locked by "
            r6.append(r8)
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r6 = r6.toString()
            r7.<init>(r6)
            throw r7
        L9e:
            boolean r3 = r2 instanceof kotlinx.coroutines.internal.OpDescriptor
            if (r3 == 0) goto La9
            kotlinx.coroutines.internal.OpDescriptor r2 = (kotlinx.coroutines.internal.OpDescriptor) r2
            r2.c(r6)
            goto Ld
        La9:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "Illegal state "
            r7.append(r8)
            r7.append(r2)
            java.lang.String r7 = r7.toString()
            java.lang.String r7 = r7.toString()
            r6.<init>(r7)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.sync.MutexImpl.c(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.sync.Mutex
    public Object a(Object obj, Continuation continuation) {
        Object d2;
        if (d(obj)) {
            return Unit.f18288a;
        }
        Object c2 = c(obj, continuation);
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        return c2 == d2 ? c2 : Unit.f18288a;
    }

    @Override // kotlinx.coroutines.sync.Mutex
    public void b(Object obj) {
        Empty empty;
        Symbol symbol;
        while (true) {
            Object obj2 = this._state;
            if (obj2 instanceof Empty) {
                if (obj == null) {
                    Object obj3 = ((Empty) obj2).f19498a;
                    symbol = MutexKt.f19516d;
                    if (obj3 == symbol) {
                        throw new IllegalStateException("Mutex is not locked".toString());
                    }
                } else {
                    Empty empty2 = (Empty) obj2;
                    if (empty2.f19498a != obj) {
                        throw new IllegalStateException(("Mutex is locked by " + empty2.f19498a + " but expected " + obj).toString());
                    }
                }
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f19499c;
                empty = MutexKt.f19518f;
                if (atomicReferenceFieldUpdater.compareAndSet(this, obj2, empty)) {
                    return;
                }
            } else if (obj2 instanceof OpDescriptor) {
                ((OpDescriptor) obj2).c(this);
            } else {
                if (!(obj2 instanceof LockedQueue)) {
                    throw new IllegalStateException(("Illegal state " + obj2).toString());
                }
                if (obj != null) {
                    LockedQueue lockedQueue = (LockedQueue) obj2;
                    if (lockedQueue.owner != obj) {
                        throw new IllegalStateException(("Mutex is locked by " + lockedQueue.owner + " but expected " + obj).toString());
                    }
                }
                LockedQueue lockedQueue2 = (LockedQueue) obj2;
                LockFreeLinkedListNode Z = lockedQueue2.Z();
                if (Z == null) {
                    UnlockOp unlockOp = new UnlockOp(lockedQueue2);
                    if (f19499c.compareAndSet(this, obj2, unlockOp) && unlockOp.c(this) == null) {
                        return;
                    }
                } else {
                    LockWaiter lockWaiter = (LockWaiter) Z;
                    if (lockWaiter.f0()) {
                        Object obj4 = lockWaiter.f19506j;
                        if (obj4 == null) {
                            obj4 = MutexKt.f19515c;
                        }
                        lockedQueue2.owner = obj4;
                        lockWaiter.d0();
                        return;
                    }
                }
            }
        }
    }

    public boolean d(Object obj) {
        Symbol symbol;
        while (true) {
            Object obj2 = this._state;
            if (obj2 instanceof Empty) {
                Object obj3 = ((Empty) obj2).f19498a;
                symbol = MutexKt.f19516d;
                if (obj3 != symbol) {
                    return false;
                }
                if (f19499c.compareAndSet(this, obj2, obj == null ? MutexKt.f19517e : new Empty(obj))) {
                    return true;
                }
            } else {
                if (obj2 instanceof LockedQueue) {
                    if (((LockedQueue) obj2).owner != obj) {
                        return false;
                    }
                    throw new IllegalStateException(("Already locked by " + obj).toString());
                }
                if (!(obj2 instanceof OpDescriptor)) {
                    throw new IllegalStateException(("Illegal state " + obj2).toString());
                }
                ((OpDescriptor) obj2).c(this);
            }
        }
    }

    public String toString() {
        while (true) {
            Object obj = this._state;
            if (obj instanceof Empty) {
                return "Mutex[" + ((Empty) obj).f19498a + ']';
            }
            if (!(obj instanceof OpDescriptor)) {
                if (!(obj instanceof LockedQueue)) {
                    throw new IllegalStateException(("Illegal state " + obj).toString());
                }
                return "Mutex[" + ((LockedQueue) obj).owner + ']';
            }
            ((OpDescriptor) obj).c(this);
        }
    }

    @Override // kotlinx.coroutines.selects.SelectClause2
    public void v(SelectInstance selectInstance, Object obj, Function2 function2) {
        Symbol symbol;
        Object obj2;
        while (!selectInstance.k()) {
            Object obj3 = this._state;
            if (obj3 instanceof Empty) {
                Empty empty = (Empty) obj3;
                Object obj4 = empty.f19498a;
                symbol = MutexKt.f19516d;
                if (obj4 != symbol) {
                    f19499c.compareAndSet(this, obj3, new LockedQueue(empty.f19498a));
                } else {
                    Object C = selectInstance.C(new TryLockDesc(this, obj));
                    if (C == null) {
                        UndispatchedKt.c(function2, this, selectInstance.x());
                        return;
                    }
                    if (C == SelectKt.d()) {
                        return;
                    }
                    obj2 = MutexKt.f19513a;
                    if (C != obj2 && C != AtomicKt.f19340b) {
                        throw new IllegalStateException(("performAtomicTrySelect(TryLockDesc) returned " + C).toString());
                    }
                }
            } else if (obj3 instanceof LockedQueue) {
                LockedQueue lockedQueue = (LockedQueue) obj3;
                if (lockedQueue.owner == obj) {
                    throw new IllegalStateException(("Already locked by " + obj).toString());
                }
                LockSelect lockSelect = new LockSelect(obj, selectInstance, function2);
                lockedQueue.L(lockSelect);
                if (this._state == obj3 || !lockSelect.e0()) {
                    selectInstance.s(lockSelect);
                    return;
                }
            } else {
                if (!(obj3 instanceof OpDescriptor)) {
                    throw new IllegalStateException(("Illegal state " + obj3).toString());
                }
                ((OpDescriptor) obj3).c(this);
            }
        }
    }
}
