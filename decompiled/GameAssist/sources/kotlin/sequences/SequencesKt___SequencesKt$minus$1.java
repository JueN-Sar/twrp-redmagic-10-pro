package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

@Metadata
/* loaded from: classes2.dex */
public final class SequencesKt___SequencesKt$minus$1 implements Sequence<Object> {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ Sequence f18720a;

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ Object f18721b;

    @Override // kotlin.sequences.Sequence
    public Iterator iterator() {
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        Sequence sequence = this.f18720a;
        final Object obj = this.f18721b;
        return SequencesKt___SequencesKt.g(sequence, new Function1<Object, Boolean>() { // from class: kotlin.sequences.SequencesKt___SequencesKt$minus$1$iterator$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public final Boolean c(Object obj2) {
                boolean z = true;
                if (!Ref.BooleanRef.this.element && Intrinsics.a(obj2, obj)) {
                    Ref.BooleanRef.this.element = true;
                    z = false;
                }
                return Boolean.valueOf(z);
            }
        }).iterator();
    }
}
