package kotlinx.coroutines;

import java.util.concurrent.locks.LockSupport;
import kotlin.Metadata;
import kotlin.Unit;
import kotlinx.coroutines.EventLoopImplBase;

@Metadata
/* loaded from: classes2.dex */
public abstract class EventLoopImplPlatform extends EventLoop {
    protected abstract Thread w0();

    protected void x0(long j2, EventLoopImplBase.DelayedTask delayedTask) {
        DefaultExecutor.f18861n.I0(j2, delayedTask);
    }

    protected final void y0() {
        Unit unit;
        Thread w0 = w0();
        if (Thread.currentThread() != w0) {
            AbstractTimeSource a2 = AbstractTimeSourceKt.a();
            if (a2 != null) {
                a2.f(w0);
                unit = Unit.f18288a;
            } else {
                unit = null;
            }
            if (unit == null) {
                LockSupport.unpark(w0);
            }
        }
    }
}
