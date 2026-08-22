package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;

@Metadata
/* loaded from: classes2.dex */
class StandaloneCoroutine extends AbstractCoroutine<Unit> {
    public StandaloneCoroutine(CoroutineContext coroutineContext, boolean z) {
        super(coroutineContext, true, z);
    }

    @Override // kotlinx.coroutines.JobSupport
    protected boolean v0(Throwable th) {
        CoroutineExceptionHandlerKt.a(getContext(), th);
        return true;
    }
}
