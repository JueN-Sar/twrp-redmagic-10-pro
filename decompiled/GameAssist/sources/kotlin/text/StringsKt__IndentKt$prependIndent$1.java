package kotlin.text;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata
/* loaded from: classes2.dex */
final class StringsKt__IndentKt$prependIndent$1 extends Lambda implements Function1<String, String> {
    final /* synthetic */ String $indent;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    StringsKt__IndentKt$prependIndent$1(String str) {
        super(1);
        this.$indent = str;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public final String c(String it) {
        Intrinsics.e(it, "it");
        if (StringsKt__StringsJVMKt.j(it)) {
            return it.length() < this.$indent.length() ? this.$indent : it;
        }
        return this.$indent + it;
    }
}
