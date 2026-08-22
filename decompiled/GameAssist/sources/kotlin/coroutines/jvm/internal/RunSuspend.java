package kotlin.coroutines.jvm.internal;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.internal.Intrinsics;

@Metadata
/* loaded from: classes2.dex */
final class RunSuspend implements Continuation<Unit> {

    /* renamed from: c, reason: collision with root package name */
    private Result f18423c;

    @Override // kotlin.coroutines.Continuation
    public void g(Object obj) {
        synchronized (this) {
            this.f18423c = Result.a(obj);
            Intrinsics.c(this, "null cannot be cast to non-null type java.lang.Object");
            notifyAll();
            Unit unit = Unit.f18288a;
        }
    }

    @Override // kotlin.coroutines.Continuation
    public CoroutineContext getContext() {
        return EmptyCoroutineContext.INSTANCE;
    }
}
