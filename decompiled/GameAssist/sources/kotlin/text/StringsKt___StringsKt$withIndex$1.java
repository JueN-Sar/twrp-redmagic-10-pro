package kotlin.text;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata
/* loaded from: classes2.dex */
final class StringsKt___StringsKt$withIndex$1 extends Lambda implements Function0<Iterator<? extends Character>> {
    final /* synthetic */ CharSequence $this_withIndex;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    StringsKt___StringsKt$withIndex$1(CharSequence charSequence) {
        super(0);
        this.$this_withIndex = charSequence;
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public final Iterator a() {
        return StringsKt__StringsKt.x(this.$this_withIndex);
    }
}
