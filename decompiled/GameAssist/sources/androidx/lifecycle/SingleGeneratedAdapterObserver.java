package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata
/* loaded from: classes.dex */
public final class SingleGeneratedAdapterObserver implements LifecycleEventObserver {

    /* renamed from: c, reason: collision with root package name */
    private final GeneratedAdapter f4384c;

    public SingleGeneratedAdapterObserver(GeneratedAdapter generatedAdapter) {
        Intrinsics.e(generatedAdapter, "generatedAdapter");
        this.f4384c = generatedAdapter;
    }

    @Override // androidx.lifecycle.LifecycleEventObserver
    public void c(LifecycleOwner source, Lifecycle.Event event) {
        Intrinsics.e(source, "source");
        Intrinsics.e(event, "event");
        this.f4384c.a(source, event, false, null);
        this.f4384c.a(source, event, true, null);
    }
}
