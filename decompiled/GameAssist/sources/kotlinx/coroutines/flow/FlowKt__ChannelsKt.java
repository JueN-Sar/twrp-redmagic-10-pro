package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlinx.coroutines.channels.ReceiveChannel;

@Metadata
/* loaded from: classes2.dex */
final /* synthetic */ class FlowKt__ChannelsKt {
    public static final Object b(FlowCollector flowCollector, ReceiveChannel receiveChannel, Continuation continuation) {
        Object d2;
        Object c2 = c(flowCollector, receiveChannel, true, continuation);
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        return c2 == d2 ? c2 : Unit.f18288a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:17:0x006b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0075 A[Catch: all -> 0x0039, TRY_LEAVE, TryCatch #1 {all -> 0x0039, blocks: (B:12:0x0032, B:20:0x006f, B:22:0x0075, B:28:0x0084, B:30:0x0085, B:46:0x004d), top: B:7:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0085 A[Catch: all -> 0x0039, TRY_LEAVE, TryCatch #1 {all -> 0x0039, blocks: (B:12:0x0032, B:20:0x006f, B:22:0x0075, B:28:0x0084, B:30:0x0085, B:46:0x004d), top: B:7:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0024  */
    /* JADX WARN: Type inference failed for: r6v0, types: [kotlinx.coroutines.flow.FlowCollector] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v18 */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v3, types: [boolean] */
    /* JADX WARN: Type inference failed for: r6v8 */
    /* JADX WARN: Type inference failed for: r6v9 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:31:0x0095 -> B:13:0x0035). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object c(kotlinx.coroutines.flow.FlowCollector r6, kotlinx.coroutines.channels.ReceiveChannel r7, boolean r8, kotlin.coroutines.Continuation r9) {
        /*
            boolean r0 = r9 instanceof kotlinx.coroutines.flow.FlowKt__ChannelsKt$emitAllImpl$1
            if (r0 == 0) goto L13
            r0 = r9
            kotlinx.coroutines.flow.FlowKt__ChannelsKt$emitAllImpl$1 r0 = (kotlinx.coroutines.flow.FlowKt__ChannelsKt$emitAllImpl$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.flow.FlowKt__ChannelsKt$emitAllImpl$1 r0 = new kotlinx.coroutines.flow.FlowKt__ChannelsKt$emitAllImpl$1
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.d()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L57
            if (r2 == r4) goto L43
            if (r2 != r3) goto L3b
            boolean r6 = r0.Z$0
            java.lang.Object r7 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.flow.FlowCollector r8 = (kotlinx.coroutines.flow.FlowCollector) r8
            kotlin.ResultKt.b(r9)     // Catch: java.lang.Throwable -> L39
        L35:
            r5 = r8
            r8 = r6
            r6 = r5
            goto L5d
        L39:
            r8 = move-exception
            goto L9c
        L3b:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L43:
            boolean r6 = r0.Z$0
            java.lang.Object r7 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.flow.FlowCollector r8 = (kotlinx.coroutines.flow.FlowCollector) r8
            kotlin.ResultKt.b(r9)     // Catch: java.lang.Throwable -> L39
            kotlinx.coroutines.channels.ChannelResult r9 = (kotlinx.coroutines.channels.ChannelResult) r9     // Catch: java.lang.Throwable -> L39
            java.lang.Object r9 = r9.k()     // Catch: java.lang.Throwable -> L39
            goto L6f
        L57:
            kotlin.ResultKt.b(r9)
            kotlinx.coroutines.flow.FlowKt.l(r6)
        L5d:
            r0.L$0 = r6     // Catch: java.lang.Throwable -> L98
            r0.L$1 = r7     // Catch: java.lang.Throwable -> L98
            r0.Z$0 = r8     // Catch: java.lang.Throwable -> L98
            r0.label = r4     // Catch: java.lang.Throwable -> L98
            java.lang.Object r9 = r7.z(r0)     // Catch: java.lang.Throwable -> L98
            if (r9 != r1) goto L6c
            return r1
        L6c:
            r5 = r8
            r8 = r6
            r6 = r5
        L6f:
            boolean r2 = kotlinx.coroutines.channels.ChannelResult.i(r9)     // Catch: java.lang.Throwable -> L39
            if (r2 == 0) goto L85
            java.lang.Throwable r8 = kotlinx.coroutines.channels.ChannelResult.e(r9)     // Catch: java.lang.Throwable -> L39
            if (r8 != 0) goto L84
            if (r6 == 0) goto L81
            r6 = 0
            kotlinx.coroutines.channels.ChannelsKt.b(r7, r6)
        L81:
            kotlin.Unit r6 = kotlin.Unit.f18288a
            return r6
        L84:
            throw r8     // Catch: java.lang.Throwable -> L39
        L85:
            java.lang.Object r9 = kotlinx.coroutines.channels.ChannelResult.g(r9)     // Catch: java.lang.Throwable -> L39
            r0.L$0 = r8     // Catch: java.lang.Throwable -> L39
            r0.L$1 = r7     // Catch: java.lang.Throwable -> L39
            r0.Z$0 = r6     // Catch: java.lang.Throwable -> L39
            r0.label = r3     // Catch: java.lang.Throwable -> L39
            java.lang.Object r9 = r8.k(r9, r0)     // Catch: java.lang.Throwable -> L39
            if (r9 != r1) goto L35
            return r1
        L98:
            r6 = move-exception
            r5 = r8
            r8 = r6
            r6 = r5
        L9c:
            throw r8     // Catch: java.lang.Throwable -> L9d
        L9d:
            r9 = move-exception
            if (r6 == 0) goto La3
            kotlinx.coroutines.channels.ChannelsKt.b(r7, r8)
        La3:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__ChannelsKt.c(kotlinx.coroutines.flow.FlowCollector, kotlinx.coroutines.channels.ReceiveChannel, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
