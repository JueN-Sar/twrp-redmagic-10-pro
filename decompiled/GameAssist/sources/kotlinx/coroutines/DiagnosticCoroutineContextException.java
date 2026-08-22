package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import org.jetbrains.annotations.NotNull;

@Metadata
/* loaded from: classes2.dex */
final class DiagnosticCoroutineContextException extends RuntimeException {

    @NotNull
    private final CoroutineContext context;

    public DiagnosticCoroutineContextException(@NotNull CoroutineContext coroutineContext) {
        this.context = coroutineContext;
    }

    @Override // java.lang.Throwable
    public Throwable fillInStackTrace() {
        setStackTrace(new StackTraceElement[0]);
        return this;
    }

    @Override // java.lang.Throwable
    public String getLocalizedMessage() {
        return this.context.toString();
    }
}
