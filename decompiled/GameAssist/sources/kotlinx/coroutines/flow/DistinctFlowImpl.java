package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;

@Metadata
/* loaded from: classes2.dex */
final class DistinctFlowImpl<T> implements Flow<T> {

    /* renamed from: c, reason: collision with root package name */
    private final Flow f19094c;

    /* renamed from: h, reason: collision with root package name */
    public final Function1 f19095h;

    /* renamed from: i, reason: collision with root package name */
    public final Function2 f19096i;

    public DistinctFlowImpl(Flow flow, Function1 function1, Function2 function2) {
        this.f19094c = flow;
        this.f19095h = function1;
        this.f19096i = function2;
    }

    @Override // kotlinx.coroutines.flow.Flow
    public Object a(FlowCollector flowCollector, Continuation continuation) {
        Object d2;
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = (T) NullSurrogateKt.f19324a;
        Object a2 = this.f19094c.a(new DistinctFlowImpl$collect$2(this, objectRef, flowCollector), continuation);
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        return a2 == d2 ? a2 : Unit.f18288a;
    }
}
