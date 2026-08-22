package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.internal.ChannelFlowOperatorImpl;
import kotlinx.coroutines.flow.internal.FusibleFlow;

@Metadata
/* loaded from: classes2.dex */
final /* synthetic */ class FlowKt__ContextKt {
    public static final Flow a(Flow flow, int i2, BufferOverflow bufferOverflow) {
        if (i2 < 0 && i2 != -2 && i2 != -1) {
            throw new IllegalArgumentException(("Buffer size should be non-negative, BUFFERED, or CONFLATED, but was " + i2).toString());
        }
        if (i2 == -1 && bufferOverflow != BufferOverflow.SUSPEND) {
            throw new IllegalArgumentException("CONFLATED capacity cannot be used with non-default onBufferOverflow".toString());
        }
        if (i2 == -1) {
            bufferOverflow = BufferOverflow.DROP_OLDEST;
            i2 = 0;
        }
        int i3 = i2;
        BufferOverflow bufferOverflow2 = bufferOverflow;
        if (flow instanceof FusibleFlow) {
            return FusibleFlow.DefaultImpls.a((FusibleFlow) flow, null, i3, bufferOverflow2, 1, null);
        }
        return new ChannelFlowOperatorImpl(flow, null, i3, bufferOverflow2, 2, null);
    }

    public static /* synthetic */ Flow b(Flow flow, int i2, BufferOverflow bufferOverflow, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i2 = -2;
        }
        if ((i3 & 2) != 0) {
            bufferOverflow = BufferOverflow.SUSPEND;
        }
        return FlowKt.a(flow, i2, bufferOverflow);
    }
}
