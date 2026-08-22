package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.internal.ThreadContextKt;

@Metadata
/* loaded from: classes2.dex */
final class UndispatchedContextCollector<T> implements FlowCollector<T> {

    /* renamed from: c, reason: collision with root package name */
    private final CoroutineContext f19332c;

    /* renamed from: h, reason: collision with root package name */
    private final Object f19333h;

    /* renamed from: i, reason: collision with root package name */
    private final Function2 f19334i;

    public UndispatchedContextCollector(FlowCollector flowCollector, CoroutineContext coroutineContext) {
        this.f19332c = coroutineContext;
        this.f19333h = ThreadContextKt.b(coroutineContext);
        this.f19334i = new UndispatchedContextCollector$emitRef$1(flowCollector, null);
    }

    @Override // kotlinx.coroutines.flow.FlowCollector
    public Object k(Object obj, Continuation continuation) {
        Object d2;
        Object b2 = ChannelFlowKt.b(this.f19332c, obj, this.f19333h, this.f19334i, continuation);
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        return b2 == d2 ? b2 : Unit.f18288a;
    }
}
