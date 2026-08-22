package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.selects.SelectInstance;

@Metadata
/* loaded from: classes2.dex */
final class SelectAwaitOnCompletion<T, R> extends JobNode {

    /* renamed from: k, reason: collision with root package name */
    private final SelectInstance f18928k;

    /* renamed from: l, reason: collision with root package name */
    private final Function2 f18929l;

    public SelectAwaitOnCompletion(SelectInstance selectInstance, Function2 function2) {
        this.f18928k = selectInstance;
        this.f18929l = function2;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object c(Object obj) {
        d0((Throwable) obj);
        return Unit.f18288a;
    }

    @Override // kotlinx.coroutines.CompletionHandlerBase
    public void d0(Throwable th) {
        if (this.f18928k.w()) {
            e0().Q0(this.f18928k, this.f18929l);
        }
    }
}
