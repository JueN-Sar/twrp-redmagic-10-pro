package androidx.lifecycle;

import androidx.annotation.MainThread;
import androidx.lifecycle.Lifecycle;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.Job;

@Metadata
@MainThread
@SourceDebugExtension
/* loaded from: classes.dex */
public final class LifecycleController {

    /* renamed from: a, reason: collision with root package name */
    private final Lifecycle f4293a;

    /* renamed from: b, reason: collision with root package name */
    private final Lifecycle.State f4294b;

    /* renamed from: c, reason: collision with root package name */
    private final DispatchQueue f4295c;

    /* renamed from: d, reason: collision with root package name */
    private final LifecycleEventObserver f4296d;

    public LifecycleController(Lifecycle lifecycle, Lifecycle.State minState, DispatchQueue dispatchQueue, final Job parentJob) {
        Intrinsics.e(lifecycle, "lifecycle");
        Intrinsics.e(minState, "minState");
        Intrinsics.e(dispatchQueue, "dispatchQueue");
        Intrinsics.e(parentJob, "parentJob");
        this.f4293a = lifecycle;
        this.f4294b = minState;
        this.f4295c = dispatchQueue;
        LifecycleEventObserver lifecycleEventObserver = new LifecycleEventObserver() { // from class: androidx.lifecycle.b
            @Override // androidx.lifecycle.LifecycleEventObserver
            public final void c(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
                LifecycleController.c(LifecycleController.this, parentJob, lifecycleOwner, event);
            }
        };
        this.f4296d = lifecycleEventObserver;
        if (lifecycle.b() != Lifecycle.State.DESTROYED) {
            lifecycle.a(lifecycleEventObserver);
        } else {
            Job.DefaultImpls.a(parentJob, null, 1, null);
            b();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void c(LifecycleController this$0, Job parentJob, LifecycleOwner source, Lifecycle.Event event) {
        Intrinsics.e(this$0, "this$0");
        Intrinsics.e(parentJob, "$parentJob");
        Intrinsics.e(source, "source");
        Intrinsics.e(event, "<anonymous parameter 1>");
        if (source.a().b() == Lifecycle.State.DESTROYED) {
            Job.DefaultImpls.a(parentJob, null, 1, null);
            this$0.b();
        } else if (source.a().b().compareTo(this$0.f4294b) < 0) {
            this$0.f4295c.h();
        } else {
            this$0.f4295c.i();
        }
    }

    public final void b() {
        this.f4293a.c(this.f4296d);
        this.f4295c.g();
    }
}
