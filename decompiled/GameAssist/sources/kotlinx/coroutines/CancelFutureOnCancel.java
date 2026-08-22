package kotlinx.coroutines;

import java.util.concurrent.Future;
import kotlin.Metadata;
import kotlin.Unit;

@Metadata
/* loaded from: classes2.dex */
final class CancelFutureOnCancel extends CancelHandler {

    /* renamed from: c, reason: collision with root package name */
    private final Future f18828c;

    public CancelFutureOnCancel(Future future) {
        this.f18828c = future;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object c(Object obj) {
        d((Throwable) obj);
        return Unit.f18288a;
    }

    @Override // kotlinx.coroutines.CancelHandlerBase
    public void d(Throwable th) {
        if (th != null) {
            this.f18828c.cancel(false);
        }
    }

    public String toString() {
        return "CancelFutureOnCancel[" + this.f18828c + ']';
    }
}
