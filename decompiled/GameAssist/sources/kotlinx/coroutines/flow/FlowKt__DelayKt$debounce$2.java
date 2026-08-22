package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata
/* loaded from: classes2.dex */
final class FlowKt__DelayKt$debounce$2 extends Lambda implements Function1<Object, Long> {
    final /* synthetic */ long $timeoutMillis;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    FlowKt__DelayKt$debounce$2(long j2) {
        super(1);
        this.$timeoutMillis = j2;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public final Long c(Object obj) {
        return Long.valueOf(this.$timeoutMillis);
    }
}
