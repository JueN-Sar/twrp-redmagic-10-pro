package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;

@Metadata
@DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__DelayKt$sample$2$1$2", f = "Delay.kt", l = {300}, m = "invokeSuspend")
/* loaded from: classes2.dex */
final class FlowKt__DelayKt$sample$2$1$2 extends SuspendLambda implements Function2<Unit, Continuation<? super Unit>, Object> {
    final /* synthetic */ FlowCollector<Object> $downstream;
    final /* synthetic */ Ref.ObjectRef<Object> $lastValue;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    FlowKt__DelayKt$sample$2$1$2(Ref.ObjectRef<Object> objectRef, FlowCollector<Object> flowCollector, Continuation<? super FlowKt__DelayKt$sample$2$1$2> continuation) {
        super(2, continuation);
        this.$lastValue = objectRef;
        this.$downstream = flowCollector;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: A, reason: merged with bridge method [inline-methods] */
    public final Object y(Unit unit, Continuation continuation) {
        return ((FlowKt__DelayKt$sample$2$1$2) o(unit, continuation)).v(Unit.f18288a);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation o(Object obj, Continuation continuation) {
        return new FlowKt__DelayKt$sample$2$1$2(this.$lastValue, this.$downstream, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object v(Object obj) {
        Object d2;
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.b(obj);
            Ref.ObjectRef<Object> objectRef = this.$lastValue;
            Object obj2 = objectRef.element;
            if (obj2 == null) {
                return Unit.f18288a;
            }
            objectRef.element = null;
            FlowCollector<Object> flowCollector = this.$downstream;
            if (obj2 == NullSurrogateKt.f19324a) {
                obj2 = null;
            }
            this.label = 1;
            if (flowCollector.k(obj2, this) == d2) {
                return d2;
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.b(obj);
        }
        return Unit.f18288a;
    }
}
