package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import androidx.savedstate.SavedStateRegistry;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes.dex */
public final class SavedStateHandleController implements LifecycleEventObserver {

    /* renamed from: c, reason: collision with root package name */
    private final String f4366c;

    /* renamed from: h, reason: collision with root package name */
    private final SavedStateHandle f4367h;

    /* renamed from: i, reason: collision with root package name */
    private boolean f4368i;

    public SavedStateHandleController(String key, SavedStateHandle handle) {
        Intrinsics.e(key, "key");
        Intrinsics.e(handle, "handle");
        this.f4366c = key;
        this.f4367h = handle;
    }

    public final void b(SavedStateRegistry registry, Lifecycle lifecycle) {
        Intrinsics.e(registry, "registry");
        Intrinsics.e(lifecycle, "lifecycle");
        if (!(!this.f4368i)) {
            throw new IllegalStateException("Already attached to lifecycleOwner".toString());
        }
        this.f4368i = true;
        lifecycle.a(this);
        registry.h(this.f4366c, this.f4367h.e());
    }

    @Override // androidx.lifecycle.LifecycleEventObserver
    public void c(LifecycleOwner source, Lifecycle.Event event) {
        Intrinsics.e(source, "source");
        Intrinsics.e(event, "event");
        if (event == Lifecycle.Event.ON_DESTROY) {
            this.f4368i = false;
            source.a().c(this);
        }
    }

    public final SavedStateHandle e() {
        return this.f4367h;
    }

    public final boolean f() {
        return this.f4368i;
    }
}
