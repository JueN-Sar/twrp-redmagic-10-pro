package kotlin.text;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
final class StringsKt__StringsKt$rangesDelimitedBy$1 extends Lambda implements Function2<CharSequence, Integer, Pair<? extends Integer, ? extends Integer>> {
    final /* synthetic */ char[] $delimiters;
    final /* synthetic */ boolean $ignoreCase;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    StringsKt__StringsKt$rangesDelimitedBy$1(char[] cArr, boolean z) {
        super(2);
        this.$delimiters = cArr;
        this.$ignoreCase = z;
    }

    public final Pair d(CharSequence $receiver, int i2) {
        Intrinsics.e($receiver, "$this$$receiver");
        int w = StringsKt__StringsKt.w($receiver, this.$delimiters, i2, this.$ignoreCase);
        if (w < 0) {
            return null;
        }
        return TuplesKt.a(Integer.valueOf(w), 1);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object y(Object obj, Object obj2) {
        return d((CharSequence) obj, ((Number) obj2).intValue());
    }
}
