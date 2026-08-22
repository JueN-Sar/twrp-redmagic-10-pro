package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.JobKt__JobKt;

@Metadata
/* loaded from: classes.dex */
public final class LifecycleCoroutineScopeImpl extends LifecycleCoroutineScope implements LifecycleEventObserver {

    /* renamed from: c, reason: collision with root package name */
    private final Lifecycle f4297c;

    /* renamed from: h, reason: collision with root package name */
    private final CoroutineContext f4298h;

    @Override // kotlinx.coroutines.CoroutineScope
    public CoroutineContext K() {
        return this.f4298h;
    }

    @Override // androidx.lifecycle.LifecycleCoroutineScope
    public Lifecycle b() {
        return this.f4297c;
    }

    @Override // androidx.lifecycle.LifecycleEventObserver
    public void c(LifecycleOwner source, Lifecycle.Event event) {
        Intrinsics.e(source, "source");
        Intrinsics.e(event, "event");
        if (b().b().compareTo(Lifecycle.State.DESTROYED) <= 0) {
            b().c(this);
            JobKt__JobKt.d(K(), null, 1, null);
        }
    }
}
