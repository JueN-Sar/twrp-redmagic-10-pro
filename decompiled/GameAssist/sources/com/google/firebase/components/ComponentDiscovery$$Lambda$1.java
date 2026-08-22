package com.google.firebase.components;

import com.google.firebase.inject.Provider;

/* loaded from: classes.dex */
final /* synthetic */ class ComponentDiscovery$$Lambda$1 implements Provider {

    /* renamed from: a, reason: collision with root package name */
    private final String f15806a;

    private ComponentDiscovery$$Lambda$1(String str) {
        this.f15806a = str;
    }

    public static Provider a(String str) {
        return new ComponentDiscovery$$Lambda$1(str);
    }

    @Override // com.google.firebase.inject.Provider
    public Object get() {
        ComponentRegistrar c2;
        c2 = ComponentDiscovery.c(this.f15806a);
        return c2;
    }
}
