package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

@Metadata
/* loaded from: classes2.dex */
final class InvokeOnCompletion extends JobNode {

    /* renamed from: k, reason: collision with root package name */
    private final Function1 f18897k;

    public InvokeOnCompletion(Function1 function1) {
        this.f18897k = function1;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object c(Object obj) {
        d0((Throwable) obj);
        return Unit.f18288a;
    }

    @Override // kotlinx.coroutines.CompletionHandlerBase
    public void d0(Throwable th) {
        this.f18897k.c(th);
    }
}
