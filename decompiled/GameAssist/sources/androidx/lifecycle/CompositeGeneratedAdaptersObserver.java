package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata
/* loaded from: classes.dex */
public final class CompositeGeneratedAdaptersObserver implements LifecycleEventObserver {

    /* renamed from: c, reason: collision with root package name */
    private final GeneratedAdapter[] f4276c;

    public CompositeGeneratedAdaptersObserver(GeneratedAdapter[] generatedAdapters) {
        Intrinsics.e(generatedAdapters, "generatedAdapters");
        this.f4276c = generatedAdapters;
    }

    @Override // androidx.lifecycle.LifecycleEventObserver
    public void c(LifecycleOwner source, Lifecycle.Event event) {
        Intrinsics.e(source, "source");
        Intrinsics.e(event, "event");
        MethodCallsLogger methodCallsLogger = new MethodCallsLogger();
        for (GeneratedAdapter generatedAdapter : this.f4276c) {
            generatedAdapter.a(source, event, false, methodCallsLogger);
        }
        for (GeneratedAdapter generatedAdapter2 : this.f4276c) {
            generatedAdapter2.a(source, event, true, methodCallsLogger);
        }
    }
}
