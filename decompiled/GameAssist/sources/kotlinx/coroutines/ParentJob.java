package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.Metadata;

@Deprecated
@InternalCoroutinesApi
@Metadata
/* loaded from: classes2.dex */
public interface ParentJob extends Job {

    @Metadata
    public static final class DefaultImpls {
    }

    CancellationException I();
}
