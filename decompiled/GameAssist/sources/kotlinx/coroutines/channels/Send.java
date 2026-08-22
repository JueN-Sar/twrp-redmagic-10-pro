package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.Symbol;

@Metadata
/* loaded from: classes2.dex */
public abstract class Send extends LockFreeLinkedListNode {
    public abstract void d0();

    public abstract Object e0();

    public abstract void f0(Closed closed);

    public abstract Symbol g0(LockFreeLinkedListNode.PrepareOp prepareOp);

    public void h0() {
    }
}
