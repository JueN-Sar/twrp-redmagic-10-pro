package kotlinx.coroutines.sync;

import kotlin.Metadata;
import kotlin.Unit;
import kotlinx.coroutines.CancelHandler;

@Metadata
/* loaded from: classes2.dex */
final class CancelSemaphoreAcquisitionHandler extends CancelHandler {

    /* renamed from: c, reason: collision with root package name */
    private final SemaphoreSegment f19496c;

    /* renamed from: h, reason: collision with root package name */
    private final int f19497h;

    public CancelSemaphoreAcquisitionHandler(SemaphoreSegment semaphoreSegment, int i2) {
        this.f19496c = semaphoreSegment;
        this.f19497h = i2;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object c(Object obj) {
        d((Throwable) obj);
        return Unit.f18288a;
    }

    @Override // kotlinx.coroutines.CancelHandlerBase
    public void d(Throwable th) {
        this.f19496c.q(this.f19497h);
    }

    public String toString() {
        return "CancelSemaphoreAcquisitionHandler[" + this.f19496c + ", " + this.f19497h + ']';
    }
}
