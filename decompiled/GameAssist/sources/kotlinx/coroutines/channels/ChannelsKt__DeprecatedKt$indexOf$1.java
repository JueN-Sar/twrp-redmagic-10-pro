package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata
@DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", l = {487}, m = "indexOf")
/* loaded from: classes2.dex */
final class ChannelsKt__DeprecatedKt$indexOf$1<E> extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;

    ChannelsKt__DeprecatedKt$indexOf$1(Continuation<? super ChannelsKt__DeprecatedKt$indexOf$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object v(Object obj) {
        Object i2;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        i2 = ChannelsKt__DeprecatedKt.i(null, null, this);
        return i2;
    }
}
