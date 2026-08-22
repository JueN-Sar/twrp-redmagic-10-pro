package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

@Metadata
/* loaded from: classes2.dex */
final class InvokeOnCancel extends CancelHandler {

    /* renamed from: c, reason: collision with root package name */
    private final Function1 f18894c;

    public InvokeOnCancel(Function1 function1) {
        this.f18894c = function1;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object c(Object obj) {
        d((Throwable) obj);
        return Unit.f18288a;
    }

    @Override // kotlinx.coroutines.CancelHandlerBase
    public void d(Throwable th) {
        this.f18894c.c(th);
    }

    public String toString() {
        return "InvokeOnCancel[" + DebugStringsKt.a(this.f18894c) + '@' + DebugStringsKt.b(this) + ']';
    }
}
