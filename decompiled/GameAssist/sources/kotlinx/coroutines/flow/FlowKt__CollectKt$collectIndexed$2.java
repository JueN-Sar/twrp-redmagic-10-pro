package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function3;

@Metadata
/* loaded from: classes2.dex */
public final class FlowKt__CollectKt$collectIndexed$2 implements FlowCollector<Object> {

    /* renamed from: c, reason: collision with root package name */
    private int f19115c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ Function3 f19116h;

    @Override // kotlinx.coroutines.flow.FlowCollector
    public Object k(Object obj, Continuation continuation) {
        Object d2;
        Function3 function3 = this.f19116h;
        int i2 = this.f19115c;
        this.f19115c = i2 + 1;
        if (i2 < 0) {
            throw new ArithmeticException("Index overflow has happened");
        }
        Object u = function3.u(Boxing.b(i2), obj, continuation);
        d2 = IntrinsicsKt__IntrinsicsKt.d();
        return u == d2 ? u : Unit.f18288a;
    }
}
