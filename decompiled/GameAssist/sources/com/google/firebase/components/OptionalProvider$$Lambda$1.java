package com.google.firebase.components;

import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;

/* loaded from: classes.dex */
final /* synthetic */ class OptionalProvider$$Lambda$1 implements Deferred.DeferredHandler {

    /* renamed from: a, reason: collision with root package name */
    private final Deferred.DeferredHandler f15849a;

    /* renamed from: b, reason: collision with root package name */
    private final Deferred.DeferredHandler f15850b;

    @Override // com.google.firebase.inject.Deferred.DeferredHandler
    public void a(Provider provider) {
        OptionalProvider.d(this.f15849a, this.f15850b, provider);
    }
}
