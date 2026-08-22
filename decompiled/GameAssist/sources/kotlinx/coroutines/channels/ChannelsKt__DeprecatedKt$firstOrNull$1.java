package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata
@DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", l = {75}, m = "firstOrNull")
/* loaded from: classes2.dex */
final class ChannelsKt__DeprecatedKt$firstOrNull$1<E> extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;

    ChannelsKt__DeprecatedKt$firstOrNull$1(Continuation<? super ChannelsKt__DeprecatedKt$firstOrNull$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object v(Object obj) {
        Object h2;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        h2 = ChannelsKt__DeprecatedKt.h(null, this);
        return h2;
    }
}
