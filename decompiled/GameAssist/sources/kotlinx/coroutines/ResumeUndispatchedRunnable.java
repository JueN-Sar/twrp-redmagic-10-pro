package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Unit;

@Metadata
/* loaded from: classes2.dex */
final class ResumeUndispatchedRunnable implements Runnable {

    /* renamed from: c, reason: collision with root package name */
    private final CoroutineDispatcher f18925c;

    /* renamed from: h, reason: collision with root package name */
    private final CancellableContinuation f18926h;

    public ResumeUndispatchedRunnable(CoroutineDispatcher coroutineDispatcher, CancellableContinuation cancellableContinuation) {
        this.f18925c = coroutineDispatcher;
        this.f18926h = cancellableContinuation;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f18926h.H(this.f18925c, Unit.f18288a);
    }
}
