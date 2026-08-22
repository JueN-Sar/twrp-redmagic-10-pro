package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.internal.ScopeCoroutine;

@Metadata
/* loaded from: classes2.dex */
final class TimeoutCoroutine<U, T extends U> extends ScopeCoroutine<T> implements Runnable {

    /* renamed from: j, reason: collision with root package name */
    public final long f18938j;

    public TimeoutCoroutine(long j2, Continuation continuation) {
        super(continuation.getContext(), continuation);
        this.f18938j = j2;
    }

    @Override // kotlinx.coroutines.AbstractCoroutine, kotlinx.coroutines.JobSupport
    public String F0() {
        return super.F0() + "(timeMillis=" + this.f18938j + ')';
    }

    @Override // java.lang.Runnable
    public void run() {
        X(TimeoutKt.a(this.f18938j, this));
    }
}
