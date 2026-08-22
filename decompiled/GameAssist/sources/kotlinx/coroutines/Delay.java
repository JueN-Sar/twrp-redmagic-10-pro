package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;

@InternalCoroutinesApi
@Metadata
/* loaded from: classes2.dex */
public interface Delay {

    @Metadata
    public static final class DefaultImpls {
        public static DisposableHandle a(Delay delay, long j2, Runnable runnable, CoroutineContext coroutineContext) {
            return DefaultExecutorKt.a().B(j2, runnable, coroutineContext);
        }
    }

    DisposableHandle B(long j2, Runnable runnable, CoroutineContext coroutineContext);

    void k(long j2, CancellableContinuation cancellableContinuation);
}
