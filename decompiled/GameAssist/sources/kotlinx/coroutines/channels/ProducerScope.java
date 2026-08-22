package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlinx.coroutines.CoroutineScope;

@Metadata
/* loaded from: classes2.dex */
public interface ProducerScope<E> extends CoroutineScope, SendChannel<E> {

    @Metadata
    public static final class DefaultImpls {
    }

    SendChannel l();
}
