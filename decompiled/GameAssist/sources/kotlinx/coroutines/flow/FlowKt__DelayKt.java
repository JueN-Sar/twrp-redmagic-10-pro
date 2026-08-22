package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ReceiveChannel;

@Metadata
/* loaded from: classes2.dex */
final /* synthetic */ class FlowKt__DelayKt {
    public static final ReceiveChannel a(CoroutineScope coroutineScope, long j2, long j3) {
        if (j2 < 0) {
            throw new IllegalArgumentException(("Expected non-negative delay, but has " + j2 + " ms").toString());
        }
        if (j3 >= 0) {
            return ProduceKt.d(coroutineScope, null, 0, new FlowKt__DelayKt$fixedPeriodTicker$3(j3, j2, null), 1, null);
        }
        throw new IllegalArgumentException(("Expected non-negative initial delay, but has " + j3 + " ms").toString());
    }

    public static /* synthetic */ ReceiveChannel b(CoroutineScope coroutineScope, long j2, long j3, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            j3 = j2;
        }
        return FlowKt.q(coroutineScope, j2, j3);
    }
}
