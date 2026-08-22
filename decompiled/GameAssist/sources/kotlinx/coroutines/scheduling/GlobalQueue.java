package kotlinx.coroutines.scheduling;

import kotlin.Metadata;
import kotlinx.coroutines.internal.LockFreeTaskQueue;

@Metadata
/* loaded from: classes2.dex */
public final class GlobalQueue extends LockFreeTaskQueue<Task> {
    public GlobalQueue() {
        super(false);
    }
}
