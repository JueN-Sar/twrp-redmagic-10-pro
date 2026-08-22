package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.internal.CombineKt;

@Metadata
@DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__ZipKt$combineTransformUnsafe$1", f = "Zip.kt", l = {273}, m = "invokeSuspend")
/* loaded from: classes2.dex */
public final class FlowKt__ZipKt$combineTransformUnsafe$1 extends SuspendLambda implements Function2<FlowCollector<Object>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Flow<Object>[] $flows;
    final /* synthetic */ Function3<FlowCollector<Object>, Object[], Continuation<? super Unit>, Object> $transform;
    private /* synthetic */ Object L$0;
    int label;

    @Metadata
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__ZipKt$combineTransformUnsafe$1$1", f = "Zip.kt", l = {273}, m = "invokeSuspend")
    /* renamed from: kotlinx.coroutines.flow.FlowKt__ZipKt$combineTransformUnsafe$1$1, reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function3<FlowCollector<Object>, Object[], Continuation<? super Unit>, Object> {
        final /* synthetic */ Function3<FlowCollector<Object>, Object[], Continuation<? super Unit>, Object> $transform;
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(Function3<? super FlowCollector<Object>, ? super Object[], ? super Continuation<? super Unit>, ? extends Object> function3, Continuation<? super AnonymousClass1> continuation) {
            super(3, continuation);
            this.$transform = function3;
        }

        @Override // kotlin.jvm.functions.Function3
        /* renamed from: A, reason: merged with bridge method [inline-methods] */
        public final Object u(FlowCollector flowCollector, Object[] objArr, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$transform, continuation);
            anonymousClass1.L$0 = flowCollector;
            anonymousClass1.L$1 = objArr;
            return anonymousClass1.v(Unit.f18288a);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object v(Object obj) {
            Object d2;
            d2 = IntrinsicsKt__IntrinsicsKt.d();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.b(obj);
                FlowCollector flowCollector = (FlowCollector) this.L$0;
                Object[] objArr = (Object[]) this.L$1;
                Function3<FlowCollector<Object>, Object[], Continuation<? super Unit>, Object> function3 = this.$transform;
                this.L$0 = null;
                this.label = 1;
                if (function3.u(flowCollector, objArr, this) == d2) {
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
    public FlowKt__ZipKt$combineTransformUnsafe$1(Flow<Object>[] flowArr, Function3<? super FlowCollector<Object>, ? super Object[], ? super Continuation<? super Unit>, ? extends Object> function3, Continuation<? super FlowKt__ZipKt$combineTransformUnsafe$1> continuation) {
        super(2, continuation);
        this.$flows = flowArr;
        this.$transform = function3;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: A, reason: merged with bridge method [inline-methods] */
    public final Object y(FlowCollector flowCollector, Continuation continuation) {
        return ((FlowKt__ZipKt$combineTransformUnsafe$1) o(flowCollector, continuation)).v(Unit.f18288a);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation o(Object obj, Continuation continuation) {
        FlowKt__ZipKt$combineTransformUnsafe$1 flowKt__ZipKt$combineTransformUnsafe$1 = new FlowKt__ZipKt$combineTransformUnsafe$1(this.$flows, this.$transform, continuation);
        flowKt__ZipKt$combineTransformUnsafe$1.L$0 = obj;
        return flowKt__ZipKt$combineTransformUnsafe$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object v(Object obj) {
        Object d2;
        Function0 b2;
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.b(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            Flow<Object>[] flowArr = this.$flows;
            b2 = FlowKt__ZipKt.b();
            Intrinsics.i();
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$transform, null);
            this.label = 1;
            if (CombineKt.a(flowCollector, flowArr, b2, anonymousClass1, this) == d2) {
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
