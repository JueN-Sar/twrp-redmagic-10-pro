package kotlin.comparisons;

import java.util.Comparator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public final class ComparisonsKt__ComparisonsKt$thenBy$2<T> implements Comparator {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ Comparator f18391c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ Comparator f18392h;

    /* renamed from: i, reason: collision with root package name */
    final /* synthetic */ Function1 f18393i;

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        int compare = this.f18391c.compare(obj, obj2);
        if (compare != 0) {
            return compare;
        }
        Comparator comparator = this.f18392h;
        Function1 function1 = this.f18393i;
        return comparator.compare(function1.c(obj), function1.c(obj2));
    }
}
