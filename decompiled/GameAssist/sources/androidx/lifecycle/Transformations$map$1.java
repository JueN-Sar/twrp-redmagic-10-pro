package androidx.lifecycle;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata
/* loaded from: classes.dex */
final class Transformations$map$1 extends Lambda implements Function1<Object, Unit> {
    final /* synthetic */ MediatorLiveData<Object> $result;
    final /* synthetic */ Function1<Object, Object> $transform;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    Transformations$map$1(MediatorLiveData<Object> mediatorLiveData, Function1<Object, Object> function1) {
        super(1);
        this.$result = mediatorLiveData;
        this.$transform = function1;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object c(Object obj) {
        d(obj);
        return Unit.f18288a;
    }

    public final void d(Object obj) {
        this.$result.o(this.$transform.c(obj));
    }
}
