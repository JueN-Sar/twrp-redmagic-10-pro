package kotlinx.coroutines;

import kotlin.Metadata;

@Metadata
/* loaded from: classes2.dex */
public final class BlockingEventLoop extends EventLoopImplBase {

    /* renamed from: n, reason: collision with root package name */
    private final Thread f18827n;

    public BlockingEventLoop(Thread thread) {
        this.f18827n = thread;
    }

    @Override // kotlinx.coroutines.EventLoopImplPlatform
    protected Thread w0() {
        return this.f18827n;
    }
}
