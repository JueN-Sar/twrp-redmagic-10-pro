package androidx.lifecycle;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata
/* loaded from: classes.dex */
public final class Transformations$switchMap$1 implements Observer<Object> {

    /* renamed from: a, reason: collision with root package name */
    private LiveData f4386a;

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ Function1 f4387b;

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ MediatorLiveData f4388c;

    @Override // androidx.lifecycle.Observer
    public void a(Object obj) {
        LiveData liveData = (LiveData) this.f4387b.c(obj);
        LiveData liveData2 = this.f4386a;
        if (liveData2 == liveData) {
            return;
        }
        if (liveData2 != null) {
            MediatorLiveData mediatorLiveData = this.f4388c;
            Intrinsics.b(liveData2);
            mediatorLiveData.q(liveData2);
        }
        this.f4386a = liveData;
        if (liveData != null) {
            MediatorLiveData mediatorLiveData2 = this.f4388c;
            Intrinsics.b(liveData);
            final MediatorLiveData mediatorLiveData3 = this.f4388c;
            mediatorLiveData2.p(liveData, new Transformations$sam$androidx_lifecycle_Observer$0(new Function1<Object, Unit>() { // from class: androidx.lifecycle.Transformations$switchMap$1$onChanged$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object c(Object obj2) {
                    d(obj2);
                    return Unit.f18288a;
                }

                public final void d(Object obj2) {
                    mediatorLiveData3.o(obj2);
                }
            }));
        }
    }
}
