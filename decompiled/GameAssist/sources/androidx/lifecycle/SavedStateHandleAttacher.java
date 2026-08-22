package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata
/* loaded from: classes.dex */
public final class SavedStateHandleAttacher implements LifecycleEventObserver {

    /* renamed from: c, reason: collision with root package name */
    private final SavedStateHandlesProvider f4365c;

    public SavedStateHandleAttacher(SavedStateHandlesProvider provider) {
        Intrinsics.e(provider, "provider");
        this.f4365c = provider;
    }

    @Override // androidx.lifecycle.LifecycleEventObserver
    public void c(LifecycleOwner source, Lifecycle.Event event) {
        Intrinsics.e(source, "source");
        Intrinsics.e(event, "event");
        if (event == Lifecycle.Event.ON_CREATE) {
            source.a().c(this);
            this.f4365c.d();
        } else {
            throw new IllegalStateException(("Next event must be ON_CREATE, it was " + event).toString());
        }
    }
}
