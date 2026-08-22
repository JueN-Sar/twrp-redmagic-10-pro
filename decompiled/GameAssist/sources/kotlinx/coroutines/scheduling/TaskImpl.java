package kotlinx.coroutines.scheduling;

import kotlin.Metadata;
import kotlinx.coroutines.DebugStringsKt;

@Metadata
/* loaded from: classes2.dex */
public final class TaskImpl extends Task {

    /* renamed from: i, reason: collision with root package name */
    public final Runnable f19463i;

    public TaskImpl(Runnable runnable, long j2, TaskContext taskContext) {
        super(j2, taskContext);
        this.f19463i = runnable;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.f19463i.run();
        } finally {
            this.f19461h.W();
        }
    }

    public String toString() {
        return "Task[" + DebugStringsKt.a(this.f19463i) + '@' + DebugStringsKt.b(this.f19463i) + ", " + this.f19460c + ", " + this.f19461h + ']';
    }
}
