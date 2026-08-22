package kotlinx.coroutines.channels;

import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.AbstractSendChannel;
import kotlinx.coroutines.internal.AtomicKt;
import kotlinx.coroutines.internal.OnUndeliveredElementKt;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.internal.UndeliveredElementException;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.SelectKt;

@Metadata
/* loaded from: classes2.dex */
public class ConflatedChannel<E> extends AbstractChannel<E> {

    /* renamed from: j, reason: collision with root package name */
    private final ReentrantLock f19022j;

    /* renamed from: k, reason: collision with root package name */
    private Object f19023k;

    public ConflatedChannel(Function1 function1) {
        super(function1);
        this.f19022j = new ReentrantLock();
        this.f19023k = AbstractChannelKt.f18971a;
    }

    private final UndeliveredElementException k0(Object obj) {
        Function1 function1;
        Object obj2 = this.f19023k;
        UndeliveredElementException undeliveredElementException = null;
        if (obj2 != AbstractChannelKt.f18971a && (function1 = this.f18978c) != null) {
            undeliveredElementException = OnUndeliveredElementKt.d(function1, obj2, null, 2, null);
        }
        this.f19023k = obj;
        return undeliveredElementException;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    protected final boolean A() {
        return false;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    protected Object C(Object obj) {
        ReceiveOrClosed I;
        ReentrantLock reentrantLock = this.f19022j;
        reentrantLock.lock();
        try {
            Closed k2 = k();
            if (k2 != null) {
                return k2;
            }
            if (this.f19023k == AbstractChannelKt.f18971a) {
                do {
                    I = I();
                    if (I != null) {
                        if (I instanceof Closed) {
                            return I;
                        }
                        Intrinsics.b(I);
                    }
                } while (I.F(obj, null) == null);
                Unit unit = Unit.f18288a;
                reentrantLock.unlock();
                I.p(obj);
                return I.e();
            }
            UndeliveredElementException k0 = k0(obj);
            if (k0 == null) {
                return AbstractChannelKt.f18972b;
            }
            throw k0;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    protected Object D(Object obj, SelectInstance selectInstance) {
        Object C;
        ReentrantLock reentrantLock = this.f19022j;
        reentrantLock.lock();
        try {
            Closed k2 = k();
            if (k2 != null) {
                return k2;
            }
            if (this.f19023k == AbstractChannelKt.f18971a) {
                do {
                    AbstractSendChannel.TryOfferDesc g2 = g(obj);
                    C = selectInstance.C(g2);
                    if (C == null) {
                        Object o2 = g2.o();
                        Unit unit = Unit.f18288a;
                        reentrantLock.unlock();
                        Intrinsics.b(o2);
                        ReceiveOrClosed receiveOrClosed = (ReceiveOrClosed) o2;
                        receiveOrClosed.p(obj);
                        return receiveOrClosed.e();
                    }
                    if (C != AbstractChannelKt.f18973c) {
                    }
                } while (C == AtomicKt.f19340b);
                if (C != SelectKt.d() && !(C instanceof Closed)) {
                    throw new IllegalStateException(("performAtomicTrySelect(describeTryOffer) returned " + C).toString());
                }
                return C;
            }
            if (!selectInstance.w()) {
                return SelectKt.d();
            }
            UndeliveredElementException k0 = k0(obj);
            if (k0 == null) {
                return AbstractChannelKt.f18972b;
            }
            throw k0;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    protected boolean T(Receive receive) {
        ReentrantLock reentrantLock = this.f19022j;
        reentrantLock.lock();
        try {
            return super.T(receive);
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    protected final boolean W() {
        return false;
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    protected final boolean X() {
        ReentrantLock reentrantLock = this.f19022j;
        reentrantLock.lock();
        try {
            return this.f19023k == AbstractChannelKt.f18971a;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    protected void a0(boolean z) {
        ReentrantLock reentrantLock = this.f19022j;
        reentrantLock.lock();
        try {
            UndeliveredElementException k0 = k0(AbstractChannelKt.f18971a);
            Unit unit = Unit.f18288a;
            reentrantLock.unlock();
            super.a0(z);
            if (k0 != null) {
                throw k0;
            }
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    protected Object e0() {
        ReentrantLock reentrantLock = this.f19022j;
        reentrantLock.lock();
        try {
            Object obj = this.f19023k;
            Symbol symbol = AbstractChannelKt.f18971a;
            if (obj != symbol) {
                this.f19023k = symbol;
                Unit unit = Unit.f18288a;
                return obj;
            }
            Object k2 = k();
            if (k2 == null) {
                k2 = AbstractChannelKt.f18974d;
            }
            return k2;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    protected Object f0(SelectInstance selectInstance) {
        ReentrantLock reentrantLock = this.f19022j;
        reentrantLock.lock();
        try {
            Object obj = this.f19023k;
            Symbol symbol = AbstractChannelKt.f18971a;
            if (obj == symbol) {
                Object k2 = k();
                if (k2 == null) {
                    k2 = AbstractChannelKt.f18974d;
                }
                return k2;
            }
            if (!selectInstance.w()) {
                return SelectKt.d();
            }
            Object obj2 = this.f19023k;
            this.f19023k = symbol;
            Unit unit = Unit.f18288a;
            return obj2;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    protected String i() {
        ReentrantLock reentrantLock = this.f19022j;
        reentrantLock.lock();
        try {
            return "(value=" + this.f19023k + ')';
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    protected final boolean v() {
        return false;
    }
}
