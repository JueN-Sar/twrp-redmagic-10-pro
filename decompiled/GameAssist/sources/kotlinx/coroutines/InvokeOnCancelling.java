package kotlinx.coroutines;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;

@Metadata
/* loaded from: classes2.dex */
final class InvokeOnCancelling extends JobCancellingNode {

    /* renamed from: l, reason: collision with root package name */
    private static final /* synthetic */ AtomicIntegerFieldUpdater f18895l = AtomicIntegerFieldUpdater.newUpdater(InvokeOnCancelling.class, "_invoked");

    @NotNull
    private volatile /* synthetic */ int _invoked = 0;

    /* renamed from: k, reason: collision with root package name */
    private final Function1 f18896k;

    public InvokeOnCancelling(Function1 function1) {
        this.f18896k = function1;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object c(Object obj) {
        d0((Throwable) obj);
        return Unit.f18288a;
    }

    @Override // kotlinx.coroutines.CompletionHandlerBase
    public void d0(Throwable th) {
        if (f18895l.compareAndSet(this, 0, 1)) {
            this.f18896k.c(th);
        }
    }
}
