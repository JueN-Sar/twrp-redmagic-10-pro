package kotlin.text;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata
/* loaded from: classes2.dex */
final class Regex$findAll$1 extends Lambda implements Function0<MatchResult> {
    final /* synthetic */ CharSequence $input;
    final /* synthetic */ int $startIndex;
    final /* synthetic */ Regex this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    Regex$findAll$1(Regex regex, CharSequence charSequence, int i2) {
        super(0);
        this.this$0 = regex;
        this.$input = charSequence;
        this.$startIndex = i2;
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public final MatchResult a() {
        return this.this$0.b(this.$input, this.$startIndex);
    }
}
