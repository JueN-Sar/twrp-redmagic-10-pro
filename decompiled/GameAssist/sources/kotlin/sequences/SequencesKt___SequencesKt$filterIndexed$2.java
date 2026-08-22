package kotlin.sequences;

import kotlin.Metadata;
import kotlin.collections.IndexedValue;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata
/* loaded from: classes2.dex */
final class SequencesKt___SequencesKt$filterIndexed$2 extends Lambda implements Function1<IndexedValue<Object>, Object> {
    public static final SequencesKt___SequencesKt$filterIndexed$2 INSTANCE = new SequencesKt___SequencesKt$filterIndexed$2();

    SequencesKt___SequencesKt$filterIndexed$2() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public final Object c(IndexedValue it) {
        Intrinsics.e(it, "it");
        return it.b();
    }
}
