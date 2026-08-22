package com.google.firebase.components;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes.dex */
public final class Component<T> {

    /* renamed from: a, reason: collision with root package name */
    private final Set f15789a;

    /* renamed from: b, reason: collision with root package name */
    private final Set f15790b;

    /* renamed from: c, reason: collision with root package name */
    private final int f15791c;

    /* renamed from: d, reason: collision with root package name */
    private final int f15792d;

    /* renamed from: e, reason: collision with root package name */
    private final ComponentFactory f15793e;

    /* renamed from: f, reason: collision with root package name */
    private final Set f15794f;

    public static class Builder<T> {

        /* renamed from: a, reason: collision with root package name */
        private final Set f15798a;

        /* renamed from: b, reason: collision with root package name */
        private final Set f15799b;

        /* renamed from: c, reason: collision with root package name */
        private int f15800c;

        /* renamed from: d, reason: collision with root package name */
        private int f15801d;

        /* renamed from: e, reason: collision with root package name */
        private ComponentFactory f15802e;

        /* renamed from: f, reason: collision with root package name */
        private Set f15803f;

        /* JADX INFO: Access modifiers changed from: private */
        public Builder e() {
            this.f15801d = 1;
            return this;
        }

        private void f(Class cls) {
            Preconditions.a(!this.f15798a.contains(cls), "Components are not allowed to depend on interfaces they themselves provide.");
        }

        public Builder b(Dependency dependency) {
            Preconditions.c(dependency, "Null dependency");
            f(dependency.b());
            this.f15799b.add(dependency);
            return this;
        }

        public Component c() {
            Preconditions.d(this.f15802e != null, "Missing required property: factory.");
            return new Component(new HashSet(this.f15798a), new HashSet(this.f15799b), this.f15800c, this.f15801d, this.f15802e, this.f15803f);
        }

        public Builder d(ComponentFactory componentFactory) {
            this.f15802e = (ComponentFactory) Preconditions.c(componentFactory, "Null factory");
            return this;
        }

        private Builder(Class cls, Class... clsArr) {
            HashSet hashSet = new HashSet();
            this.f15798a = hashSet;
            this.f15799b = new HashSet();
            this.f15800c = 0;
            this.f15801d = 0;
            this.f15803f = new HashSet();
            Preconditions.c(cls, "Null interface");
            hashSet.add(cls);
            for (Class cls2 : clsArr) {
                Preconditions.c(cls2, "Null interface");
            }
            Collections.addAll(this.f15798a, clsArr);
        }
    }

    public static Builder a(Class cls) {
        return new Builder(cls, new Class[0]);
    }

    public static Builder b(Class cls, Class... clsArr) {
        return new Builder(cls, clsArr);
    }

    public static Builder g(Class cls) {
        return a(cls).e();
    }

    static /* synthetic */ Object k(Object obj, ComponentContainer componentContainer) {
        return obj;
    }

    static /* synthetic */ Object l(Object obj, ComponentContainer componentContainer) {
        return obj;
    }

    static /* synthetic */ Object m(Object obj, ComponentContainer componentContainer) {
        return obj;
    }

    public static Component n(Object obj, Class cls, Class... clsArr) {
        return b(cls, clsArr).d(Component$$Lambda$2.b(obj)).c();
    }

    public Set c() {
        return this.f15790b;
    }

    public ComponentFactory d() {
        return this.f15793e;
    }

    public Set e() {
        return this.f15789a;
    }

    public Set f() {
        return this.f15794f;
    }

    public boolean h() {
        return this.f15791c == 1;
    }

    public boolean i() {
        return this.f15791c == 2;
    }

    public boolean j() {
        return this.f15792d == 0;
    }

    public String toString() {
        return "Component<" + Arrays.toString(this.f15789a.toArray()) + ">{" + this.f15791c + ", type=" + this.f15792d + ", deps=" + Arrays.toString(this.f15790b.toArray()) + "}";
    }

    private Component(Set set, Set set2, int i2, int i3, ComponentFactory componentFactory, Set set3) {
        this.f15789a = Collections.unmodifiableSet(set);
        this.f15790b = Collections.unmodifiableSet(set2);
        this.f15791c = i2;
        this.f15792d = i3;
        this.f15793e = componentFactory;
        this.f15794f = Collections.unmodifiableSet(set3);
    }
}
