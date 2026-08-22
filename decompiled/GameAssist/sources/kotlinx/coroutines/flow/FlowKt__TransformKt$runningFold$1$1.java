package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref;

@Metadata
/* loaded from: classes2.dex */
final class FlowKt__TransformKt$runningFold$1$1<T> implements FlowCollector {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ Ref.ObjectRef f19226c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ Function3 f19227h;

    /* renamed from: i, reason: collision with root package name */
    final /* synthetic */ FlowCollector f19228i;

    FlowKt__TransformKt$runningFold$1$1(Ref.ObjectRef objectRef, Function3 function3, FlowCollector flowCollector) {
        this.f19226c = objectRef;
        this.f19227h = function3;
        this.f19228i = flowCollector;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x006f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object k(java.lang.Object r8, kotlin.coroutines.Continuation r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof kotlinx.coroutines.flow.FlowKt__TransformKt$runningFold$1$1$emit$1
            if (r0 == 0) goto L13
            r0 = r9
            kotlinx.coroutines.flow.FlowKt__TransformKt$runningFold$1$1$emit$1 r0 = (kotlinx.coroutines.flow.FlowKt__TransformKt$runningFold$1$1$emit$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.flow.FlowKt__TransformKt$runningFold$1$1$emit$1 r0 = new kotlinx.coroutines.flow.FlowKt__TransformKt$runningFold$1$1$emit$1
            r0.<init>(r7, r9)
        L18:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.d()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L40
            if (r2 == r4) goto L34
            if (r2 != r3) goto L2c
            kotlin.ResultKt.b(r9)
            goto L70
        L2c:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L34:
            java.lang.Object r7 = r0.L$1
            kotlin.jvm.internal.Ref$ObjectRef r7 = (kotlin.jvm.internal.Ref.ObjectRef) r7
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.flow.FlowKt__TransformKt$runningFold$1$1 r8 = (kotlinx.coroutines.flow.FlowKt__TransformKt$runningFold$1$1) r8
            kotlin.ResultKt.b(r9)
            goto L5a
        L40:
            kotlin.ResultKt.b(r9)
            kotlin.jvm.internal.Ref$ObjectRef r9 = r7.f19226c
            kotlin.jvm.functions.Function3 r2 = r7.f19227h
            T r5 = r9.element
            r0.L$0 = r7
            r0.L$1 = r9
            r0.label = r4
            java.lang.Object r8 = r2.u(r5, r8, r0)
            if (r8 != r1) goto L56
            return r1
        L56:
            r6 = r8
            r8 = r7
            r7 = r9
            r9 = r6
        L5a:
            r7.element = r9
            kotlinx.coroutines.flow.FlowCollector r7 = r8.f19228i
            kotlin.jvm.internal.Ref$ObjectRef r8 = r8.f19226c
            T r8 = r8.element
            r9 = 0
            r0.L$0 = r9
            r0.L$1 = r9
            r0.label = r3
            java.lang.Object r7 = r7.k(r8, r0)
            if (r7 != r1) goto L70
            return r1
        L70:
            kotlin.Unit r7 = kotlin.Unit.f18288a
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__TransformKt$runningFold$1$1.k(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
