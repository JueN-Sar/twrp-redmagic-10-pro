package androidx.savedstate;

import android.os.Bundle;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.savedstate.SavedStateRegistry;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata
/* loaded from: classes.dex */
public final class Recreator implements LifecycleEventObserver {

    /* renamed from: h, reason: collision with root package name */
    public static final Companion f5333h = new Companion(null);

    /* renamed from: c, reason: collision with root package name */
    private final SavedStateRegistryOwner f5334c;

    @Metadata
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata
    public static final class SavedStateProvider implements SavedStateRegistry.SavedStateProvider {

        /* renamed from: a, reason: collision with root package name */
        private final Set f5335a;

        public SavedStateProvider(SavedStateRegistry registry) {
            Intrinsics.e(registry, "registry");
            this.f5335a = new LinkedHashSet();
            registry.h("androidx.savedstate.Restarter", this);
        }

        @Override // androidx.savedstate.SavedStateRegistry.SavedStateProvider
        public Bundle a() {
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("classes_to_restore", new ArrayList<>(this.f5335a));
            return bundle;
        }

        public final void b(String className) {
            Intrinsics.e(className, "className");
            this.f5335a.add(className);
        }
    }

    public Recreator(SavedStateRegistryOwner owner) {
        Intrinsics.e(owner, "owner");
        this.f5334c = owner;
    }

    private final void b(String str) {
        try {
            Class<? extends U> asSubclass = Class.forName(str, false, Recreator.class.getClassLoader()).asSubclass(SavedStateRegistry.AutoRecreated.class);
            Intrinsics.d(asSubclass, "{\n                Class.…class.java)\n            }");
            try {
                Constructor declaredConstructor = asSubclass.getDeclaredConstructor(null);
                declaredConstructor.setAccessible(true);
                try {
                    Object newInstance = declaredConstructor.newInstance(null);
                    Intrinsics.d(newInstance, "{\n                constr…wInstance()\n            }");
                    ((SavedStateRegistry.AutoRecreated) newInstance).a(this.f5334c);
                } catch (Exception e2) {
                    throw new RuntimeException("Failed to instantiate " + str, e2);
                }
            } catch (NoSuchMethodException e3) {
                throw new IllegalStateException("Class " + asSubclass.getSimpleName() + " must have default constructor in order to be automatically recreated", e3);
            }
        } catch (ClassNotFoundException e4) {
            throw new RuntimeException("Class " + str + " wasn't found", e4);
        }
    }

    @Override // androidx.lifecycle.LifecycleEventObserver
    public void c(LifecycleOwner source, Lifecycle.Event event) {
        Intrinsics.e(source, "source");
        Intrinsics.e(event, "event");
        if (event != Lifecycle.Event.ON_CREATE) {
            throw new AssertionError("Next event must be ON_CREATE");
        }
        source.a().c(this);
        Bundle b2 = this.f5334c.i().b("androidx.savedstate.Restarter");
        if (b2 == null) {
            return;
        }
        ArrayList<String> stringArrayList = b2.getStringArrayList("classes_to_restore");
        if (stringArrayList == null) {
            throw new IllegalStateException("Bundle with restored state for the component \"androidx.savedstate.Restarter\" must contain list of strings by the key \"classes_to_restore\"");
        }
        Iterator<String> it = stringArrayList.iterator();
        while (it.hasNext()) {
            b(it.next());
        }
    }
}
