package androidx.lifecycle;

import androidx.arch.core.util.Function;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata
/* loaded from: classes.dex */
public final class Transformations$switchMap$2 implements Observer {

    /* renamed from: a, reason: collision with root package name */
    private LiveData f4389a;

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ Function f4390b;

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ MediatorLiveData f4391c;

    @Override // androidx.lifecycle.Observer
    public void a(Object obj) {
        LiveData liveData = (LiveData) this.f4390b.apply(obj);
        LiveData liveData2 = this.f4389a;
        if (liveData2 == liveData) {
            return;
        }
        if (liveData2 != null) {
            MediatorLiveData mediatorLiveData = this.f4391c;
            Intrinsics.b(liveData2);
            mediatorLiveData.q(liveData2);
        }
        this.f4389a = liveData;
        if (liveData != null) {
            MediatorLiveData mediatorLiveData2 = this.f4391c;
            Intrinsics.b(liveData);
            final MediatorLiveData mediatorLiveData3 = this.f4391c;
            mediatorLiveData2.p(liveData, new Transformations$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: androidx.lifecycle.Transformations$switchMap$2$onChanged$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object c(Object obj2) {
                    d(obj2);
                    return Unit.f18288a;
                }

                public final void d(Object obj2) {
                    MediatorLiveData.this.o(obj2);
                }
            }));
        }
    }
}
