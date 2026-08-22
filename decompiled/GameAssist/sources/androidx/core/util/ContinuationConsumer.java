package androidx.core.util;

import androidx.annotation.RequiresApi;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;

@RequiresApi
@Metadata
/* loaded from: classes.dex */
final class ContinuationConsumer<T> extends AtomicBoolean implements java.util.function.Consumer<T> {

    @NotNull
    private final Continuation<T> continuation;

    /* JADX WARN: Multi-variable type inference failed */
    public ContinuationConsumer(@NotNull Continuation<? super T> continuation) {
        super(false);
        this.continuation = continuation;
    }

    @Override // java.util.function.Consumer
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
