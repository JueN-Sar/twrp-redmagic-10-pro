package androidx.core.os;

import android.os.OutcomeReceiver;
import androidx.annotation.RequiresApi;
import java.lang.Throwable;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;

@RequiresApi
@Metadata
/* loaded from: classes.dex */
final class ContinuationOutcomeReceiver<R, E extends Throwable> extends AtomicBoolean implements OutcomeReceiver<R, E> {

    @NotNull
    private final Continuation<R> continuation;

    /* JADX WARN: Multi-variable type inference failed */
    public ContinuationOutcomeReceiver(@NotNull Continuation<? super R> continuation) {
        super(false);
        this.continuation = continuation;
    }

    @Override // android.os.OutcomeReceiver
    public void onError(Throwable th) {
        if (compareAndSet(false, true)) {
            Continuation<R> continuation = this.continuation;
            Result.Companion companion = Result.Companion;
            continuation.g(Result.b(ResultKt.a(th)));
        }
    }

    @Override // android.os.OutcomeReceiver
    public void onResult(Object obj) {
        if (compareAndSet(false, true)) {
            this.continuation.g(Result.b(obj));
        }
    }

    @Override // java.util.concurrent.atomic.AtomicBoolean
    public String toString() {
        return "ContinuationOutcomeReceiver(outcomeReceived = " + get() + ')';
    }
}
