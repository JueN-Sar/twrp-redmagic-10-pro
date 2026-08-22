package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.intrinsics.CancellableKt;

@Metadata
/* loaded from: classes2.dex */
final class LazyDeferredCoroutine<T> extends DeferredCoroutine<T> {

    /* renamed from: i, reason: collision with root package name */
    private final Continuation f18918i;

    @Override // kotlinx.coroutines.JobSupport
    protected void L0() {
        CancellableKt.b(this.f18918i, this);
    }
}
