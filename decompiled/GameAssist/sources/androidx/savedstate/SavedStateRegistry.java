package androidx.savedstate;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.arch.core.internal.SafeIterableMap;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.savedstate.Recreator;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SuppressLint({"RestrictedApi"})
@SourceDebugExtension
/* loaded from: classes.dex */
public final class SavedStateRegistry {

    /* renamed from: g, reason: collision with root package name */
    private static final Companion f5336g = new Companion(null);

    /* renamed from: b, reason: collision with root package name */
    private boolean f5338b;

    /* renamed from: c, reason: collision with root package name */
    private Bundle f5339c;

    /* renamed from: d, reason: collision with root package name */
    private boolean f5340d;

    /* renamed from: e, reason: collision with root package name */
    private Recreator.SavedStateProvider f5341e;

    /* renamed from: a, reason: collision with root package name */
    private final SafeIterableMap f5337a = new SafeIterableMap();

    /* renamed from: f, reason: collision with root package name */
    private boolean f5342f = true;

    @Metadata
    public interface AutoRecreated {
        void a(SavedStateRegistryOwner savedStateRegistryOwner);
    }

    @Metadata
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata
    public interface SavedStateProvider {
        Bundle a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void d(SavedStateRegistry this$0, LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        Intrinsics.e(this$0, "this$0");
        Intrinsics.e(lifecycleOwner, "<anonymous parameter 0>");
        Intrinsics.e(event, "event");
        if (event == Lifecycle.Event.ON_START) {
            this$0.f5342f = true;
        } else if (event == Lifecycle.Event.ON_STOP) {
            this$0.f5342f = false;
        }
    }

    public final Bundle b(String key) {
        Intrinsics.e(key, "key");
        if (!this.f5340d) {
            throw new IllegalStateException("You can consumeRestoredStateForKey only after super.onCreate of corresponding component".toString());
        }
        Bundle bundle = this.f5339c;
        if (bundle == null) {
            return null;
        }
        Bundle bundle2 = bundle != null ? bundle.getBundle(key) : null;
        Bundle bundle3 = this.f5339c;
        if (bundle3 != null) {
            bundle3.remove(key);
        }
        Bundle bundle4 = this.f5339c;
        if (bundle4 == null || bundle4.isEmpty()) {
            this.f5339c = null;
        }
        return bundle2;
    }

    public final SavedStateProvider c(String key) {
        Intrinsics.e(key, "key");
        Iterator it = this.f5337a.iterator();
        while (it.hasNext()) {
            Map.Entry components = (Map.Entry) it.next();
            Intrinsics.d(components, "components");
            String str = (String) components.getKey();
            SavedStateProvider savedStateProvider = (SavedStateProvider) components.getValue();
            if (Intrinsics.a(str, key)) {
                return savedStateProvider;
            }
        }
        return null;
    }

    public final void e(Lifecycle lifecycle) {
        Intrinsics.e(lifecycle, "lifecycle");
        if (!(!this.f5338b)) {
            throw new IllegalStateException("SavedStateRegistry was already attached.".toString());
        }
        lifecycle.a(new LifecycleEventObserver() { // from class: androidx.savedstate.a
            @Override // androidx.lifecycle.LifecycleEventObserver
            public final void c(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
                SavedStateRegistry.d(SavedStateRegistry.this, lifecycleOwner, event);
            }
        });
        this.f5338b = true;
    }

    public final void f(Bundle bundle) {
        if (!this.f5338b) {
            throw new IllegalStateException("You must call performAttach() before calling performRestore(Bundle).".toString());
        }
        if (!(!this.f5340d)) {
            throw new IllegalStateException("SavedStateRegistry was already restored.".toString());
        }
        this.f5339c = bundle != null ? bundle.getBundle("androidx.lifecycle.BundlableSavedStateRegistry.key") : null;
        this.f5340d = true;
    }

    public final void g(Bundle outBundle) {
        Intrinsics.e(outBundle, "outBundle");
        Bundle bundle = new Bundle();
        Bundle bundle2 = this.f5339c;
        if (bundle2 != null) {
            bundle.putAll(bundle2);
        }
        SafeIterableMap.IteratorWithAdditions f2 = this.f5337a.f();
        Intrinsics.d(f2, "this.components.iteratorWithAdditions()");
        while (f2.hasNext()) {
            Map.Entry next = f2.next();
            bundle.putBundle((String) next.getKey(), ((SavedStateProvider) next.getValue()).a());
        }
        if (bundle.isEmpty()) {
            return;
        }
        outBundle.putBundle("androidx.lifecycle.BundlableSavedStateRegistry.key", bundle);
    }

    public final void h(String key, SavedStateProvider provider) {
        Intrinsics.e(key, "key");
        Intrinsics.e(provider, "provider");
        if (((SavedStateProvider) this.f5337a.i(key, provider)) != null) {
            throw new IllegalArgumentException("SavedStateProvider with the given key is already registered".toString());
        }
    }

    public final void i(Class clazz) {
        Intrinsics.e(clazz, "clazz");
        if (!this.f5342f) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState".toString());
        }
        Recreator.SavedStateProvider savedStateProvider = this.f5341e;
        if (savedStateProvider == null) {
            savedStateProvider = new Recreator.SavedStateProvider(this);
        }
        this.f5341e = savedStateProvider;
        try {
            clazz.getDeclaredConstructor(null);
            Recreator.SavedStateProvider savedStateProvider2 = this.f5341e;
            if (savedStateProvider2 != null) {
                String name = clazz.getName();
                Intrinsics.d(name, "clazz.name");
                savedStateProvider2.b(name);
            }
        } catch (NoSuchMethodException e2) {
            throw new IllegalArgumentException("Class " + clazz.getSimpleName() + " must have default constructor in order to be automatically recreated", e2);
        }
    }
}
