package com.google.firebase.components;

import com.google.firebase.components.ComponentRuntime;
import com.google.firebase.inject.Provider;

/* loaded from: classes.dex */
final /* synthetic */ class ComponentRuntime$Builder$$Lambda$1 implements Provider {

    /* renamed from: a, reason: collision with root package name */
    private final ComponentRegistrar f15823a;

    @Override // com.google.firebase.inject.Provider
    public Object get() {
        return ComponentRuntime.Builder.d(this.f15823a);
    }
}
