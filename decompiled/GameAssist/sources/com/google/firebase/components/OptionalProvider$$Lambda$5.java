package com.google.firebase.components;

import com.google.firebase.inject.Provider;

/* loaded from: classes.dex */
final /* synthetic */ class OptionalProvider$$Lambda$5 implements Provider {

    /* renamed from: a, reason: collision with root package name */
    private static final OptionalProvider$$Lambda$5 f15852a = new OptionalProvider$$Lambda$5();

    private OptionalProvider$$Lambda$5() {
    }

    public static Provider a() {
        return f15852a;
    }

    @Override // com.google.firebase.inject.Provider
    public Object get() {
        return OptionalProvider.c();
    }
}
