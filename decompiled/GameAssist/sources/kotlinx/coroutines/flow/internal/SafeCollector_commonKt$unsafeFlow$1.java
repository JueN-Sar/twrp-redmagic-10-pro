package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata
/* loaded from: classes2.dex */
public final class SafeCollector_commonKt$unsafeFlow$1 implements Flow<Object> {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ Function2 f19328c;

    @Override // kotlinx.coroutines.flow.Flow
    public Object a(FlowCollector flowCollector, Continuation continuation) {
        Object d2;
        Object y = this.f19328c.y(flowCollector, continuation);
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        return y == d2 ? y : Unit.f18288a;
    }
}
