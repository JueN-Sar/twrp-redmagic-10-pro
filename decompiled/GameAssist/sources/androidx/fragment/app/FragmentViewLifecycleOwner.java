package androidx.fragment.app;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.SavedStateHandleSupport;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.lifecycle.viewmodel.MutableCreationExtras;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryController;
import androidx.savedstate.SavedStateRegistryOwner;

/* loaded from: classes.dex */
class FragmentViewLifecycleOwner implements HasDefaultViewModelProviderFactory, SavedStateRegistryOwner, ViewModelStoreOwner {

    /* renamed from: c, reason: collision with root package name */
    private final Fragment f4202c;

    /* renamed from: h, reason: collision with root package name */
    private final ViewModelStore f4203h;

    /* renamed from: i, reason: collision with root package name */
    private LifecycleRegistry f4204i = null;

    /* renamed from: j, reason: collision with root package name */
    private SavedStateRegistryController f4205j = null;

    FragmentViewLifecycleOwner(Fragment fragment, ViewModelStore viewModelStore) {
        this.f4202c = fragment;
        this.f4203h = viewModelStore;
    }

    @Override // androidx.lifecycle.LifecycleOwner
    public Lifecycle a() {
        c();
        return this.f4204i;
    }

    void b(Lifecycle.Event event) {
        this.f4204i.h(event);
    }

    void c() {
        if (this.f4204i == null) {
            this.f4204i = new LifecycleRegistry(this);
            SavedStateRegistryController a2 = SavedStateRegistryController.a(this);
            this.f4205j = a2;
            a2.c();
            SavedStateHandleSupport.c(this);
        }
    }

    boolean d() {
        return this.f4204i != null;
    }

    void e(Bundle bundle) {
        this.f4205j.d(bundle);
    }

    void f(Bundle bundle) {
        this.f4205j.e(bundle);
    }

    void g(Lifecycle.State state) {
        this.f4204i.m(state);
    }

    @Override // androidx.lifecycle.ViewModelStoreOwner
    public ViewModelStore h() {
        c();
        return this.f4203h;
    }

    @Override // androidx.savedstate.SavedStateRegistryOwner
    public SavedStateRegistry i() {
        c();
        return this.f4205j.b();
    }

    @Override // androidx.lifecycle.HasDefaultViewModelProviderFactory
    public CreationExtras s() {
        Application application;
        Context applicationContext = this.f4202c.D1().getApplicationContext();
        while (true) {
            if (!(applicationContext instanceof ContextWrapper)) {
                application = null;
                break;
            }
            if (applicationContext instanceof Application) {
                application = (Application) applicationContext;
                break;
            }
            applicationContext = ((ContextWrapper) applicationContext).getBaseContext();
        }
        MutableCreationExtras mutableCreationExtras = new MutableCreationExtras();
        if (application != null) {
            mutableCreationExtras.c(ViewModelProvider.AndroidViewModelFactory.f4404g, application);
        }
        mutableCreationExtras.c(SavedStateHandleSupport.f4369a, this);
        mutableCreationExtras.c(SavedStateHandleSupport.f4370b, this);
        if (this.f4202c.x() != null) {
            mutableCreationExtras.c(SavedStateHandleSupport.f4371c, this.f4202c.x());
        }
        return mutableCreationExtras;
    }
}
