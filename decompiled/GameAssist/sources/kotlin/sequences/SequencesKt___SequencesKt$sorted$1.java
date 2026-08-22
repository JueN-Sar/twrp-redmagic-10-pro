package kotlin.sequences;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__MutableCollectionsJVMKt;

@Metadata
/* loaded from: classes2.dex */
public final class SequencesKt___SequencesKt$sorted$1 implements Sequence<Comparable<Object>> {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ Sequence f18728a;

    @Override // kotlin.sequences.Sequence
    public Iterator iterator() {
        List m2 = SequencesKt___SequencesKt.m(this.f18728a);
        CollectionsKt__MutableCollectionsJVMKt.o(m2);
        return m2.iterator();
    }
}
