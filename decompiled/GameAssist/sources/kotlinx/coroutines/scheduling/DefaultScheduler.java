package kotlinx.coroutines.scheduling;

import kotlin.Metadata;

@Metadata
/* loaded from: classes2.dex */
public final class DefaultScheduler extends SchedulerCoroutineDispatcher {

    /* renamed from: o, reason: collision with root package name */
    public static final DefaultScheduler f19446o = new DefaultScheduler();

    private DefaultScheduler() {
        super(TasksKt.f19465b, TasksKt.f19466c, TasksKt.f19467d, "DefaultDispatcher");
    }

    @Override // kotlinx.coroutines.scheduling.SchedulerCoroutineDispatcher, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        throw new UnsupportedOperationException("Dispatchers.Default cannot be closed");
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public String toString() {
        return "Dispatchers.Default";
    }
}
