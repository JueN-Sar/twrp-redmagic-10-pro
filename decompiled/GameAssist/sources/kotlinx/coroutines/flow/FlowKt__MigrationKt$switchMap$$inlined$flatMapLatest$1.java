package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

@Metadata
@DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__MigrationKt$switchMap$$inlined$flatMapLatest$1", f = "Migration.kt", l = {190, 190}, m = "invokeSuspend")
/* loaded from: classes2.dex */
public final class FlowKt__MigrationKt$switchMap$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3<FlowCollector<Object>, Object, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function2 $transform;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowKt__MigrationKt$switchMap$$inlined$flatMapLatest$1(Function2 function2, Continuation continuation) {
        super(3, continuation);
        this.$transform = function2;
    }

    @Override // kotlin.jvm.functions.Function3
    /* renamed from: A, reason: merged with bridge method [inline-methods] */
    public final Object u(FlowCollector flowCollector, Object obj, Continuation continuation) {
        FlowKt__MigrationKt$switchMap$$inlined$flatMapLatest$1 flowKt__MigrationKt$switchMap$$inlined$flatMapLatest$1 = new FlowKt__MigrationKt$switchMap$$inlined$flatMapLatest$1(this.$transform, continuation);
        flowKt__MigrationKt$switchMap$$inlined$flatMapLatest$1.L$0 = flowCollector;
        flowKt__MigrationKt$switchMap$$inlined$flatMapLatest$1.L$1 = obj;
        return flowKt__MigrationKt$switchMap$$inlined$flatMapLatest$1.v(Unit.f18288a);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object v(Object obj) {
        Object d2;
        FlowCollector flowCollector;
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.b(obj);
            flowCollector = (FlowCollector) this.L$0;
            Object obj2 = this.L$1;
            Function2 function2 = this.$transform;
            this.L$0 = flowCollector;
            this.label = 1;
            obj = function2.y(obj2, this);
            if (obj == d2) {
                return d2;
            }
        } else {
            if (i2 != 1) {
                if (i2 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.b(obj);
                return Unit.f18288a;
            }
            flowCollector = (FlowCollector) this.L$0;
            ResultKt.b(obj);
        }
        this.L$0 = null;
        this.label = 2;
        if (FlowKt.k(flowCollector, (Flow) obj, this) == d2) {
            return d2;
        }
        return Unit.f18288a;
    }
}
