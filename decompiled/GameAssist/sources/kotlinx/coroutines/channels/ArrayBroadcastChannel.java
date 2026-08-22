package kotlinx.coroutines.channels;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.SelectKt;
import org.jetbrains.annotations.NotNull;

@Metadata
/* loaded from: classes2.dex */
public final class ArrayBroadcastChannel<E> extends AbstractSendChannel<E> implements BroadcastChannel<E> {

    @NotNull
    private volatile /* synthetic */ long _head;

    @NotNull
    private volatile /* synthetic */ int _size;

    @NotNull
    private volatile /* synthetic */ long _tail;

    /* renamed from: j, reason: collision with root package name */
    private final int f18988j;

    /* renamed from: k, reason: collision with root package name */
    private final ReentrantLock f18989k;

    /* renamed from: l, reason: collision with root package name */
    private final Object[] f18990l;

    /* renamed from: m, reason: collision with root package name */
    private final List f18991m;

    @Metadata
    private static final class Subscriber<E> extends AbstractChannel<E> implements ReceiveChannel<E> {

        @NotNull
        private volatile /* synthetic */ long _subHead;

        /* renamed from: j, reason: collision with root package name */
        private final ArrayBroadcastChannel f18992j;

        /* renamed from: k, reason: collision with root package name */
        private final ReentrantLock f18993k;

        public Subscriber(ArrayBroadcastChannel arrayBroadcastChannel) {
            super(null);
            this.f18992j = arrayBroadcastChannel;
            this.f18993k = new ReentrantLock();
            this._subHead = 0L;
        }

        private final boolean m0() {
            if (j() != null) {
                return false;
            }
            return (X() && this.f18992j.j() == null) ? false : true;
        }

        private final Object n0() {
            long l0 = l0();
            Closed j2 = this.f18992j.j();
            if (l0 < this.f18992j.V()) {
                Object S = this.f18992j.S(l0);
                Closed j3 = j();
                return j3 != null ? j3 : S;
            }
            if (j2 != null) {
                return j2;
            }
            Closed j4 = j();
            return j4 == null ? AbstractChannelKt.f18974d : j4;
        }

        @Override // kotlinx.coroutines.channels.AbstractSendChannel
        protected boolean A() {
            throw new IllegalStateException("Should not be used".toString());
        }

        @Override // kotlinx.coroutines.channels.AbstractSendChannel, kotlinx.coroutines.channels.SendChannel
        public boolean J(Throwable th) {
            boolean J = super.J(th);
            if (J) {
                ArrayBroadcastChannel.a0(this.f18992j, null, this, 1, null);
                ReentrantLock reentrantLock = this.f18993k;
                reentrantLock.lock();
                try {
                    o0(this.f18992j.V());
                    Unit unit = Unit.f18288a;
                } finally {
                    reentrantLock.unlock();
                }
            }
            return J;
        }

        @Override // kotlinx.coroutines.channels.AbstractChannel
        protected boolean W() {
            return false;
        }

        @Override // kotlinx.coroutines.channels.AbstractChannel
        protected boolean X() {
            return l0() >= this.f18992j.V();
        }

        @Override // kotlinx.coroutines.channels.AbstractChannel
        protected Object e0() {
            boolean z;
            ReentrantLock reentrantLock = this.f18993k;
            reentrantLock.lock();
            try {
                Object n0 = n0();
                if ((n0 instanceof Closed) || n0 == AbstractChannelKt.f18974d) {
                    z = false;
                } else {
                    o0(l0() + 1);
                    z = true;
                }
                reentrantLock.unlock();
                Closed closed = n0 instanceof Closed ? (Closed) n0 : null;
                if (closed != null) {
                    J(closed.f19009j);
                }
                if (k0() || z) {
                    ArrayBroadcastChannel.a0(this.f18992j, null, null, 3, null);
                }
                return n0;
            } catch (Throwable th) {
                reentrantLock.unlock();
                throw th;
            }
        }

