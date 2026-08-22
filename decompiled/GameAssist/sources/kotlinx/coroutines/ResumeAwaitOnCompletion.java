package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;

@Metadata
/* loaded from: classes2.dex */
final class ResumeAwaitOnCompletion<T> extends JobNode {

    /* renamed from: k, reason: collision with root package name */
    private final CancellableContinuationImpl f18923k;

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object c(Object obj) {
        d0((Throwable) obj);
        return Unit.f18288a;
    }

    @Override // kotlinx.coroutines.CompletionHandlerBase
    public void d0(Throwable th) {
        Object u0 = e0().u0();
        if (u0 instanceof CompletedExceptionally) {
            CancellableContinuationImpl cancellableContinuationImpl = this.f18923k;
            Result.Companion companion = Result.Companion;
            cancellableContinuationImpl.g(Result.b(ResultKt.a(((CompletedExceptionally) u0).f18845a)));
        } else {
            CancellableContinuationImpl cancellableContinuationImpl2 = this.f18923k;
            Result.Companion companion2 = Result.Companion;
            cancellableContinuationImpl2.g(Result.b(JobSupportKt.h(u0)));
        }
    }
}
