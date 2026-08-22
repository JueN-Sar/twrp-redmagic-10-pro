package kotlin.coroutines.intrinsics;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.RestrictedContinuationImpl;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public final class IntrinsicsKt__IntrinsicsJvmKt$createCoroutineFromSuspendFunction$1 extends RestrictedContinuationImpl {
    final /* synthetic */ Function1<Continuation<Object>, Object> $block;
    private int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public IntrinsicsKt__IntrinsicsJvmKt$createCoroutineFromSuspendFunction$1(Continuation<Object> continuation, Function1<? super Continuation<Object>, ? extends Object> function1) {
        super(continuation);
        this.$block = function1;
        Intrinsics.c(continuation, "null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    protected Object v(Object obj) {
        int i2 = this.label;
        if (i2 == 0) {
            this.label = 1;
            ResultKt.b(obj);
            return this.$block.c(this);
        }
        if (i2 != 1) {
            throw new IllegalStateException("This coroutine had already completed".toString());
        }
        this.label = 2;
        ResultKt.b(obj);
        return obj;
    }
}
