package androidx.lifecycle;

import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.savedstate.SavedStateRegistry;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata
/* loaded from: classes.dex */
public abstract class AbstractSavedStateViewModelFactory extends ViewModelProvider.OnRequeryFactory implements ViewModelProvider.Factory {

    /* renamed from: e, reason: collision with root package name */
    public static final Companion f4265e = new Companion(null);

    /* renamed from: b, reason: collision with root package name */
    private SavedStateRegistry f4266b;

    /* renamed from: c, reason: collision with root package name */
    private Lifecycle f4267c;

    /* renamed from: d, reason: collision with root package name */
    private Bundle f4268d;

    @Metadata
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private final ViewModel d(String str, Class cls) {
        SavedStateRegistry savedStateRegistry = this.f4266b;
        Intrinsics.b(savedStateRegistry);
        Lifecycle lifecycle = this.f4267c;
        Intrinsics.b(lifecycle);
        SavedStateHandleController b2 = LegacySavedStateHandleController.b(savedStateRegistry, lifecycle, str, this.f4268d);
        ViewModel e2 = e(str, cls, b2.e());
        e2.e("androidx.lifecycle.savedstate.vm.tag", b2);
        return e2;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public ViewModel a(Class modelClass, CreationExtras extras) {
        Intrinsics.e(modelClass, "modelClass");
        Intrinsics.e(extras, "extras");
        String str = (String) extras.a(ViewModelProvider.NewInstanceFactory.f4411d);
        if (str != null) {
            return this.f4266b != null ? d(str, modelClass) : e(str, modelClass, SavedStateHandleSupport.a(extras));
        }
        throw new IllegalStateException("VIEW_MODEL_KEY must always be provided by ViewModelProvider");
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public ViewModel b(Class modelClass) {
        Intrinsics.e(modelClass, "modelClass");
        String canonicalName = modelClass.getCanonicalName();
        if (canonicalName == null) {
            throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
        }
        if (this.f4267c != null) {
            return d(canonicalName, modelClass);
        }
        throw new UnsupportedOperationException("AbstractSavedStateViewModelFactory constructed with empty constructor supports only calls to create(modelClass: Class<T>, extras: CreationExtras).");
    }

    @Override // androidx.lifecycle.ViewModelProvider.OnRequeryFactory
    public void c(ViewModel viewModel) {
        Intrinsics.e(viewModel, "viewModel");
        SavedStateRegistry savedStateRegistry = this.f4266b;
        if (savedStateRegistry != null) {
            Intrinsics.b(savedStateRegistry);
            Lifecycle lifecycle = this.f4267c;
            Intrinsics.b(lifecycle);
            LegacySavedStateHandleController.a(viewModel, savedStateRegistry, lifecycle);
        }
    }

    protected abstract ViewModel e(String str, Class cls, SavedStateHandle savedStateHandle);
}
