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
public final class FlowKt__MergeKt$flatMapConcat$$inlined$map$1 implements Flow<Flow<Object>> {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ Flow f19168c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ Function2 f19169h;

    @Metadata
    /* renamed from: kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapConcat$$inlined$map$1$2, reason: invalid class name */
    public static final class AnonymousClass2<T> implements FlowCollector {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ FlowCollector f19170c;

        /* renamed from: h, reason: collision with root package name */
        final /* synthetic */ Function2 f19171h;

        @Metadata
        @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapConcat$$inlined$map$1$2", f = "Merge.kt", l = {223, 223}, m = "emit")
        /* renamed from: kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapConcat$$inlined$map$1$2$1, reason: invalid class name */
        public static final class AnonymousClass1 extends ContinuationImpl {
            Object L$0;
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
            this.f19170c = flowCollector;
            this.f19171h = function2;
        }

        /* JADX WARN: Removed duplicated region for block: B:19:0x005c A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:20:0x003c  */
        /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
        @Override // kotlinx.coroutines.flow.FlowCollector
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object k(java.lang.Object r7, kotlin.coroutines.Continuation r8) {
            /*
                r6 = this;
                boolean r0 = r8 instanceof kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapConcat$$inlined$map$1.AnonymousClass2.AnonymousClass1
                if (r0 == 0) goto L13
                r0 = r8
                kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapConcat$$inlined$map$1$2$1 r0 = (kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapConcat$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L13
                int r1 = r1 - r2
                r0.label = r1
                goto L18
            L13:
                kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapConcat$$inlined$map$1$2$1 r0 = new kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapConcat$$inlined$map$1$2$1
                r0.<init>(r8)
            L18:
                java.lang.Object r8 = r0.result
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.d()
                int r2 = r0.label
                r3 = 2
                r4 = 1
                if (r2 == 0) goto L3c
                if (r2 == r4) goto L34
                if (r2 != r3) goto L2c
                kotlin.ResultKt.b(r8)
                goto L5d
            L2c:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r7)
                throw r6
            L34:
                java.lang.Object r6 = r0.L$0
                kotlinx.coroutines.flow.FlowCollector r6 = (kotlinx.coroutines.flow.FlowCollector) r6
                kotlin.ResultKt.b(r8)
                goto L51
            L3c:
                kotlin.ResultKt.b(r8)
                kotlinx.coroutines.flow.FlowCollector r8 = r6.f19170c
                kotlin.jvm.functions.Function2 r6 = r6.f19171h
                r0.L$0 = r8
                r0.label = r4
                java.lang.Object r6 = r6.y(r7, r0)
                if (r6 != r1) goto L4e
                return r1
            L4e:
                r5 = r8
                r8 = r6
                r6 = r5
            L51:
                r7 = 0
                r0.L$0 = r7
                r0.label = r3
                java.lang.Object r6 = r6.k(r8, r0)
                if (r6 != r1) goto L5d
                return r1
            L5d:
                kotlin.Unit r6 = kotlin.Unit.f18288a
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapConcat$$inlined$map$1.AnonymousClass2.k(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    @Override // kotlinx.coroutines.flow.Flow
    public Object a(FlowCollector flowCollector, Continuation continuation) {
        Object d2;
        Object a2 = this.f19168c.a(new AnonymousClass2(flowCollector, this.f19169h), continuation);
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        return a2 == d2 ? a2 : Unit.f18288a;
    }
}
