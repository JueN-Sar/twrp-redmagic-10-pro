package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;

@Metadata
/* loaded from: classes2.dex */
public final class NonCancellable extends AbstractCoroutineContextElement implements Job {

    /* renamed from: h, reason: collision with root package name */
    public static final NonCancellable f18920h = new NonCancellable();

    private NonCancellable() {
        super(Job.f18898f);
    }

    @Override // kotlinx.coroutines.Job
    public DisposableHandle A(Function1 function1) {
        return NonDisposableHandle.f18921c;
    }

    @Override // kotlinx.coroutines.Job
    public boolean L() {
        return false;
    }

    @Override // kotlinx.coroutines.Job
    public Object T(Continuation continuation) {
        throw new UnsupportedOperationException("This job is always active");
    }

    @Override // kotlinx.coroutines.Job
    public void a(CancellationException cancellationException) {
    }

    @Override // kotlinx.coroutines.Job
    public ChildHandle h0(ChildJob childJob) {
        return NonDisposableHandle.f18921c;
    }

    @Override // kotlinx.coroutines.Job
    public boolean isActive() {
        return true;
    }

    @Override // kotlinx.coroutines.Job
    public boolean isCancelled() {
        return false;
    }

    @Override // kotlinx.coroutines.Job
    public DisposableHandle p(boolean z, boolean z2, Function1 function1) {
        return NonDisposableHandle.f18921c;
    }

    @Override // kotlinx.coroutines.Job
    public CancellationException s() {
        throw new IllegalStateException("This job is always active");
    }

    @Override // kotlinx.coroutines.Job
    public boolean start() {
        return false;
    }

    public String toString() {
        return "NonCancellable";
    }
}
