package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.DelayKt;

@Metadata
@DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__MigrationKt$delayEach$1", f = "Migration.kt", l = {427}, m = "invokeSuspend")
/* loaded from: classes2.dex */
final class FlowKt__MigrationKt$delayEach$1 extends SuspendLambda implements Function2<Object, Continuation<? super Unit>, Object> {
    final /* synthetic */ long $timeMillis;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    FlowKt__MigrationKt$delayEach$1(long j2, Continuation<? super FlowKt__MigrationKt$delayEach$1> continuation) {
        super(2, continuation);
        this.$timeMillis = j2;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: A, reason: merged with bridge method [inline-methods] */
    public final Object y(Object obj, Continuation continuation) {
        return ((FlowKt__MigrationKt$delayEach$1) o(obj, continuation)).v(Unit.f18288a);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation o(Object obj, Continuation continuation) {
        return new FlowKt__MigrationKt$delayEach$1(this.$timeMillis, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object v(Object obj) {
        Object d2;
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.b(obj);
            long j2 = this.$timeMillis;
            this.label = 1;
            if (DelayKt.b(j2, this) == d2) {
                return d2;
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.b(obj);
        }
        return Unit.f18288a;
    }
}
