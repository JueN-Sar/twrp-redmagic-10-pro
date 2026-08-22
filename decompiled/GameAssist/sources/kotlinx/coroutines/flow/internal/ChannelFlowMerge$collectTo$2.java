package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.sync.Semaphore;

@Metadata
/* loaded from: classes2.dex */
final class ChannelFlowMerge$collectTo$2<T> implements FlowCollector {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ Job f19296c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ Semaphore f19297h;

    /* renamed from: i, reason: collision with root package name */
    final /* synthetic */ ProducerScope f19298i;

    /* renamed from: j, reason: collision with root package name */
    final /* synthetic */ SendingCollector f19299j;

    @Metadata
    @DebugMetadata(c = "kotlinx.coroutines.flow.internal.ChannelFlowMerge$collectTo$2$1", f = "Merge.kt", l = {69}, m = "invokeSuspend")
    /* renamed from: kotlinx.coroutines.flow.internal.ChannelFlowMerge$collectTo$2$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ SendingCollector<T> $collector;
        final /* synthetic */ Flow<T> $inner;
        final /* synthetic */ Semaphore $semaphore;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(Flow<? extends T> flow, SendingCollector<T> sendingCollector, Semaphore semaphore, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$inner = flow;
            this.$collector = sendingCollector;
            this.$semaphore = semaphore;
        }

        @Override // kotlin.jvm.functions.Function2
        /* renamed from: A, reason: merged with bridge method [inline-methods] */
        public final Object y(CoroutineScope coroutineScope, Continuation continuation) {
            return ((AnonymousClass1) o(coroutineScope, continuation)).v(Unit.f18288a);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation o(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.$inner, this.$collector, this.$semaphore, continuation);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r3v4, types: [java.lang.Object, kotlin.Unit] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object v(Object obj) {
            Object d2;
            d2 = IntrinsicsKt__IntrinsicsKt.d();
            int i2 = this.label;
            try {
                if (i2 == 0) {
                    ResultKt.b(obj);
                    Flow<T> flow = this.$inner;
                    SendingCollector<T> sendingCollector = this.$collector;
                    this.label = 1;
                    if (flow.a(sendingCollector, this) == d2) {
                        return d2;
                    }
                } else {
                    if (i2 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.b(obj);
                }
                this.$semaphore.release();
                this = Unit.f18288a;
                return this;
            } catch (Throwable th) {
                this.$semaphore.release();
                throw th;
            }
        }
    }

    ChannelFlowMerge$collectTo$2(Job job, Semaphore semaphore, ProducerScope producerScope, SendingCollector sendingCollector) {
        this.f19296c = job;
        this.f19297h = semaphore;
        this.f19298i = producerScope;
        this.f19299j = sendingCollector;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object k(kotlinx.coroutines.flow.Flow r7, kotlin.coroutines.Continuation r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof kotlinx.coroutines.flow.internal.ChannelFlowMerge$collectTo$2$emit$1
            if (r0 == 0) goto L13
            r0 = r8
            kotlinx.coroutines.flow.internal.ChannelFlowMerge$collectTo$2$emit$1 r0 = (kotlinx.coroutines.flow.internal.ChannelFlowMerge$collectTo$2$emit$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.flow.internal.ChannelFlowMerge$collectTo$2$emit$1 r0 = new kotlinx.coroutines.flow.internal.ChannelFlowMerge$collectTo$2$emit$1
            r0.<init>(r6, r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.d()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3a
            if (r2 != r3) goto L32
            java.lang.Object r6 = r0.L$1
            r7 = r6
            kotlinx.coroutines.flow.Flow r7 = (kotlinx.coroutines.flow.Flow) r7
            java.lang.Object r6 = r0.L$0
            kotlinx.coroutines.flow.internal.ChannelFlowMerge$collectTo$2 r6 = (kotlinx.coroutines.flow.internal.ChannelFlowMerge$collectTo$2) r6
            kotlin.ResultKt.b(r8)
            goto L53
        L32:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L3a:
            kotlin.ResultKt.b(r8)
            kotlinx.coroutines.Job r8 = r6.f19296c
            if (r8 == 0) goto L44
            kotlinx.coroutines.JobKt.h(r8)
        L44:
            kotlinx.coroutines.sync.Semaphore r8 = r6.f19297h
            r0.L$0 = r6
            r0.L$1 = r7
            r0.label = r3
            java.lang.Object r8 = r8.a(r0)
            if (r8 != r1) goto L53
            return r1
        L53:
            kotlinx.coroutines.channels.ProducerScope r0 = r6.f19298i
            kotlinx.coroutines.flow.internal.ChannelFlowMerge$collectTo$2$1 r3 = new kotlinx.coroutines.flow.internal.ChannelFlowMerge$collectTo$2$1
            kotlinx.coroutines.flow.internal.SendingCollector r8 = r6.f19299j
            kotlinx.coroutines.sync.Semaphore r6 = r6.f19297h
            r1 = 0
            r3.<init>(r7, r8, r6, r1)
            r4 = 3
            r5 = 0
            r2 = 0
            kotlinx.coroutines.BuildersKt.b(r0, r1, r2, r3, r4, r5)
            kotlin.Unit r6 = kotlin.Unit.f18288a
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.internal.ChannelFlowMerge$collectTo$2.k(kotlinx.coroutines.flow.Flow, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
