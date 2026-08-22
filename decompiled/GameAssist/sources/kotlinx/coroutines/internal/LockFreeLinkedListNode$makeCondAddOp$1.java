package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;

@Metadata
/* loaded from: classes2.dex */
public final class LockFreeLinkedListNode$makeCondAddOp$1 extends LockFreeLinkedListNode.CondAddOp {

    /* renamed from: d, reason: collision with root package name */
    final /* synthetic */ Function0 f19381d;

    @Override // kotlinx.coroutines.internal.AtomicOp
    /* renamed from: k, reason: merged with bridge method [inline-methods] */
    public Object i(LockFreeLinkedListNode lockFreeLinkedListNode) {
        if (((Boolean) this.f19381d.a()).booleanValue()) {
            return null;
        }
        return LockFreeLinkedListKt.a();
    }
}
