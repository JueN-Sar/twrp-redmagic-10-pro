package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.IndexedValue;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.internal.Ref;

@Metadata
/* loaded from: classes2.dex */
public final class FlowKt__TransformKt$withIndex$$inlined$unsafeFlow$1 implements Flow<IndexedValue<Object>> {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ Flow f19225c;

    @Override // kotlinx.coroutines.flow.Flow
    public Object a(FlowCollector flowCollector, Continuation continuation) {
        Object d2;
        Object a2 = this.f19225c.a(new FlowKt__TransformKt$withIndex$1$1(flowCollector, new Ref.IntRef()), continuation);
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        return a2 == d2 ? a2 : Unit.f18288a;
    }
}
