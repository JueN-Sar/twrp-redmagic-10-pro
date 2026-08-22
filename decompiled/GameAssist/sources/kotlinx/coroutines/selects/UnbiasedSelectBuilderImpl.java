package kotlinx.coroutines.selects;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

@Metadata
@PublishedApi
/* loaded from: classes2.dex */
public final class UnbiasedSelectBuilderImpl<R> implements SelectBuilder<R> {

    /* renamed from: c, reason: collision with root package name */
    private final SelectBuilderImpl f19494c;

    /* renamed from: h, reason: collision with root package name */
    private final ArrayList f19495h;

    @Override // kotlinx.coroutines.selects.SelectBuilder
    public void G(final SelectClause1 selectClause1, final Function2 function2) {
        this.f19495h.add(new Function0<Unit>() { // from class: kotlinx.coroutines.selects.UnbiasedSelectBuilderImpl$invoke$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Object a() {
                d();
                return Unit.f18288a;
            }

            public final void d() {
                selectClause1.b(this.a(), function2);
            }
        });
    }

    public final SelectBuilderImpl a() {
        return this.f19494c;
    }

    @Override // kotlinx.coroutines.selects.SelectBuilder
    public void o(final long j2, final Function1 function1) {
        this.f19495h.add(new Function0<Unit>(this) { // from class: kotlinx.coroutines.selects.UnbiasedSelectBuilderImpl$onTimeout$1
            final /* synthetic */ UnbiasedSelectBuilderImpl<R> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(0);
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Object a() {
                d();
                return Unit.f18288a;
            }

            public final void d() {
                this.this$0.a().o(j2, function1);
            }
        });
    }
}
