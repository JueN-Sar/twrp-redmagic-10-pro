package kotlinx.coroutines.channels;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata
@DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", l = {487}, m = "filterNotNullTo")
/* loaded from: classes2.dex */
final class ChannelsKt__DeprecatedKt$filterNotNullTo$1<E, C extends Collection<? super E>> extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;

    ChannelsKt__DeprecatedKt$filterNotNullTo$1(Continuation<? super ChannelsKt__DeprecatedKt$filterNotNullTo$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object v(Object obj) {
        Object e2;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        e2 = ChannelsKt__DeprecatedKt.e(null, null, this);
        return e2;
    }
}
