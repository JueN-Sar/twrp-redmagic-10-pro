package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.internal.NopCollector;

@Metadata
/* loaded from: classes2.dex */
final /* synthetic */ class FlowKt__CollectKt {
    public static final Object a(Flow flow, Continuation continuation) {
        Object d2;
        Object a2 = flow.a(NopCollector.f19323c, continuation);
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        return a2 == d2 ? a2 : Unit.f18288a;
    }

    public static final Object b(Flow flow, Function2 function2, Continuation continuation) {
        Flow b2;
        Object d2;
        b2 = FlowKt__ContextKt.b(FlowKt.w(flow, function2), 0, null, 2, null);
        Object d3 = FlowKt.d(b2, continuation);
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        return d3 == d2 ? d3 : Unit.f18288a;
    }

    public static final Object c(FlowCollector flowCollector, Flow flow, Continuation continuation) {
        Object d2;
        FlowKt.l(flowCollector);
        Object a2 = flow.a(flowCollector, continuation);
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        return a2 == d2 ? a2 : Unit.f18288a;
    }
}
