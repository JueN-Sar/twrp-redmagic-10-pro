package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Metadata
/* loaded from: classes2.dex */
final class ResumeOnCompletion extends JobNode {

    /* renamed from: k, reason: collision with root package name */
    private final Continuation f18924k;

    public ResumeOnCompletion(Continuation continuation) {
        this.f18924k = continuation;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object c(Object obj) {
        d0((Throwable) obj);
        return Unit.f18288a;
    }

    @Override // kotlinx.coroutines.CompletionHandlerBase
    public void d0(Throwable th) {
        Continuation continuation = this.f18924k;
        Result.Companion companion = Result.Companion;
        continuation.g(Result.b(Unit.f18288a));
    }
}
