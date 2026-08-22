package kotlin.sequences;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

@Metadata
/* loaded from: classes2.dex */
final class SequencesKt___SequencesKt$zipWithNext$1 extends Lambda implements Function2<Object, Object, Pair<Object, Object>> {
    public static final SequencesKt___SequencesKt$zipWithNext$1 INSTANCE = new SequencesKt___SequencesKt$zipWithNext$1();

    SequencesKt___SequencesKt$zipWithNext$1() {
        super(2);
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public final Pair y(Object obj, Object obj2) {
        return TuplesKt.a(obj, obj2);
    }
}
