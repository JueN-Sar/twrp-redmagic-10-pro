package com.google.firebase.components;

import com.google.firebase.events.Publisher;
import com.google.firebase.inject.Provider;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes.dex */
final class RestrictedComponentContainer extends AbstractComponentContainer {

    /* renamed from: a, reason: collision with root package name */
    private final Set f15853a;

    /* renamed from: b, reason: collision with root package name */
    private final Set f15854b;

    /* renamed from: c, reason: collision with root package name */
    private final Set f15855c;

    /* renamed from: d, reason: collision with root package name */
    private final Set f15856d;

    /* renamed from: e, reason: collision with root package name */
    private final Set f15857e;

    /* renamed from: f, reason: collision with root package name */
    private final Set f15858f;

    /* renamed from: g, reason: collision with root package name */
    private final ComponentContainer f15859g;

    private static class RestrictedPublisher implements Publisher {

        /* renamed from: a, reason: collision with root package name */
        private final Set f15860a;

        /* renamed from: b, reason: collision with root package name */
        private final Publisher f15861b;

        public RestrictedPublisher(Set set, Publisher publisher) {
            this.f15860a = set;
            this.f15861b = publisher;
        }
    }

    RestrictedComponentContainer(Component component, ComponentContainer componentContainer) {
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        HashSet hashSet3 = new HashSet();
        HashSet hashSet4 = new HashSet();
        HashSet hashSet5 = new HashSet();
        for (Dependency dependency : component.c()) {
            if (dependency.d()) {
                if (dependency.f()) {
                    hashSet4.add(dependency.b());
                } else {
                    hashSet.add(dependency.b());
                }
            } else if (dependency.c()) {
                hashSet3.add(dependency.b());
            } else if (dependency.f()) {
                hashSet5.add(dependency.b());
            } else {
                hashSet2.add(dependency.b());
            }
        }
        if (!component.f().isEmpty()) {
            hashSet.add(Publisher.class);
        }
        this.f15853a = Collections.unmodifiableSet(hashSet);
        this.f15854b = Collections.unmodifiableSet(hashSet2);
        this.f15855c = Collections.unmodifiableSet(hashSet3);
        this.f15856d = Collections.unmodifiableSet(hashSet4);
        this.f15857e = Collections.unmodifiableSet(hashSet5);
        this.f15858f = component.f();
        this.f15859g = componentContainer;
    }

    @Override // com.google.firebase.components.AbstractComponentContainer, com.google.firebase.components.ComponentContainer
    public Object a(Class cls) {
        if (!this.f15853a.contains(cls)) {
            throw new DependencyException(String.format("Attempting to request an undeclared dependency %s.", cls));
        }
        Object a2 = this.f15859g.a(cls);
        return !cls.equals(Publisher.class) ? a2 : new RestrictedPublisher(this.f15858f, (Publisher) a2);
    }

    @Override // com.google.firebase.components.ComponentContainer
    public Provider b(Class cls) {
        if (this.f15857e.contains(cls)) {
            return this.f15859g.b(cls);
        }
        throw new DependencyException(String.format("Attempting to request an undeclared dependency Provider<Set<%s>>.", cls));
    }

    @Override // com.google.firebase.components.AbstractComponentContainer, com.google.firebase.components.ComponentContainer
    public Set c(Class cls) {
        if (this.f15856d.contains(cls)) {
            return this.f15859g.c(cls);
        }
        throw new DependencyException(String.format("Attempting to request an undeclared dependency Set<%s>.", cls));
    }

    @Override // com.google.firebase.components.ComponentContainer
    public Provider d(Class cls) {
        if (this.f15854b.contains(cls)) {
            return this.f15859g.d(cls);
        }
        throw new DependencyException(String.format("Attempting to request an undeclared dependency Provider<%s>.", cls));
    }
}
