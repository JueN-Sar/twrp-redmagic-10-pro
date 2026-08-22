package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Unit;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;

@Metadata
/* loaded from: classes2.dex */
final class RemoveOnCancel extends BeforeResumeCancelHandler {

    /* renamed from: c, reason: collision with root package name */
    private final LockFreeLinkedListNode f18922c;

    public RemoveOnCancel(LockFreeLinkedListNode lockFreeLinkedListNode) {
        this.f18922c = lockFreeLinkedListNode;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object c(Object obj) {
        d((Throwable) obj);
        return Unit.f18288a;
    }

    @Override // kotlinx.coroutines.CancelHandlerBase
    public void d(Throwable th) {
        this.f18922c.Y();
    }

    public String toString() {
        return "RemoveOnCancel[" + this.f18922c + ']';
    }
}
