package androidx.lifecycle;

import android.app.Application;
import androidx.annotation.RestrictTo;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.lifecycle.viewmodel.MutableCreationExtras;
import java.lang.reflect.InvocationTargetException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes.dex */
public class ViewModelProvider {

    /* renamed from: a, reason: collision with root package name */
    private final ViewModelStore f4400a;

    /* renamed from: b, reason: collision with root package name */
    private final Factory f4401b;

    /* renamed from: c, reason: collision with root package name */
    private final CreationExtras f4402c;

    @Metadata
    public static class AndroidViewModelFactory extends NewInstanceFactory {

        /* renamed from: f, reason: collision with root package name */
        public static final Companion f4403f = new Companion(null);

        /* renamed from: g, reason: collision with root package name */
        public static final CreationExtras.Key f4404g = Companion.ApplicationKeyImpl.f4406a;

        /* renamed from: e, reason: collision with root package name */
        private final Application f4405e;

        @Metadata
        public static final class Companion {

            @Metadata
            private static final class ApplicationKeyImpl implements CreationExtras.Key<Application> {

                /* renamed from: a, reason: collision with root package name */
                public static final ApplicationKeyImpl f4406a = new ApplicationKeyImpl();

                private ApplicationKeyImpl() {
                }
            }

            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }
        }

        private final ViewModel e(Class cls, Application application) {
            if (!AndroidViewModel.class.isAssignableFrom(cls)) {
                return super.b(cls);
            }
            try {
                ViewModel viewModel = (ViewModel) cls.getConstructor(Application.class).newInstance(application);
                Intrinsics.d(viewModel, "{\n                try {\n…          }\n            }");
                return viewModel;
            } catch (IllegalAccessException e2) {
                throw new RuntimeException("Cannot create an instance of " + cls, e2);
            } catch (InstantiationException e3) {
                throw new RuntimeException("Cannot create an instance of " + cls, e3);
            } catch (NoSuchMethodException e4) {
                throw new RuntimeException("Cannot create an instance of " + cls, e4);
            } catch (InvocationTargetException e5) {
                throw new RuntimeException("Cannot create an instance of " + cls, e5);
            }
        }

        @Override // androidx.lifecycle.ViewModelProvider.Factory
        public ViewModel a(Class modelClass, CreationExtras extras) {
            Intrinsics.e(modelClass, "modelClass");
            Intrinsics.e(extras, "extras");
            if (this.f4405e != null) {
                return b(modelClass);
            }
            Application application = (Application) extras.a(f4404g);
            if (application != null) {
                return e(modelClass, application);
            }
            if (AndroidViewModel.class.isAssignableFrom(modelClass)) {
                throw new IllegalArgumentException("CreationExtras must have an application by `APPLICATION_KEY`");
            }
            return super.b(modelClass);
        }

