package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.functions.Function2;

@Metadata
/* loaded from: classes2.dex */
final /* synthetic */ class FlowKt__BuildersKt {
    public static final Flow a(Function2 function2) {
        return new SafeFlow(function2);
    }

    public static final Flow b(final Object obj) {
        return new Flow<Object>() { // from class: kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2
            @Override // kotlinx.coroutines.flow.Flow
            public Object a(FlowCollector flowCollector, Continuation continuation) {
                Object d2;
                Object k2 = flowCollector.k(obj, continuation);
                d2 = IntrinsicsKt__IntrinsicsKt.d();
                return k2 == d2 ? k2 : Unit.f18288a;
            }
        };
    }
}
