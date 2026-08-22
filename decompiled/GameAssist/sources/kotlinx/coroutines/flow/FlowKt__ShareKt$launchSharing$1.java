package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata
@DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__ShareKt$launchSharing$1", f = "Share.kt", l = {214, 218, 219, 225}, m = "invokeSuspend")
/* loaded from: classes2.dex */
final class FlowKt__ShareKt$launchSharing$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Object $initialValue;
    final /* synthetic */ MutableSharedFlow<Object> $shared;
    final /* synthetic */ SharingStarted $started;
    final /* synthetic */ Flow<Object> $upstream;
    int label;

    @Metadata
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__ShareKt$launchSharing$1$1", f = "Share.kt", l = {}, m = "invokeSuspend")
    /* renamed from: kotlinx.coroutines.flow.FlowKt__ShareKt$launchSharing$1$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<Integer, Continuation<? super Boolean>, Object> {
        /* synthetic */ int I$0;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        public final Object A(int i2, Continuation continuation) {
            return ((AnonymousClass1) o(Integer.valueOf(i2), continuation)).v(Unit.f18288a);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation o(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(continuation);
            anonymousClass1.I$0 = ((Number) obj).intValue();
            return anonymousClass1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object v(Object obj) {
            IntrinsicsKt__IntrinsicsKt.d();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.b(obj);
            return Boxing.a(this.I$0 > 0);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object y(Object obj, Object obj2) {
            return A(((Number) obj).intValue(), (Continuation) obj2);
        }
    }

    @Metadata
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__ShareKt$launchSharing$1$2", f = "Share.kt", l = {227}, m = "invokeSuspend")
    /* renamed from: kotlinx.coroutines.flow.FlowKt__ShareKt$launchSharing$1$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<SharingCommand, Continuation<? super Unit>, Object> {
        final /* synthetic */ Object $initialValue;
        final /* synthetic */ MutableSharedFlow<Object> $shared;
        final /* synthetic */ Flow<Object> $upstream;
        /* synthetic */ Object L$0;
        int label;

        @Metadata
        /* renamed from: kotlinx.coroutines.flow.FlowKt__ShareKt$launchSharing$1$2$WhenMappings */
        public /* synthetic */ class WhenMappings {

            /* renamed from: a, reason: collision with root package name */
            public static final /* synthetic */ int[] f19192a;

            static {
                int[] iArr = new int[SharingCommand.values().length];
                iArr[SharingCommand.START.ordinal()] = 1;
                iArr[SharingCommand.STOP.ordinal()] = 2;
                iArr[SharingCommand.STOP_AND_RESET_REPLAY_CACHE.ordinal()] = 3;
                f19192a = iArr;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(Flow<Object> flow, MutableSharedFlow<Object> mutableSharedFlow, Object obj, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$upstream = flow;
            this.$shared = mutableSharedFlow;
            this.$initialValue = obj;
        }

        @Override // kotlin.jvm.functions.Function2
        /* renamed from: A, reason: merged with bridge method [inline-methods] */
        public final Object y(SharingCommand sharingCommand, Continuation continuation) {
            return ((AnonymousClass2) o(sharingCommand, continuation)).v(Unit.f18288a);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation o(Object obj, Continuation continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$upstream, this.$shared, this.$initialValue, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object v(Object obj) {
            Object d2;
            d2 = IntrinsicsKt__IntrinsicsKt.d();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.b(obj);
                int i3 = WhenMappings.f19192a[((SharingCommand) this.L$0).ordinal()];
                if (i3 == 1) {
                    Flow<Object> flow = this.$upstream;
                    MutableSharedFlow<Object> mutableSharedFlow = this.$shared;
                    this.label = 1;
                    if (flow.a(mutableSharedFlow, this) == d2) {
                        return d2;
                    }
                } else if (i3 == 3) {
                    Object obj2 = this.$initialValue;
                    if (obj2 == SharedFlowKt.f19266a) {
                        this.$shared.i();
                    } else {
                        this.$shared.e(obj2);
                    }
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
    FlowKt__ShareKt$launchSharing$1(SharingStarted sharingStarted, Flow<Object> flow, MutableSharedFlow<Object> mutableSharedFlow, Object obj, Continuation<? super FlowKt__ShareKt$launchSharing$1> continuation) {
        super(2, continuation);
        this.$started = sharingStarted;
        this.$upstream = flow;
        this.$shared = mutableSharedFlow;
        this.$initialValue = obj;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: A, reason: merged with bridge method [inline-methods] */
    public final Object y(CoroutineScope coroutineScope, Continuation continuation) {
        return ((FlowKt__ShareKt$launchSharing$1) o(coroutineScope, continuation)).v(Unit.f18288a);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation o(Object obj, Continuation continuation) {
        return new FlowKt__ShareKt$launchSharing$1(this.$started, this.$upstream, this.$shared, this.$initialValue, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0068 A[RETURN] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object v(java.lang.Object r8) {
        /*
            r7 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.d()
            int r1 = r7.label
            r2 = 4
            r3 = 3
            r4 = 2
            r5 = 1
            if (r1 == 0) goto L25
            if (r1 == r5) goto L21
            if (r1 == r4) goto L1d
            if (r1 == r3) goto L21
            if (r1 != r2) goto L15
            goto L21
        L15:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L1d:
            kotlin.ResultKt.b(r8)
            goto L5c
        L21:
            kotlin.ResultKt.b(r8)
            goto L8d
        L25:
            kotlin.ResultKt.b(r8)
            kotlinx.coroutines.flow.SharingStarted r8 = r7.$started
            kotlinx.coroutines.flow.SharingStarted$Companion r1 = kotlinx.coroutines.flow.SharingStarted.f19269a
            kotlinx.coroutines.flow.SharingStarted r6 = r1.a()
            if (r8 != r6) goto L3f
            kotlinx.coroutines.flow.Flow<java.lang.Object> r8 = r7.$upstream
            kotlinx.coroutines.flow.MutableSharedFlow<java.lang.Object> r1 = r7.$shared
            r7.label = r5
            java.lang.Object r7 = r8.a(r1, r7)
            if (r7 != r0) goto L8d
            return r0
        L3f:
            kotlinx.coroutines.flow.SharingStarted r8 = r7.$started
            kotlinx.coroutines.flow.SharingStarted r1 = r1.b()
            r5 = 0
            if (r8 != r1) goto L69
            kotlinx.coroutines.flow.MutableSharedFlow<java.lang.Object> r8 = r7.$shared
            kotlinx.coroutines.flow.StateFlow r8 = r8.h()
            kotlinx.coroutines.flow.FlowKt__ShareKt$launchSharing$1$1 r1 = new kotlinx.coroutines.flow.FlowKt__ShareKt$launchSharing$1$1
            r1.<init>(r5)
            r7.label = r4
            java.lang.Object r8 = kotlinx.coroutines.flow.FlowKt.n(r8, r1, r7)
            if (r8 != r0) goto L5c
            return r0
        L5c:
            kotlinx.coroutines.flow.Flow<java.lang.Object> r8 = r7.$upstream
            kotlinx.coroutines.flow.MutableSharedFlow<java.lang.Object> r1 = r7.$shared
            r7.label = r3
            java.lang.Object r7 = r8.a(r1, r7)
            if (r7 != r0) goto L8d
            return r0
        L69:
            kotlinx.coroutines.flow.SharingStarted r8 = r7.$started
            kotlinx.coroutines.flow.MutableSharedFlow<java.lang.Object> r1 = r7.$shared
            kotlinx.coroutines.flow.StateFlow r1 = r1.h()
            kotlinx.coroutines.flow.Flow r8 = r8.a(r1)
            kotlinx.coroutines.flow.Flow r8 = kotlinx.coroutines.flow.FlowKt.h(r8)
            kotlinx.coroutines.flow.FlowKt__ShareKt$launchSharing$1$2 r1 = new kotlinx.coroutines.flow.FlowKt__ShareKt$launchSharing$1$2
            kotlinx.coroutines.flow.Flow<java.lang.Object> r3 = r7.$upstream
            kotlinx.coroutines.flow.MutableSharedFlow<java.lang.Object> r4 = r7.$shared
            java.lang.Object r6 = r7.$initialValue
            r1.<init>(r3, r4, r6, r5)
            r7.label = r2
            java.lang.Object r7 = kotlinx.coroutines.flow.FlowKt.e(r8, r1, r7)
            if (r7 != r0) goto L8d
            return r0
        L8d:
            kotlin.Unit r7 = kotlin.Unit.f18288a
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__ShareKt$launchSharing$1.v(java.lang.Object):java.lang.Object");
    }
}
