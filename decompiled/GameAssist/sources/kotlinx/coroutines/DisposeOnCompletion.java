package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Unit;

@Metadata
/* loaded from: classes2.dex */
public final class DisposeOnCompletion extends JobNode {

    /* renamed from: k, reason: collision with root package name */
    private final DisposableHandle f18874k;

    public DisposeOnCompletion(DisposableHandle disposableHandle) {
        this.f18874k = disposableHandle;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object c(Object obj) {
        d0((Throwable) obj);
        return Unit.f18288a;
    }

    @Override // kotlinx.coroutines.CompletionHandlerBase
    public void d0(Throwable th) {
        this.f18874k.dispose();
    }
}
