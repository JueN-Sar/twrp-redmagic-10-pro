package kotlin.text;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata
/* loaded from: classes2.dex */
final class StringsKt___StringsKt$windowedSequence$2 extends Lambda implements Function1<Integer, Object> {
    final /* synthetic */ int $size;
    final /* synthetic */ CharSequence $this_windowedSequence;
    final /* synthetic */ Function1<CharSequence, Object> $transform;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    StringsKt___StringsKt$windowedSequence$2(int i2, CharSequence charSequence, Function1<? super CharSequence, Object> function1) {
        super(1);
        this.$size = i2;
        this.$this_windowedSequence = charSequence;
        this.$transform = function1;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object c(Object obj) {
        return d(((Number) obj).intValue());
    }

    public final Object d(int i2) {
        int i3 = this.$size + i2;
        if (i3 < 0 || i3 > this.$this_windowedSequence.length()) {
            i3 = this.$this_windowedSequence.length();
        }
        return this.$transform.c(this.$this_windowedSequence.subSequence(i2, i3));
    }
}
