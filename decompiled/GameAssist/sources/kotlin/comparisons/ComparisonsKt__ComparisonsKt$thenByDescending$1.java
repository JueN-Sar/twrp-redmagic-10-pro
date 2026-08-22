package kotlin.comparisons;

import java.util.Comparator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public final class ComparisonsKt__ComparisonsKt$thenByDescending$1<T> implements Comparator {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ Comparator f18394c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ Function1 f18395h;

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        int a2;
        int compare = this.f18394c.compare(obj, obj2);
        if (compare != 0) {
            return compare;
        }
        Function1 function1 = this.f18395h;
        a2 = ComparisonsKt__ComparisonsKt.a((Comparable) function1.c(obj2), (Comparable) function1.c(obj));
        return a2;
    }
}
