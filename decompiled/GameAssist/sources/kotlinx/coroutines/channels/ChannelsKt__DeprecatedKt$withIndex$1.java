package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

@Metadata
@DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$withIndex$1", f = "Deprecated.kt", l = {370, 371}, m = "invokeSuspend")
/* loaded from: classes2.dex */
final class ChannelsKt__DeprecatedKt$withIndex$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ ReceiveChannel $this_withIndex;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ChannelsKt__DeprecatedKt$withIndex$1(ReceiveChannel receiveChannel, Continuation continuation) {
        super(2, continuation);
        this.$this_withIndex = receiveChannel;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: A, reason: merged with bridge method [inline-methods] */
    public final Object y(ProducerScope producerScope, Continuation continuation) {
        return ((ChannelsKt__DeprecatedKt$withIndex$1) o(producerScope, continuation)).v(Unit.f18288a);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation o(Object obj, Continuation continuation) {
        ChannelsKt__DeprecatedKt$withIndex$1 channelsKt__DeprecatedKt$withIndex$1 = new ChannelsKt__DeprecatedKt$withIndex$1(this.$this_withIndex, continuation);
        channelsKt__DeprecatedKt$withIndex$1.L$0 = obj;
        return channelsKt__DeprecatedKt$withIndex$1;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x007f  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x007b -> B:6:0x0044). Please report as a decompilation issue!!! */
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
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L36
            if (r1 == r3) goto L28
            if (r1 != r2) goto L20
            int r1 = r10.I$0
            java.lang.Object r4 = r10.L$1
            kotlinx.coroutines.channels.ChannelIterator r4 = (kotlinx.coroutines.channels.ChannelIterator) r4
            java.lang.Object r5 = r10.L$0
            kotlinx.coroutines.channels.ProducerScope r5 = (kotlinx.coroutines.channels.ProducerScope) r5
            kotlin.ResultKt.b(r11)
            r11 = r5
            r8 = r4
            r4 = r1
            r1 = r8
            goto L44
        L20:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L28:
            int r1 = r10.I$0
            java.lang.Object r4 = r10.L$1
            kotlinx.coroutines.channels.ChannelIterator r4 = (kotlinx.coroutines.channels.ChannelIterator) r4
            java.lang.Object r5 = r10.L$0
            kotlinx.coroutines.channels.ProducerScope r5 = (kotlinx.coroutines.channels.ProducerScope) r5
            kotlin.ResultKt.b(r11)
            goto L59
        L36:
            kotlin.ResultKt.b(r11)
            java.lang.Object r11 = r10.L$0
            kotlinx.coroutines.channels.ProducerScope r11 = (kotlinx.coroutines.channels.ProducerScope) r11
            kotlinx.coroutines.channels.ReceiveChannel r1 = r10.$this_withIndex
            kotlinx.coroutines.channels.ChannelIterator r1 = r1.iterator()
            r4 = 0
        L44:
            r10.L$0 = r11
            r10.L$1 = r1
            r10.I$0 = r4
            r10.label = r3
            java.lang.Object r5 = r1.a(r10)
            if (r5 != r0) goto L53
            return r0
        L53:
            r8 = r5
            r5 = r11
            r11 = r8
            r9 = r4
            r4 = r1
            r1 = r9
        L59:
            java.lang.Boolean r11 = (java.lang.Boolean) r11
            boolean r11 = r11.booleanValue()
            if (r11 == 0) goto L7f
            java.lang.Object r11 = r4.next()
            kotlin.collections.IndexedValue r6 = new kotlin.collections.IndexedValue
            int r7 = r1 + 1
            r6.<init>(r1, r11)
            r10.L$0 = r5
            r10.L$1 = r4
            r10.I$0 = r7
            r10.label = r2
            java.lang.Object r11 = r5.M(r6, r10)
            if (r11 != r0) goto L7b
            return r0
        L7b:
            r1 = r4
            r11 = r5
            r4 = r7
            goto L44
        L7f:
            kotlin.Unit r10 = kotlin.Unit.f18288a
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$withIndex$1.v(java.lang.Object):java.lang.Object");
    }
}
