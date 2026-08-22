package androidx.lifecycle;

import android.os.Bundle;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.lifecycle.viewmodel.InitializerViewModelFactoryBuilder;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryOwner;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@JvmName
@SourceDebugExtension
/* loaded from: classes.dex */
public final class SavedStateHandleSupport {

    /* renamed from: a, reason: collision with root package name */
    public static final CreationExtras.Key f4369a = new CreationExtras.Key<SavedStateRegistryOwner>() { // from class: androidx.lifecycle.SavedStateHandleSupport$SAVED_STATE_REGISTRY_OWNER_KEY$1
    };

    /* renamed from: b, reason: collision with root package name */
    public static final CreationExtras.Key f4370b = new CreationExtras.Key<ViewModelStoreOwner>() { // from class: androidx.lifecycle.SavedStateHandleSupport$VIEW_MODEL_STORE_OWNER_KEY$1
    };

    /* renamed from: c, reason: collision with root package name */
    public static final CreationExtras.Key f4371c = new CreationExtras.Key<Bundle>() { // from class: androidx.lifecycle.SavedStateHandleSupport$DEFAULT_ARGS_KEY$1
    };

    public static final SavedStateHandle a(CreationExtras creationExtras) {
        Intrinsics.e(creationExtras, "<this>");
        SavedStateRegistryOwner savedStateRegistryOwner = (SavedStateRegistryOwner) creationExtras.a(f4369a);
        if (savedStateRegistryOwner == null) {
            throw new IllegalArgumentException("CreationExtras must have a value by `SAVED_STATE_REGISTRY_OWNER_KEY`");
        }
        ViewModelStoreOwner viewModelStoreOwner = (ViewModelStoreOwner) creationExtras.a(f4370b);
        if (viewModelStoreOwner == null) {
            throw new IllegalArgumentException("CreationExtras must have a value by `VIEW_MODEL_STORE_OWNER_KEY`");
        }
        Bundle bundle = (Bundle) creationExtras.a(f4371c);
        String str = (String) creationExtras.a(ViewModelProvider.NewInstanceFactory.f4411d);
        if (str != null) {
            return b(savedStateRegistryOwner, viewModelStoreOwner, str, bundle);
        }
        throw new IllegalArgumentException("CreationExtras must have a value by `VIEW_MODEL_KEY`");
    }

    private static final SavedStateHandle b(SavedStateRegistryOwner savedStateRegistryOwner, ViewModelStoreOwner viewModelStoreOwner, String str, Bundle bundle) {
        SavedStateHandlesProvider d2 = d(savedStateRegistryOwner);
        SavedStateHandlesVM e2 = e(viewModelStoreOwner);
        SavedStateHandle savedStateHandle = (SavedStateHandle) e2.f().get(str);
        if (savedStateHandle != null) {
            return savedStateHandle;
        }
        SavedStateHandle a2 = SavedStateHandle.f4356f.a(d2.b(str), bundle);
        e2.f().put(str, a2);
        return a2;
    }

    public static final void c(SavedStateRegistryOwner savedStateRegistryOwner) {
        Intrinsics.e(savedStateRegistryOwner, "<this>");
        Lifecycle.State b2 = savedStateRegistryOwner.a().b();
        if (b2 != Lifecycle.State.INITIALIZED && b2 != Lifecycle.State.CREATED) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (savedStateRegistryOwner.i().c("androidx.lifecycle.internal.SavedStateHandlesProvider") == null) {
            SavedStateHandlesProvider savedStateHandlesProvider = new SavedStateHandlesProvider(savedStateRegistryOwner.i(), (ViewModelStoreOwner) savedStateRegistryOwner);
            savedStateRegistryOwner.i().h("androidx.lifecycle.internal.SavedStateHandlesProvider", savedStateHandlesProvider);
            savedStateRegistryOwner.a().a(new SavedStateHandleAttacher(savedStateHandlesProvider));
        }
    }

    public static final SavedStateHandlesProvider d(SavedStateRegistryOwner savedStateRegistryOwner) {
        Intrinsics.e(savedStateRegistryOwner, "<this>");
        SavedStateRegistry.SavedStateProvider c2 = savedStateRegistryOwner.i().c("androidx.lifecycle.internal.SavedStateHandlesProvider");
        SavedStateHandlesProvider savedStateHandlesProvider = c2 instanceof SavedStateHandlesProvider ? (SavedStateHandlesProvider) c2 : null;
        if (savedStateHandlesProvider != null) {
            return savedStateHandlesProvider;
        }
        throw new IllegalStateException("enableSavedStateHandles() wasn't called prior to createSavedStateHandle() call");
    }

    public static final SavedStateHandlesVM e(ViewModelStoreOwner viewModelStoreOwner) {
        Intrinsics.e(viewModelStoreOwner, "<this>");
        InitializerViewModelFactoryBuilder initializerViewModelFactoryBuilder = new InitializerViewModelFactoryBuilder();
        initializerViewModelFactoryBuilder.a(Reflection.b(SavedStateHandlesVM.class), new Function1<CreationExtras, SavedStateHandlesVM>() { // from class: androidx.lifecycle.SavedStateHandleSupport$savedStateHandlesVM$1$1
            @Override // kotlin.jvm.functions.Function1
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public final SavedStateHandlesVM c(CreationExtras initializer) {
                Intrinsics.e(initializer, "$this$initializer");
                return new SavedStateHandlesVM();
            }
        });
        return (SavedStateHandlesVM) new ViewModelProvider(viewModelStoreOwner, initializerViewModelFactoryBuilder.b()).b("androidx.lifecycle.internal.SavedStateHandlesVM", SavedStateHandlesVM.class);
    }
}
