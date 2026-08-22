package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlinx.coroutines.CancellableContinuationImplKt;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.Symbol;

@Metadata
/* loaded from: classes2.dex */
public final class Closed<E> extends Send implements ReceiveOrClosed<E> {

    /* renamed from: j, reason: collision with root package name */
    public final Throwable f19009j;

    public Closed(Throwable th) {
        this.f19009j = th;
    }

    @Override // kotlinx.coroutines.channels.ReceiveOrClosed
    public Symbol F(Object obj, LockFreeLinkedListNode.PrepareOp prepareOp) {
        Symbol symbol = CancellableContinuationImplKt.f18835a;
        if (prepareOp != null) {
            prepareOp.d();
        }
        return symbol;
    }

    @Override // kotlinx.coroutines.channels.Send
    public void d0() {
    }

    @Override // kotlinx.coroutines.channels.Send
    public void f0(Closed closed) {
    }

    @Override // kotlinx.coroutines.channels.Send
    public Symbol g0(LockFreeLinkedListNode.PrepareOp prepareOp) {
        Symbol symbol = CancellableContinuationImplKt.f18835a;
        if (prepareOp != null) {
            prepareOp.d();
        }
        return symbol;
    }

    @Override // kotlinx.coroutines.channels.ReceiveOrClosed
    /* renamed from: i0, reason: merged with bridge method [inline-methods] */
    public Closed e() {
        return this;
    }

    @Override // kotlinx.coroutines.channels.Send
    /* renamed from: j0, reason: merged with bridge method [inline-methods] */
    public Closed e0() {
        return this;
    }

    public final Throwable k0() {
        Throwable th = this.f19009j;
        return th == null ? new ClosedReceiveChannelException("Channel was closed") : th;
    }

    public final Throwable l0() {
        Throwable th = this.f19009j;
        return th == null ? new ClosedSendChannelException("Channel was closed") : th;
    }

    @Override // kotlinx.coroutines.channels.ReceiveOrClosed
    public void p(Object obj) {
    }

    @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
    public String toString() {
        return "Closed@" + DebugStringsKt.b(this) + '[' + this.f19009j + ']';
    }
}
