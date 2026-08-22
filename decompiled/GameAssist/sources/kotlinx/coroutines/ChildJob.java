package kotlinx.coroutines;

import kotlin.Deprecated;
import kotlin.Metadata;

@Deprecated
@InternalCoroutinesApi
@Metadata
/* loaded from: classes2.dex */
public interface ChildJob extends Job {

    @Metadata
    public static final class DefaultImpls {
    }

    void t(ParentJob parentJob);
}
