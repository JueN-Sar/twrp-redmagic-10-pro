package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata
@DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__ChannelsKt$trySendBlocking$2", f = "Channels.kt", l = {39}, m = "invokeSuspend")
/* loaded from: classes2.dex */
final class ChannelsKt__ChannelsKt$trySendBlocking$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ChannelResult<? extends Unit>>, Object> {
    final /* synthetic */ Object $element;
    final /* synthetic */ SendChannel<Object> $this_trySendBlocking;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ChannelsKt__ChannelsKt$trySendBlocking$2(SendChannel<Object> sendChannel, Object obj, Continuation<? super ChannelsKt__ChannelsKt$trySendBlocking$2> continuation) {
        super(2, continuation);
        this.$this_trySendBlocking = sendChannel;
        this.$element = obj;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: A, reason: merged with bridge method [inline-methods] */
    public final Object y(CoroutineScope coroutineScope, Continuation continuation) {
        return ((ChannelsKt__ChannelsKt$trySendBlocking$2) o(coroutineScope, continuation)).v(Unit.f18288a);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation o(Object obj, Continuation continuation) {
        ChannelsKt__ChannelsKt$trySendBlocking$2 channelsKt__ChannelsKt$trySendBlocking$2 = new ChannelsKt__ChannelsKt$trySendBlocking$2(this.$this_trySendBlocking, this.$element, continuation);
        channelsKt__ChannelsKt$trySendBlocking$2.L$0 = obj;
        return channelsKt__ChannelsKt$trySendBlocking$2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object v(Object obj) {
        Object d2;
        Object b2;
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        int i2 = this.label;
        try {
            if (i2 == 0) {
                ResultKt.b(obj);
                SendChannel<Object> sendChannel = this.$this_trySendBlocking;
                Object obj2 = this.$element;
                Result.Companion companion = Result.Companion;
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
            b2 = Result.b(Unit.f18288a);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            b2 = Result.b(ResultKt.a(th));
        }
        return ChannelResult.b(Result.g(b2) ? ChannelResult.f19005b.c(Unit.f18288a) : ChannelResult.f19005b.a(Result.d(b2)));
    }
}
