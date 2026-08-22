package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata
/* loaded from: classes2.dex */
public final class CombineKt$zipImpl$$inlined$unsafeFlow$1 implements Flow<Object> {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ Flow f19307c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ Flow f19308h;

    /* renamed from: i, reason: collision with root package name */
    final /* synthetic */ Function3 f19309i;

    @Override // kotlinx.coroutines.flow.Flow
    public Object a(FlowCollector flowCollector, Continuation continuation) {
        Object d2;
        Object a2 = CoroutineScopeKt.a(new CombineKt$zipImpl$1$1(flowCollector, this.f19307c, this.f19308h, this.f19309i, null), continuation);
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        return a2 == d2 ? a2 : Unit.f18288a;
    }
}
