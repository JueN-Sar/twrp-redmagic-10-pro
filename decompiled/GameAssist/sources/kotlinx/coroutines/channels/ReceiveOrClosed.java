package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.Symbol;

@Metadata
/* loaded from: classes2.dex */
public interface ReceiveOrClosed<E> {
    Symbol F(Object obj, LockFreeLinkedListNode.PrepareOp prepareOp);

    Object e();

    void p(Object obj);
}
