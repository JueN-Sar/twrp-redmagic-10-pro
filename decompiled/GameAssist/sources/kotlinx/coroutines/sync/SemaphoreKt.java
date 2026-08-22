package kotlinx.coroutines.sync;

import kotlin.Metadata;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.internal.SystemPropsKt__SystemProps_commonKt;

@Metadata
/* loaded from: classes2.dex */
public final class SemaphoreKt {

    /* renamed from: a, reason: collision with root package name */
    private static final int f19526a;

    /* renamed from: b, reason: collision with root package name */
    private static final Symbol f19527b;

    /* renamed from: c, reason: collision with root package name */
    private static final Symbol f19528c;

    /* renamed from: d, reason: collision with root package name */
    private static final Symbol f19529d;

    /* renamed from: e, reason: collision with root package name */
    private static final Symbol f19530e;

    /* renamed from: f, reason: collision with root package name */
    private static final int f19531f;

    static {
        int d2;
        int d3;
        d2 = SystemPropsKt__SystemProps_commonKt.d("kotlinx.coroutines.semaphore.maxSpinCycles", 100, 0, 0, 12, null);
        f19526a = d2;
        f19527b = new Symbol("PERMIT");
        f19528c = new Symbol("TAKEN");
        f19529d = new Symbol("BROKEN");
        f19530e = new Symbol("CANCELLED");
        d3 = SystemPropsKt__SystemProps_commonKt.d("kotlinx.coroutines.semaphore.segmentSize", 16, 0, 0, 12, null);
        f19531f = d3;
    }

    public static final Semaphore a(int i2, int i3) {
        return new SemaphoreImpl(i2, i3);
    }

    public static /* synthetic */ Semaphore b(int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i3 = 0;
        }
        return a(i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SemaphoreSegment j(long j2, SemaphoreSegment semaphoreSegment) {
        return new SemaphoreSegment(j2, semaphoreSegment, 0);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object k(kotlinx.coroutines.sync.Semaphore r4, kotlin.jvm.functions.Function0 r5, kotlin.coroutines.Continuation r6) {
        /*
            boolean r0 = r6 instanceof kotlinx.coroutines.sync.SemaphoreKt$withPermit$1
            if (r0 == 0) goto L13
            r0 = r6
            kotlinx.coroutines.sync.SemaphoreKt$withPermit$1 r0 = (kotlinx.coroutines.sync.SemaphoreKt$withPermit$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.sync.SemaphoreKt$withPermit$1 r0 = new kotlinx.coroutines.sync.SemaphoreKt$withPermit$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.d()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3a
            if (r2 != r3) goto L32
            java.lang.Object r4 = r0.L$1
            r5 = r4
            kotlin.jvm.functions.Function0 r5 = (kotlin.jvm.functions.Function0) r5
            java.lang.Object r4 = r0.L$0
            kotlinx.coroutines.sync.Semaphore r4 = (kotlinx.coroutines.sync.Semaphore) r4
            kotlin.ResultKt.b(r6)
            goto L4a
        L32:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L3a:
            kotlin.ResultKt.b(r6)
            r0.L$0 = r4
            r0.L$1 = r5
            r0.label = r3
            java.lang.Object r6 = r4.a(r0)
            if (r6 != r1) goto L4a
            return r1
        L4a:
            java.lang.Object r5 = r5.a()     // Catch: java.lang.Throwable -> L58
            kotlin.jvm.internal.InlineMarker.b(r3)
            r4.release()
            kotlin.jvm.internal.InlineMarker.a(r3)
            return r5
        L58:
            r5 = move-exception
            kotlin.jvm.internal.InlineMarker.b(r3)
            r4.release()
            kotlin.jvm.internal.InlineMarker.a(r3)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.sync.SemaphoreKt.k(kotlinx.coroutines.sync.Semaphore, kotlin.jvm.functions.Function0, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
