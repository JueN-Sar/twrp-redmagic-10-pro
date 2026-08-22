package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.selects.SelectClause1;
import kotlinx.coroutines.selects.SelectInstance;

@Metadata
/* loaded from: classes2.dex */
class DeferredCoroutine<T> extends AbstractCoroutine<T> implements Deferred<T>, SelectClause1<T> {
    @Override // kotlinx.coroutines.selects.SelectClause1
    public void b(SelectInstance selectInstance, Function2 function2) {
        O0(selectInstance, function2);
    }

    @Override // kotlinx.coroutines.Deferred
    public Object h() {
        return n0();
    }
}
