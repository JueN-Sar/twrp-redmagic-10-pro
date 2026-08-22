package androidx.core.util;

import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;

@Metadata
/* loaded from: classes.dex */
final class AndroidXContinuationConsumer<T> extends AtomicBoolean implements Consumer<T> {

    @NotNull
    private final Continuation<T> continuation;

    /* JADX WARN: Multi-variable type inference failed */
    public AndroidXContinuationConsumer(@NotNull Continuation<? super T> continuation) {
        super(false);
        this.continuation = continuation;
    }

    @Override // androidx.core.util.Consumer
    public void accept(Object obj) {
        if (compareAndSet(false, true)) {
            this.continuation.g(Result.b(obj));
        }
    }

    @Override // java.util.concurrent.atomic.AtomicBoolean
    public String toString() {
        return "ContinuationConsumer(resultAccepted = " + get() + ')';
    }
}
