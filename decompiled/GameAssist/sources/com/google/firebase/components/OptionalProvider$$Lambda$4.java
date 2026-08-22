package com.google.firebase.components;

import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;

/* loaded from: classes.dex */
final /* synthetic */ class OptionalProvider$$Lambda$4 implements Deferred.DeferredHandler {

    /* renamed from: a, reason: collision with root package name */
    private static final OptionalProvider$$Lambda$4 f15851a = new OptionalProvider$$Lambda$4();

    private OptionalProvider$$Lambda$4() {
    }

    public static Deferred.DeferredHandler b() {
        return f15851a;
    }

    @Override // com.google.firebase.inject.Deferred.DeferredHandler
    public void a(Provider provider) {
        OptionalProvider.b(provider);
    }
}
