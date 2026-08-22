package kotlin.collections;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata
/* loaded from: classes2.dex */
final class CollectionsKt___CollectionsKt$withIndex$1 extends Lambda implements Function0<Iterator<Object>> {
    final /* synthetic */ Iterable<Object> $this_withIndex;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CollectionsKt___CollectionsKt$withIndex$1(Iterable<Object> iterable) {
        super(0);
        this.$this_withIndex = iterable;
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public final Iterator a() {
        return this.$this_withIndex.iterator();
    }
}
