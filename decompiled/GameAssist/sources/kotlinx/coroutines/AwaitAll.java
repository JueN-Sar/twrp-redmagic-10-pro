package kotlinx.coroutines;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata
/* loaded from: classes2.dex */
final class AwaitAll<T> {

    /* renamed from: b, reason: collision with root package name */
    static final /* synthetic */ AtomicIntegerFieldUpdater f18820b = AtomicIntegerFieldUpdater.newUpdater(AwaitAll.class, "notCompletedCount");

    /* renamed from: a, reason: collision with root package name */
    private final Deferred[] f18821a;

    @NotNull
    volatile /* synthetic */ int notCompletedCount;

    @Metadata
    private final class AwaitAllNode extends JobNode {

        @NotNull
        private volatile /* synthetic */ Object _disposer;

        /* renamed from: k, reason: collision with root package name */
        private final CancellableContinuation f18822k;

        /* renamed from: l, reason: collision with root package name */
        public DisposableHandle f18823l;

        /* renamed from: m, reason: collision with root package name */
        final /* synthetic */ AwaitAll f18824m;

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object c(Object obj) {
            d0((Throwable) obj);
            return Unit.f18288a;
        }

        @Override // kotlinx.coroutines.CompletionHandlerBase
        public void d0(Throwable th) {
            if (th != null) {
                Object n2 = this.f18822k.n(th);
                if (n2 != null) {
                    this.f18822k.O(n2);
                    DisposeHandlersOnCancel g0 = g0();
                    if (g0 != null) {
                        g0.e();
                        return;
                    }
                    return;
                }
                return;
            }
            if (AwaitAll.f18820b.decrementAndGet(this.f18824m) == 0) {
                CancellableContinuation cancellableContinuation = this.f18822k;
                Deferred[] deferredArr = this.f18824m.f18821a;
                ArrayList arrayList = new ArrayList(deferredArr.length);
                for (Deferred deferred : deferredArr) {
                    arrayList.add(deferred.h());
                }
                cancellableContinuation.g(Result.b(arrayList));
            }
        }

        public final DisposeHandlersOnCancel g0() {
            return (DisposeHandlersOnCancel) this._disposer;
        }

        public final DisposableHandle h0() {
            DisposableHandle disposableHandle = this.f18823l;
            if (disposableHandle != null) {
                return disposableHandle;
            }
            Intrinsics.s("handle");
            return null;
        }
    }

    @Metadata
    private final class DisposeHandlersOnCancel extends CancelHandler {

        /* renamed from: c, reason: collision with root package name */
        private final AwaitAllNode[] f18825c;

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object c(Object obj) {
            d((Throwable) obj);
            return Unit.f18288a;
        }

        @Override // kotlinx.coroutines.CancelHandlerBase
        public void d(Throwable th) {
            e();
        }

        public final void e() {
            for (AwaitAllNode awaitAllNode : this.f18825c) {
                awaitAllNode.h0().dispose();
            }
        }

        public String toString() {
            return "DisposeHandlersOnCancel[" + this.f18825c + ']';
        }
    }
}
