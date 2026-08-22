package androidx.lifecycle;

import android.os.Bundle;
import androidx.lifecycle.LegacySavedStateHandleController;
import androidx.lifecycle.Lifecycle;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryOwner;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata
/* loaded from: classes.dex */
public final class LegacySavedStateHandleController {

    /* renamed from: a, reason: collision with root package name */
    public static final LegacySavedStateHandleController f4287a = new LegacySavedStateHandleController();

    @Metadata
    public static final class OnRecreation implements SavedStateRegistry.AutoRecreated {
        @Override // androidx.savedstate.SavedStateRegistry.AutoRecreated
        public void a(SavedStateRegistryOwner owner) {
            Intrinsics.e(owner, "owner");
            if (!(owner instanceof ViewModelStoreOwner)) {
                throw new IllegalStateException("Internal error: OnRecreation should be registered only on components that implement ViewModelStoreOwner".toString());
            }
            ViewModelStore h2 = ((ViewModelStoreOwner) owner).h();
            SavedStateRegistry i2 = owner.i();
            Iterator it = h2.c().iterator();
            while (it.hasNext()) {
                ViewModel b2 = h2.b((String) it.next());
                Intrinsics.b(b2);
                LegacySavedStateHandleController.a(b2, i2, owner.a());
            }
            if (!h2.c().isEmpty()) {
                i2.i(OnRecreation.class);
            }
        }
    }

    private LegacySavedStateHandleController() {
    }

    public static final void a(ViewModel viewModel, SavedStateRegistry registry, Lifecycle lifecycle) {
        Intrinsics.e(viewModel, "viewModel");
        Intrinsics.e(registry, "registry");
        Intrinsics.e(lifecycle, "lifecycle");
        SavedStateHandleController savedStateHandleController = (SavedStateHandleController) viewModel.c("androidx.lifecycle.savedstate.vm.tag");
        if (savedStateHandleController == null || savedStateHandleController.f()) {
            return;
        }
        savedStateHandleController.b(registry, lifecycle);
        f4287a.c(registry, lifecycle);
    }

    public static final SavedStateHandleController b(SavedStateRegistry registry, Lifecycle lifecycle, String str, Bundle bundle) {
        Intrinsics.e(registry, "registry");
        Intrinsics.e(lifecycle, "lifecycle");
        Intrinsics.b(str);
        SavedStateHandleController savedStateHandleController = new SavedStateHandleController(str, SavedStateHandle.f4356f.a(registry.b(str), bundle));
        savedStateHandleController.b(registry, lifecycle);
        f4287a.c(registry, lifecycle);
        return savedStateHandleController;
    }

    private final void c(final SavedStateRegistry savedStateRegistry, final Lifecycle lifecycle) {
        Lifecycle.State b2 = lifecycle.b();
        if (b2 == Lifecycle.State.INITIALIZED || b2.d(Lifecycle.State.STARTED)) {
            savedStateRegistry.i(OnRecreation.class);
        } else {
            lifecycle.a(new LifecycleEventObserver() { // from class: androidx.lifecycle.LegacySavedStateHandleController$tryToAddRecreator$1
                @Override // androidx.lifecycle.LifecycleEventObserver
                public void c(LifecycleOwner source, Lifecycle.Event event) {
                    Intrinsics.e(source, "source");
                    Intrinsics.e(event, "event");
                    if (event == Lifecycle.Event.ON_START) {
                        Lifecycle.this.c(this);
                        savedStateRegistry.i(LegacySavedStateHandleController.OnRecreation.class);
                    }
                }
            });
        }
    }
}
