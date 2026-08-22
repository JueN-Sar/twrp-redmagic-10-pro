package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.Symbol;

@Metadata
/* loaded from: classes2.dex */
public abstract class Receive<E> extends LockFreeLinkedListNode implements ReceiveOrClosed<E> {
    @Override // kotlinx.coroutines.channels.ReceiveOrClosed
    /* renamed from: d0, reason: merged with bridge method [inline-methods] */
    public Symbol e() {
        return AbstractChannelKt.f18972b;
    }

    public Function1 e0(Object obj) {
        return null;
    }

    public abstract void f0(Closed closed);
}
