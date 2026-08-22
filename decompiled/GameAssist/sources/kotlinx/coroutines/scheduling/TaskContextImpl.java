package kotlinx.coroutines.scheduling;

import kotlin.Metadata;

@Metadata
/* loaded from: classes2.dex */
final class TaskContextImpl implements TaskContext {

    /* renamed from: c, reason: collision with root package name */
    private final int f19462c;

    public TaskContextImpl(int i2) {
        this.f19462c = i2;
    }

    @Override // kotlinx.coroutines.scheduling.TaskContext
    public void W() {
    }

    @Override // kotlinx.coroutines.scheduling.TaskContext
    public int a0() {
        return this.f19462c;
    }
}
