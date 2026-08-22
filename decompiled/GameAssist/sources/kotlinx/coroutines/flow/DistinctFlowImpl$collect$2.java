package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.jvm.internal.Ref;

@Metadata
/* loaded from: classes2.dex */
final class DistinctFlowImpl$collect$2<T> implements FlowCollector {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ DistinctFlowImpl f19097c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ Ref.ObjectRef f19098h;

    /* renamed from: i, reason: collision with root package name */
    final /* synthetic */ FlowCollector f19099i;

    DistinctFlowImpl$collect$2(DistinctFlowImpl distinctFlowImpl, Ref.ObjectRef objectRef, FlowCollector flowCollector) {
        this.f19097c = distinctFlowImpl;
        this.f19098h = objectRef;
        this.f19099i = flowCollector;
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
            boolean r0 = r7 instanceof kotlinx.coroutines.flow.DistinctFlowImpl$collect$2$emit$1
            if (r0 == 0) goto L13
            r0 = r7
            kotlinx.coroutines.flow.DistinctFlowImpl$collect$2$emit$1 r0 = (kotlinx.coroutines.flow.DistinctFlowImpl$collect$2$emit$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.flow.DistinctFlowImpl$collect$2$emit$1 r0 = new kotlinx.coroutines.flow.DistinctFlowImpl$collect$2$emit$1
            r0.<init>(r5, r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.d()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 != r3) goto L29
            kotlin.ResultKt.b(r7)
            goto L67
        L29:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L31:
            kotlin.ResultKt.b(r7)
            kotlinx.coroutines.flow.DistinctFlowImpl r7 = r5.f19097c
            kotlin.jvm.functions.Function1 r7 = r7.f19095h
            java.lang.Object r7 = r7.c(r6)
            kotlin.jvm.internal.Ref$ObjectRef r2 = r5.f19098h
            T r2 = r2.element
            kotlinx.coroutines.internal.Symbol r4 = kotlinx.coroutines.flow.internal.NullSurrogateKt.f19324a
            if (r2 == r4) goto L58
            kotlinx.coroutines.flow.DistinctFlowImpl r4 = r5.f19097c
            kotlin.jvm.functions.Function2 r4 = r4.f19096i
            java.lang.Object r2 = r4.y(r2, r7)
            java.lang.Boolean r2 = (java.lang.Boolean) r2
            boolean r2 = r2.booleanValue()
            if (r2 != 0) goto L55
            goto L58
        L55:
            kotlin.Unit r5 = kotlin.Unit.f18288a
            return r5
        L58:
            kotlin.jvm.internal.Ref$ObjectRef r2 = r5.f19098h
            r2.element = r7
            kotlinx.coroutines.flow.FlowCollector r5 = r5.f19099i
            r0.label = r3
            java.lang.Object r5 = r5.k(r6, r0)
            if (r5 != r1) goto L67
            return r1
        L67:
            kotlin.Unit r5 = kotlin.Unit.f18288a
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.DistinctFlowImpl$collect$2.k(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
