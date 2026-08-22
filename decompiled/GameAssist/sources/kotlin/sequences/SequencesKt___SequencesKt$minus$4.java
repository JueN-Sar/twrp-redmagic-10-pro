package kotlin.sequences;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

@Metadata
/* loaded from: classes2.dex */
public final class SequencesKt___SequencesKt$minus$4 implements Sequence<Object> {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ Sequence f18726a;

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ Sequence f18727b;

    @Override // kotlin.sequences.Sequence
    public Iterator iterator() {
        final List l2;
        l2 = SequencesKt___SequencesKt.l(this.f18726a);
        return l2.isEmpty() ? this.f18727b.iterator() : SequencesKt___SequencesKt.h(this.f18727b, new Function1<Object, Boolean>() { // from class: kotlin.sequences.SequencesKt___SequencesKt$minus$4$iterator$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public final Boolean c(Object obj) {
                return Boolean.valueOf(l2.contains(obj));
            }
        }).iterator();
    }
}
