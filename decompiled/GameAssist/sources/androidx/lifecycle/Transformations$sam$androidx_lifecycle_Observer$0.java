package androidx.lifecycle;

import kotlin.Function;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionAdapter;
import kotlin.jvm.internal.Intrinsics;

@Metadata
/* loaded from: classes.dex */
final class Transformations$sam$androidx_lifecycle_Observer$0 implements Observer, FunctionAdapter {

    /* renamed from: a, reason: collision with root package name */
    private final /* synthetic */ Function1 f4385a;

    Transformations$sam$androidx_lifecycle_Observer$0(Function1 function) {
        Intrinsics.e(function, "function");
        this.f4385a = function;
    }

    @Override // androidx.lifecycle.Observer
    public final /* synthetic */ void a(Object obj) {
        this.f4385a.c(obj);
    }

    @Override // kotlin.jvm.internal.FunctionAdapter
    public final Function b() {
        return this.f4385a;
    }

    public final boolean equals(Object obj) {
        if ((obj instanceof Observer) && (obj instanceof FunctionAdapter)) {
            return Intrinsics.a(b(), ((FunctionAdapter) obj).b());
        }
        return false;
    }

    public final int hashCode() {
        return b().hashCode();
    }
}
