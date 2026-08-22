package kotlinx.coroutines.internal;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference0Impl;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.InternalCoroutinesApi;
import org.jetbrains.annotations.NotNull;

@InternalCoroutinesApi
@Metadata
/* loaded from: classes2.dex */
public class LockFreeLinkedListNode {

    /* renamed from: c, reason: collision with root package name */
    static final /* synthetic */ AtomicReferenceFieldUpdater f19367c = AtomicReferenceFieldUpdater.newUpdater(LockFreeLinkedListNode.class, Object.class, "_next");

    /* renamed from: h, reason: collision with root package name */
    static final /* synthetic */ AtomicReferenceFieldUpdater f19368h = AtomicReferenceFieldUpdater.newUpdater(LockFreeLinkedListNode.class, Object.class, "_prev");

    /* renamed from: i, reason: collision with root package name */
    private static final /* synthetic */ AtomicReferenceFieldUpdater f19369i = AtomicReferenceFieldUpdater.newUpdater(LockFreeLinkedListNode.class, Object.class, "_removedRef");

    @NotNull
    volatile /* synthetic */ Object _next = this;

    @NotNull
    volatile /* synthetic */ Object _prev = this;

    @NotNull
    private volatile /* synthetic */ Object _removedRef = null;

    @Metadata
    public static abstract class AbstractAtomicDesc extends AtomicDesc {
        @Override // kotlinx.coroutines.internal.AtomicDesc
        public final void a(AtomicOp atomicOp, Object obj) {
            LockFreeLinkedListNode i2;
            boolean z = obj == null;
            LockFreeLinkedListNode h2 = h();
            if (h2 == null || (i2 = i()) == null) {
                return;
            }
            if (LockFreeLinkedListNode.f19367c.compareAndSet(h2, atomicOp, z ? n(h2, i2) : i2) && z) {
                f(h2, i2);
            }
        }

        @Override // kotlinx.coroutines.internal.AtomicDesc
        public final Object c(AtomicOp atomicOp) {
            while (true) {
                LockFreeLinkedListNode m2 = m(atomicOp);
                if (m2 == null) {
                    return AtomicKt.f19340b;
                }
                Object obj = m2._next;
                if (obj == atomicOp || atomicOp.h()) {
                    return null;
                }
                if (obj instanceof OpDescriptor) {
                    OpDescriptor opDescriptor = (OpDescriptor) obj;
                    if (atomicOp.b(opDescriptor)) {
                        return AtomicKt.f19340b;
                    }
                    opDescriptor.c(m2);
                } else {
                    Object e2 = e(m2);
                    if (e2 != null) {
                        return e2;
                    }
                    if (l(m2, obj)) {
                        continue;
                    } else {
                        PrepareOp prepareOp = new PrepareOp(m2, (LockFreeLinkedListNode) obj, this);
                        if (LockFreeLinkedListNode.f19367c.compareAndSet(m2, obj, prepareOp)) {
                            try {
                                if (prepareOp.c(m2) != LockFreeLinkedList_commonKt.f19382a) {
                                    return null;
                                }
                            } catch (Throwable th) {
                                LockFreeLinkedListNode.f19367c.compareAndSet(m2, prepareOp, obj);
                                throw th;
                            }
                        } else {
                            continue;
                        }
                    }
                }
            }
        }

        protected Object e(LockFreeLinkedListNode lockFreeLinkedListNode) {
            return null;
        }

        protected abstract void f(LockFreeLinkedListNode lockFreeLinkedListNode, LockFreeLinkedListNode lockFreeLinkedListNode2);

        public abstract void g(PrepareOp prepareOp);

        protected abstract LockFreeLinkedListNode h();

        protected abstract LockFreeLinkedListNode i();

        public Object j(PrepareOp prepareOp) {
            g(prepareOp);
            return null;
        }

        public void k(LockFreeLinkedListNode lockFreeLinkedListNode) {
        }

        protected boolean l(LockFreeLinkedListNode lockFreeLinkedListNode, Object obj) {
            return false;
        }

        protected LockFreeLinkedListNode m(OpDescriptor opDescriptor) {
            LockFreeLinkedListNode h2 = h();
            Intrinsics.b(h2);
            return h2;
        }

        public abstract Object n(LockFreeLinkedListNode lockFreeLinkedListNode, LockFreeLinkedListNode lockFreeLinkedListNode2);
    }

