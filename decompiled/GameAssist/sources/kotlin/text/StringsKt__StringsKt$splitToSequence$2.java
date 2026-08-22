package kotlin.text;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.ranges.IntRange;

@Metadata
/* loaded from: classes2.dex */
final class StringsKt__StringsKt$splitToSequence$2 extends Lambda implements Function1<IntRange, String> {
    final /* synthetic */ CharSequence $this_splitToSequence;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    StringsKt__StringsKt$splitToSequence$2(CharSequence charSequence) {
        super(1);
        this.$this_splitToSequence = charSequence;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public final String c(IntRange it) {
        Intrinsics.e(it, "it");
        return StringsKt__StringsKt.N(this.$this_splitToSequence, it);
    }
}
