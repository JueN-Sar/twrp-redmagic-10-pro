package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlin.time.Duration;
import kotlinx.coroutines.DelayKt;

@Metadata
/* loaded from: classes2.dex */
final class FlowKt__DelayKt$debounce$3 extends Lambda implements Function1<Object, Long> {
    final /* synthetic */ Function1<Object, Duration> $timeout;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    FlowKt__DelayKt$debounce$3(Function1<Object, Duration> function1) {
        super(1);
        this.$timeout = function1;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public final Long c(Object obj) {
        return Long.valueOf(DelayKt.d(((Duration) this.$timeout.c(obj)).M()));
    }
}
