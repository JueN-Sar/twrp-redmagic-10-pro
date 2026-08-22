package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.intrinsics.CancellableKt;

@Metadata
/* loaded from: classes2.dex */
final class LazyStandaloneCoroutine extends StandaloneCoroutine {

    /* renamed from: i, reason: collision with root package name */
    private final Continuation f18919i;

    public LazyStandaloneCoroutine(CoroutineContext coroutineContext, Function2 function2) {
        super(coroutineContext, false);
        Continuation b2;
        b2 = IntrinsicsKt__IntrinsicsJvmKt.b(function2, this, this);
        this.f18919i = b2;
    }

    @Override // kotlinx.coroutines.JobSupport
    protected void L0() {
        CancellableKt.b(this.f18919i, this);
    }
}
