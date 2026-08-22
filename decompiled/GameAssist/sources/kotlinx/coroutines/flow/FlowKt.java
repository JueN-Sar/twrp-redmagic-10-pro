package kotlinx.coroutines.flow;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.ReceiveChannel;

@Metadata
/* loaded from: classes2.dex */
public final class FlowKt {
    public static final Object A(Flow flow, Collection collection, Continuation continuation) {
        return FlowKt__CollectionKt.a(flow, collection, continuation);
    }

    public static final Flow B(Flow flow, Function3 function3) {
        return FlowKt__MergeKt.b(flow, function3);
    }

    public static final Flow a(Flow flow, int i2, BufferOverflow bufferOverflow) {
        return FlowKt__ContextKt.a(flow, i2, bufferOverflow);
    }

    public static final Object c(Flow flow, FlowCollector flowCollector, Continuation continuation) {
        return FlowKt__ErrorsKt.a(flow, flowCollector, continuation);
    }

    public static final Object d(Flow flow, Continuation continuation) {
        return FlowKt__CollectKt.a(flow, continuation);
    }

    public static final Object e(Flow flow, Function2 function2, Continuation continuation) {
        return FlowKt__CollectKt.b(flow, function2, continuation);
    }

    public static final Object f(Flow flow, Continuation continuation) {
        return FlowKt__CountKt.a(flow, continuation);
    }

    public static final Object g(Flow flow, Function2 function2, Continuation continuation) {
        return FlowKt__CountKt.b(flow, function2, continuation);
    }

    public static final Flow h(Flow flow) {
        return FlowKt__DistinctKt.a(flow);
    }

    public static final Flow i(Flow flow, Function2 function2) {
        return FlowKt__LimitKt.c(flow, function2);
    }

    public static final Object j(FlowCollector flowCollector, ReceiveChannel receiveChannel, Continuation continuation) {
        return FlowKt__ChannelsKt.b(flowCollector, receiveChannel, continuation);
    }

    public static final Object k(FlowCollector flowCollector, Flow flow, Continuation continuation) {
        return FlowKt__CollectKt.c(flowCollector, flow, continuation);
    }

    public static final void l(FlowCollector flowCollector) {
        FlowKt__EmittersKt.b(flowCollector);
    }

    public static final Object m(Flow flow, Continuation continuation) {
        return FlowKt__ReduceKt.a(flow, continuation);
    }

    public static final Object n(Flow flow, Function2 function2, Continuation continuation) {
        return FlowKt__ReduceKt.b(flow, function2, continuation);
    }

    public static final Object o(Flow flow, Continuation continuation) {
        return FlowKt__ReduceKt.c(flow, continuation);
    }

    public static final Object p(Flow flow, Function2 function2, Continuation continuation) {
        return FlowKt__ReduceKt.d(flow, function2, continuation);
    }

    public static final ReceiveChannel q(CoroutineScope coroutineScope, long j2, long j3) {
        return FlowKt__DelayKt.a(coroutineScope, j2, j3);
    }

    public static final Flow s(Function2 function2) {
        return FlowKt__BuildersKt.a(function2);
    }

    public static final Flow t(Object obj) {
        return FlowKt__BuildersKt.b(obj);
    }

    public static final Object u(Flow flow, Continuation continuation) {
        return FlowKt__ReduceKt.f(flow, continuation);
    }

    public static final Object v(Flow flow, Continuation continuation) {
        return FlowKt__ReduceKt.g(flow, continuation);
    }

    public static final Flow w(Flow flow, Function2 function2) {
        return FlowKt__MergeKt.a(flow, function2);
    }

    public static final Object x(Flow flow, Function3 function3, Continuation continuation) {
        return FlowKt__ReduceKt.h(flow, function3, continuation);
    }

    public static final Object y(Flow flow, Continuation continuation) {
        return FlowKt__ReduceKt.i(flow, continuation);
    }

    public static final Object z(Flow flow, Continuation continuation) {
        return FlowKt__ReduceKt.j(flow, continuation);
    }
}
