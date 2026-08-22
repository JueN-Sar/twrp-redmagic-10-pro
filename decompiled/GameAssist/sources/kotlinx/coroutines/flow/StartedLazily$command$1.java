package kotlinx.coroutines.flow;

import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;

@Metadata
@DebugMetadata(c = "kotlinx.coroutines.flow.StartedLazily$command$1", f = "SharingStarted.kt", l = {155}, m = "invokeSuspend")
/* loaded from: classes2.dex */
final class StartedLazily$command$1 extends SuspendLambda implements Function2<FlowCollector<? super SharingCommand>, Continuation<? super Unit>, Object> {
    final /* synthetic */ StateFlow<Integer> $subscriptionCount;
    private /* synthetic */ Object L$0;
    int label;

    @Metadata
    /* renamed from: kotlinx.coroutines.flow.StartedLazily$command$1$1, reason: invalid class name */
    static final class AnonymousClass1<T> implements FlowCollector {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ Ref.BooleanRef f19273c;

        /* renamed from: h, reason: collision with root package name */
        final /* synthetic */ FlowCollector f19274h;

        AnonymousClass1(Ref.BooleanRef booleanRef, FlowCollector flowCollector) {
            this.f19273c = booleanRef;
            this.f19274h = flowCollector;
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x0031  */
        /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object a(int r5, kotlin.coroutines.Continuation r6) {
            /*
                r4 = this;
                boolean r0 = r6 instanceof kotlinx.coroutines.flow.StartedLazily$command$1$1$emit$1
                if (r0 == 0) goto L13
                r0 = r6
                kotlinx.coroutines.flow.StartedLazily$command$1$1$emit$1 r0 = (kotlinx.coroutines.flow.StartedLazily$command$1$1$emit$1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L13
                int r1 = r1 - r2
                r0.label = r1
                goto L18
            L13:
                kotlinx.coroutines.flow.StartedLazily$command$1$1$emit$1 r0 = new kotlinx.coroutines.flow.StartedLazily$command$1$1$emit$1
                r0.<init>(r4, r6)
            L18:
                java.lang.Object r6 = r0.result
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.d()
                int r2 = r0.label
                r3 = 1
                if (r2 == 0) goto L31
                if (r2 != r3) goto L29
                kotlin.ResultKt.b(r6)
                goto L4b
            L29:
                java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                r4.<init>(r5)
                throw r4
            L31:
                kotlin.ResultKt.b(r6)
                if (r5 <= 0) goto L4e
                kotlin.jvm.internal.Ref$BooleanRef r5 = r4.f19273c
                boolean r6 = r5.element
                if (r6 != 0) goto L4e
                r5.element = r3
                kotlinx.coroutines.flow.FlowCollector r4 = r4.f19274h
                kotlinx.coroutines.flow.SharingCommand r5 = kotlinx.coroutines.flow.SharingCommand.START
                r0.label = r3
                java.lang.Object r4 = r4.k(r5, r0)
                if (r4 != r1) goto L4b
                return r1
            L4b:
                kotlin.Unit r4 = kotlin.Unit.f18288a
                return r4
            L4e:
                kotlin.Unit r4 = kotlin.Unit.f18288a
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.StartedLazily$command$1.AnonymousClass1.a(int, kotlin.coroutines.Continuation):java.lang.Object");
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        public /* bridge */ /* synthetic */ Object k(Object obj, Continuation continuation) {
            return a(((Number) obj).intValue(), continuation);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    StartedLazily$command$1(StateFlow<Integer> stateFlow, Continuation<? super StartedLazily$command$1> continuation) {
        super(2, continuation);
        this.$subscriptionCount = stateFlow;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: A, reason: merged with bridge method [inline-methods] */
    public final Object y(FlowCollector flowCollector, Continuation continuation) {
        return ((StartedLazily$command$1) o(flowCollector, continuation)).v(Unit.f18288a);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation o(Object obj, Continuation continuation) {
        StartedLazily$command$1 startedLazily$command$1 = new StartedLazily$command$1(this.$subscriptionCount, continuation);
        startedLazily$command$1.L$0 = obj;
        return startedLazily$command$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object v(Object obj) {
        Object d2;
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.b(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            Ref.BooleanRef booleanRef = new Ref.BooleanRef();
            StateFlow<Integer> stateFlow = this.$subscriptionCount;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(booleanRef, flowCollector);
            this.label = 1;
            if (stateFlow.a(anonymousClass1, this) == d2) {
                return d2;
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.b(obj);
        }
        throw new KotlinNothingValueException();
    }
}
