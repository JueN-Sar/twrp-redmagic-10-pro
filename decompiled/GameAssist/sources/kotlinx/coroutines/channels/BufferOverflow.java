package kotlinx.coroutines.channels;

import kotlin.Metadata;

@Metadata
/* loaded from: classes2.dex */
public enum BufferOverflow {
    SUSPEND,
    DROP_OLDEST,
    DROP_LATEST
}
