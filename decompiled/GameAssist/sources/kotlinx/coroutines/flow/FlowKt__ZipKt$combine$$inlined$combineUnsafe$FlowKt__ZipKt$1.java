package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.InlineMarker;
import kotlinx.coroutines.flow.internal.CombineKt;

@Metadata
/* loaded from: classes2.dex */
public final class FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 implements Flow<Object> {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ Flow[] f19234c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ Function4 f19235h;

    @Metadata
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1$2", f = "Zip.kt", l = {333, 333}, m = "invokeSuspend")
    /* renamed from: kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1$2, reason: invalid class name */
    public static final class AnonymousClass2 extends SuspendLambda implements Function3<FlowCollector<Object>, Object[], Continuation<? super Unit>, Object> {
        final /* synthetic */ Function4 $transform$inlined;
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(Continuation continuation, Function4 function4) {
            super(3, continuation);
            this.$transform$inlined = function4;
        }

        @Override // kotlin.jvm.functions.Function3
        /* renamed from: A, reason: merged with bridge method [inline-methods] */
        public final Object u(FlowCollector flowCollector, Object[] objArr, Continuation continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(continuation, this.$transform$inlined);
            anonymousClass2.L$0 = flowCollector;
            anonymousClass2.L$1 = objArr;
            return anonymousClass2.v(Unit.f18288a);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object v(Object obj) {
            Object d2;
            FlowCollector flowCollector;
            d2 = IntrinsicsKt__IntrinsicsKt.d();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.b(obj);
                flowCollector = (FlowCollector) this.L$0;
                Object[] objArr = (Object[]) this.L$1;
                Function4 function4 = this.$transform$inlined;
                Object obj2 = objArr[0];
                Object obj3 = objArr[1];
                Object obj4 = objArr[2];
                this.L$0 = flowCollector;
                this.label = 1;
                InlineMarker.c(6);
                obj = function4.j(obj2, obj3, obj4, this);
                InlineMarker.c(7);
                if (obj == d2) {
                    return d2;
                }
            } else {
                if (i2 != 1) {
                    if (i2 != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.b(obj);
                    return Unit.f18288a;
                }
                flowCollector = (FlowCollector) this.L$0;
                ResultKt.b(obj);
            }
            this.L$0 = null;
            this.label = 2;
            if (flowCollector.k(obj, this) == d2) {
                return d2;
            }
            return Unit.f18288a;
        }
    }

    @Override // kotlinx.coroutines.flow.Flow
    public Object a(FlowCollector flowCollector, Continuation continuation) {
        Function0 b2;
        Object d2;
        Flow[] flowArr = this.f19234c;
        b2 = FlowKt__ZipKt.b();
        Object a2 = CombineKt.a(flowCollector, flowArr, b2, new AnonymousClass2(null, this.f19235h), continuation);
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        return a2 == d2 ? a2 : Unit.f18288a;
    }
}
