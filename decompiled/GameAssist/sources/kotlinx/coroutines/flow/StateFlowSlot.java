package kotlinx.coroutines.flow;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowKt;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot;
import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.NotNull;

@Metadata
/* loaded from: classes2.dex */
final class StateFlowSlot extends AbstractSharedFlowSlot<StateFlowImpl<?>> {

    /* renamed from: a, reason: collision with root package name */
    static final /* synthetic */ AtomicReferenceFieldUpdater f19280a = AtomicReferenceFieldUpdater.newUpdater(StateFlowSlot.class, Object.class, "_state");

    @NotNull
    volatile /* synthetic */ Object _state = null;

    @Override // kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public boolean a(StateFlowImpl stateFlowImpl) {
        Symbol symbol;
        if (this._state != null) {
            return false;
        }
        symbol = StateFlowKt.f19278a;
        this._state = symbol;
        return true;
    }

    public final Object d(Continuation continuation) {
        Continuation c2;
        Symbol symbol;
        Object d2;
        Object d3;
        c2 = IntrinsicsKt__IntrinsicsJvmKt.c(continuation);
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(c2, 1);
        cancellableContinuationImpl.z();
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f19280a;
        symbol = StateFlowKt.f19278a;
        if (!atomicReferenceFieldUpdater.compareAndSet(this, symbol, cancellableContinuationImpl)) {
            Result.Companion companion = Result.Companion;
            cancellableContinuationImpl.g(Result.b(Unit.f18288a));
        }
        Object w = cancellableContinuationImpl.w();
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        if (w == d2) {
            DebugProbesKt.c(continuation);
        }
        d3 = IntrinsicsKt__IntrinsicsKt.d();
        return w == d3 ? w : Unit.f18288a;
    }

    @Override // kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public Continuation[] b(StateFlowImpl stateFlowImpl) {
        this._state = null;
        return AbstractSharedFlowKt.f19290a;
    }

    public final void f() {
        Symbol symbol;
        Symbol symbol2;
        Symbol symbol3;
        Symbol symbol4;
        while (true) {
            Object obj = this._state;
            if (obj == null) {
                return;
            }
            symbol = StateFlowKt.f19279b;
            if (obj == symbol) {
                return;
            }
            symbol2 = StateFlowKt.f19278a;
            if (obj == symbol2) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f19280a;
                symbol3 = StateFlowKt.f19279b;
                if (atomicReferenceFieldUpdater.compareAndSet(this, obj, symbol3)) {
                    return;
                }
            } else {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = f19280a;
                symbol4 = StateFlowKt.f19278a;
                if (atomicReferenceFieldUpdater2.compareAndSet(this, obj, symbol4)) {
                    Result.Companion companion = Result.Companion;
                    ((CancellableContinuationImpl) obj).g(Result.b(Unit.f18288a));
                    return;
                }
            }
        }
    }

    public final boolean g() {
        Symbol symbol;
        Symbol symbol2;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f19280a;
        symbol = StateFlowKt.f19278a;
        Object andSet = atomicReferenceFieldUpdater.getAndSet(this, symbol);
        Intrinsics.b(andSet);
        symbol2 = StateFlowKt.f19279b;
        return andSet == symbol2;
    }
}
