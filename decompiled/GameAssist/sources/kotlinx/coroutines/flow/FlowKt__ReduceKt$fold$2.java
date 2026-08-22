package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref;

@Metadata
/* loaded from: classes2.dex */
public final class FlowKt__ReduceKt$fold$2<T> implements FlowCollector {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ Ref.ObjectRef f19185c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ Function3 f19186h;

    public FlowKt__ReduceKt$fold$2(Ref.ObjectRef objectRef, Function3 function3) {
        this.f19185c = objectRef;
        this.f19186h = function3;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object k(java.lang.Object r6, kotlin.coroutines.Continuation r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof kotlinx.coroutines.flow.FlowKt__ReduceKt$fold$2$emit$1
            if (r0 == 0) goto L13
            r0 = r7
            kotlinx.coroutines.flow.FlowKt__ReduceKt$fold$2$emit$1 r0 = (kotlinx.coroutines.flow.FlowKt__ReduceKt$fold$2$emit$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.flow.FlowKt__ReduceKt$fold$2$emit$1 r0 = new kotlinx.coroutines.flow.FlowKt__ReduceKt$fold$2$emit$1
            r0.<init>(r5, r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.d()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            java.lang.Object r5 = r0.L$0
            kotlin.jvm.internal.Ref$ObjectRef r5 = (kotlin.jvm.internal.Ref.ObjectRef) r5
            kotlin.ResultKt.b(r7)
            goto L4c
        L2d:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L35:
            kotlin.ResultKt.b(r7)
            kotlin.jvm.internal.Ref$ObjectRef r7 = r5.f19185c
            kotlin.jvm.functions.Function3 r5 = r5.f19186h
            T r2 = r7.element
            r0.L$0 = r7
            r0.label = r3
            java.lang.Object r5 = r5.u(r2, r6, r0)
            if (r5 != r1) goto L49
            return r1
        L49:
            r4 = r7
            r7 = r5
            r5 = r4
        L4c:
            r5.element = r7
            kotlin.Unit r5 = kotlin.Unit.f18288a
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__ReduceKt$fold$2.k(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
