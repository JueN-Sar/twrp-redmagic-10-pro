package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;

@Metadata
/* loaded from: classes2.dex */
final class CancellableFlowImpl<T> implements CancellableFlow<T> {

    /* renamed from: c, reason: collision with root package name */
    private final Flow f19088c;

    @Override // kotlinx.coroutines.flow.Flow
    public Object a(FlowCollector flowCollector, Continuation continuation) {
        Object d2;
        Object a2 = this.f19088c.a(new CancellableFlowImpl$collect$2(flowCollector), continuation);
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        return a2 == d2 ? a2 : Unit.f18288a;
    }
}
