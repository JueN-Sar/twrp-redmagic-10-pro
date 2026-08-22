package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.internal.Ref;

@Metadata
/* loaded from: classes2.dex */
public final class FlowKt__LimitKt$drop$$inlined$unsafeFlow$1 implements Flow<Object> {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ Flow f19145c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ int f19146h;

    @Override // kotlinx.coroutines.flow.Flow
    public Object a(FlowCollector flowCollector, Continuation continuation) {
        Object d2;
        Object a2 = this.f19145c.a(new FlowKt__LimitKt$drop$2$1(new Ref.IntRef(), this.f19146h, flowCollector), continuation);
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        return a2 == d2 ? a2 : Unit.f18288a;
    }
}
