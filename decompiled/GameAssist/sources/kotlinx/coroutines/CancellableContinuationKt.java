package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.internal.DispatchedContinuation;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;

@Metadata
/* loaded from: classes2.dex */
public final class CancellableContinuationKt {
    public static final void a(CancellableContinuation cancellableContinuation, DisposableHandle disposableHandle) {
        cancellableContinuation.m(new DisposeOnCancel(disposableHandle));
    }

    public static final CancellableContinuationImpl b(Continuation continuation) {
        if (!(continuation instanceof DispatchedContinuation)) {
            return new CancellableContinuationImpl(continuation, 1);
        }
        CancellableContinuationImpl k2 = ((DispatchedContinuation) continuation).k();
        if (k2 != null) {
            if (!k2.L()) {
                k2 = null;
            }
            if (k2 != null) {
                return k2;
            }
        }
        return new CancellableContinuationImpl(continuation, 2);
    }

    public static final void c(CancellableContinuation cancellableContinuation, LockFreeLinkedListNode lockFreeLinkedListNode) {
        cancellableContinuation.m(new RemoveOnCancel(lockFreeLinkedListNode));
    }
}
