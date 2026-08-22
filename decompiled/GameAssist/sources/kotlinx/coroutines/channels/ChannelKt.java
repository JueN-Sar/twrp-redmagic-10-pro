package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

@Metadata
/* loaded from: classes2.dex */
public final class ChannelKt {
    public static final Channel a(int i2, BufferOverflow bufferOverflow, Function1 function1) {
        if (i2 == -2) {
            return new ArrayChannel(bufferOverflow == BufferOverflow.SUSPEND ? Channel.f19001g.a() : 1, bufferOverflow, function1);
        }
        if (i2 != -1) {
            return i2 != 0 ? i2 != Integer.MAX_VALUE ? (i2 == 1 && bufferOverflow == BufferOverflow.DROP_OLDEST) ? new ConflatedChannel(function1) : new ArrayChannel(i2, bufferOverflow, function1) : new LinkedListChannel(function1) : bufferOverflow == BufferOverflow.SUSPEND ? new RendezvousChannel(function1) : new ArrayChannel(1, bufferOverflow, function1);
        }
        if (bufferOverflow == BufferOverflow.SUSPEND) {
            return new ConflatedChannel(function1);
        }
        throw new IllegalArgumentException("CONFLATED capacity cannot be used with non-default onBufferOverflow".toString());
    }

    public static /* synthetic */ Channel b(int i2, BufferOverflow bufferOverflow, Function1 function1, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i2 = 0;
        }
        if ((i3 & 2) != 0) {
            bufferOverflow = BufferOverflow.SUSPEND;
        }
        if ((i3 & 4) != 0) {
            function1 = null;
        }
        return a(i2, bufferOverflow, function1);
    }
}
