package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CancellableContinuationKt;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.internal.AbstractSharedFlow;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowKt;
import kotlinx.coroutines.flow.internal.FusibleFlow;

@Metadata
/* loaded from: classes2.dex */
public class SharedFlowImpl<T> extends AbstractSharedFlow<SharedFlowSlot> implements MutableSharedFlow<T>, CancellableFlow<T>, FusibleFlow<T> {

    /* renamed from: k, reason: collision with root package name */
    private final int f19253k;

    /* renamed from: l, reason: collision with root package name */
    private final int f19254l;

    /* renamed from: m, reason: collision with root package name */
    private final BufferOverflow f19255m;

    /* renamed from: n, reason: collision with root package name */
    private Object[] f19256n;

    /* renamed from: o, reason: collision with root package name */
    private long f19257o;

    /* renamed from: p, reason: collision with root package name */
    private long f19258p;

    /* renamed from: q, reason: collision with root package name */
    private int f19259q;

    /* renamed from: r, reason: collision with root package name */
    private int f19260r;

    @Metadata
    private static final class Emitter implements DisposableHandle {

        /* renamed from: c, reason: collision with root package name */
        public final SharedFlowImpl f19261c;

        /* renamed from: h, reason: collision with root package name */
        public long f19262h;

        /* renamed from: i, reason: collision with root package name */
        public final Object f19263i;

        /* renamed from: j, reason: collision with root package name */
        public final Continuation f19264j;

        public Emitter(SharedFlowImpl sharedFlowImpl, long j2, Object obj, Continuation continuation) {
            this.f19261c = sharedFlowImpl;
            this.f19262h = j2;
            this.f19263i = obj;
            this.f19264j = continuation;
        }

        @Override // kotlinx.coroutines.DisposableHandle
        public void dispose() {
            this.f19261c.z(this);
        }
    }

