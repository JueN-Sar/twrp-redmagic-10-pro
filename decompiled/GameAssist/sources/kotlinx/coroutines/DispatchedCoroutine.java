package kotlinx.coroutines;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlinx.coroutines.internal.DispatchedContinuationKt;
import kotlinx.coroutines.internal.ScopeCoroutine;
import org.jetbrains.annotations.NotNull;

@Metadata
/* loaded from: classes2.dex */
public final class DispatchedCoroutine<T> extends ScopeCoroutine<T> {

    /* renamed from: j, reason: collision with root package name */
    private static final /* synthetic */ AtomicIntegerFieldUpdater f18865j = AtomicIntegerFieldUpdater.newUpdater(DispatchedCoroutine.class, "_decision");

    @NotNull
    private volatile /* synthetic */ int _decision;

    public DispatchedCoroutine(CoroutineContext coroutineContext, Continuation continuation) {
        super(coroutineContext, continuation);
        this._decision = 0;
    }

    private final boolean i1() {
        do {
            int i2 = this._decision;
            if (i2 != 0) {
                if (i2 == 1) {
                    return false;
                }
                throw new IllegalStateException("Already resumed".toString());
            }
        } while (!f18865j.compareAndSet(this, 0, 2));
        return true;
    }

    private final boolean j1() {
        do {
            int i2 = this._decision;
            if (i2 != 0) {
                if (i2 == 2) {
                    return false;
                }
                throw new IllegalStateException("Already suspended".toString());
            }
        } while (!f18865j.compareAndSet(this, 0, 1));
        return true;
    }

    @Override // kotlinx.coroutines.internal.ScopeCoroutine, kotlinx.coroutines.JobSupport
    protected void W(Object obj) {
        c1(obj);
    }

    @Override // kotlinx.coroutines.internal.ScopeCoroutine, kotlinx.coroutines.AbstractCoroutine
    protected void c1(Object obj) {
        Continuation c2;
        if (i1()) {
            return;
        }
        c2 = IntrinsicsKt__IntrinsicsJvmKt.c(this.f19399i);
        DispatchedContinuationKt.c(c2, CompletionStateKt.a(obj, this.f19399i), null, 2, null);
    }

    public final Object h1() {
        Object d2;
        if (j1()) {
            d2 = IntrinsicsKt__IntrinsicsKt.d();
            return d2;
        }
        Object h2 = JobSupportKt.h(u0());
        if (h2 instanceof CompletedExceptionally) {
            throw ((CompletedExceptionally) h2).f18845a;
        }
        return h2;
    }
}
