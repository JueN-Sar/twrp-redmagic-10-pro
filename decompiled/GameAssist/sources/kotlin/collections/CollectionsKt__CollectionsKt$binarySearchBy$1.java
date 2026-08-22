package kotlin.collections;

import kotlin.Metadata;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public final class CollectionsKt__CollectionsKt$binarySearchBy$1 extends Lambda implements Function1<Object, Integer> {
    final /* synthetic */ Comparable<Object> $key;
    final /* synthetic */ Function1<Object, Comparable<Object>> $selector;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CollectionsKt__CollectionsKt$binarySearchBy$1(Function1<Object, Comparable<Object>> function1, Comparable<Object> comparable) {
        super(1);
        this.$selector = function1;
        this.$key = comparable;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public final Integer c(Object obj) {
        int a2;
        a2 = ComparisonsKt__ComparisonsKt.a((Comparable) this.$selector.c(obj), this.$key);
        return Integer.valueOf(a2);
    }
}
