package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata
@DebugMetadata(c = "kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest$flowCollect$3", f = "Merge.kt", l = {27}, m = "invokeSuspend")
/* loaded from: classes2.dex */
final class ChannelFlowTransformLatest$flowCollect$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ FlowCollector<R> $collector;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ ChannelFlowTransformLatest<T, R> this$0;

    @Metadata
    /* renamed from: kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest$flowCollect$3$1, reason: invalid class name */
    static final class AnonymousClass1<T> implements FlowCollector {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ Ref.ObjectRef f19302c;

        /* renamed from: h, reason: collision with root package name */
        final /* synthetic */ CoroutineScope f19303h;

        /* renamed from: i, reason: collision with root package name */
        final /* synthetic */ ChannelFlowTransformLatest f19304i;

        /* renamed from: j, reason: collision with root package name */
        final /* synthetic */ FlowCollector f19305j;

        @Metadata
        @DebugMetadata(c = "kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest$flowCollect$3$1$2", f = "Merge.kt", l = {34}, m = "invokeSuspend")
        /* renamed from: kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest$flowCollect$3$1$2, reason: invalid class name */
        static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<R> $collector;
            final /* synthetic */ T $value;
            int label;
            final /* synthetic */ ChannelFlowTransformLatest<T, R> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass2(ChannelFlowTransformLatest<T, R> channelFlowTransformLatest, FlowCollector<? super R> flowCollector, T t, Continuation<? super AnonymousClass2> continuation) {
                super(2, continuation);
                this.this$0 = channelFlowTransformLatest;
                this.$collector = flowCollector;
                this.$value = t;
            }

            @Override // kotlin.jvm.functions.Function2
            /* renamed from: A, reason: merged with bridge method [inline-methods] */
            public final Object y(CoroutineScope coroutineScope, Continuation continuation) {
                return ((AnonymousClass2) o(coroutineScope, continuation)).v(Unit.f18288a);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation o(Object obj, Continuation continuation) {
                return new AnonymousClass2(this.this$0, this.$collector, this.$value, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object v(Object obj) {
                Object d2;
                Function3 function3;
                d2 = IntrinsicsKt__IntrinsicsKt.d();
                int i2 = this.label;
                if (i2 == 0) {
                    ResultKt.b(obj);
                    function3 = ((ChannelFlowTransformLatest) this.this$0).f19301k;
                    Object obj2 = this.$collector;
                    T t = this.$value;
                    this.label = 1;
                    if (function3.u(obj2, t, this) == d2) {
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

        AnonymousClass1(Ref.ObjectRef objectRef, CoroutineScope coroutineScope, ChannelFlowTransformLatest channelFlowTransformLatest, FlowCollector flowCollector) {
            this.f19302c = objectRef;
            this.f19303h = coroutineScope;
            this.f19304i = channelFlowTransformLatest;
            this.f19305j = flowCollector;
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x003b  */
        /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
        @Override // kotlinx.coroutines.flow.FlowCollector
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object k(java.lang.Object r7, kotlin.coroutines.Continuation r8) {
            /*
                r6 = this;
                boolean r0 = r8 instanceof kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest$flowCollect$3$1$emit$1
                if (r0 == 0) goto L13
                r0 = r8
                kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest$flowCollect$3$1$emit$1 r0 = (kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest$flowCollect$3$1$emit$1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L13
                int r1 = r1 - r2
                r0.label = r1
                goto L18
            L13:
                kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest$flowCollect$3$1$emit$1 r0 = new kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest$flowCollect$3$1$emit$1
                r0.<init>(r6, r8)
            L18:
                java.lang.Object r8 = r0.result
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.d()
                int r2 = r0.label
                r3 = 1
                if (r2 == 0) goto L3b
                if (r2 != r3) goto L33
                java.lang.Object r6 = r0.L$2
                kotlinx.coroutines.Job r6 = (kotlinx.coroutines.Job) r6
                java.lang.Object r7 = r0.L$1
                java.lang.Object r6 = r0.L$0
                kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest$flowCollect$3$1 r6 = (kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest$flowCollect$3.AnonymousClass1) r6
                kotlin.ResultKt.b(r8)
                goto L5d
            L33:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r7)
                throw r6
            L3b:
                kotlin.ResultKt.b(r8)
                kotlin.jvm.internal.Ref$ObjectRef r8 = r6.f19302c
                T r8 = r8.element
                kotlinx.coroutines.Job r8 = (kotlinx.coroutines.Job) r8
                if (r8 == 0) goto L5d
                kotlinx.coroutines.flow.internal.ChildCancelledException r2 = new kotlinx.coroutines.flow.internal.ChildCancelledException
                r2.<init>()
                r8.a(r2)
                r0.L$0 = r6
                r0.L$1 = r7
                r0.L$2 = r8
                r0.label = r3
                java.lang.Object r8 = r8.T(r0)
                if (r8 != r1) goto L5d
                return r1
            L5d:
                kotlin.jvm.internal.Ref$ObjectRef r8 = r6.f19302c
                kotlinx.coroutines.CoroutineScope r0 = r6.f19303h
                kotlinx.coroutines.CoroutineStart r2 = kotlinx.coroutines.CoroutineStart.UNDISPATCHED
                kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest$flowCollect$3$1$2 r3 = new kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest$flowCollect$3$1$2
                kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest r1 = r6.f19304i
                kotlinx.coroutines.flow.FlowCollector r6 = r6.f19305j
                r4 = 0
                r3.<init>(r1, r6, r7, r4)
                r4 = 1
                r5 = 0
                r1 = 0
                kotlinx.coroutines.Job r6 = kotlinx.coroutines.BuildersKt.b(r0, r1, r2, r3, r4, r5)
                r8.element = r6
                kotlin.Unit r6 = kotlin.Unit.f18288a
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest$flowCollect$3.AnonymousClass1.k(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    ChannelFlowTransformLatest$flowCollect$3(ChannelFlowTransformLatest<T, R> channelFlowTransformLatest, FlowCollector<? super R> flowCollector, Continuation<? super ChannelFlowTransformLatest$flowCollect$3> continuation) {
        super(2, continuation);
        this.this$0 = channelFlowTransformLatest;
        this.$collector = flowCollector;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: A, reason: merged with bridge method [inline-methods] */
    public final Object y(CoroutineScope coroutineScope, Continuation continuation) {
        return ((ChannelFlowTransformLatest$flowCollect$3) o(coroutineScope, continuation)).v(Unit.f18288a);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation o(Object obj, Continuation continuation) {
        ChannelFlowTransformLatest$flowCollect$3 channelFlowTransformLatest$flowCollect$3 = new ChannelFlowTransformLatest$flowCollect$3(this.this$0, this.$collector, continuation);
        channelFlowTransformLatest$flowCollect$3.L$0 = obj;
        return channelFlowTransformLatest$flowCollect$3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object v(Object obj) {
        Object d2;
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.b(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            ChannelFlowTransformLatest<T, R> channelFlowTransformLatest = this.this$0;
            Flow flow = channelFlowTransformLatest.f19300j;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(objectRef, coroutineScope, channelFlowTransformLatest, this.$collector);
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
