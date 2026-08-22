package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.selects.SelectClause1;
import kotlinx.coroutines.selects.SelectInstance;

@Metadata
/* loaded from: classes2.dex */
final class CompletableDeferredImpl<T> extends JobSupport implements CompletableDeferred<T>, SelectClause1<T> {
    @Override // kotlinx.coroutines.CompletableDeferred
    public boolean F(Throwable th) {
        return C0(new CompletedExceptionally(th, false, 2, null));
    }

    @Override // kotlinx.coroutines.CompletableDeferred
    public boolean G(Object obj) {
        return C0(obj);
    }

    @Override // kotlinx.coroutines.selects.SelectClause1
    public void b(SelectInstance selectInstance, Function2 function2) {
        O0(selectInstance, function2);
    }

    @Override // kotlinx.coroutines.Deferred
    public Object h() {
        return n0();
    }

    @Override // kotlinx.coroutines.JobSupport
    public boolean r0() {
        return true;
    }
}
