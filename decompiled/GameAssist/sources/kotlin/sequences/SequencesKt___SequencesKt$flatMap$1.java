package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

@Metadata
/* loaded from: classes2.dex */
final /* synthetic */ class SequencesKt___SequencesKt$flatMap$1 extends FunctionReferenceImpl implements Function1<Iterable<Object>, Iterator<Object>> {
    public static final SequencesKt___SequencesKt$flatMap$1 INSTANCE = new SequencesKt___SequencesKt$flatMap$1();

    SequencesKt___SequencesKt$flatMap$1() {
        super(1, Iterable.class, "iterator", "iterator()Ljava/util/Iterator;", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: p, reason: merged with bridge method [inline-methods] */
    public final Iterator c(Iterable p0) {
        Intrinsics.e(p0, "p0");
        return p0.iterator();
    }
}
