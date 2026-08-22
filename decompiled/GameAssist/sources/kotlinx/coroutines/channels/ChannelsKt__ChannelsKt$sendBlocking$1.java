package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata
@DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__ChannelsKt$sendBlocking$1", f = "Channels.kt", l = {58}, m = "invokeSuspend")
/* loaded from: classes2.dex */
final class ChannelsKt__ChannelsKt$sendBlocking$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Object $element;
    final /* synthetic */ SendChannel<Object> $this_sendBlocking;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ChannelsKt__ChannelsKt$sendBlocking$1(SendChannel<Object> sendChannel, Object obj, Continuation<? super ChannelsKt__ChannelsKt$sendBlocking$1> continuation) {
        super(2, continuation);
        this.$this_sendBlocking = sendChannel;
        this.$element = obj;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: A, reason: merged with bridge method [inline-methods] */
    public final Object y(CoroutineScope coroutineScope, Continuation continuation) {
        return ((ChannelsKt__ChannelsKt$sendBlocking$1) o(coroutineScope, continuation)).v(Unit.f18288a);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation o(Object obj, Continuation continuation) {
        return new ChannelsKt__ChannelsKt$sendBlocking$1(this.$this_sendBlocking, this.$element, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object v(Object obj) {
        Object d2;
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.b(obj);
            SendChannel<Object> sendChannel = this.$this_sendBlocking;
            Object obj2 = this.$element;
            this.label = 1;
            if (sendChannel.M(obj2, this) == d2) {
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
