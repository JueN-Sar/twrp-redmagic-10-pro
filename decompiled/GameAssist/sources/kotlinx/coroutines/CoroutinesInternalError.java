package kotlinx.coroutines;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata
/* loaded from: classes2.dex */
public final class CoroutinesInternalError extends Error {
    public CoroutinesInternalError(@NotNull String str, @NotNull Throwable th) {
        super(str, th);
    }
}
