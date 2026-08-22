package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;

@Metadata
/* loaded from: classes2.dex */
public final class FlowKt__TransformKt$runningReduce$$inlined$unsafeFlow$1 implements Flow<Object> {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ Flow f19223c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ Function3 f19224h;

    /* JADX WARN: Type inference failed for: r1v0, types: [T, kotlinx.coroutines.internal.Symbol] */
    @Override // kotlinx.coroutines.flow.Flow
    public Object a(FlowCollector flowCollector, Continuation continuation) {
        Object d2;
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = NullSurrogateKt.f19324a;
        Object a2 = this.f19223c.a(new FlowKt__TransformKt$runningReduce$1$1(objectRef, this.f19224h, flowCollector), continuation);
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        return a2 == d2 ? a2 : Unit.f18288a;
    }
}
