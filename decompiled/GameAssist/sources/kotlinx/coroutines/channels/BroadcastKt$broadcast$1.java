package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata
/* loaded from: classes2.dex */
final class BroadcastKt$broadcast$1 extends Lambda implements Function1<Throwable, Unit> {
    final /* synthetic */ ReceiveChannel<Object> $this_broadcast;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    BroadcastKt$broadcast$1(ReceiveChannel<Object> receiveChannel) {
        super(1);
        this.$this_broadcast = receiveChannel;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object c(Object obj) {
        d((Throwable) obj);
        return Unit.f18288a;
    }

    public final void d(Throwable th) {
        ChannelsKt.b(this.$this_broadcast, th);
    }
}
