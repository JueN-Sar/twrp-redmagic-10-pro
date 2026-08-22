package kotlin;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata
/* loaded from: classes2.dex */
public final class NotImplementedError extends Error {
    /* JADX WARN: Multi-variable type inference failed */
    public NotImplementedError() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NotImplementedError(@NotNull String message) {
        super(message);
        Intrinsics.e(message, "message");
    }

    public /* synthetic */ NotImplementedError(String str, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "An operation is not implemented." : str);
    }
}
