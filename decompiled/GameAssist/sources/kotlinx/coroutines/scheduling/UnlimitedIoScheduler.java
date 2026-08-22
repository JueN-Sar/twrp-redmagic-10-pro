package kotlinx.coroutines.scheduling;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineDispatcher;

@Metadata
/* loaded from: classes2.dex */
final class UnlimitedIoScheduler extends CoroutineDispatcher {

    /* renamed from: i, reason: collision with root package name */
    public static final UnlimitedIoScheduler f19471i = new UnlimitedIoScheduler();

    private UnlimitedIoScheduler() {
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public void j0(CoroutineContext coroutineContext, Runnable runnable) {
        DefaultScheduler.f19446o.o0(runnable, TasksKt.f19470g, false);
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public void k0(CoroutineContext coroutineContext, Runnable runnable) {
        DefaultScheduler.f19446o.o0(runnable, TasksKt.f19470g, true);
    }
}