    @Metadata
    public /* synthetic */ class WhenMappings {

        /* renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f19265a;

        static {
            int[] iArr = new int[BufferOverflow.values().length];
            iArr[BufferOverflow.SUSPEND.ordinal()] = 1;
            iArr[BufferOverflow.DROP_LATEST.ordinal()] = 2;
            iArr[BufferOverflow.DROP_OLDEST.ordinal()] = 3;
            f19265a = iArr;
        }
    }

    public SharedFlowImpl(int i2, int i3, BufferOverflow bufferOverflow) {
        this.f19253k = i2;
        this.f19254l = i3;
        this.f19255m = bufferOverflow;
    }

    private final void A() {
        Object d2;
        if (this.f19254l != 0 || this.f19260r > 1) {
            Object[] objArr = this.f19256n;
            Intrinsics.b(objArr);
            while (this.f19260r > 0) {
                d2 = SharedFlowKt.d(objArr, (L() + Q()) - 1);
                if (d2 != SharedFlowKt.f19266a) {
                    return;
                }
                this.f19260r--;
                SharedFlowKt.e(objArr, L() + Q(), null);
            }
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(10:0|1|(2:3|(7:5|6|(3:(6:(1:(1:11)(2:41|42))(1:43)|12|13|14|15|(3:16|(3:28|29|(2:31|32)(1:33))(4:18|(1:20)|21|(2:23|24)(1:26))|27))(4:44|45|46|47)|37|38)(5:53|54|55|(2:57|(1:59))|61)|48|49|15|(3:16|(0)(0)|27)))|64|6|(0)(0)|48|49|15|(3:16|(0)(0)|27)) */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00c2, code lost:
    
        r10 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00c3, code lost:
    
        r5 = r8;
        r8 = r10;
     */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00c6 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00b1 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static /* synthetic */ java.lang.Object B(kotlinx.coroutines.flow.SharedFlowImpl r8, kotlinx.coroutines.flow.FlowCollector r9, kotlin.coroutines.Continuation r10) {
        /*
            Method dump skipped, instructions count: 224
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.SharedFlowImpl.B(kotlinx.coroutines.flow.SharedFlowImpl, kotlinx.coroutines.flow.FlowCollector, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:3:0x0006, code lost:
    
        r0 = ((kotlinx.coroutines.flow.internal.AbstractSharedFlow) r8).f19286c;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void C(long r9) {
        /*
            r8 = this;
            int r0 = kotlinx.coroutines.flow.internal.AbstractSharedFlow.c(r8)
            if (r0 == 0) goto L27
            kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot[] r0 = kotlinx.coroutines.flow.internal.AbstractSharedFlow.d(r8)
            if (r0 == 0) goto L27
            int r1 = r0.length
            r2 = 0
        Le:
            if (r2 >= r1) goto L27
            r3 = r0[r2]
            if (r3 == 0) goto L24
            kotlinx.coroutines.flow.SharedFlowSlot r3 = (kotlinx.coroutines.flow.SharedFlowSlot) r3
            long r4 = r3.f19267a
            r6 = 0
            int r6 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r6 < 0) goto L24
            int r4 = (r4 > r9 ? 1 : (r4 == r9 ? 0 : -1))
            if (r4 >= 0) goto L24
            r3.f19267a = r9
        L24:
            int r2 = r2 + 1
            goto Le
        L27:
            r8.f19258p = r9
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.SharedFlowImpl.C(long):void");
    }

    private final void F() {
        Object[] objArr = this.f19256n;
        Intrinsics.b(objArr);
        SharedFlowKt.e(objArr, L(), null);
        this.f19259q--;
        long L = L() + 1;
        if (this.f19257o < L) {
            this.f19257o = L;
        }
        if (this.f19258p < L) {
            C(L);
        }
    }

    static /* synthetic */ Object G(SharedFlowImpl sharedFlowImpl, Object obj, Continuation continuation) {
        Object d2;
        if (sharedFlowImpl.e(obj)) {
            return Unit.f18288a;
        }
        Object H = sharedFlowImpl.H(obj, continuation);
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        return H == d2 ? H : Unit.f18288a;
    }

    private final Object H(Object obj, Continuation continuation) {
        Continuation c2;
        Continuation[] continuationArr;
        Emitter emitter;
        Object d2;
        Object d3;
        c2 = IntrinsicsKt__IntrinsicsJvmKt.c(continuation);
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(c2, 1);
        cancellableContinuationImpl.z();
        Continuation[] continuationArr2 = AbstractSharedFlowKt.f19290a;
        synchronized (this) {
            try {
                if (S(obj)) {
                    Result.Companion companion = Result.Companion;
                    cancellableContinuationImpl.g(Result.b(Unit.f18288a));
                    continuationArr = J(continuationArr2);
                    emitter = null;
                } else {
                    Emitter emitter2 = new Emitter(this, Q() + L(), obj, cancellableContinuationImpl);
                    I(emitter2);
                    this.f19260r++;
                    if (this.f19254l == 0) {
                        continuationArr2 = J(continuationArr2);
                    }
                    continuationArr = continuationArr2;
                    emitter = emitter2;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        if (emitter != null) {
            CancellableContinuationKt.a(cancellableContinuationImpl, emitter);
        }
        for (Continuation continuation2 : continuationArr) {
            if (continuation2 != null) {
                Result.Companion companion2 = Result.Companion;
                continuation2.g(Result.b(Unit.f18288a));
            }
        }
        Object w = cancellableContinuationImpl.w();
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        if (w == d2) {
            DebugProbesKt.c(continuation);
        }
        d3 = IntrinsicsKt__IntrinsicsKt.d();
        return w == d3 ? w : Unit.f18288a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void I(Object obj) {
        int Q = Q();
        Object[] objArr = this.f19256n;
        if (objArr == null) {
            objArr = R(null, 0, 2);
        } else if (Q >= objArr.length) {
            objArr = R(objArr, Q, objArr.length * 2);
        }
        SharedFlowKt.e(objArr, L() + Q, obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:3:0x0007, code lost:
    
        r1 = ((kotlinx.coroutines.flow.internal.AbstractSharedFlow) r10).f19286c;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v6, types: [java.lang.Object, java.lang.Object[]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final kotlin.coroutines.Continuation[] J(kotlin.coroutines.Continuation[] r11) {
        /*
            r10 = this;
            int r0 = r11.length
            int r1 = kotlinx.coroutines.flow.internal.AbstractSharedFlow.c(r10)
            if (r1 == 0) goto L47
            kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot[] r1 = kotlinx.coroutines.flow.internal.AbstractSharedFlow.d(r10)
            if (r1 == 0) goto L47
            int r2 = r1.length
            r3 = 0
        Lf:
            if (r3 >= r2) goto L47
            r4 = r1[r3]
            if (r4 == 0) goto L44
            kotlinx.coroutines.flow.SharedFlowSlot r4 = (kotlinx.coroutines.flow.SharedFlowSlot) r4
            kotlin.coroutines.Continuation r5 = r4.f19268b
            if (r5 != 0) goto L1c
            goto L44
        L1c:
            long r6 = r10.U(r4)
            r8 = 0
            int r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r6 < 0) goto L44
            int r6 = r11.length
            if (r0 < r6) goto L39
            int r6 = r11.length
            r7 = 2
            int r6 = r6 * r7
            int r6 = java.lang.Math.max(r7, r6)
            java.lang.Object[] r11 = java.util.Arrays.copyOf(r11, r6)
            java.lang.String r6 = "copyOf(this, newSize)"
            kotlin.jvm.internal.Intrinsics.d(r11, r6)
        L39:
            r6 = r11
            kotlin.coroutines.Continuation[] r6 = (kotlin.coroutines.Continuation[]) r6
            int r7 = r0 + 1
            r6[r0] = r5
            r0 = 0
            r4.f19268b = r0
            r0 = r7
        L44:
            int r3 = r3 + 1
            goto Lf
        L47:
            kotlin.coroutines.Continuation[] r11 = (kotlin.coroutines.Continuation[]) r11
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.SharedFlowImpl.J(kotlin.coroutines.Continuation[]):kotlin.coroutines.Continuation[]");
    }

    private final long K() {
        return L() + this.f19259q;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long L() {
        return Math.min(this.f19258p, this.f19257o);
    }

    private final Object N(long j2) {
        Object d2;
        Object[] objArr = this.f19256n;
        Intrinsics.b(objArr);
        d2 = SharedFlowKt.d(objArr, j2);
        return d2 instanceof Emitter ? ((Emitter) d2).f19263i : d2;
    }

    private final long O() {
        return L() + this.f19259q + this.f19260r;
    }

    private final int P() {
        return (int) ((L() + this.f19259q) - this.f19257o);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int Q() {
        return this.f19259q + this.f19260r;
    }

    private final Object[] R(Object[] objArr, int i2, int i3) {
        Object d2;
        if (i3 <= 0) {
            throw new IllegalStateException("Buffer size overflow".toString());
        }
        Object[] objArr2 = new Object[i3];
        this.f19256n = objArr2;
        if (objArr == null) {
            return objArr2;
        }
        long L = L();
        for (int i4 = 0; i4 < i2; i4++) {
            long j2 = i4 + L;
            d2 = SharedFlowKt.d(objArr, j2);
            SharedFlowKt.e(objArr2, j2, d2);
        }
        return objArr2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean S(Object obj) {
        if (m() == 0) {
            return T(obj);
        }
        if (this.f19259q >= this.f19254l && this.f19258p <= this.f19257o) {
            int i2 = WhenMappings.f19265a[this.f19255m.ordinal()];
            if (i2 == 1) {
                return false;
            }
            if (i2 == 2) {
                return true;
            }
        }
        I(obj);
        int i3 = this.f19259q + 1;
        this.f19259q = i3;
        if (i3 > this.f19254l) {
            F();
        }
        if (P() > this.f19253k) {
            W(this.f19257o + 1, this.f19258p, K(), O());
        }
        return true;
    }

    private final boolean T(Object obj) {
        if (this.f19253k == 0) {
            return true;
        }
        I(obj);
        int i2 = this.f19259q + 1;
        this.f19259q = i2;
        if (i2 > this.f19253k) {
            F();
        }
        this.f19258p = L() + this.f19259q;
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long U(SharedFlowSlot sharedFlowSlot) {
        long j2 = sharedFlowSlot.f19267a;
        if (j2 < K()) {
            return j2;
        }
        if (this.f19254l <= 0 && j2 <= L() && this.f19260r != 0) {
            return j2;
        }
        return -1L;
    }

    private final Object V(SharedFlowSlot sharedFlowSlot) {
        Object obj;
        Continuation[] continuationArr = AbstractSharedFlowKt.f19290a;
        synchronized (this) {
            try {
                long U = U(sharedFlowSlot);
                if (U < 0) {
                    obj = SharedFlowKt.f19266a;
                } else {
                    long j2 = sharedFlowSlot.f19267a;
                    Object N = N(U);
                    sharedFlowSlot.f19267a = U + 1;
                    continuationArr = X(j2);
                    obj = N;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        for (Continuation continuation : continuationArr) {
            if (continuation != null) {
                Result.Companion companion = Result.Companion;
                continuation.g(Result.b(Unit.f18288a));
            }
        }
        return obj;
    }

    private final void W(long j2, long j3, long j4, long j5) {
        long min = Math.min(j3, j2);
        for (long L = L(); L < min; L++) {
            Object[] objArr = this.f19256n;
            Intrinsics.b(objArr);
            SharedFlowKt.e(objArr, L, null);
        }
        this.f19257o = j2;
        this.f19258p = j3;
        this.f19259q = (int) (j4 - min);
        this.f19260r = (int) (j5 - j4);
    }

    private final Object y(SharedFlowSlot sharedFlowSlot, Continuation continuation) {
        Continuation c2;
        Object d2;
        Object d3;
        c2 = IntrinsicsKt__IntrinsicsJvmKt.c(continuation);
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(c2, 1);
        cancellableContinuationImpl.z();
        synchronized (this) {
            try {
                if (U(sharedFlowSlot) < 0) {
                    sharedFlowSlot.f19268b = cancellableContinuationImpl;
                } else {
                    Result.Companion companion = Result.Companion;
                    cancellableContinuationImpl.g(Result.b(Unit.f18288a));
                }
                Unit unit = Unit.f18288a;
            } catch (Throwable th) {
                throw th;
            }
        }
        Object w = cancellableContinuationImpl.w();
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        if (w == d2) {
            DebugProbesKt.c(continuation);
        }
        d3 = IntrinsicsKt__IntrinsicsKt.d();
        return w == d3 ? w : Unit.f18288a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void z(Emitter emitter) {
        Object d2;
        synchronized (this) {
            if (emitter.f19262h < L()) {
                return;
            }
            Object[] objArr = this.f19256n;
            Intrinsics.b(objArr);
            d2 = SharedFlowKt.d(objArr, emitter.f19262h);
            if (d2 != emitter) {
                return;
            }
            SharedFlowKt.e(objArr, emitter.f19262h, SharedFlowKt.f19266a);
            A();
            Unit unit = Unit.f18288a;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.flow.internal.AbstractSharedFlow
    /* renamed from: D, reason: merged with bridge method [inline-methods] */
    public SharedFlowSlot g() {
        return new SharedFlowSlot();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.flow.internal.AbstractSharedFlow
    /* renamed from: E, reason: merged with bridge method [inline-methods] */
    public SharedFlowSlot[] j(int i2) {
        return new SharedFlowSlot[i2];
    }

    protected final Object M() {
        Object d2;
        Object[] objArr = this.f19256n;
        Intrinsics.b(objArr);
        d2 = SharedFlowKt.d(objArr, (this.f19257o + P()) - 1);
        return d2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0024, code lost:
    
        r4 = ((kotlinx.coroutines.flow.internal.AbstractSharedFlow) r21).f19286c;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final kotlin.coroutines.Continuation[] X(long r22) {
        /*
            Method dump skipped, instructions count: 274
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.SharedFlowImpl.X(long):kotlin.coroutines.Continuation[]");
    }

    public final long Y() {
        long j2 = this.f19257o;
        if (j2 < this.f19258p) {
            this.f19258p = j2;
        }
        return j2;
    }

    @Override // kotlinx.coroutines.flow.SharedFlow, kotlinx.coroutines.flow.Flow
    public Object a(FlowCollector flowCollector, Continuation continuation) {
        return B(this, flowCollector, continuation);
    }

    @Override // kotlinx.coroutines.flow.internal.FusibleFlow
    public Flow b(CoroutineContext coroutineContext, int i2, BufferOverflow bufferOverflow) {
        return SharedFlowKt.c(this, coroutineContext, i2, bufferOverflow);
    }

    @Override // kotlinx.coroutines.flow.MutableSharedFlow
    public boolean e(Object obj) {
        int i2;
        boolean z;
        Continuation[] continuationArr = AbstractSharedFlowKt.f19290a;
        synchronized (this) {
            if (S(obj)) {
                continuationArr = J(continuationArr);
                z = true;
            } else {
                z = false;
            }
        }
        for (Continuation continuation : continuationArr) {
            if (continuation != null) {
                Result.Companion companion = Result.Companion;
                continuation.g(Result.b(Unit.f18288a));
            }
        }
        return z;
    }

    @Override // kotlinx.coroutines.flow.MutableSharedFlow
    public void i() {
        synchronized (this) {
            W(K(), this.f19258p, K(), O());
            Unit unit = Unit.f18288a;
        }
    }

    @Override // kotlinx.coroutines.flow.FlowCollector
    public Object k(Object obj, Continuation continuation) {
        return G(this, obj, continuation);
    }
}
