package kotlinx.coroutines.selects;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

@Metadata
/* loaded from: classes2.dex */
final class UnbiasedSelectBuilderImpl$invoke$3 extends Lambda implements Function0<Unit> {
    final /* synthetic */ Function2<Object, Continuation<Object>, Object> $block;
    final /* synthetic */ Object $param;
    final /* synthetic */ SelectClause2<Object, Object> $this_invoke;
    final /* synthetic */ UnbiasedSelectBuilderImpl<Object> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    UnbiasedSelectBuilderImpl$invoke$3(SelectClause2<Object, Object> selectClause2, UnbiasedSelectBuilderImpl<Object> unbiasedSelectBuilderImpl, Object obj, Function2<Object, ? super Continuation<Object>, ? extends Object> function2) {
        super(0);
        this.$this_invoke = selectClause2;
        this.this$0 = unbiasedSelectBuilderImpl;
        this.$param = obj;
        this.$block = function2;
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Object a() {
        d();
        return Unit.f18288a;
    }

    public final void d() {
        this.$this_invoke.v(this.this$0.a(), this.$param, this.$block);
    }
}
