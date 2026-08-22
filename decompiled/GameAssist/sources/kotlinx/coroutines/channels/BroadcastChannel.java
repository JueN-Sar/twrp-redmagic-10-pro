package kotlinx.coroutines.channels;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlinx.coroutines.ObsoleteCoroutinesApi;

@ObsoleteCoroutinesApi
@Metadata
/* loaded from: classes2.dex */
public interface BroadcastChannel<E> extends SendChannel<E> {

    @Metadata
    public static final class DefaultImpls {
    }

    void a(CancellationException cancellationException);

    ReceiveChannel q();
}
