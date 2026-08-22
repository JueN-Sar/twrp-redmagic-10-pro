package kotlinx.coroutines.channels;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlinx.coroutines.CoroutineExceptionHandlerKt;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.ExceptionsKt;

@Metadata
/* loaded from: classes2.dex */
class ActorCoroutine<E> extends ChannelCoroutine<E> implements ActorScope<E> {
    @Override // kotlinx.coroutines.JobSupport
    protected void J0(Throwable th) {
        Channel h1 = h1();
        if (th != null) {
            r1 = th instanceof CancellationException ? (CancellationException) th : null;
            if (r1 == null) {
                r1 = ExceptionsKt.a(DebugStringsKt.a(this) + " was cancelled", th);
            }
        }
        h1.a(r1);
    }

    @Override // kotlinx.coroutines.JobSupport
    protected boolean v0(Throwable th) {
        CoroutineExceptionHandlerKt.a(getContext(), th);
        return true;
    }
}
