package com.google.firebase.components;

import android.util.Log;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import com.google.firebase.dynamicloading.ComponentLoader;
import com.google.firebase.events.Publisher;
import com.google.firebase.events.Subscriber;
import com.google.firebase.inject.Provider;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public class ComponentRuntime extends AbstractComponentContainer implements ComponentLoader {

    /* renamed from: g, reason: collision with root package name */
    private static final Provider f15808g = ComponentRuntime$$Lambda$5.a();

    /* renamed from: a, reason: collision with root package name */
    private final Map f15809a;

    /* renamed from: b, reason: collision with root package name */
    private final Map f15810b;

    /* renamed from: c, reason: collision with root package name */
    private final Map f15811c;

    /* renamed from: d, reason: collision with root package name */
    private final List f15812d;

    /* renamed from: e, reason: collision with root package name */
    private final EventBus f15813e;

    /* renamed from: f, reason: collision with root package name */
    private final AtomicReference f15814f;

    public static final class Builder {

        /* renamed from: a, reason: collision with root package name */
        private final Executor f15824a;

        /* renamed from: b, reason: collision with root package name */
        private final List f15825b = new ArrayList();

        /* renamed from: c, reason: collision with root package name */
        private final List f15826c = new ArrayList();

        Builder(Executor executor) {
            this.f15824a = executor;
        }

        static /* synthetic */ ComponentRegistrar d(ComponentRegistrar componentRegistrar) {
            return componentRegistrar;
        }

        public Builder a(Component component) {
            this.f15826c.add(component);
            return this;
        }

        public Builder b(Collection collection) {
            this.f15825b.addAll(collection);
            return this;
        }

        public ComponentRuntime c() {
            return new ComponentRuntime(this.f15824a, this.f15825b, this.f15826c);
        }
    }

    public static Builder e(Executor executor) {
        return new Builder(executor);
    }

    private void f(List list) {
        ArrayList arrayList = new ArrayList();
        synchronized (this) {
            Iterator it = this.f15812d.iterator();
            while (it.hasNext()) {
                try {
                    ComponentRegistrar componentRegistrar = (ComponentRegistrar) ((Provider) it.next()).get();
                    if (componentRegistrar != null) {
                        list.addAll(componentRegistrar.a());
                        it.remove();
                    }
                } catch (InvalidRegistrarException e2) {
                    it.remove();
                    Log.w("ComponentDiscovery", "Invalid component registrar.", e2);
                }
            }
            if (this.f15809a.isEmpty()) {
                CycleDetector.a(list);
            } else {
                ArrayList arrayList2 = new ArrayList(this.f15809a.keySet());
                arrayList2.addAll(list);
                CycleDetector.a(arrayList2);
            }
            Iterator it2 = list.iterator();
            while (it2.hasNext()) {
                Component component = (Component) it2.next();
                this.f15809a.put(component, new Lazy(ComponentRuntime$$Lambda$1.a(this, component)));
            }
            arrayList.addAll(p(list));
            arrayList.addAll(q());
            o();
        }
        Iterator it3 = arrayList.iterator();
        while (it3.hasNext()) {
            ((Runnable) it3.next()).run();
        }
        n();
    }

    private void g(Map map, boolean z) {
        for (Map.Entry entry : map.entrySet()) {
            Component component = (Component) entry.getKey();
            Provider provider = (Provider) entry.getValue();
            if (component.h() || (component.i() && z)) {
                provider.get();
            }
        }
        this.f15813e.a();
    }

    private static List i(Iterable iterable) {
        ArrayList arrayList = new ArrayList();
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }

    static /* synthetic */ ComponentRegistrar m(ComponentRegistrar componentRegistrar) {
        return componentRegistrar;
    }

    private void n() {
        Boolean bool = (Boolean) this.f15814f.get();
        if (bool != null) {
            g(this.f15809a, bool.booleanValue());
        }
    }

    private void o() {
        for (Component component : this.f15809a.keySet()) {
            for (Dependency dependency : component.c()) {
                if (dependency.f() && !this.f15811c.containsKey(dependency.b())) {
                    this.f15811c.put(dependency.b(), LazySet.b(Collections.emptySet()));
                } else if (this.f15810b.containsKey(dependency.b())) {
                    continue;
                } else {
                    if (dependency.e()) {
                        throw new MissingDependencyException(String.format("Unsatisfied dependency for component %s: %s", component, dependency.b()));
                    }
                    if (!dependency.f()) {
                        this.f15810b.put(dependency.b(), OptionalProvider.a());
                    }
                }
            }
        }
    }

    private List p(List list) {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Component component = (Component) it.next();
            if (component.j()) {
                Provider provider = (Provider) this.f15809a.get(component);
                for (Class cls : component.e()) {
                    if (this.f15810b.containsKey(cls)) {
                        arrayList.add(ComponentRuntime$$Lambda$3.a((OptionalProvider) ((Provider) this.f15810b.get(cls)), provider));
                    } else {
                        this.f15810b.put(cls, provider);
                    }
                }
            }
        }
        return arrayList;
    }

    private List q() {
        ArrayList arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        for (Map.Entry entry : this.f15809a.entrySet()) {
            Component component = (Component) entry.getKey();
            if (!component.j()) {
                Provider provider = (Provider) entry.getValue();
                for (Class cls : component.e()) {
                    if (!hashMap.containsKey(cls)) {
                        hashMap.put(cls, new HashSet());
                    }
                    ((Set) hashMap.get(cls)).add(provider);
                }
            }
        }
        for (Map.Entry entry2 : hashMap.entrySet()) {
            if (this.f15811c.containsKey(entry2.getKey())) {
                LazySet lazySet = (LazySet) this.f15811c.get(entry2.getKey());
                Iterator it = ((Set) entry2.getValue()).iterator();
                while (it.hasNext()) {
                    arrayList.add(ComponentRuntime$$Lambda$4.a(lazySet, (Provider) it.next()));
                }
            } else {
                this.f15811c.put((Class) entry2.getKey(), LazySet.b((Collection) entry2.getValue()));
            }
        }
        return arrayList;
    }

    @Override // com.google.firebase.components.AbstractComponentContainer, com.google.firebase.components.ComponentContainer
    public /* bridge */ /* synthetic */ Object a(Class cls) {
        return super.a(cls);
    }

    @Override // com.google.firebase.components.ComponentContainer
    public synchronized Provider b(Class cls) {
        LazySet lazySet = (LazySet) this.f15811c.get(cls);
        if (lazySet != null) {
            return lazySet;
        }
        return f15808g;
    }

    @Override // com.google.firebase.components.AbstractComponentContainer, com.google.firebase.components.ComponentContainer
    public /* bridge */ /* synthetic */ Set c(Class cls) {
        return super.c(cls);
    }

    @Override // com.google.firebase.components.ComponentContainer
    public synchronized Provider d(Class cls) {
        Preconditions.c(cls, "Null interface requested.");
        return (Provider) this.f15810b.get(cls);
    }

    public void h(boolean z) {
        HashMap hashMap;
        if (this.f15814f.compareAndSet(null, Boolean.valueOf(z))) {
            synchronized (this) {
                hashMap = new HashMap(this.f15809a);
            }
            g(hashMap, z);
        }
    }

    @RestrictTo
    @VisibleForTesting
    public void initializeAllComponentsForTests() {
        Iterator it = this.f15809a.values().iterator();
        while (it.hasNext()) {
            ((Provider) it.next()).get();
        }
    }

    private ComponentRuntime(Executor executor, Iterable iterable, Collection collection) {
        this.f15809a = new HashMap();
        this.f15810b = new HashMap();
        this.f15811c = new HashMap();
        this.f15814f = new AtomicReference();
        EventBus eventBus = new EventBus(executor);
        this.f15813e = eventBus;
        ArrayList arrayList = new ArrayList();
        arrayList.add(Component.n(eventBus, EventBus.class, Subscriber.class, Publisher.class));
        arrayList.add(Component.n(this, ComponentLoader.class, new Class[0]));
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            Component component = (Component) it.next();
            if (component != null) {
                arrayList.add(component);
            }
        }
        this.f15812d = i(iterable);
        f(arrayList);
    }
}
