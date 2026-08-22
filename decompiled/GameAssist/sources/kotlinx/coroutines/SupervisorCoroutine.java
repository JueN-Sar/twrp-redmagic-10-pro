package kotlinx.coroutines;

import kotlin.Metadata;
import kotlinx.coroutines.internal.ScopeCoroutine;

@Metadata
/* loaded from: classes2.dex */
final class SupervisorCoroutine<T> extends ScopeCoroutine<T> {
    @Override // kotlinx.coroutines.JobSupport
    public boolean g0(Throwable th) {
        return false;
    }
}
