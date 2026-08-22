package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.jvm.internal.Ref;

@Metadata
/* loaded from: classes2.dex */
final class FlowKt__LimitKt$drop$2$1<T> implements FlowCollector {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ Ref.IntRef f19158c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ int f19159h;

    /* renamed from: i, reason: collision with root package name */
    final /* synthetic */ FlowCollector f19160i;

    FlowKt__LimitKt$drop$2$1(Ref.IntRef intRef, int i2, FlowCollector flowCollector) {
        this.f19158c = intRef;
        this.f19159h = i2;
        this.f19160i = flowCollector;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object k(java.lang.Object r6, kotlin.coroutines.Continuation r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof kotlinx.coroutines.flow.FlowKt__LimitKt$drop$2$1$emit$1
            if (r0 == 0) goto L13
            r0 = r7
            kotlinx.coroutines.flow.FlowKt__LimitKt$drop$2$1$emit$1 r0 = (kotlinx.coroutines.flow.FlowKt__LimitKt$drop$2$1$emit$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.flow.FlowKt__LimitKt$drop$2$1$emit$1 r0 = new kotlinx.coroutines.flow.FlowKt__LimitKt$drop$2$1$emit$1
            r0.<init>(r5, r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.d()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 != r3) goto L29
            kotlin.ResultKt.b(r7)
            goto L47
        L29:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L31:
            kotlin.ResultKt.b(r7)
            kotlin.jvm.internal.Ref$IntRef r7 = r5.f19158c
            int r2 = r7.element
            int r4 = r5.f19159h
            if (r2 < r4) goto L4a
            kotlinx.coroutines.flow.FlowCollector r5 = r5.f19160i
            r0.label = r3
            java.lang.Object r5 = r5.k(r6, r0)
            if (r5 != r1) goto L47
            return r1
        L47:
            kotlin.Unit r5 = kotlin.Unit.f18288a
            return r5
        L4a:
            int r2 = r2 + r3
            r7.element = r2
            kotlin.Unit r5 = kotlin.Unit.f18288a
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__LimitKt$drop$2$1.k(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
