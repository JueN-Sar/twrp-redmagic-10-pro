package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.ObsoleteCoroutinesApi;

@ObsoleteCoroutinesApi
@Metadata
/* loaded from: classes2.dex */
public interface ActorScope<E> extends CoroutineScope, ReceiveChannel<E> {

    @Metadata
    public static final class DefaultImpls {
    }
}
