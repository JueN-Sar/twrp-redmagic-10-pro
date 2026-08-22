package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata
/* loaded from: classes2.dex */
public abstract class JobNode extends CompletionHandlerBase implements DisposableHandle, Incomplete {

    /* renamed from: j, reason: collision with root package name */
    public JobSupport f18901j;

    @Override // kotlinx.coroutines.DisposableHandle
    public void dispose() {
        e0().P0(this);
    }

    public final JobSupport e0() {
        JobSupport jobSupport = this.f18901j;
        if (jobSupport != null) {
            return jobSupport;
        }
        Intrinsics.s("job");
        return null;
    }

    public final void f0(JobSupport jobSupport) {
        this.f18901j = jobSupport;
    }

    @Override // kotlinx.coroutines.Incomplete
    public NodeList i() {
        return null;
    }

    @Override // kotlinx.coroutines.Incomplete
    public boolean isActive() {
        return true;
    }

    @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
    public String toString() {
        return DebugStringsKt.a(this) + '@' + DebugStringsKt.b(this) + "[job@" + DebugStringsKt.b(e0()) + ']';
    }
}
