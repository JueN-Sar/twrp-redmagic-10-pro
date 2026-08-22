package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.time.Duration;

@Metadata
/* loaded from: classes2.dex */
public final class DelayKt {
    /* JADX WARN: Removed duplicated region for block: B:15:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object a(kotlin.coroutines.Continuation r4) {
        /*
            boolean r0 = r4 instanceof kotlinx.coroutines.DelayKt$awaitCancellation$1
            if (r0 == 0) goto L13
            r0 = r4
            kotlinx.coroutines.DelayKt$awaitCancellation$1 r0 = (kotlinx.coroutines.DelayKt$awaitCancellation$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.DelayKt$awaitCancellation$1 r0 = new kotlinx.coroutines.DelayKt$awaitCancellation$1
            r0.<init>(r4)
        L18:
            java.lang.Object r4 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.d()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 == r3) goto L2d
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r0)
            throw r4
        L2d:
            kotlin.ResultKt.b(r4)
            goto L52
        L31:
            kotlin.ResultKt.b(r4)
            r0.label = r3
            kotlinx.coroutines.CancellableContinuationImpl r4 = new kotlinx.coroutines.CancellableContinuationImpl
            kotlin.coroutines.Continuation r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.c(r0)
            r4.<init>(r2, r3)
            r4.z()
            java.lang.Object r4 = r4.w()
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.d()
            if (r4 != r2) goto L4f
            kotlin.coroutines.jvm.internal.DebugProbesKt.c(r0)
        L4f:
            if (r4 != r1) goto L52
            return r1
        L52:
            kotlin.KotlinNothingValueException r4 = new kotlin.KotlinNothingValueException
            r4.<init>()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.DelayKt.a(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final Object b(long j2, Continuation continuation) {
        Continuation c2;
        Object d2;
        Object d3;
        if (j2 <= 0) {
            return Unit.f18288a;
        }
        c2 = IntrinsicsKt__IntrinsicsJvmKt.c(continuation);
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(c2, 1);
        cancellableContinuationImpl.z();
        if (j2 < Long.MAX_VALUE) {
            c(cancellableContinuationImpl.getContext()).k(j2, cancellableContinuationImpl);
        }
        Object w = cancellableContinuationImpl.w();
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        if (w == d2) {
            DebugProbesKt.c(continuation);
        }
        d3 = IntrinsicsKt__IntrinsicsKt.d();
        return w == d3 ? w : Unit.f18288a;
    }

    public static final Delay c(CoroutineContext coroutineContext) {
        CoroutineContext.Element c2 = coroutineContext.c(ContinuationInterceptor.f18409d);
        Delay delay = c2 instanceof Delay ? (Delay) c2 : null;
        return delay == null ? DefaultExecutorKt.a() : delay;
    }

    public static final long d(long j2) {
        long b2;
        if (Duration.h(j2, Duration.f18801h.a()) <= 0) {
            return 0L;
        }
        b2 = RangesKt___RangesKt.b(Duration.t(j2), 1L);
        return b2;
    }
}
