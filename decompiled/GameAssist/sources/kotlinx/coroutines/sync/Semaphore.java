package kotlinx.coroutines.sync;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;

@Metadata
/* loaded from: classes2.dex */
public interface Semaphore {
    Object a(Continuation continuation);

    void release();
}
