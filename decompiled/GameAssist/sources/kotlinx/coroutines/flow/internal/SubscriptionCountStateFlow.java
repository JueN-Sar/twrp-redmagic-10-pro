package kotlinx.coroutines.flow.internal;

import com.google.android.gms.common.api.Api;
import kotlin.Metadata;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.SharedFlowImpl;
import kotlinx.coroutines.flow.StateFlow;

@Metadata
/* loaded from: classes2.dex */
final class SubscriptionCountStateFlow extends SharedFlowImpl<Integer> implements StateFlow<Integer> {
    public SubscriptionCountStateFlow(int i2) {
        super(1, Api.BaseClientBuilder.API_PRIORITY_OTHER, BufferOverflow.DROP_OLDEST);
        e(Integer.valueOf(i2));
    }

    public final boolean Z(int i2) {
        boolean e2;
        synchronized (this) {
            e2 = e(Integer.valueOf(((Number) M()).intValue() + i2));
        }
        return e2;
    }
}
