package kotlinx.coroutines.debug.internal;

import com.zte.mifavor.widget.VolumeView;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata
@DebugMetadata(c = "kotlinx.coroutines.debug.internal.DebugCoroutineInfoImpl", f = "DebugCoroutineInfoImpl.kt", l = {VolumeView.MINI_VOLUME}, m = "yieldFrames")
/* loaded from: classes2.dex */
final class DebugCoroutineInfoImpl$yieldFrames$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ DebugCoroutineInfoImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DebugCoroutineInfoImpl$yieldFrames$1(DebugCoroutineInfoImpl debugCoroutineInfoImpl, Continuation<? super DebugCoroutineInfoImpl$yieldFrames$1> continuation) {
        super(continuation);
        this.this$0 = debugCoroutineInfoImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object v(Object obj) {
        Object i2;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        i2 = this.this$0.i(null, null, this);
        return i2;
    }
}
