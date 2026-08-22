package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.JobKt__JobKt;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.internal.ThreadContextKt;

@Metadata
@DebugMetadata(c = "kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1", f = "Combine.kt", l = {129}, m = "invokeSuspend")
/* loaded from: classes2.dex */
final class CombineKt$zipImpl$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Flow<Object> $flow;
    final /* synthetic */ Flow<Object> $flow2;
    final /* synthetic */ FlowCollector<Object> $this_unsafeFlow;
    final /* synthetic */ Function3<Object, Object, Continuation<Object>, Object> $transform;
    private /* synthetic */ Object L$0;
    int label;

    @Metadata
    @DebugMetadata(c = "kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$2", f = "Combine.kt", l = {130}, m = "invokeSuspend")
    /* renamed from: kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<Unit, Continuation<? super Unit>, Object> {
        final /* synthetic */ Object $cnt;
        final /* synthetic */ Flow<Object> $flow;
        final /* synthetic */ CoroutineContext $scopeContext;
        final /* synthetic */ ReceiveChannel<Object> $second;
        final /* synthetic */ FlowCollector<Object> $this_unsafeFlow;
        final /* synthetic */ Function3<Object, Object, Continuation<Object>, Object> $transform;
        int label;

        @Metadata
        /* renamed from: kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$2$1, reason: invalid class name */
        static final class AnonymousClass1<T> implements FlowCollector {

            /* renamed from: c, reason: collision with root package name */
            final /* synthetic */ CoroutineContext f19312c;

            /* renamed from: h, reason: collision with root package name */
            final /* synthetic */ Object f19313h;

            /* renamed from: i, reason: collision with root package name */
            final /* synthetic */ ReceiveChannel f19314i;

            /* renamed from: j, reason: collision with root package name */
            final /* synthetic */ FlowCollector f19315j;

            /* renamed from: k, reason: collision with root package name */
            final /* synthetic */ Function3 f19316k;

            @Metadata
            @DebugMetadata(c = "kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$2$1$1", f = "Combine.kt", l = {132, 135, 135}, m = "invokeSuspend")
            /* renamed from: kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$2$1$1, reason: invalid class name and collision with other inner class name */
            static final class C00151 extends SuspendLambda implements Function2<Unit, Continuation<? super Unit>, Object> {
                final /* synthetic */ ReceiveChannel<Object> $second;
                final /* synthetic */ FlowCollector<Object> $this_unsafeFlow;
                final /* synthetic */ Function3<Object, Object, Continuation<Object>, Object> $transform;
                final /* synthetic */ Object $value;
                Object L$0;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                C00151(ReceiveChannel<? extends Object> receiveChannel, FlowCollector<Object> flowCollector, Function3<Object, Object, ? super Continuation<Object>, ? extends Object> function3, Object obj, Continuation<? super C00151> continuation) {
                    super(2, continuation);
                    this.$second = receiveChannel;
                    this.$this_unsafeFlow = flowCollector;
                    this.$transform = function3;
                    this.$value = obj;
                }

                @Override // kotlin.jvm.functions.Function2
                /* renamed from: A, reason: merged with bridge method [inline-methods] */
                public final Object y(Unit unit, Continuation continuation) {
                    return ((C00151) o(unit, continuation)).v(Unit.f18288a);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation o(Object obj, Continuation continuation) {
                    return new C00151(this.$second, this.$this_unsafeFlow, this.$transform, this.$value, continuation);
                }

                /* JADX WARN: Removed duplicated region for block: B:15:0x006e A[RETURN] */
                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object v(java.lang.Object r9) {
                    /*
                        r8 = this;
                        java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.d()
                        int r1 = r8.label
                        r2 = 0
                        r3 = 3
                        r4 = 2
                        r5 = 1
                        if (r1 == 0) goto L30
                        if (r1 == r5) goto L26
                        if (r1 == r4) goto L1e
                        if (r1 != r3) goto L16
                        kotlin.ResultKt.b(r9)
                        goto L6f
                    L16:
                        java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                        java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
                        r8.<init>(r9)
                        throw r8
                    L1e:
                        java.lang.Object r1 = r8.L$0
                        kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                        kotlin.ResultKt.b(r9)
                        goto L64
                    L26:
                        kotlin.ResultKt.b(r9)
                        kotlinx.coroutines.channels.ChannelResult r9 = (kotlinx.coroutines.channels.ChannelResult) r9
                        java.lang.Object r9 = r9.k()
                        goto L3e
                    L30:
                        kotlin.ResultKt.b(r9)
                        kotlinx.coroutines.channels.ReceiveChannel<java.lang.Object> r9 = r8.$second
                        r8.label = r5
                        java.lang.Object r9 = r9.z(r8)
                        if (r9 != r0) goto L3e
                        return r0
                    L3e:
                        kotlinx.coroutines.flow.FlowCollector<java.lang.Object> r1 = r8.$this_unsafeFlow
                        boolean r5 = r9 instanceof kotlinx.coroutines.channels.ChannelResult.Failed
                        if (r5 == 0) goto L50
                        java.lang.Throwable r8 = kotlinx.coroutines.channels.ChannelResult.e(r9)
                        if (r8 != 0) goto L4f
                        kotlinx.coroutines.flow.internal.AbortFlowException r8 = new kotlinx.coroutines.flow.internal.AbortFlowException
                        r8.<init>(r1)
                    L4f:
                        throw r8
                    L50:
                        kotlin.jvm.functions.Function3<java.lang.Object, java.lang.Object, kotlin.coroutines.Continuation<java.lang.Object>, java.lang.Object> r5 = r8.$transform
                        java.lang.Object r6 = r8.$value
                        kotlinx.coroutines.internal.Symbol r7 = kotlinx.coroutines.flow.internal.NullSurrogateKt.f19324a
                        if (r9 != r7) goto L59
                        r9 = r2
                    L59:
                        r8.L$0 = r1
                        r8.label = r4
                        java.lang.Object r9 = r5.u(r6, r9, r8)
                        if (r9 != r0) goto L64
                        return r0
                    L64:
                        r8.L$0 = r2
                        r8.label = r3
                        java.lang.Object r8 = r1.k(r9, r8)
                        if (r8 != r0) goto L6f
                        return r0
                    L6f:
                        kotlin.Unit r8 = kotlin.Unit.f18288a
                        return r8
                    */
                    throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1.AnonymousClass2.AnonymousClass1.C00151.v(java.lang.Object):java.lang.Object");
                }
            }

            AnonymousClass1(CoroutineContext coroutineContext, Object obj, ReceiveChannel receiveChannel, FlowCollector flowCollector, Function3 function3) {
                this.f19312c = coroutineContext;
                this.f19313h = obj;
                this.f19314i = receiveChannel;
                this.f19315j = flowCollector;
                this.f19316k = function3;
            }

            /* JADX WARN: Removed duplicated region for block: B:15:0x0031  */
            /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
            @Override // kotlinx.coroutines.flow.FlowCollector
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object k(java.lang.Object r13, kotlin.coroutines.Continuation r14) {
                /*
                    r12 = this;
                    boolean r0 = r14 instanceof kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$2$1$emit$1
                    if (r0 == 0) goto L13
                    r0 = r14
                    kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$2$1$emit$1 r0 = (kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$2$1$emit$1) r0
                    int r1 = r0.label
                    r2 = -2147483648(0xffffffff80000000, float:-0.0)
                    r3 = r1 & r2
                    if (r3 == 0) goto L13
                    int r1 = r1 - r2
                    r0.label = r1
                    goto L18
                L13:
                    kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$2$1$emit$1 r0 = new kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$2$1$emit$1
                    r0.<init>(r12, r14)
                L18:
                    java.lang.Object r14 = r0.result
                    java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.d()
                    int r2 = r0.label
                    r3 = 1
                    if (r2 == 0) goto L31
                    if (r2 != r3) goto L29
                    kotlin.ResultKt.b(r14)
                    goto L51
                L29:
                    java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
                    java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
                    r12.<init>(r13)
                    throw r12
                L31:
                    kotlin.ResultKt.b(r14)
                    kotlin.coroutines.CoroutineContext r14 = r12.f19312c
                    kotlin.Unit r2 = kotlin.Unit.f18288a
                    java.lang.Object r4 = r12.f19313h
                    kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$2$1$1 r11 = new kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$2$1$1
                    kotlinx.coroutines.channels.ReceiveChannel r6 = r12.f19314i
                    kotlinx.coroutines.flow.FlowCollector r7 = r12.f19315j
                    kotlin.jvm.functions.Function3 r8 = r12.f19316k
                    r10 = 0
                    r5 = r11
                    r9 = r13
                    r5.<init>(r6, r7, r8, r9, r10)
                    r0.label = r3
                    java.lang.Object r12 = kotlinx.coroutines.flow.internal.ChannelFlowKt.b(r14, r2, r4, r11, r0)
                    if (r12 != r1) goto L51
                    return r1
                L51:
                    kotlin.Unit r12 = kotlin.Unit.f18288a
                    return r12
                */
                throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1.AnonymousClass2.AnonymousClass1.k(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass2(Flow<Object> flow, CoroutineContext coroutineContext, Object obj, ReceiveChannel<? extends Object> receiveChannel, FlowCollector<Object> flowCollector, Function3<Object, Object, ? super Continuation<Object>, ? extends Object> function3, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$flow = flow;
            this.$scopeContext = coroutineContext;
            this.$cnt = obj;
            this.$second = receiveChannel;
            this.$this_unsafeFlow = flowCollector;
            this.$transform = function3;
        }

        @Override // kotlin.jvm.functions.Function2
        /* renamed from: A, reason: merged with bridge method [inline-methods] */
        public final Object y(Unit unit, Continuation continuation) {
            return ((AnonymousClass2) o(unit, continuation)).v(Unit.f18288a);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation o(Object obj, Continuation continuation) {
            return new AnonymousClass2(this.$flow, this.$scopeContext, this.$cnt, this.$second, this.$this_unsafeFlow, this.$transform, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object v(Object obj) {
            Object d2;
            d2 = IntrinsicsKt__IntrinsicsKt.d();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.b(obj);
                Flow<Object> flow = this.$flow;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$scopeContext, this.$cnt, this.$second, this.$this_unsafeFlow, this.$transform);
                this.label = 1;
                if (flow.a(anonymousClass1, this) == d2) {
                    return d2;
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.b(obj);
            }
            return Unit.f18288a;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    CombineKt$zipImpl$1$1(FlowCollector<Object> flowCollector, Flow<Object> flow, Flow<Object> flow2, Function3<Object, Object, ? super Continuation<Object>, ? extends Object> function3, Continuation<? super CombineKt$zipImpl$1$1> continuation) {
        super(2, continuation);
        this.$this_unsafeFlow = flowCollector;
        this.$flow2 = flow;
        this.$flow = flow2;
        this.$transform = function3;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: A, reason: merged with bridge method [inline-methods] */
    public final Object y(CoroutineScope coroutineScope, Continuation continuation) {
        return ((CombineKt$zipImpl$1$1) o(coroutineScope, continuation)).v(Unit.f18288a);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation o(Object obj, Continuation continuation) {
        CombineKt$zipImpl$1$1 combineKt$zipImpl$1$1 = new CombineKt$zipImpl$1$1(this.$this_unsafeFlow, this.$flow2, this.$flow, this.$transform, continuation);
        combineKt$zipImpl$1$1.L$0 = obj;
        return combineKt$zipImpl$1$1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v12, types: [kotlinx.coroutines.channels.ReceiveChannel] */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v2, types: [kotlinx.coroutines.channels.ReceiveChannel] */
    /* JADX WARN: Type inference failed for: r1v5 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object v(Object obj) {
        Object d2;
        final CompletableJob b2;
        ReceiveChannel receiveChannel;
        ReceiveChannel receiveChannel2;
        CoroutineContext R;
        Unit unit;
        AnonymousClass2 anonymousClass2;
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        ?? r1 = this.label;
        try {
            if (r1 != 0) {
                if (r1 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                receiveChannel2 = (ReceiveChannel) this.L$0;
                try {
                    ResultKt.b(obj);
                    r1 = receiveChannel2;
                } catch (AbortFlowException e2) {
                    e = e2;
                }
                ReceiveChannel.DefaultImpls.a(r1, null, 1, null);
                return Unit.f18288a;
            }
            ResultKt.b(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            ReceiveChannel d3 = ProduceKt.d(coroutineScope, null, 0, new CombineKt$zipImpl$1$1$second$1(this.$flow2, null), 3, null);
            b2 = JobKt__JobKt.b(null, 1, null);
            final FlowCollector<Object> flowCollector = this.$this_unsafeFlow;
            ((SendChannel) d3).u(new Function1<Throwable, Unit>() { // from class: kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object c(Object obj2) {
                    d((Throwable) obj2);
                    return Unit.f18288a;
                }

                public final void d(Throwable th) {
                    if (CompletableJob.this.isActive()) {
                        CompletableJob.this.a(new AbortFlowException(flowCollector));
                    }
                }
            });
            try {
                CoroutineContext K = coroutineScope.K();
                Object b3 = ThreadContextKt.b(K);
                R = coroutineScope.K().R(b2);
                unit = Unit.f18288a;
                anonymousClass2 = new AnonymousClass2(this.$flow, K, b3, d3, this.$this_unsafeFlow, this.$transform, null);
                this.L$0 = d3;
                this.label = 1;
                receiveChannel = d3;
                try {
                } catch (AbortFlowException e3) {
                    e = e3;
                    receiveChannel2 = receiveChannel;
                    FlowExceptions_commonKt.a(e, this.$this_unsafeFlow);
                    r1 = receiveChannel2;
                    ReceiveChannel.DefaultImpls.a(r1, null, 1, null);
                    return Unit.f18288a;
                } catch (Throwable th) {
                    th = th;
                    r1 = receiveChannel;
                    ReceiveChannel.DefaultImpls.a(r1, null, 1, null);
                    throw th;
                }
            } catch (AbortFlowException e4) {
                e = e4;
                receiveChannel = d3;
            } catch (Throwable th2) {
                th = th2;
                receiveChannel = d3;
            }
            if (ChannelFlowKt.c(R, unit, null, anonymousClass2, this, 4, null) == d2) {
                return d2;
            }
            r1 = receiveChannel;
            ReceiveChannel.DefaultImpls.a(r1, null, 1, null);
            return Unit.f18288a;
            FlowExceptions_commonKt.a(e, this.$this_unsafeFlow);
            r1 = receiveChannel2;
            ReceiveChannel.DefaultImpls.a(r1, null, 1, null);
            return Unit.f18288a;
        } catch (Throwable th3) {
            th = th3;
        }
    }
}
