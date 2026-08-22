package kotlinx.coroutines.selects;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata
/* loaded from: classes2.dex */
final class UnbiasedSelectBuilderImpl$invoke$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ Function1<Continuation<Object>, Object> $block;
    final /* synthetic */ SelectClause0 $this_invoke;
    final /* synthetic */ UnbiasedSelectBuilderImpl<Object> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    UnbiasedSelectBuilderImpl$invoke$1(SelectClause0 selectClause0, UnbiasedSelectBuilderImpl<Object> unbiasedSelectBuilderImpl, Function1<? super Continuation<Object>, ? extends Object> function1) {
        super(0);
        this.$this_invoke = selectClause0;
        this.this$0 = unbiasedSelectBuilderImpl;
        this.$block = function1;
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Object a() {
        d();
        return Unit.f18288a;
    }

    public final void d() {
        this.$this_invoke.k(this.this$0.a(), this.$block);
    }
}
