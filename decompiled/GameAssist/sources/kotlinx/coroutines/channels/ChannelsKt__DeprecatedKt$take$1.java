package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

@Metadata
@DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$take$1", f = "Deprecated.kt", l = {254, 255}, m = "invokeSuspend")
/* loaded from: classes2.dex */
final class ChannelsKt__DeprecatedKt$take$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ int $n;
    final /* synthetic */ ReceiveChannel $this_take;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ChannelsKt__DeprecatedKt$take$1(int i2, ReceiveChannel receiveChannel, Continuation continuation) {
        super(2, continuation);
        this.$n = i2;
        this.$this_take = receiveChannel;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: A, reason: merged with bridge method [inline-methods] */
    public final Object y(ProducerScope producerScope, Continuation continuation) {
        return ((ChannelsKt__DeprecatedKt$take$1) o(producerScope, continuation)).v(Unit.f18288a);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation o(Object obj, Continuation continuation) {
        ChannelsKt__DeprecatedKt$take$1 channelsKt__DeprecatedKt$take$1 = new ChannelsKt__DeprecatedKt$take$1(this.$n, this.$this_take, continuation);
        channelsKt__DeprecatedKt$take$1.L$0 = obj;
        return channelsKt__DeprecatedKt$take$1;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x005c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x007f  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x0078 -> B:6:0x001b). Please report as a decompilation issue!!! */
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
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L33
            if (r1 == r3) goto L25
            if (r1 != r2) goto L1d
            int r1 = r7.I$0
            java.lang.Object r4 = r7.L$1
            kotlinx.coroutines.channels.ChannelIterator r4 = (kotlinx.coroutines.channels.ChannelIterator) r4
            java.lang.Object r5 = r7.L$0
            kotlinx.coroutines.channels.ProducerScope r5 = (kotlinx.coroutines.channels.ProducerScope) r5
            kotlin.ResultKt.b(r8)
        L1b:
            r8 = r5
            goto L7b
        L1d:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L25:
            int r1 = r7.I$0
            java.lang.Object r4 = r7.L$1
            kotlinx.coroutines.channels.ChannelIterator r4 = (kotlinx.coroutines.channels.ChannelIterator) r4
            java.lang.Object r5 = r7.L$0
            kotlinx.coroutines.channels.ProducerScope r5 = (kotlinx.coroutines.channels.ProducerScope) r5
            kotlin.ResultKt.b(r8)
            goto L60
        L33:
            kotlin.ResultKt.b(r8)
            java.lang.Object r8 = r7.L$0
            kotlinx.coroutines.channels.ProducerScope r8 = (kotlinx.coroutines.channels.ProducerScope) r8
            int r1 = r7.$n
            if (r1 != 0) goto L41
            kotlin.Unit r7 = kotlin.Unit.f18288a
            return r7
        L41:
            if (r1 < 0) goto L45
            r4 = r3
            goto L46
        L45:
            r4 = 0
        L46:
            if (r4 == 0) goto L85
            kotlinx.coroutines.channels.ReceiveChannel r4 = r7.$this_take
            kotlinx.coroutines.channels.ChannelIterator r4 = r4.iterator()
        L4e:
            r7.L$0 = r8
            r7.L$1 = r4
            r7.I$0 = r1
            r7.label = r3
            java.lang.Object r5 = r4.a(r7)
            if (r5 != r0) goto L5d
            return r0
        L5d:
            r6 = r5
            r5 = r8
            r8 = r6
        L60:
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            boolean r8 = r8.booleanValue()
            if (r8 == 0) goto L82
            java.lang.Object r8 = r4.next()
            r7.L$0 = r5
            r7.L$1 = r4
            r7.I$0 = r1
            r7.label = r2
            java.lang.Object r8 = r5.M(r8, r7)
            if (r8 != r0) goto L1b
            return r0
        L7b:
            int r1 = r1 + (-1)
            if (r1 != 0) goto L4e
            kotlin.Unit r7 = kotlin.Unit.f18288a
            return r7
        L82:
            kotlin.Unit r7 = kotlin.Unit.f18288a
            return r7
        L85:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "Requested element count "
            r7.append(r8)
            r7.append(r1)
            java.lang.String r8 = " is less than zero."
            r7.append(r8)
            java.lang.String r7 = r7.toString()
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.String r7 = r7.toString()
            r8.<init>(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$take$1.v(java.lang.Object):java.lang.Object");
    }
}
