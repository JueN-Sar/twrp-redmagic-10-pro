package kotlinx.coroutines;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@InternalCoroutinesApi
@Metadata
/* loaded from: classes2.dex */
public final class CompletionHandlerException extends RuntimeException {
    public CompletionHandlerException(@NotNull String str, @NotNull Throwable th) {
        super(str, th);
    }
}
