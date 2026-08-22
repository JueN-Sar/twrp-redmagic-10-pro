package kotlin.coroutines.intrinsics;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.jvm.internal.BaseContinuationImpl;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.RestrictedContinuationImpl;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.TypeIntrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public class IntrinsicsKt__IntrinsicsJvmKt {
    /* JADX WARN: Multi-variable type inference failed */
    public static Continuation a(final Function1 function1, Continuation completion) {
        Intrinsics.e(function1, "<this>");
        Intrinsics.e(completion, "completion");
        final Continuation a2 = DebugProbesKt.a(completion);
        if (function1 instanceof BaseContinuationImpl) {
            return ((BaseContinuationImpl) function1).p(a2);
        }
        final CoroutineContext context = a2.getContext();
        return context == EmptyCoroutineContext.INSTANCE ? new RestrictedContinuationImpl(a2, function1) { // from class: kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt$createCoroutineUnintercepted$$inlined$createCoroutineFromSuspendFunction$IntrinsicsKt__IntrinsicsJvmKt$1
            final /* synthetic */ Function1 $this_createCoroutineUnintercepted$inlined;
            private int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(a2);
                this.$this_createCoroutineUnintercepted$inlined = function1;
                Intrinsics.c(a2, "null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            protected Object v(Object obj) {
                int i2 = this.label;
                if (i2 == 0) {
                    this.label = 1;
                    ResultKt.b(obj);
                    Intrinsics.c(this.$this_createCoroutineUnintercepted$inlined, "null cannot be cast to non-null type kotlin.Function1<kotlin.coroutines.Continuation<T of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted$lambda$0>, kotlin.Any?>");
                    return ((Function1) TypeIntrinsics.a(this.$this_createCoroutineUnintercepted$inlined, 1)).c(this);
                }
                if (i2 != 1) {
                    throw new IllegalStateException("This coroutine had already completed".toString());
                }
                this.label = 2;
                ResultKt.b(obj);
                return obj;
            }
        } : new ContinuationImpl(a2, context, function1) { // from class: kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt$createCoroutineUnintercepted$$inlined$createCoroutineFromSuspendFunction$IntrinsicsKt__IntrinsicsJvmKt$2
            final /* synthetic */ Function1 $this_createCoroutineUnintercepted$inlined;
            private int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(a2, context);
                this.$this_createCoroutineUnintercepted$inlined = function1;
                Intrinsics.c(a2, "null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            protected Object v(Object obj) {
                int i2 = this.label;
                if (i2 == 0) {
                    this.label = 1;
                    ResultKt.b(obj);
                    Intrinsics.c(this.$this_createCoroutineUnintercepted$inlined, "null cannot be cast to non-null type kotlin.Function1<kotlin.coroutines.Continuation<T of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted$lambda$0>, kotlin.Any?>");
                    return ((Function1) TypeIntrinsics.a(this.$this_createCoroutineUnintercepted$inlined, 1)).c(this);
                }
                if (i2 != 1) {
                    throw new IllegalStateException("This coroutine had already completed".toString());
                }
                this.label = 2;
                ResultKt.b(obj);
                return obj;
            }
        };
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Continuation b(final Function2 function2, final Object obj, Continuation completion) {
        Intrinsics.e(function2, "<this>");
        Intrinsics.e(completion, "completion");
        final Continuation a2 = DebugProbesKt.a(completion);
        if (function2 instanceof BaseContinuationImpl) {
            return ((BaseContinuationImpl) function2).o(obj, a2);
        }
        final CoroutineContext context = a2.getContext();
        return context == EmptyCoroutineContext.INSTANCE ? new RestrictedContinuationImpl(a2, function2, obj) { // from class: kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt$createCoroutineUnintercepted$$inlined$createCoroutineFromSuspendFunction$IntrinsicsKt__IntrinsicsJvmKt$3
            final /* synthetic */ Object $receiver$inlined;
            final /* synthetic */ Function2 $this_createCoroutineUnintercepted$inlined;
            private int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(a2);
                this.$this_createCoroutineUnintercepted$inlined = function2;
                this.$receiver$inlined = obj;
                Intrinsics.c(a2, "null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            protected Object v(Object obj2) {
                int i2 = this.label;
                if (i2 == 0) {
                    this.label = 1;
                    ResultKt.b(obj2);
                    Intrinsics.c(this.$this_createCoroutineUnintercepted$inlined, "null cannot be cast to non-null type kotlin.Function2<R of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted$lambda$1, kotlin.coroutines.Continuation<T of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted$lambda$1>, kotlin.Any?>");
                    return ((Function2) TypeIntrinsics.a(this.$this_createCoroutineUnintercepted$inlined, 2)).y(this.$receiver$inlined, this);
                }
                if (i2 != 1) {
                    throw new IllegalStateException("This coroutine had already completed".toString());
                }
                this.label = 2;
                ResultKt.b(obj2);
                return obj2;
            }
        } : new ContinuationImpl(a2, context, function2, obj) { // from class: kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt$createCoroutineUnintercepted$$inlined$createCoroutineFromSuspendFunction$IntrinsicsKt__IntrinsicsJvmKt$4
            final /* synthetic */ Object $receiver$inlined;
            final /* synthetic */ Function2 $this_createCoroutineUnintercepted$inlined;
            private int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(a2, context);
                this.$this_createCoroutineUnintercepted$inlined = function2;
                this.$receiver$inlined = obj;
                Intrinsics.c(a2, "null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            protected Object v(Object obj2) {
                int i2 = this.label;
                if (i2 == 0) {
                    this.label = 1;
                    ResultKt.b(obj2);
                    Intrinsics.c(this.$this_createCoroutineUnintercepted$inlined, "null cannot be cast to non-null type kotlin.Function2<R of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted$lambda$1, kotlin.coroutines.Continuation<T of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted$lambda$1>, kotlin.Any?>");
                    return ((Function2) TypeIntrinsics.a(this.$this_createCoroutineUnintercepted$inlined, 2)).y(this.$receiver$inlined, this);
                }
                if (i2 != 1) {
                    throw new IllegalStateException("This coroutine had already completed".toString());
                }
                this.label = 2;
                ResultKt.b(obj2);
                return obj2;
            }
        };
    }

    public static Continuation c(Continuation continuation) {
        Continuation x;
        Intrinsics.e(continuation, "<this>");
        ContinuationImpl continuationImpl = continuation instanceof ContinuationImpl ? (ContinuationImpl) continuation : null;
        return (continuationImpl == null || (x = continuationImpl.x()) == null) ? continuation : x;
    }
}
