package com.google.firebase.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: classes.dex */
class CycleDetector {

    private static class ComponentNode {

        /* renamed from: a, reason: collision with root package name */
        private final Component f15827a;

        /* renamed from: b, reason: collision with root package name */
        private final Set f15828b = new HashSet();

        /* renamed from: c, reason: collision with root package name */
        private final Set f15829c = new HashSet();

        ComponentNode(Component component) {
            this.f15827a = component;
        }

        void a(ComponentNode componentNode) {
            this.f15828b.add(componentNode);
        }

        void b(ComponentNode componentNode) {
            this.f15829c.add(componentNode);
        }

        Component c() {
            return this.f15827a;
        }

        Set d() {
            return this.f15828b;
        }

        boolean e() {
            return this.f15828b.isEmpty();
        }

        boolean f() {
            return this.f15829c.isEmpty();
        }

        void g(ComponentNode componentNode) {
            this.f15829c.remove(componentNode);
        }
    }

    private static class Dep {

        /* renamed from: a, reason: collision with root package name */
        private final Class f15830a;

        /* renamed from: b, reason: collision with root package name */
        private final boolean f15831b;

        public boolean equals(Object obj) {
            if (!(obj instanceof Dep)) {
                return false;
            }
            Dep dep = (Dep) obj;
            return dep.f15830a.equals(this.f15830a) && dep.f15831b == this.f15831b;
        }

        public int hashCode() {
            return Boolean.valueOf(this.f15831b).hashCode() ^ ((this.f15830a.hashCode() ^ 1000003) * 1000003);
        }

        private Dep(Class cls, boolean z) {
            this.f15830a = cls;
            this.f15831b = z;
        }
    }

    static void a(List list) {
        Set<ComponentNode> c2 = c(list);
        Set b2 = b(c2);
        int i2 = 0;
        while (!b2.isEmpty()) {
            ComponentNode componentNode = (ComponentNode) b2.iterator().next();
            b2.remove(componentNode);
            i2++;
            for (ComponentNode componentNode2 : componentNode.d()) {
                componentNode2.g(componentNode);
                if (componentNode2.f()) {
                    b2.add(componentNode2);
                }
            }
        }
        if (i2 == list.size()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (ComponentNode componentNode3 : c2) {
            if (!componentNode3.f() && !componentNode3.e()) {
                arrayList.add(componentNode3.c());
            }
        }
        throw new DependencyCycleException(arrayList);
    }

    private static Set b(Set set) {
        HashSet hashSet = new HashSet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            ComponentNode componentNode = (ComponentNode) it.next();
            if (componentNode.f()) {
                hashSet.add(componentNode);
            }
        }
        return hashSet;
    }

    private static Set c(List list) {
        Set<ComponentNode> set;
        HashMap hashMap = new HashMap(list.size());
        Iterator it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                Iterator it2 = hashMap.values().iterator();
                while (it2.hasNext()) {
                    for (ComponentNode componentNode : (Set) it2.next()) {
                        for (Dependency dependency : componentNode.c().c()) {
                            if (dependency.d() && (set = (Set) hashMap.get(new Dep(dependency.b(), dependency.f()))) != null) {
                                for (ComponentNode componentNode2 : set) {
                                    componentNode.a(componentNode2);
                                    componentNode2.b(componentNode);
                                }
                            }
                        }
                    }
                }
                HashSet hashSet = new HashSet();
                Iterator it3 = hashMap.values().iterator();
                while (it3.hasNext()) {
                    hashSet.addAll((Set) it3.next());
                }
                return hashSet;
            }
            Component component = (Component) it.next();
            ComponentNode componentNode3 = new ComponentNode(component);
            for (Class cls : component.e()) {
                Dep dep = new Dep(cls, !component.j());
                if (!hashMap.containsKey(dep)) {
                    hashMap.put(dep, new HashSet());
                }
                Set set2 = (Set) hashMap.get(dep);
                if (!set2.isEmpty() && !dep.f15831b) {
                    throw new IllegalArgumentException(String.format("Multiple components provide %s.", cls));
                }
                set2.add(componentNode3);
            }
        }
    }
}
