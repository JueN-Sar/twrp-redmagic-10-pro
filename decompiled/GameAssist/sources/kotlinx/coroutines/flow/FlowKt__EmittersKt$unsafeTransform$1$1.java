package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.jvm.functions.Function3;

@Metadata
/* loaded from: classes2.dex */
public final class FlowKt__EmittersKt$unsafeTransform$1$1<T> implements FlowCollector {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ Function3 f19137c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ FlowCollector f19138h;

    public FlowKt__EmittersKt$unsafeTransform$1$1(Function3 function3, FlowCollector flowCollector) {
        this.f19137c = function3;
        this.f19138h = flowCollector;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object k(java.lang.Object r5, kotlin.coroutines.Continuation r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof kotlinx.coroutines.flow.FlowKt__EmittersKt$unsafeTransform$1$1$emit$1
            if (r0 == 0) goto L13
            r0 = r6
            kotlinx.coroutines.flow.FlowKt__EmittersKt$unsafeTransform$1$1$emit$1 r0 = (kotlinx.coroutines.flow.FlowKt__EmittersKt$unsafeTransform$1$1$emit$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.flow.FlowKt__EmittersKt$unsafeTransform$1$1$emit$1 r0 = new kotlinx.coroutines.flow.FlowKt__EmittersKt$unsafeTransform$1$1$emit$1
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.d()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 != r3) goto L29
            kotlin.ResultKt.b(r6)
            goto L41
        L29:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L31:
            kotlin.ResultKt.b(r6)
            kotlin.jvm.functions.Function3 r6 = r4.f19137c
            kotlinx.coroutines.flow.FlowCollector r4 = r4.f19138h
            r0.label = r3
            java.lang.Object r4 = r6.u(r4, r5, r0)
            if (r4 != r1) goto L41
            return r1
        L41:
            kotlin.Unit r4 = kotlin.Unit.f18288a
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__EmittersKt$unsafeTransform$1$1.k(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
