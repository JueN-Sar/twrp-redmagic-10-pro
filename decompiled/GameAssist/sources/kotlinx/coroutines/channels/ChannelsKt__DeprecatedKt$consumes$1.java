package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata
/* loaded from: classes2.dex */
final class ChannelsKt__DeprecatedKt$consumes$1 extends Lambda implements Function1<Throwable, Unit> {
    final /* synthetic */ ReceiveChannel<?> $this_consumes;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ChannelsKt__DeprecatedKt$consumes$1(ReceiveChannel<?> receiveChannel) {
        super(1);
        this.$this_consumes = receiveChannel;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object c(Object obj) {
        d((Throwable) obj);
        return Unit.f18288a;
    }

    public final void d(Throwable th) {
        ChannelsKt.b(this.$this_consumes, th);
    }
}
