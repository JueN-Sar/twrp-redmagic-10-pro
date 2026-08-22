package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot;

@Metadata
/* loaded from: classes2.dex */
public final class SharedFlowSlot extends AbstractSharedFlowSlot<SharedFlowImpl<?>> {

    /* renamed from: a, reason: collision with root package name */
    public long f19267a = -1;

    /* renamed from: b, reason: collision with root package name */
    public Continuation f19268b;

    @Override // kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public boolean a(SharedFlowImpl sharedFlowImpl) {
        if (this.f19267a >= 0) {
            return false;
        }
        this.f19267a = sharedFlowImpl.Y();
        return true;
    }

    @Override // kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public Continuation[] b(SharedFlowImpl sharedFlowImpl) {
        long j2 = this.f19267a;
        this.f19267a = -1L;
        this.f19268b = null;
        return sharedFlowImpl.X(j2);
    }
}
