package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function4;

@Metadata
/* loaded from: classes2.dex */
public final class FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1 implements Flow<Object> {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ Flow f19141c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ Function4 f19142h;

    @Metadata
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1", f = "Errors.kt", l = {117, 119}, m = "collect")
    /* renamed from: kotlinx.coroutines.flow.FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1$1, reason: invalid class name */
    public static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        long J$0;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object v(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1.this.a(null, this);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0074 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x007b -> B:14:0x00ab). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x009c -> B:11:0x009f). Please report as a decompilation issue!!! */
    @Override // kotlinx.coroutines.flow.Flow
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object a(kotlinx.coroutines.flow.FlowCollector r13, kotlin.coroutines.Continuation r14) {
        /*
            r12 = this;
            boolean r0 = r14 instanceof kotlinx.coroutines.flow.FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r14
            kotlinx.coroutines.flow.FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1$1 r0 = (kotlinx.coroutines.flow.FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.flow.FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1$1 r0 = new kotlinx.coroutines.flow.FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1$1
            r0.<init>(r14)
        L18:
            java.lang.Object r14 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.d()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L59
            if (r2 == r4) goto L43
            if (r2 != r3) goto L3b
            long r12 = r0.J$0
            java.lang.Object r2 = r0.L$2
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r5 = r0.L$1
            kotlinx.coroutines.flow.FlowCollector r5 = (kotlinx.coroutines.flow.FlowCollector) r5
            java.lang.Object r6 = r0.L$0
            kotlinx.coroutines.flow.FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1 r6 = (kotlinx.coroutines.flow.FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1) r6
            kotlin.ResultKt.b(r14)
            goto L9f
        L3b:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L43:
            int r12 = r0.I$0
            long r5 = r0.J$0
            java.lang.Object r13 = r0.L$1
            kotlinx.coroutines.flow.FlowCollector r13 = (kotlinx.coroutines.flow.FlowCollector) r13
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.flow.FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1 r2 = (kotlinx.coroutines.flow.FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1) r2
            kotlin.ResultKt.b(r14)
            r9 = r2
            r2 = r12
            r10 = r5
            r5 = r13
            r6 = r9
            r12 = r10
            goto L79
        L59:
            kotlin.ResultKt.b(r14)
            r5 = 0
        L5e:
            kotlinx.coroutines.flow.Flow r14 = r12.f19141c
            r0.L$0 = r12
            r0.L$1 = r13
            r2 = 0
            r0.L$2 = r2
            r0.J$0 = r5
            r2 = 0
            r0.I$0 = r2
            r0.label = r4
            java.lang.Object r14 = kotlinx.coroutines.flow.FlowKt.c(r14, r13, r0)
            if (r14 != r1) goto L75
            return r1
        L75:
            r9 = r5
            r6 = r12
            r5 = r13
            r12 = r9
        L79:
            java.lang.Throwable r14 = (java.lang.Throwable) r14
            if (r14 == 0) goto Lab
            kotlin.jvm.functions.Function4 r2 = r6.f19142h
            java.lang.Long r7 = kotlin.coroutines.jvm.internal.Boxing.c(r12)
            r0.L$0 = r6
            r0.L$1 = r5
            r0.L$2 = r14
            r0.J$0 = r12
            r0.label = r3
            r8 = 6
            kotlin.jvm.internal.InlineMarker.c(r8)
            java.lang.Object r2 = r2.j(r5, r14, r7, r0)
            r7 = 7
            kotlin.jvm.internal.InlineMarker.c(r7)
            if (r2 != r1) goto L9c
            return r1
        L9c:
            r9 = r2
            r2 = r14
            r14 = r9
        L9f:
            java.lang.Boolean r14 = (java.lang.Boolean) r14
            boolean r14 = r14.booleanValue()
            if (r14 == 0) goto Lb0
            r7 = 1
            long r12 = r12 + r7
            r2 = r4
        Lab:
            r9 = r12
            r13 = r5
            r12 = r6
            r5 = r9
            goto Lb1
        Lb0:
            throw r2
        Lb1:
            if (r2 != 0) goto L5e
            kotlin.Unit r12 = kotlin.Unit.f18288a
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1.a(kotlinx.coroutines.flow.FlowCollector, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
