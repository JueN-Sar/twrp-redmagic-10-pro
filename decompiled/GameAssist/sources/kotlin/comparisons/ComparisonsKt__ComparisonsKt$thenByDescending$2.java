package kotlin.comparisons;

import java.util.Comparator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public final class ComparisonsKt__ComparisonsKt$thenByDescending$2<T> implements Comparator {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ Comparator f18396c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ Comparator f18397h;

    /* renamed from: i, reason: collision with root package name */
    final /* synthetic */ Function1 f18398i;

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        int compare = this.f18396c.compare(obj, obj2);
        if (compare != 0) {
            return compare;
        }
        Comparator comparator = this.f18397h;
        Function1 function1 = this.f18398i;
        return comparator.compare(function1.c(obj2), function1.c(obj));
    }
}
