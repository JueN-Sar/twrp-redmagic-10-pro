package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Unit;

@Metadata
/* loaded from: classes2.dex */
public final class ChildContinuation extends JobCancellingNode {

    /* renamed from: k, reason: collision with root package name */
    public final CancellableContinuationImpl f18837k;

    public ChildContinuation(CancellableContinuationImpl cancellableContinuationImpl) {
        this.f18837k = cancellableContinuationImpl;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object c(Object obj) {
        d0((Throwable) obj);
        return Unit.f18288a;
    }

    @Override // kotlinx.coroutines.CompletionHandlerBase
    public void d0(Throwable th) {
        CancellableContinuationImpl cancellableContinuationImpl = this.f18837k;
        cancellableContinuationImpl.J(cancellableContinuationImpl.v(e0()));
    }
}
