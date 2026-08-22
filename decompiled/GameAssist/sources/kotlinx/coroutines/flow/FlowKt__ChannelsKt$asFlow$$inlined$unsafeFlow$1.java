package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlinx.coroutines.channels.BroadcastChannel;

@Metadata
/* loaded from: classes2.dex */
public final class FlowKt__ChannelsKt$asFlow$$inlined$unsafeFlow$1 implements Flow<Object> {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ BroadcastChannel f19113c;

    @Override // kotlinx.coroutines.flow.Flow
    public Object a(FlowCollector flowCollector, Continuation continuation) {
        Object d2;
        Object j2 = FlowKt.j(flowCollector, this.f19113c.q(), continuation);
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        return j2 == d2 ? j2 : Unit.f18288a;
    }
}
