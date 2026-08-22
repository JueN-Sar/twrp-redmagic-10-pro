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
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;

@Metadata
@DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__DelayKt$debounceInternal$1$3$2", f = "Delay.kt", l = {243}, m = "invokeSuspend")
/* loaded from: classes2.dex */
final class FlowKt__DelayKt$debounceInternal$1$3$2 extends SuspendLambda implements Function2<ChannelResult<? extends Object>, Continuation<? super Unit>, Object> {
    final /* synthetic */ FlowCollector<Object> $downstream;
    final /* synthetic */ Ref.ObjectRef<Object> $lastValue;
    /* synthetic */ Object L$0;
    Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    FlowKt__DelayKt$debounceInternal$1$3$2(Ref.ObjectRef<Object> objectRef, FlowCollector<Object> flowCollector, Continuation<? super FlowKt__DelayKt$debounceInternal$1$3$2> continuation) {
        super(2, continuation);
        this.$lastValue = objectRef;
        this.$downstream = flowCollector;
    }

    public final Object A(Object obj, Continuation continuation) {
        return ((FlowKt__DelayKt$debounceInternal$1$3$2) o(ChannelResult.b(obj), continuation)).v(Unit.f18288a);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation o(Object obj, Continuation continuation) {
        FlowKt__DelayKt$debounceInternal$1$3$2 flowKt__DelayKt$debounceInternal$1$3$2 = new FlowKt__DelayKt$debounceInternal$1$3$2(this.$lastValue, this.$downstream, continuation);
        flowKt__DelayKt$debounceInternal$1$3$2.L$0 = obj;
        return flowKt__DelayKt$debounceInternal$1$3$2;
    }

    /* JADX WARN: Type inference failed for: r6v4, types: [T, kotlinx.coroutines.internal.Symbol] */
    /* JADX WARN: Type inference failed for: r7v3, types: [T, java.lang.Object] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object v(Object obj) {
        Object d2;
        Ref.ObjectRef<Object> objectRef;
        Ref.ObjectRef<Object> objectRef2;
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.b(obj);
            ?? k2 = ((ChannelResult) this.L$0).k();
            objectRef = this.$lastValue;
            boolean z = k2 instanceof ChannelResult.Failed;
            if (!z) {
                objectRef.element = k2;
            }
            FlowCollector<Object> flowCollector = this.$downstream;
            if (z) {
                Throwable e2 = ChannelResult.e(k2);
                if (e2 != null) {
                    throw e2;
                }
                Object obj2 = objectRef.element;
                if (obj2 != null) {
                    if (obj2 == NullSurrogateKt.f19324a) {
                        obj2 = null;
                    }
                    this.L$0 = k2;
                    this.L$1 = objectRef;
                    this.label = 1;
                    if (flowCollector.k(obj2, this) == d2) {
                        return d2;
                    }
                    objectRef2 = objectRef;
                }
                objectRef.element = NullSurrogateKt.f19326c;
            }
            return Unit.f18288a;
        }
        if (i2 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        objectRef2 = (Ref.ObjectRef) this.L$1;
        ResultKt.b(obj);
        objectRef = objectRef2;
        objectRef.element = NullSurrogateKt.f19326c;
        return Unit.f18288a;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object y(Object obj, Object obj2) {
        return A(((ChannelResult) obj).k(), (Continuation) obj2);
    }
}
