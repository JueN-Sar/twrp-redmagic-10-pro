package kotlinx.coroutines.flow;

import com.google.android.gms.common.api.Api;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;
import kotlinx.coroutines.internal.SystemPropsKt;

@Metadata
/* loaded from: classes2.dex */
final /* synthetic */ class FlowKt__MergeKt {

    /* renamed from: a, reason: collision with root package name */
    private static final int f19167a = SystemPropsKt.b("kotlinx.coroutines.flow.defaultConcurrency", 16, 1, Api.BaseClientBuilder.API_PRIORITY_OTHER);

    public static final Flow a(Flow flow, Function2 function2) {
        return FlowKt.B(flow, new FlowKt__MergeKt$mapLatest$1(function2, null));
    }

    public static final Flow b(Flow flow, Function3 function3) {
        return new ChannelFlowTransformLatest(function3, flow, null, 0, null, 28, null);
    }
}
