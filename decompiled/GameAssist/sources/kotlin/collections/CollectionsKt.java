package kotlin.collections;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

@Metadata
/* loaded from: classes2.dex */
public final class CollectionsKt extends CollectionsKt___CollectionsKt {
    public static /* bridge */ /* synthetic */ Appendable C(Iterable iterable, Appendable appendable, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i2, CharSequence charSequence4, Function1 function1, int i3, Object obj) {
        Appendable B;
        B = CollectionsKt___CollectionsKt.B(iterable, appendable, (r14 & 2) != 0 ? ", " : charSequence, (r14 & 4) != 0 ? "" : charSequence2, (r14 & 8) == 0 ? charSequence3 : "", (r14 & 16) != 0 ? -1 : i2, (r14 & 32) != 0 ? "..." : charSequence4, (r14 & 64) != 0 ? null : function1);
        return B;
    }
}
