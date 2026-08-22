package kotlinx.coroutines.scheduling;

import kotlin.Metadata;

@Metadata
/* loaded from: classes2.dex */
public abstract class Task implements Runnable {

    /* renamed from: c, reason: collision with root package name */
    public long f19460c;

    /* renamed from: h, reason: collision with root package name */
    public TaskContext f19461h;

    public Task(long j2, TaskContext taskContext) {
        this.f19460c = j2;
        this.f19461h = taskContext;
    }

    public Task() {
        this(0L, TasksKt.f19469f);
    }
}
