package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.functions.Function2;

@Metadata
/* loaded from: classes2.dex */
public final class FlowKt__CollectKt$collect$3 implements FlowCollector<Object> {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ Function2 f19114c;

    @Override // kotlinx.coroutines.flow.FlowCollector
    public Object k(Object obj, Continuation continuation) {
        Object d2;
        Object y = this.f19114c.y(obj, continuation);
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        return y == d2 ? y : Unit.f18288a;
    }
}
