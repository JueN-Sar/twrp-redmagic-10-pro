package kotlinx.coroutines.selects;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.InternalCoroutinesApi;
import kotlinx.coroutines.internal.AtomicDesc;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;

@InternalCoroutinesApi
@Metadata
/* loaded from: classes2.dex */
public interface SelectInstance<R> {
    void A(Throwable th);

    Object C(AtomicDesc atomicDesc);

    boolean k();

    void s(DisposableHandle disposableHandle);

    Object v(LockFreeLinkedListNode.PrepareOp prepareOp);

    boolean w();

    Continuation x();
}
