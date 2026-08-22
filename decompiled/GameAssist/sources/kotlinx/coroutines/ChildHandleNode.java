package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Unit;

@Metadata
/* loaded from: classes2.dex */
public final class ChildHandleNode extends JobCancellingNode implements ChildHandle {

    /* renamed from: k, reason: collision with root package name */
    public final ChildJob f18838k;

    public ChildHandleNode(ChildJob childJob) {
        this.f18838k = childJob;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object c(Object obj) {
        d0((Throwable) obj);
        return Unit.f18288a;
    }

    @Override // kotlinx.coroutines.CompletionHandlerBase
    public void d0(Throwable th) {
        this.f18838k.t(e0());
    }

    @Override // kotlinx.coroutines.ChildHandle
    public Job getParent() {
        return e0();
    }

    @Override // kotlinx.coroutines.ChildHandle
    public boolean h(Throwable th) {
        return e0().g0(th);
    }
}
