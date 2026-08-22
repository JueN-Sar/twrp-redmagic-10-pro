package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.internal.CombineKt;

@Metadata
/* loaded from: classes2.dex */
public final class FlowKt__ZipKt$combine$$inlined$unsafeFlow$3 implements Flow<Object> {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ Flow[] f19245c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ Function2 f19246h;

    @Metadata
    /* renamed from: kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$3$1, reason: invalid class name */
    public static final class AnonymousClass1 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object v(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FlowKt__ZipKt$combine$$inlined$unsafeFlow$3.this.a(null, this);
        }
    }

    @Override // kotlinx.coroutines.flow.Flow
    public Object a(FlowCollector flowCollector, Continuation continuation) {
        Object d2;
        Flow[] flowArr = this.f19245c;
        Intrinsics.i();
        final Flow[] flowArr2 = this.f19245c;
        Function0<Object[]> function0 = new Function0<Object[]>() { // from class: kotlinx.coroutines.flow.FlowKt__ZipKt$combine$6$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public final Object[] a() {
                int length = flowArr2.length;
                Intrinsics.j(0, "T?");
                return new Object[length];
            }
        };
        Intrinsics.i();
        Object a2 = CombineKt.a(flowCollector, flowArr, function0, new FlowKt__ZipKt$combine$6$2(this.f19246h, null), continuation);
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        return a2 == d2 ? a2 : Unit.f18288a;
    }
}
