package kotlinx.coroutines;

import java.util.concurrent.Future;
import kotlin.Metadata;
import kotlin.Unit;

@Metadata
/* loaded from: classes2.dex */
final class CancelFutureOnCompletion extends JobNode {

    /* renamed from: k, reason: collision with root package name */
    private final Future f18829k;

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object c(Object obj) {
        d0((Throwable) obj);
        return Unit.f18288a;
    }

    @Override // kotlinx.coroutines.CompletionHandlerBase
    public void d0(Throwable th) {
        if (th != null) {
            this.f18829k.cancel(false);
        }
    }
}
