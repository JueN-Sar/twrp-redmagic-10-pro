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
public final class FlowKt__ZipKt$combineUnsafe$$inlined$unsafeFlow$1 implements Flow<Object> {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ Flow[] f19247c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ Function2 f19248h;

    @Metadata
    /* renamed from: kotlinx.coroutines.flow.FlowKt__ZipKt$combineUnsafe$$inlined$unsafeFlow$1$1, reason: invalid class name */
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
            return FlowKt__ZipKt$combineUnsafe$$inlined$unsafeFlow$1.this.a(null, this);
        }
    }

    @Override // kotlinx.coroutines.flow.Flow
    public Object a(FlowCollector flowCollector, Continuation continuation) {
        Function0 b2;
        Object d2;
        Flow[] flowArr = this.f19247c;
        b2 = FlowKt__ZipKt.b();
        Intrinsics.i();
        Object a2 = CombineKt.a(flowCollector, flowArr, b2, new FlowKt__ZipKt$combineUnsafe$1$1(this.f19248h, null), continuation);
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        return a2 == d2 ? a2 : Unit.f18288a;
    }
}
