package kotlinx.coroutines.channels;

import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.ObsoleteCoroutinesApi;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.intrinsics.UndispatchedKt;
import kotlinx.coroutines.selects.SelectInstance;
import org.jetbrains.annotations.NotNull;

@ObsoleteCoroutinesApi
@Metadata
/* loaded from: classes2.dex */
public final class ConflatedBroadcastChannel<E> implements BroadcastChannel<E> {

    /* renamed from: h, reason: collision with root package name */
    private static final /* synthetic */ AtomicReferenceFieldUpdater f19011h;

    /* renamed from: i, reason: collision with root package name */
    private static final /* synthetic */ AtomicIntegerFieldUpdater f19012i;

    /* renamed from: j, reason: collision with root package name */
    private static final /* synthetic */ AtomicReferenceFieldUpdater f19013j;

    /* renamed from: l, reason: collision with root package name */
    private static final Symbol f19015l;

    /* renamed from: m, reason: collision with root package name */
    private static final State f19016m;

    @NotNull
    private volatile /* synthetic */ Object _state;

    @NotNull
    private volatile /* synthetic */ int _updating;

    @NotNull
    private volatile /* synthetic */ Object onCloseHandler;

    /* renamed from: c, reason: collision with root package name */
    private static final Companion f19010c = new Companion(null);

    /* renamed from: k, reason: collision with root package name */
    private static final Closed f19014k = new Closed(null);

    @Metadata
    private static final class Closed {

        /* renamed from: a, reason: collision with root package name */
        public final Throwable f19017a;

        public Closed(Throwable th) {
            this.f19017a = th;
        }

        public final Throwable a() {
            Throwable th = this.f19017a;
            return th == null ? new ClosedSendChannelException("Channel was closed") : th;
        }
    }

    @Metadata
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata
    private static final class State<E> {

        /* renamed from: a, reason: collision with root package name */
        public final Object f19018a;

        /* renamed from: b, reason: collision with root package name */
        public final Subscriber[] f19019b;

        public State(Object obj, Subscriber[] subscriberArr) {
            this.f19018a = obj;
            this.f19019b = subscriberArr;
        }
    }

    @Metadata
    private static final class Subscriber<E> extends ConflatedChannel<E> implements ReceiveChannel<E> {

        /* renamed from: l, reason: collision with root package name */
        private final ConflatedBroadcastChannel f19020l;

        public Subscriber(ConflatedBroadcastChannel conflatedBroadcastChannel) {
            super(null);
            this.f19020l = conflatedBroadcastChannel;
        }

        @Override // kotlinx.coroutines.channels.ConflatedChannel, kotlinx.coroutines.channels.AbstractSendChannel
        public Object C(Object obj) {
            return super.C(obj);
        }

        @Override // kotlinx.coroutines.channels.ConflatedChannel, kotlinx.coroutines.channels.AbstractChannel
        protected void a0(boolean z) {
            if (z) {
                this.f19020l.e(this);
            }
        }
    }

    static {
        Symbol symbol = new Symbol("UNDEFINED");
        f19015l = symbol;
        f19016m = new State(symbol, null);
        f19011h = AtomicReferenceFieldUpdater.newUpdater(ConflatedBroadcastChannel.class, Object.class, "_state");
        f19012i = AtomicIntegerFieldUpdater.newUpdater(ConflatedBroadcastChannel.class, "_updating");
        f19013j = AtomicReferenceFieldUpdater.newUpdater(ConflatedBroadcastChannel.class, Object.class, "onCloseHandler");
    }

