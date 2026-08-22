package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function2;

@Metadata
/* renamed from: kotlinx.coroutines.flow.FlowKt__LimitKt$takeWhile$lambda-6$$inlined$collectWhile$1, reason: invalid class name */
/* loaded from: classes2.dex */
public final class FlowKt__LimitKt$takeWhile$lambda6$$inlined$collectWhile$1 implements FlowCollector<Object> {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ Function2 f19153c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ FlowCollector f19154h;

    @Metadata
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__LimitKt$takeWhile$lambda-6$$inlined$collectWhile$1", f = "Limit.kt", l = {142, 143}, m = "emit")
    /* renamed from: kotlinx.coroutines.flow.FlowKt__LimitKt$takeWhile$lambda-6$$inlined$collectWhile$1$1, reason: invalid class name */
    public static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object v(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FlowKt__LimitKt$takeWhile$lambda6$$inlined$collectWhile$1.this.k(null, this);
        }
    }

    public FlowKt__LimitKt$takeWhile$lambda6$$inlined$collectWhile$1(Function2 function2, FlowCollector flowCollector) {
        this.f19153c = function2;
        this.f19154h = flowCollector;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object k(java.lang.Object r6, kotlin.coroutines.Continuation r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof kotlinx.coroutines.flow.FlowKt__LimitKt$takeWhile$lambda6$$inlined$collectWhile$1.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r7
            kotlinx.coroutines.flow.FlowKt__LimitKt$takeWhile$lambda-6$$inlined$collectWhile$1$1 r0 = (kotlinx.coroutines.flow.FlowKt__LimitKt$takeWhile$lambda6$$inlined$collectWhile$1.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.flow.FlowKt__LimitKt$takeWhile$lambda-6$$inlined$collectWhile$1$1 r0 = new kotlinx.coroutines.flow.FlowKt__LimitKt$takeWhile$lambda-6$$inlined$collectWhile$1$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.d()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L42
            if (r2 == r4) goto L38
            if (r2 != r3) goto L30
            java.lang.Object r5 = r0.L$0
            kotlinx.coroutines.flow.FlowKt__LimitKt$takeWhile$lambda-6$$inlined$collectWhile$1 r5 = (kotlinx.coroutines.flow.FlowKt__LimitKt$takeWhile$lambda6$$inlined$collectWhile$1) r5
            kotlin.ResultKt.b(r7)
            goto L75
        L30:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L38:
            java.lang.Object r6 = r0.L$1
            java.lang.Object r5 = r0.L$0
            kotlinx.coroutines.flow.FlowKt__LimitKt$takeWhile$lambda-6$$inlined$collectWhile$1 r5 = (kotlinx.coroutines.flow.FlowKt__LimitKt$takeWhile$lambda6$$inlined$collectWhile$1) r5
            kotlin.ResultKt.b(r7)
            goto L5c
        L42:
            kotlin.ResultKt.b(r7)
            kotlin.jvm.functions.Function2 r7 = r5.f19153c
            r0.L$0 = r5
            r0.L$1 = r6
            r0.label = r4
            r2 = 6
            kotlin.jvm.internal.InlineMarker.c(r2)
            java.lang.Object r7 = r7.y(r6, r0)
            r2 = 7
            kotlin.jvm.internal.InlineMarker.c(r2)
            if (r7 != r1) goto L5c
            return r1
        L5c:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r7 = r7.booleanValue()
            if (r7 == 0) goto L74
            kotlinx.coroutines.flow.FlowCollector r7 = r5.f19154h
            r0.L$0 = r5
            r2 = 0
            r0.L$1 = r2
            r0.label = r3
            java.lang.Object r6 = r7.k(r6, r0)
            if (r6 != r1) goto L75
            return r1
        L74:
            r4 = 0
        L75:
            if (r4 == 0) goto L7a
            kotlin.Unit r5 = kotlin.Unit.f18288a
            return r5
        L7a:
            kotlinx.coroutines.flow.internal.AbortFlowException r6 = new kotlinx.coroutines.flow.internal.AbortFlowException
            r6.<init>(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__LimitKt$takeWhile$lambda6$$inlined$collectWhile$1.k(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
