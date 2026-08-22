package kotlinx.coroutines.internal;

import kotlin.Metadata;

@Metadata
/* loaded from: classes2.dex */
public final class LimitedDispatcherKt {
    public static final void a(int i2) {
        if (i2 >= 1) {
            return;
        }
        throw new IllegalArgumentException(("Expected positive parallelism level, but got " + i2).toString());
    }
}
