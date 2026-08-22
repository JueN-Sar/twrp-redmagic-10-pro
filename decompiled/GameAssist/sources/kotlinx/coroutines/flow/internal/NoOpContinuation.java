package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;

@Metadata
/* loaded from: classes2.dex */
final class NoOpContinuation implements Continuation<Object> {

    /* renamed from: c, reason: collision with root package name */
    public static final NoOpContinuation f19321c = new NoOpContinuation();

    /* renamed from: h, reason: collision with root package name */
    private static final CoroutineContext f19322h = EmptyCoroutineContext.INSTANCE;

    private NoOpContinuation() {
    }

    @Override // kotlin.coroutines.Continuation
    public void g(Object obj) {
    }

    @Override // kotlin.coroutines.Continuation
    public CoroutineContext getContext() {
        return f19322h;
    }
}
