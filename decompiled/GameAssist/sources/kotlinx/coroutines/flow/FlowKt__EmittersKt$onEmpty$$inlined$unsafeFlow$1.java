package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function2;

@Metadata
/* loaded from: classes2.dex */
public final class FlowKt__EmittersKt$onEmpty$$inlined$unsafeFlow$1 implements Flow<Object> {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ Flow f19127c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ Function2 f19128h;

    @Metadata
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__EmittersKt$onEmpty$$inlined$unsafeFlow$1", f = "Emitters.kt", l = {114, 122}, m = "collect")
    /* renamed from: kotlinx.coroutines.flow.FlowKt__EmittersKt$onEmpty$$inlined$unsafeFlow$1$1, reason: invalid class name */
    public static final class AnonymousClass1 extends ContinuationImpl {
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
            return FlowKt__EmittersKt$onEmpty$$inlined$unsafeFlow$1.this.a(null, this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0024  */
    /* JADX WARN: Type inference failed for: r6v0, types: [java.lang.Object, kotlinx.coroutines.flow.FlowKt__EmittersKt$onEmpty$$inlined$unsafeFlow$1] */
    /* JADX WARN: Type inference failed for: r6v1, types: [kotlinx.coroutines.flow.internal.SafeCollector] */
    /* JADX WARN: Type inference failed for: r6v13 */
    /* JADX WARN: Type inference failed for: r6v14 */
    /* JADX WARN: Type inference failed for: r6v7, types: [kotlinx.coroutines.flow.internal.SafeCollector] */
    @Override // kotlinx.coroutines.flow.Flow
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object a(kotlinx.coroutines.flow.FlowCollector r7, kotlin.coroutines.Continuation r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof kotlinx.coroutines.flow.FlowKt__EmittersKt$onEmpty$$inlined$unsafeFlow$1.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r8
            kotlinx.coroutines.flow.FlowKt__EmittersKt$onEmpty$$inlined$unsafeFlow$1$1 r0 = (kotlinx.coroutines.flow.FlowKt__EmittersKt$onEmpty$$inlined$unsafeFlow$1.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.flow.FlowKt__EmittersKt$onEmpty$$inlined$unsafeFlow$1$1 r0 = new kotlinx.coroutines.flow.FlowKt__EmittersKt$onEmpty$$inlined$unsafeFlow$1$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.d()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L4a
            if (r2 == r4) goto L3a
            if (r2 != r3) goto L32
            java.lang.Object r6 = r0.L$0
            kotlinx.coroutines.flow.internal.SafeCollector r6 = (kotlinx.coroutines.flow.internal.SafeCollector) r6
            kotlin.ResultKt.b(r8)     // Catch: java.lang.Throwable -> L30
            goto L93
        L30:
            r7 = move-exception
            goto L97
        L32:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L3a:
            java.lang.Object r6 = r0.L$2
            kotlin.jvm.internal.Ref$BooleanRef r6 = (kotlin.jvm.internal.Ref.BooleanRef) r6
            java.lang.Object r7 = r0.L$1
            kotlinx.coroutines.flow.FlowCollector r7 = (kotlinx.coroutines.flow.FlowCollector) r7
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.flow.FlowKt__EmittersKt$onEmpty$$inlined$unsafeFlow$1 r2 = (kotlinx.coroutines.flow.FlowKt__EmittersKt$onEmpty$$inlined$unsafeFlow$1) r2
            kotlin.ResultKt.b(r8)
            goto L6c
        L4a:
            kotlin.ResultKt.b(r8)
            kotlin.jvm.internal.Ref$BooleanRef r8 = new kotlin.jvm.internal.Ref$BooleanRef
            r8.<init>()
            r8.element = r4
            kotlinx.coroutines.flow.Flow r2 = r6.f19127c
            kotlinx.coroutines.flow.FlowKt__EmittersKt$onEmpty$1$1 r5 = new kotlinx.coroutines.flow.FlowKt__EmittersKt$onEmpty$1$1
            r5.<init>(r8, r7)
            r0.L$0 = r6
            r0.L$1 = r7
            r0.L$2 = r8
            r0.label = r4
            java.lang.Object r2 = r2.a(r5, r0)
            if (r2 != r1) goto L6a
            return r1
        L6a:
            r2 = r6
            r6 = r8
        L6c:
            boolean r6 = r6.element
            if (r6 == 0) goto L9b
            kotlinx.coroutines.flow.internal.SafeCollector r6 = new kotlinx.coroutines.flow.internal.SafeCollector
            kotlin.coroutines.CoroutineContext r8 = r0.getContext()
            r6.<init>(r7, r8)
            kotlin.jvm.functions.Function2 r7 = r2.f19128h     // Catch: java.lang.Throwable -> L30
            r0.L$0 = r6     // Catch: java.lang.Throwable -> L30
            r8 = 0
            r0.L$1 = r8     // Catch: java.lang.Throwable -> L30
            r0.L$2 = r8     // Catch: java.lang.Throwable -> L30
            r0.label = r3     // Catch: java.lang.Throwable -> L30
            r8 = 6
            kotlin.jvm.internal.InlineMarker.c(r8)     // Catch: java.lang.Throwable -> L30
            java.lang.Object r7 = r7.y(r6, r0)     // Catch: java.lang.Throwable -> L30
            r8 = 7
            kotlin.jvm.internal.InlineMarker.c(r8)     // Catch: java.lang.Throwable -> L30
            if (r7 != r1) goto L93
            return r1
        L93:
            r6.w()
            goto L9b
        L97:
            r6.w()
            throw r7
        L9b:
            kotlin.Unit r6 = kotlin.Unit.f18288a
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__EmittersKt$onEmpty$$inlined$unsafeFlow$1.a(kotlinx.coroutines.flow.FlowCollector, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
