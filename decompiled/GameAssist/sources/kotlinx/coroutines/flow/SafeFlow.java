package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.functions.Function2;

@Metadata
/* loaded from: classes2.dex */
final class SafeFlow<T> extends AbstractFlow<T> {

    /* renamed from: c, reason: collision with root package name */
    private final Function2 f19252c;

    public SafeFlow(Function2 function2) {
        this.f19252c = function2;
    }

    @Override // kotlinx.coroutines.flow.AbstractFlow
    public Object c(FlowCollector flowCollector, Continuation continuation) {
        Object d2;
        Object y = this.f19252c.y(flowCollector, continuation);
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        return y == d2 ? y : Unit.f18288a;
    }
}
