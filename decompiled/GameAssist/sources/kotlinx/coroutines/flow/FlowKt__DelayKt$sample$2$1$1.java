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
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.flow.internal.ChildCancelledException;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;

@Metadata
@DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__DelayKt$sample$2$1$1", f = "Delay.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes2.dex */
final class FlowKt__DelayKt$sample$2$1$1 extends SuspendLambda implements Function2<ChannelResult<? extends Object>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Ref.ObjectRef<Object> $lastValue;
    final /* synthetic */ ReceiveChannel<Unit> $ticker;
    /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    FlowKt__DelayKt$sample$2$1$1(Ref.ObjectRef<Object> objectRef, ReceiveChannel<Unit> receiveChannel, Continuation<? super FlowKt__DelayKt$sample$2$1$1> continuation) {
        super(2, continuation);
        this.$lastValue = objectRef;
        this.$ticker = receiveChannel;
    }

    public final Object A(Object obj, Continuation continuation) {
        return ((FlowKt__DelayKt$sample$2$1$1) o(ChannelResult.b(obj), continuation)).v(Unit.f18288a);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation o(Object obj, Continuation continuation) {
        FlowKt__DelayKt$sample$2$1$1 flowKt__DelayKt$sample$2$1$1 = new FlowKt__DelayKt$sample$2$1$1(this.$lastValue, this.$ticker, continuation);
        flowKt__DelayKt$sample$2$1$1.L$0 = obj;
        return flowKt__DelayKt$sample$2$1$1;
    }

    /* JADX WARN: Type inference failed for: r2v4, types: [T, kotlinx.coroutines.internal.Symbol] */
    /* JADX WARN: Type inference failed for: r3v4, types: [T, java.lang.Object] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object v(Object obj) {
        IntrinsicsKt__IntrinsicsKt.d();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.b(obj);
        ?? k2 = ((ChannelResult) this.L$0).k();
        Ref.ObjectRef<Object> objectRef = this.$lastValue;
        boolean z = k2 instanceof ChannelResult.Failed;
        if (!z) {
            objectRef.element = k2;
        }
        ReceiveChannel<Unit> receiveChannel = this.$ticker;
        if (z) {
            Throwable e2 = ChannelResult.e(k2);
            if (e2 != null) {
                throw e2;
            }
            receiveChannel.a(new ChildCancelledException());
            objectRef.element = NullSurrogateKt.f19326c;
        }
        return Unit.f18288a;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object y(Object obj, Object obj2) {
        return A(((ChannelResult) obj).k(), (Continuation) obj2);
    }
}
