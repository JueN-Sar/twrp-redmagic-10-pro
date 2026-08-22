package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata
/* loaded from: classes2.dex */
public final class TimeoutCancellationException extends CancellationException implements CopyableThrowable<TimeoutCancellationException> {

    @JvmField
    @Nullable
    public final transient Job coroutine;

    public TimeoutCancellationException(@NotNull String str, @Nullable Job job) {
        super(str);
        this.coroutine = job;
    }

    public TimeoutCancellationException(@NotNull String str) {
        this(str, null);
    }
}
