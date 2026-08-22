package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

@Metadata
/* loaded from: classes2.dex */
final class ChannelsKt__DeprecatedKt$zip$1 extends Lambda implements Function2 {
    public static final ChannelsKt__DeprecatedKt$zip$1 INSTANCE = new ChannelsKt__DeprecatedKt$zip$1();

    ChannelsKt__DeprecatedKt$zip$1() {
        super(2);
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public final Pair y(Object obj, Object obj2) {
        return TuplesKt.a(obj, obj2);
    }
}
