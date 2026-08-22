package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.internal.ScopeCoroutine;

@Metadata
/* loaded from: classes2.dex */
final class FlowCoroutine<T> extends ScopeCoroutine<T> {
    public FlowCoroutine(CoroutineContext coroutineContext, Continuation continuation) {
        super(coroutineContext, continuation);
    }

    @Override // kotlinx.coroutines.JobSupport
    public boolean g0(Throwable th) {
        if (th instanceof ChildCancelledException) {
            return true;
        }
        return Z(th);
    }
}
