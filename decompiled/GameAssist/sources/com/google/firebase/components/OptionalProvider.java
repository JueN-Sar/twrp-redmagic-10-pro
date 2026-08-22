package com.google.firebase.components;

import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;

/* loaded from: classes.dex */
class OptionalProvider<T> implements Provider<T>, Deferred<T> {

    /* renamed from: c, reason: collision with root package name */
    private static final Deferred.DeferredHandler f15845c = OptionalProvider$$Lambda$4.b();

    /* renamed from: d, reason: collision with root package name */
    private static final Provider f15846d = OptionalProvider$$Lambda$5.a();

    /* renamed from: a, reason: collision with root package name */
    private Deferred.DeferredHandler f15847a;

    /* renamed from: b, reason: collision with root package name */
    private volatile Provider f15848b;

    private OptionalProvider(Deferred.DeferredHandler deferredHandler, Provider provider) {
        this.f15847a = deferredHandler;
        this.f15848b = provider;
    }

    static OptionalProvider a() {
        return new OptionalProvider(f15845c, f15846d);
    }

    static /* synthetic */ void b(Provider provider) {
    }

    static /* synthetic */ Object c() {
        return null;
    }

    static /* synthetic */ void d(Deferred.DeferredHandler deferredHandler, Deferred.DeferredHandler deferredHandler2, Provider provider) {
        deferredHandler.a(provider);
        deferredHandler2.a(provider);
    }

    void e(Provider provider) {
        Deferred.DeferredHandler deferredHandler;
        if (this.f15848b != f15846d) {
            throw new IllegalStateException("provide() can be called only once.");
        }
        synchronized (this) {
            deferredHandler = this.f15847a;
            this.f15847a = null;
            this.f15848b = provider;
        }
        deferredHandler.a(provider);
    }

    @Override // com.google.firebase.inject.Provider
    public Object get() {
        return this.f15848b.get();
    }
}
