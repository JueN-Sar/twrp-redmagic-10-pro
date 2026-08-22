package kotlinx.coroutines.sync;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;

@Metadata
/* loaded from: classes2.dex */
public interface Mutex {

    @Metadata
    public static final class DefaultImpls {
    }

    Object a(Object obj, Continuation continuation);

    void b(Object obj);
}
