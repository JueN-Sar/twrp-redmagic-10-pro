package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;

@Metadata
/* loaded from: classes2.dex */
final class FlowKt__CountKt$count$4<T> implements FlowCollector {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ Function2 f19119c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ Ref.IntRef f19120h;

    FlowKt__CountKt$count$4(Function2 function2, Ref.IntRef intRef) {
        this.f19119c = function2;
        this.f19120h = intRef;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object k(java.lang.Object r5, kotlin.coroutines.Continuation r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof kotlinx.coroutines.flow.FlowKt__CountKt$count$4$emit$1
            if (r0 == 0) goto L13
            r0 = r6
            kotlinx.coroutines.flow.FlowKt__CountKt$count$4$emit$1 r0 = (kotlinx.coroutines.flow.FlowKt__CountKt$count$4$emit$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.flow.FlowKt__CountKt$count$4$emit$1 r0 = new kotlinx.coroutines.flow.FlowKt__CountKt$count$4$emit$1
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.d()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            java.lang.Object r4 = r0.L$0
            kotlinx.coroutines.flow.FlowKt__CountKt$count$4 r4 = (kotlinx.coroutines.flow.FlowKt__CountKt$count$4) r4
            kotlin.ResultKt.b(r6)
            goto L45
        L2d:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L35:
            kotlin.ResultKt.b(r6)
            kotlin.jvm.functions.Function2 r6 = r4.f19119c
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r6 = r6.y(r5, r0)
            if (r6 != r1) goto L45
            return r1
        L45:
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r5 = r6.booleanValue()
            if (r5 == 0) goto L54
            kotlin.jvm.internal.Ref$IntRef r4 = r4.f19120h
            int r5 = r4.element
            int r5 = r5 + r3
            r4.element = r5
        L54:
            kotlin.Unit r4 = kotlin.Unit.f18288a
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__CountKt$count$4.k(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
