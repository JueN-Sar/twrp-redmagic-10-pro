package com.google.firebase.components;

/* loaded from: classes.dex */
final /* synthetic */ class Component$$Lambda$2 implements ComponentFactory {

    /* renamed from: a, reason: collision with root package name */
    private final Object f15796a;

    private Component$$Lambda$2(Object obj) {
        this.f15796a = obj;
    }

    public static ComponentFactory b(Object obj) {
        return new Component$$Lambda$2(obj);
    }

    @Override // com.google.firebase.components.ComponentFactory
    public Object a(ComponentContainer componentContainer) {
        return Component.m(this.f15796a, componentContainer);
    }
}
