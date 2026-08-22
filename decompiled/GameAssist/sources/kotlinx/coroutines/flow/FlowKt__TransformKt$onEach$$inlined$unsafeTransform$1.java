package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function2;

@Metadata
/* loaded from: classes2.dex */
public final class FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1 implements Flow<Object> {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ Flow f19216c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ Function2 f19217h;

    @Metadata
    /* renamed from: kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1$2, reason: invalid class name */
    public static final class AnonymousClass2<T> implements FlowCollector {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ FlowCollector f19218c;

        /* renamed from: h, reason: collision with root package name */
        final /* synthetic */ Function2 f19219h;

        @Metadata
        @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1$2", f = "Transform.kt", l = {223, 224}, m = "emit")
        /* renamed from: kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1$2$1, reason: invalid class name */
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
                return AnonymousClass2.this.k(null, this);
            }
        }

        public AnonymousClass2(FlowCollector flowCollector, Function2 function2) {
            this.f19218c = flowCollector;
            this.f19219h = function2;
        }

        /* JADX WARN: Removed duplicated region for block: B:19:0x0068 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:20:0x003e  */
        /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
        @Override // kotlinx.coroutines.flow.FlowCollector
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object k(java.lang.Object r6, kotlin.coroutines.Continuation r7) {
            /*
                r5 = this;
                boolean r0 = r7 instanceof kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1.AnonymousClass2.AnonymousClass1
                if (r0 == 0) goto L13
                r0 = r7
                kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1$2$1 r0 = (kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1.AnonymousClass2.AnonymousClass1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L13
                int r1 = r1 - r2
                r0.label = r1
                goto L18
            L13:
                kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1$2$1 r0 = new kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1$2$1
                r0.<init>(r7)
            L18:
                java.lang.Object r7 = r0.result
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.d()
                int r2 = r0.label
                r3 = 2
                r4 = 1
                if (r2 == 0) goto L3e
                if (r2 == r4) goto L34
                if (r2 != r3) goto L2c
                kotlin.ResultKt.b(r7)
                goto L69
            L2c:
                java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                r5.<init>(r6)
                throw r5
            L34:
                java.lang.Object r5 = r0.L$1
                kotlinx.coroutines.flow.FlowCollector r5 = (kotlinx.coroutines.flow.FlowCollector) r5
                java.lang.Object r6 = r0.L$0
                kotlin.ResultKt.b(r7)
                goto L5b
            L3e:
                kotlin.ResultKt.b(r7)
                kotlinx.coroutines.flow.FlowCollector r7 = r5.f19218c
                kotlin.jvm.functions.Function2 r5 = r5.f19219h
                r0.L$0 = r6
                r0.L$1 = r7
                r0.label = r4
                r2 = 6
                kotlin.jvm.internal.InlineMarker.c(r2)
                java.lang.Object r5 = r5.y(r6, r0)
                r2 = 7
                kotlin.jvm.internal.InlineMarker.c(r2)
                if (r5 != r1) goto L5a
                return r1
            L5a:
                r5 = r7
            L5b:
                r7 = 0
                r0.L$0 = r7
                r0.L$1 = r7
                r0.label = r3
                java.lang.Object r5 = r5.k(r6, r0)
                if (r5 != r1) goto L69
                return r1
            L69:
                kotlin.Unit r5 = kotlin.Unit.f18288a
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1.AnonymousClass2.k(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    @Override // kotlinx.coroutines.flow.Flow
    public Object a(FlowCollector flowCollector, Continuation continuation) {
        Object d2;
        Object a2 = this.f19216c.a(new AnonymousClass2(flowCollector, this.f19217h), continuation);
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        return a2 == d2 ? a2 : Unit.f18288a;
    }
}
