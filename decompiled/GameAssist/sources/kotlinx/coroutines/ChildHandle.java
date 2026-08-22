package kotlinx.coroutines;

import kotlin.Deprecated;
import kotlin.Metadata;

@Deprecated
@InternalCoroutinesApi
@Metadata
/* loaded from: classes2.dex */
public interface ChildHandle extends DisposableHandle {

    @Metadata
    public static final class DefaultImpls {
    }

    Job getParent();

    boolean h(Throwable th);
}
