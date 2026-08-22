package kotlinx.coroutines;

import java.util.concurrent.Future;
import kotlin.Metadata;

@Metadata
/* loaded from: classes2.dex */
final class DisposableFutureHandle implements DisposableHandle {

    /* renamed from: c, reason: collision with root package name */
    private final Future f18872c;

    public DisposableFutureHandle(Future future) {
        this.f18872c = future;
    }

    @Override // kotlinx.coroutines.DisposableHandle
    public void dispose() {
        this.f18872c.cancel(false);
    }

    public String toString() {
        return "DisposableFutureHandle[" + this.f18872c + ']';
    }
}
