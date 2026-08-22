package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata
@DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", l = {420, 422}, m = "maxWith")
/* loaded from: classes2.dex */
final class ChannelsKt__DeprecatedKt$maxWith$1<E> extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;

    ChannelsKt__DeprecatedKt$maxWith$1(Continuation<? super ChannelsKt__DeprecatedKt$maxWith$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object v(Object obj) {
        Object m2;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        m2 = ChannelsKt__DeprecatedKt.m(null, null, this);
        return m2;
    }
}
