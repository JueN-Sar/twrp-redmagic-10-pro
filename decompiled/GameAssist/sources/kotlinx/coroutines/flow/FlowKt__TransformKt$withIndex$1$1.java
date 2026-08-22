package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.jvm.internal.Ref;

@Metadata
/* loaded from: classes2.dex */
final class FlowKt__TransformKt$withIndex$1$1<T> implements FlowCollector {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ FlowCollector f19232c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ Ref.IntRef f19233h;

    FlowKt__TransformKt$withIndex$1$1(FlowCollector flowCollector, Ref.IntRef intRef) {
        this.f19232c = flowCollector;
        this.f19233h = intRef;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object k(java.lang.Object r7, kotlin.coroutines.Continuation r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof kotlinx.coroutines.flow.FlowKt__TransformKt$withIndex$1$1$emit$1
            if (r0 == 0) goto L13
            r0 = r8
            kotlinx.coroutines.flow.FlowKt__TransformKt$withIndex$1$1$emit$1 r0 = (kotlinx.coroutines.flow.FlowKt__TransformKt$withIndex$1$1$emit$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.flow.FlowKt__TransformKt$withIndex$1$1$emit$1 r0 = new kotlinx.coroutines.flow.FlowKt__TransformKt$withIndex$1$1$emit$1
            r0.<init>(r6, r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.d()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 != r3) goto L29
            kotlin.ResultKt.b(r8)
            goto L4e
        L29:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L31:
            kotlin.ResultKt.b(r8)
            kotlinx.coroutines.flow.FlowCollector r8 = r6.f19232c
            kotlin.collections.IndexedValue r2 = new kotlin.collections.IndexedValue
            kotlin.jvm.internal.Ref$IntRef r6 = r6.f19233h
            int r4 = r6.element
            int r5 = r4 + 1
            r6.element = r5
            if (r4 < 0) goto L51
            r2.<init>(r4, r7)
            r0.label = r3
            java.lang.Object r6 = r8.k(r2, r0)
            if (r6 != r1) goto L4e
            return r1
        L4e:
            kotlin.Unit r6 = kotlin.Unit.f18288a
            return r6
        L51:
            java.lang.ArithmeticException r6 = new java.lang.ArithmeticException
            java.lang.String r7 = "Index overflow has happened"
            r6.<init>(r7)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__TransformKt$withIndex$1$1.k(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
