package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata
/* loaded from: classes2.dex */
final /* synthetic */ class FlowKt__DistinctKt {

    /* renamed from: a, reason: collision with root package name */
    private static final Function1 f19123a = new Function1<Object, Object>() { // from class: kotlinx.coroutines.flow.FlowKt__DistinctKt$defaultKeySelector$1
        @Override // kotlin.jvm.functions.Function1
        public final Object c(Object obj) {
            return obj;
        }
    };

    /* renamed from: b, reason: collision with root package name */
    private static final Function2 f19124b = new Function2<Object, Object, Boolean>() { // from class: kotlinx.coroutines.flow.FlowKt__DistinctKt$defaultAreEquivalent$1
        @Override // kotlin.jvm.functions.Function2
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public final Boolean y(Object obj, Object obj2) {
            return Boolean.valueOf(Intrinsics.a(obj, obj2));
        }
    };

    public static final Flow a(Flow flow) {
        return flow instanceof StateFlow ? flow : b(flow, f19123a, f19124b);
    }

    private static final Flow b(Flow flow, Function1 function1, Function2 function2) {
        if (flow instanceof DistinctFlowImpl) {
            DistinctFlowImpl distinctFlowImpl = (DistinctFlowImpl) flow;
            if (distinctFlowImpl.f19095h == function1 && distinctFlowImpl.f19096i == function2) {
                return flow;
            }
        }
        return new DistinctFlowImpl(flow, function1, function2);
    }
}
