package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

@Metadata
@DebugMetadata(c = "kotlinx.coroutines.flow.LintKt$retry$1", f = "Lint.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes2.dex */
public final class LintKt$retry$1 extends SuspendLambda implements Function2<Throwable, Continuation<? super Boolean>, Object> {
    int label;

    public LintKt$retry$1(Continuation<? super LintKt$retry$1> continuation) {
        super(2, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: A, reason: merged with bridge method [inline-methods] */
    public final Object y(Throwable th, Continuation continuation) {
        return ((LintKt$retry$1) o(th, continuation)).v(Unit.f18288a);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation o(Object obj, Continuation continuation) {
        return new LintKt$retry$1(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object v(Object obj) {
        IntrinsicsKt__IntrinsicsKt.d();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.b(obj);
        return Boxing.a(true);
    }
}
