package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.jvm.internal.Ref;

@Metadata
/* loaded from: classes2.dex */
final class FlowKt__ErrorsKt$catchImpl$2<T> implements FlowCollector {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ FlowCollector f19143c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ Ref.ObjectRef f19144h;

    FlowKt__ErrorsKt$catchImpl$2(FlowCollector flowCollector, Ref.ObjectRef objectRef) {
        this.f19143c = flowCollector;
        this.f19144h = objectRef;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0023  */
    /* JADX WARN: Type inference failed for: r5v1, types: [T, java.lang.Throwable] */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object k(java.lang.Object r5, kotlin.coroutines.Continuation r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof kotlinx.coroutines.flow.FlowKt__ErrorsKt$catchImpl$2$emit$1
            if (r0 == 0) goto L13
            r0 = r6
            kotlinx.coroutines.flow.FlowKt__ErrorsKt$catchImpl$2$emit$1 r0 = (kotlinx.coroutines.flow.FlowKt__ErrorsKt$catchImpl$2$emit$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.flow.FlowKt__ErrorsKt$catchImpl$2$emit$1 r0 = new kotlinx.coroutines.flow.FlowKt__ErrorsKt$catchImpl$2$emit$1
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.d()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L37
            if (r2 != r3) goto L2f
            java.lang.Object r4 = r0.L$0
            kotlinx.coroutines.flow.FlowKt__ErrorsKt$catchImpl$2 r4 = (kotlinx.coroutines.flow.FlowKt__ErrorsKt$catchImpl$2) r4
            kotlin.ResultKt.b(r6)     // Catch: java.lang.Throwable -> L2d
            goto L47
        L2d:
            r5 = move-exception
            goto L4a
        L2f:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L37:
            kotlin.ResultKt.b(r6)
            kotlinx.coroutines.flow.FlowCollector r6 = r4.f19143c     // Catch: java.lang.Throwable -> L2d
            r0.L$0 = r4     // Catch: java.lang.Throwable -> L2d
            r0.label = r3     // Catch: java.lang.Throwable -> L2d
            java.lang.Object r4 = r6.k(r5, r0)     // Catch: java.lang.Throwable -> L2d
            if (r4 != r1) goto L47
            return r1
        L47:
            kotlin.Unit r4 = kotlin.Unit.f18288a
            return r4
        L4a:
            kotlin.jvm.internal.Ref$ObjectRef r4 = r4.f19144h
            r4.element = r5
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__ErrorsKt$catchImpl$2.k(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
