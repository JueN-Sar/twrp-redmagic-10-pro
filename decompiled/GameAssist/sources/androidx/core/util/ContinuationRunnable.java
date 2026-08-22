package androidx.core.util;

import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;

@Metadata
/* loaded from: classes.dex */
final class ContinuationRunnable extends AtomicBoolean implements Runnable {

    @NotNull
    private final Continuation<Unit> continuation;

    /* JADX WARN: Multi-variable type inference failed */
    public ContinuationRunnable(@NotNull Continuation<? super Unit> continuation) {
        super(false);
        this.continuation = continuation;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (compareAndSet(false, true)) {
            Continuation<Unit> continuation = this.continuation;
            Result.Companion companion = Result.Companion;
            continuation.g(Result.b(Unit.f18288a));
        }
    }

    @Override // java.util.concurrent.atomic.AtomicBoolean
    public String toString() {
        return "ContinuationRunnable(ran = " + get() + ')';
    }
}
