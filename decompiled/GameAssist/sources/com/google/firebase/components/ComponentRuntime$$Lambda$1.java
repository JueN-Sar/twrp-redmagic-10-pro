package com.google.firebase.components;

import com.google.firebase.inject.Provider;

/* loaded from: classes.dex */
final /* synthetic */ class ComponentRuntime$$Lambda$1 implements Provider {

    /* renamed from: a, reason: collision with root package name */
    private final ComponentRuntime f15815a;

    /* renamed from: b, reason: collision with root package name */
    private final Component f15816b;

    private ComponentRuntime$$Lambda$1(ComponentRuntime componentRuntime, Component component) {
        this.f15815a = componentRuntime;
        this.f15816b = component;
    }

    public static Provider a(ComponentRuntime componentRuntime, Component component) {
        return new ComponentRuntime$$Lambda$1(componentRuntime, component);
    }

    @Override // com.google.firebase.inject.Provider
    public Object get() {
        Object a2;
        a2 = r1.d().a(new RestrictedComponentContainer(this.f15816b, this.f15815a));
        return a2;
    }
}
