package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlinx.coroutines.AbstractCoroutine;
import kotlinx.coroutines.ChildHandle;
import kotlinx.coroutines.CompletionStateKt;
import kotlinx.coroutines.Job;

@Metadata
/* loaded from: classes2.dex */
public class ScopeCoroutine<T> extends AbstractCoroutine<T> implements CoroutineStackFrame {

    /* renamed from: i, reason: collision with root package name */
    public final Continuation f19399i;

    public ScopeCoroutine(CoroutineContext coroutineContext, Continuation continuation) {
        super(coroutineContext, true, true);
        this.f19399i = continuation;
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public final StackTraceElement B() {
        return null;
    }

    @Override // kotlinx.coroutines.JobSupport
    protected void W(Object obj) {
        Continuation c2;
        c2 = IntrinsicsKt__IntrinsicsJvmKt.c(this.f19399i);
        DispatchedContinuationKt.c(c2, CompletionStateKt.a(obj, this.f19399i), null, 2, null);
    }

    @Override // kotlinx.coroutines.AbstractCoroutine
    protected void c1(Object obj) {
        Continuation continuation = this.f19399i;
        continuation.g(CompletionStateKt.a(obj, continuation));
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public final CoroutineStackFrame f() {
        Continuation continuation = this.f19399i;
        if (continuation instanceof CoroutineStackFrame) {
            return (CoroutineStackFrame) continuation;
        }
        return null;
    }

    public final Job g1() {
        ChildHandle t0 = t0();
        if (t0 != null) {
            return t0.getParent();
        }
        return null;
    }

    @Override // kotlinx.coroutines.JobSupport
    protected final boolean y0() {
        return true;
    }
}
