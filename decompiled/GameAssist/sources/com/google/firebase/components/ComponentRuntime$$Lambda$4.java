package com.google.firebase.components;

import com.google.firebase.inject.Provider;

/* loaded from: classes.dex */
final /* synthetic */ class ComponentRuntime$$Lambda$4 implements Runnable {

    /* renamed from: c, reason: collision with root package name */
    private final LazySet f15820c;

    /* renamed from: h, reason: collision with root package name */
    private final Provider f15821h;

    private ComponentRuntime$$Lambda$4(LazySet lazySet, Provider provider) {
        this.f15820c = lazySet;
        this.f15821h = provider;
    }

    public static Runnable a(LazySet lazySet, Provider provider) {
        return new ComponentRuntime$$Lambda$4(lazySet, provider);
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f15820c.a(this.f15821h);
    }
}
