package kotlinx.coroutines;

import kotlin.Metadata;

@Metadata
/* loaded from: classes2.dex */
public final class ThreadLocalEventLoop {

    /* renamed from: a, reason: collision with root package name */
    public static final ThreadLocalEventLoop f18932a = new ThreadLocalEventLoop();

    /* renamed from: b, reason: collision with root package name */
    private static final ThreadLocal f18933b = new ThreadLocal();

    private ThreadLocalEventLoop() {
    }

    public final EventLoop a() {
        ThreadLocal threadLocal = f18933b;
        EventLoop eventLoop = (EventLoop) threadLocal.get();
        if (eventLoop != null) {
            return eventLoop;
        }
        EventLoop a2 = EventLoopKt.a();
        threadLocal.set(a2);
        return a2;
    }

    public final void b() {
        f18933b.set(null);
    }

    public final void c(EventLoop eventLoop) {
        f18933b.set(eventLoop);
    }
}
