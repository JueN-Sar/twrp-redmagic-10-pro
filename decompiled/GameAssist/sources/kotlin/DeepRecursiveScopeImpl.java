package kotlin;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function3;

@Metadata
/* loaded from: classes2.dex */
final class DeepRecursiveScopeImpl<T, R> extends DeepRecursiveScope<T, R> implements Continuation<R> {

    /* renamed from: c, reason: collision with root package name */
    private Function3 f18252c;

    /* renamed from: h, reason: collision with root package name */
    private Continuation f18253h;

    /* renamed from: i, reason: collision with root package name */
    private Object f18254i;

    @Override // kotlin.coroutines.Continuation
    public void g(Object obj) {
        this.f18253h = null;
        this.f18254i = obj;
    }

    @Override // kotlin.coroutines.Continuation
    public CoroutineContext getContext() {
        return EmptyCoroutineContext.INSTANCE;
    }
}
