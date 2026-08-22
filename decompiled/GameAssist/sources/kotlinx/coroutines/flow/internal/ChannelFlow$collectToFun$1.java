package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ProducerScope;

/* JADX INFO: Add missing generic type declarations: [T] */
@Metadata
@DebugMetadata(c = "kotlinx.coroutines.flow.internal.ChannelFlow$collectToFun$1", f = "ChannelFlow.kt", l = {60}, m = "invokeSuspend")
/* loaded from: classes2.dex */
final class ChannelFlow$collectToFun$1<T> extends SuspendLambda implements Function2<ProducerScope<? super T>, Continuation<? super Unit>, Object> {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ ChannelFlow<T> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ChannelFlow$collectToFun$1(ChannelFlow<T> channelFlow, Continuation<? super ChannelFlow$collectToFun$1> continuation) {
        super(2, continuation);
        this.this$0 = channelFlow;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: A, reason: merged with bridge method [inline-methods] */
    public final Object y(ProducerScope producerScope, Continuation continuation) {
        return ((ChannelFlow$collectToFun$1) o(producerScope, continuation)).v(Unit.f18288a);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation o(Object obj, Continuation continuation) {
        ChannelFlow$collectToFun$1 channelFlow$collectToFun$1 = new ChannelFlow$collectToFun$1(this.this$0, continuation);
        channelFlow$collectToFun$1.L$0 = obj;
        return channelFlow$collectToFun$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object v(Object obj) {
        Object d2;
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.b(obj);
            ProducerScope producerScope = (ProducerScope) this.L$0;
            ChannelFlow<T> channelFlow = this.this$0;
            this.label = 1;
            if (channelFlow.f(producerScope, this) == d2) {
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
