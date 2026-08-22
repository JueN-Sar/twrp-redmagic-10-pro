package kotlinx.coroutines.scheduling;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;

@Metadata
/* loaded from: classes2.dex */
public class SchedulerCoroutineDispatcher extends ExecutorCoroutineDispatcher {

    /* renamed from: j, reason: collision with root package name */
    private final int f19455j;

    /* renamed from: k, reason: collision with root package name */
    private final int f19456k;

    /* renamed from: l, reason: collision with root package name */
    private final long f19457l;

    /* renamed from: m, reason: collision with root package name */
    private final String f19458m;

    /* renamed from: n, reason: collision with root package name */
    private CoroutineScheduler f19459n = n0();

    public SchedulerCoroutineDispatcher(int i2, int i3, long j2, String str) {
        this.f19455j = i2;
        this.f19456k = i3;
        this.f19457l = j2;
        this.f19458m = str;
    }

    private final CoroutineScheduler n0() {
        return new CoroutineScheduler(this.f19455j, this.f19456k, this.f19457l, this.f19458m);
    }

    public void close() {
        this.f19459n.close();
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public void j0(CoroutineContext coroutineContext, Runnable runnable) {
        CoroutineScheduler.j(this.f19459n, runnable, null, false, 6, null);
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public void k0(CoroutineContext coroutineContext, Runnable runnable) {
        CoroutineScheduler.j(this.f19459n, runnable, null, true, 2, null);
    }

    public final void o0(Runnable runnable, TaskContext taskContext, boolean z) {
        this.f19459n.i(runnable, taskContext, z);
    }
}
