package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Unit;

@Metadata
/* loaded from: classes2.dex */
final class DisposeOnCancel extends CancelHandler {

    /* renamed from: c, reason: collision with root package name */
    private final DisposableHandle f18873c;

    public DisposeOnCancel(DisposableHandle disposableHandle) {
        this.f18873c = disposableHandle;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object c(Object obj) {
        d((Throwable) obj);
        return Unit.f18288a;
    }

    @Override // kotlinx.coroutines.CancelHandlerBase
    public void d(Throwable th) {
        this.f18873c.dispose();
    }

    public String toString() {
        return "DisposeOnCancel[" + this.f18873c + ']';
    }
}
