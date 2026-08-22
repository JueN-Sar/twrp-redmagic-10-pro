package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;

@Metadata
/* loaded from: classes2.dex */
public final class InterruptibleKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Object b(CoroutineContext coroutineContext, Function0 function0) {
        try {
            ThreadState threadState = new ThreadState(JobKt.i(coroutineContext));
            threadState.g();
            try {
                return function0.a();
            } finally {
                threadState.d();
            }
        } catch (InterruptedException e2) {
            throw new CancellationException("Blocking call was interrupted due to parent cancellation").initCause(e2);
        }
    }
}
