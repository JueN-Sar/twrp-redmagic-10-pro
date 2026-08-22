package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImplKt;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.Symbol;

@Metadata
/* loaded from: classes2.dex */
public class SendElement<E> extends Send {

    /* renamed from: j, reason: collision with root package name */
    private final Object f19027j;

    /* renamed from: k, reason: collision with root package name */
    public final CancellableContinuation f19028k;

    public SendElement(Object obj, CancellableContinuation cancellableContinuation) {
        this.f19027j = obj;
        this.f19028k = cancellableContinuation;
    }

    @Override // kotlinx.coroutines.channels.Send
    public void d0() {
        this.f19028k.O(CancellableContinuationImplKt.f18835a);
    }

    @Override // kotlinx.coroutines.channels.Send
    public Object e0() {
        return this.f19027j;
    }

    @Override // kotlinx.coroutines.channels.Send
    public void f0(Closed closed) {
        CancellableContinuation cancellableContinuation = this.f19028k;
        Result.Companion companion = Result.Companion;
        cancellableContinuation.g(Result.b(ResultKt.a(closed.l0())));
    }

    @Override // kotlinx.coroutines.channels.Send
    public Symbol g0(LockFreeLinkedListNode.PrepareOp prepareOp) {
        if (this.f19028k.d(Unit.f18288a, prepareOp != null ? prepareOp.f19377c : null) == null) {
            return null;
        }
        if (prepareOp != null) {
            prepareOp.d();
        }
        return CancellableContinuationImplKt.f18835a;
    }

    @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
    public String toString() {
        return DebugStringsKt.a(this) + '@' + DebugStringsKt.b(this) + '(' + e0() + ')';
    }
}
