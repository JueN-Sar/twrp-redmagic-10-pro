package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function3;

@Metadata
/* loaded from: classes2.dex */
public final class FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1 implements Flow<Object> {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ Flow f19125c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ Function3 f19126h;

    @Metadata
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1", f = "Emitters.kt", l = {114, 121, 128}, m = "collect")
    /* renamed from: kotlinx.coroutines.flow.FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1$1, reason: invalid class name */
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
            return FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1.this.a(null, this);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0089 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0026  */
    @Override // kotlinx.coroutines.flow.Flow
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object a(kotlinx.coroutines.flow.FlowCollector r9, kotlin.coroutines.Continuation r10) {
        /*
            r8 = this;
            boolean r0 = r10 instanceof kotlinx.coroutines.flow.FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r10
            kotlinx.coroutines.flow.FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1$1 r0 = (kotlinx.coroutines.flow.FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.flow.FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1$1 r0 = new kotlinx.coroutines.flow.FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1$1
            r0.<init>(r10)
        L18:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.d()
            int r2 = r0.label
            r3 = 3
            r4 = 2
            r5 = 1
            r6 = 0
            if (r2 == 0) goto L58
            if (r2 == r5) goto L46
            if (r2 == r4) goto L3e
            if (r2 != r3) goto L36
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.flow.internal.SafeCollector r8 = (kotlinx.coroutines.flow.internal.SafeCollector) r8
            kotlin.ResultKt.b(r10)     // Catch: java.lang.Throwable -> L34
            goto L8b
        L34:
            r9 = move-exception
            goto L93
        L36:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L3e:
            java.lang.Object r8 = r0.L$0
            java.lang.Throwable r8 = (java.lang.Throwable) r8
            kotlin.ResultKt.b(r10)
            goto Lab
        L46:
            java.lang.Object r8 = r0.L$1
            r9 = r8
            kotlinx.coroutines.flow.FlowCollector r9 = (kotlinx.coroutines.flow.FlowCollector) r9
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.flow.FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1 r8 = (kotlinx.coroutines.flow.FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1) r8
            kotlin.ResultKt.b(r10)     // Catch: java.lang.Throwable -> L53
            goto L6a
        L53:
            r9 = move-exception
            r7 = r9
            r9 = r8
            r8 = r7
            goto L97
        L58:
            kotlin.ResultKt.b(r10)
            kotlinx.coroutines.flow.Flow r10 = r8.f19125c     // Catch: java.lang.Throwable -> L53
            r0.L$0 = r8     // Catch: java.lang.Throwable -> L53
            r0.L$1 = r9     // Catch: java.lang.Throwable -> L53
            r0.label = r5     // Catch: java.lang.Throwable -> L53
            java.lang.Object r10 = r10.a(r9, r0)     // Catch: java.lang.Throwable -> L53
            if (r10 != r1) goto L6a
            return r1
        L6a:
            kotlinx.coroutines.flow.internal.SafeCollector r10 = new kotlinx.coroutines.flow.internal.SafeCollector
            kotlin.coroutines.CoroutineContext r2 = r0.getContext()
            r10.<init>(r9, r2)
            kotlin.jvm.functions.Function3 r8 = r8.f19126h     // Catch: java.lang.Throwable -> L91
            r0.L$0 = r10     // Catch: java.lang.Throwable -> L91
            r0.L$1 = r6     // Catch: java.lang.Throwable -> L91
            r0.label = r3     // Catch: java.lang.Throwable -> L91
            r9 = 6
            kotlin.jvm.internal.InlineMarker.c(r9)     // Catch: java.lang.Throwable -> L91
            java.lang.Object r8 = r8.u(r10, r6, r0)     // Catch: java.lang.Throwable -> L91
            r9 = 7
            kotlin.jvm.internal.InlineMarker.c(r9)     // Catch: java.lang.Throwable -> L91
            if (r8 != r1) goto L8a
            return r1
        L8a:
            r8 = r10
        L8b:
            r8.w()
            kotlin.Unit r8 = kotlin.Unit.f18288a
            return r8
        L91:
            r9 = move-exception
            r8 = r10
        L93:
            r8.w()
            throw r9
        L97:
            kotlinx.coroutines.flow.ThrowingCollector r10 = new kotlinx.coroutines.flow.ThrowingCollector
            r10.<init>(r8)
            kotlin.jvm.functions.Function3 r9 = r9.f19126h
            r0.L$0 = r8
            r0.L$1 = r6
            r0.label = r4
            java.lang.Object r9 = kotlinx.coroutines.flow.FlowKt__EmittersKt.a(r10, r9, r8, r0)
            if (r9 != r1) goto Lab
            return r1
        Lab:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1.a(kotlinx.coroutines.flow.FlowCollector, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
