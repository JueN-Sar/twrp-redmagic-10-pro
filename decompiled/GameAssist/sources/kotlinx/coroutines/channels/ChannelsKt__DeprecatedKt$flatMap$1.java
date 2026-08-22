package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

@Metadata
@DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$flatMap$1", f = "Deprecated.kt", l = {321, 322, 322}, m = "invokeSuspend")
/* loaded from: classes2.dex */
final class ChannelsKt__DeprecatedKt$flatMap$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ ReceiveChannel $this_flatMap;
    final /* synthetic */ Function2 $transform;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ChannelsKt__DeprecatedKt$flatMap$1(ReceiveChannel receiveChannel, Function2 function2, Continuation continuation) {
        super(2, continuation);
        this.$this_flatMap = receiveChannel;
        this.$transform = function2;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: A, reason: merged with bridge method [inline-methods] */
    public final Object y(ProducerScope producerScope, Continuation continuation) {
        return ((ChannelsKt__DeprecatedKt$flatMap$1) o(producerScope, continuation)).v(Unit.f18288a);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation o(Object obj, Continuation continuation) {
        ChannelsKt__DeprecatedKt$flatMap$1 channelsKt__DeprecatedKt$flatMap$1 = new ChannelsKt__DeprecatedKt$flatMap$1(this.$this_flatMap, this.$transform, continuation);
        channelsKt__DeprecatedKt$flatMap$1.L$0 = obj;
        return channelsKt__DeprecatedKt$flatMap$1;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0081 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0082  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x007f -> B:7:0x004b). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object v(java.lang.Object r8) {
        /*
            r7 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.d()
            int r1 = r7.label
            r2 = 3
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L3d
            if (r1 == r4) goto L31
            if (r1 == r3) goto L25
            if (r1 != r2) goto L1d
            java.lang.Object r1 = r7.L$1
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r5 = r7.L$0
            kotlinx.coroutines.channels.ProducerScope r5 = (kotlinx.coroutines.channels.ProducerScope) r5
            kotlin.ResultKt.b(r8)
            goto L4b
        L1d:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L25:
            java.lang.Object r1 = r7.L$1
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r5 = r7.L$0
            kotlinx.coroutines.channels.ProducerScope r5 = (kotlinx.coroutines.channels.ProducerScope) r5
            kotlin.ResultKt.b(r8)
            goto L73
        L31:
            java.lang.Object r1 = r7.L$1
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r5 = r7.L$0
            kotlinx.coroutines.channels.ProducerScope r5 = (kotlinx.coroutines.channels.ProducerScope) r5
            kotlin.ResultKt.b(r8)
            goto L58
        L3d:
            kotlin.ResultKt.b(r8)
            java.lang.Object r8 = r7.L$0
            kotlinx.coroutines.channels.ProducerScope r8 = (kotlinx.coroutines.channels.ProducerScope) r8
            kotlinx.coroutines.channels.ReceiveChannel r1 = r7.$this_flatMap
            kotlinx.coroutines.channels.ChannelIterator r1 = r1.iterator()
            r5 = r8
        L4b:
            r7.L$0 = r5
            r7.L$1 = r1
            r7.label = r4
            java.lang.Object r8 = r1.a(r7)
            if (r8 != r0) goto L58
            return r0
        L58:
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            boolean r8 = r8.booleanValue()
            if (r8 == 0) goto L82
            java.lang.Object r8 = r1.next()
            kotlin.jvm.functions.Function2 r6 = r7.$transform
            r7.L$0 = r5
            r7.L$1 = r1
            r7.label = r3
            java.lang.Object r8 = r6.y(r8, r7)
            if (r8 != r0) goto L73
            return r0
        L73:
            kotlinx.coroutines.channels.ReceiveChannel r8 = (kotlinx.coroutines.channels.ReceiveChannel) r8
            r7.L$0 = r5
            r7.L$1 = r1
            r7.label = r2
            java.lang.Object r8 = kotlinx.coroutines.channels.ChannelsKt.s(r8, r5, r7)
            if (r8 != r0) goto L4b
            return r0
        L82:
            kotlin.Unit r7 = kotlin.Unit.f18288a
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$flatMap$1.v(java.lang.Object):java.lang.Object");
    }
}
