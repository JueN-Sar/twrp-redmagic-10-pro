package androidx.lifecycle.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes.dex */
public final class InitializerViewModelFactory implements ViewModelProvider.Factory {

    /* renamed from: b, reason: collision with root package name */
    private final ViewModelInitializer[] f4422b;

    public InitializerViewModelFactory(ViewModelInitializer... initializers) {
        Intrinsics.e(initializers, "initializers");
        this.f4422b = initializers;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public ViewModel a(Class modelClass, CreationExtras extras) {
        Intrinsics.e(modelClass, "modelClass");
        Intrinsics.e(extras, "extras");
        ViewModel viewModel = null;
        for (ViewModelInitializer viewModelInitializer : this.f4422b) {
            if (Intrinsics.a(viewModelInitializer.a(), modelClass)) {
                Object c2 = viewModelInitializer.b().c(extras);
                viewModel = c2 instanceof ViewModel ? (ViewModel) c2 : null;
            }
        }
        if (viewModel != null) {
            return viewModel;
        }
        throw new IllegalArgumentException("No initializer set for given class " + modelClass.getName());
    }
}
