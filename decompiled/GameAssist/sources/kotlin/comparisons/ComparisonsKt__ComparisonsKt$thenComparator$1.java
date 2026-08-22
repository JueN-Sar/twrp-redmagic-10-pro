package kotlin.comparisons;

import java.util.Comparator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public final class ComparisonsKt__ComparisonsKt$thenComparator$1<T> implements Comparator {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ Comparator f18399c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ Function2 f18400h;

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        int compare = this.f18399c.compare(obj, obj2);
        return compare != 0 ? compare : ((Number) this.f18400h.y(obj, obj2)).intValue();
    }
}
