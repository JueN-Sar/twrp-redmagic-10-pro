package kotlinx.coroutines.debug.internal;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.SequenceScope;

@Metadata
@DebugMetadata(c = "kotlinx.coroutines.debug.internal.DebugCoroutineInfoImpl$creationStackTrace$1", f = "DebugCoroutineInfoImpl.kt", l = {75}, m = "invokeSuspend")
/* loaded from: classes2.dex */
final class DebugCoroutineInfoImpl$creationStackTrace$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super StackTraceElement>, Continuation<? super Unit>, Object> {
    final /* synthetic */ StackTraceFrame $bottom;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ DebugCoroutineInfoImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DebugCoroutineInfoImpl$creationStackTrace$1(DebugCoroutineInfoImpl debugCoroutineInfoImpl, StackTraceFrame stackTraceFrame, Continuation<? super DebugCoroutineInfoImpl$creationStackTrace$1> continuation) {
        super(2, continuation);
        this.this$0 = debugCoroutineInfoImpl;
        this.$bottom = stackTraceFrame;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation o(Object obj, Continuation continuation) {
        DebugCoroutineInfoImpl$creationStackTrace$1 debugCoroutineInfoImpl$creationStackTrace$1 = new DebugCoroutineInfoImpl$creationStackTrace$1(this.this$0, this.$bottom, continuation);
        debugCoroutineInfoImpl$creationStackTrace$1.L$0 = obj;
        return debugCoroutineInfoImpl$creationStackTrace$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object v(Object obj) {
        Object d2;
        Object i2;
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.b(obj);
            SequenceScope sequenceScope = (SequenceScope) this.L$0;
            DebugCoroutineInfoImpl debugCoroutineInfoImpl = this.this$0;
            CoroutineStackFrame f2 = this.$bottom.f();
            this.label = 1;
            i2 = debugCoroutineInfoImpl.i(sequenceScope, f2, this);
            if (i2 == d2) {
                return d2;
            }
        } else {
            if (i3 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.b(obj);
        }
        return Unit.f18288a;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: x, reason: merged with bridge method [inline-methods] */
    public final Object y(SequenceScope sequenceScope, Continuation continuation) {
        return ((DebugCoroutineInfoImpl$creationStackTrace$1) o(sequenceScope, continuation)).v(Unit.f18288a);
    }
}
