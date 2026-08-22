package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.PublishedApi;

@Metadata
@PublishedApi
/* loaded from: classes2.dex */
public class JobImpl extends JobSupport implements CompletableJob {

    /* renamed from: h, reason: collision with root package name */
    private final boolean f18900h;

    public JobImpl(Job job) {
        super(true);
        x0(job);
        this.f18900h = c1();
    }

    private final boolean c1() {
        JobSupport e0;
        ChildHandle t0 = t0();
        ChildHandleNode childHandleNode = t0 instanceof ChildHandleNode ? (ChildHandleNode) t0 : null;
        if (childHandleNode != null && (e0 = childHandleNode.e0()) != null) {
            while (!e0.q0()) {
                ChildHandle t02 = e0.t0();
                ChildHandleNode childHandleNode2 = t02 instanceof ChildHandleNode ? (ChildHandleNode) t02 : null;
                if (childHandleNode2 != null && (e0 = childHandleNode2.e0()) != null) {
                }
            }
            return true;
        }
        return false;
    }

    @Override // kotlinx.coroutines.JobSupport
    public boolean q0() {
        return this.f18900h;
    }

    @Override // kotlinx.coroutines.JobSupport
    public boolean r0() {
        return true;
    }
}
