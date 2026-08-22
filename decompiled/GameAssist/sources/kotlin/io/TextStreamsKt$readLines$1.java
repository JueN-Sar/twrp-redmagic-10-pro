package kotlin.io;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata
/* loaded from: classes2.dex */
final class TextStreamsKt$readLines$1 extends Lambda implements Function1<String, Unit> {
    final /* synthetic */ ArrayList<String> $result;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TextStreamsKt$readLines$1(ArrayList<String> arrayList) {
        super(1);
        this.$result = arrayList;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object c(Object obj) {
        d((String) obj);
        return Unit.f18288a;
    }

    public final void d(String it) {
        Intrinsics.e(it, "it");
        this.$result.add(it);
    }
}
