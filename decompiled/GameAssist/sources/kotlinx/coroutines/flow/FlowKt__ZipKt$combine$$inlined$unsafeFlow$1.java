package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.internal.CombineKt;

@Metadata
/* loaded from: classes2.dex */
public final class FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 implements Flow<Object> {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ Flow f19240c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ Flow f19241h;

    /* renamed from: i, reason: collision with root package name */
    final /* synthetic */ Function3 f19242i;

    @Override // kotlinx.coroutines.flow.Flow
    public Object a(FlowCollector flowCollector, Continuation continuation) {
        Function0 b2;
        Object d2;
        Flow[] flowArr = {this.f19240c, this.f19241h};
        b2 = FlowKt__ZipKt.b();
        Object a2 = CombineKt.a(flowCollector, flowArr, b2, new FlowKt__ZipKt$combine$1$1(this.f19242i, null), continuation);
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        return a2 == d2 ? a2 : Unit.f18288a;
    }
}
