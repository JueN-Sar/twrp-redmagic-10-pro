package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;

@Metadata
/* loaded from: classes2.dex */
public interface CancellableContinuation<T> extends Continuation<T> {

    @Metadata
    public static final class DefaultImpls {
        public static /* synthetic */ Object a(CancellableContinuation cancellableContinuation, Object obj, Object obj2, int i2, Object obj3) {
            if (obj3 != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: tryResume");
            }
            if ((i2 & 2) != 0) {
                obj2 = null;
            }
            return cancellableContinuation.d(obj, obj2);
        }
    }

    Object D(Object obj, Object obj2, Function1 function1);

    void H(CoroutineDispatcher coroutineDispatcher, Object obj);

    void O(Object obj);

    Object d(Object obj, Object obj2);

    void m(Function1 function1);

    Object n(Throwable th);

    void r(Object obj, Function1 function1);
}
