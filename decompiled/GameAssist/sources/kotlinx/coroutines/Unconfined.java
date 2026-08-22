package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;

@Metadata
/* loaded from: classes2.dex */
public final class Unconfined extends CoroutineDispatcher {

    /* renamed from: i, reason: collision with root package name */
    public static final Unconfined f18939i = new Unconfined();

    private Unconfined() {
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public void j0(CoroutineContext coroutineContext, Runnable runnable) {
        YieldContext yieldContext = (YieldContext) coroutineContext.c(YieldContext.f18942i);
        if (yieldContext == null) {
            throw new UnsupportedOperationException("Dispatchers.Unconfined.dispatch function can only be used by the yield function. If you wrap Unconfined dispatcher in your code, make sure you properly delegate isDispatchNeeded and dispatch calls.");
        }
        yieldContext.f18943h = true;
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public boolean l0(CoroutineContext coroutineContext) {
        return false;
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public String toString() {
        return "Dispatchers.Unconfined";
    }
}
