package androidx.savedstate;

import android.os.Bundle;
import androidx.lifecycle.Lifecycle;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata
/* loaded from: classes.dex */
public final class SavedStateRegistryController {

    /* renamed from: d, reason: collision with root package name */
    public static final Companion f5343d = new Companion(null);

    /* renamed from: a, reason: collision with root package name */
    private final SavedStateRegistryOwner f5344a;

    /* renamed from: b, reason: collision with root package name */
    private final SavedStateRegistry f5345b;

    /* renamed from: c, reason: collision with root package name */
    private boolean f5346c;

    @Metadata
    public static final class Companion {
        private Companion() {
        }

        public final SavedStateRegistryController a(SavedStateRegistryOwner owner) {
            Intrinsics.e(owner, "owner");
            return new SavedStateRegistryController(owner, null);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public /* synthetic */ SavedStateRegistryController(SavedStateRegistryOwner savedStateRegistryOwner, DefaultConstructorMarker defaultConstructorMarker) {
        this(savedStateRegistryOwner);
    }

    public static final SavedStateRegistryController a(SavedStateRegistryOwner savedStateRegistryOwner) {
        return f5343d.a(savedStateRegistryOwner);
    }

    public final SavedStateRegistry b() {
        return this.f5345b;
    }

    public final void c() {
        Lifecycle a2 = this.f5344a.a();
        if (a2.b() != Lifecycle.State.INITIALIZED) {
            throw new IllegalStateException("Restarter must be created only during owner's initialization stage".toString());
        }
        a2.a(new Recreator(this.f5344a));
        this.f5345b.e(a2);
        this.f5346c = true;
    }

    public final void d(Bundle bundle) {
        if (!this.f5346c) {
            c();
        }
        Lifecycle a2 = this.f5344a.a();
        if (!a2.b().d(Lifecycle.State.STARTED)) {
            this.f5345b.f(bundle);
            return;
        }
        throw new IllegalStateException(("performRestore cannot be called when owner is " + a2.b()).toString());
    }

    public final void e(Bundle outBundle) {
        Intrinsics.e(outBundle, "outBundle");
        this.f5345b.g(outBundle);
    }

    private SavedStateRegistryController(SavedStateRegistryOwner savedStateRegistryOwner) {
        this.f5344a = savedStateRegistryOwner;
        this.f5345b = new SavedStateRegistry();
    }
}
