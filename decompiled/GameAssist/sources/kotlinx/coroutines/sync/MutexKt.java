package kotlinx.coroutines.sync;

import kotlin.Metadata;
import kotlinx.coroutines.internal.Symbol;

@Metadata
/* loaded from: classes2.dex */
public final class MutexKt {

    /* renamed from: a, reason: collision with root package name */
    private static final Symbol f19513a = new Symbol("LOCK_FAIL");

    /* renamed from: b, reason: collision with root package name */
    private static final Symbol f19514b = new Symbol("UNLOCK_FAIL");

    /* renamed from: c, reason: collision with root package name */
    private static final Symbol f19515c;

    /* renamed from: d, reason: collision with root package name */
    private static final Symbol f19516d;

    /* renamed from: e, reason: collision with root package name */
    private static final Empty f19517e;

    /* renamed from: f, reason: collision with root package name */
    private static final Empty f19518f;

    static {
        Symbol symbol = new Symbol("LOCKED");
        f19515c = symbol;
        Symbol symbol2 = new Symbol("UNLOCKED");
        f19516d = symbol2;
        f19517e = new Empty(symbol);
        f19518f = new Empty(symbol2);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object g(kotlinx.coroutines.sync.Mutex r4, java.lang.Object r5, kotlin.jvm.functions.Function0 r6, kotlin.coroutines.Continuation r7) {
        /*
            boolean r0 = r7 instanceof kotlinx.coroutines.sync.MutexKt$withLock$1
            if (r0 == 0) goto L13
            r0 = r7
            kotlinx.coroutines.sync.MutexKt$withLock$1 r0 = (kotlinx.coroutines.sync.MutexKt$withLock$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.sync.MutexKt$withLock$1 r0 = new kotlinx.coroutines.sync.MutexKt$withLock$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.d()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3c
            if (r2 != r3) goto L34
            java.lang.Object r4 = r0.L$2
            r6 = r4
            kotlin.jvm.functions.Function0 r6 = (kotlin.jvm.functions.Function0) r6
            java.lang.Object r5 = r0.L$1
            java.lang.Object r4 = r0.L$0
            kotlinx.coroutines.sync.Mutex r4 = (kotlinx.coroutines.sync.Mutex) r4
            kotlin.ResultKt.b(r7)
            goto L4e
        L34:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L3c:
            kotlin.ResultKt.b(r7)
            r0.L$0 = r4
            r0.L$1 = r5
            r0.L$2 = r6
            r0.label = r3
            java.lang.Object r7 = r4.a(r5, r0)
            if (r7 != r1) goto L4e
            return r1
        L4e:
            java.lang.Object r6 = r6.a()     // Catch: java.lang.Throwable -> L5c
            kotlin.jvm.internal.InlineMarker.b(r3)
            r4.b(r5)
            kotlin.jvm.internal.InlineMarker.a(r3)
            return r6
        L5c:
            r6 = move-exception
            kotlin.jvm.internal.InlineMarker.b(r3)
            r4.b(r5)
            kotlin.jvm.internal.InlineMarker.a(r3)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.sync.MutexKt.g(kotlinx.coroutines.sync.Mutex, java.lang.Object, kotlin.jvm.functions.Function0, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
