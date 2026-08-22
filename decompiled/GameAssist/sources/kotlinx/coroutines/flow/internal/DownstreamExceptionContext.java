package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;

@Metadata
/* loaded from: classes2.dex */
public final class DownstreamExceptionContext implements CoroutineContext {

    /* renamed from: c, reason: collision with root package name */
    public final Throwable f19318c;

    /* renamed from: h, reason: collision with root package name */
    private final /* synthetic */ CoroutineContext f19319h;

    public DownstreamExceptionContext(Throwable th, CoroutineContext coroutineContext) {
        this.f19318c = th;
        this.f19319h = coroutineContext;
    }

    @Override // kotlin.coroutines.CoroutineContext
    public CoroutineContext R(CoroutineContext coroutineContext) {
        return this.f19319h.R(coroutineContext);
    }

    @Override // kotlin.coroutines.CoroutineContext
    public CoroutineContext Y(CoroutineContext.Key key) {
        return this.f19319h.Y(key);
    }

    @Override // kotlin.coroutines.CoroutineContext
    public CoroutineContext.Element c(CoroutineContext.Key key) {
        return this.f19319h.c(key);
    }

    @Override // kotlin.coroutines.CoroutineContext
    public Object e0(Object obj, Function2 function2) {
        return this.f19319h.e0(obj, function2);
    }
}
