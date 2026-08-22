package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

@Metadata
@DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$drop$1", f = "Deprecated.kt", l = {164, 169, 170}, m = "invokeSuspend")
/* loaded from: classes2.dex */
final class ChannelsKt__DeprecatedKt$drop$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ int $n;
    final /* synthetic */ ReceiveChannel $this_drop;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ChannelsKt__DeprecatedKt$drop$1(int i2, ReceiveChannel receiveChannel, Continuation continuation) {
        super(2, continuation);
        this.$n = i2;
        this.$this_drop = receiveChannel;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: A, reason: merged with bridge method [inline-methods] */
    public final Object y(ProducerScope producerScope, Continuation continuation) {
        return ((ChannelsKt__DeprecatedKt$drop$1) o(producerScope, continuation)).v(Unit.f18288a);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation o(Object obj, Continuation continuation) {
        ChannelsKt__DeprecatedKt$drop$1 channelsKt__DeprecatedKt$drop$1 = new ChannelsKt__DeprecatedKt$drop$1(this.$n, this.$this_drop, continuation);
        channelsKt__DeprecatedKt$drop$1.L$0 = obj;
        return channelsKt__DeprecatedKt$drop$1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x0075, code lost:
    
        if (r1 == 0) goto L27;
     */
    /* JADX WARN: Removed duplicated region for block: B:10:0x008a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:12:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0070  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x00a4 -> B:7:0x001c). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:29:0x0065 -> B:24:0x0068). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object v(java.lang.Object r9) {
        /*
            r8 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.d()
            int r1 = r8.label
            r2 = 3
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L40
            if (r1 == r4) goto L32
            if (r1 == r3) goto L26
            if (r1 != r2) goto L1e
            java.lang.Object r1 = r8.L$1
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r4 = r8.L$0
            kotlinx.coroutines.channels.ProducerScope r4 = (kotlinx.coroutines.channels.ProducerScope) r4
            kotlin.ResultKt.b(r9)
        L1c:
            r9 = r4
            goto L7e
        L1e:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L26:
            java.lang.Object r1 = r8.L$1
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r4 = r8.L$0
            kotlinx.coroutines.channels.ProducerScope r4 = (kotlinx.coroutines.channels.ProducerScope) r4
            kotlin.ResultKt.b(r9)
            goto L8e
        L32:
            int r1 = r8.I$0
            java.lang.Object r5 = r8.L$1
            kotlinx.coroutines.channels.ChannelIterator r5 = (kotlinx.coroutines.channels.ChannelIterator) r5
            java.lang.Object r6 = r8.L$0
            kotlinx.coroutines.channels.ProducerScope r6 = (kotlinx.coroutines.channels.ProducerScope) r6
            kotlin.ResultKt.b(r9)
            goto L68
        L40:
            kotlin.ResultKt.b(r9)
            java.lang.Object r9 = r8.L$0
            kotlinx.coroutines.channels.ProducerScope r9 = (kotlinx.coroutines.channels.ProducerScope) r9
            int r1 = r8.$n
            if (r1 < 0) goto L4d
            r5 = r4
            goto L4e
        L4d:
            r5 = 0
        L4e:
            if (r5 == 0) goto Laa
            if (r1 <= 0) goto L78
            kotlinx.coroutines.channels.ReceiveChannel r5 = r8.$this_drop
            kotlinx.coroutines.channels.ChannelIterator r5 = r5.iterator()
            r6 = r9
        L59:
            r8.L$0 = r6
            r8.L$1 = r5
            r8.I$0 = r1
            r8.label = r4
            java.lang.Object r9 = r5.a(r8)
            if (r9 != r0) goto L68
            return r0
        L68:
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r9 = r9.booleanValue()
            if (r9 == 0) goto L77
            r5.next()
            int r1 = r1 + (-1)
            if (r1 != 0) goto L59
        L77:
            r9 = r6
        L78:
            kotlinx.coroutines.channels.ReceiveChannel r1 = r8.$this_drop
            kotlinx.coroutines.channels.ChannelIterator r1 = r1.iterator()
        L7e:
            r8.L$0 = r9
            r8.L$1 = r1
            r8.label = r3
            java.lang.Object r4 = r1.a(r8)
            if (r4 != r0) goto L8b
            return r0
        L8b:
            r7 = r4
            r4 = r9
            r9 = r7
        L8e:
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r9 = r9.booleanValue()
            if (r9 == 0) goto La7
            java.lang.Object r9 = r1.next()
            r8.L$0 = r4
            r8.L$1 = r1
            r8.label = r2
            java.lang.Object r9 = r4.M(r9, r8)
            if (r9 != r0) goto L1c
            return r0
        La7:
            kotlin.Unit r8 = kotlin.Unit.f18288a
            return r8
        Laa:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "Requested element count "
            r8.append(r9)
            r8.append(r1)
            java.lang.String r9 = " is less than zero."
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            java.lang.String r8 = r8.toString()
            r9.<init>(r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$drop$1.v(java.lang.Object):java.lang.Object");
    }
}
