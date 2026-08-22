package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;

@Metadata
/* loaded from: classes2.dex */
final class StackFrameContinuation<T> implements Continuation<T>, CoroutineStackFrame {

    /* renamed from: c, reason: collision with root package name */
    private final Continuation f19330c;

    /* renamed from: h, reason: collision with root package name */
    private final CoroutineContext f19331h;

    public StackFrameContinuation(Continuation continuation, CoroutineContext coroutineContext) {
        this.f19330c = continuation;
        this.f19331h = coroutineContext;
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public StackTraceElement B() {
        return null;
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public CoroutineStackFrame f() {
        Continuation continuation = this.f19330c;
        if (continuation instanceof CoroutineStackFrame) {
            return (CoroutineStackFrame) continuation;
        }
        return null;
    }

    @Override // kotlin.coroutines.Continuation
    public void g(Object obj) {
        this.f19330c.g(obj);
    }

    @Override // kotlin.coroutines.Continuation
    public CoroutineContext getContext() {
        return this.f19331h;
    }
}
