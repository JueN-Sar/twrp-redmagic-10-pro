package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

@Metadata
@DebugMetadata(c = "kotlinx.coroutines.channels.ReceiveChannel$onReceiveOrNull$1$registerSelectClause1$1", f = "Channel.kt", l = {375}, m = "invokeSuspend")
/* loaded from: classes2.dex */
final class ReceiveChannel$onReceiveOrNull$1$registerSelectClause1$1 extends SuspendLambda implements Function2<ChannelResult<Object>, Continuation<Object>, Object> {
    final /* synthetic */ Function2<Object, Continuation<Object>, Object> $block;
    /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    ReceiveChannel$onReceiveOrNull$1$registerSelectClause1$1(Function2<Object, ? super Continuation<Object>, ? extends Object> function2, Continuation<? super ReceiveChannel$onReceiveOrNull$1$registerSelectClause1$1> continuation) {
        super(2, continuation);
        this.$block = function2;
    }

    public final Object A(Object obj, Continuation continuation) {
        return ((ReceiveChannel$onReceiveOrNull$1$registerSelectClause1$1) o(ChannelResult.b(obj), continuation)).v(Unit.f18288a);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation o(Object obj, Continuation continuation) {
        ReceiveChannel$onReceiveOrNull$1$registerSelectClause1$1 receiveChannel$onReceiveOrNull$1$registerSelectClause1$1 = new ReceiveChannel$onReceiveOrNull$1$registerSelectClause1$1(this.$block, continuation);
        receiveChannel$onReceiveOrNull$1$registerSelectClause1$1.L$0 = obj;
        return receiveChannel$onReceiveOrNull$1$registerSelectClause1$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object v(Object obj) {
        Object d2;
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.b(obj);
            Object k2 = ((ChannelResult) this.L$0).k();
            Throwable e2 = ChannelResult.e(k2);
            if (e2 != null) {
                throw e2;
            }
            Function2<Object, Continuation<Object>, Object> function2 = this.$block;
            Object f2 = ChannelResult.f(k2);
            this.label = 1;
            obj = function2.y(f2, this);
            if (obj == d2) {
                return d2;
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.b(obj);
        }
        return obj;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object y(Object obj, Object obj2) {
        return A(((ChannelResult) obj).k(), (Continuation) obj2);
    }
}