    private final Subscriber[] d(Subscriber[] subscriberArr, Subscriber subscriber) {
        Object[] q2;
        if (subscriberArr == null) {
            return new Subscriber[]{subscriber};
        }
        q2 = ArraysKt___ArraysJvmKt.q(subscriberArr, subscriber);
        return (Subscriber[]) q2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void e(Subscriber subscriber) {
        Object obj;
        Object obj2;
        Subscriber[] subscriberArr;
        do {
            obj = this._state;
            if (obj instanceof Closed) {
                return;
            }
            if (!(obj instanceof State)) {
                throw new IllegalStateException(("Invalid state " + obj).toString());
            }
            State state = (State) obj;
            obj2 = state.f19018a;
            subscriberArr = state.f19019b;
            Intrinsics.b(subscriberArr);
        } while (!f19011h.compareAndSet(this, obj, new State(obj2, i(subscriberArr, subscriber))));
    }

    private final void f(Throwable th) {
        Symbol symbol;
        Object obj = this.onCloseHandler;
        if (obj == null || obj == (symbol = AbstractChannelKt.f18976f) || !f19013j.compareAndSet(this, obj, symbol)) {
            return;
        }
        ((Function1) TypeIntrinsics.a(obj, 1)).c(th);
    }

    private final Closed g(Object obj) {
        Object obj2;
        if (!f19012i.compareAndSet(this, 0, 1)) {
            return null;
        }
        do {
            try {
                obj2 = this._state;
                if (obj2 instanceof Closed) {
                    return (Closed) obj2;
                }
                if (!(obj2 instanceof State)) {
                    throw new IllegalStateException(("Invalid state " + obj2).toString());
                }
            } finally {
                this._updating = 0;
            }
        } while (!f19011h.compareAndSet(this, obj2, new State(obj, ((State) obj2).f19019b)));
        Subscriber[] subscriberArr = ((State) obj2).f19019b;
        if (subscriberArr != null) {
            for (Subscriber subscriber : subscriberArr) {
                subscriber.C(obj);
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void h(SelectInstance selectInstance, Object obj, Function2 function2) {
        if (selectInstance.w()) {
            Closed g2 = g(obj);
            if (g2 != null) {
                selectInstance.A(g2.a());
            } else {
                UndispatchedKt.c(function2, this, selectInstance.x());
            }
        }
    }

    private final Subscriber[] i(Subscriber[] subscriberArr, Subscriber subscriber) {
        int length = subscriberArr.length;
        int H = ArraysKt___ArraysKt.H(subscriberArr, subscriber);
        if (length == 1) {
            return null;
        }
        Subscriber[] subscriberArr2 = new Subscriber[length - 1];
        ArraysKt___ArraysJvmKt.i(subscriberArr, subscriberArr2, 0, 0, H, 6, null);
        ArraysKt___ArraysJvmKt.i(subscriberArr, subscriberArr2, H, H + 1, 0, 8, null);
        return subscriberArr2;
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public boolean J(Throwable th) {
        Object obj;
        int i2;
        do {
            obj = this._state;
            if (obj instanceof Closed) {
                return false;
            }
            if (!(obj instanceof State)) {
                throw new IllegalStateException(("Invalid state " + obj).toString());
            }
        } while (!f19011h.compareAndSet(this, obj, th == null ? f19014k : new Closed(th)));
        Subscriber[] subscriberArr = ((State) obj).f19019b;
        if (subscriberArr != null) {
            for (Subscriber subscriber : subscriberArr) {
                subscriber.J(th);
            }
        }
        f(th);
        return true;
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public Object M(Object obj, Continuation continuation) {
        Object d2;
        Closed g2 = g(obj);
        if (g2 != null) {
            throw g2.a();
        }
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        if (d2 == null) {
            return null;
        }
        return Unit.f18288a;
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public boolean N() {
        return this._state instanceof Closed;
    }

    @Override // kotlinx.coroutines.channels.BroadcastChannel
    public void a(CancellationException cancellationException) {
        J(cancellationException);
    }

    @Override // kotlinx.coroutines.channels.BroadcastChannel
    public ReceiveChannel q() {
        Object obj;
        State state;
        Subscriber subscriber = new Subscriber(this);
        do {
            obj = this._state;
            if (obj instanceof Closed) {
                subscriber.J(((Closed) obj).f19017a);
                return subscriber;
            }
            if (!(obj instanceof State)) {
                throw new IllegalStateException(("Invalid state " + obj).toString());
            }
            state = (State) obj;
            Object obj2 = state.f19018a;
            if (obj2 != f19015l) {
                subscriber.C(obj2);
            }
        } while (!f19011h.compareAndSet(this, obj, new State(state.f19018a, d(state.f19019b, subscriber))));
        return subscriber;
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public void u(Function1 function1) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f19013j;
        if (atomicReferenceFieldUpdater.compareAndSet(this, null, function1)) {
            Object obj = this._state;
            if ((obj instanceof Closed) && atomicReferenceFieldUpdater.compareAndSet(this, function1, AbstractChannelKt.f18976f)) {
                function1.c(((Closed) obj).f19017a);
                return;
            }
            return;
        }
        Object obj2 = this.onCloseHandler;
        if (obj2 == AbstractChannelKt.f18976f) {
            throw new IllegalStateException("Another handler was already registered and successfully invoked");
        }
        throw new IllegalStateException("Another handler was already registered: " + obj2);
    }
}
