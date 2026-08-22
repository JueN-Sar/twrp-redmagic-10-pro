package com.google.firebase.components;

import com.google.firebase.inject.Provider;

/* loaded from: classes.dex */
final /* synthetic */ class ComponentRuntime$$Lambda$3 implements Runnable {

    /* renamed from: c, reason: collision with root package name */
    private final OptionalProvider f15818c;

    /* renamed from: h, reason: collision with root package name */
    private final Provider f15819h;

    private ComponentRuntime$$Lambda$3(OptionalProvider optionalProvider, Provider provider) {
        this.f15818c = optionalProvider;
        this.f15819h = provider;
    }

    public static Runnable a(OptionalProvider optionalProvider, Provider provider) {
        return new ComponentRuntime$$Lambda$3(optionalProvider, provider);
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f15818c.e(this.f15819h);
    }
}
