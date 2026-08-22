package kotlinx.coroutines.sync;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CancellableContinuationKt;
import kotlinx.coroutines.internal.ConcurrentLinkedListKt;
import kotlinx.coroutines.internal.ConcurrentLinkedListNode;
import kotlinx.coroutines.internal.Segment;
import kotlinx.coroutines.internal.SegmentOrClosed;
import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.NotNull;

@Metadata
/* loaded from: classes2.dex */
final class SemaphoreImpl implements Semaphore {

    /* renamed from: c, reason: collision with root package name */
    private static final /* synthetic */ AtomicReferenceFieldUpdater f19519c = AtomicReferenceFieldUpdater.newUpdater(SemaphoreImpl.class, Object.class, "head");

    /* renamed from: d, reason: collision with root package name */
    private static final /* synthetic */ AtomicLongFieldUpdater f19520d = AtomicLongFieldUpdater.newUpdater(SemaphoreImpl.class, "deqIdx");

    /* renamed from: e, reason: collision with root package name */
    private static final /* synthetic */ AtomicReferenceFieldUpdater f19521e = AtomicReferenceFieldUpdater.newUpdater(SemaphoreImpl.class, Object.class, "tail");

    /* renamed from: f, reason: collision with root package name */
    private static final /* synthetic */ AtomicLongFieldUpdater f19522f = AtomicLongFieldUpdater.newUpdater(SemaphoreImpl.class, "enqIdx");

    /* renamed from: g, reason: collision with root package name */
    static final /* synthetic */ AtomicIntegerFieldUpdater f19523g = AtomicIntegerFieldUpdater.newUpdater(SemaphoreImpl.class, "_availablePermits");

    @NotNull
    volatile /* synthetic */ int _availablePermits;

    /* renamed from: a, reason: collision with root package name */
    private final int f19524a;

    /* renamed from: b, reason: collision with root package name */
    private final Function1 f19525b;

    @NotNull
    private volatile /* synthetic */ long deqIdx = 0;

    @NotNull
    private volatile /* synthetic */ long enqIdx = 0;

    @NotNull
    private volatile /* synthetic */ Object head;

    @NotNull
    private volatile /* synthetic */ Object tail;