        @Override // androidx.lifecycle.ViewModelProvider.NewInstanceFactory, androidx.lifecycle.ViewModelProvider.Factory
        public ViewModel b(Class modelClass) {
            Intrinsics.e(modelClass, "modelClass");
            Application application = this.f4405e;
            if (application != null) {
                return e(modelClass, application);
            }
            throw new UnsupportedOperationException("AndroidViewModelFactory constructed with empty constructor works only with create(modelClass: Class<T>, extras: CreationExtras).");
        }
    }

    @Metadata
    public interface Factory {

        /* renamed from: a, reason: collision with root package name */
        public static final Companion f4407a = Companion.f4408a;

        @Metadata
        public static final class Companion {

            /* renamed from: a, reason: collision with root package name */
            static final /* synthetic */ Companion f4408a = new Companion();

            private Companion() {
            }
        }

        default ViewModel a(Class modelClass, CreationExtras extras) {
            Intrinsics.e(modelClass, "modelClass");
            Intrinsics.e(extras, "extras");
            return b(modelClass);
        }

        default ViewModel b(Class modelClass) {
            Intrinsics.e(modelClass, "modelClass");
            throw new UnsupportedOperationException("Factory.create(String) is unsupported.  This Factory requires `CreationExtras` to be passed into `create` method.");
        }
    }

    @Metadata
    public static class NewInstanceFactory implements Factory {

        /* renamed from: c, reason: collision with root package name */
        private static NewInstanceFactory f4410c;

        /* renamed from: b, reason: collision with root package name */
        public static final Companion f4409b = new Companion(null);

        /* renamed from: d, reason: collision with root package name */
        public static final CreationExtras.Key f4411d = Companion.ViewModelKeyImpl.f4412a;

        @Metadata
        public static final class Companion {

            @Metadata
            private static final class ViewModelKeyImpl implements CreationExtras.Key<String> {

                /* renamed from: a, reason: collision with root package name */
                public static final ViewModelKeyImpl f4412a = new ViewModelKeyImpl();

                private ViewModelKeyImpl() {
                }
            }

            private Companion() {
            }

            public final NewInstanceFactory a() {
                if (NewInstanceFactory.f4410c == null) {
                    NewInstanceFactory.f4410c = new NewInstanceFactory();
                }
                NewInstanceFactory newInstanceFactory = NewInstanceFactory.f4410c;
                Intrinsics.b(newInstanceFactory);
                return newInstanceFactory;
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }
        }

        @Override // androidx.lifecycle.ViewModelProvider.Factory
        public ViewModel b(Class modelClass) {
            Intrinsics.e(modelClass, "modelClass");
            try {
                Object newInstance = modelClass.getDeclaredConstructor(null).newInstance(null);
                Intrinsics.d(newInstance, "{\n                modelC…wInstance()\n            }");
                return (ViewModel) newInstance;
            } catch (IllegalAccessException e2) {
                throw new RuntimeException("Cannot create an instance of " + modelClass, e2);
            } catch (InstantiationException e3) {
                throw new RuntimeException("Cannot create an instance of " + modelClass, e3);
            } catch (NoSuchMethodException e4) {
                throw new RuntimeException("Cannot create an instance of " + modelClass, e4);
            }
        }
    }

    @Metadata
    @RestrictTo
    public static class OnRequeryFactory {
        public void c(ViewModel viewModel) {
            Intrinsics.e(viewModel, "viewModel");
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ViewModelProvider(ViewModelStore store, Factory factory) {
        this(store, factory, null, 4, null);
        Intrinsics.e(store, "store");
        Intrinsics.e(factory, "factory");
    }

    public ViewModel a(Class modelClass) {
        Intrinsics.e(modelClass, "modelClass");
        String canonicalName = modelClass.getCanonicalName();
        if (canonicalName == null) {
            throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
        }
        return b("androidx.lifecycle.ViewModelProvider.DefaultKey:" + canonicalName, modelClass);
    }

    public ViewModel b(String key, Class modelClass) {
        ViewModel b2;
        Intrinsics.e(key, "key");
        Intrinsics.e(modelClass, "modelClass");
        ViewModel b3 = this.f4400a.b(key);
        if (!modelClass.isInstance(b3)) {
            MutableCreationExtras mutableCreationExtras = new MutableCreationExtras(this.f4402c);
            mutableCreationExtras.c(NewInstanceFactory.f4411d, key);
            try {
                b2 = this.f4401b.a(modelClass, mutableCreationExtras);
            } catch (AbstractMethodError unused) {
                b2 = this.f4401b.b(modelClass);
            }
            this.f4400a.d(key, b2);
            return b2;
        }
        Object obj = this.f4401b;
        OnRequeryFactory onRequeryFactory = obj instanceof OnRequeryFactory ? (OnRequeryFactory) obj : null;
        if (onRequeryFactory != null) {
            Intrinsics.b(b3);
            onRequeryFactory.c(b3);
        }
        Intrinsics.c(b3, "null cannot be cast to non-null type T of androidx.lifecycle.ViewModelProvider.get");
        return b3;
    }

    public ViewModelProvider(ViewModelStore store, Factory factory, CreationExtras defaultCreationExtras) {
        Intrinsics.e(store, "store");
        Intrinsics.e(factory, "factory");
        Intrinsics.e(defaultCreationExtras, "defaultCreationExtras");
        this.f4400a = store;
        this.f4401b = factory;
        this.f4402c = defaultCreationExtras;
    }

    public /* synthetic */ ViewModelProvider(ViewModelStore viewModelStore, Factory factory, CreationExtras creationExtras, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(viewModelStore, factory, (i2 & 4) != 0 ? CreationExtras.Empty.f4421b : creationExtras);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ViewModelProvider(ViewModelStoreOwner owner, Factory factory) {
        this(owner.h(), factory, ViewModelProviderGetKt.a(owner));
        Intrinsics.e(owner, "owner");
        Intrinsics.e(factory, "factory");
    }
}
