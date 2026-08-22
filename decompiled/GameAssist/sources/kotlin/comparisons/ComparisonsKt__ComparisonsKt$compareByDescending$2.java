package kotlin.comparisons;

import java.util.Comparator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public final class ComparisonsKt__ComparisonsKt$compareByDescending$2<T> implements Comparator {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ Comparator f18387c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ Function1 f18388h;

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        Comparator comparator = this.f18387c;
        Function1 function1 = this.f18388h;
        return comparator.compare(function1.c(obj2), function1.c(obj));
    }
}
