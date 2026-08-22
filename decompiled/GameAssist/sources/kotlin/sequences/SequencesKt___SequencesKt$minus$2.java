package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.functions.Function1;

@Metadata
/* loaded from: classes2.dex */
public final class SequencesKt___SequencesKt$minus$2 implements Sequence<Object> {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ Sequence f18722a;

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ Object[] f18723b;

    @Override // kotlin.sequences.Sequence
    public Iterator iterator() {
        Sequence sequence = this.f18722a;
        final Object[] objArr = this.f18723b;
        return SequencesKt___SequencesKt.h(sequence, new Function1<Object, Boolean>() { // from class: kotlin.sequences.SequencesKt___SequencesKt$minus$2$iterator$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public final Boolean c(Object obj) {
                boolean w;
                w = ArraysKt___ArraysKt.w(objArr, obj);
                return Boolean.valueOf(w);
            }
        }).iterator();
    }
}
