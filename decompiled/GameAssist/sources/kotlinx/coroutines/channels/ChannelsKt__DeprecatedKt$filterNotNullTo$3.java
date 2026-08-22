package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlinx.coroutines.channels.SendChannel;

@Metadata
@DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", l = {487, 242}, m = "filterNotNullTo")
/* loaded from: classes2.dex */
final class ChannelsKt__DeprecatedKt$filterNotNullTo$3<E, C extends SendChannel<? super E>> extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;

    ChannelsKt__DeprecatedKt$filterNotNullTo$3(Continuation<? super ChannelsKt__DeprecatedKt$filterNotNullTo$3> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object v(Object obj) {
        Object f2;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        f2 = ChannelsKt__DeprecatedKt.f(null, null, this);
        return f2;
    }
}
