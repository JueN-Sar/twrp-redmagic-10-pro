package kotlinx.coroutines.channels;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlinx.coroutines.ExceptionsKt;

@Metadata
/* loaded from: classes2.dex */
final /* synthetic */ class ChannelsKt__Channels_commonKt {
    public static final void a(ReceiveChannel receiveChannel, Throwable th) {
        if (th != null) {
            r0 = th instanceof CancellationException ? (CancellationException) th : null;
            if (r0 == null) {
                r0 = ExceptionsKt.a("Channel was consumed, consumer had failed", th);
            }
        }
        receiveChannel.a(r0);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x006d A[Catch: all -> 0x0077, TryCatch #0 {all -> 0x0077, blocks: (B:15:0x0065, B:17:0x006d, B:29:0x007a), top: B:14:0x0065 }] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0060 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x007a A[Catch: all -> 0x0077, TRY_LEAVE, TryCatch #0 {all -> 0x0077, blocks: (B:15:0x0065, B:17:0x006d, B:29:0x007a), top: B:14:0x0065 }] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x0061 -> B:13:0x0038). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object b(kotlinx.coroutines.channels.BroadcastChannel r6, kotlin.jvm.functions.Function1 r7, kotlin.coroutines.Continuation r8) {
        /*
            boolean r0 = r8 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$consumeEach$3
            if (r0 == 0) goto L13
            r0 = r8
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$consumeEach$3 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$consumeEach$3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$consumeEach$3 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$consumeEach$3
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.d()
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L44
            if (r2 != r4) goto L3c
            java.lang.Object r6 = r0.L$2
            kotlinx.coroutines.channels.ChannelIterator r6 = (kotlinx.coroutines.channels.ChannelIterator) r6
            java.lang.Object r7 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r2 = r0.L$0
            kotlin.jvm.functions.Function1 r2 = (kotlin.jvm.functions.Function1) r2
            kotlin.ResultKt.b(r8)     // Catch: java.lang.Throwable -> L3a
            r5 = r0
            r0 = r7
            r7 = r2
        L38:
            r2 = r5
            goto L65
        L3a:
            r6 = move-exception
            goto L8f
        L3c:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L44:
            kotlin.ResultKt.b(r8)
            kotlinx.coroutines.channels.ReceiveChannel r6 = r6.q()
            kotlinx.coroutines.channels.ChannelIterator r8 = r6.iterator()     // Catch: java.lang.Throwable -> L8b
            r5 = r8
            r8 = r6
            r6 = r5
        L52:
            r0.L$0 = r7     // Catch: java.lang.Throwable -> L88
            r0.L$1 = r8     // Catch: java.lang.Throwable -> L88
            r0.L$2 = r6     // Catch: java.lang.Throwable -> L88
            r0.label = r4     // Catch: java.lang.Throwable -> L88
            java.lang.Object r2 = r6.a(r0)     // Catch: java.lang.Throwable -> L88
            if (r2 != r1) goto L61
            return r1
        L61:
            r5 = r0
            r0 = r8
            r8 = r2
            goto L38
        L65:
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch: java.lang.Throwable -> L77
            boolean r8 = r8.booleanValue()     // Catch: java.lang.Throwable -> L77
            if (r8 == 0) goto L7a
            java.lang.Object r8 = r6.next()     // Catch: java.lang.Throwable -> L77
            r7.c(r8)     // Catch: java.lang.Throwable -> L77
            r8 = r0
            r0 = r2
            goto L52
        L77:
            r6 = move-exception
            r7 = r0
            goto L8f
        L7a:
            kotlin.Unit r6 = kotlin.Unit.f18288a     // Catch: java.lang.Throwable -> L77
            kotlin.jvm.internal.InlineMarker.b(r4)
            kotlinx.coroutines.channels.ReceiveChannel.DefaultImpls.a(r0, r3, r4, r3)
            kotlin.jvm.internal.InlineMarker.a(r4)
            kotlin.Unit r6 = kotlin.Unit.f18288a
            return r6
        L88:
            r6 = move-exception
            r7 = r8
            goto L8f
        L8b:
            r7 = move-exception
            r5 = r7
            r7 = r6
            r6 = r5
        L8f:
            kotlin.jvm.internal.InlineMarker.b(r4)
            kotlinx.coroutines.channels.ReceiveChannel.DefaultImpls.a(r7, r3, r4, r3)
            kotlin.jvm.internal.InlineMarker.a(r4)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.b(kotlinx.coroutines.channels.BroadcastChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0064 A[Catch: all -> 0x0035, TryCatch #1 {all -> 0x0035, blocks: (B:11:0x0031, B:12:0x005c, B:14:0x0064, B:15:0x004a, B:20:0x006d), top: B:10:0x0031 }] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0058 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x006d A[Catch: all -> 0x0035, TRY_LEAVE, TryCatch #1 {all -> 0x0035, blocks: (B:11:0x0031, B:12:0x005c, B:14:0x0064, B:15:0x004a, B:20:0x006d), top: B:10:0x0031 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x0059 -> B:12:0x005c). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object c(kotlinx.coroutines.channels.ReceiveChannel r5, kotlin.jvm.functions.Function1 r6, kotlin.coroutines.Continuation r7) {
        /*
            boolean r0 = r7 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$consumeEach$1
            if (r0 == 0) goto L13
            r0 = r7
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$consumeEach$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$consumeEach$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$consumeEach$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$consumeEach$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.d()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3f
            if (r2 != r3) goto L37
            java.lang.Object r5 = r0.L$2
            kotlinx.coroutines.channels.ChannelIterator r5 = (kotlinx.coroutines.channels.ChannelIterator) r5
            java.lang.Object r6 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r6 = (kotlinx.coroutines.channels.ReceiveChannel) r6
            java.lang.Object r2 = r0.L$0
            kotlin.jvm.functions.Function1 r2 = (kotlin.jvm.functions.Function1) r2
            kotlin.ResultKt.b(r7)     // Catch: java.lang.Throwable -> L35
            goto L5c
        L35:
            r5 = move-exception
            goto L80
        L37:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L3f:
            kotlin.ResultKt.b(r7)
            kotlinx.coroutines.channels.ChannelIterator r7 = r5.iterator()     // Catch: java.lang.Throwable -> L7c
            r4 = r6
            r6 = r5
            r5 = r7
            r7 = r4
        L4a:
            r0.L$0 = r7     // Catch: java.lang.Throwable -> L35
            r0.L$1 = r6     // Catch: java.lang.Throwable -> L35
            r0.L$2 = r5     // Catch: java.lang.Throwable -> L35
            r0.label = r3     // Catch: java.lang.Throwable -> L35
            java.lang.Object r2 = r5.a(r0)     // Catch: java.lang.Throwable -> L35
            if (r2 != r1) goto L59
            return r1
        L59:
            r4 = r2
            r2 = r7
            r7 = r4
        L5c:
            java.lang.Boolean r7 = (java.lang.Boolean) r7     // Catch: java.lang.Throwable -> L35
            boolean r7 = r7.booleanValue()     // Catch: java.lang.Throwable -> L35
            if (r7 == 0) goto L6d
            java.lang.Object r7 = r5.next()     // Catch: java.lang.Throwable -> L35
            r2.c(r7)     // Catch: java.lang.Throwable -> L35
            r7 = r2
            goto L4a
        L6d:
            kotlin.Unit r5 = kotlin.Unit.f18288a     // Catch: java.lang.Throwable -> L35
            kotlin.jvm.internal.InlineMarker.b(r3)
            r5 = 0
            kotlinx.coroutines.channels.ChannelsKt.b(r6, r5)
            kotlin.jvm.internal.InlineMarker.a(r3)
            kotlin.Unit r5 = kotlin.Unit.f18288a
            return r5
        L7c:
            r6 = move-exception
            r4 = r6
            r6 = r5
            r5 = r4
        L80:
            throw r5     // Catch: java.lang.Throwable -> L81
        L81:
            r7 = move-exception
            kotlin.jvm.internal.InlineMarker.b(r3)
            kotlinx.coroutines.channels.ChannelsKt.b(r6, r5)
            kotlin.jvm.internal.InlineMarker.a(r3)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.c(kotlinx.coroutines.channels.ReceiveChannel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x006f A[Catch: all -> 0x0039, TryCatch #2 {all -> 0x0039, blocks: (B:11:0x0035, B:12:0x0067, B:14:0x006f, B:29:0x0078), top: B:10:0x0035 }] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0063 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0078 A[Catch: all -> 0x0039, TRY_LEAVE, TryCatch #2 {all -> 0x0039, blocks: (B:11:0x0035, B:12:0x0067, B:14:0x006f, B:29:0x0078), top: B:10:0x0035 }] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x0064 -> B:12:0x0067). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object d(kotlinx.coroutines.channels.ReceiveChannel r7, kotlin.coroutines.Continuation r8) {
        /*
            boolean r0 = r8 instanceof kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$toList$1
            if (r0 == 0) goto L13
            r0 = r8
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$toList$1 r0 = (kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$toList$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$toList$1 r0 = new kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$toList$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.d()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L44
            if (r2 != r3) goto L3c
            java.lang.Object r7 = r0.L$3
            kotlinx.coroutines.channels.ChannelIterator r7 = (kotlinx.coroutines.channels.ChannelIterator) r7
            java.lang.Object r2 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r2 = (kotlinx.coroutines.channels.ReceiveChannel) r2
            java.lang.Object r4 = r0.L$1
            java.util.List r4 = (java.util.List) r4
            java.lang.Object r5 = r0.L$0
            java.util.List r5 = (java.util.List) r5
            kotlin.ResultKt.b(r8)     // Catch: java.lang.Throwable -> L39
            goto L67
        L39:
            r7 = move-exception
            r8 = r2
            goto L89
        L3c:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L44:
            kotlin.ResultKt.b(r8)
            java.util.List r8 = kotlin.collections.CollectionsKt.c()
            kotlinx.coroutines.channels.ChannelIterator r2 = r7.iterator()     // Catch: java.lang.Throwable -> L85
            r4 = r8
            r5 = r4
            r8 = r7
            r7 = r2
        L53:
            r0.L$0 = r5     // Catch: java.lang.Throwable -> L83
            r0.L$1 = r4     // Catch: java.lang.Throwable -> L83
            r0.L$2 = r8     // Catch: java.lang.Throwable -> L83
            r0.L$3 = r7     // Catch: java.lang.Throwable -> L83
            r0.label = r3     // Catch: java.lang.Throwable -> L83
            java.lang.Object r2 = r7.a(r0)     // Catch: java.lang.Throwable -> L83
            if (r2 != r1) goto L64
            return r1
        L64:
            r6 = r2
            r2 = r8
            r8 = r6
        L67:
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch: java.lang.Throwable -> L39
            boolean r8 = r8.booleanValue()     // Catch: java.lang.Throwable -> L39
            if (r8 == 0) goto L78
            java.lang.Object r8 = r7.next()     // Catch: java.lang.Throwable -> L39
            r4.add(r8)     // Catch: java.lang.Throwable -> L39
            r8 = r2
            goto L53
        L78:
            kotlin.Unit r7 = kotlin.Unit.f18288a     // Catch: java.lang.Throwable -> L39
            r7 = 0
            kotlinx.coroutines.channels.ChannelsKt.b(r2, r7)
            java.util.List r7 = kotlin.collections.CollectionsKt.a(r5)
            return r7
        L83:
            r7 = move-exception
            goto L89
        L85:
            r8 = move-exception
            r6 = r8
            r8 = r7
            r7 = r6
        L89:
            throw r7     // Catch: java.lang.Throwable -> L8a
        L8a:
            r0 = move-exception
            kotlinx.coroutines.channels.ChannelsKt.b(r8, r7)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.d(kotlinx.coroutines.channels.ReceiveChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
