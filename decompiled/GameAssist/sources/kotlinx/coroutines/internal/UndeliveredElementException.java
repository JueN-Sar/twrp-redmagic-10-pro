package kotlinx.coroutines.internal;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata
/* loaded from: classes2.dex */
public final class UndeliveredElementException extends RuntimeException {
    public UndeliveredElementException(@NotNull String str, @NotNull Throwable th) {
        super(str, th);
    }
}
