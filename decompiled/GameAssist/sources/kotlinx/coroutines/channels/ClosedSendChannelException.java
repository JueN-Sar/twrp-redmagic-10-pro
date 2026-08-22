package kotlinx.coroutines.channels;

import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata
/* loaded from: classes2.dex */
public final class ClosedSendChannelException extends IllegalStateException {
    public ClosedSendChannelException(@Nullable String str) {
        super(str);
    }
}
