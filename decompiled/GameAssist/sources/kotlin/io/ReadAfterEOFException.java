package kotlin.io;

import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata
/* loaded from: classes2.dex */
public final class ReadAfterEOFException extends RuntimeException {
    public ReadAfterEOFException(@Nullable String str) {
        super(str);
    }
}
