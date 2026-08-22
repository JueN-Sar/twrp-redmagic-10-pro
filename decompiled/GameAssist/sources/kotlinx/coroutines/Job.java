package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

@Metadata
/* loaded from: classes2.dex */
public interface Job extends CoroutineContext.Element {

    /* renamed from: f, reason: collision with root package name */
    public static final Key f18898f = Key.f18899c;

    @Metadata
    public static final class DefaultImpls {
        public static /* synthetic */ void a(Job job, CancellationException cancellationException, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: cancel");
            }
            if ((i2 & 1) != 0) {
                cancellationException = null;
            }
            job.a(cancellationException);
        }

        public static Object b(Job job, Object obj, Function2 function2) {
            return CoroutineContext.Element.DefaultImpls.a(job, obj, function2);
        }

        public static CoroutineContext.Element c(Job job, CoroutineContext.Key key) {
            return CoroutineContext.Element.DefaultImpls.b(job, key);
        }

        public static /* synthetic */ DisposableHandle d(Job job, boolean z, boolean z2, Function1 function1, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: invokeOnCompletion");
            }
            if ((i2 & 1) != 0) {
                z = false;
            }
            if ((i2 & 2) != 0) {
                z2 = true;
            }
            return job.p(z, z2, function1);
        }

        public static CoroutineContext e(Job job, CoroutineContext.Key key) {
            return CoroutineContext.Element.DefaultImpls.c(job, key);
        }

        public static CoroutineContext f(Job job, CoroutineContext coroutineContext) {
            return CoroutineContext.Element.DefaultImpls.d(job, coroutineContext);
        }
    }

    @Metadata
    public static final class Key implements CoroutineContext.Key<Job> {

        /* renamed from: c, reason: collision with root package name */
        static final /* synthetic */ Key f18899c = new Key();

        private Key() {
        }
    }

    DisposableHandle A(Function1 function1);

    boolean L();

    Object T(Continuation continuation);

    void a(CancellationException cancellationException);

    ChildHandle h0(ChildJob childJob);

    boolean isActive();

    boolean isCancelled();

    DisposableHandle p(boolean z, boolean z2, Function1 function1);

    CancellationException s();

    boolean start();
}
