package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;
import kotlinx.coroutines.selects.SelectBuilderImpl;

@Metadata
@DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__DelayKt$sample$2", f = "Delay.kt", l = {352}, m = "invokeSuspend")
/* loaded from: classes2.dex */
final class FlowKt__DelayKt$sample$2 extends SuspendLambda implements Function3<CoroutineScope, FlowCollector<Object>, Continuation<? super Unit>, Object> {
    final /* synthetic */ long $periodMillis;
    final /* synthetic */ Flow<Object> $this_sample;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    FlowKt__DelayKt$sample$2(long j2, Flow<Object> flow, Continuation<? super FlowKt__DelayKt$sample$2> continuation) {
        super(3, continuation);
        this.$periodMillis = j2;
        this.$this_sample = flow;
    }

    @Override // kotlin.jvm.functions.Function3
    /* renamed from: A, reason: merged with bridge method [inline-methods] */
    public final Object u(CoroutineScope coroutineScope, FlowCollector flowCollector, Continuation continuation) {
        FlowKt__DelayKt$sample$2 flowKt__DelayKt$sample$2 = new FlowKt__DelayKt$sample$2(this.$periodMillis, this.$this_sample, continuation);
        flowKt__DelayKt$sample$2.L$0 = coroutineScope;
        flowKt__DelayKt$sample$2.L$1 = flowCollector;
        return flowKt__DelayKt$sample$2.v(Unit.f18288a);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object v(Object obj) {
        Object d2;
        ReceiveChannel b2;
        FlowCollector flowCollector;
        ReceiveChannel receiveChannel;
        Ref.ObjectRef objectRef;
        ReceiveChannel receiveChannel2;
        Object d3;
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.b(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            FlowCollector flowCollector2 = (FlowCollector) this.L$1;
            ReceiveChannel d4 = ProduceKt.d(coroutineScope, null, -1, new FlowKt__DelayKt$sample$2$values$1(this.$this_sample, null), 1, null);
            Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
            b2 = FlowKt__DelayKt.b(coroutineScope, this.$periodMillis, 0L, 2, null);
            flowCollector = flowCollector2;
            receiveChannel = d4;
            objectRef = objectRef2;
            receiveChannel2 = b2;
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            receiveChannel2 = (ReceiveChannel) this.L$3;
            objectRef = (Ref.ObjectRef) this.L$2;
            receiveChannel = (ReceiveChannel) this.L$1;
            flowCollector = (FlowCollector) this.L$0;
            ResultKt.b(obj);
        }
        while (objectRef.element != NullSurrogateKt.f19326c) {
            this.L$0 = flowCollector;
            this.L$1 = receiveChannel;
            this.L$2 = objectRef;
            this.L$3 = receiveChannel2;
            this.label = 1;
            SelectBuilderImpl selectBuilderImpl = new SelectBuilderImpl(this);
            try {
                selectBuilderImpl.G(receiveChannel.x(), new FlowKt__DelayKt$sample$2$1$1(objectRef, receiveChannel2, null));
                selectBuilderImpl.G(receiveChannel2.w(), new FlowKt__DelayKt$sample$2$1$2(objectRef, flowCollector, null));
            } catch (Throwable th) {
                selectBuilderImpl.j0(th);
            }
            Object i0 = selectBuilderImpl.i0();
            d3 = IntrinsicsKt__IntrinsicsKt.d();
            if (i0 == d3) {
                DebugProbesKt.c(this);
            }
            if (i0 == d2) {
                return d2;
            }
        }
        return Unit.f18288a;
    }
}
