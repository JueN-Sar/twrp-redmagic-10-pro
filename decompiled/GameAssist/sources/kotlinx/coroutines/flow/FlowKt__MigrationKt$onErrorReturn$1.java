package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata
/* loaded from: classes2.dex */
final class FlowKt__MigrationKt$onErrorReturn$1 extends Lambda implements Function1<Throwable, Boolean> {
    public static final FlowKt__MigrationKt$onErrorReturn$1 INSTANCE = new FlowKt__MigrationKt$onErrorReturn$1();

    FlowKt__MigrationKt$onErrorReturn$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public final Boolean c(Throwable th) {
        return Boolean.TRUE;
    }
}
