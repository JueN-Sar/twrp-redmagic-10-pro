package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlinx.coroutines.InternalCoroutinesApi;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.Flow;

@InternalCoroutinesApi
@Metadata
/* loaded from: classes2.dex */
public interface FusibleFlow<T> extends Flow<T> {

    @Metadata
    public static final class DefaultImpls {
        public static /* synthetic */ Flow a(FusibleFlow fusibleFlow, CoroutineContext coroutineContext, int i2, BufferOverflow bufferOverflow, int i3, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: fuse");
            }
            if ((i3 & 1) != 0) {
                coroutineContext = EmptyCoroutineContext.INSTANCE;
            }
            if ((i3 & 2) != 0) {
                i2 = -3;
            }
            if ((i3 & 4) != 0) {
                bufferOverflow = BufferOverflow.SUSPEND;
            }
            return fusibleFlow.b(coroutineContext, i2, bufferOverflow);
        }
    }

    Flow b(CoroutineContext coroutineContext, int i2, BufferOverflow bufferOverflow);
}
