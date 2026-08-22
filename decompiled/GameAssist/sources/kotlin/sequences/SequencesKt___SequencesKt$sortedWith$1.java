package kotlin.sequences;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__MutableCollectionsJVMKt;

@Metadata
/* loaded from: classes2.dex */
public final class SequencesKt___SequencesKt$sortedWith$1 implements Sequence<Object> {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ Sequence f18729a;

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ Comparator f18730b;

    @Override // kotlin.sequences.Sequence
    public Iterator iterator() {
        List m2 = SequencesKt___SequencesKt.m(this.f18729a);
        CollectionsKt__MutableCollectionsJVMKt.p(m2, this.f18730b);
        return m2.iterator();
    }
}