        @Override // kotlinx.coroutines.channels.AbstractChannel
        protected Object f0(SelectInstance selectInstance) {
            ReentrantLock reentrantLock = this.f18993k;
            reentrantLock.lock();
            try {
                Object n0 = n0();
                boolean z = false;
                if (!(n0 instanceof Closed) && n0 != AbstractChannelKt.f18974d) {
                    if (selectInstance.w()) {
                        o0(l0() + 1);
                        z = true;
                    } else {
                        n0 = SelectKt.d();
                    }
                }
                reentrantLock.unlock();
                Closed closed = n0 instanceof Closed ? (Closed) n0 : null;
                if (closed != null) {
                    J(closed.f19009j);
                }
                if (k0() || z) {
                    ArrayBroadcastChannel.a0(this.f18992j, null, null, 3, null);
                }
                return n0;
            } catch (Throwable th) {
                reentrantLock.unlock();
                throw th;
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:36:0x0022, code lost:
        
            r2 = (kotlinx.coroutines.channels.Closed) r1;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final boolean k0() {
            /*
                r8 = this;
                r0 = 0
            L1:
                boolean r1 = r8.m0()
                r2 = 0
                if (r1 == 0) goto L5a
                java.util.concurrent.locks.ReentrantLock r1 = r8.f18993k
                boolean r1 = r1.tryLock()
                if (r1 == 0) goto L5a
                java.lang.Object r1 = r8.n0()     // Catch: java.lang.Throwable -> L2b
                kotlinx.coroutines.internal.Symbol r3 = kotlinx.coroutines.channels.AbstractChannelKt.f18974d     // Catch: java.lang.Throwable -> L2b
                if (r1 != r3) goto L1e
            L18:
                java.util.concurrent.locks.ReentrantLock r1 = r8.f18993k
                r1.unlock()
                goto L1
            L1e:
                boolean r3 = r1 instanceof kotlinx.coroutines.channels.Closed     // Catch: java.lang.Throwable -> L2b
                if (r3 == 0) goto L2d
                r2 = r1
                kotlinx.coroutines.channels.Closed r2 = (kotlinx.coroutines.channels.Closed) r2     // Catch: java.lang.Throwable -> L2b
            L25:
                java.util.concurrent.locks.ReentrantLock r1 = r8.f18993k
                r1.unlock()
                goto L5a
            L2b:
                r0 = move-exception
                goto L54
            L2d:
                kotlinx.coroutines.channels.ReceiveOrClosed r3 = r8.I()     // Catch: java.lang.Throwable -> L2b
                if (r3 != 0) goto L34
                goto L25
            L34:
                boolean r4 = r3 instanceof kotlinx.coroutines.channels.Closed     // Catch: java.lang.Throwable -> L2b
                if (r4 == 0) goto L39
                goto L25
            L39:
                kotlinx.coroutines.internal.Symbol r2 = r3.F(r1, r2)     // Catch: java.lang.Throwable -> L2b
                if (r2 != 0) goto L40
                goto L18
            L40:
                long r4 = r8.l0()     // Catch: java.lang.Throwable -> L2b
                r6 = 1
                long r4 = r4 + r6
                r8.o0(r4)     // Catch: java.lang.Throwable -> L2b
                java.util.concurrent.locks.ReentrantLock r0 = r8.f18993k
                r0.unlock()
                r3.p(r1)
                r0 = 1
                goto L1
            L54:
                java.util.concurrent.locks.ReentrantLock r8 = r8.f18993k
                r8.unlock()
                throw r0
            L5a:
                if (r2 == 0) goto L61
                java.lang.Throwable r1 = r2.f19009j
                r8.J(r1)
            L61:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ArrayBroadcastChannel.Subscriber.k0():boolean");
        }

        public final long l0() {
            return this._subHead;
        }

        public final void o0(long j2) {
            this._subHead = j2;
        }

        @Override // kotlinx.coroutines.channels.AbstractSendChannel
        protected boolean v() {
            throw new IllegalStateException("Should not be used".toString());
        }
    }

    private final boolean P(Throwable th) {
        boolean J = J(th);
        Iterator<E> it = this.f18991m.iterator();
        while (it.hasNext()) {
            ((Subscriber) it.next()).Q(th);
        }
        return J;
    }

    private final void Q() {
        boolean z;
        Iterator<E> it = this.f18991m.iterator();
        boolean z2 = false;
        loop0: while (true) {
            z = z2;
            while (it.hasNext()) {
                if (((Subscriber) it.next()).k0()) {
                    break;
                } else {
                    z = true;
                }
            }
            z2 = true;
        }
        if (z2 || !z) {
            a0(this, null, null, 3, null);
        }
    }

    private final long R() {
        Iterator<E> it = this.f18991m.iterator();
        long j2 = Long.MAX_VALUE;
        while (it.hasNext()) {
            j2 = RangesKt___RangesKt.d(j2, ((Subscriber) it.next()).l0());
        }
        return j2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object S(long j2) {
        return this.f18990l[(int) (j2 % this.f18988j)];
    }

    private final long T() {
        return this._head;
    }

    private final int U() {
        return this._size;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long V() {
        return this._tail;
    }

    private final void W(long j2) {
        this._head = j2;
    }

    private final void X(int i2) {
        this._size = i2;
    }

    private final void Y(long j2) {
        this._tail = j2;
    }

    private final void Z(Subscriber subscriber, Subscriber subscriber2) {
        long d2;
        Send K;
        while (true) {
            ReentrantLock reentrantLock = this.f18989k;
            reentrantLock.lock();
            if (subscriber != null) {
                try {
                    subscriber.o0(V());
                    boolean isEmpty = this.f18991m.isEmpty();
                    this.f18991m.add(subscriber);
                    if (!isEmpty) {
                        return;
                    }
                } finally {
                    reentrantLock.unlock();
                }
            }
            if (subscriber2 != null) {
                this.f18991m.remove(subscriber2);
                if (T() != subscriber2.l0()) {
                    return;
                }
            }
            long R = R();
            long V = V();
            long T = T();
            d2 = RangesKt___RangesKt.d(R, V);
            if (d2 <= T) {
                return;
            }
            int U = U();
            while (T < d2) {
                Object[] objArr = this.f18990l;
                int i2 = this.f18988j;
                objArr[(int) (T % i2)] = null;
                boolean z = U >= i2;
                T++;
                W(T);
                int i3 = U - 1;
                X(i3);
                if (z) {
                    do {
                        K = K();
                        if (K != null && !(K instanceof Closed)) {
                            Intrinsics.b(K);
                        }
                    } while (K.g0(null) == null);
                    this.f18990l[(int) (V % this.f18988j)] = K.e0();
                    X(U);
                    Y(V + 1);
                    Unit unit = Unit.f18288a;
                    reentrantLock.unlock();
                    K.d0();
                    Q();
                    subscriber = null;
                    subscriber2 = null;
                }
                U = i3;
            }
            return;
        }
    }

    static /* synthetic */ void a0(ArrayBroadcastChannel arrayBroadcastChannel, Subscriber subscriber, Subscriber subscriber2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            subscriber = null;
        }
        if ((i2 & 2) != 0) {
            subscriber2 = null;
        }
        arrayBroadcastChannel.Z(subscriber, subscriber2);
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    protected boolean A() {
        return U() >= this.f18988j;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    protected Object C(Object obj) {
        ReentrantLock reentrantLock = this.f18989k;
        reentrantLock.lock();
        try {
            Closed k2 = k();
            if (k2 != null) {
                return k2;
            }
            int U = U();
            if (U >= this.f18988j) {
                return AbstractChannelKt.f18973c;
            }
            long V = V();
            this.f18990l[(int) (V % this.f18988j)] = obj;
            X(U + 1);
            Y(V + 1);
            Unit unit = Unit.f18288a;
            reentrantLock.unlock();
            Q();
            return AbstractChannelKt.f18972b;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    protected Object D(Object obj, SelectInstance selectInstance) {
        ReentrantLock reentrantLock = this.f18989k;
        reentrantLock.lock();
        try {
            Closed k2 = k();
            if (k2 != null) {
                return k2;
            }
            int U = U();
            if (U >= this.f18988j) {
                return AbstractChannelKt.f18973c;
            }
            if (!selectInstance.w()) {
                return SelectKt.d();
            }
            long V = V();
            this.f18990l[(int) (V % this.f18988j)] = obj;
            X(U + 1);
            Y(V + 1);
            Unit unit = Unit.f18288a;
            reentrantLock.unlock();
            Q();
            return AbstractChannelKt.f18972b;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel, kotlinx.coroutines.channels.SendChannel
    public boolean J(Throwable th) {
        if (!super.J(th)) {
            return false;
        }
        Q();
        return true;
    }

    @Override // kotlinx.coroutines.channels.BroadcastChannel
    public void a(CancellationException cancellationException) {
        P(cancellationException);
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    protected String i() {
        return "(buffer:capacity=" + this.f18990l.length + ",size=" + U() + ')';
    }

    @Override // kotlinx.coroutines.channels.BroadcastChannel
    public ReceiveChannel q() {
        Subscriber subscriber = new Subscriber(this);
        a0(this, subscriber, null, 2, null);
        return subscriber;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    protected boolean v() {
        return false;
    }
}
