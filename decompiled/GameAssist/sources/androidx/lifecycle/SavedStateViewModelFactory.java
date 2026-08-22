package androidx.lifecycle;

import android.app.Application;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.savedstate.SavedStateRegistry;
import java.lang.reflect.Constructor;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata
/* loaded from: classes.dex */
public final class SavedStateViewModelFactory extends ViewModelProvider.OnRequeryFactory implements ViewModelProvider.Factory {

    /* renamed from: b, reason: collision with root package name */
    private Application f4377b;

    /* renamed from: c, reason: collision with root package name */
    private final ViewModelProvider.Factory f4378c;

    /* renamed from: d, reason: collision with root package name */
    private Bundle f4379d;

    /* renamed from: e, reason: collision with root package name */
    private Lifecycle f4380e;

    /* renamed from: f, reason: collision with root package name */
    private SavedStateRegistry f4381f;

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public ViewModel a(Class modelClass, CreationExtras extras) {
        List list;
        Constructor c2;
        List list2;
        Intrinsics.e(modelClass, "modelClass");
        Intrinsics.e(extras, "extras");
        String str = (String) extras.a(ViewModelProvider.NewInstanceFactory.f4411d);
        if (str == null) {
            throw new IllegalStateException("VIEW_MODEL_KEY must always be provided by ViewModelProvider");
        }
        if (extras.a(SavedStateHandleSupport.f4369a) == null || extras.a(SavedStateHandleSupport.f4370b) == null) {
            if (this.f4380e != null) {
                return d(str, modelClass);
            }
            throw new IllegalStateException("SAVED_STATE_REGISTRY_OWNER_KEY andVIEW_MODEL_STORE_OWNER_KEY must be provided in the creation extras tosuccessfully create a ViewModel.");
        }
        Application application = (Application) extras.a(ViewModelProvider.AndroidViewModelFactory.f4404g);
        boolean isAssignableFrom = AndroidViewModel.class.isAssignableFrom(modelClass);
        if (!isAssignableFrom || application == null) {
            list = SavedStateViewModelFactoryKt.f4383b;
            c2 = SavedStateViewModelFactoryKt.c(modelClass, list);
        } else {
            list2 = SavedStateViewModelFactoryKt.f4382a;
            c2 = SavedStateViewModelFactoryKt.c(modelClass, list2);
        }
        return c2 == null ? this.f4378c.a(modelClass, extras) : (!isAssignableFrom || application == null) ? SavedStateViewModelFactoryKt.d(modelClass, c2, SavedStateHandleSupport.a(extras)) : SavedStateViewModelFactoryKt.d(modelClass, c2, application, SavedStateHandleSupport.a(extras));
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public ViewModel b(Class modelClass) {
        Intrinsics.e(modelClass, "modelClass");
        String canonicalName = modelClass.getCanonicalName();
        if (canonicalName != null) {
            return d(canonicalName, modelClass);
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }

    @Override // androidx.lifecycle.ViewModelProvider.OnRequeryFactory
    public void c(ViewModel viewModel) {
        Intrinsics.e(viewModel, "viewModel");
        if (this.f4380e != null) {
            SavedStateRegistry savedStateRegistry = this.f4381f;
            Intrinsics.b(savedStateRegistry);
            Lifecycle lifecycle = this.f4380e;
            Intrinsics.b(lifecycle);
            LegacySavedStateHandleController.a(viewModel, savedStateRegistry, lifecycle);
        }
    }

    public final ViewModel d(String key, Class modelClass) {
        List list;
        Constructor c2;
        ViewModel d2;
        Application application;
        List list2;
        Intrinsics.e(key, "key");
        Intrinsics.e(modelClass, "modelClass");
        Lifecycle lifecycle = this.f4380e;
        if (lifecycle == null) {
            throw new UnsupportedOperationException("SavedStateViewModelFactory constructed with empty constructor supports only calls to create(modelClass: Class<T>, extras: CreationExtras).");
        }
        boolean isAssignableFrom = AndroidViewModel.class.isAssignableFrom(modelClass);
        if (!isAssignableFrom || this.f4377b == null) {
            list = SavedStateViewModelFactoryKt.f4383b;
            c2 = SavedStateViewModelFactoryKt.c(modelClass, list);
        } else {
            list2 = SavedStateViewModelFactoryKt.f4382a;
            c2 = SavedStateViewModelFactoryKt.c(modelClass, list2);
        }
        if (c2 == null) {
            return this.f4377b != null ? this.f4378c.b(modelClass) : ViewModelProvider.NewInstanceFactory.f4409b.a().b(modelClass);
        }
        SavedStateRegistry savedStateRegistry = this.f4381f;
        Intrinsics.b(savedStateRegistry);
        SavedStateHandleController b2 = LegacySavedStateHandleController.b(savedStateRegistry, lifecycle, key, this.f4379d);
        if (!isAssignableFrom || (application = this.f4377b) == null) {
            d2 = SavedStateViewModelFactoryKt.d(modelClass, c2, b2.e());
        } else {
            Intrinsics.b(application);
            d2 = SavedStateViewModelFactoryKt.d(modelClass, c2, application, b2.e());
        }
        d2.e("androidx.lifecycle.savedstate.vm.tag", b2);
        return d2;
    }
}
