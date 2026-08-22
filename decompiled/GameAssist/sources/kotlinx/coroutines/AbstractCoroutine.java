package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;

@InternalCoroutinesApi
@Metadata
/* loaded from: classes2.dex */
public abstract class AbstractCoroutine<T> extends JobSupport implements Job, Continuation<T>, CoroutineScope {

    /* renamed from: h, reason: collision with root package name */
    private final CoroutineContext f18817h;

    public AbstractCoroutine(CoroutineContext coroutineContext, boolean z, boolean z2) {
        super(z2);
        if (z) {
            x0((Job) coroutineContext.c(Job.f18898f));
        }
        this.f18817h = coroutineContext.R(this);
    }

    @Override // kotlinx.coroutines.JobSupport
    public String F0() {
        String b2 = CoroutineContextKt.b(this.f18817h);
        if (b2 == null) {
            return super.F0();
        }
        return '\"' + b2 + "\":" + super.F0();
    }

    @Override // kotlinx.coroutines.CoroutineScope
    public CoroutineContext K() {
        return this.f18817h;
    }

    @Override // kotlinx.coroutines.JobSupport
    protected final void K0(Object obj) {
        if (!(obj instanceof CompletedExceptionally)) {
            e1(obj);
        } else {
            CompletedExceptionally completedExceptionally = (CompletedExceptionally) obj;
            d1(completedExceptionally.f18845a, completedExceptionally.a());
        }
    }

    protected void c1(Object obj) {
        W(obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.JobSupport
    public String d0() {
        return DebugStringsKt.a(this) + " was cancelled";
    }

    protected void d1(Throwable th, boolean z) {
    }

    protected void e1(Object obj) {
    }

    public final void f1(CoroutineStart coroutineStart, Object obj, Function2 function2) {
        coroutineStart.d(function2, obj, this);
    }

    @Override // kotlin.coroutines.Continuation
    public final void g(Object obj) {
        Object D0 = D0(CompletionStateKt.d(obj, null, 1, null));
        if (D0 == JobSupportKt.f18912b) {
            return;
        }
        c1(D0);
    }

    @Override // kotlin.coroutines.Continuation
    public final CoroutineContext getContext() {
        return this.f18817h;
    }

    @Override // kotlinx.coroutines.JobSupport, kotlinx.coroutines.Job
    public boolean isActive() {
        return super.isActive();
    }

    @Override // kotlinx.coroutines.JobSupport
    public final void w0(Throwable th) {
        CoroutineExceptionHandlerKt.a(this.f18817h, th);
    }
}
