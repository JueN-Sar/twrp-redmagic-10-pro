package kotlin.coroutines.jvm.internal;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;

@Metadata
/* loaded from: classes2.dex */
public final class CompletedContinuation implements Continuation<Object> {

    /* renamed from: c, reason: collision with root package name */
    public static final CompletedContinuation f18416c = new CompletedContinuation();

    private CompletedContinuation() {
    }

    @Override // kotlin.coroutines.Continuation
    public void g(Object obj) {
        throw new IllegalStateException("This continuation is already complete".toString());
    }

    @Override // kotlin.coroutines.Continuation
    public CoroutineContext getContext() {
        throw new IllegalStateException("This continuation is already complete".toString());
    }

    public String toString() {
        return "This continuation is already complete";
    }
}
