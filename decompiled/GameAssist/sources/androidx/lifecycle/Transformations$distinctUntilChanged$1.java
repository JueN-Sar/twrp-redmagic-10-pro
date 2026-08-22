package androidx.lifecycle;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;

@Metadata
/* loaded from: classes.dex */
final class Transformations$distinctUntilChanged$1 extends Lambda implements Function1<Object, Unit> {
    final /* synthetic */ Ref.BooleanRef $firstTime;
    final /* synthetic */ MediatorLiveData<Object> $outputLiveData;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    Transformations$distinctUntilChanged$1(MediatorLiveData<Object> mediatorLiveData, Ref.BooleanRef booleanRef) {
        super(1);
        this.$outputLiveData = mediatorLiveData;
        this.$firstTime = booleanRef;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object c(Object obj) {
        d(obj);
        return Unit.f18288a;
    }

    public final void d(Object obj) {
        Object f2 = this.$outputLiveData.f();
        if (this.$firstTime.element || ((f2 == null && obj != null) || !(f2 == null || Intrinsics.a(f2, obj)))) {
            this.$firstTime.element = false;
            this.$outputLiveData.o(obj);
        }
    }
}
