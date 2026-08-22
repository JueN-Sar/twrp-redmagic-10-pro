package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.internal.AbstractSharedFlow;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot;
import kotlinx.coroutines.flow.internal.FusibleFlow;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;
import org.jetbrains.annotations.NotNull;

@Metadata
/* loaded from: classes2.dex */
final class StateFlowImpl<T> extends AbstractSharedFlow<StateFlowSlot> implements MutableStateFlow<T>, CancellableFlow<T>, FusibleFlow<T> {

    @NotNull
    private volatile /* synthetic */ Object _state;

    /* renamed from: k, reason: collision with root package name */
    private int f19277k;

    public StateFlowImpl(Object obj) {
        this._state = obj;
    }

    private final boolean q(Object obj, Object obj2) {
        int i2;
        AbstractSharedFlowSlot[] n2;
        n();
        synchronized (this) {
            Object obj3 = this._state;
            if (obj != null && !Intrinsics.a(obj3, obj)) {
                return false;
            }
            if (Intrinsics.a(obj3, obj2)) {
                return true;
            }
            this._state = obj2;
            int i3 = this.f19277k;
            if ((i3 & 1) != 0) {
                this.f19277k = i3 + 2;
                return true;
            }
            int i4 = i3 + 1;
            this.f19277k = i4;
            AbstractSharedFlowSlot[] n3 = n();
            Unit unit = Unit.f18288a;
            while (true) {
                StateFlowSlot[] stateFlowSlotArr = (StateFlowSlot[]) n3;
                if (stateFlowSlotArr != null) {
                    for (StateFlowSlot stateFlowSlot : stateFlowSlotArr) {
                        if (stateFlowSlot != null) {
                            stateFlowSlot.f();
                        }
                    }
                }
                synchronized (this) {
                    i2 = this.f19277k;
                    if (i2 == i4) {
                        this.f19277k = i4 + 1;
                        return true;
                    }
                    n2 = n();
                    Unit unit2 = Unit.f18288a;
                }
                n3 = n2;
                i4 = i2;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x00b9, code lost:
    
        if (kotlin.jvm.internal.Intrinsics.a(r12, r7) == false) goto L41;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:15:0x00b0 A[Catch: all -> 0x0078, TryCatch #1 {all -> 0x0078, blocks: (B:13:0x00ac, B:15:0x00b0, B:17:0x00b5, B:19:0x00da, B:21:0x00e0, B:25:0x00bb, B:28:0x00c2, B:11:0x0074, B:12:0x009d, B:49:0x0087, B:51:0x008b), top: B:7:0x0024 }] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x00b5 A[Catch: all -> 0x0078, TryCatch #1 {all -> 0x0078, blocks: (B:13:0x00ac, B:15:0x00b0, B:17:0x00b5, B:19:0x00da, B:21:0x00e0, B:25:0x00bb, B:28:0x00c2, B:11:0x0074, B:12:0x009d, B:49:0x0087, B:51:0x008b), top: B:7:0x0024 }] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00e0 A[Catch: all -> 0x0078, TRY_LEAVE, TryCatch #1 {all -> 0x0078, blocks: (B:13:0x00ac, B:15:0x00b0, B:17:0x00b5, B:19:0x00da, B:21:0x00e0, B:25:0x00bb, B:28:0x00c2, B:11:0x0074, B:12:0x009d, B:49:0x0087, B:51:0x008b), top: B:7:0x0024 }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00d4 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0026  */
    /* JADX WARN: Type inference failed for: r2v0, types: [int] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v18 */
    /* JADX WARN: Type inference failed for: r2v19 */
    /* JADX WARN: Type inference failed for: r2v2, types: [kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot] */
    /* JADX WARN: Type inference failed for: r2v20 */
    /* JADX WARN: Type inference failed for: r2v21 */
    /* JADX WARN: Type inference failed for: r2v7, types: [java.lang.Object] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x00de -> B:13:0x00ac). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x00f0 -> B:13:0x00ac). Please report as a decompilation issue!!! */
    @Override // kotlinx.coroutines.flow.SharedFlow, kotlinx.coroutines.flow.Flow
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object a(kotlinx.coroutines.flow.FlowCollector r11, kotlin.coroutines.Continuation r12) {
        /*
            Method dump skipped, instructions count: 247
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.StateFlowImpl.a(kotlinx.coroutines.flow.FlowCollector, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.flow.internal.FusibleFlow
    public Flow b(CoroutineContext coroutineContext, int i2, BufferOverflow bufferOverflow) {
        return StateFlowKt.d(this, coroutineContext, i2, bufferOverflow);
    }

    @Override // kotlinx.coroutines.flow.MutableSharedFlow
    public boolean e(Object obj) {
        setValue(obj);
        return true;
    }

    @Override // kotlinx.coroutines.flow.MutableSharedFlow
    public void i() {
        throw new UnsupportedOperationException("MutableStateFlow.resetReplayCache is not supported");
    }

    @Override // kotlinx.coroutines.flow.FlowCollector
    public Object k(Object obj, Continuation continuation) {
        setValue(obj);
        return Unit.f18288a;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.flow.internal.AbstractSharedFlow
    /* renamed from: o, reason: merged with bridge method [inline-methods] */
    public StateFlowSlot g() {
        return new StateFlowSlot();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.flow.internal.AbstractSharedFlow
    /* renamed from: p, reason: merged with bridge method [inline-methods] */
    public StateFlowSlot[] j(int i2) {
        return new StateFlowSlot[i2];
    }

    @Override // kotlinx.coroutines.flow.MutableStateFlow
    public void setValue(Object obj) {
        if (obj == null) {
            obj = NullSurrogateKt.f19324a;
        }
        q(null, obj);
    }
}
