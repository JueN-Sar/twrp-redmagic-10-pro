package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata
/* loaded from: classes2.dex */
public final class FlowExceptions_commonKt {
    public static final void a(AbortFlowException abortFlowException, FlowCollector flowCollector) {
        if (abortFlowException.owner != flowCollector) {
            throw abortFlowException;
        }
    }
}
