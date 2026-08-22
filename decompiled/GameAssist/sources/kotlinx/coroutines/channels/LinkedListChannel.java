package kotlinx.coroutines.channels;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.channels.AbstractSendChannel;
import kotlinx.coroutines.internal.AtomicKt;
import kotlinx.coroutines.internal.OnUndeliveredElementKt;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.internal.UndeliveredElementException;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.SelectKt;

@Metadata
/* loaded from: classes2.dex */
public class LinkedListChannel<E> extends AbstractChannel<E> {
    public LinkedListChannel(Function1 function1) {
        super(function1);
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    protected final boolean A() {
        return false;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    protected Object C(Object obj) {
        ReceiveOrClosed G;
        do {
            Object C = super.C(obj);
            Symbol symbol = AbstractChannelKt.f18972b;
            if (C == symbol) {
                return symbol;
            }
            if (C != AbstractChannelKt.f18973c) {
                if (C instanceof Closed) {
                    return C;
                }
                throw new IllegalStateException(("Invalid offerInternal result " + C).toString());
            }
            G = G(obj);
            if (G == null) {
                return symbol;
            }
        } while (!(G instanceof Closed));
        return G;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    protected Object D(Object obj, SelectInstance selectInstance) {
        Object C;
        while (true) {
            if (V()) {
                C = super.D(obj, selectInstance);
            } else {
                C = selectInstance.C(f(obj));
                if (C == null) {
                    C = AbstractChannelKt.f18972b;
                }
            }
            if (C == SelectKt.d()) {
                return SelectKt.d();
            }
            Symbol symbol = AbstractChannelKt.f18972b;
            if (C == symbol) {
                return symbol;
            }
            if (C != AbstractChannelKt.f18973c && C != AtomicKt.f19340b) {
                if (C instanceof Closed) {
                    return C;
                }
                throw new IllegalStateException(("Invalid result " + C).toString());
            }
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    protected final boolean W() {
        return true;
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    protected final boolean X() {
        return true;
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    protected void b0(Object obj, Closed closed) {
        UndeliveredElementException undeliveredElementException = null;
        if (obj != null) {
            if (obj instanceof ArrayList) {
                ArrayList arrayList = (ArrayList) obj;
                UndeliveredElementException undeliveredElementException2 = null;
                for (int size = arrayList.size() - 1; -1 < size; size--) {
                    Send send = (Send) arrayList.get(size);
                    if (send instanceof AbstractSendChannel.SendBuffered) {
                        Function1 function1 = this.f18978c;
                        undeliveredElementException2 = function1 != null ? OnUndeliveredElementKt.c(function1, ((AbstractSendChannel.SendBuffered) send).f18981j, undeliveredElementException2) : null;
                    } else {
                        send.f0(closed);
                    }
                }
                undeliveredElementException = undeliveredElementException2;
            } else {
                Send send2 = (Send) obj;
                if (send2 instanceof AbstractSendChannel.SendBuffered) {
                    Function1 function12 = this.f18978c;
                    if (function12 != null) {
                        undeliveredElementException = OnUndeliveredElementKt.c(function12, ((AbstractSendChannel.SendBuffered) send2).f18981j, null);
                    }
                } else {
                    send2.f0(closed);
                }
            }
        }
        if (undeliveredElementException != null) {
            throw undeliveredElementException;
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    protected final boolean v() {
        return false;
    }
}
