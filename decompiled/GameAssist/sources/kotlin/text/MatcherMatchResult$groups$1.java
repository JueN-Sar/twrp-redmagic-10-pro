package kotlin.text;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.AbstractCollection;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt___SequencesKt;

@Metadata
/* loaded from: classes2.dex */
public final class MatcherMatchResult$groups$1 extends AbstractCollection<MatchGroup> implements MatchNamedGroupCollection {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ MatcherMatchResult f18783c;

    MatcherMatchResult$groups$1(MatcherMatchResult matcherMatchResult) {
        this.f18783c = matcherMatchResult;
    }

    @Override // kotlin.collections.AbstractCollection
    public int b() {
        java.util.regex.MatchResult b2;
        b2 = this.f18783c.b();
        return b2.groupCount() + 1;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj == null || (obj instanceof MatchGroup)) {
            return d((MatchGroup) obj);
        }
        return false;
    }

    public /* bridge */ boolean d(MatchGroup matchGroup) {
        return super.contains(matchGroup);
    }

    public MatchGroup f(int i2) {
        java.util.regex.MatchResult b2;
        IntRange e2;
        java.util.regex.MatchResult b3;
        b2 = this.f18783c.b();
        e2 = RegexKt.e(b2, i2);
        if (e2.b().intValue() < 0) {
            return null;
        }
        b3 = this.f18783c.b();
        String group = b3.group(i2);
        Intrinsics.d(group, "matchResult.group(index)");
        return new MatchGroup(group, e2);
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return false;
    }

    @Override // java.util.Collection, java.lang.Iterable
    public Iterator iterator() {
        IntRange h2;
        Sequence w;
        Sequence j2;
        h2 = CollectionsKt__CollectionsKt.h(this);
        w = CollectionsKt___CollectionsKt.w(h2);
        j2 = SequencesKt___SequencesKt.j(w, new Function1<Integer, MatchGroup>() { // from class: kotlin.text.MatcherMatchResult$groups$1$iterator$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object c(Object obj) {
                return d(((Number) obj).intValue());
            }

            public final MatchGroup d(int i2) {
                return MatcherMatchResult$groups$1.this.f(i2);
            }
        });
        return j2.iterator();
    }
}
