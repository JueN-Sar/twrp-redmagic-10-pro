package kotlin.sequences;

import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.jvm.functions.Function1;

@Metadata
/* loaded from: classes2.dex */
public final class SequencesKt___SequencesKt$minus$3 implements Sequence<Object> {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ Iterable f18724a;

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ Sequence f18725b;

    @Override // kotlin.sequences.Sequence
    public Iterator iterator() {
        final Collection q2;
        q2 = CollectionsKt__MutableCollectionsKt.q(this.f18724a);
        return q2.isEmpty() ? this.f18725b.iterator() : SequencesKt___SequencesKt.h(this.f18725b, new Function1<Object, Boolean>() { // from class: kotlin.sequences.SequencesKt___SequencesKt$minus$3$iterator$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public final Boolean c(Object obj) {
                return Boolean.valueOf(q2.contains(obj));
            }
        }).iterator();
    }
}
