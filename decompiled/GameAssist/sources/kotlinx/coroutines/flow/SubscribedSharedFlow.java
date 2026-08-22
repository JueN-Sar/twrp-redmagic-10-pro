package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.jvm.functions.Function2;

@Metadata
/* loaded from: classes2.dex */
final class SubscribedSharedFlow<T> implements SharedFlow<T> {

    /* renamed from: c, reason: collision with root package name */
    private final SharedFlow f19283c;

    /* renamed from: h, reason: collision with root package name */
    private final Function2 f19284h;

    /* JADX WARN: Removed duplicated region for block: B:15:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    @Override // kotlinx.coroutines.flow.SharedFlow, kotlinx.coroutines.flow.Flow
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object a(kotlinx.coroutines.flow.FlowCollector r5, kotlin.coroutines.Continuation r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof kotlinx.coroutines.flow.SubscribedSharedFlow$collect$1
            if (r0 == 0) goto L13
            r0 = r6
            kotlinx.coroutines.flow.SubscribedSharedFlow$collect$1 r0 = (kotlinx.coroutines.flow.SubscribedSharedFlow$collect$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.flow.SubscribedSharedFlow$collect$1 r0 = new kotlinx.coroutines.flow.SubscribedSharedFlow$collect$1
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.d()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 == r3) goto L2d
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L2d:
            kotlin.ResultKt.b(r6)
            goto L46
        L31:
            kotlin.ResultKt.b(r6)
            kotlinx.coroutines.flow.SharedFlow r6 = r4.f19283c
            kotlinx.coroutines.flow.SubscribedFlowCollector r2 = new kotlinx.coroutines.flow.SubscribedFlowCollector
            kotlin.jvm.functions.Function2 r4 = r4.f19284h
            r2.<init>(r5, r4)
            r0.label = r3
            java.lang.Object r4 = r6.a(r2, r0)
            if (r4 != r1) goto L46
            return r1
        L46:
            kotlin.KotlinNothingValueException r4 = new kotlin.KotlinNothingValueException
            r4.<init>()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.SubscribedSharedFlow.a(kotlinx.coroutines.flow.FlowCollector, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
