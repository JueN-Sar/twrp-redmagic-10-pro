package androidx.lifecycle;

import androidx.arch.core.util.Function;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata
/* loaded from: classes.dex */
final class Transformations$map$2 extends Lambda implements Function1 {
    final /* synthetic */ Function $mapFunction;
    final /* synthetic */ MediatorLiveData $result;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    Transformations$map$2(MediatorLiveData mediatorLiveData, Function function) {
        super(1);
        this.$result = mediatorLiveData;
        this.$mapFunction = function;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object c(Object obj) {
        d(obj);
        return Unit.f18288a;
    }

    public final void d(Object obj) {
        this.$result.o(this.$mapFunction.apply(obj));
    }
}
