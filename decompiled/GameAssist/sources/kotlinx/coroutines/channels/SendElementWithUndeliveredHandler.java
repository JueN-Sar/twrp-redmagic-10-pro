package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.internal.OnUndeliveredElementKt;

@Metadata
/* loaded from: classes2.dex */
public final class SendElementWithUndeliveredHandler<E> extends SendElement<E> {

    /* renamed from: l, reason: collision with root package name */
    public final Function1 f19029l;

    public SendElementWithUndeliveredHandler(Object obj, CancellableContinuation cancellableContinuation, Function1 function1) {
        super(obj, cancellableContinuation);
        this.f19029l = function1;
    }

    @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
    public boolean Y() {
        if (!super.Y()) {
            return false;
        }
        h0();
        return true;
    }

    @Override // kotlinx.coroutines.channels.Send
    public void h0() {
        OnUndeliveredElementKt.b(this.f19029l, e0(), this.f19028k.getContext());
    }
}
