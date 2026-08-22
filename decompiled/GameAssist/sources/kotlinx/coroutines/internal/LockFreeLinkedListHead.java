package kotlinx.coroutines.internal;

import kotlin.Metadata;

@Metadata
/* loaded from: classes2.dex */
public class LockFreeLinkedListHead extends LockFreeLinkedListNode {
    @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
    public boolean X() {
        return false;
    }

    @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
    public /* bridge */ /* synthetic */ boolean Y() {
        return ((Boolean) e0()).booleanValue();
    }

    public final boolean d0() {
        return S() == this;
    }

    public final Void e0() {
        throw new IllegalStateException("head cannot be removed".toString());
    }
}
