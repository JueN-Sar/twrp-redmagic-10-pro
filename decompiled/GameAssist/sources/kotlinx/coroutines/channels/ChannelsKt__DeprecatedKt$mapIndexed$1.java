package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

@Metadata
@DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$mapIndexed$1", f = "Deprecated.kt", l = {344, 345, 345}, m = "invokeSuspend")
/* loaded from: classes2.dex */
final class ChannelsKt__DeprecatedKt$mapIndexed$1 extends SuspendLambda implements Function2<ProducerScope<Object>, Continuation<? super Unit>, Object> {
    final /* synthetic */ ReceiveChannel<Object> $this_mapIndexed;
    final /* synthetic */ Function3<Integer, Object, Continuation<Object>, Object> $transform;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    ChannelsKt__DeprecatedKt$mapIndexed$1(ReceiveChannel<Object> receiveChannel, Function3<? super Integer, Object, ? super Continuation<Object>, ? extends Object> function3, Continuation<? super ChannelsKt__DeprecatedKt$mapIndexed$1> continuation) {
        super(2, continuation);
        this.$this_mapIndexed = receiveChannel;
        this.$transform = function3;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: A, reason: merged with bridge method [inline-methods] */
    public final Object y(ProducerScope producerScope, Continuation continuation) {
        return ((ChannelsKt__DeprecatedKt$mapIndexed$1) o(producerScope, continuation)).v(Unit.f18288a);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation o(Object obj, Continuation continuation) {
        ChannelsKt__DeprecatedKt$mapIndexed$1 channelsKt__DeprecatedKt$mapIndexed$1 = new ChannelsKt__DeprecatedKt$mapIndexed$1(this.$this_mapIndexed, this.$transform, continuation);
        channelsKt__DeprecatedKt$mapIndexed$1.L$0 = obj;
        return channelsKt__DeprecatedKt$mapIndexed$1;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00a5 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00a9  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x00a6 -> B:7:0x0059). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object v(java.lang.Object r11) {
        /*
            r10 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.d()
            int r1 = r10.label
            r2 = 3
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L48
            if (r1 == r4) goto L3a
            if (r1 == r3) goto L28
            if (r1 != r2) goto L20
            int r1 = r10.I$0
            java.lang.Object r5 = r10.L$1
            kotlinx.coroutines.channels.ChannelIterator r5 = (kotlinx.coroutines.channels.ChannelIterator) r5
            java.lang.Object r6 = r10.L$0
            kotlinx.coroutines.channels.ProducerScope r6 = (kotlinx.coroutines.channels.ProducerScope) r6
            kotlin.ResultKt.b(r11)
            r11 = r6
            goto L59
        L20:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L28:
            int r1 = r10.I$0
            java.lang.Object r5 = r10.L$2
            kotlinx.coroutines.channels.ProducerScope r5 = (kotlinx.coroutines.channels.ProducerScope) r5
            java.lang.Object r6 = r10.L$1
            kotlinx.coroutines.channels.ChannelIterator r6 = (kotlinx.coroutines.channels.ChannelIterator) r6
            java.lang.Object r7 = r10.L$0
            kotlinx.coroutines.channels.ProducerScope r7 = (kotlinx.coroutines.channels.ProducerScope) r7
            kotlin.ResultKt.b(r11)
            goto L94
        L3a:
            int r1 = r10.I$0
            java.lang.Object r5 = r10.L$1
            kotlinx.coroutines.channels.ChannelIterator r5 = (kotlinx.coroutines.channels.ChannelIterator) r5
            java.lang.Object r6 = r10.L$0
            kotlinx.coroutines.channels.ProducerScope r6 = (kotlinx.coroutines.channels.ProducerScope) r6
            kotlin.ResultKt.b(r11)
            goto L6b
        L48:
            kotlin.ResultKt.b(r11)
            java.lang.Object r11 = r10.L$0
            kotlinx.coroutines.channels.ProducerScope r11 = (kotlinx.coroutines.channels.ProducerScope) r11
            kotlinx.coroutines.channels.ReceiveChannel<java.lang.Object> r1 = r10.$this_mapIndexed
            kotlinx.coroutines.channels.ChannelIterator r1 = r1.iterator()
            r5 = 0
            r9 = r5
            r5 = r1
            r1 = r9
        L59:
            r10.L$0 = r11
            r10.L$1 = r5
            r10.I$0 = r1
            r10.label = r4
            java.lang.Object r6 = r5.a(r10)
            if (r6 != r0) goto L68
            return r0
        L68:
            r9 = r6
            r6 = r11
            r11 = r9
        L6b:
            java.lang.Boolean r11 = (java.lang.Boolean) r11
            boolean r11 = r11.booleanValue()
            if (r11 == 0) goto La9
            java.lang.Object r11 = r5.next()
            kotlin.jvm.functions.Function3<java.lang.Integer, java.lang.Object, kotlin.coroutines.Continuation<java.lang.Object>, java.lang.Object> r7 = r10.$transform
            int r8 = r1 + 1
            java.lang.Integer r1 = kotlin.coroutines.jvm.internal.Boxing.b(r1)
            r10.L$0 = r6
            r10.L$1 = r5
            r10.L$2 = r6
            r10.I$0 = r8
            r10.label = r3
            java.lang.Object r11 = r7.u(r1, r11, r10)
            if (r11 != r0) goto L90
            return r0
        L90:
            r7 = r6
            r1 = r8
            r6 = r5
            r5 = r7
        L94:
            r10.L$0 = r7
            r10.L$1 = r6
            r8 = 0
            r10.L$2 = r8
            r10.I$0 = r1
            r10.label = r2
            java.lang.Object r11 = r5.M(r11, r10)
            if (r11 != r0) goto La6
            return r0
        La6:
            r5 = r6
            r11 = r7
            goto L59
        La9:
            kotlin.Unit r10 = kotlin.Unit.f18288a
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$mapIndexed$1.v(java.lang.Object):java.lang.Object");
    }
}