    @Metadata
    public static class AddLastDesc<T extends LockFreeLinkedListNode> extends AbstractAtomicDesc {

        /* renamed from: d, reason: collision with root package name */
        private static final /* synthetic */ AtomicReferenceFieldUpdater f19370d = AtomicReferenceFieldUpdater.newUpdater(AddLastDesc.class, Object.class, "_affectedNode");

        @NotNull
        private volatile /* synthetic */ Object _affectedNode = null;

        /* renamed from: b, reason: collision with root package name */
        public final LockFreeLinkedListNode f19371b;

        /* renamed from: c, reason: collision with root package name */
        public final LockFreeLinkedListNode f19372c;

        public AddLastDesc(LockFreeLinkedListNode lockFreeLinkedListNode, LockFreeLinkedListNode lockFreeLinkedListNode2) {
            this.f19371b = lockFreeLinkedListNode;
            this.f19372c = lockFreeLinkedListNode2;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        protected void f(LockFreeLinkedListNode lockFreeLinkedListNode, LockFreeLinkedListNode lockFreeLinkedListNode2) {
            this.f19372c.R(this.f19371b);
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        public void g(PrepareOp prepareOp) {
            f19370d.compareAndSet(this, null, prepareOp.f19375a);
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        protected final LockFreeLinkedListNode h() {
            return (LockFreeLinkedListNode) this._affectedNode;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        protected final LockFreeLinkedListNode i() {
            return this.f19371b;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        protected boolean l(LockFreeLinkedListNode lockFreeLinkedListNode, Object obj) {
            return obj != this.f19371b;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        protected final LockFreeLinkedListNode m(OpDescriptor opDescriptor) {
            return this.f19371b.P(opDescriptor);
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        public Object n(LockFreeLinkedListNode lockFreeLinkedListNode, LockFreeLinkedListNode lockFreeLinkedListNode2) {
            LockFreeLinkedListNode lockFreeLinkedListNode3 = this.f19372c;
            LockFreeLinkedListNode.f19368h.compareAndSet(lockFreeLinkedListNode3, lockFreeLinkedListNode3, lockFreeLinkedListNode);
            LockFreeLinkedListNode lockFreeLinkedListNode4 = this.f19372c;
            LockFreeLinkedListNode.f19367c.compareAndSet(lockFreeLinkedListNode4, lockFreeLinkedListNode4, this.f19371b);
            return this.f19372c;
        }
    }

    @Metadata
    @PublishedApi
    public static abstract class CondAddOp extends AtomicOp<LockFreeLinkedListNode> {

        /* renamed from: b, reason: collision with root package name */
        public final LockFreeLinkedListNode f19373b;

        /* renamed from: c, reason: collision with root package name */
        public LockFreeLinkedListNode f19374c;

        public CondAddOp(LockFreeLinkedListNode lockFreeLinkedListNode) {
            this.f19373b = lockFreeLinkedListNode;
        }

        @Override // kotlinx.coroutines.internal.AtomicOp
        /* renamed from: j, reason: merged with bridge method [inline-methods] */
        public void d(LockFreeLinkedListNode lockFreeLinkedListNode, Object obj) {
            boolean z = obj == null;
            LockFreeLinkedListNode lockFreeLinkedListNode2 = z ? this.f19373b : this.f19374c;
            if (lockFreeLinkedListNode2 != null && LockFreeLinkedListNode.f19367c.compareAndSet(lockFreeLinkedListNode, this, lockFreeLinkedListNode2) && z) {
                LockFreeLinkedListNode lockFreeLinkedListNode3 = this.f19373b;
                LockFreeLinkedListNode lockFreeLinkedListNode4 = this.f19374c;
                Intrinsics.b(lockFreeLinkedListNode4);
                lockFreeLinkedListNode3.R(lockFreeLinkedListNode4);
            }
        }
    }

    @Metadata
    public static final class PrepareOp extends OpDescriptor {

        /* renamed from: a, reason: collision with root package name */
        public final LockFreeLinkedListNode f19375a;

        /* renamed from: b, reason: collision with root package name */
        public final LockFreeLinkedListNode f19376b;

        /* renamed from: c, reason: collision with root package name */
        public final AbstractAtomicDesc f19377c;

        public PrepareOp(LockFreeLinkedListNode lockFreeLinkedListNode, LockFreeLinkedListNode lockFreeLinkedListNode2, AbstractAtomicDesc abstractAtomicDesc) {
            this.f19375a = lockFreeLinkedListNode;
            this.f19376b = lockFreeLinkedListNode2;
            this.f19377c = abstractAtomicDesc;
        }

        @Override // kotlinx.coroutines.internal.OpDescriptor
        public AtomicOp a() {
            return this.f19377c.b();
        }

        @Override // kotlinx.coroutines.internal.OpDescriptor
        public Object c(Object obj) {
            if (obj == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
            }
            LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) obj;
            Object j2 = this.f19377c.j(this);
            Object obj2 = LockFreeLinkedList_commonKt.f19382a;
            if (j2 != obj2) {
                Object e2 = j2 != null ? a().e(j2) : a().f();
                LockFreeLinkedListNode.f19367c.compareAndSet(lockFreeLinkedListNode, this, e2 == AtomicKt.f19339a ? a() : e2 == null ? this.f19377c.n(lockFreeLinkedListNode, this.f19376b) : this.f19376b);
                return null;
            }
            LockFreeLinkedListNode lockFreeLinkedListNode2 = this.f19376b;
            if (LockFreeLinkedListNode.f19367c.compareAndSet(lockFreeLinkedListNode, this, lockFreeLinkedListNode2.b0())) {
                this.f19377c.k(lockFreeLinkedListNode);
                lockFreeLinkedListNode2.P(null);
            }
            return obj2;
        }

        public final void d() {
            this.f19377c.g(this);
        }

        @Override // kotlinx.coroutines.internal.OpDescriptor
        public String toString() {
            return "PrepareOp(op=" + a() + ')';
        }
    }

    @Metadata
    public static class RemoveFirstDesc<T> extends AbstractAtomicDesc {

        /* renamed from: c, reason: collision with root package name */
        private static final /* synthetic */ AtomicReferenceFieldUpdater f19378c = AtomicReferenceFieldUpdater.newUpdater(RemoveFirstDesc.class, Object.class, "_affectedNode");

        /* renamed from: d, reason: collision with root package name */
        private static final /* synthetic */ AtomicReferenceFieldUpdater f19379d = AtomicReferenceFieldUpdater.newUpdater(RemoveFirstDesc.class, Object.class, "_originalNext");

        @NotNull
        private volatile /* synthetic */ Object _affectedNode = null;

        @NotNull
        private volatile /* synthetic */ Object _originalNext = null;

        /* renamed from: b, reason: collision with root package name */
        public final LockFreeLinkedListNode f19380b;

        public RemoveFirstDesc(LockFreeLinkedListNode lockFreeLinkedListNode) {
            this.f19380b = lockFreeLinkedListNode;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        protected Object e(LockFreeLinkedListNode lockFreeLinkedListNode) {
            if (lockFreeLinkedListNode == this.f19380b) {
                return LockFreeLinkedListKt.b();
            }
            return null;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        protected final void f(LockFreeLinkedListNode lockFreeLinkedListNode, LockFreeLinkedListNode lockFreeLinkedListNode2) {
            lockFreeLinkedListNode2.P(null);
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        public void g(PrepareOp prepareOp) {
            f19378c.compareAndSet(this, null, prepareOp.f19375a);
            f19379d.compareAndSet(this, null, prepareOp.f19376b);
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        protected final LockFreeLinkedListNode h() {
            return (LockFreeLinkedListNode) this._affectedNode;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        protected final LockFreeLinkedListNode i() {
            return (LockFreeLinkedListNode) this._originalNext;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        protected final boolean l(LockFreeLinkedListNode lockFreeLinkedListNode, Object obj) {
            if (!(obj instanceof Removed)) {
                return false;
            }
            ((Removed) obj).f19398a.W();
            return true;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        protected final LockFreeLinkedListNode m(OpDescriptor opDescriptor) {
            LockFreeLinkedListNode lockFreeLinkedListNode = this.f19380b;
            while (true) {
                Object obj = lockFreeLinkedListNode._next;
                if (!(obj instanceof OpDescriptor)) {
                    return (LockFreeLinkedListNode) obj;
                }
                OpDescriptor opDescriptor2 = (OpDescriptor) obj;
                if (opDescriptor.b(opDescriptor2)) {
                    return null;
                }
                opDescriptor2.c(this.f19380b);
            }
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        public final Object n(LockFreeLinkedListNode lockFreeLinkedListNode, LockFreeLinkedListNode lockFreeLinkedListNode2) {
            return lockFreeLinkedListNode2.b0();
        }

        public final Object o() {
            LockFreeLinkedListNode h2 = h();
            Intrinsics.b(h2);
            return h2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0048, code lost:
    
        if (kotlinx.coroutines.internal.LockFreeLinkedListNode.f19367c.compareAndSet(r3, r2, ((kotlinx.coroutines.internal.Removed) r4).f19398a) != false) goto L30;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final kotlinx.coroutines.internal.LockFreeLinkedListNode P(kotlinx.coroutines.internal.OpDescriptor r8) {
        /*
            r7 = this;
        L0:
            java.lang.Object r0 = r7._prev
            kotlinx.coroutines.internal.LockFreeLinkedListNode r0 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r0
            r1 = 0
            r2 = r0
        L6:
            r3 = r1
        L7:
            java.lang.Object r4 = r2._next
            if (r4 != r7) goto L18
            if (r0 != r2) goto Le
            return r2
        Le:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r1 = kotlinx.coroutines.internal.LockFreeLinkedListNode.f19368h
            boolean r0 = r1.compareAndSet(r7, r0, r2)
            if (r0 != 0) goto L17
            goto L0
        L17:
            return r2
        L18:
            boolean r5 = r7.X()
            if (r5 == 0) goto L1f
            return r1
        L1f:
            if (r4 != r8) goto L22
            return r2
        L22:
            boolean r5 = r4 instanceof kotlinx.coroutines.internal.OpDescriptor
            if (r5 == 0) goto L38
            if (r8 == 0) goto L32
            r0 = r4
            kotlinx.coroutines.internal.OpDescriptor r0 = (kotlinx.coroutines.internal.OpDescriptor) r0
            boolean r0 = r8.b(r0)
            if (r0 == 0) goto L32
            return r1
        L32:
            kotlinx.coroutines.internal.OpDescriptor r4 = (kotlinx.coroutines.internal.OpDescriptor) r4
            r4.c(r2)
            goto L0
        L38:
            boolean r5 = r4 instanceof kotlinx.coroutines.internal.Removed
            if (r5 == 0) goto L52
            if (r3 == 0) goto L4d
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r5 = kotlinx.coroutines.internal.LockFreeLinkedListNode.f19367c
            kotlinx.coroutines.internal.Removed r4 = (kotlinx.coroutines.internal.Removed) r4
            kotlinx.coroutines.internal.LockFreeLinkedListNode r4 = r4.f19398a
            boolean r2 = r5.compareAndSet(r3, r2, r4)
            if (r2 != 0) goto L4b
            goto L0
        L4b:
            r2 = r3
            goto L6
        L4d:
            java.lang.Object r2 = r2._prev
            kotlinx.coroutines.internal.LockFreeLinkedListNode r2 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r2
            goto L7
        L52:
            r3 = r4
            kotlinx.coroutines.internal.LockFreeLinkedListNode r3 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r3
            r6 = r3
            r3 = r2
            r2 = r6
            goto L7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.internal.LockFreeLinkedListNode.P(kotlinx.coroutines.internal.OpDescriptor):kotlinx.coroutines.internal.LockFreeLinkedListNode");
    }

    private final LockFreeLinkedListNode Q(LockFreeLinkedListNode lockFreeLinkedListNode) {
        while (lockFreeLinkedListNode.X()) {
            lockFreeLinkedListNode = (LockFreeLinkedListNode) lockFreeLinkedListNode._prev;
        }
        return lockFreeLinkedListNode;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void R(LockFreeLinkedListNode lockFreeLinkedListNode) {
        LockFreeLinkedListNode lockFreeLinkedListNode2;
        do {
            lockFreeLinkedListNode2 = (LockFreeLinkedListNode) lockFreeLinkedListNode._prev;
            if (S() != lockFreeLinkedListNode) {
                return;
            }
        } while (!f19368h.compareAndSet(lockFreeLinkedListNode, lockFreeLinkedListNode2, this));
        if (X()) {
            lockFreeLinkedListNode.P(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Removed b0() {
        Removed removed = (Removed) this._removedRef;
        if (removed != null) {
            return removed;
        }
        Removed removed2 = new Removed(this);
        f19369i.lazySet(this, removed2);
        return removed2;
    }

    public final void L(LockFreeLinkedListNode lockFreeLinkedListNode) {
        while (!U().M(lockFreeLinkedListNode, this)) {
        }
    }

    public final boolean M(LockFreeLinkedListNode lockFreeLinkedListNode, LockFreeLinkedListNode lockFreeLinkedListNode2) {
        f19368h.lazySet(lockFreeLinkedListNode, this);
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f19367c;
        atomicReferenceFieldUpdater.lazySet(lockFreeLinkedListNode, lockFreeLinkedListNode2);
        if (!atomicReferenceFieldUpdater.compareAndSet(this, lockFreeLinkedListNode2, lockFreeLinkedListNode)) {
            return false;
        }
        lockFreeLinkedListNode.R(lockFreeLinkedListNode2);
        return true;
    }

    public final boolean N(LockFreeLinkedListNode lockFreeLinkedListNode) {
        f19368h.lazySet(lockFreeLinkedListNode, this);
        f19367c.lazySet(lockFreeLinkedListNode, this);
        while (S() == this) {
            if (f19367c.compareAndSet(this, this, lockFreeLinkedListNode)) {
                lockFreeLinkedListNode.R(this);
                return true;
            }
        }
        return false;
    }

    public final Object S() {
        while (true) {
            Object obj = this._next;
            if (!(obj instanceof OpDescriptor)) {
                return obj;
            }
            ((OpDescriptor) obj).c(this);
        }
    }

    public final LockFreeLinkedListNode T() {
        return LockFreeLinkedListKt.c(S());
    }

    public final LockFreeLinkedListNode U() {
        LockFreeLinkedListNode P = P(null);
        return P == null ? Q((LockFreeLinkedListNode) this._prev) : P;
    }

    public final void V() {
        ((Removed) S()).f19398a.W();
    }

    public final void W() {
        while (true) {
            Object S = this.S();
            if (!(S instanceof Removed)) {
                this.P(null);
                return;
            }
            this = ((Removed) S).f19398a;
        }
    }

    public boolean X() {
        return S() instanceof Removed;
    }

    public boolean Y() {
        return a0() == null;
    }

    public final LockFreeLinkedListNode Z() {
        while (true) {
            LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) S();
            if (lockFreeLinkedListNode == this) {
                return null;
            }
            if (lockFreeLinkedListNode.Y()) {
                return lockFreeLinkedListNode;
            }
            lockFreeLinkedListNode.V();
        }
    }

    public final LockFreeLinkedListNode a0() {
        Object S;
        LockFreeLinkedListNode lockFreeLinkedListNode;
        do {
            S = S();
            if (S instanceof Removed) {
                return ((Removed) S).f19398a;
            }
            if (S == this) {
                return (LockFreeLinkedListNode) S;
            }
            lockFreeLinkedListNode = (LockFreeLinkedListNode) S;
        } while (!f19367c.compareAndSet(this, S, lockFreeLinkedListNode.b0()));
        lockFreeLinkedListNode.P(null);
        return null;
    }

    public final int c0(LockFreeLinkedListNode lockFreeLinkedListNode, LockFreeLinkedListNode lockFreeLinkedListNode2, CondAddOp condAddOp) {
        f19368h.lazySet(lockFreeLinkedListNode, this);
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f19367c;
        atomicReferenceFieldUpdater.lazySet(lockFreeLinkedListNode, lockFreeLinkedListNode2);
        condAddOp.f19374c = lockFreeLinkedListNode2;
        if (atomicReferenceFieldUpdater.compareAndSet(this, lockFreeLinkedListNode2, condAddOp)) {
            return condAddOp.c(this) == null ? 1 : 2;
        }
        return 0;
    }

    public String toString() {
        return new PropertyReference0Impl(this) { // from class: kotlinx.coroutines.internal.LockFreeLinkedListNode$toString$1
            @Override // kotlin.jvm.internal.PropertyReference0Impl, kotlin.reflect.KProperty0
            public Object get() {
                return DebugStringsKt.a(this.receiver);
            }
        } + '@' + DebugStringsKt.b(this);
    }
}
