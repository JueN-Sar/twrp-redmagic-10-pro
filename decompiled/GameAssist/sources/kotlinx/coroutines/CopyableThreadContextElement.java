package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;

@Metadata
@DelicateCoroutinesApi
@ExperimentalCoroutinesApi
/* loaded from: classes2.dex */
public interface CopyableThreadContextElement<S> extends ThreadContextElement<S> {

    @Metadata
    public static final class DefaultImpls {
    }

    CopyableThreadContextElement C();

    CoroutineContext j(CoroutineContext.Element element);
}
