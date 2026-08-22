package kotlinx.coroutines;

import kotlin.Metadata;

@Metadata
/* loaded from: classes2.dex */
public final class EventLoopKt {
    public static final EventLoop a() {
        return new BlockingEventLoop(Thread.currentThread());
    }
}
