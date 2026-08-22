package kotlinx.coroutines.flow;

import com.google.mlkit.common.MlKitException;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata
@DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__CountKt", f = "Count.kt", l = {MlKitException.UNSUPPORTED}, m = "count")
/* loaded from: classes2.dex */
final class FlowKt__CountKt$count$1<T> extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;

    FlowKt__CountKt$count$1(Continuation<? super FlowKt__CountKt$count$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object v(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return FlowKt.f(null, this);
    }
}
