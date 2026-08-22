package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.intrinsics.CancellableKt;
import kotlinx.coroutines.selects.SelectInstance;

@Metadata
/* loaded from: classes2.dex */
final class SelectJoinOnCompletion<R> extends JobNode {

    /* renamed from: k, reason: collision with root package name */
    private final SelectInstance f18930k;

    /* renamed from: l, reason: collision with root package name */
    private final Function1 f18931l;

    public SelectJoinOnCompletion(SelectInstance selectInstance, Function1 function1) {
        this.f18930k = selectInstance;
        this.f18931l = function1;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object c(Object obj) {
        d0((Throwable) obj);
        return Unit.f18288a;
    }

    @Override // kotlinx.coroutines.CompletionHandlerBase
    public void d0(Throwable th) {
        if (this.f18930k.w()) {
            CancellableKt.c(this.f18931l, this.f18930k.x());
        }
    }
}
