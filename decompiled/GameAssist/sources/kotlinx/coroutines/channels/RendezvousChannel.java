package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

@Metadata
/* loaded from: classes2.dex */
public class RendezvousChannel<E> extends AbstractChannel<E> {
    public RendezvousChannel(Function1 function1) {
        super(function1);
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    protected final boolean A() {
        return true;
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    protected final boolean W() {
        return true;
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    protected final boolean X() {
        return true;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    protected final boolean v() {
        return true;
    }
}
