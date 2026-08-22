package kotlinx.coroutines;

import kotlin.Metadata;
import kotlinx.coroutines.internal.LockFreeLinkedListHead;

@Metadata
/* loaded from: classes2.dex */
public final class NodeList extends LockFreeLinkedListHead implements Incomplete {
    @Override // kotlinx.coroutines.Incomplete
    public NodeList i() {
        return this;
    }

    @Override // kotlinx.coroutines.Incomplete
    public boolean isActive() {
        return true;
    }

    @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
    public String toString() {
        return super.toString();
    }
}
