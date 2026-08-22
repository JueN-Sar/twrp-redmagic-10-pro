package kotlinx.coroutines.channels;

import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.AbstractSendChannel;
import kotlinx.coroutines.internal.AtomicKt;
import kotlinx.coroutines.internal.OnUndeliveredElementKt;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.internal.UndeliveredElementException;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.SelectKt;
import org.jetbrains.annotations.NotNull;

@Metadata
/* loaded from: classes2.dex */
public class ArrayChannel<E> extends AbstractChannel<E> {

    /* renamed from: j, reason: collision with root package name */
    private final int f18994j;

    /* renamed from: k, reason: collision with root package name */
    private final BufferOverflow f18995k;

    /* renamed from: l, reason: collision with root package name */
    private final ReentrantLock f18996l;

    /* renamed from: m, reason: collision with root package name */
    private Object[] f18997m;

    /* renamed from: n, reason: collision with root package name */
    private int f18998n;

    @NotNull
    private volatile /* synthetic */ int size;

    @Metadata
    public /* synthetic */ class WhenMappings {

        /* renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f18999a;

        static {
            int[] iArr = new int[BufferOverflow.values().length];
            iArr[BufferOverflow.SUSPEND.ordinal()] = 1;
            iArr[BufferOverflow.DROP_LATEST.ordinal()] = 2;
            iArr[BufferOverflow.DROP_OLDEST.ordinal()] = 3;
            f18999a = iArr;
        }
    }

    public ArrayChannel(int i2, BufferOverflow bufferOverflow, Function1 function1) {
        super(function1);
        this.f18994j = i2;
        this.f18995k = bufferOverflow;
        if (i2 < 1) {
            throw new IllegalArgumentException(("ArrayChannel capacity must be at least 1, but " + i2 + " was specified").toString());
        }
        this.f18996l = new ReentrantLock();
        Object[] objArr = new Object[Math.min(i2, 8)];
        ArraysKt___ArraysJvmKt.p(objArr, AbstractChannelKt.f18971a, 0, 0, 6, null);
        this.f18997m = objArr;
        this.size = 0;
    }

    private final void k0(int i2, Object obj) {
        if (i2 < this.f18994j) {
            l0(i2);
            Object[] objArr = this.f18997m;
            objArr[(this.f18998n + i2) % objArr.length] = obj;
        } else {
            Object[] objArr2 = this.f18997m;
            int i3 = this.f18998n;
            objArr2[i3 % objArr2.length] = null;
            objArr2[(i2 + i3) % objArr2.length] = obj;
            this.f18998n = (i3 + 1) % objArr2.length;
        }
    }

    private final void l0(int i2) {
        Object[] objArr = this.f18997m;
        if (i2 >= objArr.length) {
            int min = Math.min(objArr.length * 2, this.f18994j);
            Object[] objArr2 = new Object[min];
            for (int i3 = 0; i3 < i2; i3++) {
                Object[] objArr3 = this.f18997m;
                objArr2[i3] = objArr3[(this.f18998n + i3) % objArr3.length];
            }
            ArraysKt___ArraysJvmKt.m(objArr2, AbstractChannelKt.f18971a, i2, min);
            this.f18997m = objArr2;
            this.f18998n = 0;
        }
    }

    private final Symbol m0(int i2) {
        if (i2 < this.f18994j) {
            this.size = i2 + 1;
            return null;
        }
        int i3 = WhenMappings.f18999a[this.f18995k.ordinal()];
        if (i3 == 1) {
            return AbstractChannelKt.f18973c;
        }
        if (i3 == 2) {
            return AbstractChannelKt.f18972b;
        }
        if (i3 == 3) {
            return null;
        }
        throw new NoWhenBranchMatchedException();
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    protected final boolean A() {
        return this.size == this.f18994j && this.f18995k == BufferOverflow.SUSPEND;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    protected Object C(Object obj) {
        ReceiveOrClosed I;
        ReentrantLock reentrantLock = this.f18996l;
        reentrantLock.lock();
        try {
            int i2 = this.size;
            Closed k2 = k();
            if (k2 != null) {
                return k2;
            }
            Symbol m0 = m0(i2);
            if (m0 != null) {
                return m0;
            }
            if (i2 == 0) {
                do {
                    I = I();
                    if (I != null) {
                        if (I instanceof Closed) {
                            this.size = i2;
                            return I;
                        }
                        Intrinsics.b(I);
                    }
                } while (I.F(obj, null) == null);
                this.size = i2;
                Unit unit = Unit.f18288a;
                reentrantLock.unlock();
                I.p(obj);
                return I.e();
            }
            k0(i2, obj);
            return AbstractChannelKt.f18972b;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    protected Object D(Object obj, SelectInstance selectInstance) {
        Object C;
        ReentrantLock reentrantLock = this.f18996l;
        reentrantLock.lock();
        try {
            int i2 = this.size;
            Closed k2 = k();
            if (k2 != null) {
                return k2;
            }
            Symbol m0 = m0(i2);
            if (m0 != null) {
                return m0;
            }
            if (i2 == 0) {
                do {
                    AbstractSendChannel.TryOfferDesc g2 = g(obj);
                    C = selectInstance.C(g2);
                    if (C == null) {
                        this.size = i2;
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
                this.size = i2;
                return C;
            }
            if (selectInstance.w()) {
                k0(i2, obj);
                return AbstractChannelKt.f18972b;
            }
            this.size = i2;
            return SelectKt.d();
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    protected boolean T(Receive receive) {
        ReentrantLock reentrantLock = this.f18996l;
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
        return this.size == 0;
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    public boolean Y() {
        ReentrantLock reentrantLock = this.f18996l;
        reentrantLock.lock();
        try {
            return super.Y();
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    protected void a0(boolean z) {
        Function1 function1 = this.f18978c;
        ReentrantLock reentrantLock = this.f18996l;
        reentrantLock.lock();
        try {
            int i2 = this.size;
            UndeliveredElementException undeliveredElementException = null;
            for (int i3 = 0; i3 < i2; i3++) {
                Object obj = this.f18997m[this.f18998n];
                if (function1 != null && obj != AbstractChannelKt.f18971a) {
                    undeliveredElementException = OnUndeliveredElementKt.c(function1, obj, undeliveredElementException);
                }
                Object[] objArr = this.f18997m;
                int i4 = this.f18998n;
                objArr[i4] = AbstractChannelKt.f18971a;
                this.f18998n = (i4 + 1) % objArr.length;
            }
            this.size = 0;
            Unit unit = Unit.f18288a;
            reentrantLock.unlock();
            super.a0(z);
            if (undeliveredElementException != null) {
                throw undeliveredElementException;
            }
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    protected Object e0() {
        ReentrantLock reentrantLock = this.f18996l;
        reentrantLock.lock();
        try {
            int i2 = this.size;
            if (i2 == 0) {
                Object k2 = k();
                if (k2 == null) {
                    k2 = AbstractChannelKt.f18974d;
                }
                return k2;
            }
            Object[] objArr = this.f18997m;
            int i3 = this.f18998n;
            Object obj = objArr[i3];
            Send send = null;
            objArr[i3] = null;
            this.size = i2 - 1;
            Object obj2 = AbstractChannelKt.f18974d;
            boolean z = false;
            if (i2 == this.f18994j) {
                Send send2 = null;
                while (true) {
                    Send K = K();
                    if (K == null) {
                        send = send2;
                        break;
                    }
                    Intrinsics.b(K);
                    if (K.g0(null) != null) {
                        obj2 = K.e0();
                        z = true;
                        send = K;
                        break;
                    }
                    K.h0();
                    send2 = K;
                }
            }
            if (obj2 != AbstractChannelKt.f18974d && !(obj2 instanceof Closed)) {
                this.size = i2;
                Object[] objArr2 = this.f18997m;
                objArr2[(this.f18998n + i2) % objArr2.length] = obj2;
            }
            this.f18998n = (this.f18998n + 1) % this.f18997m.length;
            Unit unit = Unit.f18288a;
            if (z) {
                Intrinsics.b(send);
                send.d0();
            }
            return obj;
        } finally {
            reentrantLock.unlock();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x009f A[Catch: all -> 0x0013, TRY_LEAVE, TryCatch #0 {all -> 0x0013, blocks: (B:3:0x0006, B:5:0x000a, B:7:0x0010, B:11:0x001a, B:13:0x002d, B:48:0x0037, B:28:0x0085, B:30:0x0089, B:32:0x008d, B:33:0x00af, B:38:0x0099, B:40:0x009f, B:15:0x0047, B:17:0x004b, B:20:0x004f, B:22:0x0055, B:25:0x0061, B:43:0x0069, B:44:0x0083), top: B:2:0x0006 }] */
    @Override // kotlinx.coroutines.channels.AbstractChannel
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected java.lang.Object f0(kotlinx.coroutines.selects.SelectInstance r9) {
        /*
            r8 = this;
            r0 = 1
            java.util.concurrent.locks.ReentrantLock r1 = r8.f18996l
            r1.lock()
            int r2 = r8.size     // Catch: java.lang.Throwable -> L13
            if (r2 != 0) goto L1a
            kotlinx.coroutines.channels.Closed r8 = r8.k()     // Catch: java.lang.Throwable -> L13
            if (r8 != 0) goto L16
            kotlinx.coroutines.internal.Symbol r8 = kotlinx.coroutines.channels.AbstractChannelKt.f18974d     // Catch: java.lang.Throwable -> L13
            goto L16
        L13:
            r8 = move-exception
            goto Lc8
        L16:
            r1.unlock()
            return r8
        L1a:
            java.lang.Object[] r3 = r8.f18997m     // Catch: java.lang.Throwable -> L13
            int r4 = r8.f18998n     // Catch: java.lang.Throwable -> L13
            r5 = r3[r4]     // Catch: java.lang.Throwable -> L13
            r6 = 0
            r3[r4] = r6     // Catch: java.lang.Throwable -> L13
            int r3 = r2 + (-1)
            r8.size = r3     // Catch: java.lang.Throwable -> L13
            kotlinx.coroutines.internal.Symbol r3 = kotlinx.coroutines.channels.AbstractChannelKt.f18974d     // Catch: java.lang.Throwable -> L13
            int r4 = r8.f18994j     // Catch: java.lang.Throwable -> L13
            if (r2 != r4) goto L84
        L2d:
            kotlinx.coroutines.channels.AbstractChannel$TryPollDesc r4 = r8.R()     // Catch: java.lang.Throwable -> L13
            java.lang.Object r7 = r9.C(r4)     // Catch: java.lang.Throwable -> L13
            if (r7 != 0) goto L47
            java.lang.Object r6 = r4.o()     // Catch: java.lang.Throwable -> L13
            kotlin.jvm.internal.Intrinsics.b(r6)     // Catch: java.lang.Throwable -> L13
            r3 = r6
            kotlinx.coroutines.channels.Send r3 = (kotlinx.coroutines.channels.Send) r3     // Catch: java.lang.Throwable -> L13
            java.lang.Object r3 = r3.e0()     // Catch: java.lang.Throwable -> L13
            r4 = r0
            goto L85
        L47:
            kotlinx.coroutines.internal.Symbol r4 = kotlinx.coroutines.channels.AbstractChannelKt.f18974d     // Catch: java.lang.Throwable -> L13
            if (r7 == r4) goto L84
            java.lang.Object r4 = kotlinx.coroutines.internal.AtomicKt.f19340b     // Catch: java.lang.Throwable -> L13
            if (r7 == r4) goto L2d
            java.lang.Object r3 = kotlinx.coroutines.selects.SelectKt.d()     // Catch: java.lang.Throwable -> L13
            if (r7 != r3) goto L61
            r8.size = r2     // Catch: java.lang.Throwable -> L13
            java.lang.Object[] r9 = r8.f18997m     // Catch: java.lang.Throwable -> L13
            int r8 = r8.f18998n     // Catch: java.lang.Throwable -> L13
            r9[r8] = r5     // Catch: java.lang.Throwable -> L13
            r1.unlock()
            return r7
        L61:
            boolean r3 = r7 instanceof kotlinx.coroutines.channels.Closed     // Catch: java.lang.Throwable -> L13
            if (r3 == 0) goto L69
            r4 = r0
            r3 = r7
            r6 = r3
            goto L85
        L69:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L13
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L13
            r9.<init>()     // Catch: java.lang.Throwable -> L13
            java.lang.String r0 = "performAtomicTrySelect(describeTryOffer) returned "
            r9.append(r0)     // Catch: java.lang.Throwable -> L13
            r9.append(r7)     // Catch: java.lang.Throwable -> L13
            java.lang.String r9 = r9.toString()     // Catch: java.lang.Throwable -> L13
            java.lang.String r9 = r9.toString()     // Catch: java.lang.Throwable -> L13
            r8.<init>(r9)     // Catch: java.lang.Throwable -> L13
            throw r8     // Catch: java.lang.Throwable -> L13
        L84:
            r4 = 0
        L85:
            kotlinx.coroutines.internal.Symbol r7 = kotlinx.coroutines.channels.AbstractChannelKt.f18974d     // Catch: java.lang.Throwable -> L13
            if (r3 == r7) goto L99
            boolean r7 = r3 instanceof kotlinx.coroutines.channels.Closed     // Catch: java.lang.Throwable -> L13
            if (r7 != 0) goto L99
            r8.size = r2     // Catch: java.lang.Throwable -> L13
            java.lang.Object[] r9 = r8.f18997m     // Catch: java.lang.Throwable -> L13
            int r7 = r8.f18998n     // Catch: java.lang.Throwable -> L13
            int r7 = r7 + r2
            int r2 = r9.length     // Catch: java.lang.Throwable -> L13
            int r7 = r7 % r2
            r9[r7] = r3     // Catch: java.lang.Throwable -> L13
            goto Laf
        L99:
            boolean r9 = r9.w()     // Catch: java.lang.Throwable -> L13
            if (r9 != 0) goto Laf
            r8.size = r2     // Catch: java.lang.Throwable -> L13
            java.lang.Object[] r9 = r8.f18997m     // Catch: java.lang.Throwable -> L13
            int r8 = r8.f18998n     // Catch: java.lang.Throwable -> L13
            r9[r8] = r5     // Catch: java.lang.Throwable -> L13
            java.lang.Object r8 = kotlinx.coroutines.selects.SelectKt.d()     // Catch: java.lang.Throwable -> L13
            r1.unlock()
            return r8
        Laf:
            int r9 = r8.f18998n     // Catch: java.lang.Throwable -> L13
            int r9 = r9 + r0
            java.lang.Object[] r0 = r8.f18997m     // Catch: java.lang.Throwable -> L13
            int r0 = r0.length     // Catch: java.lang.Throwable -> L13
            int r9 = r9 % r0
            r8.f18998n = r9     // Catch: java.lang.Throwable -> L13
            kotlin.Unit r8 = kotlin.Unit.f18288a     // Catch: java.lang.Throwable -> L13
            r1.unlock()
            if (r4 == 0) goto Lc7
            kotlin.jvm.internal.Intrinsics.b(r6)
            kotlinx.coroutines.channels.Send r6 = (kotlinx.coroutines.channels.Send) r6
            r6.d0()
        Lc7:
            return r5
        Lc8:
            r1.unlock()
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ArrayChannel.f0(kotlinx.coroutines.selects.SelectInstance):java.lang.Object");
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    protected Object h(Send send) {
        ReentrantLock reentrantLock = this.f18996l;
        reentrantLock.lock();
        try {
            return super.h(send);
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    protected String i() {
        return "(buffer:capacity=" + this.f18994j + ",size=" + this.size + ')';
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    protected final boolean v() {
        return false;
    }
}
