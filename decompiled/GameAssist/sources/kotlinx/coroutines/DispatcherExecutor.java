package kotlinx.coroutines;

import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.coroutines.EmptyCoroutineContext;

@Metadata
/* loaded from: classes2.dex */
final class DispatcherExecutor implements Executor {

    /* renamed from: c, reason: collision with root package name */
    public final CoroutineDispatcher f18867c;

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        this.f18867c.j0(EmptyCoroutineContext.INSTANCE, runnable);
    }

    public String toString() {
        return this.f18867c.toString();
    }
}