    public SemaphoreImpl(int i2, int i3) {
        this.f19524a = i2;
        if (i2 <= 0) {
            throw new IllegalArgumentException(("Semaphore should have at least 1 permit, but had " + i2).toString());
        }
        if (i3 < 0 || i3 > i2) {
            throw new IllegalArgumentException(("The number of acquired permits should be in 0.." + i2).toString());
        }
        SemaphoreSegment semaphoreSegment = new SemaphoreSegment(0L, null, 2);
        this.head = semaphoreSegment;
        this.tail = semaphoreSegment;
        this._availablePermits = i2 - i3;
        this.f19525b = new Function1<Throwable, Unit>() { // from class: kotlinx.coroutines.sync.SemaphoreImpl$onCancellationRelease$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object c(Object obj) {
                d((Throwable) obj);
                return Unit.f18288a;
            }

            public final void d(Throwable th) {
                SemaphoreImpl.this.release();
            }
        };
    }

    private final Object d(Continuation continuation) {
        Continuation c2;
        Object d2;
        Object d3;
        c2 = IntrinsicsKt__IntrinsicsJvmKt.c(continuation);
        CancellableContinuationImpl b2 = CancellableContinuationKt.b(c2);
        while (true) {
            if (e(b2)) {
                break;
            }
            if (f19523g.getAndDecrement(this) > 0) {
                b2.r(Unit.f18288a, this.f19525b);
                break;
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

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean e(CancellableContinuation cancellableContinuation) {
        int i2;
        Symbol symbol;
        Symbol symbol2;
        Object a2;
        int i3;
        Symbol symbol3;
        Symbol symbol4;
        SemaphoreSegment semaphoreSegment = (SemaphoreSegment) this.tail;
        long andIncrement = f19522f.getAndIncrement(this);
        i2 = SemaphoreKt.f19531f;
        long j2 = andIncrement / i2;
        loop0: while (true) {
            SemaphoreSegment semaphoreSegment2 = semaphoreSegment;
            while (true) {
                if (semaphoreSegment2.m() >= j2 && !semaphoreSegment2.g()) {
                    a2 = SegmentOrClosed.a(semaphoreSegment2);
                    break;
                }
                Object e2 = semaphoreSegment2.e();
                symbol = ConcurrentLinkedListKt.f19345a;
                if (e2 == symbol) {
                    symbol2 = ConcurrentLinkedListKt.f19345a;
                    a2 = SegmentOrClosed.a(symbol2);
                    break;
                }
                ConcurrentLinkedListNode concurrentLinkedListNode = (Segment) ((ConcurrentLinkedListNode) e2);
                if (concurrentLinkedListNode == null) {
                    concurrentLinkedListNode = SemaphoreKt.j(semaphoreSegment2.m() + 1, semaphoreSegment2);
                    if (semaphoreSegment2.k(concurrentLinkedListNode)) {
                        if (semaphoreSegment2.g()) {
                            semaphoreSegment2.j();
                        }
                    }
                }
                semaphoreSegment2 = concurrentLinkedListNode;
            }
            if (!SegmentOrClosed.e(a2)) {
                Segment c2 = SegmentOrClosed.c(a2);
                while (true) {
                    Segment segment = (Segment) this.tail;
                    if (segment.m() >= c2.m()) {
                        break loop0;
                    }
                    if (!c2.p()) {
                        break;
                    }
                    if (f19521e.compareAndSet(this, segment, c2)) {
                        if (segment.l()) {
                            segment.j();
                        }
                    } else if (c2.l()) {
                        c2.j();
                    }
                }
            } else {
                break;
            }
        }
        SemaphoreSegment semaphoreSegment3 = (SemaphoreSegment) SegmentOrClosed.c(a2);
        i3 = SemaphoreKt.f19531f;
        int i4 = (int) (andIncrement % i3);
        if (semaphoreSegment3.f19532e.compareAndSet(i4, null, cancellableContinuation)) {
            cancellableContinuation.m(new CancelSemaphoreAcquisitionHandler(semaphoreSegment3, i4));
            return true;
        }
        symbol3 = SemaphoreKt.f19527b;
        symbol4 = SemaphoreKt.f19528c;
        if (!semaphoreSegment3.f19532e.compareAndSet(i4, symbol3, symbol4)) {
            return false;
        }
        cancellableContinuation.r(Unit.f18288a, this.f19525b);
        return true;
    }

    private final boolean f(CancellableContinuation cancellableContinuation) {
        Object D = cancellableContinuation.D(Unit.f18288a, null, this.f19525b);
        if (D == null) {
            return false;
        }
        cancellableContinuation.O(D);
        return true;
    }

    private final boolean g() {
        int i2;
        Symbol symbol;
        Symbol symbol2;
        Object a2;
        int i3;
        Symbol symbol3;
        Symbol symbol4;
        int i4;
        Symbol symbol5;
        Symbol symbol6;
        Symbol symbol7;
        SemaphoreSegment semaphoreSegment = (SemaphoreSegment) this.head;
        long andIncrement = f19520d.getAndIncrement(this);
        i2 = SemaphoreKt.f19531f;
        long j2 = andIncrement / i2;
        loop0: while (true) {
            SemaphoreSegment semaphoreSegment2 = semaphoreSegment;
            while (true) {
                if (semaphoreSegment2.m() >= j2 && !semaphoreSegment2.g()) {
                    a2 = SegmentOrClosed.a(semaphoreSegment2);
                    break;
                }
                Object e2 = semaphoreSegment2.e();
                symbol = ConcurrentLinkedListKt.f19345a;
                if (e2 == symbol) {
                    symbol2 = ConcurrentLinkedListKt.f19345a;
                    a2 = SegmentOrClosed.a(symbol2);
                    break;
                }
                ConcurrentLinkedListNode concurrentLinkedListNode = (Segment) ((ConcurrentLinkedListNode) e2);
                if (concurrentLinkedListNode == null) {
                    concurrentLinkedListNode = SemaphoreKt.j(semaphoreSegment2.m() + 1, semaphoreSegment2);
                    if (semaphoreSegment2.k(concurrentLinkedListNode)) {
                        if (semaphoreSegment2.g()) {
                            semaphoreSegment2.j();
                        }
                    }
                }
                semaphoreSegment2 = concurrentLinkedListNode;
            }
            if (SegmentOrClosed.e(a2)) {
                break;
            }
            Segment c2 = SegmentOrClosed.c(a2);
            while (true) {
                Segment segment = (Segment) this.head;
                if (segment.m() >= c2.m()) {
                    break loop0;
                }
                if (!c2.p()) {
                    break;
                }
                if (f19519c.compareAndSet(this, segment, c2)) {
                    if (segment.l()) {
                        segment.j();
                    }
                } else if (c2.l()) {
                    c2.j();
                }
            }
        }
        SemaphoreSegment semaphoreSegment3 = (SemaphoreSegment) SegmentOrClosed.c(a2);
        semaphoreSegment3.b();
        if (semaphoreSegment3.m() > j2) {
            return false;
        }
        i3 = SemaphoreKt.f19531f;
        int i5 = (int) (andIncrement % i3);
        symbol3 = SemaphoreKt.f19527b;
        Object andSet = semaphoreSegment3.f19532e.getAndSet(i5, symbol3);
        if (andSet != null) {
            symbol4 = SemaphoreKt.f19530e;
            if (andSet == symbol4) {
                return false;
            }
            return f((CancellableContinuation) andSet);
        }
        i4 = SemaphoreKt.f19526a;
        for (int i6 = 0; i6 < i4; i6++) {
            Object obj = semaphoreSegment3.f19532e.get(i5);
            symbol7 = SemaphoreKt.f19528c;
            if (obj == symbol7) {
                return true;
            }
        }
        symbol5 = SemaphoreKt.f19527b;
        symbol6 = SemaphoreKt.f19529d;
        return !semaphoreSegment3.f19532e.compareAndSet(i5, symbol5, symbol6);
    }

    @Override // kotlinx.coroutines.sync.Semaphore
    public Object a(Continuation continuation) {
        Object d2;
        if (f19523g.getAndDecrement(this) > 0) {
            return Unit.f18288a;
        }
        Object d3 = d(continuation);
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        return d3 == d2 ? d3 : Unit.f18288a;
    }

    @Override // kotlinx.coroutines.sync.Semaphore
    public void release() {
        while (true) {
            int i2 = this._availablePermits;
            if (i2 >= this.f19524a) {
                throw new IllegalStateException(("The number of released permits cannot be greater than " + this.f19524a).toString());
            }
            if (f19523g.compareAndSet(this, i2, i2 + 1) && (i2 >= 0 || g())) {
                return;
            }
        }
    }
}
