package kotlinx.coroutines.channels;

import kotlin.ExceptionsKt__ExceptionsKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata
/* loaded from: classes2.dex */
final class ChannelsKt__DeprecatedKt$consumesAll$1 extends Lambda implements Function1<Throwable, Unit> {
    final /* synthetic */ ReceiveChannel<?>[] $channels;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ChannelsKt__DeprecatedKt$consumesAll$1(ReceiveChannel<?>[] receiveChannelArr) {
        super(1);
        this.$channels = receiveChannelArr;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object c(Object obj) {
        d((Throwable) obj);
        return Unit.f18288a;
    }

    public final void d(Throwable th) {
        Throwable th2 = null;
        for (ReceiveChannel<?> receiveChannel : this.$channels) {
            try {
                ChannelsKt.b(receiveChannel, th);
            } catch (Throwable th3) {
                if (th2 == null) {
                    th2 = th3;
                } else {
                    ExceptionsKt__ExceptionsKt.a(th2, th3);
                }
            }
        }
        if (th2 != null) {
            throw th2;
        }
    }
}
